package com.zhiren.fuelmis.dc.dao.kucgl;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhiren.fuelmis.dc.dao.kucgl.KucbbcxDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/applicationContext.xml",
		"file:WebContent/WEB-INF/spring-servlet.xml" })
public class KucbbcxDaoTest {
	@Autowired
	private KucbbcxDao kucbbcxDao;
	private static Logger logger = LogManager.getLogger(KucbbcxDaoTest.class);

	@Test
	public void testGetFadgrckhyList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetShiscbhsbb() {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("riq", "2016-06");
//		List<Arrays> shiscbhsbb = kucbbcxDao.getShiscbhsbb(map);
//		for (Arrays a : shiscbhsbb) {
//			logger.info(a);
//		}

	}

	@Test
	public void testGetChukdbb() {

	}

}
