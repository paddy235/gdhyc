package com.zhiren.fuelmis.dc.service.impl.kucgl;
import com.zhiren.fuelmis.dc.service.impl.common.SaveService;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.kucgl.ShujlrDao;
import com.zhiren.fuelmis.dc.entity.kucgl.PandGdjt;
import com.zhiren.fuelmis.dc.service.kucgl.IShujlrService;
import com.zhiren.fuelmis.dc.utils.PropertiesUtil;
import com.zhiren.fuelmis.dc.wsClient.PandClient;

/** 
 * @author 陈宝露
 */
@Service
public class ShujlrServiceImpl extends SaveService implements IShujlrService {
	
	@Autowired
	private ShujlrDao shujlrDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public JSONObject getAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<PandGdjt> list = shujlrDao.getAll(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JSONArray jsonArray = new JSONArray();
		for(int i = 0;i<list.size();i++){
			PandGdjt pand = list.get(i);
			String fujmc;
			try {
				if(pand.getFujmc()!=null){
					fujmc = URLEncoder.encode(pand.getFujmc(), "utf-8");
					pand.setFujmc("<a href='common/downloadFile?fileId="+fujmc+"'>"+pand.getFujmc()+"</a>");
				}
				jsonArray.add(pand);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		jsonMap.put("data", jsonArray);
		jsonMap.put("count", list.size());
		JSONObject result = JSONObject.fromObject(jsonMap);
		
		return result;
	}



	@Override
	public JSONObject insertPandxx(Map<String, Object> map) {
		// TODO Auto-generated method stub
		PandGdjt pandGdjt = new PandGdjt();
		pandGdjt.setId(System.currentTimeMillis());
		pandGdjt.setRiq(map.get("riq").toString());
		pandGdjt.setDiancxxb_id(map.get("dianc").toString());
		pandGdjt.setZhangmkc(0);
		pandGdjt.setShipkc(0);
		pandGdjt.setChangsl(0);
		pandGdjt.setShuifctzl(0);
		pandGdjt.setYingkd(0);
		pandGdjt.setFujzt(0);
		pandGdjt.setFujmc("");
		pandGdjt.setZhuangt(1);
		int result = 0;
		try{
			result = shujlrDao.insertPandxx(pandGdjt);
		}catch(Exception e){
			e.printStackTrace();
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("data", result);
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		return jsonObject;
	}

	@Override
	public JSONObject updatePandxx(Map<String, Object> map) {
		// TODO Auto-generated method stub
		String[] params = map.get("id").toString().split("_");
		String value = map.get("value").toString();
		map.clear();
		map.put("id", params[1]);
		map.put(params[0], value);
		int result = 0;
		try{
			result = shujlrDao.updatePandxx(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("data", result);
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		return jsonObject;
	}

	@Override
	public JSONObject updateFuj(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			result = shujlrDao.updateFuj(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("data", result);
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		return jsonObject;
	}

	@Override
	public JSONArray getCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(shujlrDao.getCount(map));
		return jsonArray;
	}

	@Override
	public String submitData(Map<String, Object> map) {
		int  flag = jdbcTemplate.update("update PAND_GDJT set ZHUANGT=1  where ID="+map.get("ID"));
		JSONArray jsonArray = new JSONArray();
		try{
			Map<String,Object> maps = jdbcTemplate.queryForMap("select ID,RIQ,DIANCXXB_ID,ZHANGMKC,SHIPKC,CHANGSL,SHUIFCTZL,YINGKD,FUJZT,FUJMC,ZHUANGT from PAND_GDJT where ID = "+map.get("ID"));
			//获取文件
			//String fileName = jdbcTemplate.queryForObject("select MINGC from WENJXXB where ID = "+maps.get("FUJMC"), String.class);
			String fileName = maps.get("FUJMC").toString();
			String filePath = PropertiesUtil.getValue("upload_file_folder")+fileName;
			File file = new File(filePath);
			DataHandler handler = new DataHandler(new FileDataSource(file));
			PandClient.getinstance().uploadPand(maps,handler,fileName,map.get("dianc").toString());
			jsonArray.add(1);
		}catch(Exception e){
			jsonArray.add(0);
		}
		//return jsonArray;
		return String.valueOf( flag);
	}

	@Override
	public void save(List<Map<String, Object>> list) {
		for(Map<String, Object> map:list){
			this.saveData(map,  "PAND_GDJT", "ID");
		}
	}
}