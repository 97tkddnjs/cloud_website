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

    GeoService e = new GeoService();
    @RequestMapping("/")
    String index(){
        return "index";
    }

    @RequestMapping("/input")
    String index2(){
        //e.adder("불정로 6");
        return "input";
    }
    @RequestMapping("/show")
    String visual(Model model){
        //e.adder("불정로 6");
        return "show";
    }
    @PostMapping ("/show")
    String visual(@RequestParam String adder , Model model){

        model.addAttribute("adder",adder);
        System.out.println("adder = " + adder);
        return "redirect:/show";
    }

}
