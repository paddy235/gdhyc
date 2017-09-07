package com.zhiren.fuelmis.dc.scheduler;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


/**
 * @author cocoa可哥
 */
@Component("rulShuldrJob")
public class RulShuldrJob {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void execute() {
		String sql =  "select getnewid(515) ID, id as jk_meihyb_id,\n" +
						"       to_char(RULRQ, 'yyyy-mm-dd') as RULRQ,\n" + 
						"       TABLETYPE, CODE,\n" + 
						"       nvl(FADHY,0) FADHY, nvl(GONGRHY,0) GONGRHY,\n" + 
						"       nvl(QITY,0) QITY, nvl(FEISCY,0) FEISCY,\n" + 
						"       BEIZ,\n" + 
						"       assaycode HUAYBM,\n" + 
						"       HANDLERTYPE,\n" + 
						"       DATASTATUS\n" + 
						"  from jk_meihy\n" + 
						" where DATASTATUS = 0";

		List<Map<String, Object>> rulshulList = jdbcTemplate.queryForList(sql);
		if (null != rulshulList && rulshulList.size() > 0) {
			for (Map<String, Object> data : rulshulList) {
				if (data.get("HANDLERTYPE").toString().equals("0") ) {
					this.insert(data);
				} else if (data.get("HANDLERTYPE").toString().equals("1") ) {
					// 避免在更新的时候报空指针，更新前先判断有没有更新的数据记录
					int count = getcount(data);
					if (count == 0) {
						this.insert(data);
					} else {
						this.update(data);
					}
				} else if (data.get("HANDLERTYPE").toString().equals("2") ) {
					this.delete(data);
				}
				this.updateDataState(data);
			}
		}
	}

	public void insert(Map<String, Object> map) {
		String sql = "insert into rl_rul_meihyb(id,rulrq,diancxxb_id,rulbzb_id,jizfzb_id,"
				+ "fadhy,gongrhy,qity,feiscy,lursj,jk_meihyb_id,HUAYBM) "
				+ "values(getnewid(515),'" + map.get("RULRQ").toString() + "',515,"
				+ "(select id from rl_rul_banzb  where bianm = '"+map.get("CODE").toString()+"') , \n"
				+ "(select id from rl_rul_jizb  where bianm ='"+map.get("TABLETYPE").toString()+"') ,\n"
				+ map.get("FADHY").toString() + ","
				+ map.get("GONGRHY").toString() + ","
				+ map.get("QITY").toString() + ","
				+ map.get("FEISCY").toString() + ",\n"
				+ " to_char(sysdate,'yyyy-mm-dd'), "+map.get("JK_MEIHYB_ID").toString()+" ,'"+map.get("HUAYBM").toString()+"')";
		System.out.println(sql);
		jdbcTemplate.update(sql);

	}

	public void update(Map<String, Object> map) {
		String sql = "update rl_rul_meihyb set \n"
				+ " fadhy='" + map.get("FADHY").toString() + "',\n"
				+ "gongrhy='" + map.get("GONGRHY").toString() + "',\n"
				+ "QITY='" + map.get("QITY").toString() + "',\n"
				+ "feiscy='" + map.get("FEISCY").toString() + "',\n"
				+ "rulrq = '" + map.get("RULRQ").toString() + "',\n"
				+ "rulbzb_id = (select id from rl_rul_banzb  where bianm = '"+map.get("CODE").toString()+"'),\n"
				+ "jizfzb_id = (select id from rl_rul_jizb  where bianm ='"+map.get("TABLETYPE").toString()+"'),\n"
				+ "HUAYBM = '"+map.get("HUAYBM").toString()+"'\n"
				+ " where jk_meihyb_id =" + map.get("JK_MEIHYB_ID").toString() + " ";
		jdbcTemplate.update(sql);
	}

	public void delete(Map<String, Object> map){
		String sql ="delete from rl_rul_meihyb where jk_meihyb_id =" + map.get("JK_MEIHYB_ID").toString() + " ";
				jdbcTemplate.update(sql);
	}

	public int getcount(Map<String, Object> map) {
		String sql = " select count(*) from rl_rul_meihyb where jk_meihyb_id = " + map.get("JK_MEIHYB_ID").toString() + "";
		return jdbcTemplate.queryForInt(sql);
	}

	public void updateDataState(Map<String, Object> map) {
		String sql = "update jk_meihy set DATASTATUS = 1 where id =" + map.get("JK_MEIHYB_ID").toString() + "";
				jdbcTemplate.update(sql);
	}
}
