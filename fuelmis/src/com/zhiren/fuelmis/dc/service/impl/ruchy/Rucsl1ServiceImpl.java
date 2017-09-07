package com.zhiren.fuelmis.dc.service.impl.ruchy;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.ruchy.SanfsllrDao;
import com.zhiren.fuelmis.dc.service.ruchy.IRucslService;

/**
 * @author 刘志宇
 */
@Service
public class Rucsl1ServiceImpl implements IRucslService {
    @Autowired
    private SanfsllrDao sanfsllrDao;

    @Override
    public List<Map<String, Object>> getGongysList(String riq) {
        return sanfsllrDao.getGongysList(riq);
    }

    @Override
    public List<Map<String, Object>> getChep(Map<String, Object> conditionMap) {
        return sanfsllrDao.getChep(conditionMap);
    }

    @Override
    public void updateChep(List<Map<String, Object>> a) {
        for (int i = 0; i < a.size(); i++) {
            Map<String, Object> map = a.get(i);
            sanfsllrDao.updateChep(map);
        }

    }


}
