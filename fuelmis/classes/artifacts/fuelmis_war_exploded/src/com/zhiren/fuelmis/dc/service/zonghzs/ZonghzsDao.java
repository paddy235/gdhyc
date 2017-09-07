package com.zhiren.fuelmis.dc.service.zonghzs;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * @author 孟建云
 */
@Repository
public interface ZonghzsDao {
	/**
	 * 浮动信息展示
	 */
	List<Map<String, Object>> getRanlxxgdts();
	/**
	 * 分矿信息展示
	 */
	List<Map<String, Object>> getFenklmxxData();
	/**
	 * 分矿来煤量
	 */
	List<Map<String, Object>> getFenklmcolumnData();
	/**
	 * 库存情况
	 */
	List<Map<String, Object>> getKucjg(String yearmonth);
	/**
	 * 库存情况曲线图数据
	 */
	List<Map<String, Object>> getKucqkqxData(String qsriq, String jzriq);
	/**
	 * 来耗煤情况曲线数据
	 */
	List<Map<String, Object>> getLaihmqkqxData(String qsriq, String jzriq);
	/**
	 * 库存煤曲线数据
	 */
	List<Map<String, Object>> getKucmqx(String riq);
	/**
	 * 来耗存日报报表数据
	 */
	List<Map<String, Object>> getLaihcrbGridData(String riq);
	/**
	 * 饼图供应商
	 */
	List<Map<String, Object>> bingtgys(String yearmonth);
	/**
	 * 饼图煤矿
	 */
	List<Map<String, Object>> bingtmk(String yearmonth);
	/**
	 * 饼图煤种
	 */
	List<Map<String, Object>> bingtmz(String yearmonth);
	/**
	 * 来煤信息
	 */
	List<Map<String, Object>> getLaimxx(String riq);
	/**
	 * 入厂标煤单价
	 */
	List<Map<String, Object>> getRucbmdj(String riq, String jzriq);
	/**
	 * 入厂天然煤价
	 */
	List<Map<String, Object>> getRuctrmj(String riq, String jzriq);
	/**
	 * 日入厂标煤单价
	 */
	List<Map<String, Object>> getDaysofRucbmdj(String riq, String jzriq);
	/**
	 * Grid数据
	 */
	List<Map<String, Object>> gridLoad(String qsriq, String jzriq);
	/**
	 * 线图数据
	 */
	List<Map<String, Object>> chartLoad(String qsriq, String jzriq);
	/**
	 * 饼图口径
	 */
	List<Map<String, Object>> bingtkj(String yearmonth);
	
}
