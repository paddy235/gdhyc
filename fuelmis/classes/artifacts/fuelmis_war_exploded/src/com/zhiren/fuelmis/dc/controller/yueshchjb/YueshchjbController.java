package com.zhiren.fuelmis.dc.controller.yueshchjb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.dao.yueshchjb.YueshchjbDao;
import com.zhiren.fuelmis.dc.entity.xitgl.Diancxx;
import com.zhiren.fuelmis.dc.entity.yueshchjb.CustomMaths;
import com.zhiren.fuelmis.dc.entity.yueshchjb.YueshchjbVo;
import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.Sequence;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 耗存合计组件Controller
 * @author mjy
 *
 */
@Controller
@RequestMapping("yuehc/yueshchjb")
public class YueshchjbController {

	@Autowired
	private YueshchjbDao yueshchjbDao;
	
	public static final String strParam ="strtime";
	private static final int shouml_shouml = 0;
	private static final int shouml_sunh = 1;
	private static final int shouml_tiaozl = 2;
	private static final int shouml_yingd = 3;
	private static final int shouml_kuid = 4;
	private static final int shouml_biaoz = 5;
	private static final int shouml_laimsl = 6;
	private static final int meihl_fady = 0;
	private static final int meihl_gongry = 1;
	private static final int meihl_qith = 2;
	private static final int qickc_kc = 0;
	private static final int huiz_qickc = 0;
	private static final int huiz_shouml = 1;
	private static final int huiz_fady = 2;
	private static final int huiz_gongry = 3;
	private static final int huiz_qith = 4;
	private static final int huiz_sunh = 5;
	private static final int huiz_diaocl = 6;
	private static final int huiz_panyk = 7;
	private static final int huiz_kuc  = 8;
	private static final int huiz_shuifctz=9;
	private static final int huiz_runxcs=10;
	private static final int huiz_jitcs=11;
//	界面用户提示
	private String msg="";
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	/**
	 * 新增耗存合计组件
	 * @param riq
	 * @param dianc
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/addYueshchjb")
	public void addYueshchjb(@RequestParam String riq,@RequestParam String dianc, HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		//获取电厂信息
		String diancxxb_id = dianc;
		String sjdiancxxb_id = "";       //查询数据用
//		大同：生成月报时，二期、三期、洗煤厂的数据统计到二期。
		sjdiancxxb_id = getXitxx_item("月报", "月报数据多厂合并", diancxxb_id, diancxxb_id);
		String strDate[] = riq.split("-");
		String CurrODate = strDate[0]+"-"+strDate[1];
		String Riq1=strDate[0]+"-"+strDate[1]+"-01";
		String month = "";
		if(strDate[1].equals("12")){
			month = "01";
			strDate[0] = (Integer.parseInt(strDate[0])+1)+"";
		}else{
			month = (Integer.parseInt(strDate[1])+1)+"";
		}
		String Riq2=strDate[0]+"-"+month+"-01";
		int intYuef=Integer.parseInt(strDate[1]);
		
		String date_c = getXitxx_item("月报", "月报取数日期差", diancxxb_id, "0");
		String strSql="";
		int flag = 0;
//		本月数组
		double beny[] = new double[12];
//		当月盘盈亏
		double panyk = 0.0;//盘盈亏
//		当月库存
		double kuc = 0.0;
//		取得当月收煤量  [0] 收煤量 [1] 损耗 [2] 调整
		double shouml[] = getShouml(sjdiancxxb_id,CurrODate);
//		if(shouml == null) {
//			return;
//		}
//		取得当月耗煤量 [0] 发电用  [1] 供热用 [2] 其它用 = 其它用 + 非生产用
		double meihl[] = getMeihl(sjdiancxxb_id,Riq1,Riq2,date_c);
		if(meihl == null) {
			return;
		}
		//取得当月入厂入炉水分 [0]入厂平均水分 [1]入炉平均水分
		double shuifc[]=getShuifc(sjdiancxxb_id, Riq1,Riq2);
		//当月水分差调整
		double shuifctzl=CustomMaths.round(shuifc[0]==100?0:(meihl[0]+meihl[1]+meihl[2])*(1-(100-shuifc[1])/(100-shuifc[0])),2);
//		取得期初库存 [0] 当月期初库存 
		String year=riq.substring(0, 4);
		String date=null;
		if(riq.substring(5, 7).equals("01")){
			date=year+"-01";
		}else{
			date=riq;
		}
		
		double qickc[] = getQickc(sjdiancxxb_id,date);
		if(qickc == null) {
			return;
		}
//		计算当月库存
		kuc+=shuifctzl;
		kuc += qickc[qickc_kc];
		/*
		 * 运损不计入库存 如想计入运损则自定义来煤量
		 * 修改时间：2008-12-03
		 * 修改人：王磊
		 * 原句 kuc += (shouml[shouml_shouml] + shouml[shouml_sunh] - shouml[shouml_tiaozl]);
		 * 新增收煤量算法shouml(jingz) - yuns + yingd - kuid 
		 */
		double shoumlv=0.00;
		if(shouml!=null){
			 shoumlv = getShoumlv(diancxxb_id,shouml);
		}else{
			shouml=new double[8];
			shouml[shouml_tiaozl]=0.0;
		}

