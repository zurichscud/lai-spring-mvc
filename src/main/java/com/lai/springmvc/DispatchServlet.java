package com.lai.springmvc;

import com.lai.springmvc.annotation.RequestParam;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * @Author: zurichscud
 * @Date: 2024/4/1 1:03
 * @Description: TODO
 */
public class DispatchServlet extends HttpServlet {

    private Map<String, Handler> handlerMap;

    @Override
    public void init() {
        ServletConfig servletConfig = getServletConfig();
        String location = servletConfig.getInitParameter("contextConfigLocation");
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(location);
        HandlerMapping handlerMapping = new HandlerMapping(applicationContext,servletConfig.getServletContext());
        handlerMap = handlerMapping.getMap();
        System.out.println(handlerMap);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        executeDispatch(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }

    /**
     * @Description: 请求分发
     * @Param:
     * @Return:
     **/
    private void executeDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestURI = req.getRequestURI();
        Handler handler = handlerMap.get(requestURI);
        if (handler == null) {
            resp.getWriter().write("<h1>404 NOT FOUND</h1>");
        }else {
            Object object = handler.getHandler();
            try {
                Method method = handler.getMethod();
                //处理方法的参数，适应不同形参列的方法
                Class<?>[] parameterTypes = method.getParameterTypes();
                Object[] args=new Object[parameterTypes.length];
                //检查形参列是否存在Request，Response
                for (int i = 0; i < parameterTypes.length; i++) {
                    if (HttpServletRequest.class.isAssignableFrom(parameterTypes[i])){
                        args[i]=req;
                    }
                    if (HttpServletResponse.class.isAssignableFrom(parameterTypes[i])){
                        args[i]=resp;
                    }
                }
                //请求参数映射，将args填写完整
                userParamMapping(req,method,args);
                //调用对应的handler中的method
                method.invoke(object,args);
            } catch (Exception e) {
                throw new RuntimeException("方法执行错误");
            }
        }
    }

    /**
     * @Description: 用户自定义形参与请求参数的映射处理
     * @Param: 
     * @Return: 
     **/
    private void userParamMapping(HttpServletRequest req, Method method, Object[] args) {
        Parameter[] parameters = method.getParameters();
        Map<String, String[]> reqMap = req.getParameterMap();
        for (int i = 0; i < parameters.length; i++) {
            //参数是req，res的处理

            //存在注解时的处理
            if (parameters[i].isAnnotationPresent(RequestParam.class)) {
                String key = parameters[i].getAnnotation(RequestParam.class).value();
                String[] strings = reqMap.get(key);
                if (strings != null) {
                    args[i] = strings[0];
                } else {
                    throw new NullPointerException("指定参数不存在");
                }
            } else {
                //无注解默认处理，形参名和请求参数相同则直接赋值
                for (String key : reqMap.keySet()) {
                    if (key.equals(parameters[i].getName())) {
                        args[i] = reqMap.get(key)[0];
                    }
                }
            }
        }
    }
}
