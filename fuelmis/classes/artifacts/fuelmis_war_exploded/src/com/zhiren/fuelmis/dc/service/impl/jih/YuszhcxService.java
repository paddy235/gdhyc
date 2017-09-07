package com.zhiren.fuelmis.dc.service.impl.jih;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.cnfy.YuszhcxDao;
import com.zhiren.fuelmis.dc.service.cnfy.IYuszhcxService;
@Service
public class YuszhcxService implements IYuszhcxService {
	@Autowired
	private YuszhcxDao yuszhcxDao;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public JSONObject getYuszhcxdata(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[3][17];
		List yuslist = yuszhcxDao.getyusdata(map);
		if(yuslist.size()>0){
			objs[0][2] = "下达预算";
			String ndyused = ((HashMap<String, Object>)yuslist.get(0)).get("NDYUSED").toString();
			BigDecimal yushej = new BigDecimal(ndyused);
			objs[0][3] = yushej.doubleValue();
			double hej = 0;
			for(int i = 0;i<yuslist.size();i++){
				HashMap<String, Object> hashMap = (HashMap<String, Object>) yuslist.get(i);
				String xuh = (String) hashMap.get("XUH");
				int index = Integer.parseInt(xuh)+3;
				String jine = hashMap.get("YUSED").toString();
				BigDecimal big = new BigDecimal(jine);
				double value = big.doubleValue(); 
				objs[0][index] = value;
				hej = hej+value;
			}
			objs[0][16] = hej;
		}
		List shijlist = yuszhcxDao.getshijwcdata(map);
		if(shijlist.size()>0){
			String diancmc = ((HashMap<String, Object>)shijlist.get(0)).get("DIANCMC").toString();
			String feiymc = ((HashMap<String, Object>)shijlist.get(0)).get("FEIYMC").toString();
			objs[0][0] = diancmc;
			objs[0][1] = feiymc;
			objs[1][0] = "";
			objs[1][1] = "";
			objs[1][2] = "实际完成";
			String hej1 = ((HashMap<String, Object>)shijlist.get(0)).get("HEJ").toString();
			BigDecimal bighej = new BigDecimal(hej1);
			objs[1][3] = bighej.doubleValue();
			objs[1][16] = bighej.doubleValue();
			for(int i = 0;i<shijlist.size();i++){
				HashMap<String, Object> hashMap = (HashMap<String, Object>) shijlist.get(i);
				String xuh = (String) hashMap.get("XUH");
				int index = Integer.parseInt(xuh)+3;
				String jine = hashMap.get("JINE").toString();
				BigDecimal big = new BigDecimal(jine);
				objs[1][index] = big.doubleValue(); 
			}
		}
		if(yuslist.size()>0&&shijlist.size()>0){
			objs[2][0] = "";
			objs[2][1] = "";
			objs[2][2] = "差值";
			for(int i =3;i<=16;i++){
				if(null==objs[0][i]&&null==objs[1][i]){
					continue;
				}else{
					if(null==objs[0][i]){
						objs[0][i] = 0.0;
					}
					if(null == objs[1][i]){
						objs[1][i] = 0.0;
					}
				}
				objs[2][i] = (double)objs[0][i]-(double)objs[1][i];
			}
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

}
