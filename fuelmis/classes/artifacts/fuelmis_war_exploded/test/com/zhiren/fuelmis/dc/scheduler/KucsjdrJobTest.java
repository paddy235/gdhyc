package com.zhiren.fuelmis.dc.scheduler;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhiren.fuelmis.dc.scheduler.KucsjdrJob;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/applicationContext.xml",
		"file:WebContent/WEB-INF/spring-servlet.xml" })
public class KucsjdrJobTest {
@Autowired
private KucsjdrJob kucsjdrJob;
	@Test
	public void testExecute() {
		kucsjdrJob.execute();
	}

	@Test
	public void testChukdj() {
		kucsjdrJob.chukdj();
	}

}
