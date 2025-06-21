package com.practice.design.problems.parkinglot;

public class ParkingAgent extends Account{

    public boolean processTicket(String ticketNo){
       // Logic to process the ticket.
        return true;
    }
    @Override
    public boolean resetPassword() {
        return false;
    }
}
