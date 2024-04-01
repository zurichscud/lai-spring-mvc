package com.lai.controller;

import com.lai.bean.Pig;
import com.lai.springmvc.annotation.RequestBody;
import com.lai.springmvc.annotation.RequestMapping;
import com.lai.springmvc.annotation.RequestParam;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: zurichscud
 * @Date: 2024/4/1 1:25
 * @Description: TODO
 */
//@RequestMapping
@Controller
public class PigController {

    @RequestMapping("/pig/select")
    public String selectPig(HttpServletRequest req, HttpServletResponse resp,@RequestParam("name") String name){

        System.out.println(name);
        System.out.println("PIG GET");
        return "success.jsp";
    }
    @RequestMapping("/pig/add")
    public String addPig(HttpServletRequest req, HttpServletResponse resp,String name){
        System.out.println(name);
        System.out.println("PIG ADD");
        return"redirect:/success.jsp";
    }
    @RequestMapping("/pig/test3")
    public String test3(HttpServletRequest req, HttpServletResponse resp,String name){
        System.out.println(name);
        return"forward:/success.jsp";
    }
    @RequestMapping("/pig/test4")
    public String test4(HttpServletRequest req, HttpServletResponse resp,String name){
        System.out.println(name);
        return"/success.jsp";
    }
    @RequestMapping("/pig/test5")
    public String test5(HttpServletRequest req, HttpServletResponse resp,String name){
        System.out.println(name);
        return"/success5.jsp";
    }
    @RequestBody
    @RequestMapping("/pig/test6")
    public Object test6(HttpServletRequest req, HttpServletResponse resp,String name){
        System.out.println(name);
        Pig pig = new Pig();
        pig.setName(name);
        pig.setAge(12);
        return pig;
    }
}
