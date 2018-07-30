package com.iotek.humanresources.controller;

import com.iotek.humanresources.service.PositionService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by grzha on 2018/7/30.
 */
@Controller
public class PositionController {
    @Resource
    private PositionService positionService;

}
