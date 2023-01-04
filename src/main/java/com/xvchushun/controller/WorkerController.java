package com.xvchushun.controller;

import com.xvchushun.domain.LogAopPointcut;
import com.xvchushun.domain.Repair;
import com.xvchushun.service.WorkerService;
import org.apache.ibatis.ognl.IntHashMap;
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
    @LogAopPointcut(logModulename = "工人模块" , logDesc = "查看维修记录",logType = LogAopPointcut.log_Type.SELECT)
    public List<Repair> wokersRepairs(@RequestParam("Account") String account, HttpServletResponse response){
        int id = workerService.getIdByAccount(account);
        List<Repair> repairs = workerService.getRepairsByWorkerId(id);
        System.out.println(repairs);
        return repairs;
    }
    @PostMapping("/worker/finishRepair")
    @ResponseBody
    @LogAopPointcut(logModulename = "工人模块" , logDesc = "完成订单",logType = LogAopPointcut.log_Type.UPDATE)
    public Map<String,Integer> finishRepair(@RequestParam("repairId") int id){
        int count = workerService.finishRepair(id);
        Map<String,Integer> map = new HashMap<>();
        map.put("rows",count);
        return map;
    }
    @PostMapping("/worker/abandonRepairOrder")
    @ResponseBody
    @LogAopPointcut(logModulename = "工人模块" , logDesc = "放弃模块",logType = LogAopPointcut.log_Type.UPDATE)
    public Map<String,Integer> abandonRepairOrder(@RequestParam("repairId") int id){
        int count = workerService.abandonRepairOrder(id);
        Map<String,Integer> map = new HashMap<>();
        map.put("rows",count);
        return map;
    }

    @PostMapping("/worker/findAllRepairOrdersWithoutAccept")
    @ResponseBody
    @LogAopPointcut(logModulename = "工人模块" , logDesc = "查看未接受报修单",logType = LogAopPointcut.log_Type.SELECT)
    public List<Repair> findAllRepairOrdersWithoutAccept(){
        return workerService.findAllRepairOrdersWithoutAccept();
    }
    @PostMapping("/worker/acceptRepairOrder")
    @ResponseBody
    @LogAopPointcut(logModulename = "工人模块" , logDesc = "接单",logType = LogAopPointcut.log_Type.UPDATE)
    public Map<String,Integer> acceptRepairOrder(@RequestParam("repairId") int id,@RequestParam("Account") String account){
        int w_id = workerService.getIdByAccount(account);
        int count = workerService.acceptRepairOrder(w_id,id);
        Map<String,Integer> map = new HashMap<>();
        map.put("rows",count);
        return map;
    }
}
