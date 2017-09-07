package com.zhiren.fuelmis.dc.entity.common;
/** 
 * @author 陈宝露
 */
public class Combobox {
	private Object name;
	
	private Object value;

	public Object getName() {
		return name;
	}

	public void setName(Object name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	public Combobox(){}
	
	public Combobox(Object name,Object value){
		this.name = name;
		this.value = value;
	}
}
