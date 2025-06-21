package com.practice.design.parkinglot.service;

import com.practice.design.parkinglot.spot.ParkingSpot;
import com.practice.design.parkinglot.strategy.ParkingStrategy;
import com.practice.design.parkinglot.model.Vehicle;

import java.util.*;

// ========== Singleton: ParkingLot ==========
public class ParkingLot {
    private static ParkingLot instance;
    private List<ParkingSpot> availableSpots = new ArrayList<>();
    private ParkingStrategy strategy;

    private ParkingLot() {}

    public static ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    public void setStrategy(ParkingStrategy strategy) {
        this.strategy = strategy;
    }

    public void addSpot(ParkingSpot spot) {
        availableSpots.add(spot);
    }

    public ParkingSpot park(Vehicle vehicle) {
        return strategy.findSpot(availableSpots, vehicle);
    }
}