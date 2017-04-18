package com.project.salem.web.controller;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ExceptionHandlerController implements ErrorController{

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public ModelAndView error() {
        return new ModelAndView("404");
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}