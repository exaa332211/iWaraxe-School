package com.yahor.elevator.building;

import com.yahor.elevator.elevator.Elevator;
import com.yahor.elevator.elevator.ElevatorState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Building {
    public int floorNumber;
    int a = 0;
    boolean isPassengersOnFloors = false;
    List<Floor> floorList = new ArrayList<>();
    private Map<String, Integer> statistics = new HashMap<>();
    private static final Logger logger
            = LoggerFactory.getLogger(Building.class);

    public Building(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    private void addStatistics(String key, Integer value) {
        Integer oldValue = statistics.getOrDefault(key, 0);
        statistics.put(key, oldValue + value);
    }

    public void start(List<Floor> floorList, Elevator elevator) {

        while (true) {
            if (elevator.getElevatorState() == ElevatorState.MOVING_UP) {
                for (int i = elevator.getFloorNumber() - 1; i < floorNumber; i++) {
                    Floor floor = floorList.get(i);
                    if (floor.IsUserMovingUpNotEmpty()) {
                        isPassengersOnFloors = true;
                    }
                }
                if (isPassengersOnFloors || elevator.isPassengers()) {
                    for (int i = elevator.getFloorNumber() - 1; i < floorNumber; i++) {
                        Floor floor = floorList.get(i);
                        elevator.dropOffPassengers();
                        if (elevator.addPassengersUp(floor)) {
                            isPassengersOnFloors = false;
                        }
                        if (elevator.getFloorNumber() < 8) {
                            elevator.moveUp();
                            break;
                        }
                    }
                } else {
                    elevator.stop();
                }
            }
            if (elevator.getElevatorState() == ElevatorState.MOVING_DOWN) {
                for (int i = elevator.getFloorNumber() - 1; i > 0; i--) {
                    Floor floor = floorList.get(i);
                    if (floor.IsUserMovingDownNotEmpty()) {
                        isPassengersOnFloors = true;
                    }
                }
                if (isPassengersOnFloors || elevator.isPassengers()) {
                    for (int i = elevator.getFloorNumber() - 1; i >= 0; i--) {
                        Floor floor = floorList.get(i);
                        elevator.dropOffPassengers();
                        elevator.addPassengersDown(floor);
                        isPassengersOnFloors = false;
                        if (elevator.getFloorNumber() > 1) {
                            elevator.moveDown();
                            break;
                        }
                    }
                } else {
                    elevator.stop();
                }
            }
            if (elevator.getElevatorState() == ElevatorState.IDLE) {
                for (int i = elevator.getFloorNumber() - 1; i < floorNumber; i++) {
                    Floor floor = floorList.get(i);
                    if (floor.IsUserMovingUpNotEmpty()) {
                        elevator.setElevatorState(ElevatorState.MOVING_UP);
                        logger.info("Лифт № " + elevator.getElevatorNumber() + " едет вверх");
                        break;
                    }
                }
            }
            if (elevator.getElevatorState() == ElevatorState.IDLE) {
                for (int i = elevator.getFloorNumber() - 1; i > 0; i--) {
                    Floor floor = floorList.get(i);
                    if (floor.IsUserMovingDownNotEmpty()) {
                        elevator.setElevatorState(ElevatorState.MOVING_DOWN);
                        logger.info("Лифт № " + elevator.getElevatorNumber() + " едет вниз");
                        break;
                    }
                }
            }
        }
    }
}



