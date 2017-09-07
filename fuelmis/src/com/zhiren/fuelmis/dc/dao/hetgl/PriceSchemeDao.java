package com.zhiren.fuelmis.dc.dao.hetgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.hetgl.PriceScheme;

@Repository("priceSchemeDao")
public interface PriceSchemeDao {

    int addPriceScheme(PriceScheme priceScheme);

    int updatePriceScheme(PriceScheme priceScheme);

    List<Map<String, Object>> getPriceSchemeList(Map<String, Object> map);

    void delPriceScheme(Map<String, Object> map);

    List<PriceScheme> getPriceSchemeById(Map<String, Object> map);

    List<PriceScheme> getPriceSchemeByCaigddId(Map<String, Object> map);


    void insetScheme(Map<String, Object> map);

    void updateScheme(Map<String, Object> map);
}
