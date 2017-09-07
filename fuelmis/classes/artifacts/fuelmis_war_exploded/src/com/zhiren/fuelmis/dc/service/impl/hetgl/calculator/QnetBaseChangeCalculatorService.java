package com.zhiren.fuelmis.dc.service.impl.hetgl.calculator;

import com.zhiren.fuelmis.dc.dao.hetgl.QnetBaseChangeCalculatorDao;
import com.zhiren.fuelmis.dc.entity.hetgl.PriceBean;
import com.zhiren.fuelmis.dc.entity.hetgl.PriceQalityRange;
import com.zhiren.fuelmis.dc.entity.hetgl.PriceScheme;
import com.zhiren.fuelmis.dc.service.hetgl.Icalculator;
import com.zhiren.fuelmis.dc.service.hetgl.PriceSchemeService;
import com.zhiren.fuelmis.dc.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class QnetBaseChangeCalculatorService implements Icalculator {

	@Autowired
	private QnetBaseChangeCalculatorDao qnetBaseChangeCalculatorDao;
	
	@Autowired
	private PriceSchemeService priceSchemeService;
	
	@Override
	public boolean create(String po_sub_id) {
		return false;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PriceBean computePrice(String po_price_scheme_id, Map mapParas) {
		String range_min="";//��ֵ�����ַ�
		String range_max="";//��ֵ�������ַ�
		String rang_standard="";//���۱�׼�ַ�
		String base_price="";//��׼�۸��ַ�
		String unitPrice="0";//�۸��ַ�

		String quality_item_id="0";
		double dblRange_min=0;//��ֵ����ֵ
		double dblRange_max=0;//��ֵ����ֵ
		double dblRang_standard=0;//���۱�׼ֵ
		double dblBase_price=0;//��׼�۸�ֵ
		double dblUnitPrice=0;//�۸�ֵ

		boolean rangeIsFound=false;//�Ƿ��ҵ���ֵ��Ӧ�ķ�Χ
		PriceBean priceBean=new PriceBean();

		//�õ�ָ��ID
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("po_price_scheme_id", po_price_scheme_id);
		quality_item_id = qnetBaseChangeCalculatorDao.getItemID(map);

		map.clear();
		map.put("id", po_price_scheme_id);
		PriceScheme priceScheme = priceSchemeService.getPriceSchemeByCaigddId(map);

		String componetId = priceScheme.getPrice_component_id().toString();

		//����ָ���id��Ϊ����������,�õ�ָ���ֵ
		double quality_item_vlaue = getParameterValue(mapParas,quality_item_id);

		try {
			//���ʼƼ۵ı�����
			//List list = qnetBaseChangeCalculatorDao.getTemplateData(po_price_scheme_id);
			Map pMap = new HashMap<String, Object>();
			pMap.put("componentid", componetId);
			List<PriceQalityRange> list = qnetBaseChangeCalculatorDao.getTemplateData(map);
			if (list.size()<=0){
				priceBean.errMsg="û���ҵ�ָ���ģ�����ݣ�����ʧ�ܣ�";
				return priceBean;
			}
			/****************************************��ṹŪ���� ���� ��ʱ����********************************/

			//ѭ������ģ������ÿһ�����ݣ��ҵ���ֵ��Ӧ�ķ�Χ���۸�
			for (PriceQalityRange priceQalityRange : list) {
				range_min = String.valueOf(priceQalityRange.getRange_min());
				range_max = String.valueOf(priceQalityRange.getRange_max());
				rang_standard = String.valueOf(priceQalityRange.getUnitprice());
			}
			Iterator iter = list.iterator();
			while(iter.hasNext()){
				HashMap record = (HashMap) iter.next();
				range_min=(String)record.get("RANGE_MIN");
				range_max=(String)record.get("RANGE_MAX");
				rang_standard=(String)record.get("RANGE_STANDARD");
				base_price=(String)record.get("BASE_PRICE");
				unitPrice=(String)record.get("UNITPRICE");

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
						dblRange_min=Util.toDouble(range_min,0);
						dblRange_max=Util.toDouble(range_max,0);
						if  (quality_item_vlaue>=dblRange_min &&  quality_item_vlaue<=dblRange_max){
							rangeIsFound=true;
							break;
						}
					}
				}
			}
			//�ҵ����ϵķ�Χ������۸�
			if (rangeIsFound){
				dblRang_standard=Util.toDouble(rang_standard,0);
				dblBase_price=Util.toDouble(base_price,0);
				dblUnitPrice=Util.toDouble(unitPrice,0);

				//(������-����������׼)*���ۼ۸�+��׼�۸�
				priceBean.price=dblBase_price+dblUnitPrice*(quality_item_vlaue-dblRang_standard);
				priceBean.priceChange=dblUnitPrice*(quality_item_vlaue-dblRang_standard);
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
