package com.itheima.utils;
import com.itheima.domain.SysLog;
import com.itheima.service.ISysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 自动记录日志的切面类。在执行控制器方法之后，自动记录日志。
 */
@Component  // 创建对象，加入容器
@Aspect     // 指定当前类为切面类
public class LogAspect {

    // 注入日志的service
    @Autowired
    private ISysLogService sysLogService;
    // Spring已经提供的了ServletRequest监听器，会监听request对象的创建，并且把创建的结果加入容器。
    // 所以，只需要在web.xml配置监听器即可。
    @Autowired
    private HttpServletRequest request;

    /**
     * 使用环绕通知，自动记录日志
     */
    // 拦截控制器的所有方法
    @Around("execution(* com.itheima.controller.*.*(..))")
    public Object insertLog(ProceedingJoinPoint pjp){
        // 获取用户名
        String username = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        // 获取当前执行方法所在的类的全名
        String className = pjp.getTarget().getClass().getName();
        // 获取当前执行的方法名
        String methodName = pjp.getSignature().getName();
        // 结果
        String result = className + "." + methodName + "(..)";

        // 封装日志对象
        SysLog log = new SysLog();
        log.setVisitTime(new Date());
        log.setUsername(username);
        log.setMethod(result);
        log.setIp(request.getRemoteAddr());

        try {
            // 执行控制器方法
            Object retV = pjp.proceed();
            // 记录日志
            sysLogService.save(log);
            return retV;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

}

















