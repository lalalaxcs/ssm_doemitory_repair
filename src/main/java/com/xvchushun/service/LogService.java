package com.xvchushun.service;

import com.github.pagehelper.PageInfo;
import com.xvchushun.domain.MyLog;

import java.util.List;

public interface LogService {
    public int AddLog(MyLog log);
    public List<MyLog> selectLog();
}
