package com.practice.designpractice.problems.parkinglot;

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
