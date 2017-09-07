package com.zhiren.fuelmis.dc.scheduler;

import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/applicationContext.xml",
		"file:WebContent/WEB-INF/spring-servlet.xml" })
public class ZhilrkJobTest {
	@Autowired
	private ZhilrkJob rijsJob;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static Logger logger = LogManager.getLogger(ZhilrkJobTest.class);

	@Test
	public void testExecute() {
		rijsJob.execute();
	}

	@Test
	public void testJiesrk() {
		String sql = 
				"select kc.id,\n" +
						"       yanssl,\n" + 
						"       round(yanssl * round(j.JIESJG / 1.17, 2), 2) jine,\n" + 
						"       round(j.JIESJG / 1.17 * 29.271 / j.jiesrz, 2) biaomdj,\n" + 
						"       round(j.jingz * 29.271 / j.jiesrz, 2) rukbml\n" + 
						"  from vm_rijsmx j\n" + 
						" inner join vm_kucmx kc\n" + 
						"    on j.chepb_id = kc.chepb_id\n" + 
						" where (kc.rukdbh, kc.hangh) in\n" + 
						"       (select rukdbh, hangh\n" + 
						"          from gx_churkdb_yunsdj\n" + 
						"         where yuandj_id in\n" + 
						"               (select id\n" + 
						"                  from vm_chepmx cp\n" + 
						"                 where cp.id not in\n" + 
						"                       (select c.id\n" + 
						"                          from rl_ys_chepb     c,\n" + 
						"                               rl_ys_chepb_qc  qc,\n" + 
						"                               gx_jiesdb_chepb rgx,\n" + 
						"                               gx_rijsd_yuejsd ygx\n" + 
						"                         where c.id = qc.chepb_id\n" + 
						"                           and c.id = rgx.chepb_id\n" + 
						"                           and rgx.jiesdb_id = ygx.rijsdb_id)\n" + 
						"                   and substr(cp.qingcsj, 0, 10) >= '2016-01-01')\n" + 
						"            or yuandj_id in\n" + 
						"               (select id\n" + 
						"                  from vm_chepmx cp\n" + 
						"                 where cp.id in\n" + 
						"                       (select c.id\n" + 
						"                          from rl_ys_chepb     c,\n" + 
						"                               rl_ys_chepb_qc  qc,\n" + 
						"                               gx_jiesdb_chepb rgx,\n" + 
						"                               gx_rijsd_yuejsd ygx,\n" + 
						"                               rl_js_yuejsdb   yjs\n" + 
						"                         where c.id = qc.chepb_id\n" + 
						"                           and c.id = rgx.chepb_id\n" + 
						"                           and yjs.id = ygx.yuejsdb_id\n" + 
						"                           and yjs.caozrq >= '2016-06-01'\n" + 
						"                           and rgx.jiesdb_id = ygx.rijsdb_id)))\n" + 
						"   and kc.yewrq < '2016-07-01'";

				
				
				//"select k.id,round(yanssl*round(j.JIESJG/1.17,2),2) jine,round( j.JIESJG/1.17*29.271/j.jiesrz,2) biaomdj, round(j.jingz*29.271/j.jiesrz,2) rukbml\n"
				//+ " from vm_rijsmx j inner join vm_kucmx k on j.chepb_id=k.chepb_id where substr(j.caozrq,0,7)='2016-06'";

		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> map : list) {
			sql = "update rl_kc_churkdb set jine=" + map.get("JINE") + ",biaomdj=" + map.get("BIAOMDJ") + ",rukbml= "
					+ map.get("RUKBML") + ",shifdr=0  where id=" + map.get("ID");
			jdbcTemplate.update(sql);
		}
	}

}
