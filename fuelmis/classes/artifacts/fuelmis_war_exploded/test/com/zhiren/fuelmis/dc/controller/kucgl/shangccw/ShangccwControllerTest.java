package com.zhiren.fuelmis.dc.controller.kucgl.shangccw;

import static org.junit.Assert.*;

import org.junit.Test;

import com.zhiren.fuelmis.dc.controller.kucgl.shangccw.ShangccwController;

public class ShangccwControllerTest {

	@Test
	public void testGetXMLMap() {
		String  resulXML=  
				   "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
						   " <response_info>\n" + 
						   "   <return_info>\n" + 
						   "     <gk_billid>GD-JS-dsrd-201608-17</gk_billid>\n" + 
						   "     <rsp_code>1</rsp_code>\n" + 
						   "     <rsp_msg>第三方系统至财务管控-映射(往来单位映射)[管控类型:5069,第三方类型:vendor_class,映射原始值:129532,映射字段:/itemList/shdwbm]未找到 第三方系统至财务管控-管控映射(往来单位映射)[管控表名 = xtgldx5069,管控ID字段 = dxid,第三方ID字段 = dxdm,映射原始值 = 129532,映射字段:/itemList/shdwbm] 未找到</rsp_msg>\n" + 
						   "   </return_info>\n" + 
						   " </response_info>";
		ShangccwController sc=	new ShangccwController();
		sc.getXMLMap(resulXML);
	}

}
