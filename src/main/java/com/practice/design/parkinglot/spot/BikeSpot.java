package com.practice.design.parkinglot.spot;

import com.practice.design.parkinglot.model.Vehicle;
import com.practice.design.parkinglot.model.Bike;

class BikeSpot extends ParkingSpot {
    public BikeSpot(String id) { super(id); }
    public boolean canFitVehicle(Vehicle v) {
        return v instanceof Bike;
    }
}
