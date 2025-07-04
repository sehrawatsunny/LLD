package com.practice.design.parkinglot.strategy;

import com.practice.design.parkinglot.model.Vehicle;
import com.practice.design.parkinglot.spot.ParkingSpot;

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