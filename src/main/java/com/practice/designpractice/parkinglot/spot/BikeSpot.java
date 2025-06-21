package com.practice.designpractice.parkinglot.spot;

import com.practice.designpractice.parkinglot.model.Vehicle;
import com.practice.designpractice.parkinglot.model.Bike;

class BikeSpot extends ParkingSpot {
    public BikeSpot(String id) { super(id); }
    public boolean canFitVehicle(Vehicle v) {
        return v instanceof Bike;
    }
}
