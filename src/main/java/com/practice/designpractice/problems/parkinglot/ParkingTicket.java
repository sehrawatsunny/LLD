package com.practice.designpractice.problems.parkinglot;

import java.util.Date;

public class ParkingTicket {
    private int ticketNo;
    private Date timestamp;
    private Date exitTs;

    private double amount;
    private boolean status;

    private Vehicle vehicle;
    private Payment payment;
    private Exit exit;
    private Entrance entrance;
}
