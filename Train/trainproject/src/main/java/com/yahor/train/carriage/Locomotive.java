package com.yahor.train.carriage;

import com.yahor.train.user.Driver;

public class Locomotive extends Carriage {
    private Driver driver;

    public Locomotive(int carriageNumber, Driver driver) {
        super(carriageNumber);
        this.driver = driver;
    }
}
