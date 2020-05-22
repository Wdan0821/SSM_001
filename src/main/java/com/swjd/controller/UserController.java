package com.swjd.controller;

import com.swjd.bean.User;
import com.swjd.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserServiceImpl userServiceImpl;

    //去登陆
    @RequestMapping("/toLogin")
    public String toLogin(Model model){
        User user=new User();
        model.addAttribute("user",user);
        return "login";
    }

    //做登录
    @RequestMapping("/doLogin")
    public String doLogin(User user, Model model, HttpSession session){
        User u=userServiceImpl.login(user);
        if (u!=null){
            //账号密码没问题
            if (u.getFlag().equals("1")){
                //登录成功把用户名存到session
                session.setAttribute("activeName",u.getUname());
                return "redirect:/customerController/toMain";
            }else {
                //账号被禁用了
                model.addAttribute("errorMsg","该账号被禁用,请联系管理员");
                model.addAttribute("user",user);
                return "login";
            }
        }else {
            //账号密码有问题
            model.addAttribute("errorMsg","账号或者密码错误");
            model.addAttribute("user",new User());
            return "login";
        }
    }

    //去main.jsp
    @RequestMapping("/toMain")
    public String toMain(){
        return "redirect:/customerController/toMain";
    }

    //退出账号
    @RequestMapping("/logOut")
    public String logOut(HttpSession session){
        session.invalidate();//让session过期
        return "redirect:/toLogin";
    }

    //去我的淘宝
    @RequestMapping("/toTaoBao")
    public String toTaoBao(){
        return "taobao";
    }

    //去购物车
    @RequestMapping("toCar")
    public String toCar(){
        return "shoppingcar";
    }

}
