package com.zhiren.fuelmis.dc.service.impl.jiesgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zhiren.fuelmis.dc.dao.jiesgl.YuejstjtzDao;
import com.zhiren.fuelmis.dc.report.Report;
import com.zhiren.fuelmis.dc.report.Table;
import com.zhiren.fuelmis.dc.service.jiesgl.IYuejstjtzService;
import com.zhiren.fuelmis.dc.utils.formular.Result;

import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;

@Service
public class YuejstjtzServiceImpl implements IYuejstjtzService {

	@Resource
	private YuejstjtzDao yuejstjtzDao;
	
	@Override
	public JSONArray getYuejstjtz(Map<String,Object> map) {
		List<Map<String , Object>> list = yuejstjtzDao.getYuejstjtz(map);
		String[][] arrData=Result.list2array(list,new String[]{"CAOZRQ","JIESBH","GONGYSB_ID","PINZ","HETBH","SHIJD","CHES","JIESSL",
		"JIESRZ","JIESLF","JIESJG","JIESJE"});
		Report rt = new Report();
		String ArrHeader[][] = new String[1][12];
		ArrHeader[0] = new String[] { "结算日期","结算编号","供应商", "品种",  "合同编号",
				"来煤日期", "车数", "结算数量",
				"结算热值", "结算硫分", "结算价格",
				"价结算金额"};

		int ArrWidth[] = new int[] { 80, 120, 80, 70,150, 135, 55, 60, 60, 60,60, 85};
		rt.setBody(new Table(arrData, 1, 0, 0, 12));
		rt.body.setHeaderData(ArrHeader);
		rt.body.setPageRows(15);
		int pageCount = rt.getPages();
		rt.body.ShowZero = false;
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
		rt.body.setColAlign(3, Table.ALIGN_CENTER);
		rt.body.setColAlign(4, Table.ALIGN_CENTER);
		rt.body.setColAlign(5, Table.ALIGN_CENTER);
		for (int i = 6; i <= 12; i++) {
			rt.body.setColAlign(i, Table.ALIGN_RIGHT);
		}
		rt.setTitle("月结算统计台帐", ArrWidth);
		rt.body.setWidth(ArrWidth);
		rt.body.mergeFixedRowCol();

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("html", rt.getAllPagesHtml(ArrWidth));
		resultMap.put("pageCount", pageCount);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}

	@Override
	public List<Map<String, Object>> getHetbh(Map<String, Object> map) {
		List<Map<String, Object>> list=yuejstjtzDao.getHetbh(map);
		Map<String, Object> map2=new HashMap<String, Object>();
		map2.put("VALUE", "-1");
		map2.put("NAME", "全部");
		list.add(map2);
		return list;
	}


}
