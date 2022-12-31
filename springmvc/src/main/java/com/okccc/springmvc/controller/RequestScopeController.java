package com.okccc.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Author: okccc
 * Date: 2022/11/24 1:45 下午
 * Desc: 向请求域共享数据的方式
 *
 * 1.使用ServletAPI向请求域共享数据(很少用,过于原始)
 *
 * 2.使用ModelAndView向请求域共享数据
 *
 * 3.使用Model(常用)、ModelMap、Map向请求域共享数据
 * Model、ModelMap、Map类型的参数最终都是通过BindingAwareModelMap创建
 * public interface Model {}
 * public class ModelMap extends LinkedHashMap<String, Object> {}
 * public class ExtendedModelMap extends ModelMap implements Model {}
 * public class BindingAwareModelMap extends ExtendedModelMap {}
 */
@Controller
public class RequestScopeController {

    @RequestMapping("/scope/servletApi")
    public String testServletAPI(HttpServletRequest request) {
        request.setAttribute("testRequestScope", "hello, ServletAPI");
        return "success";
    }

    @RequestMapping("/scope/modelAndView")
    public ModelAndView testModelAndView() {
        ModelAndView modelAndView = new ModelAndView();
        // Model功能：向请求域共享数据
        modelAndView.addObject("testRequestScope", "hello, ModelAndView");
        // View功能：渲染视图实现页面跳转
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @RequestMapping("/scope/model")
    public String testModel(Model model) {
        model.addAttribute("testRequestScope", "hello, Model");
        return "success";
    }

    @RequestMapping("/scope/modelMap")
    public String testModelMap(ModelMap modelMap) {
        modelMap.addAttribute("testRequestScope", "hello, ModelMap");
        return "success";
    }

    @RequestMapping("/scope/map")
    public String testMap(Map<String, Object> map) {
        map.put("testRequestScope", "hello, Map");
        return "success";
    }

    @RequestMapping("/scope/session")
    public String testSession(HttpSession session) {
        session.setAttribute("testSessionScope", "hello, session");
        return "success";
    }

    @RequestMapping("/scope/application")
    public String testApplication(HttpSession session) {
        ServletContext servletContext = session.getServletContext();
        servletContext.setAttribute("testApplicationScope", "hello, application");
        return "success";
    }
}
