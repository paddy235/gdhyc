package com.zhiren.fuelmis.dc.service.impl.kucgl.rukgl;

import com.zhiren.fuelmis.dc.dao.kucgl.rukgl.ZidrkDao;
import com.zhiren.fuelmis.dc.service.kucgl.rukgl.IZidrkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzhiyu on 2016/12/22.
 */


public class ZidrkServiceImpl implements IZidrkService {
    @Autowired
    private ZidrkDao zidrkDao;
    @Override@Transactional
    public void updateDatas(Map<String, Object> jiesdMap, List<Map<String, Object>> zengkmxList, List<Map<String, Object>> gxList) {
        zidrkDao.updateData(jiesdMap,"rl_js_rijsdb");
        for (Map<String,Object> zengkmx:zengkmxList) {
            zidrkDao.updateData(zengkmx,"RL_JS_RIJSDB_ZENGKMXB");
        }
        for (Map<String,Object> gx:gxList) {
            zidrkDao.updateData(gx,"GX_JIESDB_CHEPB");
        }
    }
}
