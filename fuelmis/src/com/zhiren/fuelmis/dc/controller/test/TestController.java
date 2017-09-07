package com.zhiren.fuelmis.dc.controller.test;

import com.zhiren.fuelmis.dc.dao.js.JsDao;
import com.zhiren.fuelmis.dc.dao.kucgl.chukgl.HaoyckDao;
import com.zhiren.fuelmis.dc.entity.kucgl.ChurkBean;
import com.zhiren.fuelmis.dc.entity.kucgl.Shiscbhs;
import com.zhiren.fuelmis.dc.service.common.ILogService;
import com.zhiren.fuelmis.dc.service.impl.jiesgl.MeikjsServiceImpl;
import com.zhiren.fuelmis.dc.service.jiesgl.IMeikjsService;
import com.zhiren.fuelmis.dc.service.kucgl.ICaigrkService;
import com.zhiren.fuelmis.dc.service.webInterface.IWSCommonService;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 刘志宇
 */
@Controller
@RequestMapping("test")
public class TestController {

    @Autowired
    private IMeikjsService meikjsService;
    @Autowired
    private IWSCommonService iwsCommonService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ICaigrkService caigrkService;
    @Autowired
    private ILogService logService;
    @Autowired
    private HaoyckDao haoyckDao;
    @Autowired
    private JsDao jsDao;
    private static Logger logger = LogManager.getLogger(MeikjsServiceImpl.class);

    private SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");

    private DecimalFormat df = new DecimalFormat("0.00");

    @RequestMapping(value = "/model")
    public void rijsJob(@RequestParam(defaultValue = "") String riq, @RequestParam(defaultValue = "") String dianc,
                        HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, Object> map = new HashMap<String, Object>();
        //==========================================测试1====================================================================
//		map.put("rukdbh","20151207216");
//		Renyxx renyxx=(Renyxx)session.getAttribute("user");
//		map.put("user",renyxx);
//		caigrkService.meikrk(map);
        //===========================================测试2===================================================================
/*		String jiesdbh="GD-JS-dsrd-201512-06";
        map.put("jiesdbh",jiesdbh);
		//根据入库单编号更新rl_kc_ruk_meik关于结算的信息
		map.put("caozr","me");
		map.put("advice","provider");
		map.put("huitlc_id","333");
		//3：得到rukdbh
		List<String> rukdbhs=jsDao.getRukdbh(jiesdbh);
		for(String rukdbh:rukdbhs){
			map.put("rukdbh",rukdbh);
			jsDao.updateMeikByRukdbh(map);
		}
		//向rl_kc_ruk_meik_sub 插入结算信息
		List<Map<String,Object>> jiesdlist=jsDao.getJiesdByYuejsdbh(jiesdbh);
		for (Map<String,Object> jiesd:jiesdlist) {
			jsDao.updateMeikSubById(jiesd);
		}*/
        //=============================================测试3=================================================================
        haoyckDao.updateChurkdZhuant(map);
        ChurkBean qicBean = haoyckDao.getQicChurkBean();
        ChurkBean chukBean = haoyckDao.getChukBean();
        ChurkBean rukBean=haoyckDao.getRukBean();

        List<ChurkBean> churkBeens=new ArrayList<ChurkBean>();
        churkBeens.add(chukBean);
        churkBeens.add(rukBean);
        Shiscbhs sh = new Shiscbhs();
        sh.iterate(qicBean,churkBeens);
        System.out.print(churkBeens);
//        sh.updateQicChurkbean(qicBean);
//        sh.updateNewChurkbean(qicBean, chukBean);
//        haoyckDao.saveChurkBean(qicBean);
//        haoyckDao.saveChurkBean(chukBean);
    }
}
