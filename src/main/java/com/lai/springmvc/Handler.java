package com.lai.springmvc;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * @Author: zurichscud
 * @Date: 2024/4/1 9:54
 * @Description: TODO
 */
@Data
public class Handler {
    private Object handler;
    private Method method;

    public Handler(Object handler, Method method) {
        this.handler = handler;
        this.method = method;
    }
}
