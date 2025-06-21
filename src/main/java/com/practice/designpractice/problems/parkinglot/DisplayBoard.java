package com.practice.designpractice.problems.parkinglot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisplayBoard {
    private final int id;
    private final Map<String, List<ParkingSpot>> parkingSpots;

    public DisplayBoard(int id){
        this.id = id;
        this.parkingSpots = new HashMap<>();
    }

    public void addParkingSpot(String spotType, List<ParkingSpot> spots){
        parkingSpots.get(spotType).addAll(spots);
    }
    public void showFreeSlot(){
        // Impl. to show free slots.
    }
}
