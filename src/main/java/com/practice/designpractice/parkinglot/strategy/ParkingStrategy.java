package com.practice.designpractice.parkinglot.strategy;

import com.practice.designpractice.parkinglot.model.Vehicle;
import com.practice.designpractice.parkinglot.spot.ParkingSpot;

import java.util.List;

// ========== Strategy: ParkingStrategy ==========
public interface ParkingStrategy {
    ParkingSpot findSpot(List<ParkingSpot> spots, Vehicle vehicle);
}
