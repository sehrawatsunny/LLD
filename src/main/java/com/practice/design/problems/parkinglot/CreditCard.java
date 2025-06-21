package com.practice.design.problems.parkinglot;

public class CreditCard extends Payment{
    @Override
    public boolean initiateTransaction() {
        return false;
    }
}
