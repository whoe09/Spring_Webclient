package com.example.openapi_test.server.request.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping({"/","index"})
    public String getIndex() {
        return "index";
    }
}
