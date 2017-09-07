package com.zhiren.fuelmis.dc.entity.xitgl;

import java.util.List;


/** 
 * @author 陈宝露
 */
public class Ziyxx {
	private Long id;
	
	private int xuh;
	
	private String name;
	
	private String wenjwz;
	
	private int jib;
	
	private Long fuid;
	
	private int zhuangt; 
	
	private List<Ziyxx> children;
	
	private String open;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getXuh() {
		return xuh;
	}

	public void setXuh(int xuh) {
		this.xuh = xuh;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWenjwz() {
		return wenjwz;
	}

	public void setWenjwz(String wenjwz) {
		this.wenjwz = wenjwz==null||"".equals(wenjwz)?"#":wenjwz;
	}

	public int getJib() {
		return jib;
	}

	public void setJib(int jib) {
		this.jib = jib;
	}

	public Long getFuid() {
		return fuid;
	}

	public void setFuid(Long fuid) {
		this.fuid = fuid;
	}
	
	public int getZhuangt() {
		return zhuangt;
	}

	public void setZhuangt(int zhuangt) {
		this.zhuangt = zhuangt;
	}
	
	public List<Ziyxx> getChildren() {
		return children;
	}

	public void setChildren(List<Ziyxx> children) {
		this.children = children;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}
}
