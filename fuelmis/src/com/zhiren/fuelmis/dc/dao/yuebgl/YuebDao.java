package com.zhiren.fuelmis.dc.dao.yuebgl;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/** 
 * @author rain
 */
@Repository
public interface YuebDao {

    void delYuebData(@Param("tablename") String tablename, @Param("map") Map<String, Object> map);
}
