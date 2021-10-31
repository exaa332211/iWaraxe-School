package com.yahor.elevator.building;

import com.yahor.elevator.user.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PeopleSpawnTest {
    private PeopleSpawn peopleSpawn;

    @Before
    public void setUp() {
        peopleSpawn = new PeopleSpawn(5);
    }

    boolean peopleCreated;


    @Test
    public void testCreatePeopleMoving() {
        // what
        List<User> list = Collections.synchronizedList(new ArrayList<>());
        int floorBuilding = 3;

        // when
        list = peopleSpawn.createPeopleMoving(floorBuilding);

        // then
        peopleCreated = !list.isEmpty();
        Assert.assertTrue(peopleCreated);
    }
}