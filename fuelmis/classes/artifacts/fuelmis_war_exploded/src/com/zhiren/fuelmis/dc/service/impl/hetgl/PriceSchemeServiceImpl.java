package com.zhiren.fuelmis.dc.service.impl.hetgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.hetgl.PriceSchemeDao;
import com.zhiren.fuelmis.dc.entity.hetgl.PriceScheme;
import com.zhiren.fuelmis.dc.service.hetgl.PriceSchemeService;
import com.zhiren.fuelmis.dc.utils.Sequence;

@Service("priceSchemeService")
public class PriceSchemeServiceImpl implements PriceSchemeService {

    @Autowired
    private PriceSchemeDao priceSchemeDao;

    @Override
    public void updatePriceScheme(JSONArray priceScheme, Renyxx user) {
        for (int i=0; i<priceScheme.size(); i++) {
            Map<String,Object> map=priceScheme.getJSONObject(i);
            map.put("USERID",user.getId());
            map.put("USERNAME",user.getQuanc());
            if(null==map.get("SHANGXMAX")){
            	map.put("SHANGXMAX",0);
            }
            if(null == map.get("XIAXMIN")){
            	map.put("XIAXMIN",0);
            }
            if(map.get("ID")==null){
                map.put("ID",Sequence.nextId());
                priceSchemeDao.insetScheme(map);
            }else{
                priceSchemeDao.updateScheme(map);
            }
        }
    }


    @Override
    public JSONObject delPriceScheme(Map<String, Object> map) {
        int result = 1;
        try{
            priceSchemeDao.delPriceScheme(map);
        }catch(Exception e){
            result = 0;
            e.printStackTrace();
        }
        JSONObject json = new JSONObject();
        json.put("result", result);
        return json;
    }

    @Override
    public PriceScheme getPriceSchemeByCaigddId(Map<String, Object> map) {
        List<PriceScheme> list = priceSchemeDao.getPriceSchemeByCaigddId(map);
        PriceScheme priceScheme= null;
        if(list.size()>0){
            priceScheme = list.get(0);
        }
        return priceScheme;
    }
}
