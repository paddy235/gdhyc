package com.zhiren.fuelmis.dc.service.impl.yuebgl.yuebwh;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhiren.fuelmis.dc.service.yuebgl.yuebwh.IHaocmxService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/applicationContext.xml",
		"file:WebContent/WEB-INF/spring-servlet.xml" })
public class HaocmxServiceImplTest {
	@Autowired
	private IHaocmxService haocmxService;
	@Test
	public void testCreateData() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("riq", "2016-07");
		map.put("dianc", 515);
		haocmxService.createData(map);
	}

}
