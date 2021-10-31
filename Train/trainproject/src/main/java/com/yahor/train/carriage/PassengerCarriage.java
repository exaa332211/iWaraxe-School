package com.yahor.train.carriage;

import com.yahor.train.user.Passenger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PassengerCarriage extends Carriage {
    private final int maxTicket;
    private final int minTicket;
    private final int maxPlaces = 0;
    private int remainingPlaces;
    private List<Passenger> passengers = new ArrayList<>();
    private static final Logger logger
            = LoggerFactory.getLogger(PassengerCarriage.class);

    public PassengerCarriage(int carriageNumber, int minTicket, int maxTicket, int maxPlaces) {
        super(carriageNumber);
        this.minTicket = minTicket;
        this.maxTicket = maxTicket;
        this.remainingPlaces = maxPlaces;
    }

    public boolean addPassenger(Passenger passenger) {
        if (remainingPlaces == 0) {
            logger.error("В вагоне № " + carriageNumber + " закончились места");
            return false;
        }

        if (passenger.getTicketNumber() < minTicket || passenger.getTicketNumber() > maxTicket) {
            logger.error("У пассажира " + passenger.getFirstName() + " " +
                    passenger.getLastName() + " место не в том вагоне");
            return false;
        }

        remainingPlaces--;
        passengers.add(passenger);
        return true;
    }

    public int getMaxTicket() {
        return maxTicket;
    }

    public int getMinTicket() {
        return minTicket;
    }

    public int getRemainingPlaces() {
        return remainingPlaces;
    }
}