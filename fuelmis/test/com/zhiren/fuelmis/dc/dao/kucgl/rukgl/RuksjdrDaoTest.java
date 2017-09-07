package com.zhiren.fuelmis.dc.dao.kucgl.rukgl;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhiren.fuelmis.dc.dao.kucgl.rukgl.RuksjdrDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WebContent/WEB-INF/applicationContext.xml",
        "file:WebContent/WEB-INF/spring-servlet.xml"})
public class RuksjdrDaoTest {
    @Autowired
    private RuksjdrDao ruksjdrDao;
    private static Logger logger = LogManager.getLogger(RuksjdrDaoTest.class);
    @Test
    public void getYunzfRukdbhs() throws Exception {
        ruksjdrDao.getYunzfRukdbhs();
    }

    @Test
    public void getYunzfRukdID() throws Exception {
        ruksjdrDao.getYunzfRukdID("20160408119");
    }

    @Test
    public void updateYunzfRukd() throws Exception {
        ruksjdrDao.updateYunzfRukd("1");
    }

    @Test
    public void deleteYunzfRukdSub() throws Exception {
        ruksjdrDao.deleteYunzfRukdSub("0");
    }

    @Test
    public void insertYunzfRukdSub() throws Exception {
        ruksjdrDao.insertYunzfRukdSub("20160408119");
    }

    @Test
    public void getYunzfKucmxList() throws Exception {
        ruksjdrDao.getYunzfKucmxList("20160408119");
    }

    @Test
    public void insertYunzfKucmxhzb() throws Exception {

    }

    @Test
    public void updateYunzfKucmxhzb() throws Exception {

    }

    @Test
    public void insertYunzfRukd() throws Exception {
        ruksjdrDao.insertYunzfRukd("1","20160408119");
    }

//-------------------------------------------------------------------------------------------------------------------

    @Test
    public void testGetRukdbhs() {
//        List<String> rukdbhs = ruksjdrDao.getRukdbhs();
//    	List<String> rukdbhs = ruksjdrDao.getRukdbhs("1");
//        logger.info(rukdbhs);
    }

    @Test
    public void testGetRukdID() {
        String rukdID = ruksjdrDao.getRukdID("RKD-20160608026");
        logger.info(rukdID);
    }

    @Test
    public void testUpdateRukd() {
        fail("Not yet implemented");
    }

    @Test
    public void testDeleteRukdSub() {
        fail("Not yet implemented");
    }

    @Test
    public void testInsertRukd() {
        ruksjdrDao.insertRukd("0", "RKD-20160608026");
    }

    @Test
    public void testInsertRukdSub() {
        ruksjdrDao.insertRukdSub("RKD-20160608026");
    }

    @Test
    public void testUpdateChurkd() {
        fail("Not yet implemented");
    }

    @Test
    public void testDeteteKucmxhzb() {
        fail("Not yet implemented");
    }

    @Test
    public void testInsertKucmxhzb() {
        ruksjdrDao.insertKucmxhzb("RKD-20160608026");
    }

    @Test
    public void testUpdateKucmxhzb() {
        fail("Not yet implemented");
    }

    @Test
    public void testUpdateChurkdString() {
        fail("Not yet implemented");
    }

}
