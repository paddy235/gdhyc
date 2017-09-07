package com.zhiren.fuelmis.dc.service.impl.rucsl;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import com.zhiren.fuelmis.dc.service.rucsl.IShulshService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/applicationContext.xml",
		"file:WebContent/WEB-INF/spring-servlet.xml" })
public class ShulshServiceImplTest {
	@Autowired
	private IShulshService shulshService;

	@Test
	public void testGetFahrq() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTableData() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTableData_MX() {
		fail("Not yet implemented");
	}

	@Test
	public void testRanlcgrk() {
		fail("Not yet implemented");
	}

	@Test
	public void testRuk() {
		fail("Not yet implemented");
	}

	@Test
	public void testShenh() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("samcode", "2015122204607501");
		shulshService.shenh(map);
	}

	@Test
	public void testHuit() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertShulshLogs() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetJiesxxList() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteRijiesd() {
		fail("Not yet implemented");
	}

}
