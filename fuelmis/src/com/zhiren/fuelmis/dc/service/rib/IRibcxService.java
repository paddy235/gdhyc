package com.zhiren.fuelmis.dc.service.rib;

import java.util.Map;

/**
 * @author 摧枯拉朽cococa
 */
public interface IRibcxService {
	
	String getXitxxItem(Map<String, Object> map);
	
	String findzhi(String diancid );
	
	String getShouhc(String diancid,String kaisrq,String jiesrq);
	
	String getRipjkc(String diancid,String kaisrq,String jiesrq);
	
	String getShouhc_zhoub(String diancid,String kaisrq,String jiesrq);
	
	String getShouhcDetail(String diancid,String kaisrq,String jiesrq,String baobleix);
	
	String getShouhc_zhoubDetail(String diancid,String kaisrq,String jiesrq,String baobleix);
}
