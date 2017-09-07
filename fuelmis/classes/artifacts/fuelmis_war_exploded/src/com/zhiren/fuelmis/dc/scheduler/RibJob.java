package com.zhiren.fuelmis.dc.scheduler;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.zhiren.fuelmis.dc.service.common.ILogService;
import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.DateUtil.DateFormatType;
import com.zhiren.fuelmis.dc.wsClient.RibClient;

/**
 * @author 陈宝露
 */
@Component("ribJob")
public class RibJob {	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private RibClient ribClient;

	private static Calendar calendar = Calendar.getInstance();
	private static Logger logger = LogManager.getLogger(RibJob.class);

	@Autowired
	private ILogService logServie;
	public void execute() {
		logger.info("********************************日报开始**************************************");
		try {
			List<Map<String, Object>> listShouhcrbb = jdbcTemplate
					.queryForList("select * from shouhcrbb where riq >= to_date('"
							+ getDateBefore7Days() + "','yyyy-MM-dd')");
			ribClient.uploadShouhcrbb(listShouhcrbb);
		} catch (Exception e) {
			logServie.insertLog("shouhcrbb", "RibJob", "execute", e.getMessage(), null, null, null, null, null);
		}

		try {
			List<Map<String, Object>> listShouhcfkb = jdbcTemplate
					.queryForList("select * from shouhcfkb where riq >= to_date('"
							+ getDateBefore7Days() + "','yyyy-MM-dd')");
			ribClient.uploadShouhcfkb(listShouhcfkb);
		} catch (Exception e) {
			logServie.insertLog("shouhcfkb", "RibJob", "execute", e.getMessage(), null, null, null, null, null);
		}
		logger.info("********************************日报结束**************************************");
	}

	private static String getDateBefore7Days() {
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -7);
		return DateUtil.format(calendar.getTime(), DateFormatType.SIMPLE_TYPE);
	}
}
