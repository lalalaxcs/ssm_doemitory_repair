package com.xvchushun.service;

import com.xvchushun.dao.LogMapper;
import com.xvchushun.domain.MyLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService{
    @Autowired
    LogMapper logMapper;
    @Override
    public int AddLog(MyLog log) {
        return logMapper.AddLog(log);
    }

    @Override
    public List<MyLog> selectLog() {
        return logMapper.selectLog();
    }
}
