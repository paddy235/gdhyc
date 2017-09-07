package com.zhiren.fuelmis.dc.dao.gongyspg.yuegmjh;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("yuegmjhDao")
public interface YuegmjhDao {

	public List<Map<String, Object>> getYuegmjhList(Map<String, Object> map);

	public int addYuegmjh(Map<String, Object> map);

	public int delYuegmjh(Map<String, Object> map);

	public List<Map<String, Object>> searchYuegmjh(Map<String, Object> map);

	public String getHetzt(Map<String, Object> map);

	public int updateYuegmjh(Map<String, Object> yuegongmjh);


	public void insertYuegmjh(Map<String, Object> map);

	public void updateYuegmjhmx(Map<String, Object> map);

	public String getYuegmjhId(Map<String, Object> yuegongmjh);

	public void insertYuegmjhmx(Map<String, Object> map);

	public void submit(Map<String, Object> c);

	public int delYuegmjhmx(Map<String, Object> map);

}
