package com.zhiren.fuelmis.dc.scheduler;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import bsh.EvalError;
import bsh.Interpreter;
import com.zhiren.fuelmis.dc.scheduler.GongyspfJob;
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "file:WebContent/WEB-INF/applicationContext.xml",
//		"file:WebContent/WEB-INF/spring-servlet.xml" })
public class GongyspfJobTest {
	@Autowired
	private GongyspfJob gongyspfJob;
	@Test
	public void testExecute() {
		gongyspfJob.execute();
	}

	@Test
	public void testFab() {
		fail("Not yet implemented");
	}

	@Test
	public void testJis() {
	    try{
            Interpreter bsh = new Interpreter();
            bsh.set("CF", 140.85);
            bsh.set("KF", 100.0);
            bsh.set("指标考核标识", "SL-rank");
            bsh.set("指标标准分",20);
           // bsh.set("rank",1);
           // bsh.set("count",10);
            //数量
//            bsh.eval("double result=0;if(CF>0 && KF>0){double ratio=CF/KF;if(ratio>=1&&ratio<=1.05){result=23;}else if(ratio<1){result=19-(int)((1-ratio)/0.02);}else{result=19-(int)((ratio-1)/0.02);}}return result;");
//Qnet_ar
//            bsh.eval("double result=0;if(CF>0&&KF>0){double chaz=CF-KF;if(chaz<-21.5){result=(int)(21.5+chaz);}else if(chaz>0){result=(int)chaz;}}return result;");
//star
//            bsh.eval("double result=0;if(CF>0&&KF>0){double fud=Math.abs((CF-KF)/KF)-0.0075; if(CF-KF>0&&fud>0){result=-fud/0.001*0.5;}else if(CF-KF<0&&fud<0){result=-(int)(fud/0.001);}}return result;");
  //MT
//            bsh.eval("double result=0;if(CF>0&&KF>0){double fud=Math.abs((CF-KF)/KF)-0.15;if(CF-KF>0&&fud>0){result=-(int)(fud/0.01);}}return result;");
            //Vdaf
            bsh.eval("double result=0;if(CF>0&&KF>0){double fud=Math.abs((CF-KF)/KF);if(fud>0){result=-(int)(fud/0.05);}}return result;");
           double result=0;
            String zhibfz = bsh.get("result").toString();
            System.out.println(zhibfz);
        }catch (Exception e){
	        e.printStackTrace();
        }

	}

}
