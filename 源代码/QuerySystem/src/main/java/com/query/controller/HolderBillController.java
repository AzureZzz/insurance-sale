package com.query.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.query.entity.Clerk;
import com.query.entity.HolderBill;
import com.query.entity.Insured;
import com.query.service.IClerkService;
import com.query.service.IHolderBillService;
import com.query.utils.annotation.Authorization;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@Controller
public class HolderBillController {
	
	@Autowired
    private IHolderBillService holderBillService;

	@Autowired
    private IClerkService clerkService;

    @Authorization
    @RequestMapping(value = "download/{mode}",method = RequestMethod.GET)
    public void downloadExl(@PathVariable(value = "mode") String mode,
                            HttpServletResponse response){
        // 创建工作表
        WritableWorkbook book=null;
        response.reset();
        // 创建工作流
        OutputStream os =null;
        try {
            // 设置弹出对话框
            response.setContentType("application/DOWNLOAD");
            // 设置工作表的标题
            response.setHeader("Content-Disposition", "attachment; filename=download.xls");//设置生成的文件名字
            os  = response.getOutputStream();
            // 初始化工作表
            book = Workbook.createWorkbook(os);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try{
            if(mode.equals("1")){
                List<HolderBill> holderBills = holderBillService.getAllBills();
                WritableSheet sheet = book.createSheet("保险单表", 0);
                // 表字段名
                sheet.addCell(new jxl.write.Label(0, 0, "投保人姓名"));
                sheet.addCell(new jxl.write.Label(1, 0, "性别"));
                sheet.addCell(new jxl.write.Label(2, 0, "出生日期"));
                sheet.addCell(new jxl.write.Label(3, 0, "手机号"));
                sheet.addCell(new jxl.write.Label(4, 0, "险种"));
                sheet.addCell(new jxl.write.Label(5, 0, "保费"));
                sheet.addCell(new jxl.write.Label(6, 0, "被保险人一姓名"));
                sheet.addCell(new jxl.write.Label(7, 0, "被保险人一关系"));
                sheet.addCell(new jxl.write.Label(8, 0, "被保险人二姓名"));
                sheet.addCell(new jxl.write.Label(9, 0, "被保险人二关系"));
                sheet.addCell(new jxl.write.Label(10, 0, "出单时间"));
                sheet.addCell(new jxl.write.Label(11, 0, "保单号"));
                sheet.addCell(new jxl.write.Label(12, 0, "业务员工号"));
                // 添加数据
                for(int i=0;i<holderBills.size();i++){
                    sheet.addCell(new jxl.write.Label(0, i+1, holderBills.get(i).getHolderName()));
                    sheet.addCell(new jxl.write.Label(1, i+1, holderBills.get(i).getSex()));
                    sheet.addCell(new jxl.write.Label(2, i+1, holderBills.get(i).getBirthDate().toString()));
                    sheet.addCell(new jxl.write.Label(3, i+1, holderBills.get(i).getMobile()));
                    sheet.addCell(new jxl.write.Label(4, i+1, holderBills.get(i).getPolName()));
                    sheet.addCell(new jxl.write.Label(5, i+1, holderBills.get(i).getMoney()+""));
                    Set<Insured> insureds = holderBills.get(i).getInsureds();
                    if(!insureds.isEmpty()){
                        Iterator<Insured> it = insureds.iterator();
                        while (it.hasNext()){
                            Insured insured = it.next();
                            if(insured.getLevel()==1){
                                sheet.addCell(new jxl.write.Label(6, i+1, insured.getInsuredName()));
                                sheet.addCell(new jxl.write.Label(7, i+1, insured.getRel()));
                            }else{
                                sheet.addCell(new jxl.write.Label(8, i+1, insured.getInsuredName()));
                                sheet.addCell(new jxl.write.Label(9, i+1, insured.getRel()));
                            }
                        }
                    }
                    sheet.addCell(new jxl.write.Label(10, i+1, holderBills.get(i).getInforceTime().toString()));
                    sheet.addCell(new jxl.write.Label(11, i+1, holderBills.get(i).getBaodanNo()));
                    sheet.addCell(new jxl.write.Label(12, i+1, holderBills.get(i).getClerkID()+""));
                }
            }else if(mode.equals("2")){
                List<Clerk> clerks = clerkService.getAllClerk();
                WritableSheet sheet = book.createSheet("业务员表", 0);
                // 表字段名
                sheet.addCell(new jxl.write.Label(0, 0, "业务员ID"));
                sheet.addCell(new jxl.write.Label(1, 0, "昵称"));
                sheet.addCell(new jxl.write.Label(2, 0, "性别"));
                sheet.addCell(new jxl.write.Label(3, 0, "手机号"));
                sheet.addCell(new jxl.write.Label(4, 0, "生日"));
                sheet.addCell(new jxl.write.Label(5, 0, "个人说明"));
                sheet.addCell(new jxl.write.Label(6, 0, "销售量"));
                // 添加数据
                for(int i=0;i<clerks.size();i++){
                    sheet.addCell(new jxl.write.Label(0, i+1, clerks.get(i).getClerkId()+""));
                    sheet.addCell(new jxl.write.Label(1, i+1, clerks.get(i).getClerkName()));
                    sheet.addCell(new jxl.write.Label(2, i+1, clerks.get(i).getSex()));
                    sheet.addCell(new jxl.write.Label(3, i+1, clerks.get(i).getMobile()));
                    sheet.addCell(new jxl.write.Label(4, i+1, clerks.get(i).getBirthday().toString()));
                    sheet.addCell(new jxl.write.Label(5, i+1, clerks.get(i).getNote()));
                    sheet.addCell(new jxl.write.Label(6, i+1, clerks.get(i).getSaleNum()+""));
                }
            }
            book.write();
            book.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Authorization
    @RequestMapping(value = "bill/{id}",method = RequestMethod.DELETE)
    public ResponseEntity deleteBill(@PathVariable(value = "id") Integer id){
        try {
            holderBillService.deleteHolderBill(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @Authorization
    @RequestMapping(value = "bill/{id}",method = RequestMethod.GET)
    public ResponseEntity getBill(@PathVariable(value = "id") Integer id){
        try {
            HolderBill bill = holderBillService.getHolderBill(id);
            return new ResponseEntity(bill, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @Authorization
    @RequestMapping(value = "bill",method = RequestMethod.PUT)
    public ResponseEntity updateClerk(HttpServletRequest request,@RequestBody HolderBill holderBill){
        try {
            request.setCharacterEncoding("utf-8");
            holderBillService.updateHolderBill(holderBill);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * 批量删除保单
     * @param ids 包含保单id集合的JSON
     * @return
     */
    @Authorization
    @RequestMapping(value = "bills",method = RequestMethod.DELETE)
    public ResponseEntity deleteBills(@RequestBody Set<Integer> ids){
        try {
            holderBillService.deleteHolderBills(ids);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @Authorization
    @RequestMapping(value = "bills",method = RequestMethod.GET)
    public ResponseEntity queryBillsPage(@RequestParam String aoData){
        String[] cols = {"INFORCE_TIME","BAODAN_NO","POL_NAME","HOLDER_NAME","CLERK_ID"};
        JSONArray jsonArray = JSONArray.parseArray(aoData);
        String sEcho = null;
        int iDisplayStart = 0;
        int iDisplayLength = 0;
        String column = null;
        boolean sortDir = true;
        String keyWord = null;
        for (int i = 0; i <jsonArray.size() ; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject.get("name").equals("sEcho"))
                sEcho = jsonObject.get("value").toString();
            if (jsonObject.get("name").equals("iDisplayStart"))
                iDisplayStart = jsonObject.getIntValue("value");
            if (jsonObject.get("name").equals("iDisplayLength"))
                iDisplayLength = jsonObject.getIntValue("value");
            if(jsonObject.get("name").equals("iSortCol_0"))
                column = cols[(int) jsonObject.get("value")];
            if(jsonObject.get("name").equals("sSortDir_0")){
                if(jsonObject.get("value").equals("desc")){
                    sortDir = true;
                }else{
                    sortDir = false;
                }
            }
            if(jsonObject.get("name").equals("sSearch"))
                keyWord = jsonObject.getString("value");
        }
        try {
            List<HolderBill> bills = holderBillService.getBillsPage(iDisplayStart,iDisplayLength,sortDir,column,keyWord);
            int count = holderBillService.getBillsCount();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sEcho",sEcho);
            jsonObject.put("iTotalRecords",count);
            jsonObject.put("iTotalDisplayRecords", count);
            jsonObject.put("data",bills);
            return new ResponseEntity(jsonObject,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
