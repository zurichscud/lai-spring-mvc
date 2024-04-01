package com.lai.springmvc;

import com.lai.springmvc.annotation.RequestMapping;
import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletContext;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author: zurichscud
 * @Date: 2024/4/1 9:50
 * @Description: TODO
 */

@Getter
public class HandlerMapping {
    private final Map<String,Handler> map=new HashMap<>();
    private final String path;

    public HandlerMapping(ApplicationContext applicationContext, ServletContext servletContext) {
        String[] beans = applicationContext.getBeanDefinitionNames();
        path = servletContext.getContextPath();
        for (String bean : beans) {
            Object object = applicationContext.getBean(bean);
            if (object.getClass().isAnnotationPresent(Controller.class)) {
                initMap(object);
            }
        }

    }

    private void initMap(Object object) {
        Method[] methods = object.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(RequestMapping.class)){
                System.out.println(method);
                Handler handler = new Handler(object, method);
                String url = path+getMethodURL(method);
                map.put(url,handler);
            }
        }
    }

    private String getMethodURL(Method method) {
        return method.getAnnotation(RequestMapping.class).value();
    }

}
