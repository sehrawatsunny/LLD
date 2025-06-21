package com.practice.designpractice.parkinglot.spot;

import com.practice.designpractice.parkinglot.model.Vehicle;
import com.practice.designpractice.parkinglot.model.Car;

class CarSpot extends ParkingSpot {
    public CarSpot(String id) { super(id); }
    public boolean canFitVehicle(Vehicle v) {
        return v instanceof Car;
    }
}
