package com.zhiren.fuelmis.dc.dao.hetgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.hetgl.PriceComponent;

@Repository("priceComponetDao")
public interface PriceComponetDao {

	public int addPriceComponet(PriceComponent priceComponent);

	public int updatePriceComponet(PriceComponent priceComponent);

	public List<Map<String, Object>> getPriceComponetList(Map<String, Object> map);

	public void delPriceComponet(Map<String, Object> map);

	public List<PriceComponent> getPriceComponetById(Map<String, Object> map);

}
