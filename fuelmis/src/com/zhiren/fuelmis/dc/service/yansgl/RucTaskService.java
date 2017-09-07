package com.zhiren.fuelmis.dc.service.yansgl;


import java.util.Map;

public interface RucTaskService {
    void saveData(Map<String, Object> shulMap, String tableName, String id);

    void saveShulData(Map<String, Object> shulMap);

    void saveBianm(Map<String, Object> bianm);

    void saveHuayd(Map<String, Object> huayd);
}
