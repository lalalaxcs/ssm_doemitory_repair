package com.xvchushun.controller;

import com.mysql.cj.xdevapi.JsonString;
import com.xvchushun.domain.User;
import com.xvchushun.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;
    @PostMapping("/login")
    public String loginController(@RequestParam(value = "accout")String account, @RequestParam(value = "password")String password, Model model){
        System.out.println(account);
        System.out.println(password);
        User user = loginService.userLogin(account);
        System.out.println(user);
        if(user==null || !user.getPassword().equals(password)){
            return "redirect:login.jsp";
        }else if(user.getType()==0){
            model.addAttribute("user",user);
            return "admin";
        }else if(user.getType() == 3){
            model.addAttribute("user",user);
            return "user";
        }else if(user.getType() ==1){
            model.addAttribute("user",user);
            return "worker";
        }else {
            return "suguan";
        }
//        model.addAttribute("user", user);
//        modelAndView.addObject("user",user);
//        modelAndView.setViewName("admin");
//        return "redirect:/pages/admin.jsp";
//        return "admin";
    }
    @PostMapping("/hello")
    public void helloController(HttpServletResponse response){
        try {
            response.getWriter().write("hello");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
