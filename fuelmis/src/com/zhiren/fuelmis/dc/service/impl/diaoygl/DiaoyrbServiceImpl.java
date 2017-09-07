package com.zhiren.fuelmis.dc.service.impl.diaoygl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.diaoygl.DiaoyrbDao;
import com.zhiren.fuelmis.dc.service.diaoygl.DiaoyrbService;

@Service("diaoyrbService")
public class DiaoyrbServiceImpl implements DiaoyrbService {

	@Autowired
	private DiaoyrbDao diaoyrbDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public String CreateRBB(String diancid, String CurOraDate) {

		int flag=0;//更新成功标识
//		收耗存日报中的字段变量
		double zuorkc = 0.0, fadl = 0.0,GONGRL=0.0, jingz = 0.0, biaoz = 0.0, yuns = 0.0, yingd = 0.0;
		double kuid = 0.0, fady = 0.0, gongry = 0.0, qity = 0.0, cuns = 0.0, tiaozl = 0.0;
		double shuifctz = 0.0, panyk = 0.0, kuc = 0.0, feiscy = 0.0,changwml=0.0,bukdml=0.0,kedkc=0.0;
		String updateId;
//		StringBuffer sb = new StringBuffer();
		
		Map<String,Object> fadlMap = new HashMap<String,Object>();
		fadlMap.put("date", CurOraDate);
		fadlMap.put("diancid", diancid);
		List<Map<String, Object>> list = diaoyrbDao.getFadlInfo(fadlMap);
		HashMap<String, Object> HashMap = (HashMap<String, Object>) list.get(0);
		fadl = (double) HashMap.get("FADL");
		GONGRL = (double) HashMap.get("GONGRL");		
		
		
		String whereCondition = null;
		String xitxxInfo = getXitxx_item("收耗存日报", "是否判断发货皮重信息", "0", "否");
		if(("是").equals(xitxxInfo)){
			//whereCondition = " and piz>0 ";
			whereCondition = "1";
		}
		
		String whereCondition1 = null;
		String xitxxInfo1 = getXitxx_item("收耗存日报", "来煤是否区分厂别", "0", "是");
		if(("是").equals(xitxxInfo1)){
			//and diancxxb_id=电厂id
			whereCondition1 = "1";
		}
		
		Map<String,Object> zhonglMap = new HashMap<String, Object>();
		zhonglMap.put("date", CurOraDate);
		zhonglMap.put("whereCondition", whereCondition);
		zhonglMap.put("whereCondition1", whereCondition1);
		List<Map<String, Object>> zhonglList = getZhonglInfo(zhonglMap);
		HashMap<String, Object> zhonglHMap = (HashMap<String, Object>) zhonglList.get(0);
		
		jingz = (double) zhonglHMap.get("JINGZ");
		biaoz = (double) zhonglHMap.get("BIAOZ");
		yuns = (double) zhonglHMap.get("YUNS");
		yingd = (double) zhonglHMap.get("YINGD");
		kuid = (double) zhonglHMap.get("KUID");

		//耗用信息
		Map<String,Object> haoyMap = new HashMap<String, Object>();
		haoyMap.put("date", CurOraDate);
		haoyMap.put("diancid", diancid);
//		List<Map<String, Object>> haoyList = getHaoyInfo(haoyMap);
		HashMap<String, Object> haoyDataMap = (HashMap<String, Object>) zhonglList.get(0);
		fady = (double) haoyDataMap.get("fady");
		gongry = (double) haoyDataMap.get("gongry");
		qity = (double) haoyDataMap.get("qity");
		feiscy = (double) haoyDataMap.get("feiscy");		
		
		//获取昨日库存
		//修正库存计算方法：期初库存+总收入-总耗用
		//取上月月末日报库存
		
		//参数类型与内容与耗用一致 同样包含date、diancid
		zuorkc = Double.parseDouble(getZuorkcInfo(haoyMap));

		
		//参数与耗用一直
		String xitxxInfo2 = getXitxx_item("收耗存日报", "日报库存是否从月报中取得", "0", "否");
		if(("是").equals(xitxxInfo2)){
			//尝试从耗存合计中取得上月月报库存
			zuorkc = Double.parseDouble(getShangykcInfo(haoyMap));
		}
		
		
		
		//取得昨日库存+上日库存差
		zuorkc = zuorkc + Double.parseDouble(getShangrkccInfo(haoyMap));
			
		bukdml = Double.parseDouble(getBukdmlInfo(haoyMap));
		
		
//		// 计算库存
		kuc = zuorkc + jingz - fady - gongry - qity - cuns - feiscy + tiaozl + shuifctz + panyk;
//		// 计算可调库存
		kedkc=kuc ;
			
		// 查看当日是否已经生成来耗存日报
			
		List<Map<String, Object>> shifyscList = getShifyscInfo(haoyMap);
		if(shifyscList.size() > 0){
			// 如果日收耗存中已有数据则进行对比 有差异则更新
			boolean changed = false;		
			HashMap<String, Object> dataMap = (HashMap<String, Object>) shifyscList.get(0);
			//如果发电量有数据则不更新发电量信息
			double comFadl = Double.parseDouble(dataMap.get("FADL").toString());
			if(fadl != comFadl){
				changed = true;
				if (comFadl>0) {
					fadl= comFadl;
				}
			}
			
			double comGONGRL = Double.parseDouble(dataMap.get("GONGRL").toString());
			if(GONGRL != comGONGRL){
			changed = true;
				if (comGONGRL>0) {
				GONGRL = comGONGRL;
				}
			}
//			新增日估价来煤自动覆盖数量信息判断，默认系统自动覆盖数量信息。
			boolean Laimqz = getXitxx_item("收耗存日报", "收耗存日报来煤自动覆盖", "0", "是").equals("是");
			double comJingz = Double.parseDouble(dataMap.get("JINGZ").toString());
			if (jingz != comJingz) {
				changed = true;
				if(!Laimqz){
					jingz = comJingz;
				}
			}
			
			double comBiaoz = Double.parseDouble(dataMap.get("BIAOZ").toString());
			if (biaoz != comBiaoz) {
			changed = true;
				if(!Laimqz){
					biaoz = comBiaoz;
				}
			}
			
			double comYuns = Double.parseDouble(dataMap.get("YUNS").toString());
			if (yuns != comYuns) {
				changed = true;
				if(!Laimqz){
					yuns = comYuns;
				}
			}
		
			double comYingd = Double.parseDouble(dataMap.get("YINGD").toString());
			if (yingd != comYingd) {
				changed = true;
				if(!Laimqz){
					yingd = comYingd;
				}
			}
			
			double comKuid = Double.parseDouble(dataMap.get("KUID").toString());
			if (kuid != comKuid) {
				changed = true;
				if(!Laimqz){
					kuid = comKuid;
				}
			}
			
			//如果发电耗用有相关信息，则不更新
			double comFady = Double.parseDouble(dataMap.get("FADY").toString());
			if(fady != comFady){
				changed = true;
				if (comFady>0) {
					fady = comFady;
				}
			}
	
			//如果供热耗用有信息，则不更新
			double comGongry = Double.parseDouble(dataMap.get("GONGRY").toString());
			if(gongry != comGongry){
				changed = true;
				if (comGongry>0) {
					gongry = comGongry;
				}
			}
	
			//如果其他耗用有信息，则不更新
			double comQity = Double.parseDouble(dataMap.get("QITY").toString());
			if(qity != comQity){
				changed = true;	
				if (comQity > 0) {
					qity = comQity;
				}
			}
			
			//如果非生产用有信息，则不更新
			double comFeiscy = Double.parseDouble(dataMap.get("FEISCY").toString());
			if(feiscy != comFeiscy){
				changed = true;
				if (comFeiscy > 0) {
					feiscy = comFeiscy;
				}
			}

			//如果发电，供热，其他，非生产中任意一个有数据，那么将不覆盖更新内容
			if(getXitxx_item("收耗存日报", "收耗存日报耗用是否单独判断", "0", "否").equals("否")){
				double comFady1 = Double.parseDouble(dataMap.get("FADY").toString());
				double comGongry1 = Double.parseDouble(dataMap.get("GONGRY").toString());
				double comQity1 = Double.parseDouble(dataMap.get("QITY").toString());
				double comFeiscy1 = Double.parseDouble(dataMap.get("FEISCY").toString());
				
				if(comFady1+comGongry1+comQity1+comFeiscy1>0){
					fady = comFady1;
					gongry=comGongry1;
					qity = comQity1;
					feiscy = comFeiscy1;
				}
			}

			//如果存损有信息，则不更新
			double comCuns = Double.parseDouble(dataMap.get("CUNS").toString());
			if(cuns!=comCuns){
				changed = true;
				if (comCuns>0) {
					cuns = comCuns;
				}
			}
			
			//如果调整量有信息，则不更新
			double comTiaozl = Double.parseDouble(dataMap.get("TIAOZL").toString());
			if(tiaozl != comTiaozl){
				changed = true;
				tiaozl = comTiaozl;
			}
	
			//如果水分差调整有信息，则不更新
			double comShuifctz = Double.parseDouble(dataMap.get("SHUIFCTZ").toString());
			if(shuifctz != comShuifctz){
				changed = true;
				shuifctz = comShuifctz;
			}
	
			//如果盘盈亏有信息，则不更新
			double comPanyk = Double.parseDouble(dataMap.get("PANYK").toString());
			if(panyk != comPanyk){
				changed = true;
				panyk = comPanyk;
			}
			
			//如果不可调煤量有信息，则不更新
			double comBukdml = Double.parseDouble(dataMap.get("BUKDML").toString());			
			if(bukdml != comBukdml){
				changed = true;
				bukdml = comBukdml;
			}
			
			//如果厂外煤量有信息，则不更新
			double comChangwml = Double.parseDouble(dataMap.get("CHANGWML").toString());		
			if(changwml != comChangwml){
				changed = true;
				changwml = comChangwml;
			}
	
			
			double comKedkc = Double.parseDouble(dataMap.get("KEDKC").toString());	
			if (comKedkc>0){
				changed = true;
				kedkc = comKedkc;
			}
			
			updateId = dataMap.get("ID").toString();
			
		//重新计算库存
		kuc = zuorkc + jingz + changwml - fady - gongry - qity - cuns - feiscy + tiaozl + shuifctz + panyk;
		//重新计算可调库存
		kedkc=kuc-bukdml;
		//如果有变化则更新收耗存日报表
			
		//如果有变化则更新收耗存日报表
		if (changed){
			Map<String,Object> updateMap = new HashMap<String, Object>();
			updateMap.put("fadl", fadl);
			updateMap.put("GONGRL", GONGRL);
			updateMap.put("biaoz",biaoz);
			updateMap.put("jingz",jingz);
			updateMap.put("yuns",yuns);
			updateMap.put("yingd",yingd);
			updateMap.put("kuid",kuid);
			updateMap.put("fady",fady);
			updateMap.put("gongry",gongry);
			updateMap.put("qity",qity);
			updateMap.put("cuns",cuns);
			updateMap.put("tiaozl",tiaozl);
			updateMap.put("shuifctz",shuifctz);
			updateMap.put("panyk",panyk);
			updateMap.put("dangrgm",jingz);
			
			
			updateMap.put("haoyqkdr",(fady + gongry + qity + cuns));
			updateMap.put("feiscy",feiscy);
			updateMap.put("bukdml",bukdml);
			updateMap.put("changwml",changwml);
			updateMap.put("kedkc",kedkc);
			updateMap.put("kuc",kuc);
			updateMap.put("updateId", updateId);
			
			flag = updateShouhcrbb(updateMap);
			
			if(flag < 0){
				return "收耗存信息更新失败";
			}
			double kuccha = sub(kuc,Double.parseDouble(dataMap.get("KUC").toString()));
			//判断是否同步更新库存 默认同步更新设置为否时不同步更新
			if(getXitxx_item("收耗存日报", "收耗存日报实时更新库存", "0", "否").equals("是")){
				updateMap = new HashMap<String, Object>();
				double updatekuc = kuc + kuccha;
				updateMap.put("kuc", updatekuc);
				updateMap.put("date", CurOraDate);
				updateMap.put("diancid", diancid);
				flag = updateShouhcrbb1(updateMap);
				if(flag < 0){
					return "收耗存信息更新失败";
				}
				
				updateMap = new HashMap<String, Object>();	
				updateMap.put("date", CurOraDate);
				updateMap.put("diancid", diancid);
				updateMap.put("kedkc", kuc - bukdml);
				flag = updateShouhcrbb2(updateMap);
				if(flag < 0){
					return "收耗存信息更新失败";
				}	
			}
		}
		}else{
			// 如果日收耗存中没有数据则插入
			
			Map<String,Object> addMap = new HashMap<String, Object>();
			addMap.put("fadl", fadl);
			addMap.put("GONGRL", GONGRL);
			addMap.put("biaoz",biaoz);
			addMap.put("jingz",jingz);
			addMap.put("yuns",yuns);
			addMap.put("yingd",yingd);
			addMap.put("kuid",kuid);
			addMap.put("fady",fady);
			addMap.put("gongry",gongry);
			addMap.put("qity",qity);
			addMap.put("cuns",cuns);
			addMap.put("tiaozl",tiaozl);
			addMap.put("shuifctz",shuifctz);
			addMap.put("panyk",panyk);
			addMap.put("dangrgm",jingz);
			addMap.put("haoyqkdr",fady + gongry + qity + cuns);
			addMap.put("feiscy",feiscy);
			addMap.put("bukdml",bukdml);
			addMap.put("changwml",changwml);
			addMap.put("kedkc",kedkc);
			addMap.put("kuc",kuc);
			flag = addshouhcrbb(addMap);
			if(flag==-1){
				return "收耗存信息插入失败";
			}
			
//			判断是否同步更新库存 默认同步更新设置为否时不同步更新
			if(getXitxx_item("收耗存日报", "收耗存日报实时更新库存", "0", "否").equals("是")){
//			更新当前日期以后的所有库存和可调库存
				@SuppressWarnings("rawtypes")
				Map updateMap = new HashMap<String, Object>();
				updateMap.put("kuc", kuc+(jingz - fady - gongry - qity - cuns - feiscy + tiaozl + shuifctz + panyk));
				updateMap.put("date", CurOraDate);
				updateMap.put("diancid", diancid);
				flag = updateShouhcrbb1(updateMap);				
				if(flag < 0){
					return "收耗存信息更新失败";
				}
								
				updateMap = new HashMap<String, Object>();	
				updateMap.put("date", CurOraDate);
				updateMap.put("diancid", diancid);
				updateMap.put("kedkc", kuc - bukdml);
				flag = updateShouhcrbb2(updateMap);
				if(flag < 0){
					return "收耗存信息更新失败";
				}
			}			
			
		}
		return "";
	}	
	
