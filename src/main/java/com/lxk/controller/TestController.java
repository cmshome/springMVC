package com.lxk.controller;

import com.google.common.collect.Maps;
import com.lxk.httpModel.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 测试spring的线程安全问题
 *
 * @author LiXuekai on 2018/12/18
 */
@Controller
@RequestMapping("test")
public class TestController {
    private static final Logger LOG = LoggerFactory.getLogger(WelcomeController.class);

    public static final Map<String, String> MAP = Maps.newHashMap();

    @Value("${citic_ip_info_json_result:{\"returnCode\":\"1\",\"totalNum\":1000,\"returnNum\":100,\"returnMsg\":[{\"mainName\":\"短信平台\",\"sysName\":\"控股短信平台\",\"ipAddress\":\"1.1.1.1\"},{\"sysName\":\"分行网络-长春分行\",\"ipAddress\":\"2.2.2.2\"},{\"sysName\":\"分行网络-北京分行\",\"ipAddress\":\"3.3.3.3\"},{\"sysName\":\"分行网络-南京分行\",\"ipAddress\":\"4.4.4.4\"},{\"sysName\":\"分行网络-xx分行\",\"ipAddress\":\"5.5.5.5\"},{\"sysName\":\"分行网络-yy分行\",\"ipAddress\":\"6.6.6.6\"},{\"mainName\":\"\",\"sysName\":\"分行网络-zz分行\",\"ipAddress\":\"7.7.7.7\"},{}]}}")
    private String jsonMap;

    private static final String json = "{\"pageNo\":1,\"pageSize\":1000,\"totalPage\":1,\"total\":146,\"records\":[{\"userId\":\"452ca0aadd904245b0fee55671fa788d\",\"tenantId\":\"e10adc3949ba59abbe56e057f20f88dd\",\"realname\":\"shui\",\"account\":\"lxk1\",\"email\":\"123456@qq.com\",\"mobile\":\"18899963\",\"passwd\":null,\"skin\":null,\"lockStatus\":null,\"lockEndTime\":null,\"accountExpire\":null,\"passwdExpire\":1575968162000,\"imgPath\":\"/tenant/userimages/default.png\",\"crossUserId\":null,\"type\":null,\"position\":null,\"empId\":null,\"ext\":null,\"addTime\":1575968162000,\"status\":null,\"apiKeys\":null,\"sex\":null,\"tel\":null,\"openId\":null,\"wxStatus\":null,\"language\":null,\"id\":null},{\"userId\":\"205dd1bc5ba74a9a824304fdc190612d\",\"tenantId\":\"e10adc3949ba59abbe56e057f20f88dd\",\"realname\":\"shui\",\"account\":\"lxk2\",\"email\":\"123456@qq.com\",\"mobile\":\"18899964\",\"passwd\":null,\"skin\":null,\"lockStatus\":1,\"lockEndTime\":null,\"accountExpire\":null,\"passwdExpire\":1575964173000,\"imgPath\":\"/tenant/userimages/default.png\",\"crossUserId\":null,\"type\":null,\"position\":null,\"empId\":null,\"ext\":null,\"addTime\":1575964173000,\"status\":null,\"apiKeys\":null,\"sex\":null,\"tel\":null,\"openId\":null,\"wxStatus\":null,\"language\":null,\"id\":null},{\"userId\":\"c428ddd0f66c4c338e878021056090c0\",\"tenantId\":\"e10adc3949ba59abbe56e057f20f88dd\",\"realname\":\"shui\",\"account\":\"lxk3\",\"email\":\"123456@qq.com\",\"mobile\":\"18899964523\",\"passwd\":null,\"skin\":null,\"lockStatus\":null,\"lockEndTime\":null,\"accountExpire\":null,\"passwdExpire\":1575963992000,\"imgPath\":\"/tenant/userimages/default.png\",\"crossUserId\":null,\"type\":null,\"position\":null,\"empId\":null,\"ext\":null,\"addTime\":1575963992000,\"status\":null,\"apiKeys\":null,\"sex\":null,\"tel\":null,\"openId\":null,\"wxStatus\":null,\"language\":null,\"id\":null}]}";


    /**
     * init：初始化页面
     */
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public JsonResult inits(@RequestParam(required = false) Integer pageNo, @RequestParam(required = false) Integer pageSize) {
        LOG.debug("---------------TestController: init---------------");
        LOG.debug("---------------pageNo:{}", pageNo);
        LOG.debug("---------------pageSize:{}", pageSize);

        System.out.println("http get ");
        System.out.println("pageNo：" + pageNo);
        System.out.println("pageSize：" + pageSize);

        if (MAP.isEmpty()) {
            MAP.put(System.currentTimeMillis() + "", "大师兄--get");
        }
        System.out.println("get 请求到此一游。。。");
        return new JsonResult(true, "lxk", MAP);
    }

