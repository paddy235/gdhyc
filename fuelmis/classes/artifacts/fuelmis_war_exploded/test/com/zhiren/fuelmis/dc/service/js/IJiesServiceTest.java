package com.zhiren.fuelmis.dc.service.js;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhiren.fuelmis.dc.service.js.IJiesService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/applicationContext.xml",
		"file:WebContent/WEB-INF/spring-servlet.xml" })
public class IJiesServiceTest {
@Autowired
private IJiesService jiesService;
@Autowired
private JdbcTemplate jdbcTemplate;
	@Test
	public void testJsHuit() {
		List<String> list=new ArrayList<String>();
		list.add("GD-JS-dsrd-201606-01");
		list.add("GD-JS-dsrd-201606-02");
		list.add("GD-JS-dsrd-201606-03");
		list.add("GD-JS-dsrd-201606-05");
		list.add("GD-JS-dsrd-201606-06");
		list.add("GD-JS-dsrd-201606-07");
		list.add("GD-JS-dsrd-201606-08");
		for (String jiesdbh : list) {
			jiesService.jsHuit("2", "", jiesdbh, "", "");
		}
	}

}
