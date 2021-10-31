package com.yahor.train.carriage;

import com.yahor.train.cargo.Cargo;
import com.yahor.train.user.Passenger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CargoCarriageTest {
    private CargoCarriage cargoCarriage;

    @Before
    public void setUp() {
        cargoCarriage = new CargoCarriage(3, 34);
    }

    @Test
    public void testAddCargoIfCarriageHasProperPlace() {
        // what
        int wegiht = 20;
        int number = 2;


        Cargo cargo = new Cargo(wegiht, number);

        // when
        boolean cargoAdded = cargoCarriage.addCargo(cargo);

        // then
        Assert.assertTrue(cargoAdded);
    }

    @Test
    public void testAddPassengerIfCarriageHasNoProperPlace() {
        // what
        int wegiht = 55;
        int number = 2;


        Cargo cargo = new Cargo(wegiht, number);

        // when
        boolean cargoAdded = cargoCarriage.addCargo(cargo);

        // then
        Assert.assertFalse(cargoAdded);
    }
}
