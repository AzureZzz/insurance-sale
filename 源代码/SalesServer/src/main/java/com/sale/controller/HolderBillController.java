package com.sale.controller;

import com.alibaba.fastjson.JSONObject;
import com.sale.entity.Clerk;
import com.sale.entity.HolderBill;
import com.sale.utils.annotation.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.sale.service.IHolderBillService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class HolderBillController {
	
	@Autowired
    private IHolderBillService holderBillService;

    /**
     * 添加保单
     * @param holderBill 包含保单信息的JSON
     * @return
     */
	@Authorization
    @RequestMapping(value = "bill",method = RequestMethod.POST)
    public ResponseEntity saveBill(@RequestBody HolderBill holderBill, HttpServletRequest request){
        Clerk clerk = new Clerk();
        clerk.setClerkId(request.getIntHeader("Clerk-Id"));
        holderBill.setClerk(clerk);
        holderBill.setBaodanNo("danhao"+ System.currentTimeMillis());
        holderBill.setInforceTime(new Date());
        System.out.println(holderBill.getClerk() == null);
        JSONObject jsonObject = new JSONObject();
        try {
            Integer id = holderBillService.saveHolderBill(holderBill);
            jsonObject.put("id",id);
            return new ResponseEntity(jsonObject,HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
