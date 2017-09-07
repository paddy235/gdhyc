package com.zhiren.fuelmis.dc.entity.yueshchjb;

public class YueshchjbVo {
	private Long id;		
	private String riq;		
	private String fenx;//		项目(本月\累计)
	private double qickc;//		期初库存
	private double shouml;//		收煤量
	private double fady;//		发电耗
	private double gongry;//		供热耗
	private double qith;//		其他耗
	private double sunh;//		损耗
	private double diaocl;//		调出量
	
	private double fadyx;//		发电耗
	private double gongryx;//		供热耗
	private double qithx;//		其他耗
	private double sunhx;//		损耗
	private double diaoclx;//		调出量
	
	private double panyk;//		盘盈亏
	private double kuc;//		库存
	private Long diancxxb_id;//			组织id
	private double shuifctz;//		水分差调整
	
	public double getFadyx() {
		return fadyx;
	}
	public void setFadyx(double fadyx) {
		this.fadyx = fadyx;
	}
	public double getGongryx() {
		return gongryx;
	}
	public void setGongryx(double gongryx) {
		this.gongryx = gongryx;
	}
	public double getQithx() {
		return qithx;
	}
	public void setQithx(double qithx) {
		this.qithx = qithx;
	}
	public double getSunhx() {
		return sunhx;
	}
	public void setSunhx(double sunhx) {
		this.sunhx = sunhx;
	}
	public double getDiaoclx() {
		return diaoclx;
	}
	public void setDiaoclx(double diaoclx) {
		this.diaoclx = diaoclx;
	}
	public double getShuifctzx() {
		return shuifctzx;
	}
	public void setShuifctzx(double shuifctzx) {
		this.shuifctzx = shuifctzx;
	}
	public double getRunxcsx() {
		return runxcsx;
	}
	public void setRunxcsx(double runxcsx) {
		this.runxcsx = runxcsx;
	}
	private double shuifctzx;//		水分差调整
	
	
	private int zhuangt;//		审核状态
	private double jitcs;//		计提储损
	private double runxcs;//		允许储损
	private double runxcsx;//		允许储损
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRiq() {
		return riq;
	}
	public void setRiq(String riq) {
		this.riq = riq;
	}
	public String getFenx() {
		return fenx;
	}
	public void setFenx(String fenx) {
		this.fenx = fenx;
	}
	public double getQickc() {
		return qickc;
	}
	public void setQickc(double qickc) {
		this.qickc = qickc;
	}
	public double getShouml() {
		return shouml;
	}
	public void setShouml(double shouml) {
		this.shouml = shouml;
	}
	public double getFady() {
		return fady;
	}
	public void setFady(double fady) {
		this.fady = fady;
	}
	public double getGongry() {
		return gongry;
	}
	public void setGongry(double gongry) {
		this.gongry = gongry;
	}
	public double getQith() {
		return qith;
	}
	public void setQith(double qith) {
		this.qith = qith;
	}
	public double getSunh() {
		return sunh;
	}
	public void setSunh(double sunh) {
		this.sunh = sunh;
	}
	public double getDiaocl() {
		return diaocl;
	}
	public void setDiaocl(double diaocl) {
		this.diaocl = diaocl;
	}
	public double getPanyk() {
		return panyk;
	}
	public void setPanyk(double panyk) {
		this.panyk = panyk;
	}
	public double getKuc() {
		return kuc;
	}
	public void setKuc(double kuc) {
		this.kuc = kuc;
	}
	public Long getDiancxxb_id() {
		return diancxxb_id;
	}
	public void setDiancxxb_id(Long diancxxb_id) {
		this.diancxxb_id = diancxxb_id;
	}
	public double getShuifctz() {
		return shuifctz;
	}
	public void setShuifctz(double shuifctz) {
		this.shuifctz = shuifctz;
	}
	public int getZhuangt() {
		return zhuangt;
	}
	public void setZhuangt(int zhuangt) {
		this.zhuangt = zhuangt;
	}
	public double getJitcs() {
		return jitcs;
	}
	public void setJitcs(double jitcs) {
		this.jitcs = jitcs;
	}
	public double getRunxcs() {
		return runxcs;
	}
	public void setRunxcs(double runxcs) {
		this.runxcs = runxcs;
	}
}