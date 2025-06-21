package com.practice.design.problems.parkinglot;

public abstract class Account {
    private String username;
    private String password;
    private Person person;
    private AccountStatus accountStatus;

    public abstract boolean resetPassword();
}
