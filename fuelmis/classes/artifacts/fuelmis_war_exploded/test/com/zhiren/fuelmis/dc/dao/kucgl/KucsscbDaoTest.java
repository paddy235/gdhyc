package com.zhiren.fuelmis.dc.dao.kucgl;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.ibatis.annotations.Update;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhiren.fuelmis.dc.dao.kucgl.KucsscbDao;
import com.zhiren.fuelmis.dc.entity.kucgl.ChurkBean;
import com.zhiren.fuelmis.dc.entity.kucgl.Shiscbhs;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/applicationContext.xml" })
public class KucsscbDaoTest {
	private static Logger logger = LogManager.getLogger(KucsscbDaoTest.class);
	@Autowired
	private KucsscbDao kucsscbDao;

	@Test
	public void testGetBaseChurkBean() {
		ChurkBean churkBean = kucsscbDao.getBaseChurkBean();
//		logger.info(churkBean);
		logger.info("===========================Not yet implemented======================");
	}

	@Test
	public void testGetNewChurkBeans() {
		List<ChurkBean> newChurkBeans = kucsscbDao.getNewChurkBeans("RKD-20160603001");
		logger.info(newChurkBeans);
//		logger.info(newChurkBeans);
//		fail("Not yet implemented");
	}

	@Test
	public void testSaveChurkBean() {
		ChurkBean churkBean = kucsscbDao.getBaseChurkBean();
		List<ChurkBean> newChurkBeans = kucsscbDao.getNewChurkBeans("RKD-20160603001");
		Shiscbhs shiscbhs = new Shiscbhs();
		shiscbhs.iterate(churkBean, newChurkBeans);
		for (ChurkBean churkBean2 : newChurkBeans) {
			kucsscbDao.deleteChurkBeanByID(churkBean2.getId());
			kucsscbDao.saveChurkBean(churkBean2);
		}
		logger.info(newChurkBeans);

	}

	@Test
	public void testDeleteChurkBeans() {
		fail("Not yet implemented");
	}

}
