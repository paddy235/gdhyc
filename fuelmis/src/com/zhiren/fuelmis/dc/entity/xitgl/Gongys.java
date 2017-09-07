package com.zhiren.fuelmis.dc.entity.xitgl;
/** 
 * @author 陈宝露
 */
public class Gongys {
	private Long id;
	
	private String xuh;
	
	private String bianm;
	
	private String mingc;
	
	private String quanc;
	
	private String piny;
	
	private String meikxxb_id;
	
	private String shengfb_id;
	
	private String shangjgsbm;
	
	private int zhuangt;
	
	private String wenjmc;
	
	private String faddbr;
	
	private String youzbm;

	private String dianh;
	
	private String kaihyh;
	
	private String zhangh;
	
	private String shuih;
	
	private String chuanz;
	
	private String danwdz;
	
	
	
	
	
	public String getDanwdz() {
		return danwdz;
	}

	public void setDanwdz(String danwdz) {
		this.danwdz = danwdz;
	}

	public String getFaddbr() {
		return faddbr;
	}

	public void setFaddbr(String faddbr) {
		this.faddbr = faddbr;
	}

	public String getYouzbm() {
		return youzbm;
	}

	public void setYouzbm(String youzbm) {
		this.youzbm = youzbm;
	}

	public String getDianh() {
		return dianh;
	}

	public void setDianh(String dianh) {
		this.dianh = dianh;
	}

	public String getKaihyh() {
		return kaihyh;
	}

	public void setKaihyh(String kaihyh) {
		this.kaihyh = kaihyh;
	}

	public String getZhangh() {
		return zhangh;
	}

	public void setZhangh(String zhangh) {
		this.zhangh = zhangh;
	}

	public String getShuih() {
		return shuih;
	}

	public void setShuih(String shuih) {
		this.shuih = shuih;
	}

	public String getChuanz() {
		return chuanz;
	}

	public void setChuanz(String chuanz) {
		this.chuanz = chuanz;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBianm() {
		return bianm;
	}

	public void setBianm(String bianm) {
		this.bianm = bianm;
	}

	public String getMingc() {
		return mingc;
	}

	public void setMingc(String mingc) {
		this.mingc = mingc;
	}

	public String getQuanc() {
		return quanc;
	}

	public void setQuanc(String quanc) {
		this.quanc = quanc;
	}

	public String getPiny() {
		return piny;
	}

	public void setPiny(String piny) {
		this.piny = piny;
	}

	public String getMeikxxb_id() {
		return meikxxb_id;
	}

	public void setMeikxxb_id(String meikxxb_id) {
		this.meikxxb_id = meikxxb_id;
	}

	public String getShengfb_id() {
		return shengfb_id;
	}

	public void setShengfb_id(String shengfb_id) {
		this.shengfb_id = shengfb_id;
	}

	public String getShangjgsbm() {
		return shangjgsbm;
	}

	public void setShangjgsbm(String shangjgsbm) {
		this.shangjgsbm = shangjgsbm;
	}

	public String getZhuangt() {
		return zhuangt==1?"启用":"停用";
	}

	public void setZhuangt(int zhuangt) {
		this.zhuangt = zhuangt;
	}

	public String getXuh() {
		return xuh;
	}

	public void setXuh(String xuh) {
		this.xuh = xuh;
	}

	public String getWenjmc() {
		return wenjmc;
	}

	public void setWenjmc(String wenjmc) {
		this.wenjmc = wenjmc;
	}
}
