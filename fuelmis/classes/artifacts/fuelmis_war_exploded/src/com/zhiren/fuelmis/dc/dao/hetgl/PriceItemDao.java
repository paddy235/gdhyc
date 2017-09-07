package com.zhiren.fuelmis.dc.dao.hetgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.hetgl.PriceItem;

@Repository("priceItemDao")
public interface PriceItemDao {

	public int addPriceItem(PriceItem priceItem);

	public int updatePriceItem(PriceItem priceItem);

	public List<Map<String, Object>> getPriceItemList(Map<String, Object> map);

	public void delPriceItem(Map<String, Object> map);

	public List<PriceItem> getPriceItemById(Map<String, Object> map);

}
