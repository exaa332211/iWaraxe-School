package com.yahor.elevator.building;

import com.yahor.elevator.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PeopleSpawn {
    int peopleNumber = 5;
    private int peopleCounter = 1;
    int floorNumber = 1;
    List<User> userList = Collections.synchronizedList(new ArrayList<>());
    private static final Logger logger
            = LoggerFactory.getLogger(Building.class);


    public PeopleSpawn(int peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public List createPeopleMoving(int floorBuilding) {
        userList = new ArrayList<>();
        for (int i = 1; i <= peopleNumber; i++) {
            User user = new User(peopleCounter);
            user.setDestinationFloor(floorBuilding);
            if (user.getDestinationFloor() != floorNumber) {
                userList.add(user);
                peopleCounter++;
                logger.info("Создан человек № " + user.getPassengerNumber() + " на " + floorNumber + " этаже. Едет на этаж №: " + user.getDestinationFloor());
            } else {
                peopleCounter++;
                logger.info("у человека № " + user.getPassengerNumber() + " текщий этаж совпадает с необходимым. Человек просто стоит");
            }
        }
        floorNumber++;
        return userList;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }
}