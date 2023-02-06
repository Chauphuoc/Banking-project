package com.codegym.service;

import com.codegym.model.Deposit;
import com.codegym.repository.IDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DepositServiceImp implements  IDepositService {
    @Autowired
    private IDepositRepository depositRepository;

    @Override
    public List<Deposit> findAllDeposits() {
        return null;
    }

    @Override
    public Deposit findDepositByID(Long id) {
        return null;
    }

    @Override
    public void save(Deposit deposit) {
        depositRepository.save(deposit);
    }

    @Override
    public void remove(Long id) {

    }
}
