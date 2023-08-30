package com.mmhtoo.note.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestController {


    @PostMapping( value = "/test")
    public String test(){
        return "Hello user!";
    }

}
