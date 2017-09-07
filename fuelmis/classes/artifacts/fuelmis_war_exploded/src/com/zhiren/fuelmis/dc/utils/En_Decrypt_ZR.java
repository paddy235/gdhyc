package com.zhiren.fuelmis.dc.utils;



import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import com.zhiren.fuelmis.dc.entity.common.MainGlobal;
  
public class En_Decrypt_ZR {
//	设置KeyGen 就是DES 加密密钥
	private static final String keygen ="KINGROCK";
//	将其转换为 byte[]
	public static byte[] keybyte = En_Decrypt_ZR.Str2Byt(keygen);
//	设置 IV （如果南瑞提供的是数组型的IV 则不用进行下面两步操作 直接赋值）
	private static final String IV = "ZHIRENWL";
//	将其转换为 byte[]  
	public static byte[] ivbyte = En_Decrypt_ZR.Str2Byt(IV);

	public static byte[] encryptByDES(String strP) throws Exception {
		String key=MainGlobal.getXitxx_item("接口用","密钥", "0", keygen);
		String iv=MainGlobal.getXitxx_item("接口用","初始向量", "0", IV);
		byte[] key_byte= En_Decrypt_ZR.Str2Byt(key);
		byte[] iv_byte= En_Decrypt_ZR.Str2Byt(iv);
					
		DESKeySpec desKS = new DESKeySpec(key_byte);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey sk = skf.generateSecret(desKS);
		IvParameterSpec ivSpec = new IvParameterSpec (iv_byte); 
		Cipher cip = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cip.init(Cipher.ENCRYPT_MODE, sk, ivSpec);
		return cip.doFinal(Str2Byt(strP));
		//System.out..println("JMID="+cip.doFinal(bytP));
		//byte[] bytm = cip.doFinal(Str2Byt(strP));
		//return Byt2Str(bytm);
	}
	public static String decryptByDESStr(byte[] strE) throws Exception {
		String key=MainGlobal.getXitxx_item("接口用","密钥", "0", keygen);
		String iv=MainGlobal.getXitxx_item("接口用","初始向量", "0", IV);
		byte[] key_byte= En_Decrypt_ZR.Str2Byt(key);
		byte[] iv_byte= En_Decrypt_ZR.Str2Byt(iv);
		DESKeySpec desKS = new DESKeySpec(key_byte);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey sk = skf.generateSecret(desKS);
		IvParameterSpec ivSpec = new IvParameterSpec (iv_byte); 
		Cipher cip = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cip.init(Cipher.DECRYPT_MODE,sk, ivSpec);
		byte[] bytm = cip.doFinal(strE);
		//byte[] bytm = cip.doFinal(Str2Byt(strE));
		return Byt2Str(bytm);
	}
	
	public static byte[] encryptByDES(byte[] bytP) throws Exception {
		String key=MainGlobal.getXitxx_item("接口用","密钥", "0", keygen);
		String iv=MainGlobal.getXitxx_item("接口用","初始向量", "0", IV);
		byte[] key_byte= En_Decrypt_ZR.Str2Byt(key);
		byte[] iv_byte= En_Decrypt_ZR.Str2Byt(iv);
		DESKeySpec desKS = new DESKeySpec(key_byte);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey sk = skf.generateSecret(desKS);
		IvParameterSpec ivSpec = new IvParameterSpec (iv_byte); 
		Cipher cip = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cip.init(Cipher.ENCRYPT_MODE, sk, ivSpec);
		//System.out..println("JMID="+cip.doFinal(bytP));
		return cip.doFinal(bytP);
	}
	public static byte[] decryptByDES(byte[] bytE) throws Exception {
		String key=MainGlobal.getXitxx_item("接口用","密钥", "0", keygen);
		String iv=MainGlobal.getXitxx_item("接口用","初始向量", "0", IV);
		byte[] key_byte= En_Decrypt_ZR.Str2Byt(key);
		byte[] iv_byte= En_Decrypt_ZR.Str2Byt(iv);
		DESKeySpec desKS = new DESKeySpec(key_byte);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey sk = skf.generateSecret(desKS);
		IvParameterSpec ivSpec = new IvParameterSpec (iv_byte); 
		Cipher cip = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cip.init(Cipher.DECRYPT_MODE,sk, ivSpec);
		return cip.doFinal(bytE);
	}
	public static byte[] encryptByDES(byte[] bytP, byte[] bytKey, byte[] EncryptionIV ) throws Exception {
		DESKeySpec desKS = new DESKeySpec(bytKey);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey sk = skf.generateSecret(desKS);
		IvParameterSpec ivSpec = new IvParameterSpec (EncryptionIV ); 
		Cipher cip = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cip.init(Cipher.ENCRYPT_MODE, sk, ivSpec);
		//System.out..println("JMID="+cip.doFinal(bytP));
		return cip.doFinal(bytP);
	}
	public static byte[] decryptByDES(byte[] bytE, byte[] bytKey, byte[] EncryptionIV ) throws Exception {
		DESKeySpec desKS = new DESKeySpec(bytKey);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey sk = skf.generateSecret(desKS);
		IvParameterSpec ivSpec = new IvParameterSpec (EncryptionIV ); 
		Cipher cip = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cip.init(Cipher.DECRYPT_MODE,sk, ivSpec);
		return cip.doFinal(bytE);
	}
	public static String Byt2Str(byte[] bt) {
		String ss=null;
		char[] chars = new char[bt.length];
		for(int i=0;i<bt.length;i++) {
			chars[i] = (char)bt[i];
		}
		ss = String.valueOf(chars);
		return ss;
	}
	public static byte[] Str2Byt(String str) {
		byte[] bt=null;
		char[] chars = str.toCharArray();
		bt = new byte[chars.length];
		for(int i=0;i<chars.length;i++) {
			bt[i] = (byte)chars[i];
		}
		return bt;
	}
}
