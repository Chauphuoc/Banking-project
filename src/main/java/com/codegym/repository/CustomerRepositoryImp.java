package com.codegym.repository;

import com.codegym.model.Customer;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
@Transactional
public class CustomerRepositoryImp implements ICustomerRepository{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Customer> findAllCustomers() {
        TypedQuery query = entityManager.createQuery("SELECT c FROM Customer c", Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findCustomerByID(Long id) {
        return null;
    }

    @Override
    public void save(Customer customer) {

    }

    @Override
    public void remove(Long id) {

    }
}
