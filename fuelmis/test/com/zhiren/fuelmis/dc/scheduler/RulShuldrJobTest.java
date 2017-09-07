package com.zhiren.fuelmis.dc.scheduler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liuzhiyu on 2017/5/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/applicationContext.xml",
        "file:WebContent/WEB-INF/spring-servlet.xml" })
public class RulShuldrJobTest {
    @Autowired
    private RulShuldrJob rulShuldrJob;
    @Test
    public void execute() throws Exception {
        rulShuldrJob.execute();
    }

}