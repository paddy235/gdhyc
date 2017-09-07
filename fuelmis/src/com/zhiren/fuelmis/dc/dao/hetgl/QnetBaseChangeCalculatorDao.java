package com.zhiren.fuelmis.dc.dao.hetgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.hetgl.PriceQalityRange;

@Repository("qnetBaseChangeCalculatorDao")
public interface QnetBaseChangeCalculatorDao {

	public String getItemID(Map<String,Object> map);

	public List<PriceQalityRange> getTemplateData(Map<String,Object> map);

}
