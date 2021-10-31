package com.yahor.elevator.elevator;

import com.yahor.elevator.building.Floor;
import com.yahor.elevator.building.PeopleSpawn;
import com.yahor.elevator.user.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ElevatorTest {
    private Elevator elevator;

    @Before
    public void setUp() {
        elevator = new Elevator(1, 400, 1, 1);
    }

    int peopleNumber = 1;
    boolean passengerAdded;
    PeopleSpawn peopleSpawn = new PeopleSpawn(peopleNumber);
    Floor floor = new Floor(4);


    @Test
    public void testAddPassengersUpIfElevatorHasNoPlaces() {
        // what
        List<User> list = Collections.synchronizedList(new ArrayList<>());
        User user = new User(1);
        user.setDestinationFloorForTest(5);
        list.add(user);
        System.out.println("Создан пасссажир № 1 на " + floor.getFloorNumber() + " этаже");
        floor.setUserList(list);
        floor.createUserLists();
        elevator.setCurrentCapacity(10);
        int passengersNumber = elevator.getCurrentPassengersList().size();

        // when
        elevator.addPassengersUp(floor);

        // then
        passengerAdded = passengersNumber < elevator.getCurrentPassengersList().size();
        Assert.assertFalse(passengerAdded);
    }

    @Test
    public void testAddPassengersUpIfElevatorHasPlaces() {
        // what
        List<User> list = Collections.synchronizedList(new ArrayList<>());
        User user = new User(1);
        user.setDestinationFloorForTest(5);
        list.add(user);
        System.out.println("Создан пасссажир № 1 на " + floor.getFloorNumber() + " этаже");
        floor.setUserList(list);
        floor.createUserLists();

        int passengersNumber = elevator.getCurrentPassengersList().size();

        // when
        elevator.addPassengersUp(floor);

        // then
        passengerAdded = passengersNumber < elevator.getCurrentPassengersList().size();
        Assert.assertTrue(passengerAdded);
    }

    @Test
    public void testAddPassengersDownIfElevatorHasNoPlaces() {
        // what
        List<User> list = Collections.synchronizedList(new ArrayList<>());
        User user = new User(1);
        user.setDestinationFloorForTest(2);
        list.add(user);
        System.out.println("Создан пасссажир № 1 на " + floor.getFloorNumber() + " этаже");
        floor.setUserList(list);
        floor.createUserLists();
        elevator.setCurrentCapacity(10);
        int passengersNumber = elevator.getCurrentPassengersList().size();

        // when
        elevator.addPassengersDown(floor);

        // then
        passengerAdded = passengersNumber < elevator.getCurrentPassengersList().size();
        Assert.assertFalse(passengerAdded);
    }

    @Test
    public void testAddPassengersDownIfElevatorHasPlaces() {
        // what
        List<User> list = Collections.synchronizedList(new ArrayList<>());
        User user = new User(1);
        user.setDestinationFloorForTest(2);
        list.add(user);
        System.out.println("Создан пасссажир № 1 на " + floor.getFloorNumber() + " этаже");
        floor.setUserList(list);
        floor.createUserLists();

        int passengersNumber = elevator.getCurrentPassengersList().size();

        // when
        elevator.addPassengersDown(floor);

        // then
        passengerAdded = passengersNumber < elevator.getCurrentPassengersList().size();
        Assert.assertTrue(passengerAdded);
    }


    @Test
    public void testDropOffPassengersIfElevatorOnNeedFloor() {
        // what
        List<User> list = Collections.synchronizedList(new ArrayList<>());
        User user = new User(1);
        list.add(user);
        user.setDestinationFloorForTest(5);
        floor.setUserList(list);
        floor.createUserLists();
        elevator.addPassengersUp(floor);
        elevator.setFloorNumber(user.getDestinationFloor());
        int passengersNumber = elevator.getCurrentPassengersList().size();


        // when
        elevator.dropOffPassengers();

        // then
        passengerAdded = passengersNumber > elevator.getCurrentPassengersList().size();
        Assert.assertTrue(passengerAdded);
    }

    @Test
    public void testDropOffPassengersIfElevatorNoOnNeedFloor() {
        // what
        List<User> list = Collections.synchronizedList(new ArrayList<>());
        User user = new User(1);
        list.add(user);
        user.setDestinationFloorForTest(5);
        floor.setUserList(list);
        floor.createUserLists();
        elevator.addPassengersUp(floor);
        elevator.setFloorNumber(user.getDestinationFloor() + 1);
        int passengersNumber = elevator.getCurrentPassengersList().size();


        // when
        elevator.dropOffPassengers();

        // then
        passengerAdded = passengersNumber > elevator.getCurrentPassengersList().size();
        Assert.assertFalse(passengerAdded);
    }


}
