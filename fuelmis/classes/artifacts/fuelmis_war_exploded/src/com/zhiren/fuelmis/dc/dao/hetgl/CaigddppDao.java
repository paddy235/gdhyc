package com.zhiren.fuelmis.dc.dao.hetgl;

import java.util.List;
import java.util.Map;

/**
 * Created by liuzhiyu on 2016/12/30.
 */
public interface CaigddppDao {
    List<Map<String,Object>> getCaigddppList(Map map);

    List<Map<String,Object>> getCaigddxxcombox();

    void addcaigddpp(Map map);

    void updatecaigddpp(Map map);

    void deleteCaigddppById(String id);
}
