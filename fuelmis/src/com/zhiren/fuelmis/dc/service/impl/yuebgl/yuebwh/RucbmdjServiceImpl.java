package com.zhiren.fuelmis.dc.service.impl.yuebgl.yuebwh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import com.zhiren.fuelmis.dc.utils.math.Math;
import com.zhiren.fuelmis.dc.dao.yuebgl.yuebwh.RucbmdjDao;
import com.zhiren.fuelmis.dc.service.impl.yuebgl.YuebService;
import com.zhiren.fuelmis.dc.service.yuebgl.yuebwh.IRucbmdjService;
import com.zhiren.fuelmis.dc.utils.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import org.springframework.transaction.annotation.Transactional;

/**
 * @author 陈宝露
 */
@Service
public class RucbmdjServiceImpl extends YuebService implements IRucbmdjService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RucbmdjDao rucbmdjDao;

    @Override
    @Transactional
    public void createData(Map<String, Object> cd) {
        List<Map<String, Object>> list = rucbmdjDao.getSecondData(cd);
        for (Map<String, Object> map : list) {
            map.put("CAOZLX", 0);
            map.put("ID", Sequence.nextId());
            this.saveData(map, "YUEJSBMDJ", "yuetjkjb_id,fenx");
            this.saveData(map, "YUEJSBMDJYF", "yuetjkjb_id,fenx");
        }
    }

    @Override
    @Transactional
    public void saveData(List<ConcurrentMap> list) throws Exception {
        for (int i = 2; i < list.size(); i += 2) {
            Map<String, Object> beny = list.get(i);
            Map<String, Object> byleij = list.get(i+1);
            Map<String, Object> leij = rucbmdjDao.getLastMonthLeij(beny.get("YUETJKJB_ID").toString());
            leij.put("ID", byleij.get("ID"));
            leij.put("YUETJKJB_ID", byleij.get("YUETJKJB_ID"));
            leij.put("FENX", "累计");
            Object leijjsl = leij.get("JIESL");
            Object beiyjsl = beny.get("JIESL");

            for (Map.Entry<String, Object> entry : beny.entrySet()) {
                String key = entry.getKey();//本月
                Object value = entry.getValue();
                //计算本月
                double daoczhj = Math.getGongsjg(beny, "MEIJ+YUNJ +DAOZZF+ZAF+QIT+KUANGQYF");
                beny.put("DAOCZHJ", daoczhj);
                double biaomdj = 0;
                double buhsbmdj = 0;
                if (!beny.get("QNET_AR").toString().equals("0")) {
                    biaomdj = Math.getGongsjg(beny, "DAOCZHJ*29.271/QNET_AR");
                    buhsbmdj = Math.getGongsjg(beny, "(DAOCZHJ-MEIJS-YUNJS-ZAFS)*29.271/QNET_AR");
                }
                beny.put("BIAOMDJ", biaomdj);
                beny.put("BUHSBMDJ", buhsbmdj);
                //计算累计
                if (key.equals("JIESL")) {
                    leij.put(key, Math.add(leij.get(key), value));
                }
                if (key.equals("MEIJ") ||
                        key.equals("MEIJS") ||
                        key.equals("KUANGQYF") ||
                        key.equals("YUNJ") ||
                        key.equals("YUNJS") ||
                        key.equals("DAOZZF") ||
                        key.equals("ZAF") ||
                        key.equals("ZAFS") ||
                        key.equals("QIT") ||
                        key.equals("QNET_AR") ||
                        key.equals("BIAOMDJ") ||
                        key.equals("BUHSBMDJ")) {
                    Map<String, Object> vMap = new HashMap<String, Object>();
                    vMap.put("LEIJ", leij.get(key));
                    vMap.put("LEIJJSL", leijjsl);
                    vMap.put("BENY", beny.get(key));
                    vMap.put("BENYJSL", beiyjsl);
                    vMap.put("JIESL", leij.get("JIESL"));
                    if(vMap.get("JIESL").toString().equals("0")){
                        leij.put(key, 0);
                    }else {
                        leij.put(key, Math.getGongsjg(vMap, "(LEIJ*LEIJJSL+BENY*BENYJSL)/JIESL"));
                    }

                }
            }
            this.saveData(beny, "YUEJSBMDJ", "yuetjkjb_id,fenx");
            this.saveData(beny, "YUEJSBMDJYF", "yuetjkjb_id,fenx");
            this.saveData(leij, "YUEJSBMDJ", "yuetjkjb_id,fenx");
            this.saveData(leij, "YUEJSBMDJYF", "yuetjkjb_id,fenx");
        }
    }


    @Override
    @Transactional
    public void delData(Map<String, Object> map) {
        this.delYuebData("YUEJSBMDJ", map);
        this.delYuebData("YUEJSBMDJYF", map);
    }

    @Override
    public JSONArray check(Map<String, Object> map) {
        // TODO Auto-generated method stub
        JSONArray jsonArray = new JSONArray();
        String sql = "select s.zhuangt zhuangt	" +
                "	from yuejsbmdj s, yuetjkjb k	" +
                "	where s.yuetjkjb_id = k.id	" +
                "	and k.diancxxb_id = " + map.get("dianc") +
                " and k.riq ='" + map.get("riq") + "'";
        return jsonArray;
    }

}
