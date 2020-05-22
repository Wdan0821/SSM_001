package com.swjd.controller;

import com.swjd.bean.Customer;
import com.swjd.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("customerController")
public class CustomerController {

    @Autowired
    CustomerServiceImpl customerServiceImpl;

    @RequestMapping("/toMain")
    public String toMain(Model model){
        List<Customer> list=new ArrayList<>();
        list=customerServiceImpl.getList();
        Customer customer=new Customer();
        model.addAttribute("customer",customer);
        model.addAttribute("list",list);
        return "main";
    }
}
