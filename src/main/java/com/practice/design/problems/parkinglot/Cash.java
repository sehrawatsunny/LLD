package com.practice.design.problems.parkinglot;

public class Cash extends Payment{
    @Override
    public boolean initiateTransaction() {
        return false;
    }
}
