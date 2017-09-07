package com.zhiren.fuelmis.dc.scheduler;

import java.nio.DoubleBuffer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.zhiren.fuelmis.dc.utils.math.Math;
import com.zhiren.fuelmis.dc.service.kucgl.rukgl.IZidrkService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.zhiren.fuelmis.dc.entity.hetgl.PriceBean;
import com.zhiren.fuelmis.dc.service.hetgl.Icalculator;
import com.zhiren.fuelmis.dc.service.impl.jiesgl.MeikjsServiceImpl;
import com.zhiren.fuelmis.dc.utils.Sequence;
import com.zhiren.fuelmis.dc.utils.StaticApplication;

/**
 * @author 陈宝露
 */
@Component("zhilrkJob")
public class ZhilrkJob {


    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private IZidrkService zidrkService;

    private static Logger logger = LogManager.getLogger(MeikjsServiceImpl.class);

    private SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");

    private DecimalFormat df = new DecimalFormat("0.00");
    private List<String> eRukdList=new ArrayList<String>();
    public void execute() {
        logger.info("*****************查询可结算入库单开始*********************");
        List<Map<String, Object>> lst = jdbcTemplate
                .queryForList("SELECT DISTINCT RUKDBH\n" +
                        " FROM rl_kc_churkdb r,\n" +
                        " (SELECT rukdbh AS rukdbh_gx,\n" +
                        " hangh AS hangh_gx,\n" +
                        " min(decode(g.yuandjlx, 1, g.yuandj_id, '')) CHEPB_ID,\n" +
                        " min(decode(g.yuandjlx, 2, g.yuandj_id, '')) CAIGDDB_ID\n" +
                        " FROM gx_churkdb_yunsdj g\n" +
                        " GROUP BY rukdbh, hangh) b, GX_JIESDB_CHEPB gx\n" +
                        " WHERE r.rukdbh = b.rukdbh_gx\n" +
                        " AND r.hangh = b.hangh_gx\n" +
                        " AND b.CHEPB_ID = gx.chepb_id (+)\n" +
                        " AND gx.chepb_id IS NULL\n" +
                        " AND r.YEWLX = 1\n" +
                        " AND r.ZHUANGT = 1\n" +
                        " AND b.chepb_id IN (SELECT id FROM vm_chepmx cp WHERE cp.huayshzt =2 ) \n");
        logger.info("*****************可结算入库单总数为:"+lst.size()+"*********************");
        this.rijs(lst);
        logger.info("====================日结算结束====================");
        logger.info("不能入库的入库单："+eRukdList.toString());
    }

    public void rijs(List<Map<String, Object>> lst) {
        for (Map<String,Object> rukdMap:lst) {
            try{
                this.calculatePrice(rukdMap.get("RUKDBH").toString());
            }catch (Exception e){
                e.printStackTrace();
                eRukdList.add(rukdMap.get("RUKDBH").toString());
            }
        }
    }

