package com.zhiren.fuelmis.dc.service.impl.hetgl;

import com.zhiren.fuelmis.dc.service.hetgl.Icalculator;

/**
 * 
 * @author ZY
 *
 */
public class CalculatorFactory {
	/**
	 * 根据类名成创建类实例
	 * @param calculatorClassName 计价组件类名
	 * @return 计价组件
	 */
    @SuppressWarnings("rawtypes")
	public Icalculator getCalculator(String calculatorClassName){
        try {
            Class classCalculator = Class.forName(calculatorClassName);
            Icalculator cal =(Icalculator)classCalculator.newInstance();
            return cal;
        }catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
}
