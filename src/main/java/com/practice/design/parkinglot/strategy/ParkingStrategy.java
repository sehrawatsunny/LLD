package com.practice.design.parkinglot.strategy;

import com.practice.design.parkinglot.model.Vehicle;
import com.practice.design.parkinglot.spot.ParkingSpot;

import java.util.List;

// ========== Strategy: ParkingStrategy ==========
public interface ParkingStrategy {
    ParkingSpot findSpot(List<ParkingSpot> spots, Vehicle vehicle);
}
