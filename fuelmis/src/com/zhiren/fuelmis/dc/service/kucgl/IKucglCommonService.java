package com.zhiren.fuelmis.dc.service.kucgl;

import java.util.List;

/** 
 * @author 陈宝露
 */
public interface IKucglCommonService {
	/**
	 * 入库记账
	 * @param idLst 出入库单ID
	 * @return
	 */
	int rukjz(List<Long> idLst);
	
	/**
	 * 计算库存余额
	 * @param idLst 库存明细ID
	 * @return
	 */
	int kucye(List<Long> idLst);
	
	/**
	 * 库存结转
	 * @param kuaijrq 会计日期
	 * @return
	 */
	int kucjz(String kuaijrq);
	
	/**
	 * 打开会计日期
	 * @param kuaijrq 会计日期
	 * @return
	 */
	int openKuaijq(String kuaijrq);
}
