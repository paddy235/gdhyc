package com.zhiren.fuelmis.dc.dao.kucgl.chukgl;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhiren.fuelmis.dc.dao.kucgl.chukgl.ChuksjdrDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/applicationContext.xml",
		"file:WebContent/WEB-INF/spring-servlet.xml" })
public class ChuksjdrDaoTest {
	@Autowired
	private ChuksjdrDao chuksjdrDao;
	private static Logger logger = LogManager.getLogger(ChuksjdrDaoTest.class);

	@Test
	public void testGetChukdbhs() {
		List<String> chukdbhs = chuksjdrDao.getChukdbhs();
		logger.info(chukdbhs);
		// [CKD-20160602003, CKD-20160603004, CKD-20160605001, CKD-20160601007,
		// CKD-20160604001]

	}

	@Test
	public void testGetChukdID() {
		String chukdID = chuksjdrDao.getChukdID("CKD-20160613001");
		logger.info(chukdID);
	}

	@Test
	public void testUpdateChukd() {
		chuksjdrDao.updateChukd("14657961179051");
	}

	@Test
	public void testDeleteChukdSub() {
		chuksjdrDao.deleteChukdSub("CKD-20160602003");
	}

	@Test
	public void testInsertChukd() {
		chuksjdrDao.insertChukd("1111", "CKD-20160613001");
	}

	@Test
	public void testInsertChukdSub() {
		chuksjdrDao.insertChukdSub("CKD-20160613001");
	}

	@Test
	public void testDeteteKucmxhzb() {
		chuksjdrDao.deteteKucmxhzb("CKD-20160602003");
	}

	@Test
	public void testInsertKucmxhzb() {
		chuksjdrDao.insertKucmxhzb("CKD-20160602003");
	}

	@Test
	public void testUpdateKucmxhzb() {
		chuksjdrDao.updateKucmxhzb("CKD-20160602003");
	}

	@Test
	public void testUpdateChurkd() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteChukdj() {
//		chuksjdrDao.deleteChukdj();
	}

	@Test
	public void testInsertChukdj() {
//		chuksjdrDao.insertChukdj();
	}

}
