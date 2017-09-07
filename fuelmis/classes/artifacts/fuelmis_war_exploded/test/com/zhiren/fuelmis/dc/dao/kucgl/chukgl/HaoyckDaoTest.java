package com.zhiren.fuelmis.dc.dao.kucgl.chukgl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhiren.fuelmis.dc.dao.kucgl.chukgl.HaoyckDao;
import com.zhiren.fuelmis.dc.entity.kucgl.ChurkBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/applicationContext.xml" })
public class HaoyckDaoTest {
	@Autowired
	private HaoyckDao haoyckDao;

	@Test
	public void testGetChukBean() {
		ChurkBean chukBean = haoyckDao.getChukBean();
		System.out.println("-------------------" + chukBean + "-----------------------");
	}

	@Test
	public void testGetRukBean() {

	}

}
