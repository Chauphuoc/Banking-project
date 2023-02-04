package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping(value = "customers")
    public ModelAndView showListCustomers() {
        ModelAndView modelAndView = new ModelAndView("customer/home");
        List<Customer> customerList = customerService.findAllCustomers();
        modelAndView.addObject("customers", customerList);
        return modelAndView;
    }
}
