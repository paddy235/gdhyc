package com.zhiren.fuelmis.dc.service.common;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.web.multipart.MultipartFile;

import com.zhiren.fuelmis.dc.entity.common.Fujxx;

/** 
 * @author 陈宝露
 */
public interface ICommonService {
	List<Map<String,Object>> getAllShengf();
	
	List<Map<String,Object>> getAllChez();
	
	List<Map<String,Object>> getAllGangk();
	
	List<Map<String,Object>> getAllDianclb();
	
	List<Map<String,Object>> getAllLiclx();
	
	List<Map<String,Object>> getAllMeikxx();

	List<Map<String, Object>> getAllPinz();

	List<Map<String, Object>> getAllJihkj();

	List<Map<String, Object>> getGongysLikeJianc(String code);
	
	List<Map<String,Object>> getAllLuj();
	
	List<Map<String,Object>> getAllMeikdq();
	
	String uploadFile(MultipartFile file);
	
	String getWenjmc(Map<String,Object> map);
	
	List<Map<String,Object>> getAllGongys();
	
	JSONArray checkNameExist(Map<String, Object> map);
	
	JSONArray getNextNum(Map<String, Object> map);
	
	List<Map<String, Object>> getAllYunsfs();

	List<Map<String, Object>> getAllRenyxx();

	List<Map<String, Object>> getAllPingffa();

	List<Map<String, Object>> getAllCaigddb();

	void saveFujxx(Fujxx f);

	List<Map<String, Object>> getComboHetmb(Map<String, Object> map);
	
	List<Map<String, Object>> getAllKuczz(Map<String, Object> map);
	
	List<Map<String, Object>> getAllKucwz();
	
	List<Map<String, Object>> getAllKucwl();
	
	List<Map<String, Object>> getAllChurkywlx();

	List<Map<String, Object>> getAllChurkywlx_fdck();

	List<Map<String, Object>> getAllJijfs(Map<String, Object> map);

	List<Map<String, Object>> getAllKaohzb();

	List<Map<String, Object>> getAllHetbh();


	List<Map<String, Object>> getAllPriceComponet();

	List<Map<String, Object>> getAllPriceItem();

	List<Map<String, Object>> getAllRuljz();
	
	List<Map<String, Object>> getAllRulbz();

	List<Map<String,Object>> getAllFeiyxm();

	List<Map<String,Object>> getAllChezXxb();
}
