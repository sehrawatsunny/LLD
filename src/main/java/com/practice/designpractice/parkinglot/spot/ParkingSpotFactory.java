package com.practice.designpractice.parkinglot.spot;

// ========== Factory: ParkingSpotFactory ==========
public class ParkingSpotFactory {
    public static ParkingSpot createSpot(String type, String id) {
        switch (type.toLowerCase()) {
            case "car": return new CarSpot(id);
            case "bike": return new BikeSpot(id);
            default: throw new IllegalArgumentException("Invalid spot type");
        }
    }
}
