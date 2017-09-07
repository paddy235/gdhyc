package com.zhiren.fuelmis.dc.dao.kucgl;

import java.util.List;
import java.util.Map;

/** 
 * 	燃煤暂估明细表
* @author 作者 : Liujiayu
* @version 创建时间：2017年6月12日 上午10:06:12 
* 类说明 
*/
public interface RanmzgDao {


	List<Map<String, Object>> getRanmzg(Map<String, Object> map);

	String getRanmzgId(Map<String, Object> map);
	
}
