package com.zhiren.fuelmis.dc.dao.kucgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.kucgl.PandGdjt;

/** 
 * @author 陈宝露
 */
@Repository
public interface ShujlrDao {
	List<PandGdjt> getAll(Map<String,Object> map);
	
	int insertPandxx(PandGdjt pandGdjt);
	
	int updatePandxx(Map<String,Object> map);
	
	int updateFuj(Map<String,Object> map);
	
	int getCount(Map<String,Object> map);
}
