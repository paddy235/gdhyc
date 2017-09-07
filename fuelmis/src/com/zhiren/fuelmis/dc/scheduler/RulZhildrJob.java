package com.zhiren.fuelmis.dc.scheduler;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.zhiren.fuelmis.dc.utils.Sequence;

/**
 * @author cocoa可哥
 */
@Component("rulZhildrJob")
public class RulZhildrJob {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void execute() {
		String sql = "SELECT PK_ORG_BM,'入炉化验' LEIX,ASSAYCODE,ASSAYDATE,ASSAYUSER,\n" +
					"  MT,MAD,AAR,AAD,AD,nvl(ADAF,0) ADAF,VAR,VAD,VD,VDAF,\n" + 
					"  FCAD,FCAR,nvl(FCD,0) FCD,nvl(FCDAF,0) FCDAF,STAR,STAD,STD,nvl(SDAF,0) SDAF,\n" + 
					"  HAD,nvl(HDAF,0)HDAF,nvl(HAR,0)HAR,HD,QBAD/1000 QBAD, QGRAD,QGRD,nvl(QGRADDAF,0)QGRADDAF,nvl(QGRAR,0)QGRAR,QNETAR,\n" + 
					"  HANDLERTYPE,DATASTATUS,READERRECONTENT,READTIME,UPLOADTIME\n" + 
					"  FROM JK_RL_HUAYBGB\n" + 
					" WHERE DATASTATUS = 0  and nvl(Qnetar,0) <> 0";


		List<Map<String, Object>> rulzhilList = jdbcTemplate.queryForList(sql);
		if (null != rulzhilList && rulzhilList.size() > 0) {
			for (Map<String, Object> data : rulzhilList) {
				if (data.get("HANDLERTYPE").toString().endsWith("0")) {
					this.insert(data);
				} else if (data.get("HANDLERTYPE").toString().endsWith("1") ) {
					// 避免在更新的时候报空指针，更新前先判断有没有更新的数据记录
					int count = getcount(data);
					if (count == 0) {
						this.insert(data);
					} else {
						this.update(data);
					}
				} else if (data.get("HANDLERTYPE").toString().endsWith("2") ) {
					this.delete(data);
				}
				this.updateDataState(data);
			}
		}
	}

	public void insert(Map<String, Object> map) {
		String huayd_id  = Sequence.nextId();
		
		String sql = "begin \n"+
				" insert into rl_hy_huaydb\n" +
				"  (HUAYD_ID, HUAYSJ, HUAYY, LURY,HUAYBM,DIANC_ID,LEIX,\n" + 
				"   MT,MAD,AAD,AD,ADAF,AAR,VAD,VD,VDAF,VAR,FCAD,FCD,FCDAF,FCAR,\n" + 
				"   STAD,STD,STAR, HAD,HD,HDAF,HAR, \n" + 
				"   QBAD,QGRAD,QGRAD_DAF,QGRAR,QNET_AR,zhuangt  )\n" + 
				"values ( "+huayd_id+" ,'" +map.get("ASSAYDATE").toString()+"','" +map.get("ASSAYUSER").toString()+"',' 系统录入',\n"+
				"'"+map.get("ASSAYCODE").toString()+"',515,'入炉化验', \n"
						+map.get("MT").toString()+	","+map.get("MAD").toString()+","				
						+map.get("AAD").toString()+","+map.get("AD").toString()+","+map.get("ADAF").toString()+","+map.get("AAR").toString()+",\n"
						+map.get("VAD").toString()+","+map.get("VD").toString()+","+map.get("VDAF").toString()+","+map.get("VAR").toString()+",\n"
						+map.get("FCAD").toString()+","+map.get("FCD").toString()+","+map.get("FCDAF").toString()+","+map.get("FCAR").toString()+",\n"
						+map.get("STAD").toString()+","+map.get("STD").toString()+","+map.get("STAR").toString()+",\n"
						+map.get("HAD").toString()+","+map.get("HD").toString()+","+map.get("HDAF").toString()+","+map.get("HAR").toString()+",\n"						
						+map.get("QBAD").toString()+","+map.get("QGRAD").toString()+","+map.get("QGRADDAF").toString()+","+map.get("QGRAR").toString()+",\n"
						+map.get("QNETAR").toString()+",2 );\n";
		sql +="update (select * from rl_rul_meihyb m where m.huaybm  = '"+map.get("ASSAYCODE").toString()+"' ) set huayd_id = "+huayd_id+" ;\n";
		sql += " end;";
		jdbcTemplate.update(sql);

	}

	public void update(Map<String, Object> map) {
		String sql = "update rl_hy_huaydb set HUAYSJ='"
				+ map.get("ASSAYDATE").toString() + "',HUAYY='"
				+ map.get("ASSAYUSER").toString() + "',AAR='"
				+ map.get("AAR").toString() + "',AD='"
				+ map.get("AD").toString() + "',VDAF='"//
				+ map.get("VDAF").toString() + "',MT='"
				+ map.get("MT").toString() + "',STAD='"
				+ map.get("STAD").toString() + "',AAD='"
				+ map.get("AAD").toString() + "',MAD='"
				+ map.get("MAD").toString() + "',QBAD='"
				+ map.get("QBAD").toString() + "',HAD='"
				+ map.get("HAD").toString() + "',VAD='"
				+ map.get("VAD").toString() + "',FCAD='"
				+ map.get("FCAD").toString() + "',STD='"
				+ map.get("STD").toString() + "',QGRAD='"
				+ map.get("QGRAD").toString() + "',HDAF='"
				+ map.get("HDAF").toString() + "',QGRAD_DAF='"
				+ map.get("QGRADDAF").toString() + "',SDAF='"
				+ map.get("SDAF").toString() + "',VAR='"
				+ map.get("VAR").toString() + "',HAR='"
				+ map.get("HAR").toString() + "',QGRD='"
				+ map.get("QGRD").toString() + "',STAR='"
				+ map.get("STAR").toString() + "',QNET_AR='"
				+ map.get("QNETAR").toString() + "',DIANC_ID='"
				+ map.get("PK_ORG_BM").toString() + "',VD='"
				+ map.get("VD").toString() + "',ADAF='"
				+ map.get("ADAF").toString() + "',FCAR='"
				+ map.get("FCAR").toString() + "',FCD='"
				+ map.get("FCD").toString() + "',FCDAF='"
				+ map.get("FCDAF").toString() + "',"
				+ "QGRAR='" + map.get("QGRAR").toString() + "',\n"
				+ "HD='" + map.get("HD").toString()+"' \n" 
				+ " where HUAYBM='"+map.get("ASSAYCODE").toString()+"'";
		jdbcTemplate.update(sql);
	}

	public void delete(Map<String, Object> map){
		String sql ="delete from rl_hy_huaydb wwhere HUAYBM='"+map.get("ASSAYCODE").toString()+"'";
				jdbcTemplate.update(sql);
	}

	public int getcount(Map<String, Object> map) {
		String sql = "select count(*) from rl_hy_huaydb where HUAYBM='"+map.get("ASSAYCODE").toString()+"'";
		return jdbcTemplate.queryForInt(sql);
	}

	public void updateDataState(Map<String, Object> map) {
		String sql = "update jk_rl_huaybgb set DATASTATUS = 1 where ASSAYCODE='"+map.get("ASSAYCODE").toString()+"'";
				jdbcTemplate.update(sql);
	}
}
