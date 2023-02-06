package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Deposit;
import com.codegym.service.ICustomerService;
import com.codegym.service.IDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IDepositService depositService;

    @GetMapping(value = "/")
    public ModelAndView showListCustomers() {
        ModelAndView modelAndView = new ModelAndView("customer/home");
        List<Customer> customerList = customerService.findAllCustomers();
        modelAndView.addObject("customers", customerList);
        return modelAndView;
    }

    @GetMapping(value = "/create-customer")
    public ModelAndView showCreateCustomer() {
        ModelAndView modelAndView = new ModelAndView("customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping(value = "/create-customer")
    public ModelAndView processCreateCustomer(@ModelAttribute(name = "customer") Customer customer) {
        ModelAndView modelAndView = new ModelAndView("customer/create");
        customerService.save(customer);
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("message", "Create customer successful");
        return modelAndView;
    }

    @GetMapping(value = "/edit-customer/{id}")
    public ModelAndView showEditCustomer(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("customer/edit");
        Customer customer = customerService.findCustomerByID(id);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @PostMapping(value = "/edit-customer")
    public ModelAndView saveEditCustomer(@ModelAttribute(name = "customer") Customer customer) {
        ModelAndView modelAndView = new ModelAndView("customer/edit");
        customerService.save(customer);
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("message", "Edit successful");
        return modelAndView;
    }

    @GetMapping(value = "/remove-customer/{id}")
    public ModelAndView removeBlog(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("customer/home");
        customerService.remove(id);
        List<Customer> customerList = customerService.findAllCustomers();
        modelAndView.addObject("customers", customerList);
        return modelAndView;
    }

    @GetMapping(value = "/deposit")
    public ModelAndView showFormDeposit() {
        ModelAndView modelAndView = new ModelAndView("customer/deposit");
        return modelAndView;
    }

    @PostMapping(value = "/deposit-customer")
    public ModelAndView processDeposit(@RequestParam(name = "idCustomer") Long idCustomer, @RequestParam(name ="amountMoney" ) String amountMoney) {
        ModelAndView modelAndView = new ModelAndView("customer/deposit");
        Customer customer = customerService.findCustomerByID(idCustomer);
        if (customer != null) {
            customer.setBalance(customer.getBalance()-Double.parseDouble(amountMoney));
            Deposit deposit = new Deposit();
            deposit.setId(null);
            deposit.setCustomer(customer);
            deposit.setDate(new Date());
            deposit.setAmount(Double.parseDouble(amountMoney));
//            depositService.save(deposit);
            customerService.save(customer);
            modelAndView.addObject("deposit", deposit);
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("message", "Deposit successful");
        } else modelAndView.addObject("message", "deposit fail");


        return modelAndView;
    }
}
