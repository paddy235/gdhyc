package com.zhiren.fuelmis.dc.entity.gongyspg.kaohzb;

/**
 * 考核指标
 * @author ZY
 *
 */
public class Kaohzb {

	private Long id;        //主键
	private String zhibmc;  //指标名称
	private String zhibdm;  //指标代码
	private String zhibdw;  //指标单位
	private String beiz;    //备注
	private int zhuangt;    //状态(1启用、2禁用)
	private int leib;       //类别(1质量指标，2数量指标)
	private Long diancxxb_id;
	

	public String getZhibdw() {
		return zhibdw;
	}
	public void setZhibdw(String zhibdw) {
		this.zhibdw = zhibdw;
	}
	public Long getDiancxxb_id() {
		return diancxxb_id;
	}
	public void setDiancxxb_id(Long diancxxb_id) {
		this.diancxxb_id = diancxxb_id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getZhibmc() {
		return zhibmc;
	}
	public void setZhibmc(String zhibmc) {
		this.zhibmc = zhibmc;
	}
	public String getZhibdm() {
		return zhibdm;
	}
	public void setZhibdm(String zhibdm) {
		this.zhibdm = zhibdm;
	}
	public String getBeiz() {
		return beiz;
	}
	public void setBeiz(String beiz) {
		this.beiz = beiz;
	}
	public int getZhuangt() {
		return zhuangt;
	}
	public void setZhuangt(int zhuangt) {
		this.zhuangt = zhuangt;
	}
	public int getLeib() {
		return leib;
	}
	public void setLeib(int leib) {
		this.leib = leib;
	}
	
	
}
