package com.zhiren.fuelmis.dc.utils;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.DateUtil.DateFormatType;

public class DateUtilTest {

	@Test
	public void testFormatDateDateFormatType() {
		System.out.println(DateUtil.format(new Date(), DateFormatType.SIMPLE_TYPE));
	}

}
