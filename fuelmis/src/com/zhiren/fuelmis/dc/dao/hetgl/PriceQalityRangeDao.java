package com.zhiren.fuelmis.dc.dao.hetgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.hetgl.PriceQalityRange;

@Repository("priceQalityRangeDao")
public interface PriceQalityRangeDao {

	public int addPriceQalityRange(PriceQalityRange priceQalityRange);

	public int updatePriceQalityRange(PriceQalityRange priceQalityRange);

	public List<Map<String, Object>> getPriceQalityRangeList(Map<String, Object> map);

	public void delPriceQalityRange(Map<String, Object> map);

	public List<PriceQalityRange> getPriceQalityRangeById(Map<String, Object> map);

	public List<PriceQalityRange> getPriceQalityRangeByComponentId(Map<String, Object> map);

}
