package com.zhiren.fuelmis.dc.scheduler;

import java.util.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import com.zhiren.fuelmis.dc.dao.kucgl.chukgl.ChuksjdrDao;
import com.zhiren.fuelmis.dc.dao.kucgl.rukgl.RuksjdrDao;
import com.zhiren.fuelmis.dc.utils.Sequence;

/**
 * @author 陈宝露
 */
@Component("kucsjdrJob")
public class KucsjdrJob {

	private static Logger logger = LogManager.getLogger(KucsjdrJob.class);
	@Autowired
	private RuksjdrDao ruksjdrDao;
	@Autowired
	private ChuksjdrDao chuksjdrDao;

	public void execute() {
		try {
			List<Map<String, Object>> kuaijrqList = ruksjdrDao.getOpenKuaijrq();
			for (Map<String, Object> kuaijrqMap : kuaijrqList) {
				ruksjdrDao.insertMonthTotal(kuaijrqMap);
			}
			boolean flag = false;
			logger.info("***************************自动导入rl_kc_ruk_meik,rl_kc_ruk_meik_sub************************");
			// 1删除表中没有的入库编号的数据
			ruksjdrDao.deleteMeik();
			ruksjdrDao.deleteMeikSub();
			ruksjdrDao.deleteKucmxhzb();
			ruksjdrDao.updateChurkdbDelete();
			// 2查出要更新和要插入的入库单编号
			List<String> rukdbhs = ruksjdrDao.getRukdbhs();// shifdr
			for (String rukdbh : rukdbhs) {
				// 3根据入库单编号判断主表中是否存在相应的入库单
				String rukdID = ruksjdrDao.getRukdID(rukdbh);
				if (rukdID != null) {// 4如果存在则先更新主表的数据
					ruksjdrDao.updateRukd(rukdID);
					ruksjdrDao.deleteRukdSub(rukdID);
					ruksjdrDao.insertRukdSub(rukdbh);
					List<String> kucmxhzbIDs = ruksjdrDao.getKucmxhzbID(rukdbh);
					if (kucmxhzbIDs.size() != 0) {
						List<Map<String, Object>> kucmxList = ruksjdrDao.getKucmxList(rukdbh);
						for (Map<String, Object> kucmx : kucmxList) {
							ruksjdrDao.updateKucmxhzb(kucmx);
						}

					} else {
						ruksjdrDao.insertKucmxhzb(rukdbh);
					}
				} else {
					rukdID = Sequence.nextId();
					ruksjdrDao.insertRukd(rukdID, rukdbh);
					ruksjdrDao.insertRukdSub(rukdbh);
					ruksjdrDao.insertKucmxhzb(rukdbh);
				}
				ruksjdrDao.updateChurkd(rukdbh);// 将shuo有shifdr状态改为1
				flag = true;
			}
			logger.info("***************************自动导入rl_kc_chukd,rl_kc_chukd_sub************************");
			chuksjdrDao.deleteChukd();
			chuksjdrDao.deleteChukdSubByNothing();
			chuksjdrDao.deleteKucmxhzb();
			chuksjdrDao.updateChurkdbDelete();
			List<String> chukdbhs = chuksjdrDao.getChukdbhs();// shifdr
			for (String chukdbh : chukdbhs) {
				String chukdID = chuksjdrDao.getChukdID(chukdbh);
				if (chukdID != null) {
					chuksjdrDao.updateChukd(chukdID);
					chuksjdrDao.deleteChukdSub(chukdID);
					chuksjdrDao.insertChukdSub(chukdbh);
					List<String> kucmxhzbIDs = chuksjdrDao.getKucmxhzbID(chukdbh);
					if (kucmxhzbIDs.size() != 0) {
						List<Map<String, Object>> kucmxList = chuksjdrDao.getKucmxList(chukdbh);
						for (Map<String, Object> kucmx : kucmxList) {
							chuksjdrDao.updateKucmxhzb(kucmx);
						}
					} else {
						chuksjdrDao.insertKucmxhzb(chukdbh);
					}
				} else {
					chukdID = Sequence.nextId();
					chuksjdrDao.insertChukd(chukdID, chukdbh);
					chuksjdrDao.insertChukdSub(chukdbh);
					chuksjdrDao.insertKucmxhzb(chukdbh);
				}
				chuksjdrDao.updateChurkd(chukdbh);
				flag = true;
			}
			logger.info("***************************自动导入rl_kc_ruk_yunzf,rl_kc_ruk_yunzf_sub************************");
			ruksjdrDao.deleteYunzfRukd();
			ruksjdrDao.deleteYunzfRukdSubByNothing();
			ruksjdrDao.updateYunzfDeleteb();
			rukdbhs = ruksjdrDao.getYunzfRukdbhs();// shifdr
			for (String rukdbh : rukdbhs) {
				String rukdID = ruksjdrDao.getYunzfRukdID(rukdbh);
				if (rukdID != null) {// 4如果存在则先更新主表的数据
					ruksjdrDao.updateYunzfRukd(rukdID);
					ruksjdrDao.deleteYunzfRukdSub(rukdID);
					ruksjdrDao.insertYunzfRukdSub(rukdbh);
					List<String> kucmxhzbIDs = ruksjdrDao.getKucmxhzbID(rukdbh);
					if (kucmxhzbIDs.size() != 0) {
						List<Map<String, Object>> kucmxList = ruksjdrDao.getYunzfKucmxList(rukdbh);
						for (Map<String, Object> kucmx : kucmxList) {
							ruksjdrDao.updateYunzfKucmxhzb(kucmx);
						}

					} else {
						ruksjdrDao.insertYunzfKucmxhzb(rukdbh);
					}
				} else {
					rukdID = Sequence.nextId();
					ruksjdrDao.insertYunzfRukd(rukdID, rukdbh);
					ruksjdrDao.insertYunzfRukdSub(rukdbh);
					ruksjdrDao.insertYunzfKucmxhzb(rukdbh);
				}
				ruksjdrDao.updateChurkd(rukdbh);// 将shuo有shifdr状态改为1
				flag = true;
			}
			if (flag) {
				this.chukdj();
			}
			logger.info("************************************库存数据自动导入结束****************************************");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void chukdj() {
		List<String> kuczzIDs = chuksjdrDao.getKuczzIDs();
		for (String kuczzID : kuczzIDs) {
			List<Map<String, Object>> condition = chuksjdrDao.getChukdjCondition(kuczzID);
			for (Map<String, Object> map : condition) {
				map.put("KUCZZ", kuczzID);
				chuksjdrDao.deleteChukdj(map);
				chuksjdrDao.insertChukdj(map);
			}
		}
	}
}
