package com.xvchushun.domain;

import lombok.Data;

@Data
public class MyLog {
    //主键
    private String logId = "";
    //请求的ip地址
    private String logIp = "";
    //请求时间
    private String logTime = "";
    //模块名称
    private String logModuleName = "";
    //功能
    private String logDesc = "";
    //操作类型
    private String logType = "";
    //请求方法
    private String logMethod = "";
}
