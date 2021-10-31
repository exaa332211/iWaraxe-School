package com.yahor.train.user;

public class Passenger extends User {
    private final int ticketNumber;

    public Passenger(String firstName, String lastName, int age, int ticketNumber) {
        super(firstName, lastName, age);
        this.ticketNumber = ticketNumber;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }
}