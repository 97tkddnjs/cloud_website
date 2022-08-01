package com.marine.website;

import com.marine.website.visualization.GeoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    GeoService e;
    @RequestMapping("/")
    String index(){
        return "index";
    }

    @RequestMapping("/show")
    String visual(Model model){

        return "show";
    }
    @RequestMapping("/input")
    String index2(){
        //e.adder("불정로 6");
        return "input";
    }

//    @PostMapping("restApi/address")


    @PostMapping ("/show")
    String visual(@RequestParam String adder , Model model){

        model.addAttribute("adder",adder);
        e.adder(adder);
        return "redirect:/show";
    }

}
