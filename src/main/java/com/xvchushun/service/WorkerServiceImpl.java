package com.xvchushun.service;

import com.xvchushun.dao.WorkerMapper;
import com.xvchushun.domain.Repair;
import com.xvchushun.domain.Worker;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WorkerServiceImpl implements WorkerService{
    @Autowired
    private WorkerMapper workerMapper;
    @Override
    public Worker findWorkerById(int id) {
        return workerMapper.findWorkerById(id);
    }

    @Override
    public int getIdByAccount(String account) {
        return workerMapper.getIdByAccount(account);
    }

    @Override
    public List<Repair> getRepairsByWorkerId(int id) {
        return workerMapper.getRepairsByWorkerId(id);
    }

    @Override
    public int finishRepair(int id) {
        return workerMapper.finishRepair(id);
    }
}
