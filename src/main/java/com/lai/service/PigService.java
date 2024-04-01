package com.lai.service;

import com.lai.dao.PigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: zurichscud
 * @Date: 2024/4/1 10:58
 * @Description: TODO
 */
@Service
public class PigService {
    @Resource
    private PigDao pigDao;

}
