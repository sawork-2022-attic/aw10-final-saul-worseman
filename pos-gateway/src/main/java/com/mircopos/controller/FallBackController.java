package com.mircopos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallBackController {

    @GetMapping("/message")
    public String test() {
        return "Ops, the service is unavailable now \n " +
                "You may refer to our partner Amazon OR JD to purchase what u want!";
    }

}
