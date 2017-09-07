package com.zhiren.fuelmis.dc.dao.kucgl;

import java.util.List;
import java.util.Map;

import com.zhiren.fuelmis.dc.entity.kucgl.ChurkBean;

import org.springframework.stereotype.Repository;

/**
 * @author 孙志涛
 */
@Repository
public interface KucsscbDao {
	List<Map<String, Object>> getKucsscbAll(Map<String, Object> map);

	ChurkBean getBaseChurkBean();

	List<ChurkBean> getNewChurkBeans(String rukdbh);

	/**
	 * 保存出入库信息
	 * 
	 * @param c
	 */
	void saveChurkBean(ChurkBean c);

	/**
	 * 根据日期删除出入库信息(rl_kc_danwcbslb)
	 * 
	 * @param riq
	 */
	void deleteChurkBeans(String riq);

	/**
	 * 更新出库出入库信息
	 * 
	 * @param churkBean
	 */
	void updateChurkBean(ChurkBean churkBean);

	void deleteChurkBeanByID(String id);
}
