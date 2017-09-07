package com.zhiren.fuelmis.dc.service.impl.rulhy;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhiren.fuelmis.dc.service.rulhy.IRulhyService;
import com.zhiren.fuelmis.dc.utils.FileUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/applicationContext.xml", "file:WebContent/WEB-INF/spring-servlet.xml" })
public class RulhyServiceImplTest {
	@Autowired
	private IRulhyService hif;
	@Test
	public void testSetHuayxx_jt() {
		File f=new File("F:/rulxml.xml");
		try {
			byte[] XMLData = FileUtil.getBytesFromFile(f);
			hif.setHuayxx_jt("", "", XMLData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
