package com.ylsoft.ssm.controller;

import com.ylsoft.ssm.domain.SysLog;
import com.ylsoft.ssm.service.ServiceSysLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;


// 定义该类是个切面类，
@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ServiceSysLog serviceSysLog;

    // 访问时间
    private Date StartTime;
    // 访问的类
    private Class Clazz;
    // 访问的方法
    private Method method;

    //    指定切入点表达式作用范围
    @Before("execution(* com.ylsoft.ssm.controller.*.*(..))")
    public void BfMethod(JoinPoint jp) throws NoSuchMethodException {
//        获取时间
        StartTime = new Date();
//        获取类
        Clazz = jp.getTarget().getClass();
//        获取访问的方法名称
        String MethodName = jp.getSignature().getName();
//        获取参数
        Object[] args = jp.getArgs();
        if (args == null || args.length == 0) {
//            如果参数没值获取方法
            method = Clazz.getMethod(MethodName);
        } else {
//            如果参数有值获取方法
//            List<Class> classArgs = new ArrayList<>();
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method = Clazz.getMethod(MethodName, classArgs);
        }
    }

    @After("execution(* com.ylsoft.ssm.controller.*.*(..))")
    public void AfMethod() {
        // 执行方法的总耗时间
        long time = new Date().getTime() - StartTime.getTime();

//        访问的url,通过反射获取
        String url = "";

        if (Clazz != null && method != null && Clazz != LogAop.class) {
//              获取类上的RequestMapping
            RequestMapping ClassAnnotation = (RequestMapping) Clazz.getAnnotation(RequestMapping.class);
            if (ClassAnnotation != null) {
//                获取参数
                String[] ClassValue = ClassAnnotation.value();
//            获取方法上的RequestMapping
                RequestMapping MethodAnnotation = method.getAnnotation(RequestMapping.class);
                if (MethodAnnotation != null) {
                    String[] MathodValue = MethodAnnotation.value();
                    url = ClassValue[0] + MathodValue[0];
                }
            }
        }

//        获取ip
        String ip = request.getRemoteAddr();
//        获取访问的用户名,通过上下文获取user对象
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();

        SysLog sysLog = new SysLog();
        sysLog.setUsername(username);   // 访问用户
        sysLog.setIp(ip);               // 访问IP
        sysLog.setExecutionTime(time);  // 执行的毫秒
        sysLog.setUrl(url);             // 访问url
        sysLog.setMethod("[类名]" + Clazz.getName() + " " + "[方法名]" + method.getName()); // 访问的类名和方法名
        sysLog.setVisitTime(StartTime);  // 访问时间

        serviceSysLog.save(sysLog);
    }
}

