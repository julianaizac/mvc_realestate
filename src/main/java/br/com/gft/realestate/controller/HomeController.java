package br.com.gft.realestate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    
    @GetMapping({"/", "/home"})
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView("Home");
        return mv;
    }

}
