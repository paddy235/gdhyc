package com.zhiren.fuelmis.dc.entity.hetgl;

public class Caigddbsub {
	private Long 	id;	        //ID
	private Long 	caigddb_id; //采购订单表ID
	private Long 	xuh;        //序号
	private double 	shul;	    //数量
	private String 	zhil;	    //质量
	private double 	pingcj;		//平仓价
	private double 	yunzf;		//运杂费
	private double 	daocj;		//到厂价
	private Long 	yunsfs_id;	//运输方式id
	private String 	shuljsfs;	//数量结算方式
	private Long 	jijfs_id;	//计价方式id
	private Long 	gongys_id;	//供应商id
	private String 	gongysmc;	//供应商名称
	private Long 	diancxxb_id;//电厂信息表id
	private Long 	huow_id;	//货物id
	private String 	huowmc;	    //货物名称
	private String 	huowgg;	    //货物规格
	/**
	 * 孟建云 add
	 */
	private String zhiljsyj;		//质量结算依据（0厂方，1矿方，2第三方）
	private String shuljsyj;		//数量结算依据（0厂方，1矿方，2第三方）
	private String jiesfs;		//1加权评价，2单批次
	private String startdate;		//开始有效期
	private String enddate;		//结束有效期
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public Long getMeiz_id() {
		return meiz_id;
	}
	public void setMeiz_id(Long meiz_id) {
		this.meiz_id = meiz_id;
	}
	private Long   meiz_id;		//煤种
	private Long   pinz_id;		//品种
	private double   pingcj_bhs;		//平仓价-不含税
	private double   yunzf_bhs;		//运杂费-不含税
	private double   daocj_bhs;		//到厂价-不含税
	private Long hetjsfsb_id;		//价格类型
	private double meikxxb_id;

	public double getMeikxxb_id() {
		return meikxxb_id;
	}
	public void setMeikxxb_id(double meikxxb_id) {
		this.meikxxb_id = meikxxb_id;
	}
	public Long getHetjsfsb_id() {
		return hetjsfsb_id;
	}
	public void setHetjsfsb_id(Long hetjsfsb_id) {
		this.hetjsfsb_id = hetjsfsb_id;
	}
	public Long getPinz_id() {
		return pinz_id;
	}
	public void setPinz_id(Long pinz_id) {
		this.pinz_id = pinz_id;
	}
	public double getPingcj_bhs() {
		return pingcj_bhs;
	}
	public void setPingcj_bhs(double pingcj_bhs) {
		this.pingcj_bhs = pingcj_bhs;
	}
	public double getYunzf_bhs() {
		return yunzf_bhs;
	}
	public void setYunzf_bhs(double yunzf_bhs) {
		this.yunzf_bhs = yunzf_bhs;
	}
	public double getDaocj_bhs() {
		return daocj_bhs;
	}
	public void setDaocj_bhs(double daocj_bhs) {
		this.daocj_bhs = daocj_bhs;
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
	public Long getXuh() {
		return xuh;
	}
	public void setXuh(Long xuh) {
		this.xuh = xuh;
	}
	public double getShul() {
		return shul;
	}
	public void setShul(double shul) {
		this.shul = shul;
	}
	public String getZhil() {
		return zhil;
	}
	public void setZhil(String zhil) {
		this.zhil = zhil;
	}
	public double getPingcj() {
		return pingcj;
	}
	public void setPingcj(double pingcj) {
		this.pingcj = pingcj;
	}
	public double getYunzf() {
		return yunzf;
	}
	public void setYunzf(double yunzf) {
		this.yunzf = yunzf;
	}
	public double getDaocj() {
		return daocj;
	}
	public void setDaocj(double daocj) {
		this.daocj = daocj;
	}
	public Long getYunsfs_id() {
		return yunsfs_id;
	}
	public void setYunsfs_id(Long yunsfs_id) {
		this.yunsfs_id = yunsfs_id;
	}
	public String getShuljsfs() {
		return shuljsfs;
	}
	public void setShuljsfs(String shuljsfs) {
		this.shuljsfs = shuljsfs;
	}
	public Long getJijfs_id() {
		return jijfs_id;
	}
	public void setJijfs_id(Long jijfs_id) {
		this.jijfs_id = jijfs_id;
	}
	public Long getGongys_id() {
		return gongys_id;
	}
	public void setGongys_id(Long gongys_id) {
		this.gongys_id = gongys_id;
	}
	public String getGongysmc() {
		return gongysmc;
	}
	public void setGongysmc(String gongysmc) {
		this.gongysmc = gongysmc;
	}
	public Long getDiancxxb_id() {
		return diancxxb_id;
	}
	public void setDiancxxb_id(Long diancxxb_id) {
		this.diancxxb_id = diancxxb_id;
	}
	public Long getHuow_id() {
		return huow_id;
	}
	public void setHuow_id(Long huow_id) {
		this.huow_id = huow_id;
	}
	public String getHuowmc() {
		return huowmc;
	}
	public void setHuowmc(String huowmc) {
		this.huowmc = huowmc;
	}
	public String getHuowgg() {
		return huowgg;
	}
	public void setHuowgg(String huowgg) {
		this.huowgg = huowgg;
	}

	public String getZhiljsyj() {
		return zhiljsyj;
	}
	public void setZhiljsyj(String zhiljsyj) {
		this.zhiljsyj = zhiljsyj;
	}
	public String getShuljsyj() {
		return shuljsyj;
	}
	public void setShuljsyj(String shuljsyj) {
		this.shuljsyj = shuljsyj;
	}
	public String getJiesfs() {
		return jiesfs;
	}
	public void setJiesfs(String jiesfs) {
		this.jiesfs = jiesfs;
	}

}
