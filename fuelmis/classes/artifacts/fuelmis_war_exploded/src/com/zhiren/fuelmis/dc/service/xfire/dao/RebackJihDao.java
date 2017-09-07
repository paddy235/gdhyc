package com.zhiren.fuelmis.dc.service.xfire.dao;

public interface RebackJihDao {
	//年计划回退
	public void HuitYear(String zhuangt,String huitlc_id,String year,String month,String diancxxb_id,String advice,String rebacker); 
	//月计划回退
	public void HuitMonth(String zhuangt,String huitlc_id,String year,String month,String diancxxb_id,String advice,String rebacker);
	
	//合同回退
	public void HuitHet(String zhuangt,String huitlc_id,String hetb_id,String rebacker,String advice);
}
