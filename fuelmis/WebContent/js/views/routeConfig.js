gddlapp.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
    $routeProvider
        .when('/', {templateUrl: 'views/home/home.jsp'})
        .when('/portal', {templateUrl: 'views/main/portal.jsp'})
        //修改密碼
        .when('/changePassword/:id', {templateUrl: 'views/home/changePassword.jsp'})
        /********************************************计划管理************************************************************/
        .when('/yudjhcx', {templateUrl: 'views/jih/yudjhcx.jsp'})
        .when('/yuedrljh', {templateUrl: 'views/jih/yuedrljh.jsp'})
        .when('/yuedcaigjh', {templateUrl: 'views/jih/yuedcaigjh.jsp'})
        .when('/yuedjhtj', {templateUrl: 'views/jih/yuedjhtj.jsp'})
        .when('/yuedcaigjhadd', {templateUrl: 'views/jih/yuedcaigjhadd.jsp'})
        .when('/yuedcaigjhadd/:riq', {templateUrl: 'views/jih/yuedcaigjhadd.jsp'})
        .when('/yuedcgjhAdd/:riq/:diancid/:id/:title', {templateUrl: 'views/jih/addyuedcgjh.jsp'})
        .when('/yuedcgjhUpdate/:id/:title/:riq', {templateUrl: 'views/jih/addyuedcgjh.jsp'})
        .when('/yuedranlzfjhadd', {templateUrl: 'views/jih/yuedranlzfjhadd.jsp'})
        .when('/yuedranlzfjhadd/:riq', {templateUrl: 'views/jih/yuedranlzfjhadd.jsp'})
        .when('/yuedralzfjhAdd/:riq/:diancid/:id/:title', {templateUrl: 'views/jih/addyuedranlzfjh.jsp'})
        .when('/yuedranlzfjhUpdate/:id/:title/:riq', {templateUrl: 'views/jih/addyuedranlzfjh.jsp'})
        .when('/jihgl/yuejhrl/yuedjhzbyc', {templateUrl: 'views/jih/yuedjh/yuedjhzbyc.jsp'})
        .when('/jihgl/nianjhrl/niandjhzbyc', {templateUrl: 'views/jih/niandjh/niandjhzbyc.jsp'})
        .when('/niandrljh', {templateUrl: 'views/jih/niandrljh.jsp'})
        .when('/niandjhtj', {templateUrl: 'views/jih/niandjhtj.jsp'})
        .when('/niandcaigjh', {templateUrl: 'views/jih/niandcaigjh.jsp'})
        .when('/niandcaigjhadd', {templateUrl: 'views/jih/niandcaigjhadd.jsp'})
        .when('/niandcaigjhadd/:nianf', {templateUrl: 'views/jih/niandcaigjhadd.jsp'})
        .when('/niandcaigAdd/:nianf/:diancid/:id/:title', {templateUrl: 'views/jih/addniandcaigjh.jsp'})
        .when('/niandcgjhUpdate/:id/:title/:nianf', {templateUrl: 'views/jih/addniandcaigjh.jsp'})
        .when('/niandranlzfjhadd', {templateUrl: 'views/jih/niandranlzfjhadd.jsp'})
        .when('/niandranlzfjhadd/:nianf', {templateUrl: 'views/jih/niandranlzfjhadd.jsp'})
        .when('/niandralzfjhAdd/:nianf/:diancid/:id/:title', {templateUrl: 'views/jih/addniandranlzfjh.jsp'})
        .when('/niandranlzfjhUpdate/:id/:title/:nianf', {templateUrl: 'views/jih/addniandranlzfjh.jsp'})

        /**********************************************合同管理**********************************************************/
        //合同管理-查询打印
        .when('/hetcx', {templateUrl: 'views/hetgl/chaxdy/hetcx.jsp'})
        .when('/pingsyjb', {templateUrl: 'views/hetgl/chaxdy/pingsyjb.jsp'})
        .when('/hetgl/caigddb', {templateUrl: 'views/hetgl/caigddb/caigddb.jsp'})
        .when('/addCaigddb/:flag', {templateUrl: 'views/hetgl/caigddb/add.jsp'})
        .when('/addCaigddb/:flag/:id', {templateUrl: 'views/hetgl/caigddb/ad' +
        'd.jsp'})
        .when('/showCaigddb/:id', {templateUrl: 'views/hetgl/caigddb/show.jsp'})
        .when('/hetgl/hetmb', {templateUrl: 'views/hetgl/hetmb/hetmb.jsp'})
        .when('/addHetmb/:flag', {templateUrl: 'views/hetgl/hetmb/add.jsp'})
        .when('/editHetmb/:id', {templateUrl: 'views/hetgl/hetmb/edit.jsp'})
        .when('/test', {templateUrl: 'views/test/test.jsp'})
        .when('/hetcaigddpp', {templateUrl: 'views/hetgl/caigddb/caigddpp.jsp'})
        .when('/hetgl/hetinfo', {templateUrl: 'views/hetgl/hetbean/hetbean.jsp'})
        .when('/hetgl/jijfs/:caigddb_id/:caigddb_sub_id', {templateUrl: 'views/hetgl/caigddb/jijfs.jsp'})
        .when('/addHetbean/:flag', {templateUrl: 'views/hetgl/hetbean/add.jsp'})
        .when('/addHetbean/:flag/:id', {templateUrl: 'views/hetgl/hetbean/add.jsp'})
        .when('/addHetbean1/:flag', {templateUrl: 'views/hetgl/hetbean/add1.jsp'})
        .when('/addHetbean1/:flag/:id', {templateUrl: 'views/hetgl/hetbean/add1.jsp'})
        .when('/addHetbean2/:flag', {templateUrl: 'views/hetgl/hetbean/add2.jsp'})
        .when('/addHetbean2/:flag/:id', {templateUrl: 'views/hetgl/hetbean/add2.jsp'})
        .when('/addShengxmy/:flag/:mbid', {templateUrl: 'views/hetgl/hetbean/shengxmy.jsp'})
        .when('/addZhangdy/:flag/:mbid', {templateUrl: 'views/hetgl/hetbean/zhangdy.jsp'})
        .when('/addYitgf/:flag/:mbid', {templateUrl: 'views/hetgl/hetbean/yitgf.jsp'})
        .when('/4500Qnet_template/:flag/:mbid', {templateUrl: 'views/hetgl/hychetbean/4500Qnet_template.jsp'})
        .when('/4000Qnet_template/:flag/:mbid', {templateUrl: 'views/hetgl/hychetbean/4000Qnet_template.jsp'})
        .when('/5000Qnet_template/:flag/:mbid', {templateUrl: 'views/hetgl/hychetbean/5000Qnet_template.jsp'})
        .when('/addGongcm201601/:flag/:mbid', {templateUrl: 'views/hetgl/hetbean/hetmb/gongcm201601.jsp'})
        .when('/addZhongglm201601/:flag/:mbid', {templateUrl: 'views/hetgl/hetbean/hetmb/zhongglm201601.jsp'})
        .when('/addDlm201601/:flag/:mbid', {templateUrl: 'views/hetgl/hetbean/hetmb/dilm201601.jsp'})
        .when('/yemian-shuangxin/:flag/:mbid', {templateUrl: 'views/hetgl/hetbean/hetmb/yemian-shuangxin.jsp'})
        .when('/yemian-eerduosi/:flag/:mbid', {templateUrl: 'views/hetgl/hetbean/hetmb/yemian-eerduosi.jsp'})
        .when('/gongcm-2016-shuangxin/:flag/:mbid', {templateUrl: 'views/hetgl/hetbean/hetmb/gongcm-2016-shuangxin.jsp'})
        .when('/zhongglm-2016-eedssmsm/:flag/:mbid', {templateUrl: 'views/hetgl/hetbean/hetmb/zhongglm-2016-eedssmsm.jsp'})
        .when('/hetgl/pricecomponet', {templateUrl: 'views/hetgl/pricecomponet/pricecomponet.jsp'})
        .when('/addPriceComponet/:flag', {templateUrl: 'views/hetgl/pricecomponet/add.jsp'})
        .when('/addPriceComponet/:flag/:id', {templateUrl: 'views/hetgl/pricecomponet/add.jsp'})
        .when('/addQalityRange/:priceItem/:id', {templateUrl: 'views/hetgl/pricecomponet/addqalityrange.jsp'})
        .when('/hetgl/priceitem', {templateUrl: 'views/hetgl/priceitem/priceitem.jsp'})
        .when('/addPriceItem/:flag', {templateUrl: 'views/hetgl/priceitem/add.jsp'})
        .when('/addPriceItem/:flag/:id', {templateUrl: 'views/hetgl/priceitem/add.jsp'})
        .when('/hetgl/priceqalityrange', {templateUrl: 'views/hetgl/priceqalityrange/priceqalityrange.jsp'})
        .when('/addPriceQalityRange/:flag', {templateUrl: 'views/hetgl/priceqalityrange/add.jsp'})
        .when('/addPriceQalityRange/:flag/:id', {templateUrl: 'views/hetgl/priceqalityrange/add.jsp'})
        /**********************************************采样管理**********************************************************/
        .when('/caiygl/caiyzmd', {templateUrl: 'views/caiygl/caiyzmd/caiyzmd.jsp'})
        .when('/caiygl/bianmcx', {templateUrl: 'views/caiygl/bianmcx/bianmcx.jsp'})
        .when('/caiygl/caizhbmwh', {templateUrl: 'views/caiygl/caizhbmwh/caizhbmwh.jsp'})
        .when('/caiygl/heby', {templateUrl: 'views/caiygl/caizhbmwh/heby.jsp'})

        /**********************************************验收管理**********************************************************/
        /*验收管理 - 报表查询- 过衡明细*/
        .when('/guohmx', {templateUrl: 'views/yansgl/baobcx/guohmx.jsp'})
        //入厂数量-入厂数量
        .when('/shujsh', {templateUrl: 'views/rucsl/rucsl/shujsh.jsp'})
        .when('/shujsh/:zhuangt/:sDate/:eDate', {templateUrl: 'views/rucsl/rucsl/shujsh.jsp'})
        .when('/shujsh_mx/:id/:zhuangt/:sDate/:eDate', {templateUrl: 'views/rucsl/rucsl/shujsh_mx.jsp'})
        .when('/yansgl/rucsl/sanfsllr', {templateUrl: 'views/rucsl/rucsl/sanfsllr.jsp'})
        .when('/yansgl/rucsl/shujplsh', {templateUrl: 'views/rucsl/rucsl/shujplsh.jsp'})
        //入厂质量-化验审核
        .when('/ruchyyjsh', {templateUrl: 'views/ruchy/huaysh/ruchyyjsh.jsp'})
        .when('/ruchyejsh', {templateUrl: 'views/ruchy/huaysh/ruchyejsh.jsp'})
        .when('/yijsh', {templateUrl: 'views/ruchy/shenh/yijsh.jsp'})
        .when('/erjsh', {templateUrl: 'views/ruchy/shenh/erjsh.jsp'})
        .when('/erjshht', {templateUrl: 'views/ruchy/shenh/erjshht.jsp'})
        .when('/rucmyjcjg', {templateUrl: 'views/ruchy/rucmyjcjg/rucmyjcjg.jsp'})
        .when('/ruczl/huayzlr', {templateUrl: 'views/ruchy/shenh/huayzlr.jsp'})
        .when('/ruczl/huaybm', {templateUrl: 'views/ruchy/shenh/huaybm.jsp'})
        .when('/yasgl/henjbl/zhilhjbl', {templateUrl: 'views/yansgl/ruchjbl/zhilhjbl.jsp'})
        //过衡日报
        .when('/guohrb', {templateUrl: 'views/rib/guohrb.jsp'})
        //数量台账
        .when('/shultz', {templateUrl: 'views/rucsl/shultz/shultz.jsp'})
        .when('/huayrb', {templateUrl: 'views/rucsl/huayrb/huayrb.jsp'})
        .when('/huaytz', {templateUrl: 'views/rucsl/huaytz/huaytz.jsp'})
        .when('/huaytzRiq', {templateUrl: 'views/rucsl/huaytz/huaytzRiq.jsp'})
        .when('/zonghtz', {templateUrl: 'views/rucsl/zonghtz/zonghtz.jsp'})
        //化验报告(新增)
        .when('/ruchybg', {templateUrl: 'views/rucsl/ruchybg/ruchybg.jsp'})
        .when('/rucslys/chepbtmp', {templateUrl: 'views/rucslys/chepbtmp.jsp'})
        //数量痕迹保留
        .when('/shulhjbl', {templateUrl: 'views/yansgl/shulhjbl.jsp'})

        /*********************************************库存管理**********************************************************/
        .when('/kuczz', {templateUrl: 'views/kucgl/kucgl/kuczz.jsp'})
        .when('/kucwz', {templateUrl: 'views/kucgl/kucgl/kucwz.jsp'})
        .when('/kuaijqdy', {templateUrl: 'views/kucgl/kucgl/kuaijqdy.jsp'})
        .when('/kucwl', {templateUrl: 'views/kucgl/kucgl/kucwl.jsp'})
        .when('/guanl', {templateUrl: 'views/kucgl/kucgl/guanl.jsp'})
        .when('/kucftsz', {templateUrl: 'views/kucgl/kucgl/kucftsz.jsp'})
        .when('/kucgl/caigrk/churkdlr', {templateUrl: 'views/kucgl/caigrk/churkdlr/churkdlr.jsp'})
        //库存报表查询
        .when('/kucsscbhs', {templateUrl: 'views/kucgl/sscbhs/kucsscbhs.jsp'})
        .when('/fadgrhyckbb', {templateUrl: 'views/kucgl/kucbbcx/fadgrhyckbb.jsp'})
        //库存管理
        .when('/shujlr', {templateUrl: 'views/kucgl/shujlr/shujlr.jsp'})
        .when('/pandcx', {templateUrl: 'views/kucgl/pandcx/pandcx.jsp'})
        .when('/ranlcgrk', {templateUrl: 'views/kucgl/caigrk/ranlcgrk.jsp'})
        .when('/addRanlcgrk', {templateUrl: 'views/kucgl/caigrk/addRanlcgrk.jsp'})
        .when('/addRanlcgrk/:rukdbh', {templateUrl: 'views/kucgl/caigrk/addRanlcgrk.jsp'})
        .when('/yansmx', {templateUrl: 'views/kucgl/caigrk/yansmx.jsp'})
        .when('/yunzfcgrk', {templateUrl: 'views/kucgl/caigrk/yunzfcgrk.jsp'})
        .when('/addYunzfcgrk', {templateUrl: 'views/kucgl/caigrk/addYunzfcgrk.jsp'})
        .when('/addYunzfcgrk/:rukdbh', {templateUrl: 'views/kucgl/caigrk/addYunzfcgrk.jsp'})
        .when('/yansmx_yunzf', {templateUrl: 'views/kucgl/caigrk/yansmx_yunzf.jsp'})
        .when('/qitrk', {templateUrl: 'views/kucgl/caigrk/qitrk.jsp'})
        .when('/addQitrk', {templateUrl: 'views/kucgl/caigrk/addQitrk.jsp'})
        //		.when('/addQitrk/:rukdbh',{templateUrl:'views/kucgl/caigrk/addQitrk.jsp'})
        .when('/editQitrk/:rukdbh', {templateUrl: 'views/kucgl/caigrk/editQitrk.jsp'})
        .when('/shiscbhs', {templateUrl: 'views/kucgl/shiscbhs/shiscbhs.jsp'})
        .when('/kucgl/kucbbcx/chukdbb', {templateUrl: 'views/kucgl/kucbbcx/chukdbb.jsp'})
        .when('/kucgl/kucbbcx/riclhcbb', {templateUrl: 'views/kucgl/kucbbcx/riclhcbb.jsp'})
        .when('/kucgl/kucbbcx/riclhcbbmx', {templateUrl: 'views/kucgl/kucbbcx/riclhcbbmx.jsp'})
        .when('/kucgl/kucbbcx/chukcxbb', {templateUrl: 'views/kucgl/kucbbcx/chukcxbb.jsp'})
        .when('/kucgl/kucbbcx/ranmzgmx',{templateUrl: 'views/kucgl/kucbbcx/ranmzgmx.jsp'})//燃煤暂估明细表
        .when('/kucgl/shangccw', {templateUrl: 'views/kucgl/shangccw/shangccw.jsp'})
        //出库管理
        .when('/fadgrhyck', {templateUrl: 'views/kucgl/chukgl/fadgrhyck.jsp'})
        .when('/churkdb', {templateUrl: 'views/kucgl/chukgl/churkdb/churkdb.jsp'})
        .when('/addFadgrhyck', {templateUrl: 'views/kucgl/chukgl/addFadgrhyck.jsp'})
        .when('/editFadgrhyck/:rukdbh', {templateUrl: 'views/kucgl/chukgl/editFadgrhyck.jsp'})
        .when('/changeCaigdd/:rukdbh', {templateUrl: 'views/kucgl/caigrk/changeCaigdd.jsp'})
        .when('/changeCaigdd_yunzf/:rukdbh', {templateUrl: 'views/kucgl/caigrk/changeCaigdd_yunzf.jsp'})
        /*********************************************结算管理**********************************************************/
        //结算管理-报表查询
        .when('/jiesdcx', {templateUrl: 'views/jiesgl/meikjs/jiesdcx.jsp'})
        .when('/hycjiesdcx', {templateUrl: 'views/jiesgl/meikjs/hycjiesdcx.jsp'})
        .when('/jiestz', {templateUrl: 'views/jiesgl/meikjs/jiestz.jsp'})
        .when('/yuejstjtz', {templateUrl: 'views/jiesgl/baobcx/yuejstjtz.jsp'})
        //结算管理-煤款结算
        .when('/meikrjs', {templateUrl: 'views/jiesgl/meikjs/meikrjs.jsp'})
        .when('/meikrjsdj/:id', {templateUrl: 'views/jiesgl/meikjs/meikrjsdj.jsp'})
        .when('/meikyjs', {templateUrl: 'views/jiesgl/meikjs/meikyjs.jsp'})
        .when('/hycmeikyjs', {templateUrl: 'views/jiesgl/meikjs/hycmeikyjs.jsp'})
        .when('/hyccreatejiesd', {templateUrl: 'views/jiesgl/meikjs/hycjiesd_template.jsp'})
        .when('/hycjiesdxg_new', {templateUrl: 'views/jiesgl/meikjs/hycjiesdxg.jsp'})
        .when('/jiesgl/meikjs/zafjs', {templateUrl: 'views/jiesgl/meikjs/zafjs.jsp'})
        .when('/jiesgl/meikjs/zafjswh', {templateUrl: 'views/jiesgl/meikjs/zafjswh.jsp'})
        .when('/jiesdxg', {templateUrl: 'views/jiesgl/meikjs/jiesdxg.jsp'})
        .when('/rijstz', {templateUrl: 'views/jiesgl/meikjs/rijstz.jsp'})
        .when('/rijssc', {templateUrl: 'views/jiesgl/rijssc/rijssc.jsp'})
        //结算管理-杂费结算
        .when('/jiesgl/zafjs/zafjswh', {templateUrl: 'views/jiesgl/zafjs/zafjswh.jsp'})
        //发票维护
        .when('/jiesGetAll', {templateUrl: 'views/jies/jies.jsp'})
        .when('/jiesGetAll/:meikxxb_id/:jiesbid/:faprq', {templateUrl: 'views/jies/jies.jsp'})
        .when('/jiesAdd/:title', {templateUrl: 'views/jies/addjies.jsp'})
        .when('/jiesAdd', {templateUrl: 'views/jies/addjies.jsp'})
        .when('/fapUpdate/:id/:title', {templateUrl: 'views/jies/updatejies.jsp'})
        .when('/jiesUpdate', {templateUrl: 'views/jies/updatejies.jsp'})
        //发票查询
        .when('/fapcx', {templateUrl: 'views/jies/fapcx.jsp'})

        /*********************************************厂内费用**********************************************************/
        /*厂内费用*/
        //财务预算
        .when('/yuedyussq', {templateUrl: 'views/cnfy/caiwys/yuedyssq.jsp'})
        .when('/yuedyussq/:riq/:diancid', {templateUrl: 'views/cnfy/caiwys/yuedyssq.jsp'})
        .when('/yuedyusAdd/:riq/:diancid/:id/:title', {templateUrl: 'views/cnfy/caiwys/addorupdateyuedyssq.jsp'})
        .when('/yuedyusUpdate/:id/:title/:riq/:diancid', {templateUrl: 'views/cnfy/caiwys/addorupdateyuedyssq.jsp'})
        .when('/niandyussq', {templateUrl: 'views/cnfy/caiwys/niandyssq.jsp'})
        .when('/niandyussq/:nianf/:diancid', {templateUrl: 'views/cnfy/caiwys/niandyssq.jsp'})
        .when('/niandyusAdd/:nianf/:diancid/:id/:title', {templateUrl: 'views/cnfy/caiwys/addorupdateniandyssq.jsp'})
        .when('/niandyusUpdate/:id/:title/:nianf/:diancid', {templateUrl: 'views/cnfy/caiwys/addorupdateniandyssq.jsp'})
        .when('/yustzsq', {templateUrl: 'views/cnfy/caiwys/yustzsq.jsp'})
        .when('/yustzsq/:diancid/:zafid', {templateUrl: 'views/cnfy/caiwys/yustzsq.jsp'})
        .when('/yustzsqAdd/:zafid/:diancid/:id/:title', {templateUrl: 'views/cnfy/caiwys/addorupdateyustzsq.jsp'})
        .when('/yustzsqUpdate/:id/:title/:zafid/:diancid', {templateUrl: 'views/cnfy/caiwys/addorupdateyustzsq.jsp'})
        .when('/feiyxmsq', {templateUrl: 'views/cnfy/caiwys/feiyxmsq.jsp'})
        .when('/feiyxmsq/:feiyxmfl/:diancid', {templateUrl: 'views/cnfy/caiwys/feiyxmsq.jsp'})
        .when('/feiyxmsqAdd/:feiyxmfl/:diancid/:id/:title', {templateUrl: 'views/cnfy/caiwys/addorupdatefeiyxmsq.jsp'})
        .when('/feiyxmsqUpdate/:id/:title/:feiyxmfl/:diancid', {templateUrl: 'views/cnfy/caiwys/addorupdatefeiyxmsq.jsp'})
        .when('/yuszhcx', {templateUrl: 'views/cnfy/caiwys/yuszhcx.jsp'})
        //费用项目
        .when('/feiyxmflcx', {templateUrl: 'views/cnfy/feiyxm/feiyxmfl.jsp'})
        .when('/feiyxmflAdd/:id/:title', {templateUrl: 'views/cnfy/feiyxm/addorupdatefeiyxmfl.jsp'})
        .when('/feiyxmflUpdate/:id/:title', {templateUrl: 'views/cnfy/feiyxm/addorupdatefeiyxmfl.jsp'})
        .when('/feiyxmwh', {templateUrl: 'views/cnfy/feiyxm/feiyxmwh.jsp'})
        .when('/feiyxmwhAdd/:id/:title', {templateUrl: 'views/cnfy/feiyxm/addorupdatefeiyxmwh.jsp'})
        .when('/feiyxmwhUpdate/:id/:title', {templateUrl: 'views/cnfy/feiyxm/addorupdatefeiyxmwh.jsp'})
        //费用明细
        .when('/feiymx', {templateUrl: 'views/cnfy/feiymx/feiymx.jsp'})
        .when('/feiybxddisplay/:id', {templateUrl: 'views/cnfy/feiymx/feiybxd.jsp'})
        //杂费结算
        .when('/zafjswh', {templateUrl: 'views/cnfy/zafjs/zafjswh.jsp'})
        .when('/zafjswh/:nianf', {templateUrl: 'views/cnfy/zafjs/zafjswh.jsp'})
        .when('/zafjsbxdAdd/:id/:title/:nianf', {templateUrl: 'views/cnfy/zafjs/zafjs.jsp'})
        .when('/zafjsbxdUpdate/:id/:title/:nianf', {templateUrl: 'views/cnfy/zafjs/zafjs.jsp'})
        //杂费合同
        .when('/zafht', {templateUrl: 'views/cnfy/zafht/zafht.jsp'})
        .when('/zafhtAdd/:title', {templateUrl: 'views/cnfy/zafht/addzafht.jsp'})
        .when('/zafhtUpdate/:id/:title', {templateUrl: 'views/cnfy/zafht/updatezafht.jsp'})
        /*********************************************入炉管理**********************************************************/
        //入炉管理
        .when('/rulhyd', {templateUrl: 'views/rulgl/baobcx/rulhyd.jsp'})
        .when('/ruljzbb', {templateUrl: 'views/rulgl/baobcx/ruljzbb.jsp'})
        .when('/ruljztz', {templateUrl: 'views/rulgl/baobcx/ruljztz.jsp'})
        .when('/rucrlrzc', {templateUrl: 'views/rulgl/baobcx/rucrlrzc.jsp'})
        .when('/sis_shujcx', {templateUrl: 'views/rulgl/baobcx/sis_shujcx.jsp'})
        /*入炉关联班组信息*/
        .when('/rulbanz', {templateUrl: 'views/rulgl/baseSet/banzxx.jsp'})
        /*入炉关联机组信息*/
        .when('/ruljiz', {templateUrl: 'views/rulgl/baseSet/jizxx.jsp'})
        //入炉管理-相关维护
        .when('/meihy', {templateUrl: 'views/rulgl/xianggwh/meihy.jsp'})
        .when('/meihysis', {templateUrl: 'views/rulgl/xianggwh/meihysis.jsp'})
        .when('/rulmpp', {templateUrl: 'views/rulgl/xianggwh/rulmpp.jsp'})
        /*********************************************调运管理**********************************************************/
        /*日报管理*/
        .when('/ribcx', {templateUrl: 'views/rib/ribcx.jsp'})
        .when('/diaodjh', {templateUrl: 'views/diaodjh/diaodjh.jsp'})
        .when('/diaodjhinfo', {templateUrl: 'views/diaodjh/diaodjhinfo.jsp'})
        .when('/addDiaodjh/:flag', {templateUrl: 'views/diaodjh/add.jsp'})
        .when('/addDiaodjh/:flag/:id', {templateUrl: 'views/diaodjh/add.jsp'})
        .when('/diaoygl/ribtb', {templateUrl: 'views/diaoygl/ribtb/ribtb.jsp'})
        /*调运管理-日报管理*/
        .when('/ribtb', {templateUrl: 'views/diaoygl/ribtb/ribtb.jsp'})
        .when('/ribgs', {templateUrl: 'views/diaoygl/ribgs/ribgs.jsp'})
        .when('/diaoygl/diaodgl/riddsh', {templateUrl: 'views/diaoygl/diaodgl/riddsh.jsp'})
        .when('/diaoygl/diaodgl/diaodjhwh', {templateUrl: 'views/diaoygl/diaodgl/diaodjhwh.jsp'})
        .when('/diaoygl/boabcx/jihd', {templateUrl: 'views/diaoygl/baobcx/jihd.jsp'})
        .when('/diaoygl/boabcx/duizd', {templateUrl: 'views/diaoygl/baobcx/duizd.jsp'})
        .when('/ranmzhrbb', {templateUrl: 'views/diaoygl/ribtb/ranmzhrbb.jsp'})
        .when('/dianmcgjgrb', {templateUrl: 'views/diaoygl/ribtb/dianmcgjgrb.jsp'})
        /*********************************************供应商管理*********************************************************/
        .when('/gongyspg/kaohzb', {templateUrl: 'views/gongyspg/kaohzb/kaohzb.jsp'})
        .when('/addKaohzb/:flag', {templateUrl: 'views/gongyspg/kaohzb/add.jsp'})
        .when('/addKaohzb/:flag/:id', {templateUrl: 'views/gongyspg/kaohzb/add.jsp'})
        .when('/gongyspg/rizbpf', {templateUrl: 'views/gongyspg/gongyspggl/rizbpf.jsp'})
        .when('/gongyspg/ritspf', {templateUrl: 'views/gongyspg/gongyspggl/ritspf.jsp'})
        .when('/gongyspg/yuezbpf', {templateUrl: 'views/gongyspg/gongyspggl/yuezbpf.jsp'})
        .when('/gongyspg/gongmjhtz', {templateUrl: 'views/gongyspg/yuegmjh/gongmjhtz.jsp'})
        .when('/gongyspg/gongmjhcx', {templateUrl: 'views/gongyspg/yuegmjh/gongmjhcx.jsp'})
        .when('/gongyspg/jicxx/ritszb', {templateUrl: 'views/gongyspg/jicxx/ritszb.jsp'})
        .when('/gongyspg/pingffa', {templateUrl: 'views/gongyspg/pingffa/pingffa.jsp'})
        .when('/addPingffa/:flag', {templateUrl: 'views/gongyspg/pingffa/add.jsp'})
        .when('/addPingffa/:flag/:id', {templateUrl: 'views/gongyspg/pingffa/add.jsp'})
        .when('/gongyspg/pingffaxzb', {templateUrl: 'views/gongyspg/pingffaxzb/pingffaxzb.jsp'})
        .when('/addPingffaxzb/:flag', {templateUrl: 'views/gongyspg/pingffaxzb/add.jsp'})
        .when('/addPingffaxzb/:flag/:id', {templateUrl: 'views/gongyspg/pingffaxzb/add.jsp'})
        .when('/hetgl/rlhtmb', {templateUrl: 'views/hetgl/rlhetmb/rlhetmb.jsp'})
        .when('/addRlhtmb/:flag', {templateUrl: 'views/hetgl/rlhetmb/add.jsp'})
        .when('/addRlhtmb/:flag/:id', {templateUrl: 'views/hetgl/rlhetmb/add.jsp'})
        .when('/gongyspg/rigmjh', {templateUrl: 'views/gongyspg/rigmjh/rigmjh.jsp'})
        /*发货表管理*/
        .when('/gongyspg/fahb', {templateUrl: 'views/gongyspg/fahb/fahb.jsp'})
        .when('/gongyspg/hetb', {templateUrl: 'views/gongyspg/hetb/hetb.jsp'})
        .when('/addHetb/:flag', {templateUrl: 'views/gongyspg/hetb/add.jsp'})
        .when('/addHetb/:flag/:id', {templateUrl: 'views/gongyspg/hetb/add.jsp'})
        .when('/gongyspg/yuegmjh', {templateUrl: 'views/gongyspg/yuegmjh/yuegmjh.jsp'})
        .when('/gongyspg/gongmjhzl', {templateUrl: 'views/gongyspg/gongmjhzl/gongmjhzl.jsp'})
        //供应商管理-报表查询
        .when('/gongys/baobcx/gongysypfcx', {templateUrl: 'views/gongyspg/baobcx/gongysypfcx.jsp'})
        //<!-- 供应商管理 - 合同执行管理 - 日计划质量填报 -->
        .when('/gongyspg/rijhzltb', {templateUrl: 'views/gongyspg/rijhzltb/rijhzltb.jsp'})

        /*********************************************月报管理**********************************************************/
        //月报管理-数据维护
        .when('/yuebsjpz', {templateUrl: 'views/yuebgl/shujwh/yuebsjpz.jsp'})
        .when('/rucsl', {templateUrl: 'views/yuebgl/shujwh/rucsl.jsp'})
        .when('/ruczl', {templateUrl: 'views/yuebgl/shujwh/ruczl.jsp'})
        .when('/haochj', {templateUrl: 'views/yuebgl/shujwh/haochj.jsp'})
        .when('/haocmx', {templateUrl: 'views/yuebgl/shujwh/haocmx.jsp'})
        .when('/rucbmdj', {templateUrl: 'views/yuebgl/shujwh/rucbmdj.jsp'})
        .when('/zhibqk', {templateUrl: 'views/yuebgl/shujwh/zhibqk.jsp'})
        .when('/rulrz', {templateUrl: 'views/yuebgl/shujwh/rulrz.jsp'})
        .when('/youshc', {templateUrl: 'views/yuebgl/shujwh/youshc.jsp'})
        .when('/zafwh', {templateUrl: 'views/yuebgl/shujwh/zafwh.jsp'})
        .when('/duibbmdj', {templateUrl: 'views/yuebgl/shujwh/duibbmdj.jsp'})
        .when('/rucbddb', {templateUrl: 'views/yuebgl/shujwh/rucbddb.jsp'})
        .when('/rucrlkcbmdj', {templateUrl: 'views/yuebgl/shujwh/rucrlkcbmdj.jsp'})

        //月报管理-新增报表
        .when('/fenkcgrcbmdj', {templateUrl: 'views/xinzbb/fenkcgrcbmdj.jsp'})
        .when('/laihcl', {templateUrl: 'views/xinzbb/laihcl.jsp'})
        .when('/rucbddbwcsjbb', {templateUrl: 'views/xinzbb/rucbddbbb.jsp'})
        .when('/kucbmdj', {templateUrl: 'views/xinzbb/kucbmdj.jsp'})


        //月报管理-国电报表
        .when('/meishcbb', {templateUrl: 'views/yuebgl/guodbb/meishcbb.jsp'})
        .when('/shulysbb', {templateUrl: 'views/yuebgl/guodbb/shulysbb.jsp'})
        .when('/zhilysbb', {templateUrl: 'views/yuebgl/guodbb/zhilysbb.jsp'})
        .when('/rucbmdjbb', {templateUrl: 'views/yuebgl/guodbb/rucbmdjbb.jsp'})
        .when('/ranyshc', {templateUrl: 'views/yuebgl/guodbb/ranyshc.jsp'})
        .when('/rulmrz', {templateUrl: 'views/yuebgl/guodbb/rulmrz.jsp'})
        .when('/ranlcbbb', {templateUrl: 'views/yuebgl/guodbb/ranlcbbb.jsp'})
        .when('/changnfycx', {templateUrl: 'views/yuebgl/guodbb/changnfycx.jsp'})
        .when('/changnfycxMX', {templateUrl: 'views/yuebgl/guodbb/changnfycxMX.jsp'})
        .when('/ranlzbqkb', {templateUrl: 'views/yuebgl/guodbb/ranlzbqkb.jsp'})
        .when('/dianmcgjgyb', {templateUrl: 'views/yuebgl/guodbb/dianmcgjgyb.jsp'})
        .when('/rucmydb', {templateUrl: 'views/yuebgl/guodbb/rucmydb.jsp'})
        //月报管理-月报上传
        .when('/yuebsc', {templateUrl: 'views/yuebgl/yuebsc/yuebsc.jsp'})
        /*********************************************工作流***********************************************************/
        /*工作流-三级审批*/
        /*http://10.115.25.122:8089/gdsjsp/user/list 用户信息维护*/
        .when('/gongzl/jicxxwh/yonghxxwh', {templateUrl: 'views/gongzl/jicxxwh/yonghxxwh.jsp'})
        /*http://10.115.25.122:8089/gdsjsp/group/list 用户组信息维护*/
        .when('/gongzl/jicxxwh/yonghzxxwh', {templateUrl: 'views/gongzl/jicxxwh/yonghzxxwh.jsp'})
        /*http://10.115.25.122:8089/gdsjsp/membership/list 用户和用户组关系维护*/
        .when('/gongzl/jicxxwh/membership', {templateUrl: 'views/gongzl/jicxxwh/membership.jsp'})
        /*http://10.115.25.122:8089/gdsjsp/Activiti/list 流程部署*/
        .when('/gongzl/jicxxwh/Activiti', {templateUrl: 'views/gongzl/jicxxwh/Activiti.jsp'})
        /*http://10.115.25.122:8089/gdsjsp/Shenp/todotaskslist?userId=#uesrId 审批任务*/
        .when('/gongzl/Shenp/todotaskslist', {templateUrl: 'views/gongzl/Shenp/todotaskslist.jsp'})
        /*http://10.115.25.122:8089/gdsjsp/Shenp/tofinishedlist?userId=#uesrId 历史审批任务*/
        .when('/gongzl/Shenp/tofinishedlist', {templateUrl: 'views/gongzl/Shenp/tofinishedlist.jsp'})
        /*http://10.115.25.122:8089/gdsjsp/Shenp/torunninglist?userId=#uesrId 查看运行时任务*/
        .when('/gongzl/Shenp/torunninglist', {templateUrl: 'views/gongzl/Shenp/torunninglist.jsp'})
        /*********************************************系统管理***********************************************************/
        //系统管理
        .when('/ziyxx', {templateUrl: 'views/xitgl/ziyxx/ziyxx.jsp'})
        .when('/renyxx', {templateUrl: 'views/xitgl/renyxx/renyxx.jsp'})
        .when('/modifyRenyxx/:flag', {templateUrl: 'views/xitgl/renyxx/modify.jsp'})
        .when('/modifyRenyxx/:flag/:id', {templateUrl: 'views/xitgl/renyxx/modify.jsp'})
        .when('/shezqx/:id', {templateUrl: 'views/xitgl/renyxx/quanx.jsp'})
        .when('/diancxx', {templateUrl: 'views/xitgl/diancxx/diancxx.jsp'})
        .when('/modifyDiancxx/:flag', {templateUrl: 'views/xitgl/diancxx/modify.jsp'})
        .when('/modifyDiancxx/:flag/:id', {templateUrl: 'views/xitgl/diancxx/modify.jsp'})
        .when('/meikdq', {templateUrl: 'views/xitgl/meikdq/meikdq.jsp'})
        .when('/modifyMeikdq/:flag', {templateUrl: 'views/xitgl/meikdq/modify.jsp'})
        .when('/modifyMeikdq/:flag/:id', {templateUrl: 'views/xitgl/meikdq/modify.jsp'})
        .when('/gongys', {templateUrl: 'views/xitgl/gongys/gongys.jsp'})
        .when('/modifyGongys/:flag', {templateUrl: 'views/xitgl/gongys/modify.jsp'})
        .when('/modifyGongys/:flag/:id', {templateUrl: 'views/xitgl/gongys/modify.jsp'})
        .when('/meikxx', {templateUrl: 'views/xitgl/meikxx/meikxx.jsp'})
        .when('/modifyMeikxx/:flag', {templateUrl: 'views/xitgl/meikxx/modify.jsp'})
        .when('/modifyMeikxx/:flag/:id', {templateUrl: 'views/xitgl/meikxx/modify.jsp'})
        .when('/shezChez/:id', {templateUrl: 'views/xitgl/meikxx/shezChez.jsp'})
        .when('/guanlGongys/:id', {templateUrl: 'views/xitgl/meikxx/guanlGongys.jsp'})
        .when('/chezxx', {templateUrl: 'views/xitgl/chezxx/chezxx.jsp'})
        .when('/modifyChezxx/:flag', {templateUrl: 'views/xitgl/chezxx/modify.jsp'})
        .when('/modifyChezxx/:flag/:id', {templateUrl: 'views/xitgl/chezxx/modify.jsp'})
        .when('/liclx', {templateUrl: 'views/xitgl/liclx/liclx.jsp'})
        .when('/modifyLiclx/:flag', {templateUrl: 'views/xitgl/liclx/modify.jsp'})
        .when('/modifyLiclx/:flag/:id', {templateUrl: 'views/xitgl/liclx/modify.jsp'})
        .when('/lic', {templateUrl: 'views/xitgl/lic/lic.jsp'})
        .when('/modifyLic/:flag', {templateUrl: 'views/xitgl/lic/modify.jsp'})
        .when('/modifyLic/:flag/:id', {templateUrl: 'views/xitgl/lic/modify.jsp'})
        .when('/meizxx', {templateUrl: 'views/xitgl/meizxx/meizxx.jsp'})
        .when('/modifyMeizxx/:flag', {templateUrl: 'views/xitgl/meizxx/modify.jsp'})
        .when('/modifyMeizxx/:flag/:id', {templateUrl: 'views/xitgl/meizxx/modify.jsp'})
        .when('/pinzxx', {templateUrl: 'views/xitgl/pinzxx/pinzxx.jsp'})
        .when('/modifyPinzxx/:flag', {templateUrl: 'views/xitgl/pinzxx/modify.jsp'})
        .when('/modifyPinzxx/:flag/:id', {templateUrl: 'views/xitgl/pinzxx/modify.jsp'})
        .when('/yunsdw', {templateUrl: 'views/xitgl/yunsdw/yunsdw.jsp'})
        .when('/modifyYunsdw/:flag', {templateUrl: 'views/xitgl/yunsdw/modify.jsp'})
        .when('/modifyYunsdw/:flag/:id', {templateUrl: 'views/xitgl/yunsdw/modify.jsp'})
        .when('/jiz', {templateUrl: 'views/xitgl/jiz/jiz.jsp'})
        .when('/modifyJiz/:flag', {templateUrl: 'views/xitgl/jiz/modify.jsp'})
        .when('/modifyJiz/:flag/:id', {templateUrl: 'views/xitgl/jiz/modify.jsp'})
        //配煤
        /*.when('/peimeijs',{templateUrl:'views/peim/peimei.jsp'})
         .when('/yijpeimei',{templateUrl:'views/peim/yijpeimei.jsp'})
         .when('/yijpeimeisearch',{templateUrl:'views/peim/yijpeimsearch.jsp'})
         .when('/yunscdweih',{templateUrl:'views/peim/xinxweih/yunscd.jsp'})
         .when('/addorupdateyunscdweih/:id/:title',{templateUrl:'views/peim/xinxweih/addorupdateyunscd.jsp'})
         .when('/meiyuanweih',{templateUrl:'views/peim/xinxweih/meiyuan.jsp'})
         .when('/addorupdatemeiyuanweih/:id/:title',{templateUrl:'views/peim/xinxweih/addorupdatemeiyuan.jsp'})
         .when('/meishanweih',{templateUrl:'views/peim/xinxweih/meishan.jsp'})
         .when('/addorupdatemeishanweih/:id/:title',{templateUrl:'views/peim/xinxweih/addorupdatemeishan.jsp'})
         .when('/diaoyjh',{templateUrl:'views/peim/diaoyjh.jsp'})
         .when('/chedtz',{templateUrl:'views/peim/chedtz.jsp'})
         .when('/meiccp',{templateUrl:'views/peim/meiccp.jsp'})*/
        /**
         * 测试页面
         */
        .when('/test', {templateUrl: 'views/test/test.jsp'})
        .otherwise({redirectTo: '/'});
}]);
