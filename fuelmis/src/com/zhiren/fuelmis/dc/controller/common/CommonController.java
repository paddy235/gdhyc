package com.zhiren.fuelmis.dc.controller.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.entity.common.Combobox;
import com.zhiren.fuelmis.dc.entity.common.Fujxx;
import com.zhiren.fuelmis.dc.entity.xitgl.Diancxx;
import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.common.ICommonService;
import com.zhiren.fuelmis.dc.service.xitgl.IDiancxxService;
import com.zhiren.fuelmis.dc.utils.Constant;
import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.FileOperateUtil;
import com.zhiren.fuelmis.dc.utils.PropertiesUtil;
import com.zhiren.fuelmis.dc.utils.Sequence;

/**
 * @author 陈宝露
 */
@Controller
@RequestMapping("/common")
public class CommonController {

	@Autowired
	private IDiancxxService diancxxService;

	@Autowired
	private ICommonService commonService;

	@RequestMapping(value = "/getCurrentUser")
	public void getCurrentUser(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		JSONArray jsonArray = new JSONArray();
		jsonArray.add((Renyxx) session.getAttribute("user"));

		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getComboDianc")
	public void getComboDianc(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Diancxx> list = diancxxService.getAll();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Combobox combobox = new Combobox(list.get(i).getMingc(), list.get(i)
						.getId());
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/getComboDianc_QUANC")
	public void getComboDianc_QUANC(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Diancxx> list = diancxxService.getAll();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Combobox combobox = new Combobox(list.get(i).getQuanc(), list.get(i)
						.getId());
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getComboShengf")
	public void getComboShengf(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String, Object>> list = commonService.getAllShengf();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Combobox combobox = new Combobox(list.get(i).get("QUANC"), list
						.get(i).get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getComboChez")
	public void getComboChez(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String, Object>> list = commonService.getAllChez();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Combobox combobox = new Combobox(list.get(i).get("QUANC"), list
						.get(i).get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getComboGangk")
	public void getComboGangk(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String, Object>> list = commonService.getAllGangk();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Combobox combobox = new Combobox(list.get(i).get("QUANC"), list
						.get(i).get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getComboDianclb")
	public void getComboDianclb(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String, Object>> list = commonService.getAllDianclb();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Combobox combobox = new Combobox(list.get(i).get("QUANC"), list
						.get(i).get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getComboLiclx")
	public void getComboLiclx(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String, Object>> list = commonService.getAllLiclx();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Combobox combobox = new Combobox(list.get(i).get("MINGC"), list
						.get(i).get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getComboMeikxx")
	public void getComboMeikxx(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String, Object>> list = commonService.getAllMeikxx();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			Combobox combobox = new Combobox("全部", -1);
			jsonArray.add(combobox);
			for (int i = 0; i < list.size(); i++) {
				combobox = new Combobox(list.get(i).get("MINGC"), list
						.get(i).get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/getComboMeikxx2")
	public void getComboMeikxx2(HttpServletRequest request,
							   HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String, Object>> list = commonService.getAllMeikxx();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			Combobox combobox = new Combobox();
			jsonArray.add(combobox);
			for (int i = 0; i < list.size(); i++) {
				combobox = new Combobox(list.get(i).get("MINGC"), list.get(i).get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getComboLuj")
	public void getComboLuj(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String, Object>> list = commonService.getAllLuj();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Combobox combobox = new Combobox(list.get(i).get("MINGC"), list
						.get(i).get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getComboMeikdq")
	public void getComboMeikdq(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String, Object>> list = commonService.getAllMeikdq();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Combobox combobox = new Combobox(list.get(i).get("MINGC"), list
						.get(i).get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/downloadFile")
	public void downloadFile(@RequestParam String fileId,HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
//			request.setCharacterEncoding("UTF-8");
			String fujmc=new String(fileId.getBytes("iso-8859-1"),"UTF-8");
//			String fujmc = fileId;//URLDecoder.decode(fileId, "utf-8");
			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("id", fujmc);
//			String wenjmc = commonService.getWenjmc(map);
			String path =PropertiesUtil.getValue("upload_file_folder")+fujmc;
			System.out.print("============文件路径==="+path+"=========================================");
			//Runtime.getRuntime().exec("C:\\Program Files\\Microsoft Office\\Office14\\WINWORD.EXE "+path); uploadFile
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename="+ new String(fujmc.getBytes("UTF-8"), "ISO8859-1"));
			OutputStream toClient = response.getOutputStream();
//			toClient =   BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取所有品种
	 * 
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/getComboPinz")
	public void getComboPinz(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String, Object>> list = commonService.getAllPinz();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			Combobox combobox = new Combobox("全部", -1);
			jsonArray.add(combobox);
			for (int i = 0; i < list.size(); i++) {
				combobox = new Combobox(list.get(i).get("MINGC"), list.get(i)
						.get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获取所有品种
	 * 
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/getComboPinz2")
	public void getComboPinz2(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String, Object>> list = commonService.getAllPinz();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			Combobox combobox;
			for (int i = 0; i < list.size(); i++) {
				combobox = new Combobox(list.get(i).get("MINGC"), list.get(i)
						.get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 获取所有计划口径
	 * 
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/getComboJihkj")
	public void getComboJihkj(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String, Object>> list = commonService.getAllJihkj();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			Combobox combobox = new Combobox("全部", -1);
			jsonArray.add(combobox);
			for (int i = 0; i < list.size(); i++) {

				combobox = new Combobox(list.get(i).get("MINGC"), list.get(i)
						.get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value = "/getComboJihkj2")
	public void getComboJihkj2(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String, Object>> list = commonService.getAllJihkj();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Combobox combobox = new Combobox(list.get(i).get("MINGC"), list.get(i)
						.get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@RequestMapping(value = "/getGongysLikeJianc")
	public void getGongysLikeJianc(@RequestParam("code") String code,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String, Object>> list = commonService.getGongysLikeJianc(code);
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {

				Combobox combobox = new Combobox(list.get(i).get("MINGC"), list.get(i)
						.get("ID"));

				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getComboGongys")
	public void getComboGongys(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String, Object>> list = commonService.getAllGongys();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			Combobox combobox = new Combobox("全部", -1);
			jsonArray.add(combobox);
			for (int i = 0; i < list.size(); i++) {
				combobox = new Combobox(list.get(i).get("MINGC"), list.get(i).get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 获取所有品种
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/getComboYunsfs")
	public void getComboYunsfs(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String,Object>> list = commonService.getAllYunsfs();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			Combobox combobox = new Combobox("全部", -1);
			jsonArray.add(combobox);
			for (int i = 0; i < list.size(); i++) {
				combobox = new Combobox(list.get(i).get("MINGC"), list.get(i)
						.get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@RequestMapping(value = "/getComboGongys2")
	public void getComboGongys2(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String, Object>> list = commonService.getAllGongys();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Combobox combobox = new Combobox(list.get(i).get("MINGC"), list.get(i).get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/getComboGongys_quanc")
	public void getComboGongys_quanc(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String, Object>> list = commonService.getAllGongys();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Combobox combobox = new Combobox(list.get(i).get("QUANC"), list.get(i).get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/checkNameExist")
	public void checkNameExist(@RequestParam(defaultValue="dual",value="param1") String tableName,
			@RequestParam(defaultValue="1",value="param2") String columnName,
			@RequestParam(defaultValue="1",value="param3") Object value,
			HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", tableName);
		map.put("columnName", columnName);
		map.put("value", value);
		JSONArray jsonArray = commonService.checkNameExist(map);
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getNextNum")
	public void getNextNum(@RequestParam(defaultValue="dual",value="param1") String tableName,
			@RequestParam(defaultValue="1",value="param2") String columnName,
			@RequestParam(defaultValue="1",value="param3") Object value,
			HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", tableName);
		map.put("columnName", columnName);
		map.put("value", value);
		JSONArray jsonArray = commonService.getNextNum(map);
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getComboRulbz")
	public void getComboRulbz(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String,Object>> list = commonService.getAllRulbz();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Combobox combobox = new Combobox(list.get(i).get("MINGC"), list.get(i)
						.get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 人员信息
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/getComboRenyxx")
	public void getComboRenyxx(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String,Object>> list = commonService.getAllRenyxx();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			Combobox combobox;
			for (int i = 0; i < list.size(); i++) {
				combobox = new Combobox(list.get(i).get("QUANC"), list.get(i)
						.get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 评分方案信息
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/getComboPingffa")
	public void getComboPingffa(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String,Object>> list = commonService.getAllPingffa();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			Combobox combobox;
			for (int i = 0; i < list.size(); i++) {
				combobox = new Combobox(list.get(i).get("MINGC"), list.get(i)
						.get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 合同订单信息
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/getComboCaigddb")
	public void getComboCaigddb(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String,Object>> list = commonService.getAllCaigddb();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			Combobox combobox;
			for (int i = 0; i < list.size(); i++) {
				combobox = new Combobox(list.get(i).get("QUANC"), list.get(i)
						.get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	
	@RequestMapping(value = "/getComboCaigddb2")
	public void getComboCaigddb2(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String,Object>> list = commonService.getAllCaigddb();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			Combobox combobox = new Combobox("全部", "-1");
			jsonArray.add(combobox);
			for (int i = 0; i < list.size(); i++) {
				combobox = new Combobox(list.get(i).get("QUANC"), list.get(i)
						.get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	

	/**
	 * 合同订单信息
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value="/getComboHetmb")
	public void getComboHetmb(@RequestParam String info, HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("date", DateUtil.getCurrentTime());
		map.put("yewly", info);
		List<Map<String,Object>> list = commonService.getComboHetmb(map);		
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			Combobox combobox;
			for (int i = 0; i < list.size(); i++) {
				combobox = new Combobox(list.get(i).get("DAIM"), list.get(i)
						.get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	
	/**
	 * 上传文件
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/upload")
	public void upload(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try{
			String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");			
			Map<String, String> result = FileOperateUtil.upload1(request,path);
			//Map<String, String> result = FileOperateUtil.upload(request,path);

			Fujxx f = new Fujxx();
			f.setId(Long.parseLong(Sequence.nextId()));
			f.setMingc(result.get("fileName"));
			f.setYewly(Constant.HETMB);
			String fid = result.get("fid");
			f.setFid(Long.parseLong(fid));
			commonService.saveFujxx(f); 
			JSONObject json = JSONObject.fromObject(f);	
		    response.setContentType("text/html;charset=UTF-8");
		    PrintWriter out = response.getWriter();  
		    out.print(json);   
		    out.flush();   
		    out.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}

	}
	
	@RequestMapping(value = "/getComboKuczz")
	public void getComboKuczz(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("suosdw", ((Diancxx)request.getSession().getAttribute("diancxx")).getId());
		
		List<Map<String,Object>> list = commonService.getAllKuczz(map);
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Combobox combobox = new Combobox(list.get(i).get("KUCZZMC"), list.get(i)
						.get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getComboKucwz")
	public void getComboKucwz(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		
		List<Map<String,Object>> list = commonService.getAllKucwz();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Combobox combobox = new Combobox(list.get(i).get("KUCWZMS"), list.get(i)
						.get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getComboKucwl")
	public void getComboKucwl(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		
		List<Map<String,Object>> list = commonService.getAllKucwl();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Combobox combobox = new Combobox(list.get(i).get("WUZMC"), list.get(i)
						.get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getAllChurkywlx")
	public void getAllChurkywlx(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		
		List<Map<String,Object>> list = commonService.getAllChurkywlx();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Combobox combobox = new Combobox(list.get(i).get("LEIXMC"), list.get(i)
						.get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取硫分计价公式
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/getJijfs")
	public void getAllJijfs(@RequestParam String type,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("type", type);
		List<Map<String,Object>> list = commonService.getAllJijfs(map);
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Combobox combobox = new Combobox(list.get(i).get("GONGS"), list.get(i)
						.get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
	@RequestMapping(value = "/getAllChurkywlx_fdck")
	public void getAllChurkywlx_fdck(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		
		List<Map<String,Object>> list = commonService.getAllChurkywlx_fdck();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Combobox combobox = new Combobox(list.get(i).get("LEIXMC"), list.get(i)
						.get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value = "/getAllKaohzb")
	public void getAllKaohzb(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		
		List<Map<String,Object>> list = commonService.getAllKaohzb();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Combobox combobox = new Combobox(list.get(i).get("ZHIBDM"), list.get(i)
						.get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getAllPriceComponet")
	public void getAllPriceComponet(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String,Object>> list = commonService.getAllPriceComponet();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Combobox combobox = new Combobox(list.get(i).get("NAME"), list.get(i)
						.get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@RequestMapping(value = "/getAllPriceComponet1")
	public void getAllPriceComponet1(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String,Object>> list = commonService.getAllPriceComponet();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				JSONObject json = new JSONObject();
				json.put("id", list.get(i).get("ID"));
				json.put("name",list.get(i).get("NAME"));
				json.put("url",list.get(i).get("URL"));
				jsonArray.add(json);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value = "/getAllPriceItem")
	public void getAllPriceItem(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String,Object>> list = commonService.getAllPriceItem();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Combobox combobox = new Combobox(list.get(i).get("NAME"), list.get(i)
						.get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getAllPriceItem1")
	public void getAllPriceItem1(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String,Object>> list = commonService.getAllPriceItem();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				JSONObject json = new JSONObject();
				json.put("id", list.get(i).get("ID"));
				json.put("name", list.get(i).get("NAME"));
				json.put("code", list.get(i).get("CODE"));
				jsonArray.add(json);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	@RequestMapping(value = "/getComboHetbh")
	public void getComboHetbh(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String,Object>> list = commonService.getAllHetbh();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			Combobox combobox = new Combobox("全部", "-1");
			jsonArray.add(combobox);
			for (int i = 0; i < list.size(); i++) {
				combobox = new Combobox(list.get(i).get("HETBH"), list.get(i)
						.get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@RequestMapping(value = "/getComboRuljz")
	public void getComboRuljz(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String,Object>> list = commonService.getAllRuljz();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Combobox combobox = new Combobox(list.get(i).get("MINGC"), list.get(i)
						.get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/getComboFeiyxm")
	public void getComboFeiyxm(HttpServletRequest request,
							   HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String, Object>> list = commonService.getAllFeiyxm();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Combobox combobox = new Combobox(list.get(i).get("MINGC"), list
						.get(i).get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getComboChezXxb")
	public void getComboChezXxb(HttpServletRequest request,
								HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String, Object>> list = commonService.getAllChezXxb();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Combobox combobox = new Combobox(list.get(i).get("QUANC"), list
						.get(i).get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}