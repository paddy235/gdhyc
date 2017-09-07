package com.zhiren.fuelmis.dc.service.impl.caiygl.caizhbmwh;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.dao.caiygl.caizhbmwh.CaizhbmwhDao;
import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.caiygl.caizhbmwh.ICaizhbmwhService;
import com.zhiren.fuelmis.dc.utils.RandomCode;

@Repository("caizhbmwhService")
public class CaizhbmServiceImpl implements ICaizhbmwhService {
	@Autowired
	private CaizhbmwhDao caizhbmwhDao;

	@Override
	public List<Map<String, Object>> getBianm(String diancid, String riq) {

		List<Map<String, Object>> bianmList = caizhbmwhDao.getBianm(diancid, riq);
		for (int i = 0; i < bianmList.size(); i++) {
			String zhiybm = (String) bianmList.get(i).get("ZHIYBM");
			if (zhiybm == null) {
				bianmList.get(i).put("isNew", true);
			} else {
				bianmList.get(i).put("isNew", false);
			}
		}
		return bianmList;
	}

	@Override
	public void updateBianm(JSONArray array, Renyxx user) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期
		String nowTime = df.format(new Date());// new Date()为获取当前系统时间
		try {
			int n = array.size();

			for (int i = 0; i < n; i++) {
				JSONObject object = array.getJSONObject(i);
				String caiybm = object.getString("CAIYBM");
				String zhiybm = object.getString("ZHIYBM");
				String huaybm = object.getString("HUAYBM");
				String diancid = object.getString("DIANCID");
				if ((boolean) object.get("isNew") == true) {
					System.out.println("this is new !");
					// caizhbmwhDao.insertBianm(object);
					caizhbmwhDao.insertBianm(caiybm, zhiybm, diancid, "1", nowTime, user.getQuanc());
					caizhbmwhDao.insertBianm(zhiybm, huaybm, diancid, "2", nowTime, user.getQuanc());
				} else {
					System.out.println("this is old !");
					// 判斷制样编码是否改变
					// 查询旧的制样编码和化样编码通过采样编码
					List<Map<String, Object>> bianmList = caizhbmwhDao.getBianmByCaiybm(caiybm, diancid);
					System.out.println(bianmList);
					String zhiybm_old = (String) bianmList.get(0).get("ZHIYBM");
					String huaybm_old = (String) bianmList.get(0).get("HUAYBM");

					caizhbmwhDao.updateBianm(caiybm, zhiybm, caiybm, diancid);// 更新製樣編碼

					caizhbmwhDao.updateBianm(zhiybm, huaybm, zhiybm_old, diancid);// 更新化验编码

					// 更新化验单里的化验编码
					caizhbmwhDao.updateHuaybmOfHuayd(huaybm, huaybm_old, diancid);

				}
			}
			// caizhbmwhDao.updateBianm(map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public JSONArray generateBianm(JSONArray array, String riq) {
		try {
			for (int i = 0; i < array.size(); i++) {
				// 1.获取每一个编码组对象
				JSONObject bianmz = array.getJSONObject(i);
				// 2.判断编码中对象的制样编码是否为空
				Object zhiybm = bianmz.get("ZHIYBM");
				if (zhiybm == null || ((String) zhiybm).trim().equals("")) {// 如果为空则生成编码
					while (true) {
						// 1.1产生一个两位随机数并生成一个新的制样编码
						String newBianm = "A" + riq.replace("-", "") + RandomCode.getHexRadomCode(2);
						// 1.2判断该编码是否已经存在
						boolean isExist = this.isExistZhiybm(array, newBianm);
						if (isExist == false) {
							array.getJSONObject(i).put("ZHIYBM", newBianm);
							break;
						}
					}
				}
				Object huaybm = bianmz.get("HUAYBM");
				if (huaybm == null || ((String) huaybm).trim().equals("")) {// 如果为空则生成编码
					while (true) {
						// 2.1产生一个两位随机数并生成一个新的制样编码
						String newBianm = "A" + riq.replace("-","") + RandomCode.getHexRadomCode(3);
						// 2.2判断该编码是否已经存在
						boolean isExist = this.isExistHuaybm(array, newBianm);
						if (isExist == false) {
							array.getJSONObject(i).put("HUAYBM", newBianm);
							break;
						}
					}
				}
				array.getJSONObject(i).put("huayzt", "未化验");
			}
			return array;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	/**
	 * 判断数组中是否含有制样编码
	 * 
	 * @param array
	 *            数组
	 * @param newBianm
	 *            值
	 * @return true：存在， false：不存在。
	 */
	private boolean isExistZhiybm(JSONArray array, String newBianm) {
		boolean isExist = false;
		for (int i = 0; i < array.size(); i++) {
			Object zhiybm = array.getJSONObject(i).get("ZHIYBM");
			if (zhiybm != null && !((String)zhiybm).trim().equals("")) {
				if (zhiybm.equals(newBianm)) {
					isExist = true;
					break;
				}
			}
		}
		return isExist;
	}
	/**
	 * 判断数组中是否含有化验编码
	 * 
	 * @param array
	 *            数组
	 * @param newBianm
	 *            值
	 * @return true：存在， false：不存在。
	 */
	private boolean isExistHuaybm(JSONArray array, String newBianm) {
		boolean isExist = false;
		for (int i = 0; i < array.size(); i++) {
			Object zhiybm = array.getJSONObject(i).get("HUAYBM");
			if (zhiybm != null && !((String)zhiybm).trim().equals("")) {
				if (zhiybm.equals(newBianm)) {
					isExist = true;
					break;
				}
			}
		}
		return isExist;
	}
}
