package com.zhiren.fuelmis.dc.service.impl.yuebgl.yuebwh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhiren.fuelmis.dc.service.impl.yuebgl.YuebService;
import com.zhiren.fuelmis.dc.utils.formular.Result;
import com.zhiren.fuelmis.dc.utils.math.Math;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhiren.fuelmis.dc.dao.yuebgl.yuebwh.HaochjDao;
import com.zhiren.fuelmis.dc.service.yuebgl.yuebwh.IHaochjService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 陈宝露
 */
@Service
public class HaochjServiceImpl extends YuebService implements IHaochjService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private HaochjDao haochjDao;

    @Override
    public JSONObject getAll(Map<String, Object> map) {
        // TODO Auto-generated method stub
        Map<String, Object> jsonMap = new HashMap<String, Object>();

        List<Map<String, Object>> list = haochjDao.getAll(map);
        jsonMap.put("data", list);

        JSONObject result = JSONObject.fromObject(jsonMap);
        return result;
    }

    @Override
    public JSONArray createData(Map<String, Object> map) {
        // TODO Auto-generated method stub addYueshchjb
        return null;
    }

    @Override
    @Transactional
    public void saveData(List<Map> list) throws Exception {
        Map<String, Object> beny = list.get(0);
        Map<String, Object> byleij = list.get(1);
        //将key转换成大写
        beny = Result.key2UpperCase(beny);
        byleij = Result.key2UpperCase(beny);
        //重新计算累计值
        Map<String, Object> leij = haochjDao.getLastMonthLeij(beny.get("RIQ").toString());
        leij.put("ID", byleij.get("ID"));
        leij.put("FENX", "累计");
        leij.put("RIQ", beny.get("RIQ"));
//        leij.put("QICKC",byleij.get("QICKC"));
        for (Map.Entry<String, Object> entry : beny.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (key.equals("FADY") || key.equals("GONGRY") || key.equals("SHOUML")
                    || key.equals("PANYK") || key.equals("SHUIFCTZ")
                    || key.equals("QITH") || key.equals("SUNH") || key.equals("DIAOCL") || key.equals("JITCS") || key.equals("RUNXCS")) {
                leij.put(key, Math.add(leij.get(key), value));
            }
        }
        //计算累计库存
        String gongs = " QICKC + SHOUML - FADY - GONGRY - QITH + SUNH - DIAOCL + PANYK + SHUIFCTZ ";
        double kuc = Math.getGongsjg(leij, gongs);
        leij.put("KUC",kuc);
        this.saveData(beny, "YUESHCHJB", "riq,fenx");
        this.saveData(leij, "YUESHCHJB", "riq,fenx");
    }

    @Override
    public JSONArray delData(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JSONArray check(Map<String, Object> map) {
        // TODO Auto-generated method stub
        JSONArray jsonArray = new JSONArray();
        try {
            int count = jdbcTemplate.queryForInt("select count(1) from yueshchjb where riq='"
                    + map.get("riq") + "' and diancxxb_id = " + map.get("dianc"));
            if (count == 0) {
                jsonArray.add(1);
            } else {
                count = jdbcTemplate.queryForInt("select count(1) from yueshchjb where riq='"
                        + map.get("riq") + "' and diancxxb_id = " + map.get("dianc") + " and zhuangt = 0");
                jsonArray.add(count);
            }
        } catch (Exception e) {
            jsonArray.add(1);
        }
        return jsonArray;
    }

}
