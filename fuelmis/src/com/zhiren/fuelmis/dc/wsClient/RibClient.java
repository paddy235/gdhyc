package com.zhiren.fuelmis.dc.wsClient;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


/**
 * @author 陈宝露
 */
@Component
public class RibClient {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void uploadShouhcrbb(List<Map<String, Object>> list) {
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                if (!map.isEmpty()) {
                    String sql = "update shouhcrbb set ZHAUNGT=1 where id=" + map.get("ID");
                    jdbcTemplate.update(sql);
                }
            }
        }
    }


    public void uploadShouhcfkb(List<Map<String, Object>> list) {
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                Object[] arr = new Object[34];
                if (!map.isEmpty()) {
                    String sql = "update shouhcfkb set ZHAUNGT=1 where id=" + map.get("ID");
                    jdbcTemplate.update(sql);
                }
            }
        }
    }
}

