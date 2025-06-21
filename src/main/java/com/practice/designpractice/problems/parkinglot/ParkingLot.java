package com.practice.designpractice.problems.parkinglot;

import java.util.HashMap;

public class ParkingLot {
    private int id;
    private String name;
    private  Address address;
    private ParkingRate parkingRate;

    private final HashMap<String,Entrance> entrances;

    private HashMap<String ,Exit> exits;

    // Create a hashmap that identifies all currently generated tickets using their ticket number
    private HashMap<String, ParkingTicket> tickets;


    // The ParkingLot is a singleton class that ensures it will have only one active instance at a time
    // Both the Entrance and Exit classes use this class to create and close parking tickets
    private static ParkingLot parkingLot = null;

    // Created a private constructor to add a restriction (due to Singleton)
    private ParkingLot() {
        // Call the name, address and parking_rate
        // Create initial entrance and exit hashmaps respectively
        this.entrances = new HashMap<>();
        this.exits = new HashMap<>();
    }

    public static ParkingLot getInstance(){
        if(parkingLot==null){
            return new ParkingLot();
        } else {
            return parkingLot;
        }
    }

    public boolean addEntrance(Entrance entrance){
        entrances.put(entrance.getId(),entrance);
        return true;
    }
    public boolean addExit(Exit exit){
        exits.put(exit.getId(),exit);
        return true;
    }
    public boolean isFull(ParkingSpot type){
        return type.getIsFree();
    }

    // This function allows parking tickets to be available at multiple entrances
    public ParkingTicket getParkingTicket(Vehicle vehicle) {
        return null;
    }
}
