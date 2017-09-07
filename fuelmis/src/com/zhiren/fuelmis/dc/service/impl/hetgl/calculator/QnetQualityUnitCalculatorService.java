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
	 * po_price_scheme_id �ɹ�������ID
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PriceBean computePrice(String po_price_scheme_id, Map mapParas) {
		String range_min="";//��ֵ�����ַ�
		String range_max="";//��ֵ�������ַ�
		String unitPrice="0";//�۸��ַ�

		String quality_item_id="0";
		double dblRange_min=0;//��ֵ����ֵ
		double dblRange_max=0;//��ֵ����ֵ
		double dblUnitPrice=0;//�۸�ֵ

		boolean rangeIsFound=false;//�Ƿ��ҵ���ֵ��Ӧ�ķ�Χ
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

		//����ָ���id��Ϊ����������,�õ�ָ���ֵ
		double quality_item_vlaue = getParameterValue(mapParas,quality_item_id);

		try {
			//���ʼƼ۵ı�����
			map.put("id", componetId);
			List<PriceQalityRange> dataList = priceQalityRangeDao.getPriceQalityRangeByComponentId(map);
			if (dataList.size()<=0){
				priceBean.errMsg="û���ҵ�ָ���ģ�����ݣ�����ʧ�ܣ�";
				return priceBean;
			}

			//ѭ������ģ������ÿһ�����ݣ��ҵ���ֵ��Ӧ�ķ�Χ���۸�
			for (PriceQalityRange priceQalityRange : dataList) {
				range_min = priceQalityRange.getRange_min().toString();
				range_max = priceQalityRange.getRange_max().toString();
				unitPrice = priceQalityRange.getUnitprice().toString();

				//��������ޣ����޶�Ϊ�գ�����
				if (!("NULL".equalsIgnoreCase(range_min)  && "NULL".equalsIgnoreCase(range_max))){
					if ("NULL".equalsIgnoreCase(range_min)){
						//����Ϊ�գ��߼�Ϊ ���ݡ�=����
						range_min="";
						dblRange_max=Util.toDouble(range_max,0);
						if (quality_item_vlaue<=dblRange_max){
							rangeIsFound=true;
							break;
						}
					}else if ("NULL".equalsIgnoreCase(range_max)){
						//����Ϊ��  �߼�Ϊ ����>=����
						range_max="";
						dblRange_min=Util.toDouble(range_min,0);
						if (quality_item_vlaue>=dblRange_min){
							rangeIsFound=true;
							break;
						}
					}else {
						//���ޣ����޶�������  ����>=���� ��  ���ݡ�=����
						dblRange_max=Util.toDouble(range_max,0);
						dblRange_min=Util.toDouble(range_min,0);

						if  (quality_item_vlaue>=dblRange_min &&  quality_item_vlaue<=dblRange_max){
							rangeIsFound=true;
							break;
						}
					}
				}
			}

			//�ҵ����ϵķ�Χ������۸�
			if (rangeIsFound){
				dblUnitPrice=Util.toDouble(unitPrice,0);
				//������*�۸�
				priceBean.price=dblUnitPrice*quality_item_vlaue;
				priceBean.isSuccess=true;
				priceBean.rangeMin=range_min;
				priceBean.rangeMax=range_max;
			}else{
				priceBean.errMsg="û���ҵ����Ϸ�Χ�ļ۸񣬼���ʧ�ܣ�";
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

