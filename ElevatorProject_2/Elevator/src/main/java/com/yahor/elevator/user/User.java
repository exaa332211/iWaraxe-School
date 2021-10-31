package com.yahor.elevator.user;

public class User {
    private final int weight = (int) (20+Math.random()*101);
    private int destinationFloor;
    private final int passengerNumber;

    public User(int passengerNumber) {
        this.passengerNumber = passengerNumber++;
    }

    public void setDestinationFloor(int maxFloor) {
        this.destinationFloor = (int) (1+Math.random()*maxFloor);
    }

    public void setDestinationFloorForTest(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public int getWeight() {
        return weight;
    }

    public int getPassengerNumber() {
        return passengerNumber;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }
}
