package com.practice.design.parkinglot;


import com.practice.design.parkinglot.model.Bike;
import com.practice.design.parkinglot.model.Car;
import com.practice.design.parkinglot.model.Vehicle;
import com.practice.design.parkinglot.service.ParkingLot;
import com.practice.design.parkinglot.spot.ParkingSpotFactory;
import com.practice.design.parkinglot.strategy.FirstAvailableStrategy;

// ========== Main ==========
public class Main {
    public static void main(String[] args) {
        ParkingLot lot = ParkingLot.getInstance();
        lot.setStrategy(new FirstAvailableStrategy());

        lot.addSpot(ParkingSpotFactory.createSpot("car", "C1"));
        lot.addSpot(ParkingSpotFactory.createSpot("bike", "B1"));
        lot.addSpot(ParkingSpotFactory.createSpot("car", "C2"));

        Vehicle car1 = new Car("DEL1234");
        Vehicle bike1 = new Bike("DEL5678");

        lot.park(car1);   // Should get C1
        lot.park(bike1);  // Should get B1
        lot.park(new Car("DL9999"));  // Should get C2
        lot.park(new Car("DL8888"));  // Should print: No spot available
    }
}