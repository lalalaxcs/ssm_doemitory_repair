package com.xvchushun.dao;

import com.github.pagehelper.PageInfo;
import com.xvchushun.domain.MyLog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LogMapper {
    @Insert("insert into `log`(log_ip,log_time,log_moduleName,log_desc,log_type,log_methodName)values(#{logIp},#{logTime},#{logModuleName},#{logDesc},#{logType},#{logMethod})")
    public int AddLog(MyLog log);
    @Select("select * from `log`")
    @Results(value = {
            @Result(property = "logId",column = "log_id" ,id = true),
            @Result(property = "logIp",column = "log_ip"),
            @Result(property = "logTime",column = "log_time"),
            @Result(property = "logModuleName",column = "log_moduleName"),
            @Result(property = "logDesc",column = "log_desc"),
            @Result(property = "logType",column = "log_type"),
            @Result(property = "logMethod",column = "log_methodName")
    })
    public List<MyLog> selectLog();
}
