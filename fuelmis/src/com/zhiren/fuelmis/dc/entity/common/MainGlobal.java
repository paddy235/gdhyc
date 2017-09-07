package com.zhiren.fuelmis.dc.entity.common;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;





import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.utils.CustomMaths;



/**
 * @author 王磊
 * 
 */
/*
 * 2009-04-16
 * 王磊
 * 增加getOpenWinScript 新方法增加高宽的设置，并且默认高宽为800*600
 */
/*
 * 作者：王磊
 * 时间：2009-12-21
 * 描述：增加 addStr2ListNorepeat 方法
 */
/*
 * 作者：王磊
 * 时间：2009-12-24
 * 描述：增加新建ID方法 getNewid(JDBCcon con,String diancxxb_id) 
 */
@Component
public class MainGlobal {	
	private static JdbcTemplate jdbcTemplate;
//	数据库连接方式
	private static String db_type = "";
//	数据库连接池名称 （连接池）
	private static String db_jndiname = "";
//	数据库连接URL （JDBC直连）
	private static String db_jdbcDriverURL = "";
//	数据库用户名
	private static String db_username = "";
//	数据库密码
	private static String db_password = "";	
//	系统默认超级用户名
	public static String superUserName = "";
//	系统默认超级用户密码
	public static String superUserPWD = "";
//	反应系统当前的级别2为集团1为区域0为电厂
	private static String SystemLeib = "";
//	系统样式颜色
	private static String StyleColor = "";
//	登录页面logo
	private static String LogoPath = "";
	
	private static String Cheb="";
	
	//SQL SERVER数据库连接URL（JDBC直连）
	private static String db_jdbcDriverURL_SQLSERVER = "";
	//SQL SERVER数据库用户名
	private static String db_username_SQLSERVER = "";
	//SQL SERVER数据库密码
	private static String db_password_SQLSERVER = "";
	//SQL SERVER数据库连接方式
	private static String db_type_SQLSERVER = "";
	//SQLSERVER数据库连接池名称
	private static String db_jndiname_SQLSERVER = "";
	//SQL SERVER数据库驱动名称
	private static String db_DriverName_SQLSERVER="";
	
	public static String getDb_DriverName_SQLSERVER() {
		if(db_DriverName_SQLSERVER.equals("")) {
			init();
		}
		return db_DriverName_SQLSERVER;
	}
	
	public static String getDb_jdbcDriverURL_SQLSERVER() {
		if(db_jdbcDriverURL_SQLSERVER.equals("")) {
			init();
		}
		return db_jdbcDriverURL_SQLSERVER;
	}
	
	public static String getDb_password_SQLSERVER() {
		if(db_password_SQLSERVER.equals("")) {
			init();
		}
		return db_password_SQLSERVER;
	}

	public static String getDb_username_SQLSERVER() {
		if(db_username_SQLSERVER.equals("")) {
			init();
		}
		return db_username_SQLSERVER;
	}
	public static String getDb_type_SQLSERVER() {
		if(db_type_SQLSERVER.equals("")) {
			init();
		}
		return db_type_SQLSERVER;
	}
	
	public static String getDb_jndiname_SQLSERVER() {
		if(db_jndiname_SQLSERVER.equals("")) {
			init();
		}
		return db_jndiname_SQLSERVER;
	}
	
	public static String getLogoPath() {
		if("".equals(LogoPath)) {
			init();
		}
		return LogoPath;
	}
	
	public static String getStyleColor() {
		if(StyleColor.equals("")) {
			init();
		}
		return StyleColor;
	}
	public static void setStyleColor(String styleColor) {
		StyleColor = styleColor;
	}
	
	public static String getDb_jdbcDriverURL() {
		if(db_jdbcDriverURL.equals("")) {
			init();
		}
		return db_jdbcDriverURL;
	}
	
	public static String getDb_type() {
		if(db_type.equals("")) {
			init();
		}
		return db_type;
	}
	
	public static String getDb_jndiname() {
		if(db_jndiname.equals("")) {
			init();
		}
		return db_jndiname;
	}

	public static String getDb_password() {
		if(db_password.equals("")) {
			init();
		}
		return db_password;
	}

	public static String getDb_username() {
		if(db_username.equals("")) {
			init();
		}
		return db_username;
	}

	public static String getSuperUserName() {
		if(superUserName.equals("")) {
			init();
		}
		return superUserName;
	}

	public static String getSuperUserPWD() {
		if(superUserPWD.equals("")) {
			init();
		}
		return superUserPWD;
	}

	public static String getSystemLeib() {
		if(SystemLeib.equals("")) {
			init();
		}
		return SystemLeib;
	}

