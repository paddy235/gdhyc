package com.zhiren.fuelmis.dc.scheduler;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/applicationContext.xml",
		"file:WebContent/WEB-INF/spring-servlet.xml" })
public class ShulrkJobTest {
	@Autowired
	private ShulrkJob shulrkJob;
	@Test
	public void testExecute() {
		shulrkJob.execute();
	}

	@Test
	public void testRanlcgrk() {
		shulrkJob.deleteChurkdbByChepbid("4273411145688");
	}

	@Test
	public void testRuk() {
		fail("Not yet implemented");
	}

}
