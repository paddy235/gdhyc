package com.zhiren.fuelmis.dc.service.impl.yansgl;

import com.zhiren.fuelmis.dc.dao.yansgl.RucTaskDao;

import com.zhiren.fuelmis.dc.service.yansgl.RucTaskService;

import com.zhiren.fuelmis.dc.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RucTaskServiceImpl implements RucTaskService {

    @Resource
    private RucTaskDao rucTaskDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveData(Map<String, Object> data, String tableName, String colNames) {
        String[] conditions = colNames.split(",");
        String sql = "SELECT COLUMN_NAME FROM USER_TAB_COLUMNS WHERE TABLE_NAME = '" + tableName.toUpperCase() + "'";
        List<String> colnames = jdbcTemplate.queryForList(sql, String.class);
        sql = "select max(id) from " + tableName + " where 1=1 ";
        for (String cName : conditions) {
            sql += " and " + cName + " = '" + data.get(cName.toUpperCase()) + "'";
        }
        String id = jdbcTemplate.queryForObject(sql, String.class);
        if (id != null) data.put("ID", id);
        String caozlx = data.get("CAOZLX").toString();
        if (id == null && caozlx.equals("2")) return;
        if (!caozlx.equals("2")) {
            if (id == null) {
                data.put("CAOZLX", 0);
                data.put("ID", this.getNewId(515));
            } else {
                data.put("CAOZLX", 1);
            }
        }
        rucTaskDao.saveData(data, tableName, colnames, "ID");
    }

    private String getNewId(int i) {
        String id;
        String sql = "select getnewid(" + i + ") from dual";
        id = jdbcTemplate.queryForObject(sql, String.class);
        return id;
    }

    @Override
    @Transactional
    public void saveShulData(Map<String, Object> shulMap) {
        String sql;
        String gongysb_id = shulMap.get("GONGYSB_ID").toString();
        String meikxxb_id = shulMap.get("MEIKXXB_ID").toString();
        String pinzb_id = shulMap.get("PINZB_ID").toString();
        try {
            if (gongysb_id.equals("0"))
                throw new Exception("供应商不存在!");
            if (meikxxb_id.equals("0"))
                throw new Exception("煤矿不存在!");
            if (pinzb_id.equals("0"))
                throw new Exception("品种不存在!");
            this.saveData(shulMap, "chepbtmp", "PIAOJH");
            this.saveData(shulMap, "rl_ys_chepb", "PIAOJH");
            shulMap.put("CHEPB_ID", shulMap.get("ID"));
            this.saveData(shulMap, "rl_ys_chepb_qc", "CHEPB_ID");
            sql = "update jk_truckenter set datastatus=1 ,READTIME=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'), \n" +
                    "READERRECONTENT='导入成功!' where ywid= '" + shulMap.get("PIAOJH") + "'";
            jdbcTemplate.update(sql);
        } catch (Exception e) {
            e.printStackTrace();
            sql = "UPDATE JK_TRUCKENTER set DATASTATUS=0,READTIME=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'), \n" +
                    "READERRECONTENT='" + StringUtils.replaceBlank4Sql(e.getMessage()) + "' where ywid='" + shulMap.get("PIAOJH") + "'";
            jdbcTemplate.update(sql);
        }
    }
    @Override
    @Transactional
    public void saveBianm(Map<String, Object> bianm) {
        String sql;
        try {
            Map<String, Object> bmMap = new HashMap<String, Object>();
            bmMap.put("ZHUANMSJ", bianm.get("ZHUANMSJ"));
            bmMap.put("DIANCXXB_ID", 515);
            bmMap.put("CAOZLX", bianm.get("CAOZLX"));
            bmMap.put("YUANBM", bianm.get("CAIYBM"));
            bmMap.put("MUBBM", bianm.get("ZHIYBM"));
            bmMap.put("ZHUANHLB_ID", 1);
            this.saveData(bmMap, "GX_CHEP_CAIZHBMB", "YUANBM,ZHUANHLB_ID,DIANCXXB_ID");
            bmMap.put("YUANBM", bianm.get("ZHIYBM"));
            bmMap.put("MUBBM", bianm.get("HUAYBM"));
            bmMap.put("ZHUANHLB_ID", 2);
            this.saveData(bmMap, "GX_CHEP_CAIZHBMB", "YUANBM,ZHUANHLB_ID,DIANCXXB_ID");
            sql = "update jk_assaycode set datastatus=1 , \n" +
                    "READERRECONTENT='导入成功!' where SAMCODE= '" + bianm.get("CAIYBM") + "'";
            jdbcTemplate.update(sql);
        } catch (Exception e) {
            e.printStackTrace();
            sql = "update jk_assaycode set datastatus=0 , \n" +
                    "READERRECONTENT='" + StringUtils.replaceBlank4Sql(e.getMessage()) + "' where SAMCODE= '" + bianm.get("CAIYBM") + "'";
            jdbcTemplate.update(sql);
        }
    }

    @Override
    @Transactional
    public void saveHuayd(Map<String, Object> huayd) {
        String sql;
        try {
            this.saveData(huayd, "rl_hy_huaydb", "HUAYBM");
            sql = "update Jk_RC_HUAYBGB set datastatus=1 ,READTIME=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'), \n" +
                    "READERRECONTENT='导入成功!' where assaycode= '" + huayd.get("HUAYBM") + "'";
            jdbcTemplate.update(sql);
        } catch (Exception e) {
            e.printStackTrace();
            sql = "UPDATE Jk_RC_HUAYBGB set DATASTATUS=0,READTIME=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'), \n" +
                    "READERRECONTENT='" + StringUtils.replaceBlank4Sql(e.getMessage()) + "' where assaycode='" + huayd.get("HUAYBM") + "'";
            jdbcTemplate.update(sql);
        }
    }
}
