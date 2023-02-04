package com.codegym.model;

import javax.persistence.*;

@Entity(name = "Deposit")
@Table(name = "deposits")
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_amount")
    private double amount;

    public Deposit(Long id, double amount, Customer customer) {
        this.id = id;
        this.amount = amount;
        this.customer = customer;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false, referencedColumnName = "id")
    private Customer customer;

    public Deposit() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "id=" + id +
                ", amount=" + amount +
                ", customer=" + customer +
                '}';
    }
}
