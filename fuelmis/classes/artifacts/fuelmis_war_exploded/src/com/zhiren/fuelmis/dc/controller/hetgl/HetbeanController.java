package com.zhiren.fuelmis.dc.controller.hetgl;

import org.apache.commons.codec.binary.Base64;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.ParameterMode;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.codehaus.xfire.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.zhiren.fuelmis.dc.entity.hetgl.Hetbean;
import com.zhiren.fuelmis.dc.entity.xitgl.Diancxx;
import com.zhiren.fuelmis.dc.entity.xitgl.Gongys;
import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.common.ICommonService;
import com.zhiren.fuelmis.dc.service.hetgl.HetbeanService;
import com.zhiren.fuelmis.dc.utils.PropertiesUtil;
import com.zhiren.fuelmis.dc.utils.Sequence;

@Controller
@RequestMapping("hetgl/hetinfo")
public class HetbeanController {

	@Autowired
	private HetbeanService hetbeanService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ICommonService commonService;

	/**
	 * ????????????????????????
	 * 
	 * @param info
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/addHetbean")
	public void addHetbean(@RequestParam String info, @RequestParam String data, @RequestParam String subinfo,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		JSONObject subs = JSONObject.fromObject(subinfo);
		System.out.println(subs);
		// -------------------------------------------------------------------------------------------------------------
		PrintWriter writer = null;
		JSONObject json = JSONObject.fromObject(info);
		// ??????????????????
		// Diancxx diancxx = (Diancxx) session.getAttribute("diancxx");
		@SuppressWarnings("static-access")
		Hetbean hetbean = (Hetbean) json.toBean(json, Hetbean.class);
		// ----------------------------------??????????????????png---------------------------------------------------------------------

//		Base64 base64 = new Base64();
//		// ??????????????????????????????????????? data:image/jpeg;base64, ????????????
//		String d = data.substring("data:image/png;base64,".length() + 1);
//		byte[] k = base64.decode(d);
//		InputStream is = new ByteArrayInputStream(k);
//		String fujmc = hetbean.getHetbh() + ".png";
//		String imgFilePath = "E:\\FileMis\\" + fujmc;
//		BufferedImage image;
//		try {
//			image = ImageIO.read(is);
//			ImageIO.write(image, "png", new File(imgFilePath));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		// --------------------------------------------------------------------------------------------------------------------

//		hetbean.setFujmc(imgFilePath);
		// Renyxx renyxx = (Renyxx) session.getAttribute("user");
		// session??????
		// hetbean.setCaozry(renyxx.getMingc());
		// Long diancxxb_id = diancxx.getId();
		Long diancxxb_id = 515l;

//		 String riq = hetbean.getRiq();
//		 if(riq != null){
//		 String bianm = hetbeanService.getBianm(riq);
//		 riq = riq.replaceAll("-", "");
//		 if(bianm.equals("0")){
//		 bianm = riq+"1";
//		 }else{
//		 bianm = riq + (Long.parseLong(bianm)+1);
//		 }
		// hetbean.setBianh(bianm);
		// }
		// if(diancxx.getId()!=null){
		// hetbean.setDiancxxb_id(diancxx.getId());
		// }else{
		// hetbean.setDiancxxb_id(515);
		// }

//		 hetbean.setCaozsj(DateUtil.format(new Date()));
		String hetbh = subs.getString("bianh");
		String qiandrq  = subs.getString("dingdrq");
		hetbean.setHetbh(hetbh);
		hetbean.setQiandrq(qiandrq);
		hetbean.setDiancxxb_id(diancxxb_id);
		// ????????????id
		Renyxx renyxx = (Renyxx) session.getAttribute("user");
		hetbean.setLurry(renyxx.getId());
		hetbean.setZhuangt(1);
		JSONArray jsonArray = hetbeanService.addHetbean(hetbean, subs, diancxxb_id);
		// String id = "";

		// for(int i=0; i<jsonArray.size(); i++){
		// YwFencsl ywFencsl = (YwFencsl)
		// JSONObject.toBean((JSONObject)jsonArray.get(i),YwFencsl.class);
		// ywFencsl.setShuicdid(resultid);
		// objList.add(ywFencsl);
		// }

		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	@RequestMapping(value = "/updateHetbean")
	public void updateHetbean(@RequestParam String info, @RequestParam String subinfo,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		JSONObject json = JSONObject.fromObject(info);

		// ??????????????????
		Diancxx diancxx = (Diancxx) session.getAttribute("diancxx");
		Hetbean hetbean = (Hetbean) json.toBean(json, Hetbean.class);
		JSONObject subs = JSONObject.fromObject(subinfo);
		// ----------------------------------??????????????????png---------------------------------------------------------------------
//
//		Base64 base64 = new Base64();
//		// ??????????????????????????????????????? data:image/jpeg;base64, ????????????
//		String d = data.substring("data:image/png;base64,".length() + 1);
//		byte[] k = base64.decode(d);
//		InputStream is = new ByteArrayInputStream(k);
//		String fujmc = hetbean.getHetbh() + ".png";
//		String imgFilePath = "E:\\FileMis\\" + fujmc;
//		BufferedImage image;
//		try {
//			image = ImageIO.read(is);
//			ImageIO.write(image, "png", new File(imgFilePath));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		// --------------------------------------------------------------------------------------------------------------------

		JSONArray jsonArray = hetbeanService.updateHetbean(hetbean, subs, diancxx.getId());
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ??????????????????????????????
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getHetbeanList")
	public void getHetbeanList(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = hetbeanService.getHetbeanList(null);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getHetbh")
	public void getHetbh(@RequestParam(defaultValue = "") String hetbh, HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String hetbhx = hetbeanService.getHetbh(hetbh);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(hetbhx);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ??????????????????????????????
	 * 
	 * @param request
	 * @param response
	 */

