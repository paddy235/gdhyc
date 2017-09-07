package com.zhiren.fuelmis.dc.service.kucgl.baobcx;

import net.sf.json.JSONArray;

//import javax.mail.internet.NewsAddress;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/24.
 */
public interface IKucbbcxService {
	/**
	 * 发电供热出库耗用报表
	 * 
	 * @param map
	 * @return
	 */
	JSONArray getFadgrckhybb(Map<String, Object> map);

	/**
	 * 实施成本核算报表
	 * 
	 * @param map
	 * @return
	 */

	JSONArray getShiscbhsbb(Map<String, Object> map);

	/**
	 * 出库单报表
	 * 
	 * @param map
	 * @return
	 */
	JSONArray getChukdbb(Map<String, Object> map);

	/**
	 * 日常来耗存报表
	 * 
	 * @param map
	 * @return
	 */
	JSONArray getRiclhcbb(Map<String, Object> map);

	JSONArray getRiclhcbbmx(Map<String, Object> map);
	/**
	 * 出库查询报表
	 * @param map
	 * @return
	 */
	JSONArray getChukcxbb(Map<String, Object> map);

}
