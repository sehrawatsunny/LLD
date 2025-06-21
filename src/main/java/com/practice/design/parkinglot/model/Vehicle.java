package com.practice.design.parkinglot.model;


public abstract class Vehicle {
    private final String plate;

    public Vehicle(String plate) {
        this.plate = plate;
    }

    public String getPlate(){
        return this.plate;
    }
}