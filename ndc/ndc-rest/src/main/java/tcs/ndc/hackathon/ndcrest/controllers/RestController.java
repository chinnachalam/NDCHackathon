package tcs.ndc.hackathon.ndcrest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestController {

    @RequestMapping(value = "/offers", method = RequestMethod.GET)
    @ResponseBody
    public String getOffers(){
        System.out.print("offers+++++++++++++++++");
        return "offers";
    }
}