package com.yahor.train.carriage;

import com.yahor.train.user.Passenger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PassengerCarriageTest {
    private PassengerCarriage passengerCarriage;

    @Before
    public void setUp() {
        passengerCarriage = new PassengerCarriage(3, 34, 56, 22);
    }

    @Test
    public void testAddPassengerIfCarriageHasProperTicket() {
        // what
        int passengerTicketNumber = 36;
        int age = 23;


        Passenger passenger = new Passenger("Nikita", "Zayc", age, passengerTicketNumber);

        // when
        boolean passengerAdded = passengerCarriage.addPassenger(passenger);

        // then
        Assert.assertTrue(passengerAdded);
    }

    @Test
    public void testAddPassengerIfCarriageHasNoProperTicket() {
        // what
        int passengerTicketNumber = 31;
        int age = 23;


        Passenger passenger = new Passenger("Nikita", "Zayc", age, passengerTicketNumber);

        // when
        boolean passengerAdded = passengerCarriage.addPassenger(passenger);

        // then
        Assert.assertFalse(passengerAdded);
    }

}