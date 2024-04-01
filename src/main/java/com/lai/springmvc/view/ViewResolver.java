package com.lai.springmvc.view;

import org.springframework.stereotype.Component;

/**
 * @Author: zurichscud
 * @Date: 2024/4/1 16:52
 * @Description: 类似于Spring的InternalResourceViewResolver
 */
@Component
public class ViewResolver {
    private String path;


    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @Description: 根据不同的viewName得到不同的view对象
     * @Param:
     * @Return:
     **/
    public AbstractView resolveViewName(String viewName){
        if (viewName.contains(":")){
            String innerUrl = viewName.split(":")[1];
            if (viewName.startsWith("redirect")){
                //重定向URL处理
                String url=path+innerUrl;
                RedirectView view = new RedirectView(url);
                view.setViewName("redirect:");
                return view;
            } else if (viewName.startsWith("forward")) {
                InternalResourceView view = new InternalResourceView(innerUrl);
                view.setViewName("forward:");
                return view;
            }
        }
        else if (!viewName.startsWith("/")){
            return new InternalResourceView("/"+viewName);
        }
        else {
            return new InternalResourceView(viewName);
        }
        return null;
    }
}
