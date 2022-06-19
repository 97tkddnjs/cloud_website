package com.marine.website;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    String index(){
        return "index";
    }

    @RequestMapping("/show")
    String index2(){
        return "show";
    }


}
