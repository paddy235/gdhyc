package com.zhiren.fuelmis.dc.dao.yueshchjb;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.yueshchjb.YueshchjbVo;

/** 
 * @author 孟建云
 */
@Repository
public interface YueshchjbDao {	
	//	取得当月收煤量  [0] 收煤量 [1] 损耗 [2] 调整
	List<Map<String, Object>> getShouml(Map<String, Object> map);
	
	//	取得当月耗煤量 [0] 发电用  [1] 供热用 [2] 其它用 = 其它用 + 非生产用
	List<Map<String, Object>> getMeihl(Map<String, Object> map);
	
	//取得当月入厂入炉水分 [0]入厂平均水分 [1]入炉平均水分
	List<Map<String, Object>> getShuifc(Map<String, Object> map);
	
	//	取得期初库存 [0] 当月期初库存 
	List<Map<String, Object>> getQickc(Map<String, Object> map);
	
	/*
	 * 运损不计入库存 如想计入运损则自定义来煤量
	 * 原句 kuc += (shouml[shouml_shouml] + shouml[shouml_sunh] - shouml[shouml_tiaozl]);
	 * 新增收煤量算法shouml(jingz) - yuns + yingd - kuid 
	 */
	List<Map<String, Object>> getShoumlv(Map<String, Object> map);
	
	//新增字段 计提储损、允许储损
	List<Map<String, Object>> getRunxcs(Map<String, Object> map);
	
	//	取得上月累计数
	List<Map<String, Object>> getLeij(Map<String, Object> map);
	
//	取得上年12月库存累计数
	List<Map<String, Object>> getQickcLeij(Map<String, Object> map);
	
	//查询数据用
	List<Map<String, Object>> getXitxx_item(Map<String, Object> map);
	
	//请在使用本模块之前，首先完成月数量数据的计算
	List<Map<String, Object>> getisNotReady(Map<String, Object> map);
	
	//查询数据
	List<YueshchjbVo> getData(Map<String, Object> map);
	
	//生成数据
	void createData(YueshchjbVo yueshchjb);
	
	//更新数据
	void updateData(YueshchjbVo yueshchjb);
	
	//删除数据
	void DelData(Map<String, Object> map);

	void updateDataL(YueshchjbVo hjvo);
	
}
