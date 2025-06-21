package com.practice.designpractice.parkinglot.spot;


import com.practice.designpractice.parkinglot.model.Vehicle;

public abstract class ParkingSpot {
    private final String spotId;
    private boolean occupied;

    public ParkingSpot(String id) {
        this.spotId = id;
        this.occupied = false;
    }

    public boolean isAvailable() { return !occupied; }
    public void occupy() { occupied = true; }
    public void release() { occupied = false; }
    public String getId() { return spotId; }

    public abstract boolean canFitVehicle(Vehicle v);
}