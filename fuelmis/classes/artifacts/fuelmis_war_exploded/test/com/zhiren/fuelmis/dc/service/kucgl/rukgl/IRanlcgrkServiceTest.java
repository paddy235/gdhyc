package com.zhiren.fuelmis.dc.service.kucgl.rukgl;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



import com.zhiren.fuelmis.dc.service.kucgl.rukgl.IRanlcgrkService;

import net.sf.json.JSONObject;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/applicationContext.xml",
		"file:WebContent/WEB-INF/spring-servlet.xml" })
public class IRanlcgrkServiceTest {
	private static Logger logger = LogManager.getLogger(IRanlcgrkServiceTest.class);
	@Autowired
	private IRanlcgrkService ranlcgrkService;
	@Test
	public void testGetChurkdList() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sDate", "2016-01-01");
		map.put("eDate","2016-06-08");
		map.put("rukdbh", "%"+""+"%");
		map.put("yewlx", 1);
		JSONObject list = ranlcgrkService.getCaigddList(map);
		logger.info(list);
		
	}

	@Test
	public void testGetChurkdList2() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetYansmx() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetYansmxMX() {
		fail("Not yet implemented");
	}

	@Test
	public void testRanlcgrk() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRanlcgrk_WRK_MX() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRanlcgrk_WRK_MX2() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveRukd() {
		fail("Not yet implemented");
	}

	@Test
	public void testRuk() {
		fail("Not yet implemented");
	}

	@Test
	public void testChex() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetQitrk() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheck() {
		fail("Not yet implemented");
	}

	@Test
	public void testRanlhyrk() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCaigddList() {
		fail("Not yet implemented");
	}

	@Test
	public void testEditCaigdd() {
		fail("Not yet implemented");
	}

}
