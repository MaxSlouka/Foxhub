package com.gfa.foxbook.foxbook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RoutingController {

    @RequestMapping("/**/{path:[^\\.]*}")
    public String forward() {
        return "forward:/";
    }
}
