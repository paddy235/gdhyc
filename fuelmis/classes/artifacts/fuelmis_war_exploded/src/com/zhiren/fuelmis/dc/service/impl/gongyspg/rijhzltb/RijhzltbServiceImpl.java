package com.zhiren.fuelmis.dc.service.impl.gongyspg.rijhzltb;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhiren.fuelmis.dc.dao.gongyspg.gongmjhzl.GongmjhtzDao;
import com.zhiren.fuelmis.dc.dao.gongyspg.gongmjhzl.GongmjhzlDao;
import com.zhiren.fuelmis.dc.dao.gongyspg.pingggl.RizbpfDao;
import com.zhiren.fuelmis.dc.dao.gongyspg.rijhzltb.RijhzltbDao;
import com.zhiren.fuelmis.dc.service.gongyspg.gongmjhzl.GongmjhtzService;
import com.zhiren.fuelmis.dc.service.gongyspg.gongmjhzl.GongmjhzlService;
import com.zhiren.fuelmis.dc.service.gongyspg.rijhzltb.RijhzltbService;
import com.zhiren.fuelmis.dc.utils.Sequence;

//import cryptix.jce.provider.cipher.Square;

@Service("rijhzltbService")
public class RijhzltbServiceImpl implements RijhzltbService{
	
	@Resource
	private RijhzltbDao rijhzltb;
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Map<String , Object>> getRijhzltbAll(Map<String, Object> map) {
		List<Map<String , Object>> list = rijhzltb.getRijhzltbAll(map);
		JSONArray result=JSONArray.fromObject(list);
		return result;
	}

	@Override
	@Transactional
	public void insertRijhzltb(JSONArray jsonArray) {
		StringBuffer sql=new StringBuffer(" ");
		 List<Map<String,Object>> list =JSONArray.fromObject(jsonArray);
		 for (Map<String, Object> map : list) {
			 String rigmzzb_id="";
			if("".equals(map.get("ZBID")) || map.get("ZBID")==null){
				//没有日供煤指标信息
				rigmzzb_id = Sequence.nextId();
				sql.append("insert into rl_gys_rigmzbb(id,rigmjhb_id) values ("+rigmzzb_id+","+map.get("GMID")+");\n");
				//sql.append("update rl_gys_rigmjhb set ")
			}else{
				System.out.println(map.get("ZBID"));
				rigmzzb_id = map.get("ZBID").toString();
			}
			 sql.append("delete from rl_gys_rigmzbmxb where rigmzbb_id="+rigmzzb_id+";\n");
			 sql.append("insert into rl_gys_rigmzbmxb(id,rigmzbb_id,zhibdm,zhibz)");
			 StringBuffer sql2 = new StringBuffer();
			 sql2.append(Sequence.nextId());
			 sql2.append(",").append(rigmzzb_id);
			 sql2.append(",'QNET_AR'");
			 sql2.append(",").append(map.get("ZHIBZ1"));
			 sql.append(" values(").append(sql2).append(");\n");
			 
			 StringBuffer sql3 = new StringBuffer();
			 sql3.append("insert into rl_gys_rigmzbmxb(id,rigmzbb_id,zhibdm,zhibz)  values(");
			 sql3.append(Sequence.nextId());
			 sql3.append(",").append(rigmzzb_id);
			 sql3.append(",'STAR'");
			 sql3.append(",").append(map.get("ZHIBZ2"));
			 sql.append("").append(sql3).append(");\n");
			 
		 }
		 String str = sql.toString();
		 str = "begin \n"+str+ "end;";
		jdbcTemplate.update(str);
	}

	@Override
	public void updateRijhzltb(JSONArray jsonArray) {
		for(int i=0;i<jsonArray.size();i++){
			JSONObject obj = jsonArray.getJSONObject(i);
			Map<String,Object> map=obj.fromObject(obj);
			rijhzltb.updateRijhzltb(map);
		}
	}
}
