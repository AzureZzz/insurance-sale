package com.sale.controller;

import com.sale.entity.Clerk;
import com.sale.service.IClerkService;
import com.sale.service.IHolderBillService;
import com.sale.utils.annotation.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PathController {

    @Autowired
    private IClerkService clerkService;

    @Autowired
    private IHolderBillService holderBillService;

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String toLogin(){
        return "login";
    }

    @Authorization
    @RequestMapping(value = "index")
    public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response){
        try {
            Integer id = (Integer) request.getSession().getAttribute("cId");
            ModelAndView modelAndView = new ModelAndView("index");
            Clerk Clerk = clerkService.getClerk(id);
            Clerk.setPassword("");
            modelAndView.addObject("allNum",holderBillService.getIdBillsCount(id));
            modelAndView.addObject("todayNum",holderBillService.getTodayCount(id));
            modelAndView.addObject("weekNum",holderBillService.getWeekCount(id));
            modelAndView.addObject("clerk",Clerk);
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Authorization
    @RequestMapping(value = "mybills")
    public ModelAndView toMyBills(HttpServletRequest request){
        try {
            ModelAndView modelAndView = new ModelAndView("mybills");
            Clerk Clerk = clerkService.getClerk((Integer) request.getSession().getAttribute("cId"));
            modelAndView.addObject("clerk",Clerk);
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Authorization
    @RequestMapping(value = "newbill")
    public ModelAndView toNewBill(HttpServletRequest request){
        try {
            ModelAndView modelAndView = new ModelAndView("newbill");
            Clerk Clerk = clerkService.getClerk((Integer) request.getSession().getAttribute("cId"));
            modelAndView.addObject("clerk",Clerk);
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
