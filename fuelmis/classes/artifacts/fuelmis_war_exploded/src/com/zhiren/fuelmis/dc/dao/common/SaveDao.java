package com.zhiren.fuelmis.dc.dao.common;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/** 
 * @author rain
 */
@Repository
public interface SaveDao {
    void saveData(@Param("data") Map<String, Object> data, @Param("tableName") String tableName,
                  @Param("colNames") List<String> colNames, @Param("id") String id);
}
