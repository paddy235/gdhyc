package com.zhiren.fuelmis.dc.service.xfire.dao;
/*
 * 厂内费用接口
 */
public interface CnfyReback {
	//月度预算回退接口
	public String yuedyshuit(String diancid,String riq);
	//月度预算审核接口
	public String yuedys(String jsondata);
	//月度预算回退接口
	public String niandyshuit( String diancid, String nianf);
	//月度预算审核接口
	public String niandys(String jsondata);
	//费用项目审核接口
	public String feiyxmsp(String changnfyid, String feiymc);
}
