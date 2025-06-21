package com.practice.designpractice.parkinglot.strategy;

import com.practice.designpractice.parkinglot.strategy.ParkingStrategy;
import com.practice.designpractice.parkinglot.model.Vehicle;
import com.practice.designpractice.parkinglot.spot.ParkingSpot;

import java.util.List;

public class FirstAvailableStrategy implements ParkingStrategy {
    public ParkingSpot findSpot(List<ParkingSpot> spots, Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (spot.isAvailable() && spot.canFitVehicle(vehicle)) {
                spot.occupy();
                System.out.println("Allocated Spot: " + spot.getId());
                return spot;
            }
        }
        System.out.println("No spot available for: " + vehicle.getPlate());
        return null;
    }
}