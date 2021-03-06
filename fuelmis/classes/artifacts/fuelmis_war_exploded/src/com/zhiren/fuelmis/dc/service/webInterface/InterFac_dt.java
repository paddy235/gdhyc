package com.zhiren.fuelmis.dc.service.webInterface;
/**
 * @author 厂级服务
 *         厂级：	InterFac		名称	类型
 *         方法名1：	响应请求	request
 *         参数名1：	用户名	usr	String
 *         参数名2：	密码	password	String
 *         参数名3：	任务名	task	String
 *         返回值：	成功/失败1,失败2		String
 *         <p>
 *         方法名2：	响应请求	requestall
 *         参数名1：	用户名	usr	String
 *         参数名2：	密码	password	String
 *         返回值：	成功/失败1,失败2		String
 */
//与中电投有两个区别：不使用电厂信息表，因为id是唯一的CreateSql、requestall、request的sql的diancxxb_id去掉
public interface InterFac_dt {
    String inceptWenj(String user, String password, byte[] XMLData);

    void request(String task);

    void requestall();

    void requestallTrans();

    String[] sqlExe(String[] sqls, boolean isTransaction);

    String[] getJiecxx_Sj(String Type);

    //	输入一个sql语句能够返回他的结果字符串，字符串格式 1123 ，20
    //按天验证数据
    String getSqlString(String sql);

    String[] sqlExe(String diancxxb_id, String[] sqls, boolean isTransaction);

}
