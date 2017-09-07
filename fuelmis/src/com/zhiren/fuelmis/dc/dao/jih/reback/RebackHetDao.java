package com.zhiren.fuelmis.dc.dao.jih.reback;

import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 合同审批回退dao
 * @author ZY
 *
 */
@Repository
public interface RebackHetDao {

	@SuppressWarnings("rawtypes")
	public void rebackHet(Map map);

}
