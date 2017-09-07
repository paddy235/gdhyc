
package com.zhiren.fuelmis.dc.webService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.zhiren.fuelmis.dc.service.js.IJiesService;
import com.zhiren.fuelmis.dc.service.ruchy.IRuchyService;
import com.zhiren.fuelmis.dc.service.rulhy.IRulhyService;
import com.zhiren.fuelmis.dc.service.xfire.dao.RebackJihDao;
@Component
public class reback extends SpringBeanAutowiringSupport  {
	@Autowired
	private IJiesService jiesService;
	@Autowired
	private RebackJihDao jihService;
	public String Jshuit(String zhuangt,String huitlc_id,String jiesdbh,String rebacker,String advice) {
		try {
			return jiesService.jsHuit( zhuangt, huitlc_id, jiesdbh, rebacker, advice);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//年计划回退
	public void HuitYear(String zhuangt,String huitlc_id,String year,String month,String diancxxb_id,String advice,String rebacker){
		jihService.HuitYear(zhuangt, huitlc_id, year, month, diancxxb_id, advice, rebacker);
	}
	//月计划回退
	public void HuitMonth(String zhuangt,String huitlc_id,String year,String month,String diancxxb_id,String advice,String rebacker){
		jihService.HuitMonth(zhuangt, huitlc_id, year, month, diancxxb_id, advice, rebacker);
	}
	
	//合同回退
	public void Hthuit(String zhuangt,String huitlc_id,String hetb_id,String rebacker,String advice){
		jihService.HuitHet(zhuangt, huitlc_id, hetb_id, rebacker, advice);
	}
}
