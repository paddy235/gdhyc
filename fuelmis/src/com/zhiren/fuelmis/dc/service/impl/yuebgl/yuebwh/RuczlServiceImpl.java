package com.zhiren.fuelmis.dc.service.impl.yuebgl.yuebwh;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhiren.fuelmis.dc.dao.yuebgl.yuebwh.RuczlDao;
import com.zhiren.fuelmis.dc.service.yuebgl.yuebwh.IRuczlService;
import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.Sequence;
import com.zhiren.fuelmis.dc.utils.TreeUtil;
import com.zhiren.fuelmis.dc.utils.formular.Huayz;

/**
 * @author 陈宝露
 */
@Service
public class RuczlServiceImpl implements IRuczlService {

    @Autowired
    private RuczlDao ruczlDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public JSONObject getAll(Map<String, Object> map) {
        // TODO Auto-generated method stub
        Map<String, Object> jsonMap = new HashMap<String, Object>();

        List<Map<String, Object>> list = ruczlDao.getAll(map);
        jsonMap.put("data", list);

        JSONObject result = JSONObject.fromObject(jsonMap);
        return result;
    }

    @Override
    @Transactional
    public JSONArray createData(Map<String, Object> map) {
        JSONArray jsonArray = new JSONArray();
        String dianc = map.get("dianc").toString();
        String riq = map.get("riq").toString();
        String lastMonth = DateUtil.getLastMonthString(riq);
        map.put("lastMonth", lastMonth);
        int intYuef = Integer.parseInt(riq.substring(5, 7));
        
        String yuebksrq = "";
		String yuebjzrq = "";
		
		String sql_ybq = "select  y.yuebjq,y.yuebksrq,y.yuebjzrq  from yuebsjpzb  y where y.yuebjq = '" + map.get("riq") + "' ";
		
		List<Map<String, Object>> list_ybq = jdbcTemplate.queryForList(sql_ybq);
		if (list_ybq != null && list_ybq.size() > 0) {
			for (int i = 0; i < list_ybq.size(); i++) {
				yuebksrq = list_ybq.get(i).get("YUEBKSRQ").toString();
			    yuebjzrq = list_ybq.get(i).get("YUEBJZRQ").toString();	    
			}
		}
		map.put("yuebksrq", yuebksrq);
		map.put("yuebjzrq", yuebjzrq);
		
        
        // 删除已经存在的数据
        jdbcTemplate.update("delete from yuezlb where yuetjkjb_id in (select id from yuetjkjb \n"
                			+ "	where substr(riq,0,7) = '" + riq + "' \n"
                			+ "and diancxxb_id = " + dianc + ")");
        //查询相关质量根据日期
        List<Map<String, Object>> list = ruczlDao.getHuayz(map);
        if (list.size() == 0) {//没有化验数据
            //得到月统计口径表
            List<Map<String, Object>> yuetjkjbList =
                    jdbcTemplate.queryForList("select id YUETJKJB_ID,riq,diancxxb_id,xuh,gongysb_id,jihkjb_id,pinzb_id,yunsfsb_id from yuetjkjb where riq='" + riq + "'");
            for (Map<String, Object> yuetjkj : yuetjkjbList) {
                yuetjkj.putAll(map);
                Map<String, Object> leij = null;
                Map<String, Object> beny = new HashMap();
                //插入空的本月
                beny.put("ID", Sequence.nextId());
                beny.putAll(yuetjkj);
                ruczlDao.insertYuezlbEmpty(beny);
                if (intYuef == 1) {//如果是一月份累计=本月
                    leij = ruczlDao.getLeij_yuany(yuetjkj);
                } else {//如果是其他月份累计=本月+上月累计
                    leij = ruczlDao.getLeij_qit(yuetjkj);

                }
                //2.4插入累计
                if (leij != null) {
                    leij.put("ID", Sequence.nextId());
                    Huayz.round_new(leij);
                    leij.putAll(yuetjkj);
                    ruczlDao.insertYuezlbLeij(leij);
                }
            }
        } else {
            for (int i = 0; i < list.size(); i++) {
                //查询月计划口径表id
                Map<String, Object> m = list.get(i);

                m.put("dianc", dianc);
                m.put("riq", riq);
                String yuetjkjb_id = ruczlDao.getYuetjkjb_id(m);
                //向月质量表中插数
                //1向月质量表中插入本月数据
                if (yuetjkjb_id != null) {
                    m.put("YUETJKJB_ID", yuetjkjb_id);
                    m.put("ID", Sequence.nextId());
                    Huayz.round_new(m);
                    ruczlDao.insertYuezlb(m);
                }

                //2向月质量表中插入累计数据

                //2.1查询上个月累计

                m.put("lastMonth", lastMonth);
                try {
                    List<Map<String, Object>> list_yuetjkj = jdbcTemplate.queryForList("select * from yuetjkjb where id = " + yuetjkjb_id);
                    if (list_yuetjkj.size() != 0 && list_yuetjkj != null) {
                        Map<String, Object> maps = list_yuetjkj.get(0);
                        m.put("gongysb_id", maps.get("gongysb_id").toString());
                        m.put("jihkjb_id", maps.get("jihkjb_id").toString());
                        m.put("pinzb_id", maps.get("pinzb_id").toString());
                        m.put("yunsfsb_id", maps.get("yunsfsb_id").toString());
                    } //根据上月来煤情况生成本月的月统计口径表

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Map<String, Object> leij = null;
                if (intYuef == 1) {//如果是一月份累计=本月
                    leij = ruczlDao.getLeij_yuany(m);
                } else {//如果是其他月份累计=本月+上月累计
                    leij = ruczlDao.getLeij_qit(m);

                }

                //2.3计算累计
                if (leij != null) {
                    m.put("MT", Double.parseDouble(leij.get("MT").toString()));
                    m.put("MAD", Double.parseDouble(leij.get("MAD").toString()));
                    m.put("AAR", Double.parseDouble(leij.get("AAR").toString()));
                    m.put("AAD", Double.parseDouble(leij.get("AAD").toString()));
                    m.put("AD", Double.parseDouble(leij.get("AD").toString()));
                    m.put("VDAF", Double.parseDouble(leij.get("VDAF").toString()));
                    m.put("STD", Double.parseDouble(leij.get("STD").toString()));
                    m.put("HAD", Double.parseDouble(leij.get("HAD").toString()));
                    m.put("FCAD", Double.parseDouble(leij.get("FCAD").toString()));
                    m.put("QNET_AR", Double.parseDouble(leij.get("QNET_AR").toString()));
                }
                //2.4插入累计
                m.put("ID", Sequence.nextId());
                Huayz.round_new(m);
                ruczlDao.insertYuezlbLeij(m);
            }
        }

        jsonArray.add(1);
        return jsonArray;
    }

    @Override
    @Transactional
    public JSONArray saveData(Map<String, Object> map) {

        JSONArray array = new JSONArray();
        JSONArray jsonArray = JSONArray.fromObject(map.get("ruczlList"));
        @SuppressWarnings({"unchecked", "deprecation"})
        List<Map<String, Object>> list = JSONArray.toList(jsonArray, Map.class);
        if (list != null) {
            try {
                for (int i = 0; i < list.size(); i++) {
                    Map<String, Object> maps = list.get(i);
                    TreeUtil.checkMapNull(maps, new String[]{"MAD", "QBAD", "HAD", "VAD", "FCAD", "STD", "QBRAD", "HDAF", "QGRAD_DAF", "SDAF", "VAR", "QNET_AR_KF", "AAR_KF", "AD_KF", "VDAF_KF", "MT_KF", "STAD_KF", "AAD_KF", "MAD_KF", "QBAD_KF", "HAD_KF", "VAD_KF", "FCAD_KF", "STD_KF", "QBRAD_KF", "HDAF_KF", "QGRAD_DAF_KF", "SDAF_KF", "VAR_KF", "ZHUANGT", "ID", "FENX", "YUETJKJB_ID", "QNET_AR", "AAR", "AD", "VDAF", "MT", "STAD", "AAD", "ZHIJBFML", "ZHIJBFJE", "SUOPJE", "LSUOPSL", "LSUOPJE", "ZHIJBFJE_M", "ZHIJBFJE_A", "ZHIJBFJE_V", "ZHIJBFJE_Q", "ZHIJBFJE_S", "ZHIJBFJE_T"});
                    if (Long.parseLong(maps.get("ID").toString()) > 0 && maps.get("FENX").equals("本月")) {
                        jdbcTemplate.update("update yuezlb set QNET_AR="
                                + maps.get("QNET_AR") + ",MT=" + maps.get("MT")
                                + ",MAD=" + maps.get("MAD") + ",AAR="
                                + maps.get("AAR") + ",AAD=" + maps.get("AAD")
                                + ",AD=" + maps.get("AD") + ",VDAF="
                                + maps.get("VDAF") + ",STD=" + maps.get("STD")
                                + ",HAD=" + maps.get("HAD") + ",FCAD="
                                + maps.get("FCAD") + ",QNET_AR_KF="
                                + maps.get("QNET_AR_KF") + ",MT_KF="
                                + maps.get("MT_KF") + ",MAD_KF="
                                + maps.get("MAD_KF") + ",AAR_KF="
                                + maps.get("AAR_KF") + ",AAD_KF="
                                + maps.get("AAD_KF") + ",AD_KF="
                                + maps.get("AD_KF") + ",VDAF_KF="
                                + maps.get("VDAF_KF") + ",STD_KF="
                                + maps.get("STD_KF") + ",HAD_KF="
                                + maps.get("HAD_KF") + ",FCAD_KF="
                                + maps.get("FCAD_KF") + " where id = "
                                + maps.get("ID"));

                        //重新计算累计
                    }
                }
                array.add(1);
            } catch (Exception e) {
                TransactionAspectSupport.currentTransactionStatus()
                        .setRollbackOnly();
                array.add(0);
                e.printStackTrace();
            }
        }
        return array;

    }

    @Override
    @Transactional
    public JSONArray delData(Map<String, Object> map) {
        // TODO Auto-generated method stub
        JSONArray jsonArray = new JSONArray();
        try {
            jdbcTemplate
                    .update("delete from yuezlb where yuetjkjb_id in (select id from yuetjkjb where substr(riq,0,7) = '"
                            + map.get("riq")
                            + "' and diancxxb_id = "
                            + map.get("dianc") + ")");
            jsonArray.add(1);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus()
                    .setRollbackOnly();
            jsonArray.add(0);
            e.printStackTrace();
        }
        return jsonArray;
    }

    @Override
    public JSONArray check(Map<String, Object> map) {
        // TODO Auto-generated method stub
        JSONArray jsonArray = new JSONArray();
        try {
            int count = jdbcTemplate
                    .queryForInt("select count(1) from yuezlb where yuetjkjb_id in (select id from yuetjkjb where riq='"
                            + map.get("riq")
                            + "' and diancxxb_id = "
                            + map.get("dianc") + ")");
            if (count == 0) {
                jsonArray.add(1);
            } else {
                count = jdbcTemplate
                        .queryForInt("select count(1) from yuezlb where yuetjkjb_id in (select id from yuetjkjb where riq='"
                                + map.get("riq")
                                + "' and diancxxb_id = "
                                + map.get("dianc") + ") and zhuangt = 0");
                jsonArray.add(count);
            }
        } catch (Exception e) {
            jsonArray.add(1);
        }
        return jsonArray;
    }
}
