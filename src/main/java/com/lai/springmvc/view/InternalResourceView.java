package com.lai.springmvc.view;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: zurichscud
 * @Date: 2024/4/1 17:00
 * @Description: 解析内部资源的视图，基于请求转发实现
 */
public class InternalResourceView extends AbstractView{
    public InternalResourceView(String url) {
        super(url);
    }

    @Override
    public void renderMergedOutputModel(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(url);
        try {
            requestDispatcher.forward(req,resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }


}
