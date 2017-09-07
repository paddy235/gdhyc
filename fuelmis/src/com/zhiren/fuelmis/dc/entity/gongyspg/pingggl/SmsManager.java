package com.zhiren.fuelmis.dc.entity.gongyspg.pingggl;
/*时间：2011-07-10
* 作者： 	陈环红
* 适用范围：张家口热电供应商评价系统
* 修改：    去掉.的replaceAll的处理，否则全部被替换成了;
 

时间：2011-07-08
* 作者： 	邱竹伟
* 适用范围：张家口热电供应商评价系统
* 新开发：分配多手机号任务时，对于，。、；,.统一替换为英文半角符号;
 

时间：2011-06-07
* 作者： 	陈环红
* 适用范围：张家口热电供应商评价系统
* 新开发：1.发布日评分短信明细 
 

时间：2011-06-06
* 作者： 	陈环红
* 适用范围：张家口热电供应商评价系统
*  新开发：1.读取短信任务中要发送的信息发送，要发送的 sendTime<sysdate，未发送的 STATUS=0 
*  ``      2发送完成更新STATUS 为1 ，FinishTime 时间更新为sysdate
 
时间：2011-12-30
* 作者：张雪
* 修改内容：如果在电厂的xitxxb中没有mingc='串口号'这个参数，程序默认串口号是COM1口；
*		   假如服务器的串口号是COM5，需要在表中增加一条记录，语句如下：
* 		   insert into xitxxb values(2017739744, 6, 312, '串口号', 'COM5', '', '硬件设备串口号', 1, '使用');

时间：2012-02-08
* 作者：张雪
* 修改内容：系统默认发送人是张家口热电供应商系统，如果xitxxb中存在mingc='短信发送人'这个参数时，获得的zhi是什么发送人就是什么
* insert into xitxxb values(2017739745, 7, 312, '短信发送人', '大唐云冈热电供应商系统', '', '发送评分短信的人', 1, '使用');

时间：2013-06-08
* 作者：张雪
* 适用范围：大唐临汾的日评分短信内容
* 修改内容：因考核指标不同于以往的几项，所以临时增加getPingMessage_new()方法
* 		  if(dianQc(cn).equals("大唐临汾热电供应商评价系统")){
				sbMessage=getPingMessage_new(sDate,sGongysID,cn);

时间：2013-09-15
* 作者：张雪
* 适用范围：国电电力供应商无短息功能，在xitxxb中增加“日推送短信”参数，zhi='国电'，在日评分时，点击“发布”按钮，屏蔽正常没有联系人手机号码的提示，并且能够显示发布时间。
* 修改内容：FabRigmpfMessage方法中增加duanxgn的变量，判断是否等于“国电”


package com.zhiren.fuelmis.dc.entity.gongyspg.pingggl;

import java.sql.ResultSet;

import com.zhiren.common.JDBCcon;
import com.zhiren.common.ResultSetList;

public class SmsManager    {
	public static void SendSms(){
		JDBCcon cn = new JDBCcon();
		ResultSet rs_c = cn.getResultSet("select zhi from xitxxb where mingc = '串口号' and zhuangt=1");
		String com = "COM1";
		try{
		if(rs_c.next()){
			com = rs_c.getString("zhi");
		}
		rs_c.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//查询所有需要处理的短信内容
		ResultSetList rs=cn.getResultSetList("select * from duanxfsrwb where STATUS=0 and sendTime<=sysdate");
		if (rs.getRows()>0){
			JinDiSms  sms=new JinDiSms();
			System.out.print("串口号是：" + com);
			if (sms.Start(com)){
				while (rs.next()){
					if (sms.SendMessage(rs.getString("PhoneNumber"), rs.getString("MessageText"))){
						cn.getUpdate(" update duanxfsrwb set STATUS=1,FinishTime=sysdate where id="+rs.getString("id"));
					}else{
						cn.getUpdate(" update duanxfsrwb set FinishTime=sysdate,ErrorInfo='发送时发生错误' where id="+rs.getString("id"));
					}
				}
				sms.Stop();
			}else{
				//设备没有打开
			};
		}
		rs.close();
		cn.Close();
	}
	
	//保存一条评分短信
	public  boolean FabRigmpfMessage(String renyMingc, String sDate,String sGongysID,JDBCcon cn){
		int flag=-1;
		StringBuffer sbPhoneNumber = getPhoneNumber(sGongysID,cn);
		//获取供应商评价系统是否有短信功能，国电下边电厂都没有该功能，特设此参数进行控制
		ResultSet rs_gysdx = cn.getResultSet("select zhi from xitxxb where mingc = '日推送短信' and zhuangt=1");
		String duanxgn = "";//短信功能
		try{
		if(rs_gysdx.next()){
			duanxgn = rs_gysdx.getString("zhi");
		}
		rs_gysdx.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		if (sbPhoneNumber.length()>0){
			StringBuffer sbMessage = new StringBuffer();
			if(dianQc(cn).equals("大唐临汾热电供应商评价系统")){
				sbMessage=getPingMessage_new(sDate,sGongysID,cn);
			}else{
				sbMessage=getPingMessage(sDate,sGongysID,cn);
			}
			if  (sbMessage.length()>0){
				//将符号，。、；,.统一替换为英文半角符号;
				String[] arrPhoneNumber=sbPhoneNumber.toString().replaceAll(",", ";").replaceAll("，", ";").replaceAll("、", ";").replaceAll("。", ";").replaceAll("；", ";").split(";");
				for(int i=0;i<arrPhoneNumber.length;i++){
					if(arrPhoneNumber[i].length()==11){
						flag=cn.getInsert("insert into duanxfsrwb(id,sender,MESSAGETEXT,SENDTIME,PHONENUMBER)" +
							"values (getnewid(1),'"+renyMingc+"','"+sbMessage.toString()+"',sysdate,'"+arrPhoneNumber[i]+"')");
					}else{
						System.out.println("输入的手机号码【"+arrPhoneNumber[i]+"】不合格，请检查");
					}
				}
			}
		}
		if(duanxgn.equals("国电")){
			flag = 1;
		}

		if (flag!=-1){
			return true;
		}else{
			return false;
		}
	}
	
	private StringBuffer getPhoneNumber(String sGongysID,JDBCcon cn){
		StringBuffer sbPhoneNumber = new StringBuffer();
		ResultSetList rs=cn.getResultSetList("select yiddh from renyxxb where danw_id="+sGongysID);
		while (rs.next()){
			if (rs.getString("yiddh").length()>=11){
				if (sbPhoneNumber.length()>0){
					sbPhoneNumber.append(";");
				}
				sbPhoneNumber.append(rs.getString("yiddh"));
			}
		}
		rs.close();
		return sbPhoneNumber;
	}
	
	public String dianQc(JDBCcon cn){
		ResultSet rs = cn.getResultSet("select zhi from xitxxb where mingc='短信发送人'");
		String diancqc="张家口热电供应商系统";
		try{
		if(rs.next()){
			diancqc = rs.getString("zhi");
		}
		rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}	
		return diancqc;
	}
	//增加大唐临汾的日评分短信内容
	private StringBuffer getPingMessage_new(String sDate,String sGongysID,JDBCcon cn){
		StringBuffer sbsql = new StringBuffer();
		StringBuffer sbMsg=new StringBuffer();

		sbsql.append("select to_char(jh.riq,'yyyy-mm-dd') as riq, gy.jianc as mingc,round_new(avg(jf.z),2) z,  \n");
	    sbsql.append("  sum(jh.jihml) as jh,sum(sm.laiml) as lm,sum(jh.jihml-sm.laiml) as mlc,round_new(avg(jf.l),2) as jfl,  \n");
	    sbsql.append("  decode(nvl(sum(jh.jihml),0),0,0, round_new(sum(jh.q*jh.jihml)/sum(jh.jihml),0)) as kfq,  \n");
	    sbsql.append("  decode(nvl(sum(sm.laiml),0),0,0, round_new(sum(sm.q*sm.laiml)/sum(sm.laiml),0)) as cfq,round_new(avg(jf.q),2) as jfq,  \n");
	    sbsql.append("  --decode(nvl(sum(jh.jihml),0),0,0, round_new(sum(jh.a*jh.jihml)/sum(jh.jihml),2)) as kfa,  \n");
	    sbsql.append("  --decode(nvl(sum(sm.laiml),0),0,0, round_new(sum(sm.a*sm.laiml)/sum(sm.laiml),2)) as cfa,round_new(avg(jf.a),2) as jfa,  \n");
	    sbsql.append("  decode(nvl(sum(jh.jihml),0),0,0, round_new(sum(jh.s*jh.jihml)/sum(jh.jihml),2)) as kfs,  \n");
	    sbsql.append("  decode(nvl(sum(sm.laiml),0),0,0, round_new(sum(sm.s*sm.laiml)/sum(sm.laiml),2)) as cfs,round_new(avg(jf.s),2) as jfs,  \n");
	    sbsql.append("  decode(nvl(sum(jh.jihml),0),0,0, round_new(sum(jh.m*jh.jihml)/sum(jh.jihml),1)) as kfm,  \n");
	    sbsql.append("  decode(nvl(sum(sm.laiml),0),0,0, round_new(sum(sm.m*sm.laiml)/sum(sm.laiml),2)) as cfm,round_new(avg(jf.m),2) as jfm,  \n");
	    sbsql.append("  round_new(avg(nvl(jf.t,0)),2) as jft  \n");
	    sbsql.append("from   \n");
	    sbsql.append("(select jh.id,jh.gongysb_id,jh.jihml,jh.riq,  \n");
	    sbsql.append("    max(decode(mx.ZHIBDM,'QNET_AR',mx.zhibz)) as Q,  \n");
	    sbsql.append("    --max(decode(mx.ZHIBDM,'Aad',mx.zhibz)) as a,  \n");
	    sbsql.append("    max(decode(mx.ZHIBDM,'STD',mx.zhibz)) as s,  \n");
	    sbsql.append("    max(decode(mx.ZHIBDM,'MT',mx.zhibz)) as m  \n");
	    sbsql.append("from rigmjhb jh, rigmzbb zb,rigmzbmxb mx  \n");
	    sbsql.append("    where jh.id=zb.rigmjhb_id(+)  \n");
	    sbsql.append("        and zb.id= mx.rigmzbb_id(+)  \n");
	    sbsql.append("        and jh.riq=date'").append(sDate).append("'  \n");
	    sbsql.append("        and gongysb_id=").append(sGongysID).append(" \n");
	    sbsql.append("    group by jh.id,jh.gongysb_id,jh.jihml,jh.riq) jh, --计划指标  \n");
	    sbsql.append("(select fh.gongysb_id,fh.daohrq,fh.laiml,  \n");
	    sbsql.append("       max(decode(ZL.ZHIBDM,'QNET_AR',zl.zhibz)) as Q,  \n");
	    sbsql.append("       --max(decode(ZL.ZHIBDM,'Aad',zl.zhibz)) as a,  \n");
	    sbsql.append("       max(decode(ZL.ZHIBDM,'STD',zl.zhibz)) as s,  \n");
	    sbsql.append("       max(decode(ZL.ZHIBDM,'MT',zl.zhibz)) as m  \n");
	    sbsql.append(" from fahb fh,zhilb zl  \n");
	    sbsql.append("        where fh.id=zl.fahb_id(+)  \n");
	    sbsql.append("            and fh.daohrq=date'").append(sDate).append("'  \n");
	    sbsql.append("            and gongysb_id=").append(sGongysID).append(" \n");
	    sbsql.append("        group by fh.gongysb_id,fh.daohrq,fh.laiml) sm,--收煤指标  \n");
	    sbsql.append("(select riq,gongysb_id,   \n");
	    sbsql.append("       sum(decode(mx.ZHIBDM,'SL',mx.jif)) as L,  \n");
	    sbsql.append("       sum(decode(mx.ZHIBDM,'QNET_AR',mx.jif)) as Q,  \n");
	    sbsql.append("       --sum(decode(mx.ZHIBDM,'Aad',mx.jif)) as a,  \n");
	    sbsql.append("       sum(decode(mx.ZHIBDM,'STD',mx.jif)) as s,  \n");
	    sbsql.append("       sum(decode(mx.ZHIBDM,'MT',mx.jif)) as m,  \n");
	    sbsql.append("       nvl(sum(decode(mx.ZHIBDM,'TSJF',mx.jif)),0) as t,  \n");
	    sbsql.append("       sum(mx.jif) as z  \n");
	    sbsql.append("from rigmjfb jf,Rigmjfmxb mx  \n");
	    sbsql.append("    where jf.id=mx.rigmjfb_id  \n");
	    sbsql.append("        and jf.riq=date'").append(sDate).append("'  \n");
	    sbsql.append("        and gongysb_id=").append(sGongysID).append(" \n");
	    sbsql.append("    group by riq,gongysb_id) jf,gongysb gy  \n");
	    sbsql.append("where jh.gongysb_id=sm.gongysb_id(+)  \n");
	    sbsql.append("    and jh.riq=sm.daohrq(+)  \n");
	    sbsql.append("    and jh.gongysb_id=jf.gongysb_id(+)  \n");
	    sbsql.append("    and jh.riq=jf.riq(+)  \n");
	    sbsql.append("    and jh.gongysb_id=gy.id  \n");
	    sbsql.append("group by jh.riq,gy.jianc \n");
	    
	    ResultSetList rs=cn.getResultSetList(sbsql.toString());
	    while (rs.next()){
	      sbMsg.append(rs.getString("mingc")).append(" ").append(rs.getString("riq")).append("\n");
	      sbMsg.append("煤量:").append(rs.getString("jh")).append("验收").append(rs.getString("lm")).append(" 得分").append(rs.getString("JFL")).append("\n");
	      sbMsg.append("热量:").append(rs.getString("KFQ")).append("验收").append(rs.getString("CFQ")).append("得分").append(rs.getString("JFQ")).append("\n");
	      //sbMsg.append("灰分:").append(rs.getString("KFA")).append("验收").append(rs.getString("CFA")).append("得分").append(rs.getString("JFA")).append("\n");
	      sbMsg.append("硫分:").append(rs.getString("KFS")).append("验收").append(rs.getString("CFS")).append("得分").append(rs.getString("JFS")).append("\n");
	      sbMsg.append("水分:").append(rs.getString("KFM")).append("验收").append(rs.getString("CFM")).append("得分").append(rs.getString("JFM")).append("\n");
	      if (rs.getDouble("jft")>0){
	        sbMsg.append("特殊加分:").append(rs.getString("jft")).append("\n");
	      }
	      sbMsg.append("总分:").append(rs.getString("z")).append(getPingy(rs.getDouble("z"))).append("\n");
	      sbMsg.append("如对验收结果有异议请2日内反馈\n");
	      sbMsg.append("发送人：" + dianQc(cn));
	    }
		rs.close();
		return sbMsg;
	}
	
	private StringBuffer getPingMessage(String sDate,String sGongysID,JDBCcon cn) {
		StringBuffer sbsql = new StringBuffer();
		StringBuffer sbMsg=new StringBuffer();

		sbsql.append("select to_char(jh.riq,'yyyy-mm-dd') as riq, gy.jianc as mingc,round_new(avg(jf.z),2) z,  \n");
		sbsql.append("  sum(jh.jihml) as jh,sum(sm.laiml) as lm,sum(jh.jihml-sm.laiml) as mlc,round_new(avg(jf.l),2) as jfl,  \n");
		sbsql.append("  decode(nvl(sum(jh.jihml),0),0,0, round_new(sum(jh.q*jh.jihml)/sum(jh.jihml),0)) as kfq,  \n");
		sbsql.append("  decode(nvl(sum(sm.laiml),0),0,0, round_new(sum(sm.q*sm.laiml)/sum(sm.laiml),0)) as cfq,round_new(avg(jf.q),2) as jfq,  \n");
		sbsql.append("  decode(nvl(sum(jh.jihml),0),0,0, round_new(sum(jh.a*jh.jihml)/sum(jh.jihml),2)) as kfa,  \n");
		sbsql.append("  decode(nvl(sum(sm.laiml),0),0,0, round_new(sum(sm.a*sm.laiml)/sum(sm.laiml),2)) as cfa,round_new(avg(jf.a),2) as jfa,  \n");
		sbsql.append("  decode(nvl(sum(jh.jihml),0),0,0, round_new(sum(jh.s*jh.jihml)/sum(jh.jihml),2)) as kfs,  \n");
		sbsql.append("  decode(nvl(sum(sm.laiml),0),0,0, round_new(sum(sm.s*sm.laiml)/sum(sm.laiml),2)) as cfs,round_new(avg(jf.s),2) as jfs,  \n");
		sbsql.append("  decode(nvl(sum(jh.jihml),0),0,0, round_new(sum(jh.m*jh.jihml)/sum(jh.jihml),1)) as kfm,  \n");
		sbsql.append("  decode(nvl(sum(sm.laiml),0),0,0, round_new(sum(sm.m*sm.laiml)/sum(sm.laiml),2)) as cfm,round_new(avg(jf.m),2) as jfm,  \n");
		sbsql.append("  round_new(avg(nvl(jf.t,0)),2) as jft  \n");
		sbsql.append("from   \n");
		sbsql.append("(select jh.id,jh.gongysb_id,jh.jihml,jh.riq,  \n");
		sbsql.append("    max(decode(mx.ZHIBDM,'Qnet_ar',mx.zhibz)) as Q,  \n");
		sbsql.append("    max(decode(mx.ZHIBDM,'Aad',mx.zhibz)) as a,  \n");
		sbsql.append("    max(decode(mx.ZHIBDM,'St_d',mx.zhibz)) as s,  \n");
		sbsql.append("    max(decode(mx.ZHIBDM,'Mt',mx.zhibz)) as m  \n");
		sbsql.append("from rigmjhb jh, rigmzbb zb,rigmzbmxb mx  \n");
		sbsql.append("    where jh.id=zb.rigmjhb_id(+)  \n");
		sbsql.append("        and zb.id= mx.rigmzbb_id(+)  \n");
		sbsql.append("        and jh.riq=date'").append(sDate).append("'  \n");
		sbsql.append("        and gongysb_id=").append(sGongysID).append(" \n");
		sbsql.append("    group by jh.id,jh.gongysb_id,jh.jihml,jh.riq) jh, --计划指标  \n");
		sbsql.append("(select fh.gongysb_id,fh.daohrq,fh.laiml,  \n");
		sbsql.append("       max(decode(ZL.ZHIBDM,'Qnet_ar',zl.zhibz)) as Q,  \n");
		sbsql.append("       max(decode(ZL.ZHIBDM,'Aad',zl.zhibz)) as a,  \n");
		sbsql.append("       max(decode(ZL.ZHIBDM,'St_d',zl.zhibz)) as s,  \n");
		sbsql.append("       max(decode(ZL.ZHIBDM,'Mt',zl.zhibz)) as m  \n");
		sbsql.append(" from fahb fh,zhilb zl  \n");
		sbsql.append("        where fh.id=zl.fahb_id(+)  \n");
		sbsql.append("            and fh.daohrq=date'").append(sDate).append("'  \n");
		sbsql.append("            and gongysb_id=").append(sGongysID).append(" \n");
		sbsql.append("        group by fh.gongysb_id,fh.daohrq,fh.laiml) sm,--收煤指标  \n");
		sbsql.append("(select riq,gongysb_id,   \n");
		sbsql.append("       sum(decode(mx.ZHIBDM,'SL',mx.jif)) as L,  \n");
		sbsql.append("       sum(decode(mx.ZHIBDM,'Qnet_ar',mx.jif)) as Q,  \n");
		sbsql.append("       sum(decode(mx.ZHIBDM,'Aad',mx.jif)) as a,  \n");
		sbsql.append("       sum(decode(mx.ZHIBDM,'St_d',mx.jif)) as s,  \n");
		sbsql.append("       sum(decode(mx.ZHIBDM,'Mt',mx.jif)) as m,  \n");
		sbsql.append("       nvl(sum(decode(mx.ZHIBDM,'TSJF',mx.jif)),0) as t,  \n");
		sbsql.append("       sum(mx.jif) as z  \n");
		sbsql.append("from rigmjfb jf,Rigmjfmxb mx  \n");
		sbsql.append("    where jf.id=mx.rigmjfb_id  \n");
		sbsql.append("        and jf.riq=date'").append(sDate).append("'  \n");
		sbsql.append("        and gongysb_id=").append(sGongysID).append(" \n");
		sbsql.append("    group by riq,gongysb_id) jf,gongysb gy  \n");
		sbsql.append("where jh.gongysb_id=sm.gongysb_id(+)  \n");
		sbsql.append("    and jh.riq=sm.daohrq(+)  \n");
		sbsql.append("    and jh.gongysb_id=jf.gongysb_id(+)  \n");
		sbsql.append("    and jh.riq=jf.riq(+)  \n");
		sbsql.append("    and jh.gongysb_id=gy.id  \n");
		sbsql.append("group by jh.riq,gy.jianc \n");
		
		ResultSetList rs=cn.getResultSetList(sbsql.toString());
		while (rs.next()){
			sbMsg.append(rs.getString("mingc")).append(" ").append(rs.getString("riq")).append("\n");
			sbMsg.append("煤量:").append(rs.getString("jh")).append("验收").append(rs.getString("lm")).append(" 得分").append(rs.getString("JFL")).append("\n");
			sbMsg.append("热量:").append(rs.getString("KFQ")).append("验收").append(rs.getString("CFQ")).append("得分").append(rs.getString("JFQ")).append("\n");
			sbMsg.append("灰分:").append(rs.getString("KFA")).append("验收").append(rs.getString("CFA")).append("得分").append(rs.getString("JFA")).append("\n");
			sbMsg.append("硫分:").append(rs.getString("KFS")).append("验收").append(rs.getString("CFS")).append("得分").append(rs.getString("JFS")).append("\n");
			sbMsg.append("水分:").append(rs.getString("KFM")).append("验收").append(rs.getString("CFM")).append("得分").append(rs.getString("JFM")).append("\n");
			if (rs.getDouble("jft")>0){
				sbMsg.append("特殊加分:").append(rs.getString("jft")).append("\n");
			}
			sbMsg.append("总分:").append(rs.getString("z")).append(getPingy(rs.getDouble("z"))).append("\n");
			sbMsg.append("如对验收结果有异议请2日内反馈\n");
			sbMsg.append("发送人：" + dianQc(cn));
		}
		rs.close();
		return sbMsg;
	}

	//更据总分，返回评语
	private String getPingy(double dblZongf){
		String strPingy="";
		if (dblZongf>=85){
			strPingy="你很优秀啊！";
		}else if(dblZongf>=60){
			strPingy="还不错，要努力啊！";
		}else if(dblZongf>=40){
			strPingy="真差劲，这不行啊！";
		}else {
			strPingy="你算完了！";
		}
		return strPingy;
	}
}
*/