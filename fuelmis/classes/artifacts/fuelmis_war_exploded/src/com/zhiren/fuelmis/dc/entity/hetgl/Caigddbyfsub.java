package com.zhiren.fuelmis.dc.entity.hetgl;

public class Caigddbyfsub {
	private Long 	id;	        //ID
	private Long 	caigddb_id; //采购订单表ID
	private Long 	feiyxm_id;        //费用项目ID
	private Long 	faz_id;        //发站
	private Long 	daoz_id;        //到站
	private Long 	yunsfsb_id;	//运输方式id
	private double 	shul;	    //数量
	private double 	hansyfdj;		//含税运费单价
	private double 	buhsyfdj;		//不含税运费单价
	private double 	hanszfdj;		//含税杂费单价
	private double 	bhszfdj;		//不含税杂费单价
	private String 	shuljsfs;	//数量结算方式
	private double meikxxb_id;
	
	public double getMeikxxb_id() {
		return meikxxb_id;
	}
	public void setMeikxxb_id(double meikxxb_id) {
		this.meikxxb_id = meikxxb_id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCaigddb_id() {
		return caigddb_id;
	}
	public void setCaigddb_id(Long caigddb_id) {
		this.caigddb_id = caigddb_id;
	}
	public Long getFeiyxm_id() {
		return feiyxm_id;
	}
	public void setFeiyxm_id(Long feiyxm_id) {
		this.feiyxm_id = feiyxm_id;
	}
	public Long getFaz_id() {
		return faz_id;
	}
	public void setFaz_id(Long faz_id) {
		this.faz_id = faz_id;
	}
	public Long getDaoz_id() {
		return daoz_id;
	}
	public void setDaoz_id(Long daoz_id) {
		this.daoz_id = daoz_id;
	}
	public Long getYunsfsb_id() {
		return yunsfsb_id;
	}
	public void setYunsfsb_id(Long yunsfsb_id) {
		this.yunsfsb_id = yunsfsb_id;
	}
	public double getShul() {
		return shul;
	}
	public void setShul(double shul) {
		this.shul = shul;
	}
	public double getHansyfdj() {
		return hansyfdj;
	}
	public void setHansyfdj(double hansyfdj) {
		this.hansyfdj = hansyfdj;
	}
	public double getBuhsyfdj() {
		return buhsyfdj;
	}
	public void setBuhsyfdj(double buhsyfdj) {
		this.buhsyfdj = buhsyfdj;
	}
	public double getHanszfdj() {
		return hanszfdj;
	}
	public void setHanszfdj(double hanszfdj) {
		this.hanszfdj = hanszfdj;
	}
	public double getBhszfdj() {
		return bhszfdj;
	}
	public void setBhszfdj(double bhszfdj) {
		this.bhszfdj = bhszfdj;
	}
	public String getShuljsfs() {
		return shuljsfs;
	}
	public void setShuljsfs(String shuljsfs) {
		this.shuljsfs = shuljsfs;
	}
	
}
