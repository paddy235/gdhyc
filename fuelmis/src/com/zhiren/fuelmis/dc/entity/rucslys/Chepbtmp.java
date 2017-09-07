package com.zhiren.fuelmis.dc.entity.rucslys;


public class Chepbtmp {
    private Long id;	//ID
    private Long diancxxb_id=515L;	//电厂信息表Id
    private String piaojh;	//运单票据号
    private String gongysmc;	//供应商名称
    private String meikdwmc;	//煤款单位名称
    private String pinz;	//燃料品种表
    private String faz;	//发站
    private String daoz;	//到站
    private String jihkj;	//计划口径
    private String fahrq;		//发货日期
    private String daohrq;	//到货日期
    private Long hetb_id;	//合同表_Id
    private Long zhilb_id;	//质量表id
    private String caiybh;	//采样编号
    private Long jiesb_id;	//结算表id
    private String yunsfs;	//运输方式(公路、铁路、船运)
    private String chec;	    //车次
    private String cheph;	//车皮号
    private Double maoz;	    //毛重
    private Double piz;	    //皮重
    private Double biaoz;	//票重
    private Double yingd;	//盈吨
    private Double kuid;
    private Double yingk;	//盈亏
    private Double yuns;	    //运损
    private Double yunsl;	//运损率
    private Double koud;	//扣吨
    private Double kous;	//扣水
    private Double kouz;	//扣杂
    private Double sanfsl;	//三方数量(汽车煤的煤管站量及海运的港口数量)
    private Double ches;	//车速
    private String jianjfs;	//'过衡'		检斤方式(过衡、检尺)
    private Long guohb_id;	//过衡表_id
    private Long fahb_id;	//发货表_id
    private Long fahbtmp_id;	//发货表tmp_id
    private Long chebb_id;	//车别（路车,自备,汽,船）
    private String yuandz;	//原站
    private String yuanshdw;	//原收货单位
    private Long kuangfzlb_id;	//矿方质量表id
    private String yuanmkdw;	//原煤矿单位
    private String yunsdwb_id;	//运输单位
    private String qingcsj;		//轻车时间(汽车衡检皮)
    private String qingchh;	//		轻车衡号
    private String qingcjjy;	//		轻车检斤员
    private String zhongcsj;		//重车时间(汽车衡检毛)
    private String zhongchh;	//重车衡号
    private String zhongcjjy;	//重车检斤员
    private Long meicb_id;	//煤场表_id
    private String daozch;	//倒装车号
    private String lury;	//录入员
    private String beiz;	//备注
    private String caiyrq;		//采样日期
    private String lursj;		//录入时间
    private String yunsdw;		//
    private String xiecfs;	//卸车方式
    private Double yuanmz;	//
    private Double yuanpz;	//
    private String meigy;		//	煤管员
    private String daorsj;		//		导入人员
    private String daorry;	//		导入时间
    private String truckenter_id;
    private String chuanrsj;
    private String zhuangt;
    private String caozlx;//操作标志
    private String daorczr;
    private Long lsbid;
    private String meic;//煤厂
    private String chedhm;//车队号码
    private String koudyy;//扣吨原因
    private String meiczjy;//煤厂质检员
    private String koudzjy;//扣吨质检员
    private String xianshdw;//现收货单位
    private String gengxsj;//更新时间
    private Double jingz;//净重（单位：吨，减过扣吨的值）
    private Double kuangfmz;//矿方毛重
    private Double kuangfpz;//矿方皮重
    private Double kuangfjz;//矿方净重

    public String getChedhm() {
        return chedhm;
    }

    public void setChedhm(String chedhm) {
        this.chedhm = chedhm;
    }

    public String getKoudyy() {
        return koudyy;
    }

    public void setKoudyy(String koudyy) {
        this.koudyy = koudyy;
    }

    public String getMeiczjy() {
        return meiczjy;
    }

    public void setMeiczjy(String meiczjy) {
        this.meiczjy = meiczjy;
    }

    public String getKoudzjy() {
        return koudzjy;
    }

    public void setKoudzjy(String koudzjy) {
        this.koudzjy = koudzjy;
    }

    public String getXianshdw() {
        return xianshdw;
    }

    public void setXianshdw(String xianshdw) {
        this.xianshdw = xianshdw;
    }

    public String getGengxsj() {
        return gengxsj;
    }

    public void setGengxsj(String gengxsj) {
        this.gengxsj = gengxsj;
    }

    public Double getJingz() {
        return jingz;
    }

    public void setJingz(Double jingz) {
        this.jingz = jingz;
    }

    public Double getKuangfmz() {
        return kuangfmz;
    }

    public void setKuangfmz(Double kuangfmz) {
        this.kuangfmz = kuangfmz;
    }

    public Double getKuangfpz() {
        return kuangfpz;
    }

    public void setKuangfpz(Double kuangfpz) {
        this.kuangfpz = kuangfpz;
    }

