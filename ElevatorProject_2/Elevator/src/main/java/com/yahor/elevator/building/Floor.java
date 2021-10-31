package com.yahor.elevator.building;

import com.yahor.elevator.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Floor {
    private int floorNumber;
    private int currentPeopleNumber;
    List<User> userList = Collections.synchronizedList(new ArrayList<>());
    List<User> userListUp = Collections.synchronizedList(new ArrayList<>());
    List<User> userListDown = Collections.synchronizedList(new ArrayList<>());

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void createUserLists() {
        synchronized (userList) {
            for (User user : userList
            ) {
                if (user.getDestinationFloor() > floorNumber) {
                    userListUp.add(user);
                }
                if (user.getDestinationFloor() < floorNumber) {
                    userListDown.add(user);
                }
            }
            userList.removeAll(userListUp);
            userList.removeAll(userListDown);
        }
    }

    public List<User> getUserListUp() {
        return userListUp;
    }

    public void setUserListUp(List<User> userListUp) {
        this.userListUp = userListUp;
    }

    public void setUserListDown(List<User> userListDown) {
        this.userListDown = userListDown;
    }

    public List<User> getUserListDown() {
        return userListDown;
    }

    public boolean IsFloorNotEmpty() {
        return !userListUp.isEmpty() || !userListDown.isEmpty();
    }

    public boolean IsUserMovingUpNotEmpty() {
        return !userListUp.isEmpty();
    }

    public boolean IsUserMovingDownNotEmpty() {
        if (userListDown.isEmpty()) {
            return false;
        }
        return true;
    }

    public int getFloorNumber() {
        return floorNumber;
    }
}
