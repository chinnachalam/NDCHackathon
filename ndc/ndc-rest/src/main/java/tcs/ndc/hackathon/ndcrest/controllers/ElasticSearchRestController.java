package tcs.ndc.hackathon.ndcrest.controllers;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tcs.ndc.hackathon.ndcrest.elasticsearch.*;

import java.util.Date;
import java.util.UUID;

@Controller
public class ElasticSearchRestController {

    @Autowired LogDetailsRepository logDetailsRepository;

    @RequestMapping(value = "/sendlogs", method = RequestMethod.POST)
    @ResponseBody
    public String sendLogInfo(@RequestBody Object logInfo) {
        try {
            LogDetails logDetails = new LogDetails(logInfo);
            logDetailsRepository.save(logDetails);

            return "success";
        }
        catch (Exception e) {
            //log here
            return "error";
        }
    }
}
