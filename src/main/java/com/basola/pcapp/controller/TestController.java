
package com.basola.pcapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class TestController {
    
    @RequestMapping("/test/hello")
    public String helloWorld (){
        return "hello";
    }
}
