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

    private static final String json = "{\"pageNo\":1,\"pageSize\":1000,\"totalPage\":1,\"total\":146,\"records\":[{\"userId\":\"452ca0aadd904245b0fee55671fa788d\",\"tenantId\":\"e10adc3949ba59abbe56e057f20f88dd\",\"realname\":\"shui\",\"account\":\"shui123\",\"email\":\"123456@qq.com\",\"mobile\":\"18899963\",\"passwd\":null,\"skin\":null,\"lockStatus\":null,\"lockEndTime\":null,\"accountExpire\":null,\"passwdExpire\":1575968162000,\"imgPath\":\"/tenant/userimages/default.png\",\"crossUserId\":null,\"type\":null,\"position\":null,\"empId\":null,\"ext\":null,\"addTime\":1575968162000,\"status\":null,\"apiKeys\":null,\"sex\":null,\"tel\":null,\"openId\":null,\"wxStatus\":null,\"language\":null,\"id\":null},{\"userId\":\"205dd1bc5ba74a9a824304fdc190612d\",\"tenantId\":\"e10adc3949ba59abbe56e057f20f88dd\",\"realname\":\"shui\",\"account\":\"bigMax\",\"email\":\"123456@qq.com\",\"mobile\":\"18899964\",\"passwd\":null,\"skin\":null,\"lockStatus\":1,\"lockEndTime\":null,\"accountExpire\":null,\"passwdExpire\":1575964173000,\"imgPath\":\"/tenant/userimages/default.png\",\"crossUserId\":null,\"type\":null,\"position\":null,\"empId\":null,\"ext\":null,\"addTime\":1575964173000,\"status\":null,\"apiKeys\":null,\"sex\":null,\"tel\":null,\"openId\":null,\"wxStatus\":null,\"language\":null,\"id\":null},{\"userId\":\"c428ddd0f66c4c338e878021056090c0\",\"tenantId\":\"e10adc3949ba59abbe56e057f20f88dd\",\"realname\":\"shui\",\"account\":\"LxkLxkLxk\",\"email\":\"123456@qq.com\",\"mobile\":\"18899964523\",\"passwd\":null,\"skin\":null,\"lockStatus\":null,\"lockEndTime\":null,\"accountExpire\":null,\"passwdExpire\":1575963992000,\"imgPath\":\"/tenant/userimages/default.png\",\"crossUserId\":null,\"type\":null,\"position\":null,\"empId\":null,\"ext\":null,\"addTime\":1575963992000,\"status\":null,\"apiKeys\":null,\"sex\":null,\"tel\":null,\"openId\":null,\"wxStatus\":null,\"language\":null,\"id\":null}]}";


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
    @RequestMapping(value = "getUsers", method = RequestMethod.GET)
    public String getUsers(@RequestParam(required = false, value = "tenant_id") String tenantId,
                           @RequestParam(required = false, value = "page_size") Integer pageSize) {
        LOG.debug("---------------TestController: getUsers---------------");

        System.out.println("http get ");
        System.out.println("tenantId：" + tenantId);
        System.out.println("pageSize：" + pageSize);
        System.out.println("get 请求到此一游。。。");

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
