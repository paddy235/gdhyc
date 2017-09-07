package com.zhiren.fuelmis.dc.dao.rulgl;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhiren.fuelmis.dc.dao.rulgl.KucglDao;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/applicationContext.xml",
		"file:WebContent/WEB-INF/spring-servlet.xml" })
public class KucglDaoTest {
@Autowired
private KucglDao kucglDao;
	@Test
	public void testGetKuczz() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsert() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertKucwz() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateKucwz() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetKucwz() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetKuaijqdy() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateKuaijqdy() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertKuaijqdy() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetGuanl() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetKuaijqList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetKuczzList() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateGuanl() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertGuanl() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetWeiz() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFuKuczzList() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateWeiz() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertWeiz() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetKucftList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetKucftListFromKucwl() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateKucftList() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertKucftList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetChurkd() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateChurkd() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertChurkd() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelChurkd() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertKucyeb() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteKucyeb() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateKucyeb() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetChukdbh() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateMonthTotal() {
		Map<String,Object> map=new HashMap<String,Object>();
    	map.put("userid",112);
    	map.put("username","me");
    	map.put("KUAIJQ_ID", "14646760582580");
    	map.put("KUCZZ", "14473277164810");
		kucglDao.updateMonthTotal(map);
	}
	@Test
	public void testUpdateChukdSub() {
		Map<String,Object> map=new HashMap<String,Object>();
    	map.put("userid",112);
    	map.put("username","me");
    	map.put("KUAIJQ_ID", "14646760582580");
    	map.put("KUCZZ", "14473277164810");
		kucglDao.updateChukdSub(map);
	}
	@Test
	public void testUpdateChurkmxhzb() {
		Map<String,Object> map=new HashMap<String,Object>();
    	map.put("userid",112);
    	map.put("username","me");
    	map.put("KUAIJQ_ID", "14646760582580");
    	map.put("KUCZZ", "14473277164810");
		kucglDao.updateChurkmxhzb(map);
	}

}
