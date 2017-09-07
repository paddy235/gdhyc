package com.zhiren.fuelmis.dc.service.impl.yuebgl;


import com.zhiren.fuelmis.dc.dao.yuebgl.YuebDao;
import com.zhiren.fuelmis.dc.service.impl.common.SaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author rain
 */
@Service
public class YuebService extends SaveService {
	@Autowired
    JdbcTemplate jdbcTemplate;
	@Autowired
	private YuebDao yuebDao;

    /**
     *
     * @param tablename
     * @param map map.riq, map.diancid
     */
	public void delYuebData(String tablename,Map<String,Object> map){
        yuebDao.delYuebData(tablename,map);
    }

}
