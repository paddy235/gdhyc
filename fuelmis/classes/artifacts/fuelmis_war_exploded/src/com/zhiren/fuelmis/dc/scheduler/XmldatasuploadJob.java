
package com.zhiren.fuelmis.dc.scheduler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhiren.fuelmis.dc.service.webInterface.InterFac_dt;

/*
*/
/**
 * @author 张照奎
 *//*
*/

@Component("xmldatasuploadJob")
public class XmldatasuploadJob {

	@Autowired
	private InterFac_dt interFac_dt;
	public  void execute()  throws Exception{
		interFac_dt.requestall();
	}
}