	//从系统信息表中取值
	public String getXitxx_item(String leib,String mingc,String diancid,String defaultValue){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("leib", leib);
		paramMap.put("mingc", mingc);
		paramMap.put("diancid", diancid);
		String value = defaultValue;
		try{
			value = diaoyrbDao.getXitxxInfo(paramMap);
		}catch(Exception e){
			e.printStackTrace();
		}
		return value;
	}
	
	/**
	 * 获取重量信息
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getZhonglInfo(Map<String, Object> map){
		return diaoyrbDao.getZhonglInfo(map);
	} 
	
	/**
	 * 获取耗用信息
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getHaoyInfo(Map<String, Object> map){
		return diaoyrbDao.getHaoyInfo(map);
	} 
	
	/**
	 * 获取昨日库存信息
	 * @param map
	 * @return
	 */
	public String getZuorkcInfo(Map<String, Object> map){
		return diaoyrbDao.getZuorkcInfo(map);
	} 
	
	/**
	 * 获取上月库存信息
	 * @param map
	 * @return
	 */
	public String getShangykcInfo(Map<String, Object> map){
		return diaoyrbDao.getShangykcInfo(map);
	}

	/**
	 * 获取上月库存差信息
	 * @param map
	 * @return
	 */
	public String getShangrkccInfo(Map<String, Object> map){
		return diaoyrbDao.getShangrkccInfo(map);
	}

