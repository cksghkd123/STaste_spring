package com.hwang.staste.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreController {

    @GetMapping("/store")
    private String getStores(){

    }

}
