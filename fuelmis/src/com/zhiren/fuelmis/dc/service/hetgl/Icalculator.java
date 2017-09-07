package com.zhiren.fuelmis.dc.service.hetgl;
import java.util.Map;
import com.zhiren.fuelmis.dc.entity.hetgl.PriceBean;

/**
 * 
 * @author ZY
 *
 */
public interface Icalculator {

    boolean create(String po_sub_id);
    /**
     * 计算价格 
     * @param po_price_scheme_id 计价方案行ID
     * @param mapParas 参数 （名称，值），现阶段只用一个输入参数 指标的ID和指标值，考虑将来的扩展定义成Map
     * @return  PriceBean 价格计算结果Bean
     */
    PriceBean computePrice(String po_price_scheme_id,Map<String,Object> mapParas);//计算指标价格
    boolean delete(String po_sub_id,String price_component_id);//删除某个指标的模板数据
}
