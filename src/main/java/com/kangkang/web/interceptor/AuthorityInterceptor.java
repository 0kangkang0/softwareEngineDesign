package com.kangkang.web.interceptor;

import com.google.gson.Gson;
import com.kangkang.pojo.LoginRequired;
import com.kangkang.pojo.ManagerRequired;
import com.kangkang.pojo.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

@Component
public class AuthorityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json");
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // ①:START 方法注解级拦截器
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 判断接口是否需要登录
        LoginRequired loginRequireAnnotation = method.getAnnotation(LoginRequired.class);
        ManagerRequired managerRequireAnnotation = method.getAnnotation(ManagerRequired.class);
        // 有 @LoginRequired 注解，需要认证
        if (loginRequireAnnotation != null) {
            // 这写你拦截需要干的事儿，比如取缓存，SESSION，权限判断等
            HttpSession session = request.getSession();
            String userid = (String)session.getAttribute("userid");
            if(userid!=null){
                return true;
            }
            Gson gson = new Gson();
            response.getWriter().write(gson.toJson(new Result(304, "登录数据过期，请重新登录","/login.html")));
            return false;
        }
        if(managerRequireAnnotation!=null){
            HttpSession session = request.getSession();
            String userid = (String)session.getAttribute("manager");
            if(userid!=null){
                return true;
            }
            Gson gson = new Gson();
            response.getWriter().write(gson.toJson(new Result(304, "登录数据过期，请重新登录","/managerLogin.html")));
            return false;
        }
        return true;
    }
}
