package com.sale.controller;

import com.alibaba.fastjson.JSONObject;
import com.sale.entity.Clerk;
import com.sale.exceptions.PasswordErrorException;
import com.sale.service.IClerkService;
import com.sale.utils.annotation.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
public class ClerkController {

    @Autowired
    private RedisTemplate<String, Serializable> redis;

    @Autowired
    public void setRedis (RedisTemplate<String, Serializable> redis) {
        this.redis = redis;
    }

    @Autowired
    private IClerkService clerkService;


    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody Map<String,String> map, HttpServletRequest request){
        String clerkId = map.get("clerkId");
        String password = map.get("password");
        if("".equals(clerkId) || "".equals(password)){
            Map<String,String> result = new HashMap<>();
            result.put("msg","用户名或密码不能为空");
            return new ResponseEntity(result,HttpStatus.OK);
        }
        Clerk clerk = new Clerk();
        clerk.setClerkId(Integer.parseInt(clerkId));
        clerk.setPassword(password);
        try {
            Clerk c = clerkService.login(clerk);
            if(c==null){
                Map<String,String> result = new HashMap<>();
                result.put("msg","用户名或密码错误!");
                return new ResponseEntity(result,HttpStatus.OK);
            }
            Map<String,String> result = new HashMap<>();
            request.getSession().setAttribute("cId",c.getClerkId());
            redis.boundValueOps(c.getClerkId()+"").set ("isLogin", 2, TimeUnit.HOURS);
            result.put("msg","登陆成功！");
            return new ResponseEntity(result,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String,String> result = new HashMap<>();
            result.put("msg","服务器发生未知错误!");
            return new ResponseEntity(result,HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @Authorization
    @RequestMapping(value = "logout")
    public ModelAndView logout(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("login");
        redis.delete(request.getSession().getAttribute("cId")+"");
        request.getSession().removeAttribute("cId");
        return modelAndView;
    }

    @Authorization
    @RequestMapping(value = "clerk",method = RequestMethod.PUT)
    public ResponseEntity updateClerk(@RequestBody Clerk clerk){
        try {
            clerkService.updateClerk(clerk);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @Authorization
    @RequestMapping(value = "clerk",method = RequestMethod.GET)
    public ResponseEntity getClerk(HttpServletRequest request){
        try {
            Clerk clerk = clerkService.getClerk((Integer) request.getSession().getAttribute("cId"));
            return new ResponseEntity(clerk,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @Authorization
    @RequestMapping(value = "clerk",method = RequestMethod.POST)
    public ResponseEntity modifyPassword(@RequestBody Map<String,String> map, HttpServletRequest request){
        String oldPassword = map.get("oldPassword");
        String newPassword = map.get("newPassword");
        Clerk clerk = new Clerk();
        clerk.setPassword(oldPassword);
        clerk.setClerkId((Integer) request.getSession().getAttribute("cId"));
        try {
            clerkService.modifyPassword(clerk,newPassword);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg","修改成功");
            request.getSession().removeAttribute("cId");
            return new ResponseEntity(jsonObject,HttpStatus.OK);
        }catch (PasswordErrorException e){
            e.printStackTrace();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg","原密码错误！");
            return new ResponseEntity(jsonObject,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
