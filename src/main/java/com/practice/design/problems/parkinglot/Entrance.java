package com.practice.design.problems.parkinglot;

public class Entrance {
    private String id;

    public ParkingTicket getTicket(){
        return new ParkingTicket();
    }
    public String getId(){
        return this.id;
    }
}
