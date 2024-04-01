package com.lai.springmvc.view;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: zurichscud
 * @Date: 2024/4/1 16:59
 * @Description: 重定向视图，基于重定向实现
 */
public class RedirectView extends AbstractView{
    public RedirectView(String url) {
        super(url);
    }

    @Override
    public void renderMergedOutputModel(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.sendRedirect(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
