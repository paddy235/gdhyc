package com.zhiren.fuelmis.dc.service.impl.ruchy;



import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.comm.CommPortIdentifier;

import org.jbarcode.JBarcode;
import org.jbarcode.encode.Code128Encoder;
import org.jbarcode.paint.BaseLineTextPainter;
import org.jbarcode.paint.WidthCodedPainter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.ruchy.RuchyDao;
import com.zhiren.fuelmis.dc.serials.SerialConnection;
import com.zhiren.fuelmis.dc.serials.SerialParameters;
import com.zhiren.fuelmis.dc.service.ruchy.IHuaybmService;
/** 
 * @author 刘志宇
 */
@Service
public class HuaybmServiceImpl  implements IHuaybmService{
	@Autowired
	private RuchyDao ruchyDao; 
	@Override
	public void startSerial(){
		SerialParameters parameters =new SerialParameters();
		parameters.setPortName("COM3");
		SerialConnection con = new SerialConnection(parameters);
		try {
			con.openConnection();
		} catch (Exception e) {
			e.printStackTrace();
		} 
//		SerialStarter starter = new SerialStarter();
//		 Thread t1 = new Thread(starter);  
//		 t1.start(); 


//		SerialPortApplet applet = new SerialPortApplet();
//		applet.init();
//		applet.start();
	}
	public String getZhiybm(){
		return SerialConnection.zhiybmValue;
	}
	@Override
	public BufferedImage convert(){
		String zhiybm=SerialConnection.zhiybmValue;
		  try {  
	            JBarcode localJBarcode = new JBarcode(Code128Encoder.getInstance(),WidthCodedPainter.getInstance(),BaseLineTextPainter.getInstance());  
	            BufferedImage localBufferedImage = localJBarcode.createBarcode(zhiybm);  
	            return localBufferedImage;
	           // saveToGIF(localBufferedImage, "EAN8.jpg");
	        }  
	        catch (Exception localException) {  
	            localException.printStackTrace();
	        }
		return null;  
	}
	@Override
	public BufferedImage getHuaybmBar(Double height ,Double dimension,boolean ShowText) {
		String zhiybm=SerialConnection.zhiybmValue;
		String huaybm=ruchyDao.getHuaybm(zhiybm);
		BufferedImage huaybmBufferedImage=null;	
		System.out.println(huaybm);
		  try {  
	            JBarcode localJBarcode = new JBarcode(Code128Encoder.getInstance(),WidthCodedPainter.getInstance(),BaseLineTextPainter.getInstance());  
	            localJBarcode.setBarHeight(height);
	            localJBarcode.setShowText(ShowText);
//	            localJBarcode.setWideRatio(wide);
	            localJBarcode.setXDimension(dimension);
	            
	            huaybmBufferedImage = localJBarcode.createBarcode(huaybm);  
//	            textPainter.paintText(huaybmBufferedImage, huaybm, 2);
	           
	           // saveToGIF(localBufferedImage, "EAN8.jpg");
	        }  
	        catch (Exception localException) {  
	            localException.printStackTrace();
	        }
		return  huaybmBufferedImage;  
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List<String> getPortList(){
	 	Enumeration ports = CommPortIdentifier.getPortIdentifiers();
	 	List<String> list = new ArrayList<String>();
		while (ports.hasMoreElements()) {
			CommPortIdentifier portId = (CommPortIdentifier) ports.nextElement();
//			System.out.println(portId);
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) { // 是串口
				list.add(portId.getName());
			}
		}
		return list;
	}
	@Override
	public BufferedImage getHuaybmBar() {
		// TODO Auto-generated method stub
		return null;
	}

}
