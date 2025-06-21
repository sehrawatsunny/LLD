package com.practice.design.problems.parkinglot;

import java.util.Date;

public abstract class Payment {
    private PaymentStatus paymentStatus;
    private double amount;
    private Date timestamp;

    public abstract boolean initiateTransaction();
}
