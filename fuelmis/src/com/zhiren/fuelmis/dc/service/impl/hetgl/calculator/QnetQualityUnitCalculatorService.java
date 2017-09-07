package com.zhiren.fuelmis.dc.service.impl.hetgl.calculator;

import com.zhiren.fuelmis.dc.dao.hetgl.PriceQalityRangeDao;
import com.zhiren.fuelmis.dc.dao.hetgl.PriceSchemeDao;
import com.zhiren.fuelmis.dc.entity.hetgl.PriceBean;
import com.zhiren.fuelmis.dc.entity.hetgl.PriceQalityRange;
import com.zhiren.fuelmis.dc.entity.hetgl.PriceScheme;
import com.zhiren.fuelmis.dc.service.hetgl.Icalculator;
import com.zhiren.fuelmis.dc.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("qnetQualityUnitCalculatorService")
public class QnetQualityUnitCalculatorService implements Icalculator {
	
	@Autowired
	private PriceSchemeDao priceSchemeDao;
	
	@Autowired
	private PriceQalityRangeDao priceQalityRangeDao;
	
	
	
	@Override
	public boolean create(String po_sub_id) {
		return false;
	}
		
	/**
	 * po_price_scheme_id 采购订单表ID
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PriceBean computePrice(String po_price_scheme_id, Map mapParas) {
		String range_min="";//热值下限字符
		String range_max="";//热值上限限字符
		String unitPrice="0";//价格字符

		String quality_item_id="0";
		double dblRange_min=0;//热值下限值
		double dblRange_max=0;//热值上限值
		double dblUnitPrice=0;//价格值

		boolean rangeIsFound=false;//是否找到热值对应的范围
		PriceBean priceBean=new PriceBean();


		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", po_price_scheme_id);
		List<PriceScheme> list = priceSchemeDao.getPriceSchemeById(map);

		PriceScheme priceScheme = null;
		if(list.size()>0){
			priceScheme = list.get(0);
		}
		quality_item_id = priceScheme.getPrice_item_id().toString();

		map.clear();
		String componetId = priceScheme.getPrice_component_id().toString();

		//根据指标的id作为参数的名称,得到指标的值
		double quality_item_vlaue = getParameterValue(mapParas,quality_item_id);

		try {
			//按质计价的表数据
			map.put("id", componetId);
			List<PriceQalityRange> dataList = priceQalityRangeDao.getPriceQalityRangeByComponentId(map);
			if (dataList.size()<=0){
				priceBean.errMsg="没有找到指标的模板数据，计算失败！";
				return priceBean;
			}

			//循环遍历模板数据每一行数据，找到热值对应的范围及价格
			for (PriceQalityRange priceQalityRange : dataList) {
				range_min = priceQalityRange.getRange_min().toString();
				range_max = priceQalityRange.getRange_max().toString();
				unitPrice = priceQalityRange.getUnitprice().toString();

				//如果，上限，下限都为空，忽略
				if (!("NULL".equalsIgnoreCase(range_min)  && "NULL".equalsIgnoreCase(range_max))){
					if ("NULL".equalsIgnoreCase(range_min)){
						//下限为空，逻辑为 数据《=上限
						range_min="";
						dblRange_max=Util.toDouble(range_max,0);
						if (quality_item_vlaue<=dblRange_max){
							rangeIsFound=true;
							break;
						}
					}else if ("NULL".equalsIgnoreCase(range_max)){
						//上限为空  逻辑为 数据>=下限
						range_max="";
						dblRange_min=Util.toDouble(range_min,0);
						if (quality_item_vlaue>=dblRange_min){
							rangeIsFound=true;
							break;
						}
					}else {
						//上限，下限都有数据  数据>=下限 且  数据《=上限
						dblRange_max=Util.toDouble(range_max,0);
						dblRange_min=Util.toDouble(range_min,0);

						if  (quality_item_vlaue>=dblRange_min &&  quality_item_vlaue<=dblRange_max){
							rangeIsFound=true;
							break;
						}
					}
				}
			}

			//找到符合的范围，计算价格
			if (rangeIsFound){
				dblUnitPrice=Util.toDouble(unitPrice,0);
				//发热量*价格
				priceBean.price=dblUnitPrice*quality_item_vlaue;
				priceBean.isSuccess=true;
				priceBean.rangeMin=range_min;
				priceBean.rangeMax=range_max;
			}else{
				priceBean.errMsg="没有找到符合范围的价格，计算失败！";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return priceBean;
	}

	private double getParameterValue(Map<String,Double> aMap,String name){
		int mapsize = aMap.size();
		Double value = null;
		for (int i = 0; i < mapsize; i++){
			value=aMap.get(name);
		}
		return value.doubleValue();
	}
	
	public boolean delete(String transactionID, String priceItem) {
		return false;
	}
	
	public String getShowHtml(String po_price_scheme_id) {
		return null;
	}


  		
}