	@RequestMapping(value = "/searchHetbeanList")
	public void searchHetbeanList(@RequestParam String search, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Object> map = JSONObject.fromObject(search);
		JSONObject json = hetbeanService.getHetbeanList(map);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ????????????????????????
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getHetbeaninfoList")
	public void getHetbeaninfoList(@RequestParam String diancid, @RequestParam String strdate,
			@RequestParam String enddate, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		if (null == strdate || ("").equals(strdate)) {
			strdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		}
		if (null == diancid || "" == diancid) {
			diancid = "515";
		}

		JSONObject json = hetbeanService.getHetbeaninfoList(strdate, enddate, diancid);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ????????????????????????
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getHetbeaninfoList1")
	public void getHetbeaninfoList1(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String strdate = request.getParameter("strdate");
		String enddate = request.getParameter("enddate");
		String diancid = request.getParameter("diancid");
		if (null == strdate || ("").equals(strdate)) {
			strdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		}
		if (null == diancid || "" == diancid) {
			diancid = "515";
		}

		JSONObject json = hetbeanService.getHetbeaninfoList(strdate, enddate, diancid);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/delHetbean")
	public void delHetbean(@RequestParam String id, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		JSONArray json = hetbeanService.delHetbean(map);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ??????????????????
	 * 
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/editHetbean")
	public void editHetbean(@RequestParam String id, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);

		JSONObject json = hetbeanService.getHetbeanById(map);
		// Caigddb
		JSONObject caigdd = hetbeanService.getCaigddb(map);

		// ???????????? ????????????
		// JSONArray subs = hetbeanService.getCaigddbById(map);

		// JSONArray caigddbsubs = hetbeanService.getCaigddbsub(subs);
		String gongysid = json.get("gongf").toString();
		String xufId = json.get("xuf").toString();
		JSONObject xuf = hetbeanService.getDiancxxById(xufId);
		JSONObject gys = hetbeanService.getGongysById(gongysid);
		// ???????????? ????????????

		JSONObject result = new JSONObject();

		result.put("hetbean", json);

		// ???????????? ????????????
		result.put("caigdd", caigdd);
		result.put("gongys", gys);
		// result.put("caigddbsubs", caigddbsubs);
		result.put("xuf", xuf);
		// ???????????? ????????????

		System.out.println("????????????" + result.toString());
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getCaigddbsub")
	public void getCaigddbsub(@RequestParam String subinfo, HttpServletRequest request, HttpServletResponse response) {
		JSONArray subs = JSONArray.fromObject(subinfo);
		JSONArray caigddbsubs = hetbeanService.getCaigddbsub(subs);
		JSONObject result = new JSONObject();
		result.put("caigddbsubs", caigddbsubs);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ??????????????????
	 * 
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getGongysById")
	public void getGongysById(@RequestParam String id, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		JSONObject gys = hetbeanService.getGongysById(id);
		JSONObject result = new JSONObject();
		result.put("gongys", gys);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ??????????????????
	 * 
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getDiancxxById")
	public void getDiancxxById(@RequestParam String id, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		JSONObject dianc = hetbeanService.getDiancxxById(id);
		JSONObject result = new JSONObject();
		result.put("dianc", dianc);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ??????????????????????????????
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getCaigddbList")
	public void getCaigddbList(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		JSONArray json = hetbeanService.getCaigddbList(null);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ????????????
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/createDoc")
	public void createDoc(@RequestParam String id, HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {

		Map<String, String> pmap = hetbeanService.getPmapMap(id);
		// ???????????????
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
		String uploadDir = path + File.separator;
		File file = new File(uploadDir);
		if (!file.exists()) {
			file.mkdir();
		}
		HWPFDocument document = hetbeanService.getHWPFDocument(id);
		Range range = document.getRange();
		for (Map.Entry<String, String> entry : pmap.entrySet()) {
			range.replaceText(entry.getKey(), entry.getValue());
		}
		FileOutputStream outStream = null;
		try {
			outStream = new FileOutputStream(uploadDir + "/?????????????????????4333.doc");
			document.write(outStream);
			outStream.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// OutputStream fos = null;
		InputStream fis = null;
		File attFile = new File(uploadDir + "/?????????????????????4333.doc");
		try {
			fis = new FileInputStream(attFile);
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			String fileName = "????????????.doc";
			response.reset();
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			response.setContentType("application/OCTET-STREAM");
			response.setHeader("Content-disposition", "attachment;filename=" + fileName);
			response.setHeader("Content-Length", String.valueOf(attFile.length()));
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (Exception e) {
			e.printStackTrace();
			String simplename = e.getClass().getSimpleName();
			if (!"ClientAbortException".equals(simplename)) {
				e.printStackTrace();
			} else {
			}
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/** */
	/**
	 * ???????????????
	 * 
	 * @param path
	 *            ????????????
	 * @param oldname
	 *            ??????????????????
	 * @param newname
	 *            ????????????
	 */
	private void renameFile(String path, String oldname, String newname) {
		if (!oldname.equals(newname)) {// ??????????????????????????????????????????,???????????????????????????
			File oldfile = new File(path + "/" + oldname);
			File newfile = new File(path + "/" + newname);
			if (!oldfile.exists()) {
				return;// ????????????????????????
			}
			if (newfile.exists())// ????????????????????????????????????????????????????????????????????????????????????
				System.out.println(newname + "???????????????");
			else {
				oldfile.renameTo(newfile);
			}
		} else {
			System.out.println("?????????????????????????????????...");
		}
	}

	/**
	 * ????????????
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/commitDoc")
	public void commitDoc(@RequestParam String id, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws UnsupportedEncodingException {
		response.setContentType("text/html;charset=UTF-8");
		@SuppressWarnings("unused")
		Map<String, String> pmap = hetbeanService.getPmapMap(id);

		String reValue = null;
		try {

			Diancxx diancxx = (Diancxx) session.getAttribute("diancxx");
			Renyxx renyxx = (Renyxx) session.getAttribute("user");

			Map<String, Object> hetmap = new HashMap<String, Object>();
			hetmap.put("id", id);
			Hetbean hetbean = (Hetbean) JSONObject.toBean(hetbeanService.getHetbeanById(hetmap), Hetbean.class);
			Long diancxxb_id = diancxx.getId();
			Long userid = renyxx.getId();
			String hetid = id;
			String diancmingc = diancxx.getMingc();
			String quanc = diancxx.getQuanc();
			String hetbh = hetbean.getHetbh();
			String gongysid = hetbean.getGongf().toString();
			String gongfdwmc = "";
			Gongys gongys = null;
			// ??????????????????????????????
			if (gongysid != null && !("").equals(gongysid)) {
				gongys = (Gongys) JSONObject.toBean(hetbeanService.getGongysById(gongysid), Gongys.class);
				gongfdwmc = gongys.getMingc();
			}
			String qiandrq = hetbean.getQiandrq();
			String ret = null;
			// ????????????
			String renwms = "";
			renwms = "?????????" + diancmingc + "??????????????????" + hetbh + "???????????????" + gongfdwmc + "??????????????????" + qiandrq;
			String jiag = hetbean.getHetjg().toString();
			String fileid = reValue;

			// ---------------------------------??????????????????---------------------------------------------------------------------------
			String fujmc = hetbean.getFujmc();
			// Map<String, Object> map = new HashMap<String, Object>();
			// map.put("id", fujId);
			// String wenjmc = commonService.getWenjmc(map);
			String filePath = PropertiesUtil.getValue("upload_file_folder");// +fujId+wenjmc.substring(wenjmc.lastIndexOf("."));//
			byte[] fileBytes = File2byte(filePath + fujmc);
			System.out.println("==============filePath==============" + filePath + fujmc + "=======================");
			org.apache.axis.client.Service service1 = new org.apache.axis.client.Service();
			Call call1 = (Call) service1.createCall();// ???????????????
			String url = PropertiesUtil.getValue("filemis_url");
			System.out.println("==============url==============" + url + "=======================");
			call1.setTargetEndpointAddress(new java.net.URL(url));
			call1.setOperationName("upLoadFile");
			call1.addParameter("AppID", XMLType.SOAP_STRING, ParameterMode.IN);
			call1.addParameter("OperationID", XMLType.SOAP_STRING, ParameterMode.IN);
			call1.addParameter("FileName", XMLType.SOAP_STRING, ParameterMode.IN);
			call1.addParameter("Description", XMLType.SOAP_STRING, ParameterMode.IN);
			call1.addParameter("FileData", XMLType.SOAP_BASE64BINARY, ParameterMode.IN);
			call1.setReturnType(XMLType.SOAP_STRING);
			String strAppID = "?????????????????????.??????";// ??????ID
			String strOPID = diancxx.getId() + hetid;// ???????????????ID
			String description = "?????????????????????.??????";
			String fileID = (String) call1.invoke(new Object[] { strAppID, strOPID, fujmc, description, fileBytes });
			System.out.println("===========fileID=================" + fileID + "=======================");
			if (fileID != null) {
				response.getWriter().write("????????????????????????!");
			} else {
				response.getWriter().write("????????????????????????!");
			}
//--------------------------------------???????????????E://filemis????????????filid--------------------------------------------------------------
			try {
				int bytesum = 0;
				int byteread = 0;
				File oldfile = new File(filePath+fujmc);
				if (oldfile.exists()) { // ???????????????
					InputStream inStream = new FileInputStream(filePath+fujmc); // ???????????????
					String dongshtPath=PropertiesUtil.getValue("dongsht_path");
					FileOutputStream fs = new FileOutputStream(dongshtPath+fileID);
					byte[] buffer = new byte[1444];
					int length;
					while ((byteread = inStream.read(buffer)) != -1) {
						bytesum += byteread; // ????????? ????????????
						System.out.println(bytesum);
						fs.write(buffer, 0, byteread);
					}
					inStream.close();
				}
			} catch (Exception e) {
				System.out.println("??????????????????????????????");
				e.printStackTrace();

			}
// ---------------------------------------------------------------------------------------------------------------------
			// -------------------------------????????????????????????filemis-------------------------------------------------------
			// String fujmc = hetbean.getFujmc();
			// //String filePath =
			// PropertiesUtil.getValue("upload_file_folder")+fujmc;//+fujId+wenjmc.substring(wenjmc.lastIndexOf("."));//
			// String filePath="E://FilMis//"+fujmc;
			// byte[] fileBytes = File2byte(filePath);
			// org.apache.axis.client.Service service1 = new
			// org.apache.axis.client.Service();
			// Call call1 = (Call) service1.createCall();//???????????????
			// String url = PropertiesUtil.getValue("filemis_url");
			// call1.setTargetEndpointAddress(new java.net.URL(url));
			// call1.setOperationName("upLoadFile");
			// call1.addParameter("AppID",
			// XMLType.SOAP_STRING,ParameterMode.IN);
			// call1.addParameter("OperationID",
			// XMLType.SOAP_STRING,ParameterMode.IN);
			// call1.addParameter("FileName",
			// XMLType.SOAP_STRING,ParameterMode.IN);
			// call1.addParameter("Description",
			// XMLType.SOAP_STRING,ParameterMode.IN);
			// call1.addParameter("FileData",
			// XMLType.SOAP_BASE64BINARY,ParameterMode.IN);
			// call1.setReturnType(XMLType.SOAP_STRING);
			// String strAppID = "?????????????????????.??????";//??????ID
			// String strOPID = diancxx.getId()+hetid;//???????????????ID
			// String description = "?????????????????????.??????";
			// String fileID=(String) call1.invoke(new Object[] {strAppID,
			// strOPID, fujmc, description, fileBytes});
			// if(fileID!=null){
			// response.getWriter().write("????????????????????????!");
			// }else{
			// response.getWriter().write("????????????????????????!");
			// }

			// ---------------------------------------------------------------------------------------------------------
			// ????????????save and start workflow
			String html=null;
			String sanjurl = PropertiesUtil.getValue("sanjsp_url");
			sanjurl += "/service/StartupService?wsdl";
			Client client = new Client(new URL(sanjurl));
			// fileID=filePath;
			String[] parms = { "" + userid, "" + diancxxb_id, hetid, diancmingc, quanc, hetbh, gongfdwmc, qiandrq,renwms, jiag, fileID,html};
			Object[] r = client.invoke("saveDongsHetsp", parms);
			// ??????????????????1
			ret = (String) r[0];
			if (!ret.equals("0")) {
				jdbcTemplate.update("update rl_ht_hetb set liuc_id = " + ret + " where id = " + hetid);
				hetbeanService.updateHetbeanSanjzt(hetid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static byte[] File2byte(String filePath) {
		byte[] buffer = null;
		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);//
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}
	/**
	 * ??????????????????????????????
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getKaohzbbList")
	public void getKaohzbbList(@RequestParam String id, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		JSONArray jsonArray = hetbeanService.getKaohzbbList(map);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ??????????????????????????????commitDoc
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getRlhtmbList")
	public void getRlhtmbList(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Object> map = new HashMap<String, Object>();
		JSONArray jsonArray = hetbeanService.getRlhtmbList(map);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/uploadFile")
	public void uploadFile(@RequestParam(value = "upFile", required = false) MultipartFile file,
			@RequestParam String id, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String result = commonService.uploadFile(file);

		JSONArray jsonArray = new JSONArray();
		Map<String, Object> map = new HashMap<String, Object>();

		if (result != null) {
			jdbcTemplate.update("update RL_HT_HETB set FUJmc = '" + result + "' where ID = " + id);
			map.put("msg", "?????????????????????");
			jsonArray.add(map);
		} else {
			map.put("msg", "?????????????????????");
			jsonArray.add(map);
		}
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
