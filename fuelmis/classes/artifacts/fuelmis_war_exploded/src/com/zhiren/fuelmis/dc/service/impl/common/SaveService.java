package com.zhiren.fuelmis.dc.service.impl.common;


import com.zhiren.fuelmis.dc.dao.common.SaveDao;
import com.zhiren.fuelmis.dc.utils.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzhiyu on 2017/3/23.
 */
@Service
public class SaveService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Resource
    private SaveDao saveDao;

    public void  saveData(Map<String, Object> data, String tableName, String colNames) {
        String[] conditions = colNames.split(",");
        String sql = "SELECT COLUMN_NAME FROM USER_TAB_COLUMNS WHERE TABLE_NAME = '" + tableName.toUpperCase() + "'";
        List<String> colnames = jdbcTemplate.queryForList(sql, String.class);
        sql = "select max(id) from " + tableName + " where 1=1 ";
        for (String cName : conditions) {
            sql += " and " + cName + " = '" + data.get(cName.toUpperCase()) + "'";
        }
        String id = jdbcTemplate.queryForObject(sql, String.class);
        if (id != null)data.put("ID", id);
        if(data.get("CAOZLX")==null){
            data.put("CAOZLX",0);
        }
        String caozlx = data.get("CAOZLX").toString();
        if (id == null && caozlx.equals("2")) return;
        if (!caozlx.equals("2")) {
            if (id == null) {
                data.put("CAOZLX", 0);
                if(data.get("ID")==null){
                    data.put("ID", Sequence.nextId());
                }
            } else {
                data.put("CAOZLX", 1);
            }
        }
        saveDao.saveData(data, tableName, colnames, "ID");
    }
}
