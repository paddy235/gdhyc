package com.zhiren.fuelmis.dc.dao.rucslys;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhiren.fuelmis.dc.dao.rucslys.ChepbtmpDao;
import com.zhiren.fuelmis.dc.entity.rucslys.Chepbtmp;
import com.zhiren.fuelmis.dc.utils.Sequence;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/applicationContext.xml",
		"file:WebContent/WEB-INF/spring-servlet.xml" })
public class ChepbtmpDaoTest {
@Autowired
private ChepbtmpDao chepbtmpDao;
@Autowired
private JdbcTemplate jdbcTemplate;
	@Test
	public void testAddChepb() {
//		String sql="select*from chepbtmp where id=4154611135238";
//		Chepbtmp chepbtmp=jdbcTemplate.queryForObject(sql,Chepbtmp.class);
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("id", "4154611135238");
		List<Map<String,Object>> list=chepbtmpDao.getChepbtmpById(map);
		Chepbtmp chepbtmp=(Chepbtmp)list.get(0);
//		Chepbtmp chepbtmp=chepbtmpList.get(0);
		chepbtmp.setKoud(1.1);
		chepbtmp.setKous(2.2);
		chepbtmp.setKouz(3.3);
		chepbtmp.setId(Long.parseLong(Sequence.nextId()));
		chepbtmp.setKuid(0.0);
		chepbtmpDao.addChepb(chepbtmp);
	}

	@Test
	public void testUpdateChepb() {
		
//		String sql="select*from chepbtmp where id=4154611135238";
//		Chepbtmp chepbtmp=jdbcTemplate.queryForObject(sql,Chepbtmp.class);
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("id", "4154611135238");
		List<Map<String,Object>> list=chepbtmpDao.getChepbtmpById(map);
		Chepbtmp chepbtmp=(Chepbtmp)list.get(0);
//		Chepbtmp chepbtmp=chepbtmpList.get(0);
		chepbtmp.setKoud(1.0);
		chepbtmp.setKous(2.0);
		chepbtmp.setKouz(3.0);
		chepbtmp.setId(Long.parseLong("14702818140500"));
		chepbtmp.setKuid(0.0);
		chepbtmpDao.updateChepb(chepbtmp);
	}

}
