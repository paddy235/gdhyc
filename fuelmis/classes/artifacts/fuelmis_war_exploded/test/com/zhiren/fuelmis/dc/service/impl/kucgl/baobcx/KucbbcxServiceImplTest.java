package com.zhiren.fuelmis.dc.service.impl.kucgl.baobcx;

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

import com.zhiren.fuelmis.dc.service.impl.kucgl.baobcx.KucbbcxServiceImpl;

import net.sf.json.JSONArray;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/applicationContext.xml",
		"file:WebContent/WEB-INF/spring-servlet.xml" })
public class KucbbcxServiceImplTest {
	private static Logger logger = LogManager.getLogger(KucbbcxServiceImplTest.class);
@Autowired
private KucbbcxServiceImpl kucbbcxService;
	@Test
	public void testGetFadgrckhybb() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetShiscbhsbb() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetChukdbb() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("Date", "2026-06");
		JSONArray chukdbb = kucbbcxService.getChukdbb(map);
		logger.info(chukdbb);
		
	}

}
