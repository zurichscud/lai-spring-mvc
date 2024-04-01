package com.lai.springmvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: zurichscud
 * @Date: 2024/4/1 16:56
 * @Description: TODO
 */
public abstract class AbstractView {
    protected   String viewName;
    protected final String url;

    protected AbstractView(String url) {

        this.url = url;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public abstract void renderMergedOutputModel(HttpServletRequest req, HttpServletResponse resp);
}
