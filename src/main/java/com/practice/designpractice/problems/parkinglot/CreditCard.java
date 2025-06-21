package com.practice.designpractice.problems.parkinglot;

public class CreditCard extends Payment{
    @Override
    public boolean initiateTransaction() {
        return false;
    }
}
