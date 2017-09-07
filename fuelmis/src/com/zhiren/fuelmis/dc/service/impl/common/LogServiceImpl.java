package com.zhiren.fuelmis.dc.service.impl.common;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.service.common.ILogService;
import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.Sequence;

/** 
 * @author 陈宝露
 */
@Service
public class LogServiceImpl implements ILogService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void insertLog(String tableName, String className,
			String methodName, String logs, String str1, String str2,
			String str3, String str4, String str5) {
		// TODO Auto-generated method stub
		try{
			String sql = "insert into rl_cm_logs(ID,TABLE_NAME,CLASS_NAME,METHOD_NAME,LOG,TIME";
			if(!"".equals(str1)&&str1!=null) sql += ",REMARK1";
			if(!"".equals(str2)&&str2!=null) sql += ",REMARK2";
			if(!"".equals(str3)&&str3!=null) sql += ",REMARK3";
			if(!"".equals(str4)&&str4!=null) sql += ",REMARK4";
			if(!"".equals(str5)&&str5!=null) sql += ",REMARK5";
			sql += ") values (";
			sql += Sequence.nextId()+",'";
			sql += tableName+"','";
			sql += className+"','";
			sql += methodName+"','";
			sql += logs.length()>=4000?logs.substring(0,3999):logs+"','";
			sql += DateUtil.format(new Date())+"'";
			if(!"".equals(str1)&&str1!=null) sql += ",'"+str1+"'";
			if(!"".equals(str2)&&str2!=null) sql += ",'"+str2+"'";
			if(!"".equals(str3)&&str3!=null) sql += ",'"+str3+"'";
			if(!"".equals(str4)&&str4!=null) sql += ",'"+str4+"'";
			if(!"".equals(str5)&&str5!=null) sql += ",'"+str5+"'";
			sql += ")";
			jdbcTemplate.update(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
