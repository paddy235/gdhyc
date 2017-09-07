package com.zhiren.fuelmis.dc.service.impl.yuebgl.yuebwh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.Sequence;
import com.zhiren.fuelmis.dc.utils.math.Math;
import com.zhiren.fuelmis.dc.dao.yuebgl.yuebwh.HaocmxDao;
import com.zhiren.fuelmis.dc.service.impl.yuebgl.YuebService;
import com.zhiren.fuelmis.dc.service.yuebgl.yuebwh.IHaocmxService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * @author 刘志宇
 */
@Service
public class HaocmxServiceImpl extends YuebService implements IHaocmxService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private HaocmxDao haocmxDao;

    @Override
    public JSONObject getAll(Map<String, Object> map) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();

        List<Map<String, Object>> list = haocmxDao.getAll(map);
        jsonMap.put("data", list);

        JSONObject result = JSONObject.fromObject(jsonMap);
        return result;
    }

    @Override@Transactional
    public int createData(Map<String, Object> map) {
        this.delData(map);
        String diancxxb_id = map.get("dianc").toString();
        String strDate =map.get("riq").toString();
        String CurrODate =map.get("riq").toString();//
        String CurrZnDate= DateUtil.getLastMonthString(CurrODate);//

        int intYuef=Integer.parseInt(strDate.substring(5,7));//Integer.parseInt(strDate[1]);
        String strshouml = "jingz";//"biaoz + yingd - kuid - yuns";
        StringBuffer sb = new StringBuffer();

        sb.append("select\n" ).append(Sequence.nextId()).append(",k.fenx,k.yuetjkjb_id, nvl(k.qickc,0) qickc,k.shouml,k.fady,k.gongry,k.qith,k.sunh,k.diaocl,k.panyk,k.shuifctz,k.jitcs,k.kuc from ")
                .append("(select ")
                .append(" rownum,hz.id yuetjkjb_id,hz.fenx,");
        if(intYuef == 1) {
            sb.append("nvl(h.kuc,0) qickc,nvl(")
                    .append(strshouml).append(",0) shouml,\n")
                    .append("0 fady,0 gongry,0 qith,0 sunh,0 diaocl,0 panyk,0 shuifctz,0 jitcs,0 kuc\n");
        }else {
            sb.append("decode(hz.fenx,'本月',nvl(h.kuc, 0) ,getYuehcmxQckclj(hz.id ,'"+CurrZnDate+"') )as  qickc,");
            sb.append("nvl("+strshouml).append(",0) shouml,\n");
            sb.append("nvl(decode(hz.fenx,'本月',0,h.fady),0) fady, nvl(decode(hz.fenx,'本月',0,h.gongry),0) gongry,\n");
            sb.append("nvl(decode(hz.fenx,'本月',0,h.qith),0) qith, nvl(decode(hz.fenx,'本月',0,h.sunh),0) sunh,\n");
            sb.append("nvl(decode(hz.fenx,'本月',0,h.diaocl),0) diaocl, nvl(decode(hz.fenx,'本月',0,h.panyk),0) panyk,\n");
            sb.append("nvl(decode(hz.fenx,'本月',0,h.shuifctz),0) shuifctz,nvl(decode(hz.fenx,'本月',0,h.jitcs),0) jitcs,\n");
            sb.append("nvl(decode(hz.fenx,'本月',h.kuc,h.qickc),0) +nvl("+strshouml+",0) -nvl(decode(hz.fenx,'本月',0,h.fady),0) - nvl(decode(hz.fenx,'本月',0,h.gongry),0)-nvl(decode(hz.fenx,'本月',0,h.qith),0) -nvl(decode(hz.fenx,'本月',0,h.diaocl),0) + nvl(decode(hz.fenx,'本月',0,h.panyk),0)+nvl(decode(hz.fenx,'本月',0,h.shuifctz),0)-nvl(decode(hz.fenx,'本月',0,h.sunh),0) kuc\n");
        }
        sb.append("from (select * from yuetjkjb,(select '本月' fenx from dual union select '累计' from dual) where diancxxb_id =").append(diancxxb_id)
                .append("     and substr(riq,0,7) = '").append(CurrODate).append("' order by id,fenx) hz,yueslb s,").append(" (select y.yuetjkjb_id_new,h.* from ")
                .append("(select nvl(y.id,0) yuetjkjb_id_new,oy.yuetjkjb_id from yuetjkjb y, \n")
                .append("(select distinct t.id yuetjkjb_id,diancxxb_id,gongysb_id,jihkjb_id,pinzb_id,yunsfsb_id from yuehcb h,yuetjkjb t \n")
                .append("where h.yuetjkjb_id = t.id  and t.diancxxb_id =").append(diancxxb_id).append(" \n")//and h.kuc <>0
                .append("and substr(t.riq,0,7) = '").append(CurrZnDate).append("') oy \n").append("where y.diancxxb_id = oy.diancxxb_id and y.gongysb_id = oy.gongysb_id \n")
                .append("and y.jihkjb_id = oy.jihkjb_id and y.pinzb_id = oy.pinzb_id \n").append("and y.yunsfsb_id = oy.yunsfsb_id and substr(y.riq,0,7)='")
                .append(CurrODate).append("') y, yuehcb h \n").append("where h.yuetjkjb_id = y.yuetjkjb_id ) h\n")
                .append("where hz.id = s.yuetjkjb_id(+) and hz.id = h.yuetjkjb_id_new(+)")
                .append("and hz.fenx = s.fenx(+) and hz.fenx = h.fenx(+) order by hz.id,hz.fenx) k \n");
        System.out.println(sb.toString());
        //查询
        List<Map<String, Object>> haocmxList = jdbcTemplate.queryForList(sb.toString());
        //插入
        String sql="insert into yuehcb(id,fenx,yuetjkjb_id,qickc,shouml,fady,gongry,qith,sunh,diaocl,panyk,shuifctz,jitcs,kuc)";
        int flag=0;
        for (int i = 0; i < haocmxList.size(); i++) {
            Map<String,Object> m=haocmxList.get(i);
            String s=sql+"values("+Sequence.nextId()+",'"+m.get("FENX")+"',"+m.get("YUETJKJB_ID")+","+m.get("QICKC")+","+m.get("SHOUML")+","
                    +m.get("FADY")+","+m.get("GONGRY")+","+m.get("QITH")+","+m.get("SUNH")+","+m.get("DIAOCL")+","+m.get("PANYK")+","
                    +m.get("SHUIFCTZ")+","+m.get("JITCS")+","+m.get("KUC")+")";
            flag = jdbcTemplate.update(s);
            if(flag!=1){
                break;
            }
        }

        String updatekuc_sql = "update (select  * from yuehcb y   where yuetjkjb_id in (select id from yuetjkjb where substr(riq,0,7) ='2016-01') )\n" +
                "set kuc = qickc+shouml-fady-gongry-qith-diaocl+panyk +shuifctz -sunh";

        jdbcTemplate.update(updatekuc_sql);
        return flag;
    }

    @Override@Transactional
    public JSONArray saveData(List<Map<String,Object>> list) throws Exception {
        for (int i=2;i<list.size();i+=2) {
            Map<String,Object> beny =list.get(i);
            Map<String,Object> byleij =list.get(i+1);
            //计算本月库存
            String gongs=" QICKC + SHOUML - FADY - GONGRY - QITH - SUNH - DIAOCL + PANYK + SHUIFCTZ ";
            double kuc=Math.getGongsjg(beny,gongs);
            beny.put("KUC",kuc);
            //重新计算累计值
//            Map<String, Object> leij = haocmxDao.getLastMonthLeij(beny.get("YUETJKJB_ID").toString());
//            leij.put("ID",byleij.get("ID"));
//            leij.put("FENX","累计");
//            leij.put("YUETJKJB_ID",beny.get("YUETJKJB_ID"));
            //leij.put("KUC",byleij.get("KUC"));
//            leij.put("QICKC",byleij.get("QICKC"));
//            leij.put("SHOUML",byleij.get("SHOUML"));
            //计算累计库存
//            double leijkuc=Math.getGongsjg(leij,gongs);
//            leij.put("KUC",leijkuc);

//            for (Map.Entry<String, Object> entry : beny.entrySet()) {
//                String key=entry.getKey();
//                Object value=entry.getValue();
//                if(key.equals("FADY")||key.equals("GONGRY")
//                        ||key.equals("PANYK")||key.equals("SHUIFCTZ")
//                        ||key.equals("QITH")||key.equals("SUNH")||key.equals("DIAOCL")) {
//                    leij.put(key, Math.add(leij.get(key), value));
//                }
//            }
            //计算累计耗用
            for (Map.Entry<String, Object> entry : beny.entrySet()) {
                String key=entry.getKey();
                Object value=entry.getValue();
                if(key.equals("FADY")||key.equals("GONGRY")
                        ||key.equals("PANYK")||key.equals("SHUIFCTZ")
                        ||key.equals("QITH")||key.equals("SUNH")||key.equals("DIAOCL")) {
                    byleij.put(key, Math.add(byleij.get(key), value));
                }
            }
            //计算累计库存
            double leijkuc=Math.getGongsjg(byleij,gongs);
            byleij.put("KUC",leijkuc);
            this.saveData(beny, "YUEHCB", "yuetjkjb_id,fenx");
            this.saveData(byleij, "YUEHCB", "yuetjkjb_id,fenx");
        }
        JSONArray jarray =new JSONArray();
        jarray.add(1);
        return jarray;
    }

    @Override
    public JSONArray delData(Map<String, Object> map) {
        String diancxxb_id = map.get("dianc").toString();
        String strDate =map.get("riq").toString();
        String CurrZnDate=map.get("riq").toString();
        String CurrODate =map.get("riq").toString();
        JSONArray a=new JSONArray();
        String strSql=
                "delete from yuehcb where yuetjkjb_id in (select id from yuetjkjb where substr(riq,0,7)='"
                        +CurrODate+"' and diancxxb_id="+diancxxb_id+")";
        int flag = jdbcTemplate.update(strSql);
        a.add(flag);
        return a;
    }

    @Override
    public JSONArray check(Map<String, Object> map) {

        JSONArray jsonArray = new JSONArray();
        try{
            int count = jdbcTemplate.queryForInt("select count(1) from yuehcb where yuetjkjb_id in (select id from yuetjkjb where riq='"
                    +map.get("riq")+"' and diancxxb_id = "+map.get("dianc")+")");
            if(count==0){
                jsonArray.add(1);
            }else{
                count = jdbcTemplate.queryForInt("select count(1) from yuehcb where yuetjkjb_id in (select id from yuetjkjb where riq='"
                        +map.get("riq")+"' and diancxxb_id = "+map.get("dianc")+") and zhuangt = 0");
                jsonArray.add(count);
            }
        }catch(Exception e){
            jsonArray.add(1);
        }
        return jsonArray;
    }

}
