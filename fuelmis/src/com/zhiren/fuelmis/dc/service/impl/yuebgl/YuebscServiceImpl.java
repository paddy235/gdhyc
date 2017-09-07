package com.zhiren.fuelmis.dc.service.impl.yuebgl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.yuebgl.YuebscDao;
import com.zhiren.fuelmis.dc.service.yuebgl.IYuebscService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 陈宝露
 */
@Service
public class YuebscServiceImpl implements IYuebscService {

	@Autowired
	private YuebscDao yuebscDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public JSONArray getYueb(Map<String, Object> map) {
		List<Map<String, Object>> list = yuebscDao.getYueb(map);
		JSONArray jsonArray = new JSONArray();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				jsonArray.add(list.get(i));
			}
		}
		return jsonArray;
	}

	@Override
	@Transactional
	public void yuebsc(Map<String, Object> map, List<Map<String, Object>> yueb) throws Exception {
		String sql = "begin\n";
		String riq = map.get("riq").toString();
		String diancid = map.get("diancid").toString();
		for (Map<String, Object> y : yueb) {
			String zt = null;
			if (y.get("ZHUANGT") != null) {
				zt = y.get("ZHUANGT").toString();
			}
			String sb = null;
			if (y.get("SHIFSB") != null) {
				sb = y.get("SHIFSB").toString();
			}
			String tn = y.get("ID").toString();
			if (zt != null && (zt.equals("0")||zt.equals("2")) && sb != null && sb.equals("true")) {
				List<Map<String, Object>> list = jdbcTemplate.queryForList(
						"SELECT col.column_name name\n" + "  FROM USER_tab_cols col  \n" + " WHERE table_name = '"
								+ tn.toUpperCase() + "'  \n" + "   AND column_name IN ('YUETJKJB_ID')");
				String name = null;
				if (list.size() != 0) {
					name = list.get(0).get("NAME").toString();
				}
				if (name != null) {
					sql += "update " + tn + " set zhuangt=1\n"
							+ "where yuetjkjb_id in (select id from yuetjkjb where substr(riq,0,7)='" + riq
							+ "' and diancxxb_id=" + diancid + ");\n";
				} else {
					sql += "update " + tn + " set zhuangt=1\n" + "where substr(riq,0,7)='" + riq + "' and diancxxb_id="
							+ diancid + ";\n";
				}

			}
		}
		sql += "end;";
		jdbcTemplate.update(sql);
	}
}
