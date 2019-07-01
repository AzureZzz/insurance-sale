package com.query.controller;

import com.alibaba.fastjson.JSONObject;
import com.query.Exception.PasswordErrorException;
import com.query.entity.Admin;
import com.query.service.IAdminService;
import com.query.service.IClerkService;
import com.query.service.IHolderBillService;
import com.query.utils.annotation.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody Map<String,String> map, HttpServletRequest request){
        String adminId = map.get("adminId");
        String password = map.get("password");
        if("".equals(adminId) || "".equals(password)){
            Map<String,String> result = new HashMap<>();
            result.put("msg","用户名或密码不能为空");
            return new ResponseEntity(result,HttpStatus.OK);
        }
        Admin admin = new Admin();
        admin.setUsername(adminId);
        admin.setPassword(password);
        try {
            Admin a = adminService.login(admin);
            if(a==null){
                Map<String,String> result = new HashMap<>();
                result.put("msg","用户名或密码错误!");
                return new ResponseEntity(result,HttpStatus.OK);
            }
            Map<String,String> result = new HashMap<>();
            request.getSession().setAttribute("aId",a.getId());
            result.put("msg","登陆成功！");
            return new ResponseEntity(result,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String,String> result = new HashMap<>();
            result.put("msg","服务器发生未知错误!");
            return new ResponseEntity(result,HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @RequestMapping(value = "logout")
    @Authorization
    public ModelAndView logout(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("login");
        request.getSession().removeAttribute("aId");
        return modelAndView;
    }

    @RequestMapping(value = "admin",method = RequestMethod.POST)
    @Authorization
    public ResponseEntity modifyPassword(@RequestBody Map<String,String> map,
                                         HttpServletRequest request){
        String oldPassword = map.get("oldPassword");
        String newPassword = map.get("newPassword");
        Admin admin = new Admin();
        admin.setPassword(oldPassword);
        admin.setId((Integer) request.getSession().getAttribute("aId"));
        try {
            adminService.modifyPassword(admin,newPassword);
            Map<String,String> result = new HashMap<>();
            result.put("msg","修改成功");
            request.getSession().removeAttribute("cId");
            return new ResponseEntity(result,HttpStatus.OK);
        }catch (PasswordErrorException e){
            e.printStackTrace();
            Map<String,String> result = new HashMap<>();
            result.put("msg","原密码错误！");
            return new ResponseEntity(result,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @Authorization
    @RequestMapping(value = "admin",method = RequestMethod.PUT)
    public ResponseEntity updateAdmin(@RequestBody Admin admin){
        try {
            adminService.updateAdmin(admin);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
