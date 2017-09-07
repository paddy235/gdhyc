package com.zhiren.fuelmis.dc.service.rulhy;

public interface IRulhyService {
	/**
	 * 
	 * @param user
	 * @param pasd
	 * @param XMLData
	 * @return 错误信息
	 */
	String setHuayxx_jt(String user, String pasd, byte[] XMLData);
	/**
	 * @param bianm 化验编码
	 * @return 是否匹配状态
	 */
	boolean isPip(String bianm);
}