    /**
     * init：初始化页面
     */
    @ResponseBody
    @RequestMapping(value = "getUserLogin", method = RequestMethod.GET)
    public String getUserLogin(@RequestParam(required = false, value = "token") String token) {
        LOG.debug("---------------TestController: init---------------");
        LOG.debug("---------------token:{}", token);

        System.out.println("http get ");
        System.out.println("token：" + token);
        System.out.println("get 请求到此一游。。。 getUserLogin and return admin user login ....");
        String json = "{\"errCode\":null,\"message\":null,\"data\":{\"tenantId\":\"2b308777268849d7b2c2c29cdc5d3896\",\"realname\":\"测试\",\"username\":null,\"tenantName\":null,\"contacts\":null,\"email\":\"testaa@uyun.com\",\"mobile\":\"15665656565\",\"passwd\":null,\"industryId\":null,\"companySizeId\":null,\"userId\":\"2b308777268849d7b2c2c29cdc5d3896\",\"thirdPartyUserId\":null,\"userType\":null,\"apiKeys\":[{\"id\":\"e4cf86aa6274499aa763d45d4c4d6c7d\",\"tenantId\":\"2b308777268849d7b2c2c29cdc5d3896\",\"key\":\"15708061da104f3688ab9ab36097a23f\",\"addTime\":1574144298000,\"userId\":\"2b308777268849d7b2c2c29cdc5d3896\",\"secretKey\":\"e75043f153854606bac067c08514a00e1c3d06df\",\"hashValue\":null}],\"addTime\":1574144298000,\"office\":null,\"removed\":null,\"payType\":\"charge\",\"language\":\"zh_CN\",\"timezone\":null,\"timezoneId\":null,\"licenseType\":1,\"description\":null,\"inviteCode\":null,\"qq\":null,\"weixin\":null,\"site\":null,\"skin\":null,\"root\":true,\"status\":\"1\",\"products\":[{\"productId\":\"1e42b7a151ea434d800319190257f6a4\",\"productNum\":\"0001\",\"productName\":\"0001\",\"description\":\"测试第三方登录\",\"productUrl\":\"http://192.168.11.106:8080/thirdlogin\",\"enabled\":1,\"removed\":0,\"orderby\":1,\"addTime\":1574142818000,\"relevancyTime\":null,\"role\":0,\"productType\":null,\"productPicUrl\":null,\"othersFlag\":null,\"version\":null,\"headEnabled\":null,\"logo\":null,\"logoName\":null,\"sessionInvalidCallbackUrl\":null},{\"productId\":\"6ef8a9b74dcc4596a13368309253748d\",\"productNum\":\"134567899\",\"productName\":\"测试第三方登录\",\"description\":\"测试第三方登录\",\"productUrl\":\"http://192.168.11.106:8080/thirdlogin/th/getUserDetail\",\"enabled\":1,\"removed\":0,\"orderby\":1,\"addTime\":1574143902000,\"relevancyTime\":null,\"role\":1,\"productType\":null,\"productPicUrl\":null,\"othersFlag\":null,\"version\":null,\"headEnabled\":null,\"logo\":null,\"logoName\":null,\"sessionInvalidCallbackUrl\":null}],\"userExcess\":null,\"userNo\":\"lxk3\",\"userCount\":null,\"tenantRoleIds\":null,\"imagePath\":\"/tenant/userimages/default.png\",\"astrictExpiry\":null,\"expiryDate\":null,\"passwordExpiryDate\":1574144298481,\"crossDomainStatus\":null,\"topUserId\":null,\"properties\":null},\"mode\":\"offline\",\"language\":\"zh_CN\"}";
        System.out.println(json);
        return json;
    }

    /**
     * init：初始化页面
     */
    @ResponseBody
    @RequestMapping(value = "getUsers", method = RequestMethod.GET)
    public String getUsers(@RequestParam(required = false, value = "tenant_id") String tenantId,
                           @RequestParam(required = false, value = "page_size") Integer pageSize) {
        LOG.debug("---------------TestController: getUsers---------------");

        System.out.println("http get ");
        System.out.println("tenantId：" + tenantId);
        System.out.println("pageSize：" + pageSize);
        System.out.println("get 请求到此一游。。。获取   同步用户信息  ，用来同步到 EZSonar 的");

        return json;
    }

    /**
     * init：初始化页面
     */
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String post(@RequestParam(required = false) Integer pageNo, @RequestParam(required = false) Integer pageSize) {
        LOG.info("---------------TestController: post---------------");
        LOG.info("---------------pageNo:{}", pageNo);
        LOG.info("---------------pageSize:{}", pageSize);

        System.out.println("http post ");
        System.out.println("pageNo：" + pageNo);
        System.out.println("pageSize：" + pageSize);

        System.out.println(jsonMap);
        return jsonMap;
    }

}
