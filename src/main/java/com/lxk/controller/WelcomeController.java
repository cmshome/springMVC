package com.lxk.controller;

import com.google.common.collect.Lists;
import com.lxk.httpModel.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//import com.lxk.service.AreaService;

/**
 * Created by lxk on 2017/6/5
 */
@Controller
@RequestMapping("welcome")
public class WelcomeController {
    private static final Logger LOG = LoggerFactory.getLogger(WelcomeController.class);

    //@Resource(name = "areaService")
    //private AreaService areaService;

    //@Resource(name = "reportExportRecordsRepositoryCustom")
    //private ReportExportRecordsRepositoryCustom reportExportRecordsRepositoryCustom;

    /**
     * init：初始化页面
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView init() {
        LOG.debug("---------------BlogController: init---------------");

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/welcome/LetterDrop");
        return mav;
    }

    /**
     * 测试service抛的异常，controller是可以catch的。
     */
    @ResponseBody
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public JsonResult get() {
        LOG.debug("---------------BlogController: get---------------");
        try {
            //areaService.exception();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new JsonResult(false, e.getMessage());
        }
        return new JsonResult(true, "lxk", "");
    }


    /**
     * init：初始化页面
     */
    @ResponseBody
    @RequestMapping(value = "lxk", method = RequestMethod.GET)
    public JsonResult inits() {
        LOG.debug("---------------BlogController: init---------------");
        //List<Area> all = areaService.findAll();
        //boolean mongoStateOK = areaService.isMongoStateOK();
        //System.out.println(mongoStateOK);
        List<String> ids = Lists.newArrayList("577c6f2a393096c91a4b6f37");
        //Map<String, Integer> stringIntegerMap = reportExportRecordsRepositoryCustom.searchReportSettingCountVOByReportSettingIds(ids);
        //System.out.println(stringIntegerMap);
        return new JsonResult(true, "lxk", null);
    }
}
