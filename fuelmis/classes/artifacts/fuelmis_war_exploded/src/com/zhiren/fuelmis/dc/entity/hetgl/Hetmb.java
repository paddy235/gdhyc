package com.zhiren.fuelmis.dc.entity.hetgl;

public class Hetmb {
	
	private Long id;
	private Long diancxxb_id;
	private Long lurry;    //录入人员
	private int zhuangt;   //合同状态
	private String hetbh;  //合同编号
	private String youxkssj; //合同有效开始时间
	private String youxjssj; //合同有效结束时间
	private String caozsj;  //操作时间
	

	public String getHetbh() {
		return hetbh;
	}
	public void setHetbh(String hetbh) {
		this.hetbh = hetbh;
	}
	public String getYouxkssj() {
		return youxkssj;
	}
	public void setYouxkssj(String youxkssj) {
		this.youxkssj = youxkssj;
	}
	public String getYouxjssj() {
		return youxjssj;
	}
	public void setYouxjssj(String youxjssj) {
		this.youxjssj = youxjssj;
	}
	public String getCaozsj() {
		return caozsj;
	}
	public void setCaozsj(String caozsj) {
		this.caozsj = caozsj;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDiancxxb_id() {
		return diancxxb_id;
	}
	public void setDiancxxb_id(Long diancxxb_id) {
		this.diancxxb_id = diancxxb_id;
	}
	public Long getLurry() {
		return lurry;
	}
	public void setLurry(Long lurry) {
		this.lurry = lurry;
	}
	public int getZhuangt() {
		return zhuangt;
	}
	public void setZhuangt(int zhuangt) {
		this.zhuangt = zhuangt;
	}
	
	

}
