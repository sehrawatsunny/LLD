package com.practice.designpractice.problems.parkinglot;

public class Cash extends Payment{
    @Override
    public boolean initiateTransaction() {
        return false;
    }
}
