package com.swjd.controller;

import com.swjd.bean.Customer;
import com.swjd.service.CustomerServiceImpl;
import com.swjd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customerController")
public class CustomerController {

    @Autowired
    CustomerServiceImpl customerServiceImpl;
    @Autowired
    UserService userService;

    @RequestMapping("/toMain")
    public String toMain(Model model){
        List<Customer> list=new ArrayList<>();
        list=customerServiceImpl.getList();
        Customer customer=new Customer();
        model.addAttribute("customer",customer);
        model.addAttribute("list",list);
        return "main";
    }

    @RequestMapping("/findForSearch")
    public String findForSearch(@RequestParam Map<String,String> param, Model model, ModelMap modelMap){
        //第三次(查按条件查出来的数据的第2页)
        //拿第二次输入条件
        String customerName=param.get("customerName");//客户姓名的条件
        String customerId=param.get("customerId");//客户编号
        if (customerId==null||customerId.equals("")){
            customerId="0";
        }
        String customerSourse=param.get("customerSourse");//客户信息来源
        String customerDateBegin=param.get("customerDateBegin");
        String customerDateEnd=param.get("customerDateEnd");

        model.addAttribute("customerDateBegin",customerDateBegin);
        model.addAttribute("customerDateEnd",customerDateEnd);

        //第二次进(param就已经有内容了,条件查第一页)

        //第一次进

        //每页显示的数据条数
        String rows="5";
        param.put("rows",rows);
        List<Customer> list=customerServiceImpl.findForSearch(param);
        //总条数
        int totalRows=customerServiceImpl.findForCount(param);
        //当前页数
        String pageNum=param.get("pageNum");
        if (param.get("pageNum")==null){
            pageNum= "1";
        }
        //总页数
        int totalPage=0;
        if (totalRows% Integer.parseInt(rows)==0){
            totalPage=totalRows/Integer.parseInt(rows);
        }else {
            totalPage=totalRows/Integer.parseInt(rows)+1;
        }
        modelMap.put("list",list);
        modelMap.put("totalRows",totalRows);
        modelMap.put("pageNum",pageNum);
        modelMap.put("totalPage",totalPage);
        System.out.println(totalRows);
        System.out.println(pageNum);
        System.out.println(totalPage);
        Customer customer=new Customer();
        customer.setCustomerName(customerName);
        customer.setCustomerId(Integer.parseInt(customerId));
        customer.setCustomerSourse(customerSourse);

        model.addAttribute("customer",customer);
        return "main";
    }

    //去新增
    @RequestMapping("/toAdd")
    public String toAdd(Model model){
        Customer customer=new Customer();
        model.addAttribute("customer",customer);
        return "add";
    }

    //做新增
    @RequestMapping("/doAdd")
    public String doAdd(Customer customer, HttpSession session){
        //因为用户添加的时候没有添加日期,所以我们获取系统的时间添加进去
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String dateStr=format.format(date);
        customer.setCustomerDate(dateStr);
        //获取session里面存的登录名
        String uName=session.getAttribute("activeName").toString();
        int uId=userService.findUserId(uName);
        customer.setCustomerCreateId(uId);
        customer.setCustomerUserId(uId);
        //调用添加的方法
        int result=customerServiceImpl.insertCustomer(customer);
        if (result>0){
            return "redirect:/customerController/findForSearch";
        }else {
            return "add";
        }
    }

    //批量删除
    @RequestMapping("/doDelete")
    public String doDelete(@RequestParam String[] selectCustomerId){
//        int j=customerServiceImpl.
        int jg=customerServiceImpl.delete(selectCustomerId);
        if (jg>0){
            return "redirect:/customerController/findForSearch";
        }else {
            return "redirect:/customerController/findForSearch";
        }
    }

    //准备修改
    @RequestMapping("/toUpdate")
    public String toUpdate(@RequestParam("selectCustomerId") int selectCustomerId,Model model){
        Customer customer=customerServiceImpl.selectOne(selectCustomerId);
        model.addAttribute("customer",customer);
        return "update";
    }


}
