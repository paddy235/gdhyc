package com.zhiren.fuelmis.dc.service.impl.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zhiren.fuelmis.dc.dao.common.CommonDao;
import com.zhiren.fuelmis.dc.entity.common.Fujxx;
import com.zhiren.fuelmis.dc.service.common.ICommonService;
import com.zhiren.fuelmis.dc.utils.FileUtil;

/** 
 * @author 陈宝露
 */
@Service
public class CommonServiceImpl implements ICommonService {

	@Autowired
	private CommonDao commonDao;
	
	@Override
	public List<Map<String, Object>> getAllShengf() {
		// TODO Auto-generated method stub
		return commonDao.getAllShengf();
	}

	@Override
	public List<Map<String, Object>> getAllChez() {
		// TODO Auto-generated method stub
		return commonDao.getAllChez();
	}

	@Override
	public List<Map<String, Object>> getAllGangk() {
		// TODO Auto-generated method stub
		return commonDao.getAllGangk();
	}

	@Override
	public List<Map<String, Object>> getAllDianclb() {
		// TODO Auto-generated method stub
		return commonDao.getAllDianclb();
	}

	@Override
	public List<Map<String, Object>> getAllLiclx() {
		// TODO Auto-generated method stub
		return commonDao.getAllLiclx();
	}

	@Override
	public List<Map<String, Object>> getAllMeikxx() {
		// TODO Auto-generated method stub
		return commonDao.getAllMeikxx();
	}


	@Override
	public List<Map<String, Object>> getAllPinz() {
		// TODO Auto-generated method stub
		return commonDao.getAllPinz();
	}

	@Override
	public List<Map<String, Object>> getAllJihkj() {
		return commonDao.getAllJihkj();
	}

	@Override
	public List<Map<String, Object>> getGongysLikeJianc(String code) {
		Map<String,Object> shouhcmap = new HashMap<String,Object>();
		shouhcmap.put("mingc", code);
		return commonDao.getGongysLikeJianc(shouhcmap);
	}


	@Override
	public List<Map<String, Object>> getAllLuj() {
		// TODO Auto-generated method stub
		return commonDao.getAllLuj();
	}


	@Override
	public List<Map<String, Object>> getAllYunsfs() {
		return commonDao.getAllYunsfs();
	}

	@Override
	public List<Map<String, Object>> getAllMeikdq() {
		// TODO Auto-generated method stub
		return commonDao.getAllMeikdq();
	}

	@Override
	public String uploadFile(MultipartFile file) {
		// TODO Auto-generated method stub
		try{
			String[] result = FileUtil.UploadFile(file);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", result[1]);
			map.put("mingc", result[1]);
			map.put("wenjlj", "");
			//commonDao.insertWenjxx(map);
			return result[1];
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public String getWenjmc(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return commonDao.getWenjmc(map);
	}

	@Override
	public List<Map<String, Object>> getAllGongys() {
		// TODO Auto-generated method stub
		return commonDao.getAllGongys();
	}

	@Override
	public JSONArray checkNameExist(Map<String, Object> map) {
		// TODO Auto-generated method stub
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(commonDao.checkNameExist(map));
		return jsonArray;
	}

	@Override
	public JSONArray getNextNum(Map<String, Object> map) {
		// TODO Auto-generated method stub
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(commonDao.getNextNum(map));
		return jsonArray;
	}

	@Override
	public List<Map<String, Object>> getAllRulbz() {
		// TODO Auto-generated method stub
		return commonDao.getAllRulbz();
	}

	@Override
	public List<Map<String, Object>> getAllRenyxx() {
		// TODO Auto-generated method stub
		return commonDao.getAllRenyxx();
	}

	@Override
	public List<Map<String, Object>> getAllPingffa() {
		// TODO Auto-generated method stub
		return commonDao.getAllPingffa();
	}

	@Override
	public List<Map<String, Object>> getAllCaigddb() {
		// TODO Auto-generated method stub
		return commonDao.getAllCaigddb();
	}

	@Override
	public void saveFujxx(Fujxx f) {
		commonDao.saveFujxx(f);
	}

	@Override
	public List<Map<String, Object>> getComboHetmb(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return commonDao.getComboHetmb(map);
	}

	@Override
	public List<Map<String, Object>> getAllKuczz(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return commonDao.getAllKuczz(map);
	}

	@Override
	public List<Map<String, Object>> getAllKucwz() {
		// TODO Auto-generated method stub
		return commonDao.getAllKucwz();
	}

	@Override
	public List<Map<String, Object>> getAllKucwl() {
		// TODO Auto-generated method stub
		return commonDao.getAllKucwl();
	}

	@Override
	public List<Map<String, Object>> getAllChurkywlx() {
		// TODO Auto-generated method stub
		return commonDao.getAllChurkywlx();
	}

	@Override
	public List<Map<String, Object>> getAllChurkywlx_fdck() {
		// TODO Auto-generated method stub
		return commonDao.getAllChurkywlx_fdck();
	}

	@Override
	public List<Map<String, Object>> getAllJijfs(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return commonDao.getAllJijfs(map);
	}

	@Override
	public List<Map<String, Object>> getAllKaohzb() {
		// TODO Auto-generated method stub
		return commonDao.getAllKaohzb();
	}

	@Override
	public List<Map<String, Object>> getAllHetbh() {
		// TODO Auto-generated method stub
		return commonDao.getAllHetbh();
	}


	@Override
	public List<Map<String, Object>> getAllPriceComponet() {
		// TODO Auto-generated method stub
		return commonDao.getAllPriceComponet();
	}

	@Override
	public List<Map<String, Object>> getAllPriceItem() {
		// TODO Auto-generated method stub
		return commonDao.getAllPriceItem();
	}

	@Override
	public List<Map<String, Object>> getAllRuljz() {
		// TODO Auto-generated method stub
		return commonDao.getAllRuljz();
	}
	@Override
	public List<Map<String, Object>> getAllFeiyxm() {
		// TODO Auto-generated method stub
		return commonDao.getAllFeiyxm();
	}
	@Override
	public List<Map<String, Object>> getAllChezXxb() {
		// TODO Auto-generated method stub
		return commonDao.getAllChezXxb();
	}
}
