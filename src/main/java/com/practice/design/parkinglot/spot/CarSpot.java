package com.practice.design.parkinglot.spot;

import com.practice.design.parkinglot.model.Vehicle;
import com.practice.design.parkinglot.model.Car;

class CarSpot extends ParkingSpot {
    public CarSpot(String id) { super(id); }
    public boolean canFitVehicle(Vehicle v) {
        return v instanceof Car;
    }
}
