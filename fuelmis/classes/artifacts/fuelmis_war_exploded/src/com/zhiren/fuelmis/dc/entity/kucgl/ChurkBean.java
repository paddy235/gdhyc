package com.zhiren.fuelmis.dc.entity.kucgl;

import com.zhiren.fuelmis.dc.utils.Sequence;

/**
 * Created by Administrator on 2016/5/27.
 */
public class ChurkBean {
    private String id = Sequence.nextId();
    private String jizfl;//记账分类 期初 入库  出库
    private String jizsm;//记账说明 结算入库 杂费结算入库  生产消耗
    private Long huoz;
    private Long kuczz;
    private Long kucwz;
    private Long kucwl;
    private Double shul = 0.0;//数量
    private Double jine = 0.0;//金额
    private Double danwcb = 0.0;//单位成本
    private Double zangsl = 0.0;//暂估数量
    private Double zangje = 0.0;//暂估金额
    private Double zangdj = 0.0;//暂估单价
    private Double jieysl = 0.0;//结余数量
    private Double jieyje = 0.0;//结余金额
    private Double jieycb = 0.0;//结余单位成本
    private Double shismcsl = 0.0;//实时煤场数量
    private Double shismcje = 0.0;//实时煤场金额
    private Double shismccb = 0.0;//实时煤场单位成本
    private String jizrq;//记账日期
    private String jilsj;//记录时间
    private String riq;
    public String getRiq() {
		return riq;
	}

	public void setRiq(String riq) {
		this.riq = riq;
	}

	private int yewlx;
    private Long zuz ;
    public Long getZuz() {
		return zuz;
	}

	public void setZuz(Long zuz) {
		this.zuz = zuz;
	}

	public int getYewlx() {
		return yewlx;
	}

	public void setYewlx(int yewlx) {
		this.yewlx = yewlx;
	}

	public String getJilsj() {
        return jilsj;
    }

    public void setJilsj(String jilsj) {
        this.jilsj = jilsj;
    }

    public Double getShismccb() {
        return shismccb;
    }

    public void setShismccb(Double shismccb) {
        this.shismccb = shismccb;
    }

    public Long getHuoz() {

        return huoz;
    }

    public void setHuoz(Long huoz) {
        this.huoz = huoz;
    }

    public Long getKuczz() {
        return kuczz;
    }

    public void setKuczz(Long kuczz) {
        this.kuczz = kuczz;
    }

    public Long getKucwz() {
        return kucwz;
    }

    public void setKucwz(Long kucwz) {
        this.kucwz = kucwz;
    }

    public Long getKucwl() {
        return kucwl;
    }

    public void setKucwl(Long kucwl) {
        this.kucwl = kucwl;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJizrq() {
        return jizrq;
    }

    public void setJizrq(String jizrq) {
        this.jizrq = jizrq;
    }

    public String getJizfl() {
        return jizfl;
    }

    public void setJizfl(String jizfl) {
        this.jizfl = jizfl;
    }

    public String getJizsm() {
        return jizsm;
    }

    public void setJizsm(String jizsm) {
        this.jizsm = jizsm;
    }

    public Double getShul() {
        return shul;
    }

    public void setShul(Double shul) {
        this.shul = shul;
    }

    public Double getJine() {
        return jine;
    }

    public void setJine(Double jine) {
        this.jine = jine;
    }

    public Double getDanwcb() {
        return danwcb;
    }

    public void setDanwcb(Double danwcb) {
        this.danwcb = danwcb;
    }

    public Double getZangsl() {
        return zangsl;
    }

    public void setZangsl(Double zangsl) {
        this.zangsl = zangsl;
    }

    public Double getZangje() {
        return zangje;
    }

    public void setZangje(Double zangje) {
        this.zangje = zangje;
    }

    public Double getZangdj() {
        return zangdj;
    }

    public void setZangdj(Double zangdj) {
        this.zangdj = zangdj;
    }

    public Double getJieysl() {
        return jieysl;
    }

    public void setJieysl(Double jieysl) {
        this.jieysl = jieysl;
    }

    public Double getJieyje() {
        return jieyje;
    }

    public void setJieyje(Double jieyje) {
        this.jieyje = jieyje;
    }

    public Double getJieycb() {
        return jieycb;
    }

    public void setJieycb(Double jieycb) {
        this.jieycb = jieycb;
    }

    public Double getShismcsl() {
        return shismcsl;
    }

    public void setShismcsl(Double shismcsl) {
        this.shismcsl = shismcsl;
    }

    public Double getShismcje() {
        return shismcje;
    }

    public void setShismcje(Double shismcje) {
        this.shismcje = shismcje;
    }

    @Override
    public String toString() {
        return "{" +
                "id:'" + id + '\'' +
                ", jizfl:'" + jizfl + '\'' +
                ", jizsm:'" + jizsm + '\'' +
                ", huoz:" + huoz +
                ", kuczz:" + kuczz +
                ", kucwz:" + kucwz +
                ", kucwl:" + kucwl +
                ", shul:" + shul +
                ", jine:" + jine +
                ", danwcb:" + danwcb +
                ", zangsl:" + zangsl +
                ", zangje:" + zangje +
                ", zangdj:" + zangdj +
                ", jieysl:" + jieysl +
                ", jieyje:" + jieyje +
                ", jieycb:" + jieycb +
                ", shismcsl:" + shismcsl +
                ", shismcje:" + shismcje +
                ", shismccb:" + shismccb +
                ", jizrq:'" + jizrq + '\'' +
                ", jilsj:'" + jilsj + '\'' +
                '}'+"\n";
    }
}
