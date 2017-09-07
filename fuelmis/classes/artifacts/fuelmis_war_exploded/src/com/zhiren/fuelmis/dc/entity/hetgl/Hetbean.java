package com.zhiren.fuelmis.dc.entity.hetgl;

/**
 * 燃料合同
 * @author ZY
 *
 */
public class Hetbean {
	private Long id; //主键
	private Long diancxxb_id; //电厂信息表id
	private String hetbh; //合同编号
	private String qiandrq; //签订日期
	private String qianddd; //签订地点
	private String qisrq;  //起始日期
	private String guoqrq;  //过期日期	
	private Long jihkj_id;  //计划口径id
	private Double hetl;    //合同量
	private String fujmc;   //附件名称
	private Long gongf;     //供方
	private Long xuf;       //需方
	private int zhuangt;    //状态
	private Long lurry;     //录入人员
	private Double hetjg;   //合同价格

	private Long fujxxId;    //附件id
	
	private int sanj_zt;     //三级审批状态
	
	
	private Double jiag;     //合同价格
	private String jiag_dx;   //价格大写
	private Long pinz_id;    //品种id
	
	private Long mubid;      //模板id
	
	public Long getPinz_id() {
		return pinz_id;
	}
	public void setPinz_id(Long pinz_id) {
		this.pinz_id = pinz_id;
	}
	public Long getMubid() {
		return mubid;
	}
	public void setMubid(Long mubid) {
		this.mubid = mubid;
	}
	public Double getJiag() {
		return jiag;
	}
	public void setJiag(Double jiag) {
		this.jiag = jiag;
	}
	public String getJiag_dx() {
		return jiag_dx;
	}
	public void setJiag_dx(String jiag_dx) {
		this.jiag_dx = jiag_dx;
	}
	public int getSanj_zt() {
		return sanj_zt;
	}
	public void setSanj_zt(int sanj_zt) {
		this.sanj_zt = sanj_zt;
	}
	
	public Long getFujxxId() {
		return fujxxId;
	}
	public void setFujxxId(Long fujxxId) {
		this.fujxxId = fujxxId;
	}

	
	
	public Double getHetjg() {
		return hetjg;
	}
	public void setHetjg(Double hetjg) {
		this.hetjg = hetjg;
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
	public String getHetbh() {
		return hetbh;
	}
	public void setHetbh(String hetbh) {
		this.hetbh = hetbh;
	}
	public String getQiandrq() {
		return qiandrq;
	}
	public void setQiandrq(String qiandrq) {
		this.qiandrq = qiandrq;
	}
	public String getQianddd() {
		return qianddd;
	}
	public void setQianddd(String qianddd) {
		this.qianddd = qianddd;
	}

	public String getQisrq() {
		return qisrq;
	}
	public void setQisrq(String qisrq) {
		this.qisrq = qisrq;
	}
	public String getGuoqrq() {
		return guoqrq;
	}
	public void setGuoqrq(String guoqrq) {
		this.guoqrq = guoqrq;
	}
	public Long getJihkj_id() {
		return jihkj_id;
	}
	public void setJihkj_id(Long jihkj_id) {
		this.jihkj_id = jihkj_id;
	}
	public Double getHetl() {
		return hetl;
	}
	public void setHetl(Double hetl) {
		this.hetl = hetl;
	}
	public String getFujmc() {
		return fujmc;
	}
	public void setFujmc(String fujmc) {
		this.fujmc = fujmc;
	}
	public Long getGongf() {
		return gongf;
	}
	public void setGongf(Long gongf) {
		this.gongf = gongf;
	}
	public Long getXuf() {
		return xuf;
	}
	public void setXuf(Long xuf) {
		this.xuf = xuf;
	}
	
}