	/**
	 * 获取不可调煤量
	 * @param map
	 * @return
	 */
	public String getBukdmlInfo(Map<String, Object> map){
		return diaoyrbDao.getBukdmlInfo(map);
	}
	
	/**
	 * 获取当日是否已经生成来耗存日报
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getShifyscInfo(Map<String, Object> map){
		return diaoyrbDao.getShifyscInfo(map);
	}
	
	/**
	 * 更新收耗存日报
	 * @param map
	 * @return
	 */
	public int updateShouhcrbb(Map<String, Object> map){
		return diaoyrbDao.updateShouhcrbb(map);
	} 
	
	public int updateShouhcrbb1(Map<String, Object> map){
		return diaoyrbDao.updateShouhcrbb1(map);
	} 
	
	public int updateShouhcrbb2(Map<String, Object> map){
		return diaoyrbDao.updateShouhcrbb2(map);
	} 	
	
	public int addshouhcrbb(Map<String, Object> map){
		return diaoyrbDao.addShouhcrbb(map);
	} 	
	
	
	
	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @return 两个参数的差
	 */
	public static double sub(double v1, double v2) {		
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}
	
	
	
	
	public String CreateFCB(String diancid, String CurOraDate)  {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("date", CurOraDate);
		paramMap.put("diancid", diancid);
		
		List<Map<String, Object>> OLDlist = getOLDShouhcfk(paramMap);
		
		
		String whereCondition = null,whereCondition1 = null;
		if(getXitxx_item("收耗存日报", "是否判断发货皮重信息", "0", "否").equals("是")){
			whereCondition = "1";
			//whereCondition+=" and f.piz>0 \n";
		}
		if(getXitxx_item("收耗存日报", "来煤是否区分厂别", "0", "是").equals("是")){
			//whereCondition+=" and diancxxb_id="+diancxxb_id;
			whereCondition1 = "1";
		}
		paramMap.put("whereCondition", whereCondition);
		paramMap.put("whereCondition1", whereCondition1);
		
		
		List<Map<String,Object>> list = getShouhcfk(paramMap);
//		ResultSetList OLDlist=getOLDShouhcfk(con, dcid,riq);
		StringBuffer insertSql=new StringBuffer();
		boolean isTrue=false;
//		新增日估价来煤自动覆盖数量信息判断，默认系统自动覆盖数量信息。
		boolean Laimqz=getXitxx_item("收耗存日报", "收耗存日报来煤自动覆盖", "0", "是").equals("是");
		boolean Rezqz=getXitxx_item("收耗存日报", "收耗存日报热值自动覆盖", "0", "是").equals("是");
		
		insertSql.append("begin \n");
		//判断是否存在原始记录
		if(OLDlist.size()>0){
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, Object> listMap = (HashMap<String, Object>) list.get(i);
				for (int j = 0; j < OLDlist.size(); j++) {
					HashMap<String, Object> OLDMap = (HashMap<String, Object>) OLDlist.get(j);
					double laimsl= Double.parseDouble(listMap.get("LAIMSL").toString());
					double rez= Double.parseDouble(listMap.get("REZ").toString());
					//判断是否对来煤信息进行覆盖
					if(!Laimqz){
						laimsl = (double) OLDMap.get("LAIMSL");
					}
					//判断是否对热值信息进行覆盖
					if(!Rezqz){
						rez = (double) OLDMap.get("REZ");
					}
					//如果煤价为0，则使用系统取值进行覆盖操作，否则数据将保持不变
					double meij= (double) OLDMap.get("MEIJ");
					//如果运价为0，则使用系统取值进行覆盖操作，否则数据将保持不变
					double yunj= (double) OLDMap.get("YUNJ");
					
					double meijs=(double) OLDMap.get("MEIJS");
					double yunjs=(double) OLDMap.get("YUNJS");
					double huocyj=(double) OLDMap.get("HUOCYJ");
					double huocyjs=(double) OLDMap.get("HUOCYJS");
					double qicyj=(double) OLDMap.get("QICYJ");
					double qicyjs=(double) OLDMap.get("QICYJS");
					
					//如果热值为0那么煤价和运价及其对应的税金均为0
					if(rez==0){
						yunj=0;
						meij=0;
						meijs=0;
						yunjs=0;
						huocyj=0;
						huocyjs=0;
						qicyj=0;
						qicyjs=0;
					}
					if(listMap.get("MEIKXXB_ID").equals(OLDMap.get("MEIKXXB_ID")) 
							&& listMap.get("PINZB_ID").equals(OLDMap.get("PINZB_ID")) 
							&& listMap.get("GONGYSB_ID").equals(OLDMap.get("GONGYSB_ID")) 
							&& listMap.get("JIHKJB_ID").equals(OLDMap.get("JIHKJB_ID"))
							&& listMap.get("YUNSFSB_ID").equals(OLDMap.get("YUNSFSB_ID"))){
						
							Map<String, Object> shouhcfkb = new HashMap<String, Object>();
							shouhcfkb.put("diancxxb_id", diancid);
							shouhcfkb.put("riq", CurOraDate);
							shouhcfkb.put("laimsl", laimsl);
							shouhcfkb.put("rez", rez);
							shouhcfkb.put("meij", meij);
							shouhcfkb.put("yunj", yunj);
							shouhcfkb.put("meijs", meijs);
							shouhcfkb.put("yunjs", yunjs);
							shouhcfkb.put("huocyj", huocyj);
							shouhcfkb.put("huocyjs", huocyjs);
							shouhcfkb.put("qicyj", qicyj);							
							shouhcfkb.put("qicyjs", qicyjs);
							shouhcfkb.put("meikxxb_id", listMap.get("MEIKXXB_ID"));
							shouhcfkb.put("pinzb_id", listMap.get("PINZB_ID"));
							shouhcfkb.put("gongysb_id", listMap.get("GONGYSB_ID"));
							shouhcfkb.put("JIHKJB_ID", listMap.get("JIHKJB_ID"));
							shouhcfkb.put("yunsfsb_id", listMap.get("YUNSFSB_ID"));
							
//							insertSql.append(
//									"insert into shouhcfkb\n" +
//									"  (id, diancxxb_id,riq,laimsl, rez, meij, yunj,meijs,yunjs,huocyj,huocyjs,qicyj,qicyjs,meikxxb_id,pinzb_id,gongysb_id,JIHKJB_ID,yunsfsb_id)\n" + 
//									"values\n" + 
//									"  (getnewid("+dcid+"),"+dcid+","+CurOraDate+","+
//									laimsl+","+rez+","+meij+","+yunj+","+meijs+","+yunjs+","+huocyj+","+huocyjs+","
//									+qicyj+","+qicyjs+","+list.getString("meikxxb_id")+","+
//									list.getString("pinzb_id")+","+list.getString("gongysb_id")+","+list.getString("JIHKJB_ID")+","+list.getString("yunsfsb_id")+");\n");
							int result = addShouhcfkb(shouhcfkb);
							if(result<0){
								return"保存失败";
							}
							isTrue=true;
							break;
						}	
				}if(!isTrue){
					Map<String, Object> shouhcfkb = new HashMap<String, Object>();
					shouhcfkb.put("diancxxb_id", diancid);
					shouhcfkb.put("riq", CurOraDate);
					shouhcfkb.put("laimsl", listMap.get("LAIMSL"));
					shouhcfkb.put("rez", listMap.get("REZ"));
					shouhcfkb.put("meij", listMap.get("MEIJ"));
					shouhcfkb.put("yunj", listMap.get("YUNJ"));
					shouhcfkb.put("meikxxb_id", listMap.get("MEIKXXB_ID"));
					shouhcfkb.put("pinzb_id", listMap.get("PINZB_ID"));
					shouhcfkb.put("gongysb_id", listMap.get("GONGYSB_ID"));
					shouhcfkb.put("JIHKJB_ID", listMap.get("JIHKJB_ID"));
					shouhcfkb.put("yunsfsb_id", listMap.get("YUNSFSB_ID"));
					
					int result = addShouhcfkb1(shouhcfkb);
					if(result<0){
						return"保存失败";
					}
//					insertSql.append(
//							"insert into shouhcfkb\n" +
//							"  (id, diancxxb_id,riq,laimsl, rez, meij, yunj,meikxxb_id,pinzb_id,gongysb_id,JIHKJB_ID,yunsfsb_id)\n" + 
//							"values\n" + 
//							"  (getnewid("+dcid+"),"+dcid+","+CurOraDate+","+
//							list.getDouble("laimsl")+","+list.getDouble("rez")+","+
//							list.getDouble("meij")+","+list.getDouble("yunj")+","+list.getString("meikxxb_id")+","+
//							list.getString("pinzb_id")+","+list.getString("gongysb_id")+","+list.getString("JIHKJB_ID")+","+list.getString("yunsfsb_id")+");\n");
				}
				isTrue=false;
//				OLDlist.beforefirst();

				
			}
		}else{
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, Object> listMap = (HashMap<String, Object>) list.get(i);
				Map<String, Object> shouhcfkb = new HashMap<String, Object>();
				shouhcfkb.put("diancxxb_id", diancid);
				shouhcfkb.put("riq", CurOraDate);
				shouhcfkb.put("laimsl", listMap.get("LAIMSL"));
				shouhcfkb.put("rez", listMap.get("REZ"));
				shouhcfkb.put("meij", listMap.get("MEIJ"));
				shouhcfkb.put("yunj", listMap.get("YUNJ"));
				shouhcfkb.put("meikxxb_id", listMap.get("MEIKXXB_ID"));
				shouhcfkb.put("pinzb_id", listMap.get("PINZB_ID"));
				shouhcfkb.put("gongysb_id", listMap.get("GONGYSB_ID"));
				shouhcfkb.put("JIHKJB_ID", listMap.get("JIHKJB_ID"));
				shouhcfkb.put("yunsfsb_id", listMap.get("YUNSFSB_ID"));
				
				int result = addShouhcfkb1(shouhcfkb);
				if(result<0){
					return"保存失败";
				}
			}			
		}
		
		
		/*********************************原来业务是全部删除后在批量插入数据 所以上面的代码逻辑暂时有误**********************/
		//删除数据
		int re=delShouhcfkb(paramMap);
		if(re<0){
			return "分矿数据删除失败！";
		}
