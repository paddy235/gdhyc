package com.zhiren.fuelmis.dc.service.impl.kucgl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.kucgl.PandcxDao;
import com.zhiren.fuelmis.dc.report.Report;
import com.zhiren.fuelmis.dc.report.Table;
import com.zhiren.fuelmis.dc.service.kucgl.IPandcxService;

import com.zhiren.fuelmis.dc.utils.DateUtil;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author 陈宝露
 */
@Service
public class PandcxServiceImpl implements IPandcxService {

	@Autowired
	private PandcxDao pandcxDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;
	@Override
	public JSONArray getReport(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<LinkedHashMap<String, Object>> list = pandcxDao.getReport(map);
		
		List<Map<String,Object>> lst = new ArrayList<Map<String,Object>>();
		
		for(int j=0;j<list.size();j++){
			Map<String,Object> maps = list.get(j);
			String url = "/";
			if(maps.get("FUJMC")!=null){
				url = "<a href='common/downloadFile?fileId="+maps.get("FUJMC")+"'>"+maps.get("FUJMC")+"</a>";
			}
			maps.remove("FUJMC");
			maps.put("FUJMC", url);
			//maps.remove("MINGC");
			
			lst.add(maps);
		}

		String[][] arrData = new String[list.size()][8];
		for (int i = 0; i < lst.size(); i++) {
			int j = 0;
			Iterator<String> it = lst.get(i).keySet().iterator();
			while (it.hasNext()) {
				arrData[i][j++] = lst.get(i).get(it.next()).toString();
			}
		}

		Report rt = new Report();
		String[][] ArrHeader = new String[1][8];
		ArrHeader[0] = new String[] { "单位", "日期", "账面库存", "实盘库存", "场损量",
				"水分差调整量", "盈亏吨", "附件"};
		
		int[] ArrWidth = new int[] { 150, 70, 70, 70, 70, 90, 70, 300};
		rt.setTitle(map.get("riq").toString()+"年盘点情况查询", ArrWidth);
		rt.setBody(new Table(arrData, 1, 0, 0, 8));
		rt.body.setHeaderData(ArrHeader);
		rt.body.setPageRows(-1);
		int pageCount = rt.getPages();
		rt.body.ShowZero = true;
		rt.body.mergeFixedCol(1);
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
		rt.body.setColAlign(3, Table.ALIGN_RIGHT);
		rt.body.setColAlign(4, Table.ALIGN_RIGHT);
		rt.body.setColAlign(5, Table.ALIGN_RIGHT);
		rt.body.setColAlign(6, Table.ALIGN_RIGHT);
		rt.body.setColAlign(7, Table.ALIGN_RIGHT);
		rt.body.setColAlign(8, Table.ALIGN_LEFT);
		rt.createDefautlFooter(ArrWidth);
		rt.setDefautlFooter(1, 3, "打印日期:"+DateUtil.format(new Date(),DateUtil.DateFormatType.SIMPLE_TYPE), Table.ALIGN_LEFT);
		rt.setDefautlFooter(4, 2, "审核:", Table.ALIGN_LEFT);
		rt.setDefautlFooter((rt.footer.getCols() - 1), 1, "制表:",Table.ALIGN_RIGHT);
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("html", rt.getAllPagesHtml(ArrWidth));
		resultMap.put("pageCount", pageCount);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}

	@Override@Transactional
	public void shangb(Map<String, Object> map) {
	    jdbcTemplate.update("update PAND_GDJT ZHUANGT=1 where substr(riq,0,4)='"+map.get("riq")+"'");
	}
}
