package com.query.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.query.entity.Clerk;
import com.query.entity.HolderBill;
import com.query.service.IClerkService;
import com.query.utils.annotation.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ClerkController {

    @Autowired
    private IClerkService clerkService;

    @Authorization
    @RequestMapping(value = "clerks",method = RequestMethod.GET)
    public ResponseEntity queryClerksPage(@RequestParam String aoData){
        String[] cols = {"CLERK_ID","SEX","CLERK_NAME","MOBILE"};
        JSONArray jsonArray = JSONArray.parseArray(aoData);
        String sEcho = null;
        int iDisplayStart = 0;
        int iDisplayLength = 0;
        String column = null;
        boolean sortDir = true;
        String keyWord = null;
        for (int i = 0; i <jsonArray.size() ; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if(jsonObject.get("name").equals("sSortDir_0")){
                if(jsonObject.get("value").equals("desc")){
                    sortDir = true;
                }else{
                    sortDir = false;
                }
            }
            if (jsonObject.get("name").equals("sEcho"))
                sEcho = jsonObject.get("value").toString();
            if (jsonObject.get("name").equals("iDisplayStart"))
                iDisplayStart = jsonObject.getIntValue("value");
            if (jsonObject.get("name").equals("iDisplayLength"))
                iDisplayLength = jsonObject.getIntValue("value");
            if(jsonObject.get("name").equals("iSortCol_0"))
                column = cols[(int) jsonObject.get("value")];
            if(jsonObject.get("name").equals("sSearch"))
                keyWord = jsonObject.getString("value");
        }
        try {
            List<Clerk> clerks = clerkService.getClerksPage(iDisplayStart,iDisplayLength,sortDir,column,keyWord);
            int count = clerkService.getClerksCount();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sEcho",sEcho);
            jsonObject.put("iTotalRecords",count);
            jsonObject.put("iTotalDisplayRecords", count);
            jsonObject.put("data",clerks);
            return new ResponseEntity(jsonObject, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @Authorization
    @RequestMapping(value = "clerk",method = RequestMethod.POST)
    public ResponseEntity addClerk(@RequestBody Map<String,String> map){
        String password = map.get("password");
        try {
            Clerk clerk = new Clerk();
            clerk.setPassword(password);
            clerk.setBirthday(new java.sql.Date(new Date().getTime()));
            clerk.setSex("未知");
            clerk.setClerkName("new clerk");
            clerk.setHeadImg("../views/images/head.png");
            clerk.setSaleNum(0);
            clerk.setNote("hello!");
            Integer id = clerkService.addClerk(clerk);
            Map<String,String> result = new HashMap<>();
            result.put("msg",id+"");
            return new ResponseEntity(result,HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @Authorization
    @RequestMapping(value = "clerk/{id}",method = RequestMethod.GET)
    public ResponseEntity getClerk(@PathVariable(value = "id") Integer id){
        try {
            Clerk clerk = clerkService.getClerk(id);
            return new ResponseEntity(clerk,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @Authorization
    @RequestMapping(value = "clerk/{id}",method = RequestMethod.DELETE)
    public ResponseEntity deleteClerk(@PathVariable(value = "id") Integer id){
        try {
            clerkService.deleteClerk(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
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
}
