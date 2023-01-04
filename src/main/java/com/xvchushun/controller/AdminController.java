package com.xvchushun.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xvchushun.domain.LogAopPointcut;
import com.xvchushun.domain.MyLog;
import com.xvchushun.domain.User;
import com.xvchushun.service.AdminService;
import com.xvchushun.service.AdminServiceImpl;
import com.xvchushun.service.LogService;
import jdk.internal.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private LogService logService;
    @PostMapping("/admin/selectAllUser")
    @ResponseBody
    @LogAopPointcut(logModulename = "管理员模块" , logDesc = "查询用户",logType = LogAopPointcut.log_Type.SELECT)
    public List<User> selectAllUser(){
        return adminService.selectAllUser();
    }
    @PostMapping("/admin/importUser")
    @LogAopPointcut(logModulename = "管理员模块" , logDesc = "批量导入",logType = LogAopPointcut.log_Type.IMPORT)
    public String importUser(Model model, @RequestParam("excelfiles") MultipartFile file , HttpServletRequest request, HttpServletResponse response) throws Exception{
        adminService.UploadExcel(request,response);
        User user = new User();
        user.setAccount("admin");
        model.addAttribute("user",user);
        return "admin";
    }
    @PostMapping("/admin/deleteUser")
    @ResponseBody
    @LogAopPointcut(logModulename = "管理员模块" , logDesc = "删除用户",logType = LogAopPointcut.log_Type.DELETE)
    public int deleteUser(@RequestParam("id") int id){
        int type = adminService.selectUserTypeByUserId(id);
        return adminService.deleteUser(id);
    }

    @PostMapping("/admin/log")
    @ResponseBody
    @LogAopPointcut(logModulename = "日志模块" , logDesc = "查询日志",logType = LogAopPointcut.log_Type.SELECT)
    public PageInfo selectLog(@RequestParam("pageNow") int pageNow){
        PageHelper.startPage(pageNow,10);
        List<MyLog> logList  = logService.selectLog();
        PageInfo pageInfo = new PageInfo(logList);
        return pageInfo;
    }
}
