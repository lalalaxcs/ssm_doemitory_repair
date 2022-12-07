package com.xvchushun.service;

import com.xvchushun.domain.Repair;
import com.xvchushun.domain.Student;

import java.util.List;

public interface StudentService {
    public Student findStudentById(int id);
    public int findStudentId(String name);
    public List<Repair> findRepairByStudentId(int id);
    public int findStudentIdByAc(String account);
    public int addRepair(Repair repair);
}
