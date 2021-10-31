package com.yahor.elevator.building;

import com.yahor.elevator.user.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FloorTest {
    private Floor floor;

    @Before
    public void setUp() {
        floor = new Floor(1);
    }

    boolean peopleCreated;


    @Test
    public void testCreateUserLists() {
        // what
        PeopleSpawn peopleSpawn = new PeopleSpawn(5);
        int floorBuilding = 3;
        floor.setUserList(peopleSpawn.createPeopleMoving(floorBuilding));

        // when
        floor.createUserLists();

        // then
        if ((!floor.getUserListUp().isEmpty() || !floor.getUserListDown().isEmpty()) && floor.getUserList().isEmpty()) {
            peopleCreated = true;
        }
        Assert.assertTrue(peopleCreated);
    }
}