    public Double getKuangfjz() {
        return kuangfjz;
    }

    public void setKuangfjz(Double kuangfjz) {
        this.kuangfjz = kuangfjz;
    }

    public String getMeic() {
        return meic;
    }
    public void setMeic(String meic) {
        this.meic = meic;
    }
    public Double getKuid() {
        return kuid;
    }
    public void setKuid(Double kuid) {
        this.kuid = kuid;
    }
    public Long getLsbid() {
        return lsbid;
    }
    public void setLsbid(Long lsbid) {
        this.lsbid = lsbid;
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
    public String getPiaojh() {
        return piaojh;
    }
    public void setPiaojh(String piaojh) {
        this.piaojh = piaojh;
    }
    public String getGongysmc() {
        return gongysmc;
    }
    public void setGongysmc(String gongysmc) {
        this.gongysmc = gongysmc;
    }
    public String getMeikdwmc() {
        return meikdwmc;
    }
    public void setMeikdwmc(String meikdwmc) {
        this.meikdwmc = meikdwmc;
    }
    public String getPinz() {
        return pinz;
    }
    public void setPinz(String pinz) {
        this.pinz = pinz;
    }
    public String getFaz() {
        return faz;
    }
    public void setFaz(String faz) {
        this.faz = faz;
    }
    public String getDaoz() {
        return daoz;
    }
    public void setDaoz(String daoz) {
        this.daoz = daoz;
    }
    public String getJihkj() {
        return jihkj;
    }
    public void setJihkj(String jihkj) {
        this.jihkj = jihkj;
    }
    public String getFahrq() {
        return fahrq;
    }
    public void setFahrq(String fahrq) {
        this.fahrq = fahrq;
    }
    public String getDaohrq() {
        return daohrq;
    }
    public void setDaohrq(String daohrq) {
        this.daohrq = daohrq;
    }
    public Long getHetb_id() {
        return hetb_id;
    }
    public void setHetb_id(Long hetb_id) {
        this.hetb_id = hetb_id;
    }
    public Long getZhilb_id() {
        return zhilb_id;
    }
    public void setZhilb_id(Long zhilb_id) {
        this.zhilb_id = zhilb_id;
    }
    public String getCaiybh() {
        return caiybh;
    }
    public void setCaiybh(String caiybh) {
        this.caiybh = caiybh;
    }
    public Long getJiesb_id() {
        return jiesb_id;
    }
    public void setJiesb_id(Long jiesb_id) {
        this.jiesb_id = jiesb_id;
    }
    public String getYunsfs() {
        return yunsfs;
    }
    public void setYunsfs(String yunsfs) {
        this.yunsfs = yunsfs;
    }
    public String getChec() {
        return chec;
    }
    public void setChec(String chec) {
        this.chec = chec;
    }
    public String getCheph() {
        return cheph;
    }
    public void setCheph(String cheph) {
        this.cheph = cheph;
    }
    public Double getMaoz() {
        return maoz;
    }
    public void setMaoz(Double maoz) {
        this.maoz = maoz;
    }
    public Double getPiz() {
        return piz;
    }
    public void setPiz(Double piz) {
        this.piz = piz;
    }
    public Double getBiaoz() {
        return biaoz;
    }
    public void setBiaoz(Double biaoz) {
        this.biaoz = biaoz;
    }
    public Double getYingd() {
        return yingd;
    }
    public void setYingd(Double yingd) {
        this.yingd = yingd;
    }
    public Double getYingk() {
        return yingk;
    }
    public void setYingk(Double yingk) {
        this.yingk = yingk;
    }
    public Double getYuns() {
        return yuns;
    }
    public void setYuns(Double yuns) {
        this.yuns = yuns;
    }
    public Double getYunsl() {
        return yunsl;
    }
    public void setYunsl(Double yunsl) {
        this.yunsl = yunsl;
    }
    public Double getKoud() {
        return koud;
    }
    public void setKoud(Double koud) {
        this.koud = koud;
    }
    public Double getKous() {
        return kous;
    }
    public void setKous(Double kous) {
        this.kous = kous;
    }
    public Double getKouz() {
        return kouz;
    }
    public void setKouz(Double kouz) {
        this.kouz = kouz;
    }
    public Double getSanfsl() {
        return sanfsl;
    }
    public void setSanfsl(Double sanfsl) {
        this.sanfsl = sanfsl;
    }
    public Double getChes() {
        return ches;
    }
    public void setChes(Double ches) {
        this.ches = ches;
    }
    public String getJianjfs() {
        return jianjfs;
    }
    public void setJianjfs(String jianjfs) {
        this.jianjfs = jianjfs;
    }
    public Long getGuohb_id() {
        return guohb_id;
    }
    public void setGuohb_id(Long guohb_id) {
        this.guohb_id = guohb_id;
    }
    public Long getFahb_id() {
        return fahb_id;
    }
    public void setFahb_id(Long fahb_id) {
        this.fahb_id = fahb_id;
    }
    public Long getFahbtmp_id() {
        return fahbtmp_id;
    }
    public void setFahbtmp_id(Long fahbtmp_id) {
        this.fahbtmp_id = fahbtmp_id;
    }
    public Long getChebb_id() {
        return chebb_id;
    }
    public void setChebb_id(Long chebb_id) {
        this.chebb_id = chebb_id;
    }
    public String getYuandz() {
        return yuandz;
    }
    public void setYuandz(String yuandz) {
        this.yuandz = yuandz;
    }
    public String getYuanshdw() {
        return yuanshdw;
    }
    public void setYuanshdw(String yuanshdw) {
        this.yuanshdw = yuanshdw;
    }
    public Long getKuangfzlb_id() {
        return kuangfzlb_id;
    }
    public void setKuangfzlb_id(Long kuangfzlb_id) {
        this.kuangfzlb_id = kuangfzlb_id;
    }
    public String getYuanmkdw() {
        return yuanmkdw;
    }
    public void setYuanmkdw(String yuanmkdw) {
        this.yuanmkdw = yuanmkdw;
    }
    public String getYunsdwb_id() {
        return yunsdwb_id;
    }
    public void setYunsdwb_id(String yunsdwb_id) {
        this.yunsdwb_id = yunsdwb_id;
    }
    public String getQingcsj() {
        return qingcsj;
    }
    public void setQingcsj(String qingcsj) {
        this.qingcsj = qingcsj;
    }
    public String getQingchh() {
        return qingchh;
    }
    public void setQingchh(String qingchh) {
        this.qingchh = qingchh;
    }
    public String getQingcjjy() {
        return qingcjjy;
    }
    public void setQingcjjy(String qingcjjy) {
        this.qingcjjy = qingcjjy;
    }
    public String getZhongcsj() {
        return zhongcsj;
    }
    public void setZhongcsj(String zhongcsj) {
        this.zhongcsj = zhongcsj;
    }
    public String getZhongchh() {
        return zhongchh;
    }
    public void setZhongchh(String zhongchh) {
        this.zhongchh = zhongchh;
    }
    public String getZhongcjjy() {
        return zhongcjjy;
    }
    public void setZhongcjjy(String zhongcjjy) {
        this.zhongcjjy = zhongcjjy;
    }
    public Long getMeicb_id() {
        return meicb_id;
    }
    public void setMeicb_id(Long meicb_id) {
        this.meicb_id = meicb_id;
    }
    public String getDaozch() {
        return daozch;
    }
    public void setDaozch(String daozch) {
        this.daozch = daozch;
    }
    public String getLury() {
        return lury;
    }
    public void setLury(String lury) {
        this.lury = lury;
    }
    public String getBeiz() {
        return beiz;
    }
    public void setBeiz(String beiz) {
        this.beiz = beiz;
    }
    public String getCaiyrq() {
        return caiyrq;
    }
    public void setCaiyrq(String caiyrq) {
        this.caiyrq = caiyrq;
    }
    public String getLursj() {
        return lursj;
    }
    public void setLursj(String lursj) {
        this.lursj = lursj;
    }
    public String getYunsdw() {
        return yunsdw;
    }
    public void setYunsdw(String yunsdw) {
        this.yunsdw = yunsdw;
    }
    public String getXiecfs() {
        return xiecfs;
    }
    public void setXiecfs(String xiecfs) {
        this.xiecfs = xiecfs;
    }
    public Double getYuanmz() {
        return yuanmz;
    }
    public void setYuanmz(Double yuanmz) {
        this.yuanmz = yuanmz;
    }
    public Double getYuanpz() {
        return yuanpz;
    }
    public void setYuanpz(Double yuanpz) {
        this.yuanpz = yuanpz;
    }
    public String getMeigy() {
        return meigy;
    }
    public void setMeigy(String meigy) {
        this.meigy = meigy;
    }
    public String getDaorsj() {
        return daorsj;
    }
    public void setDaorsj(String daorsj) {
        this.daorsj = daorsj;
    }
    public String getDaorry() {
        return daorry;
    }
    public void setDaorry(String daorry) {
        this.daorry = daorry;
    }
    public String getTruckenter_id() {
        return truckenter_id;
    }
    public void setTruckenter_id(String truckenter_id) {
        this.truckenter_id = truckenter_id;
    }
    public String getChuanrsj() {
        return chuanrsj;
    }
    public void setChuanrsj(String chuanrsj) {
        this.chuanrsj = chuanrsj;
    }
    public String getZhuangt() {
        return zhuangt;
    }
    public void setZhuangt(String zhuangt) {
        this.zhuangt = zhuangt;
    }
    public String getCaozlx() {
        return caozlx;
    }
    public void setCaozlx(String caozlx) {
        this.caozlx = caozlx;
    }
    public String getDaorczr() {
        return daorczr;
    }
    public void setDaorczr(String daorczr) {
        this.daorczr = daorczr;
    }




}
