package com.zhiren.fuelmis.dc.service.ruchy;

import java.awt.image.BufferedImage;
import java.util.List;

/** 
 * @author 陈宝露
 */
public interface IHuaybmService {

	void startSerial();

	BufferedImage convert();

	BufferedImage getHuaybmBar();

	List<String> getPortList();



	BufferedImage getHuaybmBar(Double height, Double dimension, boolean ShowText);
}