		kuc += shoumlv - shouml[shouml_tiaozl]; 
		kuc = kuc -  meihl[meihl_fady] - meihl[meihl_gongry] - meihl[meihl_qith];
		kuc += panyk;
		/*
		 * 新增字段 计提储损、允许储损
		 */
		double runxcs=CustomMaths.round(getRunxcs(sjdiancxxb_id,Riq1,Riq2),2);
		double jitcs=0;
		
		//kuc=kuc-jitcs;    //把计提储损计入库存
//		本月数组赋值 [0] 期初库存 [1] 收煤量 [2] 发电用 [3] 供热用 [4] 其它耗 [5] 损耗 [6] 调出量 [7] 盘盈亏 [8] 库存 [9]水分差调整 [10]允许储损 [11]计提储损
		beny[huiz_qickc] = qickc[0]; 
		beny[huiz_shouml] = shoumlv; 
		beny[huiz_fady] = meihl[0];
		beny[huiz_gongry] = meihl[1]; 
		beny[huiz_qith] = meihl[2]; 
		//损耗改为手工填报 不从 shouml[shouml_sunh] 中取值
		beny[huiz_sunh] =0;
		beny[huiz_diaocl] = shouml[shouml_tiaozl]; 
		beny[huiz_panyk] = panyk; 
		beny[huiz_kuc] = kuc;
		beny[huiz_shuifctz] = shuifctzl;
		beny[huiz_runxcs]=runxcs;
		beny[huiz_jitcs]=jitcs;
//		取得上月累计数
		double leij[] = getLeij(diancxxb_id,date);
		if(leij == null) {
			return;
		}
//		新建本月的累计数
		double benylj[] = new double[12];
//		计算本月累计数
		for(int i = 0; i < leij.length ; i++) {
			benylj[i] = leij[i] + beny[i];//
		}
		benylj[huiz_qickc]=leij[huiz_qickc];
		benylj[huiz_kuc] = beny[huiz_kuc];
		
//		删除本月数
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("diancxxb_id", diancxxb_id);
		map.put("riq", CurrODate);
		yueshchjbDao.DelData(map);
		
//		生成当月数
		YueshchjbVo yuevo=new YueshchjbVo();
		yuevo.setId(Long.parseLong(Sequence.nextId().toString()));
		yuevo.setDiancxxb_id(Long.parseLong(diancxxb_id));
		yuevo.setRiq(CurrODate);
		yuevo.setFenx("本月");
		yuevo.setQickc(beny[huiz_qickc]);
		yuevo.setShouml(beny[huiz_shouml]);
		yuevo.setFady(beny[huiz_fady]);
		yuevo.setGongry(beny[huiz_gongry]);
		yuevo.setQith(beny[huiz_qith]);
		yuevo.setSunh(beny[huiz_sunh]);
		yuevo.setDiaocl(beny[huiz_diaocl]);
		yuevo.setPanyk(beny[huiz_panyk]);
		yuevo.setKuc(beny[huiz_kuc]);
		yuevo.setShuifctz(beny[huiz_shuifctz]);
		yuevo.setJitcs(beny[huiz_jitcs]);
		yuevo.setRunxcs(beny[huiz_runxcs]);
		yueshchjbDao.createData(yuevo);
//		生成累计数
		YueshchjbVo ljyuevo=new YueshchjbVo();
//		if(intYuef == 1) {
////			如果是一月累计==本月
//			ljyuevo.setId(Long.parseLong(Sequence.nextId().toString()));
//			ljyuevo.setDiancxxb_id(Long.parseLong(diancxxb_id));
//			ljyuevo.setRiq(CurrODate);
//			ljyuevo.setFenx("累计");
//			ljyuevo.setQickc(beny[huiz_qickc]);
//			ljyuevo.setShouml(beny[huiz_shouml]);
//			ljyuevo.setFady(beny[huiz_fady]);
//			ljyuevo.setGongry(beny[huiz_gongry]);
//			ljyuevo.setQith(beny[huiz_qith]);
//			ljyuevo.setSunh(beny[huiz_sunh]);
//			ljyuevo.setDiaocl(beny[huiz_diaocl]);
//			ljyuevo.setPanyk(beny[huiz_panyk]);
//			ljyuevo.setKuc(beny[huiz_kuc]);
//			ljyuevo.setShuifctz(beny[huiz_shuifctz]);
//			ljyuevo.setJitcs(beny[huiz_jitcs]);
//			ljyuevo.setRunxcs(beny[huiz_runxcs]);
//		}else{
			ljyuevo.setId(Long.parseLong(Sequence.nextId().toString()));
			ljyuevo.setDiancxxb_id(Long.parseLong(diancxxb_id));
			ljyuevo.setRiq(CurrODate);
			ljyuevo.setFenx("累计");
			ljyuevo.setQickc(benylj[huiz_qickc]);
			ljyuevo.setShouml(benylj[huiz_shouml]);
			ljyuevo.setFady(benylj[huiz_fady]);
			ljyuevo.setGongry(benylj[huiz_gongry]);
			ljyuevo.setQith(benylj[huiz_qith]);
			ljyuevo.setSunh(benylj[huiz_sunh]);
			ljyuevo.setDiaocl(benylj[huiz_diaocl]);
			ljyuevo.setPanyk(benylj[huiz_panyk]);
			ljyuevo.setKuc(benylj[huiz_kuc]);
			ljyuevo.setShuifctz(benylj[huiz_shuifctz]);
			ljyuevo.setJitcs(benylj[huiz_jitcs]);
			ljyuevo.setRunxcs(benylj[huiz_runxcs]);
//		}
		yueshchjbDao.createData(ljyuevo);
//		setMsg(CurrZnDate+"的数据成功生成！");
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		JSONObject json1 = JSONObject.fromObject(yuevo);
		JSONObject json2 = JSONObject.fromObject(ljyuevo);
		jsonArray.add(json1);
		jsonArray.add(json2);
		jsonObject.put("data", jsonArray);
		try {
			writer = response.getWriter();
			writer.write(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	
	
	/**
	 * 更新计价组件
	 * @param info
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/updateYueshchjb")
	public void updateYueshchjb(@RequestParam String info,HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		 JSONArray json = JSONArray.fromObject(info);
		 for(int i = 0 ; i < json.size(); i++){
			 YueshchjbVo hjvo = (YueshchjbVo) JSONObject.toBean(json.getJSONObject(i), YueshchjbVo.class);
			 if(hjvo.getFenx().equals("本月")){
				 yueshchjbDao.updateData(hjvo);
			 }else{
				 yueshchjbDao.updateDataL(hjvo);
			 }
			
		 }
		try {
			writer = response.getWriter();
			writer.write("更新成功！");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 获取全部
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getYueshchjbList")
	public void getYueshchjbList(@RequestParam String riq,@RequestParam String dianc, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		String strDate[] = riq.split("-");
		String CurrODate = strDate[0] + "-" + strDate[1] ;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("riq", CurrODate);
		map.put("diancxxb_id", dianc);
		List<Map<String,Object>> islist=yueshchjbDao.getisNotReady(map);
		if(islist==null||islist.size()==0){
			setMsg("请在使用本模块之前，首先完成月数量数据的计算！");
		}
		List<YueshchjbVo> list = yueshchjbDao.getData(map);
		JSONObject rejson = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(list);
		rejson.put("desc", msg);
		rejson.put("data", jsonArray);
		try {
			writer = response.getWriter();
			writer.write(rejson.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	@RequestMapping(value = "/delYueshchjb")
	public void delYueshchjb(@RequestParam String riq,@RequestParam String dianc,HttpServletRequest request , HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		String strDate[] =riq.split("-");
		String CurrZnDate =strDate[0]+"年"+strDate[1]+"月";
		String CurrODate = strDate[0]+"-"+strDate[1]+"-01";
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("diancxxb_id", dianc);
		map.put("riq", CurrODate);
		yueshchjbDao.DelData(map);
		setMsg(CurrZnDate+"的数据被成功删除！");
		try {
			writer = response.getWriter();
			writer.write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取编辑数据
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/editYueshchjb")
	public void editYueshchjb(@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {
			response.setContentType("text/html;charset=UTF-8");
//			Map<String,Object> map = new HashMap<String, Object>();
//			map.put("id", id);
//			JSONObject json = priceSchemeService.getPriceSchemeById(map);
//			JSONObject result = new JSONObject();
//			result.put("priceScheme", json);
//			PrintWriter writer  = null;
//			try {
//				writer = response.getWriter();
//				writer.write(result.toString());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
	}
	
	
	public double[] getShouml(String diancxxb_id, String CurrODate) {
		double shouml[] = null;//收煤量
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("diancxxb_id", diancxxb_id);
		map.put("riq", CurrODate);
		List<Map<String,Object>> list=yueshchjbDao.getShouml(map);
		if (list == null) {//判断是否连接失败
//			WriteLog.writeErrorLog(ErrorMessage.NullResult + "引发错误SQL:" + strSql);
//			setMsg(ErrorMessage.NullResult);
			return shouml;
		}
		if(list.size()>0){
			for(int i = 0;i<list.size();i++){
				HashMap<String, Object> dataMap = (HashMap<String, Object>) list.get(i);
				shouml = new double[7];
				shouml[shouml_shouml] = Double.valueOf(dataMap.get("SHOUML").toString());//净重
				shouml[shouml_sunh] = Double.valueOf(dataMap.get("YUNS").toString());//损耗
				shouml[shouml_tiaozl] = Double.valueOf(dataMap.get("RUCTZL").toString());
				shouml[shouml_yingd] = Double.valueOf(dataMap.get("YINGD").toString());
				shouml[shouml_kuid] =Double.valueOf( dataMap.get("KUID").toString());
				shouml[shouml_biaoz] =Double.valueOf(dataMap.get("BIAOZ").toString());
				shouml[shouml_laimsl] = Double.valueOf(dataMap.get("LAIMSL").toString());
			}
		}else{
			setMsg("收煤量获取失败，请确认数量月报已经生成！");
			return shouml;
		}
		return shouml;
	}
	
	/*
	 * 运损不计入库存 如想计入运损则自定义来煤量
	 * 修改时间：2008-12-03
	 * 修改人：王磊
	 * 原句 kuc += (shouml[shouml_shouml] + shouml[shouml_sunh] - shouml[shouml_tiaozl]);
	 * 新增收煤量算法shouml(jingz) - yuns + yingd - kuid 
	 */
	public double getShoumlv(String diancxxb_id,double shml[]) {
		double shouml[] = shml;//收煤量
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("diancxxb_id", diancxxb_id);
		List<Map<String,Object>> list=yueshchjbDao.getShoumlv(map);
		double shoumlv = 0.0;
		if(list!=null&&list.size()>0){
			if("票重+盈吨-亏吨-运损".equals(list.get(0).get("zhi"))){
				shoumlv = CustomMaths.round(shouml[shouml_biaoz] + shouml[shouml_yingd] - shouml[shouml_sunh] - shouml[shouml_kuid],2);
			}else{
				shoumlv = CustomMaths.round(shouml[shouml_shouml],2);
			}
		}else{
			shoumlv = CustomMaths.round(shouml[shouml_shouml],2);
		}
		return shoumlv;
	}
	
	public double[] getShuifc(String diancxxb_id, String Riq1,String Riq2){
		double shuifc[] = new double[2];
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("diancxxb_id", diancxxb_id);
		map.put("riq1", Riq1);
		map.put("riq2", Riq2);
		List<Map<String,Object>> list=yueshchjbDao.getShuifc(map);
		if(list==null){
			shuifc[0]=0.0;
			shuifc[1]=0.0;
			return shuifc;
		}
		if (list.size()>0) {
			if(list.get(0).get("RUCSF")!=null){
				shuifc[0]= Double.valueOf(list.get(0).get("RUCSF").toString());
			}
			if(list.get(0).get("RULSF")!=null){
				shuifc[1]=Double.valueOf(list.get(0).get("RULSF").toString());
			}
		}
		return shuifc;
	}
	
	public double[] getMeihl(String diancxxb_id, String Riq1,String Riq2, String date_c) {
		double meihl[] = null;
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("diancxxb_id", diancxxb_id);
		map.put("riq1", Riq1);
		map.put("riq2", Riq2);
		map.put("date_c", date_c);
		List<Map<String,Object>> list=yueshchjbDao.getMeihl(map);
		if(list.size()==0){
			setMsg("缺少煤耗数据！");
			meihl = new double[3];
			meihl[meihl_fady] = 0.0;
			meihl[meihl_gongry] = 0.0;
			meihl[meihl_qith] = 0.0;
			return meihl;
		}
		if(list.size()>0) {
			meihl = new double[3];
			meihl[meihl_fady] =  Double.valueOf(list.get(0).get("FADHY").toString());//发热用
			meihl[meihl_gongry] =  Double.valueOf(list.get(0).get("GONGRHY").toString());//供热用
			meihl[meihl_qith] =  Double.valueOf(list.get(0).get("QITY").toString());//其它用
		}else {
//			不可能发生的错误
			setMsg("未知错误！");
			return meihl;
		}
		return meihl;
	}
	
	public double[] getQickc(String diancxxb_id, String CurrODate) {
		double qickc[] = null;
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("diancxxb_id", diancxxb_id);
		map.put("riq", CurrODate);
		List<Map<String,Object>> list=yueshchjbDao.getQickc(map);
		if (list == null) {//判断是否连接失败
			return qickc;
		}
		qickc = new double[1];
		if (list.size()>0) {//但到上月的库存数据，本月/累计
			qickc[qickc_kc] =  Double.valueOf(list.get(0).get("KUC").toString());
		}else {
			qickc[qickc_kc] = 0.0;
		}
		return qickc;
	}
	
	public double[] getLeij(String diancxxb_id, String CurrODate) {
		double leij[] =null;
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("diancxxb_id", diancxxb_id);
		map.put("riq", CurrODate);
		map.put("lriq", DateUtil.getLastYearString(CurrODate.substring(0,4)+"-12"));
		List<Map<String,Object>> list=yueshchjbDao.getLeij(map);
		List<Map<String,Object>> l=yueshchjbDao.getQickcLeij(map);
		
		if (list== null) {//判断是否连接失败
			return leij;
		}
		leij = new double[12];
		if(list.size()>0) {
			leij[huiz_qickc] = Double.valueOf(l.get(0).get("KUC").toString());
			leij[huiz_shouml] =  Double.valueOf(list.get(0).get("SHOUML").toString());
			leij[huiz_fady] =  Double.valueOf(list.get(0).get("FADY").toString());
			leij[huiz_gongry] =  Double.valueOf(list.get(0).get("GONGRY").toString());
			leij[huiz_qith] =  Double.valueOf(list.get(0).get("QITH").toString());
			leij[huiz_sunh] =  Double.valueOf(list.get(0).get("SUNH").toString());
			leij[huiz_diaocl] =  Double.valueOf(list.get(0).get("DIAOCL").toString());
			leij[huiz_panyk] =  Double.valueOf(list.get(0).get("PANYK").toString());
			leij[huiz_kuc] = Double.valueOf(list.get(0).get("KUC").toString());
			leij[huiz_shuifctz] =  Double.valueOf(list.get(0).get("SHUIFCTZ").toString());
			leij[huiz_runxcs]= Double.valueOf(list.get(0).get("RUNXCS").toString());
			leij[huiz_jitcs]=Double.valueOf(list.get(0).get("JITCS").toString());
			
		}else {
			for(int i =0; i < leij.length ; i++) {
				leij[i] = 0.0;
			}
		}
		return leij;
	}
//	获得允许储损
	private double getRunxcs(String diancxxb_id,String Riq1,String Riq2){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("diancxxb_id", diancxxb_id);
		map.put("riq1", Riq1);
		map.put("riq2", Riq2);
		List<Map<String,Object>> list=yueshchjbDao.getRunxcs(map);
		double runxcs=0.0;
		if(list!=null&&list.size()>0){
			if(list.get(0).get("PINGJKC")!=null){
				runxcs= Double.valueOf(list.get(0).get("PINGJKC").toString());
			}
			
		}
		return runxcs;
	}
	
//	获得允许储损
	private String getXitxx_item(String leib,String mingc,String diancxxb_id,String defaultValue){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("leib", "月报");
		map.put("mingc", "月报数据多厂合并");
		map.put("diancxxb_id", diancxxb_id);
		List<Map<String,Object>> list=yueshchjbDao.getXitxx_item(map);
		String runxcs = defaultValue;
		if(list!=null&&list.size()>0){
			runxcs=(String) list.get(0).get("zhi");
		}
		return runxcs;
	}
	
}