//		更新数据
//		if(insertSql.length()>20){
//			re=con.getInsert(insertSql.toString());
//			if(re==-1){
//				OLDlist.close();
//				list.close();
//				return "分矿数据生成失败！";
//			}
//		}
		
		return "";
	}
		
			
//			while (list.next()) {
//				while(OLDlist.next()){
//
//					double laimsl=list.getDouble("laimsl");
//					double rez=list.getDouble("REZ");
////					判断是否对来煤信息进行覆盖
//					if(!Laimqz){
//						laimsl=OLDlist.getDouble("laimsl");
//					}
////					判断是否对热值信息进行覆盖
//					if(!Rezqz){
//						rez=OLDlist.getDouble("rez");
//					}
////					如果煤价为0，则使用系统取值进行覆盖操作，否则数据将保持不变
//					double meij=OLDlist.getDouble("meij");
////					if(rez>0 && meij==0){
////						meij=list.getDouble("meij");
////					}
////					如果运价为0，则使用系统取值进行覆盖操作，否则数据将保持不变
//					double yunj=OLDlist.getDouble("yunj");
////					if(rez>0 && yunj==0){
////						yunj=list.getDouble("yunj");
////					}
//					
//					double meijs=OLDlist.getDouble("meijs");
//					double yunjs=OLDlist.getDouble("yunjs");
//					double huocyj=OLDlist.getDouble("huocyj");
//					double huocyjs=OLDlist.getDouble("huocyjs");
//					double qicyj=OLDlist.getDouble("qicyj");
//					double qicyjs=OLDlist.getDouble("qicyjs");
//					
////					如果热值为0那么煤价和运价及其对应的税金均为0
//					if(rez==0){
//						yunj=0;
//						meij=0;
//						meijs=0;
//						yunjs=0;
//						huocyj=0;
//						huocyjs=0;
//						qicyj=0;
//						qicyjs=0;
//					}
//					if(list.getString("meikxxb_id").equals(OLDlist.getString("meikxxb_id")) 
//						&& list.getString("pinzb_id").equals(OLDlist.getString("pinzb_id")) 
//						&& list.getString("gongysb_id").equals(OLDlist.getString("gongysb_id")) 
//						&& list.getString("JIHKJB_ID").equals(OLDlist.getString("JIHKJB_ID"))
//						&& list.getString("YUNSFSB_ID").equals(OLDlist.getString("YUNSFSB_ID"))){
//						insertSql.append(
//								"insert into shouhcfkb\n" +
//								"  (id, diancxxb_id,riq,laimsl, rez, meij, yunj,meijs,yunjs,huocyj,huocyjs,qicyj,qicyjs,meikxxb_id,pinzb_id,gongysb_id,JIHKJB_ID,yunsfsb_id)\n" + 
//								"values\n" + 
//								"  (getnewid("+dcid+"),"+dcid+","+CurOraDate+","+
//								laimsl+","+rez+","+meij+","+yunj+","+meijs+","+yunjs+","+huocyj+","+huocyjs+","
//								+qicyj+","+qicyjs+","+list.getString("meikxxb_id")+","+
//								list.getString("pinzb_id")+","+list.getString("gongysb_id")+","+list.getString("JIHKJB_ID")+","+list.getString("yunsfsb_id")+");\n");
//						isTrue=true;
//						break;
//					}
//				}
//				if(!isTrue){
//					insertSql.append(
//							"insert into shouhcfkb\n" +
//							"  (id, diancxxb_id,riq,laimsl, rez, meij, yunj,meikxxb_id,pinzb_id,gongysb_id,JIHKJB_ID,yunsfsb_id)\n" + 
//							"values\n" + 
//							"  (getnewid("+dcid+"),"+dcid+","+CurOraDate+","+
//							list.getDouble("laimsl")+","+list.getDouble("rez")+","+
//							list.getDouble("meij")+","+list.getDouble("yunj")+","+list.getString("meikxxb_id")+","+
//							list.getString("pinzb_id")+","+list.getString("gongysb_id")+","+list.getString("JIHKJB_ID")+","+list.getString("yunsfsb_id")+");\n");
//				}
//				isTrue=false;
//				OLDlist.beforefirst();
//			}
//			insertSql.append("end;");
//		}else{
//			while (list.next()) {
//				insertSql.append(
//						"insert into shouhcfkb\n" +
//						"  (id, diancxxb_id,riq,laimsl, rez, meij, yunj,meikxxb_id,pinzb_id,gongysb_id,JIHKJB_ID,yunsfsb_id)\n" + 
//						"values\n" + 
//						"  (getnewid("+dcid+"),"+dcid+","+CurOraDate+","+
//						list.getDouble("laimsl")+","+list.getDouble("rez")+","+
//						list.getDouble("meij")+","+list.getDouble("yunj")+","+list.getString("meikxxb_id")+","+
//						list.getString("pinzb_id")+","+list.getString("gongysb_id")+","+list.getString("JIHKJB_ID")+","+list.getString("yunsfsb_id")+");\n");
//			}
//			insertSql.append("end;");
//		}
//
//		int re=0;
//
////		删除数据
//		String delData="delete from shouhcfkb where diancxxb_id="+dcid+" and riq = "+CurOraDate;
//		re=con.getDelete(delData);
//		if(re==-1){
//			OLDlist.close();
//			list.close();
//			return "分矿数据删除失败！";
//		}
////		更新数据
//		if(insertSql.length()>20){
//			re=con.getInsert(insertSql.toString());
//			if(re==-1){
//				OLDlist.close();
//				list.close();
//				return "分矿数据生成失败！";
//			}
//		}
//		OLDlist.close();
//		list.close();
//		return "";
//	}
	
	
	
	public List<Map<String,Object>> getOLDShouhcfk(Map<String, Object> map){
		return diaoyrbDao.getOLDShouhcfk(map);
	}
	
	public List<Map<String,Object>> getShouhcfk(Map<String, Object> map){
		return diaoyrbDao.getShouhcfk(map);
	}	
	
	public int addShouhcfkb(Map<String, Object> map){
		return diaoyrbDao.addShouhcfkb(map);
	}
	
	public int addShouhcfkb1(Map<String, Object> map){
		return diaoyrbDao.addShouhcfkb1(map);
	}
	
	public int delShouhcfkb(Map<String, Object> map){
		return diaoyrbDao.delShouhcfkb(map);
	}
	
}
