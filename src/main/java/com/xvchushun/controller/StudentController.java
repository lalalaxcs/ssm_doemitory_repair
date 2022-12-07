package com.xvchushun.controller;

import com.xvchushun.domain.Repair;
import com.xvchushun.domain.Student;
import com.xvchushun.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.jws.Oneway;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping("/student/myrepair")
    @ResponseBody
    public List<Repair> StudentRepair(@RequestParam("username") String name, HttpServletResponse response){
        int id = studentService.findStudentIdByAc(name);
        List<Repair> repairs = studentService.findRepairByStudentId(id);
        System.out.println(repairs);
        return repairs;
    }
    @PostMapping("/student/addRepair/{account}")
    @ResponseBody
    public Map<String, Integer> AddRepair(@RequestBody Repair repair, @PathVariable("account") String account){
        int id = studentService.findStudentIdByAc(account);
        Student student = studentService.findStudentById(id);
        repair.setStudent(student);
        int flag = studentService.addRepair(repair);
        System.out.println(flag);
        Map<String,Integer> map =new HashMap<>();
        map.put("rows",flag);
        return map;
    }
}
