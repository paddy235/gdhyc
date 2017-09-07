package com.zhiren.fuelmis.dc.entity.jih;

public class YuedranlzfjhBean {
	long id;
	long diancid;
	String riq;
	String tbrq;
	String zafmc;
	double yucje;
	String yucsm;
	long zhuangt;
	
	public YuedranlzfjhBean(){}

	public YuedranlzfjhBean(long id, long diancid, String riq, String tbrq,
			String zafmc, double yucje, String yucsm, long zhuangt) {
		super();
		this.id = id;
		this.diancid = diancid;
		this.riq = riq;
		this.tbrq = tbrq;
		this.zafmc = zafmc;
		this.yucje = yucje;
		this.yucsm = yucsm;
		this.zhuangt = zhuangt;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDiancid() {
		return diancid;
	}

	public void setDiancid(long diancid) {
		this.diancid = diancid;
	}

	public String getRiq() {
		return riq;
	}

	public void setRiq(String riq) {
		this.riq = riq;
	}

	public String getTbrq() {
		return tbrq;
	}

	public void setTbrq(String tbrq) {
		this.tbrq = tbrq;
	}

	public String getZafmc() {
		return zafmc;
	}

	public void setZafmc(String zafmc) {
		this.zafmc = zafmc;
	}

	
	public double getYucje() {
		return yucje;
	}

	public void setYucje(double yucje) {
		this.yucje = yucje;
	}

	public String getYucsm() {
		return yucsm;
	}

	public void setYucsm(String yucsm) {
		this.yucsm = yucsm;
	}

	public long getZhuangt() {
		return zhuangt;
	}

	public void setZhuangt(long zhuangt) {
		this.zhuangt = zhuangt;
	}
}
