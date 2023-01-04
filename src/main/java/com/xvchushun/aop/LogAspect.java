package com.xvchushun.aop;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.xvchushun.domain.LogAopPointcut;
import com.xvchushun.domain.MyLog;
import com.xvchushun.service.LogService;
//import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.tools.ant.util.DateUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Component("logAspect")
@Aspect
public class LogAspect {
    @Autowired
    LogService logService;
    @Pointcut("execution(* com.xvchushun.controller.*.*(..))")
    public void poincut(){}
    @Before("poincut()")
    public void before(JoinPoint joinPoint){
        //使用JoinPoint接口实例作为参数获得目标对象的类名和方法名
        System.out.print("这是前置通知！");
        System.out.print("目标类：" + joinPoint.getTarget());
        System.out.println("，被织入增强处理的目标方法为："+joinPoint.getSignature().getName());
    }
    @AfterReturning("poincut()")
    public void saveLog(JoinPoint joinPoint){
        MyLog log = new MyLog();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogAopPointcut logAopPointcut = method.getAnnotation(LogAopPointcut.class);
        if (logAopPointcut != null) {
            //模块名称
            log.setLogModuleName(logAopPointcut.logModulename());
            //功能
            log.setLogDesc(logAopPointcut.logDesc());
            //操作类型
            String index = logAopPointcut.logType().getIndex();
            String name = logAopPointcut.logType().getLogname();
            log.setLogType(name);
        }
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        log.setLogTime(formatter.format(date));
        String methodName = method.getName();
        log.setLogMethod(methodName);

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        System.out.println(request.getRemoteAddr());
        log.setLogIp(request.getRemoteAddr());
        logService.AddLog(log);
    }
}