    private void calculatePrice(String rukdbh) {
        //查询车皮信息by rukdbh
        String sql = "SELECT round(sum(c.maoz-c.piz-c.KOUD),2) jiessl,to_char(sysdate,'yyyy-mm-dd') caozrq,c.meikxxb_id,c.jihkjb_id, c.gongysb_id,c.faz_id,c.PINZB_ID,substr(q.ZHONGCSJ,0,10) zhongcsj,substr(q.qingcsj,0,10) qingcsj,c.DIANCXXB_ID,\n" +
                "round(sum(c.piaoz),2) piaoz,count(c.id) ches,min(c.CHEPH)||'-'||max(c.cheph) daibch,\n" +
                "  round(sum(c.yingd),2) yingd,round(sum(c.kuid),2) kuid,round(sum(c.maoz-c.piz-c.KOUD),2) jingz\n" +
                "FROM RL_YS_CHEPB c\n" +
                "  INNER JOIN GX_CHURKDB_YUNSDJ g\n" +
                "    ON c.id = g.YUANDJ_ID AND g.YUANDJLX = 1\n" +
                "  INNER JOIN RL_YS_CHEPB_QC q\n" +
                "  ON c.id=q.CHEPB_ID\n" +
                "WHERE g.RUKDBH = '" + rukdbh + "'\n" +
                "GROUP BY c.gongysb_id,c.faz_id,c.PINZB_ID,substr(q.ZHONGCSJ,0,10),substr(q.qingcsj,0,10),c.DIANCXXB_ID,c.JIHKJB_ID,c.MEIKXXB_ID";
        Map<String, Object> jiesdMap = jdbcTemplate.queryForMap(sql);
        String jiesdid = Sequence.nextId();
        jiesdMap.put("ID",jiesdid);
        //生成结算单编号
        jiesdMap.put("JIESBH", this.getJisdbh());
        //查询化验单
        sql = "select ID,HUAYD_ID,HUAYSJ,HUAYY,LURY, AAR,AD,VDAF,MT,STAD,AAD,MAD,QBAD,HAD,VAD,FCAD,STD,QGRAD,HDAF,QGRAD_DAF,QNET_AR as QNET_AR, \n"
        	+ "       SDAF,VAR, HAR,QGRD,STAR,Round_new(QNET_AR / 0.0041816, 0) as QNET_AR_DK,HUAYBM,ZUZBM, VD,ADAF,FCAR,FCD,FCDAF,QGRAR \n"
        	+ " from RL_HY_HUAYDB where huaybm in (\n" 
        	+ " 	select DISTINCT  b.HUAYBM from VM_CAIZHBM b INNER JOIN RL_YS_CHEPB c on b.CAIYBM=c.SAMCODE\n" 
        	+ "		INNER JOIN GX_CHURKDB_YUNSDJ g on g.YUANDJ_ID=c.id and g.YUANDJLX=1 where RUKDBH='" + rukdbh + "') \n";
        Map<String, Object> huaydMap = jdbcTemplate.queryForMap(sql);
        //查询采购订单
        sql = " SELECT DISTINCT s.id\n" +
                "FROM GX_CHURKDB_YUNSDJ g\n" +
                "  INNER JOIN RL_HT_CAIGDDB_SUB s\n" +
                "    ON g.YUANDJ_ID = s.CAIGDDB_ID\n" +
                "       AND g.YUANDJLX = 2\n" +
                "WHERE g.RUKDBH = '" + rukdbh + "'";
        String caigddbSubId = jdbcTemplate.queryForObject(sql, String.class);
        List<Map<String, Object>> zengkmxList = new ArrayList<Map<String, Object>>();
        //查询类名
        sql = 
        		"select DISTINCT  classname, e.id scheme_id , upper(it.code)  as kaohzb \n" +
        				"  from RL_HT_PRICE_SCHEME e, rl_ht_price_item it ,RL_HT_PRICE_COMPONENT m\n" + 
        				" where e.price_item_id = it.id\n" + 
        				"   and e.price_component_id = m.id\n" + 
        				"   and it.leix = 1\n" + 
        				"   and PO_SUB_ID =" + caigddbSubId; 
        
        List<Map<String,Object>> schemeList = jdbcTemplate.queryForList(sql);
        double zengkjg = 0;
        for (Map<String,Object> scheme : schemeList) {
        	huaydMap.put("HETKHZB", scheme.get("KAOHZB").toString());
            Icalculator calculator = (Icalculator) StaticApplication.getBean(scheme.get("CLASSNAME").toString());
            PriceBean priceBean = calculator.computePrice(scheme.get("SCHEME_ID").toString(), huaydMap);
            //存储日结算增扣明细
            Map<String, Object> zengkmx = new HashMap<String, Object>();
            zengkmx.put("ID", Sequence.nextId());
            zengkmx.put("RIJSDB_ID", jiesdid);
            zengkmx.put("ZHIBMC", priceBean.getType());
            zengkmx.put("ZHIBZ", huaydMap.get(priceBean.getType()));
            zengkmx.put("ZENGKJG", priceBean.getPriceChange());
            zengkmx.put("GONGS", priceBean.getFormula()==null ? " ":priceBean.getFormula() );
            zengkjg += priceBean.getPrice();
            zengkmxList.add(zengkmx);
        }
        //查询合同
        sql = "SELECT DISTINCT h.HETBH,h.JIAG hetjg FROM RL_HT_HETB h\n" +
                "  INNER JOIN RL_HT_CAIGDDB c ON h.id=c.HETB_ID\n" +
                "  INNER JOIN GX_CHURKDB_YUNSDJ g ON c.ID=g.YUANDJ_ID AND g.YUANDJLX=2\n" +
                "WHERE g.RUKDBH='" + rukdbh + "'";
        Map<String, Object> hetMap = jdbcTemplate.queryForMap(sql);
        jiesdMap.putAll(hetMap);
        double jiesjg= Math.round(zengkjg+ Double.parseDouble(hetMap.get("hetjg").toString()),2) ;
        jiesdMap.put("JIESJG",jiesjg);
        jiesdMap.put("JIESJE", jiesjg*Double.parseDouble(jiesdMap.get("JIESSL").toString()));
        sql="select DISTINCT  YUANDJ_ID id,YUANDJ_ID chepb_id,"+jiesdid+" jiesdb_id from GX_CHURKDB_YUNSDJ where YUANDJLX=1 and RUKDBH='"+rukdbh+"'";
        List<Map<String,Object>> gxList=jdbcTemplate.queryForList(sql);
        zidrkService.updateDatas(jiesdMap,zengkmxList,gxList);

    }

    private String getJisdbh() {
        String jiesbh = "GD-JS-dsrd-" + sdf.format(new Date()) + "-";
        int xuh = 0;
        List<Map<String, Object>> xuhList = jdbcTemplate.queryForList(
                "select nvl(max(substr(jiesbh,19,21)),0)+1 xuh from RL_JS_RIJSDB where substr(jiesbh,0,18)='"
                        + jiesbh + "'");
        if (xuhList.size() != 0) {
            xuh = Integer.parseInt(xuhList.get(0).get("XUH").toString());
        }
        if (xuh < 10) {
            jiesbh += "00" + xuh;
        } else if (xuh < 100) {
            jiesbh += "0" + xuh;
        } else {
            jiesbh += xuh;
        }
        return jiesbh;
    }


}
