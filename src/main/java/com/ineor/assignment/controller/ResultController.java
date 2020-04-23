package com.ineor.assignment.controller;

import com.ineor.assignment.model.Result;
import com.ineor.assignment.service.ResultService;
import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
public class ResultController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ResultService resultService;

    @RequestMapping(method = RequestMethod.GET, value = "/", produces = "application/json")
    public ResponseEntity<String> home() {
        return new ResponseEntity<>("Rok Zidarn", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/result", produces = "application/json")
    public ResponseEntity<Result> result() {
        String URL = "http://api.worldbank.org/v2/country/all/indicator/SP.POP.TOTL?date=2018&format=json";
        String response = restTemplate.getForEntity(URL, String.class).getBody();
        int numPages = JsonPath.read(response, "$.[0].pages");  // get number of pages where the data is

        String[] data = new String[numPages];
        for (int i=0; i<numPages; i++) {  // access all pages
            data[i] = restTemplate.getForEntity(URL + "&page=" + (i+1), String.class).getBody();  // get each page
        }

        Result result = resultService.processData(data);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
