package com.iotek.humanresources.controller;

import com.iotek.humanresources.service.EmpToTrService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by grzha on 2018/8/2.
 */
@Controller
public class EmpToTrController {
    @Resource
    private EmpToTrService empToTrService;
}
