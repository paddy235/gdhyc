package com.zhiren.fuelmis.dc.service.impl.gongyspg.pingggl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhiren.fuelmis.dc.dao.gongyspg.pingggl.RitspfDao;
import com.zhiren.fuelmis.dc.service.gongyspg.pingggl.IRitspfService;
import com.zhiren.fuelmis.dc.utils.Sequence;
import com.zhiren.fuelmis.dc.utils.TreeUtil;
@Service
public class RitspfServiceImpl implements IRitspfService{
	@Autowired
	private RitspfDao ritspfDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public List<Map<String, Object>> getRitspf(Map<String, Object> map) {
		return ritspfDao.getRitspf(map);
	}
	@Override@Transactional
	public void saveRitspf(List<Map<String, Object>> list) {
		// 为"保存"按钮添加处理程序
			StringBuffer sql = new StringBuffer("begin \n");
			/**
			 * 删除
			 */
			for (Map<String, Object> m : list) {
				sql.append("delete from rl_gys_RIGMJFMXB ").append(" where id =")
				.append(m.get("RIGMJFMXB_ID")).append(";\n");
			}
				
			/**
			 * 修改
			 */

			for (Map<String, Object> m : list) {
				TreeUtil.checkMapNull(m, new String[]{"BEIZ","JIAF"});
				String strRiq = m.get("RIQ").toString();
				long lngGongysb_id = Long.parseLong(m.get("GONGYSB_ID").toString());
				long lngRigmjfb_id = 0;
				long lngRigmjfmxb_id = Long.parseLong(m.get("RIGMJFMXB_ID").toString());
				int intJif = Integer.parseInt(m.get("JIAF").toString());
				String strBeiz = m.get("BEIZ").toString();
				// 检查该供应商、当前日期内是否有计算的评分结果
				String Sql = "select id from rl_gys_RIGMJFB where gongysb_id = "
						+ lngGongysb_id + " and substr(riq,0,10) = '" + strRiq + "'";
				List<Map<String, Object>> l= jdbcTemplate.queryForList(Sql.toString());
				if (l.size()!=0) {
					lngRigmjfb_id = Long.parseLong(l.get(0).get("ID").toString());
				}
				if (lngRigmjfb_id == 0) {
					// insert into RIGMJFB
					sql.append("INSERT INTO rl_gys_RIGMJFB\n"
							+ "  (ID, RIQ, GONGYSB_ID, JIF,rigmjhb_id)\n" + "VALUES\n"
							+ "  ("+Sequence.nextId()+", '" + strRiq + "', "
							+ lngGongysb_id + ", " + intJif + ",(select id from rl_gys_rigmjhb where gongysb_id="+ lngGongysb_id+ " and substr(riq,0,10)='"+ strRiq+ "'));\n");
					// Insert into RIGMJFMXB
					sql
							.append("INSERT INTO rl_gys_RIGMJFMXB\n"
									+ "  (ID, RIGMJFB_ID, ZHIBDM, JIF, BEIZ, LEIX)\n"
									+ "VALUES\n"
									+ "  ("+Sequence.nextId()+", (select id from rl_gys_rigmjfb where substr(riq,0,10) = '"
									+ strRiq + "' and gongysb_id = "
									+ lngGongysb_id + "), 'TSJF', " + intJif
									+ ", '" + strBeiz + "', 2);\n");
				} else {
					// 日供煤积分存在，判断是否有特殊加分
					if (lngRigmjfmxb_id == 0) {
						// Insert into RIGMJFMXB
						sql
								.append("INSERT INTO rl_gys_RIGMJFMXB\n"
										+ "  (ID, RIGMJFB_ID, ZHIBDM, JIF, BEIZ, LEIX)\n"
										+ "VALUES\n"
										+ "  ("+Sequence.nextId()+", (select id from rl_gys_rigmjfb where substr(riq,0,10) = '"
										+ strRiq + "' and gongysb_id = "
										+ lngGongysb_id + "), 'TSJF', "
										+ intJif + ", '" + strBeiz + "', 2);\n");
					} else {
						// Update RIGMJFMXB
						sql.append("update rl_gys_RIGMJFMXB set jif = " + intJif
								+ ",beiz = '" + strBeiz + "' where id = "
								+ lngRigmjfmxb_id + ";\n");
					}
					// 重新计算日供煤积分
					sql
							.append("update rl_gys_rigmjfb set jif = (select sum(jif) from rl_gys_RIGMJFMXB where RIGMJFB_ID = "
									+ lngRigmjfb_id
									+ ") where id = "
									+ lngRigmjfb_id + ";\n");
				}
			}//循环over
			sql.append("\n end;");
			jdbcTemplate.update(sql.toString());
	}
}
