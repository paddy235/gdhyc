package com.zhiren.fuelmis.dc.dao.hetgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.hetgl.PriceQalityRange;

@Repository("qnetQualityUnitCalculatorDao")
public interface QnetQualityUnitCalculatorDao {

	public List<PriceQalityRange> getTemplateData(Map<String, Object> map);

	public List<PriceQalityRange> getQnetQualityUnitById(Map<String, Object> map);

}
