package com.zhiren.fuelmis.dc.entity.common;

import java.io.Serializable;

public class Fujxx implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7132719570519703846L;
	private Long id;
	private String mingc;
	private String luj;
	private String yewly;
	private Long guanlid;
	private int zhuangt;
	private String daim;
	private String youxksrq;
	private String youxjsrq;
	private Long fid;
	
	
	
	public Long getFid() {
		return fid;
	}
	public void setFid(Long fid) {
		this.fid = fid;
	}
	public String getYouxksrq() {
		return youxksrq;
	}
	public void setYouxksrq(String youxksrq) {
		this.youxksrq = youxksrq;
	}
	public String getYouxjsrq() {
		return youxjsrq;
	}
	public void setYouxjsrq(String youxjsrq) {
		this.youxjsrq = youxjsrq;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMingc() {
		return mingc;
	}
	public void setMingc(String mingc) {
		this.mingc = mingc;
	}
	public String getLuj() {
		return luj;
	}
	public void setLuj(String luj) {
		this.luj = luj;
	}
	public String getYewly() {
		return yewly;
	}
	public void setYewly(String yewly) {
		this.yewly = yewly;
	}
	public Long getGuanlid() {
		return guanlid;
	}
	public void setGuanlid(Long guanlid) {
		this.guanlid = guanlid;
	}
	public int getZhuangt() {
		return zhuangt;
	}
	public void setZhuangt(int zhuangt) {
		this.zhuangt = zhuangt;
	}
	public String getDaim() {
		return daim;
	}
	public void setDaim(String daim) {
		this.daim = daim;
	}
}
