package com.easyjojo.model2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping("/hello")
    public Map<String, Object> hello() {
        return Map.of(
                "status", "UP",
                "module", "model2",
                "message", "Hello from Model2 Application!"
        );
    }
}
