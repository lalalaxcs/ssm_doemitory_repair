package com.xvchushun.service;

import com.sun.org.apache.bcel.internal.generic.ARETURN;
import com.xvchushun.dao.StudentMapper;
import com.xvchushun.domain.Repair;
import com.xvchushun.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public Student findStudentById(int id) {
        Student  student = studentMapper.findStudentById(id);
        return student;
    }

    @Override
    public int findStudentId(String name) {
        return studentMapper.findStudentId(name);
    }

    @Override
    public List<Repair> findRepairByStudentId(int id) {
        return studentMapper.findRepairByStudentId(id);
    }

    @Override
    public int findStudentIdByAc(String account) {
        return studentMapper.findStudentIdByAc(account);
    }

    @Override
    public int addRepair(Repair repair) {
        return studentMapper.addRepair(repair);
    }
}
