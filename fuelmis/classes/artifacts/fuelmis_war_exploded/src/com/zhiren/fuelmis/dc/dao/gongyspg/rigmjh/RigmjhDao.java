package com.zhiren.fuelmis.dc.dao.gongyspg.rigmjh;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("rigmjhDao")
public interface RigmjhDao {

	List searchRigmjhList(Map<String, Object> map);

	int updateRigmjh(Map<String, Object> map);

	int insertRigmjh(Map<String, Object> map);

	int delRigmjh(Long[] arr);

	int publishRigmjh(Long[] arr);

}
