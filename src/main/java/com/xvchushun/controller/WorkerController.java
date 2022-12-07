package com.xvchushun.controller;

import com.xvchushun.domain.Repair;
import com.xvchushun.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    @PostMapping("/worker/myRepair")
    @ResponseBody
    public List<Repair> wokersRepairs(@RequestParam("Account") String account, HttpServletResponse response){
        int id = workerService.getIdByAccount(account);
        List<Repair> repairs = workerService.getRepairsByWorkerId(id);
        System.out.println(repairs);
        return repairs;
    }
    @PostMapping("/worker/finishRepair")
    @ResponseBody
    public Map<String,Integer> finishRepair(@RequestParam("repairId") int id){
        int count = workerService.finishRepair(id);
        Map<String,Integer> map = new HashMap<>();
        map.put("rows",count);
        return map;
    }
}
