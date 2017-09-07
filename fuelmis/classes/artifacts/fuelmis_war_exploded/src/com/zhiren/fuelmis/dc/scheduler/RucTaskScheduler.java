package com.zhiren.fuelmis.dc.scheduler;

import java.util.*;

import com.zhiren.fuelmis.dc.dao.yansgl.RucTaskDao;
import com.zhiren.fuelmis.dc.service.yansgl.RucTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author rain
 */
@Component("rucTaskScheduler")
public class RucTaskScheduler {
    @Autowired
    private RucTaskDao rucTaskDao;
    @Autowired
    private RucTaskService rucTaskService;

    public void execute() {
        // 读取DATASTATUS=0的jk_truckenter接口表数据
        List<Map<String, Object>> shulList = rucTaskDao.getShulList();
        for (Map<String, Object> shulMap : shulList) {
            rucTaskService.saveShulData(shulMap);
        }
        List<Map<String, Object>> bianmList = rucTaskDao.getBianmList();
        for (Map<String, Object> bianm : bianmList) {
            rucTaskService.saveBianm(bianm);
        }
        List<Map<String, Object>> huaydList = rucTaskDao.getHuaydList();
        for (Map<String, Object> huayd : huaydList) {
            rucTaskService.saveHuayd(huayd);
        }

    }
}
