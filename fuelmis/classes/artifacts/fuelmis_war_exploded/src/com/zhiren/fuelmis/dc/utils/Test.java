package com.zhiren.fuelmis.dc.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Formatter;
import java.util.Locale;

/**
 *共用测试类 
 * 
 * @author 孙志涛
 *
 */



public class Test {
	public static void main(String[] args){
		try{
		            InetAddress address = InetAddress.getLocalHost(); 
		            NetworkInterface ni = NetworkInterface.getByInetAddress(address); 
		            //ni.getInetAddresses().nextElement().getAddress();  
		            byte[] mac = ni.getHardwareAddress(); 
		            String sIP = address.getHostAddress(); 
		            String sMAC = ""; 
		            Formatter formatter = new Formatter(); 
		            for (int i = 0; i < mac.length; i++) { 
		                sMAC = formatter.format(Locale.getDefault(), "%02X%s", mac[i], 
		                        (i < mac.length - 1) ? "-" : "").toString(); 
		 
		            } 
		            System.out.println("IP：" + sIP); 
		            System.out.println("MAC：" + sMAC); 
		        }catch(Exception e){ 
		            e.printStackTrace(); 
		        } 
		}
	
}
