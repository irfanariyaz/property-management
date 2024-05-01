package com.mycompany.propertymanagement.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/calculator")
public class CalculatorController {

    @GetMapping("/add")
    public Double  add(@RequestParam("num1") Double num1, @RequestParam("num2") Double num2){
        return num1 + num2;
    }

    @GetMapping("/sub/{num1}/{num2}")
    public Double  subtract(@PathVariable("num1") Double num1, @PathVariable("num2") Double num2){
        return num1 - num2;
    }



}
