package com.query.controller;

import com.query.entity.Admin;
import com.query.service.IAdminService;
import com.query.service.IClerkService;
import com.query.service.IHolderBillService;
import com.query.utils.annotation.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PathController {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IHolderBillService holderBillService;

    @Autowired
    private IClerkService clerkService;

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String toLogin(){
        return "login";
    }

    @Authorization
    @RequestMapping(value = "index")
    public ModelAndView toIndex(HttpServletRequest request){
        try {
            ModelAndView modelAndView = new ModelAndView("index");
            Admin admin = adminService.getAdmin((Integer) request.getSession().getAttribute("aId"));
            admin.setPassword("");
            modelAndView.addObject("admin",admin);
            modelAndView.addObject("saleNum",holderBillService.getBillsCount());
            modelAndView.addObject("clerkNum",clerkService.getClerksCount());
            List<Integer> n1 = holderBillService.getWeekCount();
            modelAndView.addObject("chart1",n1);
            List<Integer> n2 = holderBillService.getPolsCount();
            modelAndView.addObject("chart2",n2);
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Authorization
    @RequestMapping(value = "allbills")
    public ModelAndView toBills(HttpServletRequest request){
        try {
            ModelAndView modelAndView = new ModelAndView("bills");
            Admin admin = adminService.getAdmin((Integer) request.getSession().getAttribute("aId"));
            admin.setPassword("");
            modelAndView.addObject("admin",admin);
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Authorization
    @RequestMapping(value = "allclerks")
    public ModelAndView toClerks(HttpServletRequest request){
        try {
            ModelAndView modelAndView = new ModelAndView("clerks");
            Admin admin = adminService.getAdmin((Integer) request.getSession().getAttribute("aId"));
            admin.setPassword("");
            modelAndView.addObject("admin",admin);
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Authorization
    @RequestMapping(value = "newclerk")
    public ModelAndView toNewClerk(HttpServletRequest request){
        try {
            ModelAndView modelAndView = new ModelAndView("newclerk");
            Admin admin = adminService.getAdmin((Integer) request.getSession().getAttribute("aId"));
            admin.setPassword("");
            modelAndView.addObject("admin",admin);
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
