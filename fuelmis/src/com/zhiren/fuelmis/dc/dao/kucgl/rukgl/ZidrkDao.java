package com.zhiren.fuelmis.dc.dao.kucgl.rukgl;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * Created by liuzhiyu on 2016/12/22.
 */
public interface ZidrkDao {
    void updateData(@Param("data") Map<String, Object> data,@Param("table") String table);
}
