package com.lai.springmvc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        executeDispatch(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void executeDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestURI = req.getRequestURI();
        Handler handler = handlerMap.get(requestURI);
        if (handler == null) {
            resp.getWriter().write("<h1>404 NOT FOUND</h1>");
        }else {
            Object object = handler.getHandler();
            try {
                handler.getMethod().invoke(object,req,resp);
            } catch (Exception e) {
                throw new RuntimeException("方法无法处理");
            }
        }
    }
}
