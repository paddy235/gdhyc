package com.zhiren.fuelmis.dc.service.impl.hetgl;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ParameterMode;

import com.zhiren.fuelmis.dc.utils.formular.Result;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.hetgl.CaigddbDao;
import com.zhiren.fuelmis.dc.dao.hetgl.HetbeanDao;
import com.zhiren.fuelmis.dc.entity.common.Fujxx;
import com.zhiren.fuelmis.dc.entity.hetgl.Caigddb;
import com.zhiren.fuelmis.dc.entity.hetgl.Caigddbsub;
import com.zhiren.fuelmis.dc.entity.hetgl.Hetbean;
import com.zhiren.fuelmis.dc.entity.hetgl.Hetbxianggfzb;
import com.zhiren.fuelmis.dc.entity.xitgl.Diancxx;
import com.zhiren.fuelmis.dc.entity.xitgl.Gongys;
import com.zhiren.fuelmis.dc.service.hetgl.HetbeanService;
import com.zhiren.fuelmis.dc.utils.CnUpperCaser;
import com.zhiren.fuelmis.dc.utils.PropertiesUtil;
import com.zhiren.fuelmis.dc.utils.Sequence;


@Service("hetbeanService")
public class HetbeanServiceImpl implements HetbeanService{

	@Autowired
	private HetbeanDao hetbeanDao;

