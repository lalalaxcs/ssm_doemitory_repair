package com.xvchushun.service;

import com.xvchushun.domain.Repair;
import com.xvchushun.domain.Worker;

import java.util.List;

public interface WorkerService {
    public Worker findWorkerById(int id);
    public int getIdByAccount(String account);
    public List<Repair> getRepairsByWorkerId(int id);
    public int finishRepair(int id);
    public int abandonRepairOrder(int id);
    public List<Repair> findAllRepairOrdersWithoutAccept();
    public int acceptRepairOrder(int wId,int rId);
}
