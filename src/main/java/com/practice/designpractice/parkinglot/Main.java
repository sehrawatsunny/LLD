package com.practice.designpractice.parkinglot;


import com.practice.designpractice.parkinglot.model.Bike;
import com.practice.designpractice.parkinglot.model.Car;
import com.practice.designpractice.parkinglot.model.Vehicle;
import com.practice.designpractice.parkinglot.service.ParkingLot;
import com.practice.designpractice.parkinglot.spot.ParkingSpotFactory;
import com.practice.designpractice.parkinglot.strategy.FirstAvailableStrategy;

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