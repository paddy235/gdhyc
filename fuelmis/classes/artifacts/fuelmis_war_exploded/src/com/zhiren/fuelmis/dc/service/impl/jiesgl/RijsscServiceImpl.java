package com.zhiren.fuelmis.dc.service.impl.jiesgl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import com.zhiren.fuelmis.dc.dao.jiesgl.RijsscDao;
import com.zhiren.fuelmis.dc.service.jiesgl.IRijsscService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RijsscServiceImpl implements IRijsscService {

	@Resource
	private RijsscDao rijsscDao;
	
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Map<String , Object>> getRijscx(Map<String, Object> map) {
		return rijsscDao.getRijscx(map);
	}

	@Override@Transactional
	public void del(List<Object> list) {
		String samcodes="-1";
		for (Object samcode: list) {
			samcodes+=","+samcode;
		}
		String chepbids="SELECT id from RL_YS_CHEPB where SAMCODE in ("+samcodes+")";
		String rukdbhs="select DISTINCT rukdbh from GX_CHURKDB_YUNSDJ g inner join RL_YS_CHEPB c on g.YUANDJ_ID=c.id where c.SAMCODE in("+samcodes+")";
        String jiesdids="select DISTINCT jiesdb_id  from GX_JIESDB_CHEPB g inner join RL_YS_CHEPB c on g.CHEPB_ID=c.id where c.SAMCODE in(" + samcodes + ")";
		String churkdbid=" select k.id from RL_KC_CHURKDB k\n" +
                "      inner join GX_CHURKDB_YUNSDJ g on k.RUKDBH=g.RUKDBH and k.HANGH=g.HANGH and g.YUANDJLX=1\n" +
                "      inner join RL_YS_CHEPB c on c.id=g.YUANDJ_ID where c.SAMCODE in("+samcodes+")";
        String sql="BEGIN\n" +
                "delete from GX_CHURKDB_YUNSDJ where YUANDJ_ID  in ("+chepbids+");\n" +
				"delete from rl_kc_churkdb where rukdbh in ("+rukdbhs+");\n" +
                "delete from GX_JIESDB_CHEPB where CHEPB_ID in ("+chepbids+");\n" +
				"delete from RL_JS_RIJSDB where id in ("+jiesdids+");\n" +
                "delete from GX_CHURUK_HUAYBH where DANJB_ID in ("+churkdbid+");\n" +
                "delete from RL_KC_HUAYBGB where id in(select HUAYBG_ID from GX_CHURUK_HUAYBH where  DANJB_ID in  ("+churkdbid+"));\n"+
				"end;";
		jdbcTemplate.update(sql);
	}

	@Override
	public void delRijsd(List<Map<String, Object>> list) {
		String jiesbhs="'0'";
        for (Map<String, Object> aList : list) {
            jiesbhs += ",'" + aList.get("JIESBH") + "'";
        }
		String rukdbhs="'0'";
        for (Map<String, Object> aList : list) {
            rukdbhs += ",'" + aList.get("RUKDBH") + "'";
        }
		String sql="BEGIN\n" +
				"delete from rl_kc_churkdb where rukdbh in ("+rukdbhs+");\n" +
				"delete from GX_CHURKDB_YUNSDJ where rukdbh  in ("+rukdbhs+");\n" +
				"delete from GX_JIESDB_CHEPB where CHEPB_ID in (SELECT id from RL_JS_RIJSDB where jiesbh in ("+jiesbhs+"));\n" +
				"delete from RL_JS_RIJSDB where jiesbh in ("+jiesbhs+");\n" +
				"end;";
		jdbcTemplate.update(sql);
	}

}
