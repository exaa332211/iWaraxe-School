package com.yahor.elevator;

import com.yahor.elevator.building.Building;
import com.yahor.elevator.building.Floor;
import com.yahor.elevator.elevator.Elevator;
import com.yahor.elevator.building.PeopleSpawn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) {
        boolean dropOff;
        int f = 0;
        int notFull = 0;
        int floorNumber = 8;
        int numberElevators = 3;
        int currentElevators = 1;
        int capacity = 1000;
        int speedElevator = 0;
        int speedDoors = 0;
        int peopleInMinute = 15;
        List<Elevator> elevatorList = new ArrayList<>();
        List<Floor> floorList = new ArrayList<>();
        final Logger logger
                = LoggerFactory.getLogger(Building.class);


        Scanner in = new Scanner(System.in);
        //System.out.println("Введите количество этажей в здании: ");
        //floorBuilding = in.nextInt();
        //System.out.println("Введите количество появления людей, едущих вверх (в минуту): ");
        //peopleInMinuteUp = in.nextInt();
        //System.out.println("Введите количество появления людей, едущих вниз (в минуту): ");
        //peopleInMinuteDown = in.nextInt();
        //System.out.println("Введите количество лифтов в здании: ");
        //numberElevators = in.nextInt();
        while (currentElevators <= numberElevators) {
            //  System.out.println("Введите грузоподъемность лифта № " + currentElevators + ": ");
            //capacity = in.nextInt();
            //System.out.println("Введите скорость лифта № " + currentElevators + ": ");
            //speedElevator = in.nextInt();
            //System.out.println("Введите скорость открытия/закрытия дверей лифта № " + currentElevators + ": ");
            //speedDoors = in.nextInt();
            elevatorList.add(new Elevator(currentElevators, capacity, speedElevator, speedDoors));
            currentElevators++;
        }
        for (int i = 1; i <= floorNumber; i++) {
            floorList.add(new Floor(i));
        }
        Building building = new Building(floorNumber);
        PeopleSpawn peopleSpawn = new PeopleSpawn(peopleInMinute);
        new Thread(() -> {
            while (true) {
                try {
                    peopleSpawn.setFloorNumber(1);
                    for (int q = 0; q < floorNumber; q++) {
                        Floor floor = floorList.get(q);
                        floor.setUserList(peopleSpawn.createPeopleMoving(floorNumber));
                        floor.createUserLists();
                    }
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
            }
        }).start();
        for (Elevator elevator : elevatorList) {
            new Thread(() -> {
                while (true) {
                    try {
                        building.start(floorList, elevator);
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                    }
                }
            }).start();
        }
    }
}

