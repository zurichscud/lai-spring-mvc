package com.lai.controller;

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
    public void selectPig(HttpServletRequest req, HttpServletResponse resp,@RequestParam("username") String name){

        System.out.println(name);
        System.out.println("PIG GET");
    }
    @RequestMapping("/pig/add")
    public void addPig(HttpServletRequest req, HttpServletResponse resp,String name){
        System.out.println(name);
        System.out.println("PIG ADD");

    }
}
