package com.manager.shopmanager.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @RequestMapping("/")
    public @ResponseBody String greeting() {
        return "Hello, World";
    }
}
