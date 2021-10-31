package com.yahor.elevator.elevator;

import com.yahor.elevator.building.Building;
import com.yahor.elevator.building.Floor;
import com.yahor.elevator.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Elevator {
    private ElevatorState elevatorState = ElevatorState.IDLE;
    private int capacity;
    private int speedElevator;
    private int speedDoors;
    private int floorNumber = 1;
    private int elevatorNumber;
    private int currentCapacity;
    List<User> currentPassengersList = Collections.synchronizedList(new ArrayList<>());
    private static final Logger logger
            = LoggerFactory.getLogger(Building.class);


    public Elevator(int elevatorNumber, int capacity, int speedElevator, int speedDoors) {
        this.elevatorNumber = elevatorNumber;
        this.capacity = capacity;
        this.speedElevator = speedElevator;
        this.speedDoors = speedDoors;
        this.currentCapacity = capacity;
    }

    public int getElevatorNumber() {
        return elevatorNumber;
    }

    public void moveUp() {
        floorNumber++;
        logger.info("Лифт № " + elevatorNumber + " поднялся на этаж " + floorNumber);
        elevatorState = ElevatorState.MOVING_UP;
    }

    public void moveDown() {
        floorNumber--;
        logger.info("Лифт № " + elevatorNumber + " опустился на " + floorNumber + " этаж");
        elevatorState = ElevatorState.MOVING_DOWN;
    }

    public void setElevatorState(ElevatorState elevatorState) {
        this.elevatorState = elevatorState;
    }

    public void stop() {
        logger.info("Лифт № " + elevatorNumber + " остановился");
        logger.info("Лифт № " + elevatorNumber + " ожидает пассажиров");
        elevatorState = ElevatorState.IDLE;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public ElevatorState getElevatorState() {
        return elevatorState;
    }

    public List<User> getCurrentPassengersList() {
        return currentPassengersList;
    }

    public boolean isPassengers() {
        return !currentPassengersList.isEmpty();
    }

    public void setCurrentPassengersList(List<User> currentPassengersList) {
        this.currentPassengersList = currentPassengersList;
    }

    public void dropOffPassengers() {
        List<User> userDropOffList = new ArrayList<>();
        List<User> elevatorPassengers = currentPassengersList;
        synchronized (currentPassengersList) {
            for (User user : currentPassengersList) {
                if (floorNumber == user.getDestinationFloor()) {
                    currentCapacity = currentCapacity + user.getWeight();
                    logger.info("Пассажир № " + user.getPassengerNumber() + " вышел на " + floorNumber + " этаже из лифта № " + elevatorNumber);
                    userDropOffList.add(user);
                }
            }
        }
        elevatorPassengers.removeAll(userDropOffList);
    }

    public boolean addPassengersUp(Floor floor) {
        List<User> userNoAddedListUp = floor.getUserListUp();
        List<User> userAddedListUp = new ArrayList<>();
        synchronized (floor.getUserListUp()) {
            for (User user : floor.getUserListUp()
            ) {
                if (currentCapacity < 20) {
                    logger.info("Лифт № " + elevatorNumber + " загружен");
                    return false;
                }
                if (currentCapacity < user.getWeight()) {
                    logger.error("Пассажир № " + user.getPassengerNumber() + " не влез в лифт № " + elevatorNumber);
                    return false;
                } else {
                    currentCapacity = currentCapacity - user.getWeight();
                    logger.info("Пассажир № " + user.getPassengerNumber() + " сел на " + floor.getFloorNumber() + " этаже в лифт № " + elevatorNumber);
                    currentPassengersList.add(user);
                    userAddedListUp.add(user);
                }
            }
        }
        userNoAddedListUp.removeAll(userAddedListUp);
        floor.setUserListUp(userNoAddedListUp);
        return true;
    }

    public void addPassengersDown(Floor floor) {
        List<User> userNoAddedListDown = floor.getUserListDown();
        List<User> userAddedListDown = new ArrayList<>();
        synchronized (floor.getUserListDown()) {
            for (User user : floor.getUserListDown()
            ) {
                if (currentCapacity < 20) {
                    logger.info("Лифт № " + elevatorNumber + " загружен");
                    break;
                }
                if (currentCapacity < user.getWeight()) {
                    logger.error("Пассажир № " + user.getPassengerNumber() + " не влез в лифт № " + elevatorNumber);
                } else {
                    currentCapacity = currentCapacity - user.getWeight();
                    logger.info("Пассажир № " + user.getPassengerNumber() + " сел на " + floor.getFloorNumber() + " этаже в лифт № " + elevatorNumber);
                    currentPassengersList.add(user);
                    userAddedListDown.add(user);
                }
            }
        }
        userNoAddedListDown.removeAll(userAddedListDown);
        floor.setUserListDown(userNoAddedListDown);
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }
}