	public static void init() {
        String projectName = getProjectName();
		File file = new File(getProjectAbsolutePath(),"SystemInstall.xml");
		if(file.exists()) {
			try {
				SAXBuilder builder = new SAXBuilder();
				FileInputStream fiss = new FileInputStream(file);
				Document docw = builder.build(fiss);
				Element root = docw.getRootElement();
				List<?> elist = root.getChildren();
				for(int i =0;i < elist.size() ; i++){
					Element ehead = (Element) elist.get(i);
					if(ehead.getName().equalsIgnoreCase(projectName) &&
							getSystemHashCode(ehead.getChildText("ValidateCode"))){
							db_jdbcDriverURL = ehead.getChildText("JdbcDriverURL");
							db_username = ehead.getChildText("DataBaseUserName");
							db_password = ehead.getChildText("DataBasePassWord");
							superUserName = ehead.getChildText("SuperUserName");
							superUserPWD = ehead.getChildText("SuperPassWord");
							SystemLeib = ehead.getChildText("SystemLeib");
							StyleColor = ehead.getChildText("StyleColor");
							LogoPath = ehead.getChildText("LogoPath");
							db_type = ehead.getChildText("ConnectionType");
							db_jndiname = ehead.getChildText("JndiName");
							
							db_jdbcDriverURL_SQLSERVER = ehead.getChildText("SqlServerJdbcDriverURL");
							db_username_SQLSERVER = ehead.getChildText("SqlServerDataBaseUserName");
							db_password_SQLSERVER = ehead.getChildText("SqlServerDataBasePassWord");
//							superUserName_SQLSERVER = ehead.getChildText("SuperUserName");
//							superUserPWD_SQLSERVER = ehead.getChildText("SuperPassWord");
//							SystemLeib_SQLSERVER = ehead.getChildText("SystemLeib");
//							StyleColor_SQLSERVER = ehead.getChildText("StyleColor");
							db_DriverName_SQLSERVER = ehead.getChildText("SqlServerDriverName");
							db_type_SQLSERVER = ehead.getChildText("SqlServerConnectionType");
							db_jndiname_SQLSERVER = ehead.getChildText("SqlServerJndiName");
					}
				}
				/*if(root.getName().equals("InstallInfo")) {
					for (int eindex = 0; eindex < elist.size(); eindex++) {
						Element ehead = (Element) elist.get(eindex);
						db_jdbcDriverURL = ehead.getChildText("JdbcDriverURL");
						db_username = ehead.getChildText("DataBaseUserName");
						db_password = ehead.getChildText("DataBasePassWord");
						superUserName = ehead.getChildText("SuperUserName");
						superUserPWD = ehead.getChildText("SuperPassWord");
						SystemLeib = ehead.getChildText("SystemLeib");
						StyleColor = ehead.getChildText("StyleColor");
						LogoPath = ehead.getChildText("LogoPath");
						db_type = ehead.getChildText("ConnectionType");
						db_jndiname = ehead.getChildText("JndiName");
					}
				}*/
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	public MainGlobal() {
		
	}
	

	
	

	

	


//	新建ID
	public static String getNewID(JdbcTemplate con, String diancxxb_id) {
		String id = "";
		String sql="select xl_xul_id.nextval id from dual";
		ResultSetList rs = con.queryForObject(sql, ResultSetList.class);
		if(rs.next()) {
			id = rs.getString(0);
		}
		
		System.out.println("id="+id);
		System.out.println("diancxxb_id="+diancxxb_id);
		String[] diancxxb_idSplit = diancxxb_id.split(",");
		for (int i = 0; i < diancxxb_idSplit.length; i++) {
			id+=diancxxb_idSplit[i];
		}
		System.out.println("id拼接后="+id);
		return id;
	}
//	新建ID
	public static String getNewID(long diancxxb_id) {
		String id = getNewID(jdbcTemplate, String.valueOf(diancxxb_id));
		return id;
	}
	
//	新建ID
	public static String getNewID(JdbcTemplate con,long diancxxb_id) {
		String id = getNewID(con,String.valueOf(diancxxb_id));
		return id;
	}
	
	public static String getErrMsg(int intErrCode) {
		String message = "未知错误";
		switch(intErrCode){
			case SysConstant.ErrCode_unKnow: message= "未知错误"; break;
			case SysConstant.ErrCode_illLogin: message= "登陆失败"; break;
			case SysConstant.ErrCode_noUser: message= "用户名不存在"; break;
			case SysConstant.ErrCode_errPwd: message= "密码错误"; break;
			case SysConstant.ErrCode_noPower: message= "没有此权限"; break;
			case SysConstant.ErrCode_errdb: message= "数据库连接失败或SQL错误";break;
			case SysConstant.ErrCode_IeVar: message= "要求使用 Internet Explorer 6.0 或更高版本";break;
			
			default : message= "未知错误";
		}
		return message;
	}
	
//	得到对应表id
    public static long getTableId(String tableName,String colName,String name) throws Exception {
        try{
            String strSql="select id from " + tableName + " where " + colName + "='"+name+"'";
            ResultSet rec = (ResultSet) jdbcTemplate.queryForRowSet(strSql);
            if (rec.next()){
                return rec.getLong("id");
            }
        }catch(Exception e) {
              e.printStackTrace();
        }
        finally{
   
        }
        return 0;
    }
//    得到表某一列的值
    public static String getTableCol(String tableName,String getcolName,String colName,String value) throws Exception {
        try{
            String strSql="select "+getcolName+" from " + tableName + " where " + colName + "='"+value+"'";
            ResultSet rec = jdbcTemplate.queryForObject(strSql, ResultSet.class);
            if (rec.next()){
                return rec.getString(getcolName);
            }
            
        }catch(Exception e) {
              e.printStackTrace();
        }
        finally
        {
         
        }
        return "";
    }
    
//  得到表某一列的值
    public static String getTableCol(String tableName,String getcolName,String where) throws Exception {
       
        try{
            String strSql="select "+getcolName+" from " + tableName + " where " + where;
            ResultSet rec = jdbcTemplate.queryForObject(strSql, ResultSet.class);
            if (rec.next()){
                return rec.getString(1);
            }
            
        }catch(Exception e) {
              e.printStackTrace();
        }
        finally
        {
        
        }
        return "";
    }
    
//  得到xitxxb的值
    public static String getXitsz(String duixm, String diancxxb_id, String defaultValue) {

    	String value = defaultValue;
    	try{
			String strSql = "select zhi from xitxxb where mingc='"+duixm+"' and diancxxb_id = "+diancxxb_id;
			ResultSetList rsl =  jdbcTemplate.queryForObject(strSql, ResultSetList.class);
			if(rsl.next()) {
				value = rsl.getString(0);
			}
			rsl.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
    	return value;
    }
    
//    结算时使用验收编号
    public static String getYansbh(){
		
	
			
			try{
				
				String strYansbh="";
 				java.util.Date datCur = new java.util.Date();
	            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
	            String dat =formatter.format(datCur);
 	            int intBh=0;
	            
				String Sql="select max(bianm) as yansbh from yansbhb ";
				ResultSet rs = jdbcTemplate.queryForObject(Sql, ResultSet.class);
				if(rs.next()){
					
					strYansbh=rs.getString("yansbh");
				}
				
				
				if(strYansbh==null){
					
					strYansbh=dat+"0000";
				}
				
				intBh=Integer.parseInt(strYansbh.trim().substring(strYansbh.trim().length()-4,strYansbh.trim().length()));
	            intBh=intBh+1;
	            
	            if(intBh<10000 && intBh>=1000){
	            	strYansbh=dat+"0"+String.valueOf(intBh);
	            }else if(intBh<1000 && intBh>=100){
	            	strYansbh=dat+"00"+String.valueOf(intBh);
	            }else if (intBh<100&&intBh>=10){
	            	strYansbh=dat+"000"+String.valueOf(intBh);
	            }else{
	            	strYansbh=dat+"0000"+String.valueOf(intBh);
	            }
	            
	            return strYansbh;
			}catch(Exception e){
				
				e.printStackTrace();
			}finally{
				
			
			}
		
		return "";
	}
    
//    通过发货表Id得到首尾车号
    public static String getShouwch(String FahbId){
		

		String strSwch="";
		String sql="";
		ResultSet rs=null;
		long fahb_id=0;
		long fahb_id_end=0;
		try{
			
			if(FahbId.lastIndexOf(",")==-1){
				
//				就一个列记录
				sql="select id from fahb f where f.lie_id="+FahbId+" order by fahrq";

				rs=jdbcTemplate.queryForObject(sql,ResultSet.class);
				if(rs.next()){
//					取出fahb_id
					fahb_id=rs.getLong("id");
				}
				
				sql="select shouch||'─'||weich as shouwch from 	\n"
					+ " (select cheph as shouch from chepb where fahb_id="+fahb_id+" and rownum=1 order by id),	\n"
					+ " (select weich from (select cheph as weich from chepb where fahb_id="+fahb_id+" order by id desc) where rownum=1)";
//				得到首尾车号
				rs=jdbcTemplate.queryForObject(sql,ResultSet.class);
				if(rs.next()){
					
					strSwch=rs.getString("shouwch");
				}
				
			}else{
//				有多条记录
				sql=" select shoufahb_id,weifahb_id from "
					+ "(select id as shoufahb_id from fahb f where f.lie_id="+FahbId.substring(0,FahbId.indexOf(","))+" and rownum=1 order by fahrq),	\n"
					+ "(select id as weifahb_id from fahb f where f.lie_id="+FahbId.substring(FahbId.lastIndexOf(",")+1)+" and rownum=1 order by fahrq desc) ";
				rs=jdbcTemplate.queryForObject(sql, ResultSet.class);
				if(rs.next()){
//					取出fahb_id
					fahb_id=rs.getLong("shoufahb_id");
					fahb_id_end=rs.getLong("weifahb_id");
				}
				
				sql="select shouch||'─'||weich as shouwch from 	\n"
					+ " (select cheph as shouch from chepb where fahb_id="+fahb_id+" and rownum=1 order by lursj),	\n"
					+ " (select cheph as weich from chepb where fahb_id="+fahb_id_end+" and rownum=1 order by lursj desc)";
//				得到首尾车号
				rs=jdbcTemplate.queryForObject(sql,ResultSet.class);
				if(rs.next()){
					
					strSwch=rs.getString("shouwch");
				}
			}
            
            
		}catch(Exception e){
			
			e.printStackTrace();
		}finally{
			
			
		}
	
		return strSwch;
    }
    
    public static String getShouwch_Cp(String ChepbId) throws SQLException{
//    	从车皮表中得到首尾车号
    	
    	String shouwch="";
    	String sql="select shouch||'-'||weich as shouwch from\n" +
    			"(select cheph as shouch from shihcpb\n" + 
    			"        where id in ("+ChepbId+") and rownum=1\n" + 
    			"        order by id),\n" + 
    			"(select cheph as weich from shihcpb\n" + 
    			"        where id in ("+ChepbId+") and rownum=1\n" + 
    			"        order by id desc)";

    	ResultSet rs=jdbcTemplate.queryForObject(sql,ResultSet.class);
    	if(rs.next()){
    		
    		shouwch=rs.getString("shouwch");
    	}
    	
    
    	return shouwch;
    }
    
    

	
	
	
	
	public static String getXitxx_item(String leib,String mingc,long diancxxb_id,String defaultValue){
		return getXitxx_item(leib,mingc,String.valueOf(diancxxb_id),defaultValue);
	}
	
	//从系统信息表中取值
	public static String getXitxx_item(String leib,String mingc,String diancxxb_id,String defaultValue){
		
		
		String value=defaultValue;
		try{
			
			String sql="select zhi from xitxxb where leib='"+leib+"' and mingc='"+mingc+"' 	\n"
					+ " 	and diancxxb_id in ("+diancxxb_id+") and beiz='使用'";
			
			ResultSet rs=jdbcTemplate.queryForObject(sql,ResultSet.class);
			if(rs.next()){
				
				value=rs.getString("zhi");
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
		}finally{
			
			
		}
		return value;
	}
	
	//从系统信息表中取值
	public static String getXitxx_item(JdbcTemplate con,String leib,String mingc,String diancxxb_id,String defaultValue){
		
		String value=defaultValue;
		try{
			
			String sql="select zhi from xitxxb where leib='"+leib+"' and mingc='"+mingc+"' 	\n"
					+ " 	and diancxxb_id in ("+diancxxb_id+") and beiz='使用'";
			
			List<Map<String,Object>> rs=con.queryForList(sql);
			if(rs.size()!=0){			
				value=rs.get(0).get("zhi").toString();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return value;
	}
	// *****************取得记录集行数(存在返回记录行数，不存在返回0)********************//
	public synchronized static int getRow(ResultSet rsy) {
		int RsRows = 0;
		try {
			rsy.last();
			RsRows = rsy.getRow();
			rsy.beforeFirst();
		} catch (Exception err) {
			System.out.println("**********************判断数据存在异常**********");
			err.printStackTrace();
			return -1;
		}
		return RsRows;
	}
	public static String[][] getXitxx_items(String leib,String mingc,String diancxxb_id){
		
		
		String value[][]=null;
		try{
			
			String sql="select mingc,zhi from xitxxb where leib='"+leib+"' and mingc in ("+mingc+") 	\n"
					+ " and diancxxb_id in ("+diancxxb_id+") and beiz='使用'";
			
			ResultSet rs=jdbcTemplate.queryForObject(sql,ResultSet.class);
			if(getRow(rs)>0){
				
				value=new String[getRow(rs)][2];
				int i=0;
				while(rs.next()){
					
					value[i][0]=rs.getString("mingc");
					value[i][1]=rs.getString("zhi");
					i++;
				}
			}
			
			
		}catch(Exception e){
			
			e.printStackTrace();
		}finally{
			
		
		}
		return value;
	}
	
	//从发货表中取出最小、最大发货日期
	public static String getFahb_fahrq(String lie_id){
		
		
		String minfahrq="",maxfahrq="",fahrq="";
		
		try{
			
			String sql=" select min(fahrq) as minfahrq,max(fahrq) as maxfahrq from fahb where lie_id in ("+lie_id+") ";
			ResultSet rs=jdbcTemplate.queryForObject(sql,ResultSet.class);
			if(rs.next()){
				
				minfahrq=DateUtil.FormatDate(rs.getDate("minfahrq"));
				maxfahrq=DateUtil.FormatDate(rs.getDate("maxfahrq"));
			}
			
			if(minfahrq.equals(maxfahrq)){
				
				fahrq=minfahrq;
			}else{
				
				fahrq=minfahrq+maxfahrq;
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
		}finally{
			
	
		}
		return fahrq;
	}
	

	
	//得到车别
	public static String getCheb(int Chebb_id){
		
		switch(Chebb_id){
		
			case SysConstant.CHEB_LC:
				
				Cheb= "路车";
				break;
		
			case SysConstant.CHEB_ZB:
				
				Cheb= "自备车";
				break;
			
			case SysConstant.CHEB_QC:
				Cheb= "汽";
				break;
				
			case SysConstant.CHEB_C:
				Cheb= "船";
				break;
				
			default:
				Cheb= "路车";
				break;
		}
		return Cheb;
	}
	
	public static String getExtMessageBox(String msg,boolean isObj) {
		String _msg = "";
		if(msg != null && !"".equals(msg)) {
			if(isObj) {
				_msg = "Ext.MessageBox.alert('提示信息',"+msg+");";
			}else {
				int n = 0;
				n = 16 - msg.getBytes().length;
				for(int i=0;i<n;i++) {
					if(i%2==0) {
						msg = "&nbsp;"+msg;
					}else {
						msg += "&nbsp;";
					}
				}
				_msg = "Ext.MessageBox.alert('提示信息','"+msg+"');";
			}
		}
		return _msg;
	}
	
	public static String getExtMessageShow(String msg,String protext,int waittime) {
		String _msg = "";
		if(msg != null && !"".equals(msg)) {
			_msg = "Ext.MessageBox.show({msg:'"+msg+"',progressText:'"+protext
			+"',width:300,wait:true,waitConfig: {interval:"+waittime+"},icon:Ext.MessageBox.INFO});";
		}
		return _msg;
	}
	public static String getOpenWinScript(String pageName,String width,String height) {
		StringBuffer sb = new StringBuffer();
		sb.append("var openUrl = 'http://'+document.location.host+document.location.pathname; \n")
		.append("var end = openUrl.indexOf(';');\n")
		.append("openUrl = openUrl.substring(0,end);\n")
		.append("openUrl = openUrl + '?service=page/' + '")
		.append(pageName).append("';").append("window.open(openUrl,'newWin','width="+width+",height="+height+"');");
		return sb.toString();
	}
	public static String getOpenWinScript(String pageName) {
		return getOpenWinScript(pageName,"800","600");
	}
	
	public static double Mjkg_to_kcalkg(double value,int xiaosw){
//		兆焦/千克转化为千卡/千克
		double Dblvalue=0; 
		if(Math.abs(value)<100){
			
			Dblvalue=CustomMaths.Round_new(value*1000/4.1816, xiaosw);
		}else{
			
			Dblvalue=value;
		}
		return Dblvalue;
	}
	
	public static double Mjkg_to_kcalkg(double value,int xiaosw,String xiaosclfs){
//		兆焦/千克转化为千卡/千克,带小数处理参数
		double Dblvalue=0; 
		if(Math.abs(value)<100){
			
			if(xiaosclfs.equals(Locale.siswr_ht_xscz)){
//				四舍五入
				Dblvalue=CustomMaths.Round_new(value*1000/4.1816, xiaosw);
			}else if(xiaosclfs.equals(Locale.sheq_ht_xscz)){
//				舍去
				Dblvalue=Math.floor(Math.abs(value)*1000/4.1816);
			}else if(xiaosclfs.equals(Locale.jinw_ht_xscz)){
//				进位
				Dblvalue=Math.floor(Math.abs(value)*1000/4.1816)+1;
			}
			
		}else{
			
			Dblvalue=value;
		}
		return Dblvalue;
	}
	
	public static String Mjkg_to_kcalkg(String value,String lianjf,int xiaosw){
//		兆焦/千克转化为千卡/千克，可以处理带一个链接付的合同数据
//		参数：value表达式		lianjf连接符		xiaosw小数位数
		String Dblvalue="0";
		if(value!=null&&!value.equals("")){
			
			if(value.indexOf(lianjf)>-1){
//				针对处理合同价格为区间的情况，比如5000-6000
				double xiax=Double.parseDouble(value.substring(0,value.indexOf(lianjf)));
				double shangx=Double.parseDouble(value.substring(value.indexOf(lianjf)+1));
				
				if((Math.abs(xiax)<100)&&(Math.abs(shangx)<100)){
					
					xiax=CustomMaths.Round_new(xiax*1000/4.1816, xiaosw);
					shangx=CustomMaths.Round_new(shangx*1000/4.1816, xiaosw);
					Dblvalue=String.valueOf(xiax)+"-"+String.valueOf(shangx);
				}else{
					
					Dblvalue=value;
				}
			}else{
//				针对没有链接付的情况
				if(Math.abs(Double.parseDouble(value))<100){
					
					Dblvalue=String.valueOf(CustomMaths.Round_new(Double.parseDouble(value)*1000/4.1816, xiaosw));
				}else{
					
					Dblvalue=value;
				}
			}
		}
		
		return Dblvalue;
	}
	
	public static double kcalkg_to_Mjkg(double value,int xiaosw){
//		兆焦/千克转化为千卡/千克
		double Dblvalue=0; 
		if(Math.abs(value)>100){
			
			Dblvalue=CustomMaths.Round_new(value/1000*4.1816, xiaosw);
		}else{
			
			Dblvalue=value;
		}
		return Dblvalue;
	}
	
	public static int CreateXgsq(JdbcTemplate con,Renyxx visit,String biaos,int biaoslx,
			String leix, String shuom, String beiz) {
		int flag = 0 ;
		StringBuffer sb = new StringBuffer();
		sb.append("insert into xiugsqb(id,diancxxb_id,biaos,biaoslx,leix,shuom,zhuangt,shenqry,beiz,shenqsj) values(\n")
		.append("getnewid(").append(visit.getDiancxxb_id()).append("),").append(visit.getDiancxxb_id())
		.append(",'").append(biaos.replaceAll("'", "''")).append("',").append(biaoslx).append(",'").append(leix)
		.append("','").append(shuom).append("',0,'").append(visit.getMingc()).append("','").append(beiz)
		.append("',sysdate)");
		flag = con.update(sb.toString());
		return flag ;
	}
	
	public static String getDiancDefaultDaoz(long Diancxxb_id){
		
		String strDaoz="";
		StringBuffer sb=new StringBuffer();
		
		sb.append("	select dz.mingc as daoz				\n"); 
		sb.append("		from diancxxb dc,diancdzb gl,chezxxb dz	\n");
        sb.append("			where dc.id=gl.diancxxb_id and gl.chezxxb_id=dz.id	\n");
        sb.append("          	and dc.id="+Diancxxb_id+" order by dz.mingc ");
		
        ResultSetList rsl=jdbcTemplate.queryForObject(sb.toString(), ResultSetList.class);
        if(rsl.next()){
        	
        	strDaoz=rsl.getString("daoz");
        }
	
		return strDaoz;
	}
	
	public static String[] getSplitStringArray(String str,int index){
		int size = (int)Math.ceil(CustomMaths.div(str.length(), index)) ;
		String[] strarray = new String[size];
		int i = 0;
		while(i < size){
			if(str.length() >= index){
				strarray[i] = str.substring(0, index);
				str = str.substring(index);
			}else{
				strarray[i] = str.substring(0, str.length());
				break;
			}
			i++;
		}
		return strarray;
	}
	
	public static boolean getSystemHashCode(String valicode) {
		String osName = System.getProperty("os.name");
        String physicalAddress = "error";
        String hashCode = "";
        boolean success = false;
//        System.out.println(osName);
        try{
	        if(osName.indexOf("unknow") > -1 
	        		|| osName.equalsIgnoreCase("AIX")
	        		||osName.equalsIgnoreCase("windows vista")
	        		||osName.equalsIgnoreCase("Windows 7")
	        		|| osName.equalsIgnoreCase("Windows 8")
					|| osName.equalsIgnoreCase("Windows Server 2008 R2")
					|| osName.equalsIgnoreCase("Windows Server 2008")){
	        	hashCode = InetAddress.getLocalHost().getHostName();
	        	if(hashCode.equalsIgnoreCase(valicode)){
	        		success = true;
	        	}
	        }else{
	        	String line;
				Process process = Runtime.getRuntime().exec("ipconfig /all");
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(process.getInputStream()));
				while ((line = bufferedReader.readLine()) != null) {
					if (line.indexOf("Physical Address") != -1) {
						if (line.indexOf(":") != -1) {
							physicalAddress = line.substring(line.indexOf(":") + 2);
							String pas[] = physicalAddress.split("-");
							physicalAddress = "";
							for(int i=0;i<pas.length;i++){
								physicalAddress += pas[i];
							}
							hashCode = String.valueOf(Long.parseLong(physicalAddress,16));
							if(hashCode.equalsIgnoreCase(valicode)){
				        		success = true;
				        		break; // 找到MAC,推出循环
				        	}
						}
					}
				}
//				process.waitFor();
//				System.out.println(physicalAddress);
	        }
        }catch(UnknownHostException uhe){
        	uhe.printStackTrace();
        	return false;
        }
        catch(IOException ioe){
        	ioe.printStackTrace();
        	return false;
        }
//        catch(InterruptedException ie){
//        	ie.printStackTrace();
//        }
        catch(Exception e){
        	e.printStackTrace();
        	return false;
        }
//       System.out.println(hashCode);
        return success;

	}
	
	public static String getProjectName(){
		String ProjectName = "";
		FileNameFilter fnf = new FileNameFilter("application");
		File fs[] = getWebAbsolutePath().listFiles(fnf);
		for(int i = 0; i< fs.length; i++){
			ProjectName = fs[i].getName().replaceAll(".application", "");
		}
		/*File webxml = new File(getWebAbsolutePath(),"web.xml");
		if(webxml.exists()) {
			try {
				SAXBuilder builder = new SAXBuilder();
				FileInputStream fiss = new FileInputStream(webxml);
				Document docw = builder.build(fiss);
				Element root = docw.getRootElement();
				if(root.getName().equalsIgnoreCase("web-app")){
					ProjectName = root.getChildText("display-name");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}*/
//		System.out.println("projectName : "+ProjectName);
		return ProjectName;
	}
	public static File getProjectAbsolutePath(){
		if(getAbsolutePath().getName().indexOf(".war")>-1){
			return getAbsolutePath();
		}else{
			return getWebAbsolutePath().getParentFile();
		}
		
	}
	public static File getWebAbsolutePath(){
		if(getAbsolutePath().getName().indexOf(".war")>-1){
			return new File(getAbsolutePath().getAbsolutePath() + "/WEB-INF");
		}else{
			return getAbsolutePath().getParentFile();
		}
	}
	public static File getAbsolutePath(){
		String filepathURI = MainGlobal.class.getClassLoader().getResource("").getPath();
		File MGfile = new File(filepathURI.replaceAll("%20", " "));
//		System.out.println(MGfile.getPath());
		return MGfile;
	}
	
	public static long getSequenceNextVal(JdbcTemplate con,String sequenceName){
		String sql = "select " + sequenceName + ".nextval seqval from dual";
		ResultSetList rsl = con.queryForObject(sql, ResultSetList.class);
		long sequence =  new Date().getTime();
		if(rsl.next()){
			sequence = rsl.getLong("seqval");
		}
		rsl.close();
		return sequence;
	}
	
	public static boolean isFencb(String diancxxb_id){
		boolean isfc = false;
		String sql = "select * from diancxxb where fuid = " 
			+ diancxxb_id;
		if(jdbcTemplate.queryForList(sql).size()>0){
			isfc=true;
		}
		return isfc;
	}
	
	public static void Shujshcz(JdbcTemplate con, String diancxxb_id, 
			String riq, String shujid, String mokm, String caozy, String miaos){
		String sql = "select * from shujshb where diancxxb_id = " + diancxxb_id +
		" and riq = " + riq + " and mokmc = '" + mokm + "' and shujid=" + shujid; 
		ResultSetList rsl = con.queryForObject(sql, ResultSetList.class);
		if(rsl.next()){
			String shid = rsl.getString("id");
			if(rsl.getInt("zhuangt") == 1){
				updateShujsh(con, shid, 2);
				insertShujshzb(con, diancxxb_id, shid, caozy, miaos, 2);
			}else if(rsl.getInt("zhuangt") == 2){
				updateShujsh(con, shid, 0);
			}else if(rsl.getInt("zhuangt") == 0){
				updateShujsh(con, shid, 1);
				insertShujshzb(con, diancxxb_id, shid, caozy, miaos, 1);
			}
		}else{
			String shid = insertShujsh(con, diancxxb_id, riq, shujid, mokm, 1);
			insertShujshzb(con, diancxxb_id, shid, caozy, miaos, 1);
		}
		
	}
	public static String insertShujsh(JdbcTemplate con, String diancxxb_id, 
			String riq, String shujid, String mokm, int zhuangt){
		String id = getNewID(Long.parseLong(diancxxb_id));
		String sql = "insert into shujshb(id,diancxxb_id,riq,shujid,mokmc,zhuangt)" +
		" values(" + id + "," + diancxxb_id + "," + riq + "," + shujid + ",'" +
		mokm + "',"+zhuangt+")";
		int flag = con.update(sql);
		if(flag == -1){
			return null;
		}
		return id;
	}
	
	public static String updateShujsh(JdbcTemplate con, String id, int zhuangt){
		String sql = "update shujshb set zhuangt=" + zhuangt + " where id=" + id;
		int flag = con.update(sql);
		if(flag == -1){
			return null;
		}
		return id;
	}
	
	public static String insertShujshzb(JdbcTemplate con,String diancxxb_id, 
			String shujsh_id, String caozy, String miaos, int zhuangt){
		String sql = "";
		int flag ;
		if(zhuangt == 1){
			sql = "update shujshzb set zhuangt = 0 where shujshb_id =" + shujsh_id;
			flag = con.update(sql);
			if(flag == -1){
				return null;
			}
			sql = "insert into shujshzb(id,shujshb_id,caozy,miaos,zhuangt) " +
			"values(getnewid(" + diancxxb_id + ")," + shujsh_id + ",'" +
			caozy + "','',1)" ;
			flag = con.update(sql);
			if(flag == -1){
				return null;
			}
		}else{
			sql = "update shujshzb set shenqsj = sysdate, beiz = beiz||','||caozy, caozy ='" +
			caozy + "', miaos = '" + miaos + "' where shujshb_id = " + shujsh_id +
			" and zhuangt = 1";
			flag = con.update(sql);
			if(flag == -1){
				return null;
			}
		}
		return "";
	}
	
	public static void LogOperation(JdbcTemplate con, long diancxxb_id, String caozy, 
			String caoz, String mokm, String biaom, String id){
		String logid = getNewID(diancxxb_id);
		InsLogContent(con,diancxxb_id,logid,biaom,id);
		con.update(getInsRizbSql(logid, diancxxb_id, 
						caozy, caoz, mokm, biaom, id));
	}
	
	
	public static void InsLogContent(JdbcTemplate con, long diancxxb_id, String logid, 
			String biaom, String id){
		ResultSetList rs = con.queryForObject("select * from " + biaom +
				" where id = "+id,ResultSetList.class);
		String strResult = "";
		if(rs.next()){
			int colsize = rs.getColumnCount();
			for(int i = 0; i < colsize; i++){
				strResult += "@!" + rs.getColumnNames()[i] + "!@" + rs.getString(i);
			}
		}
		
		if(strResult.length() > 2)
			strResult = strResult.substring(2);
		
		while(strResult.length()>4000){
			String strTmp = strResult.substring(0, 3999);
			strResult = strResult.substring(3999);
			con.update(getInsRiznrbSql(diancxxb_id,logid,strTmp));
		}
		con.update(getInsRiznrbSql(diancxxb_id,logid,strResult));
	}
	public static String getInsRizbSql(String id, long diancxxb_id, String caozy, 
			String caoz, String mokm, String biaom, String biaoid){
		String sql = "insert into rizb(id,diancxxb_id,caozy,caoz,mokmc,biaom,biaoid,leix)" +
		" values("+id+"," + diancxxb_id + ",'" + caozy + "','" + caoz + "','" +
		mokm +"','" + biaom + "'," + biaoid + ",'0')";
		return sql;
	}
	
	public static String getInsRiznrbSql(long diancxxb_id, String logid, String strData){
		String sql = "insert into riznrb(id,rizb_id,neir) values(getnewid("
			+ diancxxb_id + ")," + logid + ",'" + strData.replaceAll("'", "''")
			+ "')";
		return sql;
	}
	
	public static void addStr2ListNorepeat(List<Object> list, String str){
		if(list == null) {
			list = new ArrayList<Object>();
		}
		int i=0;
		for( ;i<list.size() ;i++) {
			if(((String)list.get(i)).equals(str)) {
				break;
			}
		}
		if(i == list.size()) {
			list.add(str);
		}
	}
	
	public static ResultSetList getTableRsl(JdbcTemplate con,String Sql){
//		返回sql 对应的记录集行数
		ResultSetList rsl=con.queryForObject(Sql,ResultSetList.class);
		return rsl;
	}
	
	public static boolean isDigit(String s) {
//		判断是否是数字
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {

			try {
				Float.parseFloat(s);
				return true;
			} catch (NumberFormatException e1) {
				try {
					Double.parseDouble(s);
					return true;
				} catch (NumberFormatException e2) {
					return false;
				}
			}
		}

	}
}