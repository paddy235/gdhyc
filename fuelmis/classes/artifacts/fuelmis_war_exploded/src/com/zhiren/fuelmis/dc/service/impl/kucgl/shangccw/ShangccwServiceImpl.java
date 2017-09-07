package com.zhiren.fuelmis.dc.service.impl.kucgl.shangccw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.kucgl.shangccw.ShangccwDao;
import com.zhiren.fuelmis.dc.service.kucgl.shangccw.IShangccwService;
import com.zhiren.fuelmis.dc.utils.formular.Result;

@Service
public class ShangccwServiceImpl implements IShangccwService {
	@Autowired
	private ShangccwDao shangccwDao;

	@Override
	public String[][] getList(Map<String, Object> map) {
		String[][] array = null;
		if ((Integer) map.get("leix") == 0) {
			List<Map<String, Object>> list = shangccwDao.getJiesdList(map);
			array = Result.list2array(list, new String[] { "id", "jiesdh", "gonghr", "shoukdw", "hetbh","jiessl", "danj", "hansje", "buhsje", "shuil", "shuik", "zhuangt" });
		} else if ((Integer) map.get("leix") == 1) {
			List<Map<String, Object>> list = shangccwDao.getZangList(map);
			array = Result.list2array(list, new String[] { "kucwl", "mingc", "zang_sl", "zang_je" });
		} else if ((Integer) map.get("leix") == 2) {
			List<Map<String, Object>> list = shangccwDao.getHaoyList(map);
			array = Result.list2array(list, new String[] { "kucwl", "mingc", "byfdhysl", "byfdhyje", "bygrhysl", "bygrhyje","bycssl", "bycsje"});
		}
		return array;
	}

	@Override
	public List<Map<String, Object>> getList4Xml(List<String> idList, Map<String, Object> map) {
		String leix=map.get("leix").toString();
		String riq=map.get("riq").toString();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String ids = "-1";
		for (String id : idList) {
			ids += "," + id;
		}
		map.put("ids", ids);
		List<Map<String, Object>> itemList = shangccwDao.getList4Xml(map);
		if (leix.equals("0")) {
			for (Map<String,Object> itemMap:itemList) {
				if(itemMap.get("JIESLX")==null||itemMap.get("JIESLX").equals("煤款结算")){//结算类型为煤款结算
					itemMap.remove("JIESLX");
				}else{//结算类型为杂费结算
					itemMap.remove("JIESLX");
//					Map<String, Object> itemMap=new HashMap<String,Object>();
					List<Map<String,Object>> zafItemList=new ArrayList<Map<String,Object>>();
					Map<String,Object> zafMap=new HashMap<String,Object>();
					zafMap.put("yzfxm",itemMap.get("YZFXM"));
                    itemMap.remove("YZFXM");
					zafMap.put("yfje",itemMap.get("BHSJE"));
                    zafItemList.add(zafMap);
					itemMap.put("item",zafItemList);
				}
				list.add(itemMap);
			}
		} else if (leix.equals("2")) {//耗用
			Map<String, Object> itemMap = shangccwDao.getHaoyHead(map);
			itemMap.put("compid", "515");
			itemMap.put("compname", "国电新疆红雁池发电有限公司");
			itemMap.put("item", itemList);
			list.add(itemMap);
		} else {//暫估
			Map<String, Object>  itemMap=shangccwDao.getZangHejList(map);
			itemMap.put("compid", "515");
			itemMap.put("item", itemList);
			itemMap.put("compname", "国电新疆红雁池发电有限公司");
			itemMap.put("zhaiyao","暂估"+riq.substring(5,7)+"月燃煤款");
			list.add(itemMap);
		}
		return list;
	}

	@Override
	public void updateJiesdShangccwzt(List<String> idList) {
		String ids = "-1";
		for (String id : idList) {
			ids += "," + id;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		shangccwDao.updateJiesdShangccwzt(map);
	}

	@Override
	public void updateMonthTotalShangccwzt(List<String> idList) {
		String ids = "-1";
		for (String id : idList) {
			ids += "," + id;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		shangccwDao.updateMonthTotalShangccwzt(map);
	}

}
