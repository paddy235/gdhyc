
package com.zhiren.fuelmis.dc.service.impl.jiesgl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhiren.fuelmis.dc.dao.jiesgl.ZafjsDao;
import com.zhiren.fuelmis.dc.entity.xitgl.Diancxx;
import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.jiesgl.IZafjsService;
import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.Sequence;
import com.zhiren.fuelmis.dc.utils.DateUtil.DateFormatType;

import net.sf.json.JSONObject;
@Service
public class ZafjsServiceImpl implements IZafjsService {
	@Autowired
	private ZafjsDao zafjsDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public List<Map<String, Object>> getZafjs() {
		List<Map<String,Object>> list=zafjsDao.getZafjs();
		return list;
	}
	@Override@Transactional
	public void saveZafjs(List<Map<String, Object>> list,Renyxx user,Diancxx diancxx) {
		for (Map<String, Object> map : list) {
			if(map.get("ID")!=null){
				zafjsDao.updateZafjs(map);
			}else{
			    String newId = Sequence.nextId();
                // 生成结算编号---------------------------
                String jiesbh = "GD-JS-"
                        + diancxx.getPiny()
                        + "-"
                        + DateUtil.format(new Date(), DateFormatType.SIMPLE_TYPE_MONTH).replace("-", "") + "-";
                int xuh = 0;
                List<Map<String, Object>> xuhList = jdbcTemplate.queryForList(
                        "select nvl(max(substr(jiesbh,18,19)),0)+1 xuh from RL_JS_YUEJSDB where  substr(jiesbh,0,17)='" + jiesbh + "'");
                if (xuhList.size() != 0) {
                    xuh = Integer.parseInt(xuhList.get(0).get("XUH").toString());
                }
                if (xuh < 10) {
                    jiesbh += "0" + xuh;
                } else {
                    jiesbh += xuh;
                }
                //获取供应商名称
                String gongys_id = map.get("GONGYSB_ID").toString();
                String  gongysmc = zafjsDao.getgongysmcById(gongys_id);
                //获取单位名称
                String diancmc = diancxx.getQuanc();
                map.put("GONGYSMC", gongysmc);
                map.put("DIANCMC", diancmc);
                //-----------------------------------------------
                map.put("CHANGRLJBR", user.getQuanc());
                map.put("JIESBH", jiesbh);
                map.put("ID", Sequence.nextId());
				zafjsDao.insertZafjs(map);
			}
			
		}
	}
	@Override@Transactional
	public void delZafjs(String id) {
		zafjsDao.delZafjs(id);		
	}

	

	
	
}
