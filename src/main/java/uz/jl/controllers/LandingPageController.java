package uz.jl.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Controller
 */

@Controller
public class LandingPageController {

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    private String landingPage() {
        return "home";
    }

}