	@Autowired
	private CaigddbDao caigddbDao;
	@Autowired
    private JdbcTemplate jdbcTemplate;
	@Override
	public JSONArray addHetbean(Hetbean hetbean, JSONObject subs,Long diancxxb_id) {
		int result = 0;
		try{
			
			String subId = subs.getString("id");
			if(subId!=null&&!("").equals(subId)){
				Map<String,Object> param = new HashMap<String, Object>();
				param.put("id",subId);
				List<Caigddb> list = caigddbDao.getCaigddbById(param);
				Caigddb caigddb = null;
				if(list.size()>0){
					caigddb = list.get(0);
					hetbean.setQisrq(caigddb.getKaissj());
					hetbean.setGuoqrq(caigddb.getJiessj());
					hetbean.setJihkj_id(caigddb.getKouj_id());
				}
				List<Caigddbsub> sublist = caigddbDao.getCaigddbsubById(param);
				if(sublist.size()>0){
					Caigddbsub caigddbsub = sublist.get(0);
					hetbean.setHetl(caigddbsub.getShul());
				}
			}
			
			Long id = Long.parseLong(Sequence.nextId());
			hetbean.setId(id);
			result = hetbeanDao.addHetbean(hetbean);
			//添加数据到历史记录表
			hetbeanDao.addHetbeantmp(hetbean);
			
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("caigddbid", subs.getString("id"));
			map.put("hetbid", id);
			hetbeanDao.updateCaigddb(map);			
			
			Long xufid = hetbean.getXuf();
			Map<String,Object> dcmap = new HashMap<String, Object>();
			dcmap.put("id",xufid);
			Diancxx dianc = null;
			//需方数据
			List<Diancxx> dianclist =  hetbeanDao.DiancxxById(dcmap);
			if(dianclist.size()>0){
				dianc = dianclist.get(0);
			}
			
			Hetbxianggfzb hetbxianggfzb = new Hetbxianggfzb();
			hetbxianggfzb.setId(Long.parseLong(Sequence.nextId()));
			hetbxianggfzb.setDiancxxb_id(diancxxb_id);
			hetbxianggfzb.setHetb_id(id);
			hetbxianggfzb.setXianggflx("需方");
			hetbxianggfzb.setXiangf_id(dianc.getId());
			hetbxianggfzb.setDanwmc(dianc.getMingc());
			hetbxianggfzb.setFaddbr(dianc.getFaddbr());
			//授权代表需方暂时没有
			//hetbxianggfzb.setShouqdb(dianc.);
			hetbxianggfzb.setYouzbm(dianc.getYouzbm());
			hetbxianggfzb.setDianh(dianc.getDianh());
			hetbxianggfzb.setKaihyh(dianc.getKaihyh());
			hetbxianggfzb.setYinhzh(dianc.getZhangh());
			hetbxianggfzb.setShuih(dianc.getShuih());
			hetbxianggfzb.setChuanz(dianc.getChuanz());
			hetbeanDao.addHetbxianggfzb(hetbxianggfzb);
			
			//供方数据
			Long gongfid = hetbean.getGongf();
			Map<String,Object> gysmap = new HashMap<String, Object>();
			gysmap.put("id",gongfid);
			List<Gongys> gyslist = hetbeanDao.getGongysById(gysmap);
			Gongys gys = null;
			if(gyslist.size()>0){
				gys = gyslist.get(0);
			}
			
			Hetbxianggfzb hetbxianggfzb1 = new Hetbxianggfzb();
			hetbxianggfzb1.setId(Long.parseLong(Sequence.nextId()));
			hetbxianggfzb1.setDiancxxb_id(diancxxb_id);
			hetbxianggfzb1.setHetb_id(id);
			hetbxianggfzb1.setXianggflx("供方");
			hetbxianggfzb1.setXiangf_id(gys.getId());
			hetbxianggfzb1.setDanwmc(gys.getMingc());
			hetbxianggfzb1.setFaddbr(gys.getFaddbr());
			//hetbxianggfzb1.setShouqdb(gys.getsh);
			hetbxianggfzb1.setYouzbm(gys.getYouzbm());
			hetbxianggfzb1.setDianh(gys.getDianh());
			hetbxianggfzb1.setKaihyh(gys.getKaihyh());
			hetbxianggfzb1.setYinhzh(gys.getZhangh());
			hetbxianggfzb1.setShuih(gys.getShuih());
			hetbxianggfzb1.setChuanz(gys.getChuanz());
			hetbeanDao.addHetbxianggfzb(hetbxianggfzb1);
						
		}catch(Exception e){
			result = 0;
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONArray updateHetbean(Hetbean hetbean, JSONObject subs,Long diancxxb_id) {
		int result = 1;
		try {
			String subId = subs.getString("id");
			if(subId!=null&&!("").equals(subId)){
				Map<String,Object> param = new HashMap<String, Object>();
				param.put("id",subId);
				List<Caigddb> list = caigddbDao.getCaigddbById(param);
				Caigddb caigddb = null;
				if(list.size()>0){
					caigddb = list.get(0);
					hetbean.setQisrq(caigddb.getKaissj());
					hetbean.setGuoqrq(caigddb.getJiessj());
					hetbean.setJihkj_id(caigddb.getKouj_id());
				}
				List<Caigddbsub> sublist = caigddbDao.getCaigddbsubById(param);
				if(sublist.size()>0){
					Caigddbsub caigddbsub = sublist.get(0);
					hetbean.setHetl(caigddbsub.getShul());
				}
			}
			hetbeanDao.updateHetbean(hetbean);
			Long id = hetbean.getId();
			
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("caigddbid", subs.getString("id"));
			map.put("hetbid", id);
			map.put("id", id);
			hetbeanDao.delCaigddbid(map);
			hetbeanDao.updateCaigddb(map);
			
			//删除原有合同方表数据
			Map<String,Object> htmap = new HashMap<String, Object>();
			htmap.put("id", id);
		    hetbeanDao.delHetbxianggfzb(htmap);
			
			Long xufid = hetbean.getXuf();
			Map<String,Object> dcmap = new HashMap<String, Object>();
			dcmap.put("id",xufid);
			Diancxx dianc = null;
			
			//新增需方数据
			List<Diancxx> dianclist =  hetbeanDao.DiancxxById(dcmap);
			if(dianclist.size()>0){
				dianc = dianclist.get(0);
			}
			
			Hetbxianggfzb hetbxianggfzb = new Hetbxianggfzb();
			hetbxianggfzb.setId(Long.parseLong(Sequence.nextId()));
			hetbxianggfzb.setDiancxxb_id(diancxxb_id);
			hetbxianggfzb.setHetb_id(id);
			hetbxianggfzb.setXianggflx("需方");
			hetbxianggfzb.setXiangf_id(dianc.getId());
			hetbxianggfzb.setDanwmc(dianc.getMingc());
			hetbxianggfzb.setFaddbr(dianc.getFaddbr());
			//授权代表需方暂时没有
			//hetbxianggfzb.setShouqdb(dianc.);
			hetbxianggfzb.setYouzbm(dianc.getYouzbm());
			hetbxianggfzb.setDianh(dianc.getDianh());
			hetbxianggfzb.setKaihyh(dianc.getKaihyh());
			hetbxianggfzb.setYinhzh(dianc.getZhangh());
			hetbxianggfzb.setShuih(dianc.getShuih());
			hetbxianggfzb.setChuanz(dianc.getChuanz());
			hetbeanDao.addHetbxianggfzb(hetbxianggfzb);
			
			//新增供方数据
			Long gongfid = hetbean.getGongf();
			Map<String,Object> gysmap = new HashMap<String, Object>();
			gysmap.put("id",gongfid);
			List<Gongys> gyslist = hetbeanDao.getGongysById(gysmap);
			Gongys gys = null;
			if(gyslist.size()>0){
				gys = gyslist.get(0);
			}
			
			Hetbxianggfzb hetbxianggfzb1 = new Hetbxianggfzb();
			hetbxianggfzb1.setId(Long.parseLong(Sequence.nextId()));
			hetbxianggfzb1.setDiancxxb_id(diancxxb_id);
			hetbxianggfzb1.setHetb_id(id);
			hetbxianggfzb1.setXianggflx("供方");
			hetbxianggfzb1.setXiangf_id(gys.getId());
			hetbxianggfzb1.setDanwmc(gys.getMingc());
			hetbxianggfzb1.setFaddbr(gys.getFaddbr());
			//hetbxianggfzb1.setShouqdb(gys.getsh);
			hetbxianggfzb1.setYouzbm(gys.getYouzbm());
			hetbxianggfzb1.setDianh(gys.getDianh());
			hetbxianggfzb1.setKaihyh(gys.getKaihyh());
			hetbxianggfzb1.setYinhzh(gys.getZhangh());
			hetbxianggfzb1.setShuih(gys.getShuih());
			hetbxianggfzb1.setChuanz(gys.getChuanz());
			hetbeanDao.addHetbxianggfzb(hetbxianggfzb1);
		
		} catch (Exception e) {
			result = 0;
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONObject getHetbeanList(Map<String,Object> map) {
	    Map<String,Object> jsonMap=new HashMap<String,Object>();
		List<Map<String,Object>> list = hetbeanDao.getHetbeanList(map);
        for (Map<String,Object> het:list) {
            List<Map<String,Object>> fujList=jdbcTemplate.queryForList("select id,mingc from fujxx where guanlid="+het.get("ID")+" and yewly='rl_ht_hetb'");
            String fuj="";
            for (Map<String,Object> f:fujList) {
               fuj+="<a href='file/downloadFileById?fileId="+f.get("ID")+"'>"+f.get("MINGC")+"</a><br>";
            }
            het.put("FUJ",fuj);
        }
       String objs[][]= Result.list2array(list,new String[]{"ID","HETBH","GONGF","QIANDRQ","ZHUANGT","LURRY","FUJ"});
		jsonMap.put("data", objs);						
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	public JSONObject getHetbeaninfoList(String strdate, String enddate,
			String diancid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONArray delHetbean(Map<String, Object> map) {
		int result = 0;
		try{
			hetbeanDao.delHetbxianggfzb(map);
			hetbeanDao.delCaigddgl(map);
			result = hetbeanDao.delHetbean(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONObject getHetbeanById(Map<String, Object> map) {
		List<Hetbean> list = hetbeanDao.getHetbeanById(map);
		Hetbean hetbean = null;
		if(list.size()>0){
			hetbean = list.get(0);
		}
		JSONObject json = JSONObject.fromObject(hetbean);
		return json;
	}

	@Override
	public JSONArray getCaigddbById(Map<String, Object> map) {
		List<Map<String,Object>> list = hetbeanDao.getCaigddbById(map);
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			HashMap<String, Object> dataMap = (HashMap<String, Object>) list.get(i);
			JSONObject json = new JSONObject();
			json.put("id", dataMap.get("ID"));
			json.put("bianh", dataMap.get("BIANH"));
			jsonArray.add(json);
		}	
		return jsonArray;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getGongysById(String gongysid) {
		@SuppressWarnings("rawtypes")
		Map map = new HashMap<String, Object>();
		map.put("id", gongysid);
		Gongys gongys = null;
		List<Gongys> list = hetbeanDao.getGongysById(map);
		if(list.size()>0){
			gongys =  list.get(0);
		}
		JSONObject json = JSONObject.fromObject(gongys);
		return json;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getDiancxxById(String id) {
		@SuppressWarnings("rawtypes")
		Map map = new HashMap<String, Object>();
		map.put("id", id);
		Diancxx dianc = null;
		List<Diancxx> list = hetbeanDao.getDiancxxById(map);
		if(list.size()>0){
			dianc = list.get(0);
		}
		JSONObject json = JSONObject.fromObject(dianc);
		return json;
	}

	
	
	@Override
	public JSONArray getCaigddbsub(JSONArray subs) {
		List<Caigddbsub> list = new ArrayList<Caigddbsub>();
		for(int i=0; i<subs.size(); i++){
			JSONObject json = (JSONObject)subs.get(i);
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("id", json.get("id"));					
			List<Caigddbsub> sublist = hetbeanDao.getCaigddbsub(map);
			if(sublist.size()>0){
				list.addAll(sublist);	
			}
		}
		JSONArray json = JSONArray.fromObject(list);
		return json;
	}

	@Override
	public JSONArray createDoc(String id) {
		int result = 1;
//		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("guanlid", id);
//		List<Fujxx> list = hetbeanDao.getFujxxById(map);		
//		if (list.size()>0) {
//			Fujxx fujxx = list.get(0);
//			String path = fujxx.getLuj();
//			Map<String, String> pmap = new HashMap<String, String>();  
//			pmap.put("${Suppliers}", "hello");
//			pmap.put("${Amount}", "world");
//			
//			try {
//				HWPFDocument document = 
//						new HWPFDocument(
//								new FileInputStream(path));
//				Range range = document.getRange();  
//				for (Map.Entry<String, String> entry : pmap.entrySet()) {  
//					range.replaceText(entry.getKey(), entry.getValue());  
//				}  
//				FileOutputStream outStream = null;  
//				outStream = new FileOutputStream("D:/返回结果.doc");  
//				document.write(outStream);  
//				outStream.close();  
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}			
//		}
		
		Map<String, String> pmap = new HashMap<String, String>();
				
		org.apache.axis.client.Service service = new org.apache.axis.client.Service();
		Call call;
		try {
			call = (Call) service.createCall();
			
			String fileurl = PropertiesUtil.getValue("filemis_url");
			fileurl +="/FileService.jws";
			//call.setTargetEndpointAddress(new java.net.URL("http://10.115.25.123:8080/FileMIS/FileService.jws"));
			call.setTargetEndpointAddress(new java.net.URL(fileurl));
			call.setOperationName("downLoadFile");
			call.addParameter("FileID", XMLType.SOAP_STRING,ParameterMode.IN);
			call.setReturnType(XMLType.SOAP_BASE64BINARY);
			byte[] b = (byte[]) call.invoke(new Object[] {"20241137"});
			
			
			InputStream input = new ByteArrayInputStream(b);
			//文件下载
			File newFile = new File("D:/", "webservice返回文件.doc");
			writeFile(b, newFile);
			HWPFDocument document = new HWPFDocument(input);
			Range range = document.getRange();  
			for (Map.Entry<String, String> entry : pmap.entrySet()) {  
				range.replaceText(entry.getKey(), entry.getValue());  
			}  
			
			
			FileOutputStream outStream = null;  
			outStream = new FileOutputStream("D:/服务器返回结果.doc");  
			document.write(outStream);  
			outStream.close(); 			

			
			//服务器上传文件
//			String strAppID = "智仁燃料系统.合同";
//			String strOPID = "433";
//			// TODO Auto-generated method stub
//			File f = new File("D:\\", "服务器返回结果.doc");
//			
//			
//			byte[] FileData = getBytesFromFile(f);
//			org.apache.axis.client.Service service1 = new org.apache.axis.client.Service();
//			Call call1 = (Call) service1.createCall();//远程调用者
//			call1.setTargetEndpointAddress(new java.net.URL("http://10.115.25.123:8080/FileMIS/FileService.jws"));
//			call1.setOperationName("upLoadFile");
//  		call1.addParameter("FileID", XMLType.SOAP_STRING,ParameterMode.IN);
//			call1.addParameter("AppID", XMLType.SOAP_STRING,ParameterMode.IN);
//			call1.addParameter("OperationID", XMLType.SOAP_STRING,ParameterMode.IN);
//			call1.addParameter("FileName", XMLType.SOAP_STRING,ParameterMode.IN);
//			call1.addParameter("Description", XMLType.SOAP_STRING,ParameterMode.IN);
//			call1.addParameter("FileData", XMLType.SOAP_BASE64BINARY,ParameterMode.IN);
//			call1.setReturnType(XMLType.SOAP_STRING);
//			String reValue=String.valueOf(call1.invoke(new Object[] { 
//					strAppID, strOPID, "测试合同模板.doc", "测试", FileData}));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//远程调用者
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}
	
	
	public static void writeFile(byte[] FileData, File f) throws IOException {
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f));
		bos.write(FileData);
		bos.close();
	}
	
	@SuppressWarnings("resource")
	public static byte[] getBytesFromFile(File file) throws IOException {
	    InputStream is = new FileInputStream(file);

	    // Get the size of the file
	    long length = file.length();

	    // You cannot create an array using a long type.
	    // It needs to be an int type.
	    // Before converting to an int type, check
	    // to ensure that file is not larger than Integer.MAX_VALUE.
	    if (length > Integer.MAX_VALUE) {
	        // File is too large
	    }

	    // Create the byte array to hold the data
	    byte[] bytes = new byte[(int)length];

	    // Read in the bytes
	    int offset = 0;
	    int numRead = 0;
	    while (offset < bytes.length
	           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	        offset += numRead;
	    }

	    // Ensure all the bytes have been read in
	    if (offset < bytes.length) {
	        throw new IOException("Could not completely read file "+file.getName());
	    }

	    // Close the input stream and return bytes
	    is.close();
	    return bytes;
	}
	
	
	public static void main(String[] args) {
		org.apache.axis.client.Service service = new org.apache.axis.client.Service();
//		 
//		try {
//			Call call = (Call) service.createCall();
//			call.setTargetEndpointAddress(new java.net.URL("http://10.115.25.123:8080/FileMIS/FileService.jws"));
//			call.setOperationName("downLoadFile");
//			call.addParameter("FileID", XMLType.SOAP_STRING,ParameterMode.IN);
//			call.setReturnType(XMLType.SOAP_BASE64BINARY);
//			byte[] b = (byte[]) call.invoke(new Object[] {"20241137"});
//			for (int i = 0; i < b.length; i++) {
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}//远程调用者
		
		Map<String, String> pmap = new HashMap<String, String>();  

		
		
		Call call;
		try {
			call = (Call) service.createCall();
			call = (Call) service.createCall();
			
			String fileurl = PropertiesUtil.getValue("filemis_url");
			fileurl +="/FileService.jws";
			//call.setTargetEndpointAddress(new java.net.URL("http://10.115.25.6:8088/FileMIS/FileService.jws"));
			call.setTargetEndpointAddress(new java.net.URL(fileurl));
			call.setOperationName("downLoadFile");
			call.addParameter("FileID", XMLType.SOAP_STRING,ParameterMode.IN);
			call.setReturnType(XMLType.SOAP_BASE64BINARY);
			byte[] b = (byte[]) call.invoke(new Object[] {"20241250"});
			
			InputStream input = new ByteArrayInputStream(b);
			//文件下载
//			File newFile = new File("D:/", "webservice返回文件.doc");
//			writeFile(b, newFile);
			HWPFDocument document = new HWPFDocument(input);
			Range range = document.getRange();  
			for (Map.Entry<String, String> entry : pmap.entrySet()) {  
				range.replaceText(entry.getKey(), entry.getValue());  
			}  
			
			
			FileOutputStream outStream = null;  
			outStream = new FileOutputStream("D:/服务器返回结果1.doc");  
			document.write(outStream);  
			outStream.close(); 	
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@SuppressWarnings("unchecked")
	public Fujxx getFujxxById(String id){
		@SuppressWarnings("rawtypes")
		Map map = new HashMap<String, Object>();
		map.put("id", id);
		Fujxx fujxx = null;
		List<Fujxx> list = hetbeanDao.getFujxxById(map);
		if(list.size()>0){
			fujxx = list.get(0);
		}
		return fujxx;
	}
	
	@Override
	public HWPFDocument getHWPFDocument(String id) {
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", id);
		JSONObject json = getHetbeanById(map);
	//	String gongysid = json.get("gongf").toString();
	//	String xufId = json.get("xuf").toString();
	//	JSONObject xuf = getDiancxxById(xufId);
	//	JSONObject gys = getGongysById(gongysid);
		String fujxxId = json.get("fujxxId").toString();		
		//合同对象
	//	Hetbean hetbean = (Hetbean) JSONObject.toBean(json,Hetbean.class);
		//需方
	//	Diancxx diancxx = (Diancxx) JSONObject.toBean(xuf, Diancxx.class);
		//供方
	//	Gongys gongys = (Gongys) JSONObject.toBean(gys,Gongys.class);
		//合同模板对象
		Fujxx fujxx = getFujxxById(fujxxId);
		
		org.apache.axis.client.Service service = new org.apache.axis.client.Service();		
		Map<String, String> pmap = new HashMap<String, String>();  
		String fuwid = fujxx.getFid().toString();
		HWPFDocument document = null;
		Call call;
		try {
			call = (Call) service.createCall();
			call = (Call) service.createCall();
			String fileurl = PropertiesUtil.getValue("filemis_url");
			fileurl +="/FileService.jws";
			//call.setTargetEndpointAddress(new java.net.URL("http://10.115.25.199:9099/FileMIS/FileService.jws"));
			call.setTargetEndpointAddress(new java.net.URL(fileurl));
			call.setOperationName("downLoadFile");
			call.addParameter("FileID", XMLType.SOAP_STRING,ParameterMode.IN);
			call.setReturnType(XMLType.SOAP_BASE64BINARY);
			byte[] b = (byte[]) call.invoke(new Object[] {fuwid});			
			InputStream input = new ByteArrayInputStream(b);
			//文件下载
			document = new HWPFDocument(input);
			Range range = document.getRange();  
			for (Map.Entry<String, String> entry : pmap.entrySet()) {  
				range.replaceText(entry.getKey(), entry.getValue());  
			}  		
		}catch(Exception e){
			e.printStackTrace();
		}
		return document;
	}
	
	@Override
	public byte[] getbytes(String id) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", id);
		JSONObject json = getHetbeanById(map);
	//	String gongysid = json.get("gongf").toString();
	//	String xufId = json.get("xuf").toString();
	//	JSONObject xuf = getDiancxxById(xufId);
	//	JSONObject gys = getGongysById(gongysid);
		String fujxxId = json.get("fujxxId").toString();		
		//合同对象
	//	Hetbean hetbean = (Hetbean) JSONObject.toBean(json,Hetbean.class);
		//需方
	//	Diancxx diancxx = (Diancxx) JSONObject.toBean(xuf, Diancxx.class);
		//供方
	//	Gongys gongys = (Gongys) JSONObject.toBean(gys,Gongys.class);
		//合同模板对象
		Fujxx fujxx = getFujxxById(fujxxId);
		
		org.apache.axis.client.Service service = new org.apache.axis.client.Service();		
	//	Map<String, String> pmap = new HashMap<String, String>();  
		String fuwid = fujxx.getFid().toString();
	//	HWPFDocument document = null;
		Call call;
		byte[] b = null;
		try {
			call = (Call) service.createCall();
			call = (Call) service.createCall();
			
			String fileurl = PropertiesUtil.getValue("filemis_url");
			fileurl +="/FileService.jws";
			//call.setTargetEndpointAddress(new java.net.URL("http://10.115.25.199:9099/FileMIS/FileService.jws"));
			call.setTargetEndpointAddress(new java.net.URL(fileurl));
			
			call.setOperationName("downLoadFile");
			call.addParameter("FileID", XMLType.SOAP_STRING,ParameterMode.IN);
			call.setReturnType(XMLType.SOAP_BASE64BINARY);
			b = (byte[]) call.invoke(new Object[] {fuwid});			
//			InputStream input = new ByteArrayInputStream(b);
//			//文件下载
//			document = new HWPFDocument(input);
//			Range range = document.getRange();  
//			for (Map.Entry<String, String> entry : pmap.entrySet()) {  
//				range.replaceText(entry.getKey(), entry.getValue());  
//			}  		
		}catch(Exception e){
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public Map<String, String> getPmapMap(String id) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", id);
		JSONObject json = getHetbeanById(map);
	//	String gongysid = json.get("gongf").toString();
		String xufId = json.get("xuf").toString();
		JSONObject xuf = getDiancxxById(xufId);
	//	JSONObject gys = getGongysById(gongysid);
	//	String fujxxId = json.get("fujxxId").toString();		
		//合同对象
		Hetbean hetbean = (Hetbean) JSONObject.toBean(json,Hetbean.class);
		//需方
		Diancxx diancxx = (Diancxx) JSONObject.toBean(xuf, Diancxx.class);
		//供方
//		Gongys gongys = (Gongys) JSONObject.toBean(gys,Gongys.class);
		//合同模板对象
		
		Map<String,String> pmapMap = new HashMap<String, String>();
		
		pmapMap.put("hetmb", hetbean.getHetbh());
		pmapMap.put("qianddd", hetbean.getQianddd());
		pmapMap.put("qiandrq",hetbean.getQiandrq());
		pmapMap.put("hetl",hetbean.getHetl().toString());
		pmapMap.put("hetj",hetbean.getHetjg().toString());
		String hetjgdx = new CnUpperCaser(hetbean.getHetjg().toString()).getCnString();
		pmapMap.put("hetjdx",hetjgdx);
		pmapMap.put("xufmc",diancxx.getMingc());
		pmapMap.put("xufdz",diancxx.getDiz());
		pmapMap.put("xufdh",diancxx.getDianh());
		pmapMap.put("xufcz",diancxx.getChuanz());
		pmapMap.put("xufkhyh ",diancxx.getKaihyh());
		pmapMap.put("xufzh",diancxx.getZhangh());
		pmapMap.put("xufsh",diancxx.getShuih());
		pmapMap.put("xufyb",diancxx.getYouzbm());		
		return pmapMap;
	}

	@Override
	public void updateHetbeanSanjzt(String id) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", id);
		hetbeanDao.updateHetbeanSanjzt(map);
	}

	@Override
	public JSONArray getCaigddbList(Map<String, Object> map) {
		JSONArray result = new JSONArray();
		List<Map<String,Object>> list = hetbeanDao.getCaigddbList(map);
		@SuppressWarnings("unused")
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JSONObject json  = new JSONObject();
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> dataMap = (HashMap<String, Object>) list.get(i);
			json.put("id",dataMap.get("ID"));
			json.put("dingdrq", dataMap.get("DINGDRQ"));
			json.put("caigddlx", dataMap.get("CAIGDDLX"));
			json.put("bianh", dataMap.get("BIANH"));
			json.put("gongys_id", dataMap.get("GONGYS_ID"));
			json.put("diancxxb_id", dataMap.get("DIANCXXB_ID"));
			json.put("huowmc", dataMap.get("HUOWMC"));
			json.put("yunsfs", dataMap.get("YUNSFS"));
			json.put("shul", dataMap.get("SHUL"));
			json.put("zhil", dataMap.get("ZHIL"));
			json.put("daocj", dataMap.get("DAOCJ"));
			json.put("jihkj", dataMap.get("JIHKJ"));
			json.put("kaissj", dataMap.get("KAISSJ"));
			json.put("jiessj", dataMap.get("JIESSJ"));
			result.add(json);
		}
		return result;
	}

	@Override
	public JSONArray getKaohzbbList(Map<String, Object> map) {
		JSONArray result = new JSONArray();
		List<Map<String,Object>> list = hetbeanDao.getKaohzbbList(map);
		if (list.size()>0) {
			StringBuffer stb;
			for(int i = 0;i<list.size();i++){
				stb = new StringBuffer();
				HashMap<String, Object> dataMap = (HashMap<String, Object>) list.get(i);
				String type = dataMap.get("LEIX")==null?"":dataMap.get("LEIX").toString();
				double kouj = Double.parseDouble(dataMap.get("TIAOJ")==null?"0":dataMap.get("TIAOJ").toString());
				String strkj;
				if(kouj>0){
					strkj = "每增加";
				}else{
					strkj = "每降低";
				}
				if(("热值").equals(type)){
					JSONObject json = new JSONObject();
					String danw = "kcal/kg";
					stb.append("热值").append("在").append(dataMap.get("SHANGX").toString()).append("-").
					append(dataMap.get("XIAX").toString()).append(danw+",").append(strkj).append(dataMap.get("REZJS").toString()).
					append(danw).append("价格").append(strkj).append(dataMap.get("TIAOJ").toString()).append("元/吨");
					json.put("miaos", stb.toString());
					result.add(json);
				}
//				json.put("id",dataMap.get("ID"));
//				json.put("shangx", dataMap.get("SHANGX"));
//				json.put("jizjg", dataMap.get("JIZJG"));
//				json.put("xiax", dataMap.get("XIAX"));
//				json.put("shijrz", dataMap.get("SHIJRZ"));
//				json.put("jizrz", dataMap.get("JIZRZ"));
//				json.put("rezjs", dataMap.get("REZJS"));
//				json.put("jizzb", dataMap.get("JIZZB"));
//				json.put("jiangkjs", dataMap.get("JIANGKJS"));
//				json.put("jijgs_id", dataMap.get("JIJGS_ID"));
//				json.put("jizkj", dataMap.get("JIZKJ"));
//				json.put("leix", dataMap.get("LEIX"));
//				json.put("tiaoj", dataMap.get("TIAOJ"));	
			
			}
		}
		
		
		return result;
	}

	@Override
	public JSONObject getCaigddb(Map<String, Object> map) {
		List<Map<String,Object>> list = hetbeanDao.getCaigddb(map);
		@SuppressWarnings("unused")
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JSONObject json  = new JSONObject();
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> dataMap = (HashMap<String, Object>) list.get(i);
			json.put("id",dataMap.get("ID"));
			json.put("dingdrq", dataMap.get("DINGDRQ"));
			json.put("caigddlx", dataMap.get("CAIGDDLX"));
			json.put("bianh", dataMap.get("BIANH"));
			json.put("gongys_id", dataMap.get("GONGYS_ID"));
			json.put("diancxxb_id", dataMap.get("DIANCXXB_ID"));
			json.put("huowmc", dataMap.get("HUOWMC"));
			json.put("yunsfs", dataMap.get("YUNSFS"));
			json.put("shul", dataMap.get("SHUL"));
			json.put("zhil", dataMap.get("ZHIL"));
			json.put("daocj", dataMap.get("DAOCJ"));
			json.put("jihkj", dataMap.get("JIHKJ"));
			json.put("kaissj", dataMap.get("KAISSJ"));
			json.put("jiessj", dataMap.get("JIESSJ"));
		}
		return json;

	}

	@Override
	public JSONArray getRlhtmbList(Map<String, Object> map) {
		List<Map<String,Object>> list = hetbeanDao.getRlhtmbList(map);
		@SuppressWarnings("unused")
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JSONArray result = new JSONArray();
		JSONObject json  = null;
		for(int i = 0;i<list.size();i++){
			json  = new JSONObject();
			HashMap<String, Object> dataMap = (HashMap<String, Object>) list.get(i);
			json.put("id",dataMap.get("ID"));
			json.put("mingc", dataMap.get("MINGC"));
			json.put("luj", dataMap.get("LUJ"));
			json.put("mublj", dataMap.get("MUBLJ"));
			result.add(json);
		}		
		return result;
	}

	@Override
	public String getHetbh(String hetbh) {
		List<Map<String, Object>> bianh=hetbeanDao.getHetbh(hetbh);
		if(bianh.size()!=0){
			return "1";
		}else{
			return "0";
		}

	}

}
