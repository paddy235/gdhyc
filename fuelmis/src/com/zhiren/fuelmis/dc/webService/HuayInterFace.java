package com.zhiren.fuelmis.dc.webService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.zhiren.fuelmis.dc.service.ruchy.IRuchyService;
import com.zhiren.fuelmis.dc.service.rulhy.IRulhyService;

public class HuayInterFace extends SpringBeanAutowiringSupport {
	@Autowired
	private IRuchyService ruchyService;
	@Autowired
	private IRulhyService rulhyService;
	public String get_huay_zhillsb(String dcId, String bianm, byte[] XMLDate) {
		try {
			return ruchyService.get_huay_zhillsb(dcId, bianm, XMLDate);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public byte[] getFahInfo_jt(String dcId, String bianm) {
		try {
			return ruchyService.getFahInfo_jt(dcId, bianm);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public String setHuayxx_jt(String user,String pasd,byte[] XMLData) {
		try {
			return rulhyService.setHuayxx_jt(user, pasd, XMLData);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public boolean isPip(String bianm){
		return rulhyService.isPip(bianm);
	}
}
