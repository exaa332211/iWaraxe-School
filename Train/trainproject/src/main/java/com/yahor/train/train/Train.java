package com.yahor.train.train;

import com.yahor.train.cargo.Cargo;
import com.yahor.train.carriage.CargoCarriage;
import com.yahor.train.carriage.Carriage;
import com.yahor.train.carriage.PassengerCarriage;
import com.yahor.train.user.Passenger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Train {
    private int numberTrain = 0;
    private TrainCarriage head;
    private int trainSize = 1;
    private static final Logger logger
            = LoggerFactory.getLogger(Train.class);

    public Train(int numberTrain) {
        this.numberTrain = numberTrain;
    }

    public void insert(Carriage carriage) {
        TrainCarriage newNode = new TrainCarriage(carriage);
        if (head == null) {
            head = newNode;
        } else {
            TrainCarriage currentNode = head;
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(newNode);
            trainSize++;
        }
    }


    public void addPassenger(Passenger passenger) {
        TrainCarriage currentNode = head;
        while (currentNode != null) {
            Carriage carriage = currentNode.getCarriage();
            if (carriage instanceof PassengerCarriage passengerCarriage) {
                if (passenger.getTicketNumber() <= passengerCarriage.getMaxTicket() &&
                        passenger.getTicketNumber() >= passengerCarriage.getMinTicket()) {
                    boolean addPassenger = passengerCarriage.addPassenger(passenger);
                    if (addPassenger) {
                        logger.info("Пассажир " + passenger.getFirstName() + " " +
                                passenger.getLastName() + " сел в вагон № " + passengerCarriage.getCarriageNumber());
                        logger.info("В вагоне № " + passengerCarriage.getCarriageNumber() +
                                " осталось мест: " + passengerCarriage.getRemainingPlaces());
                        return;
                    }
                }
            }
            currentNode = currentNode.getNext();
        }

        logger.error("Пассажир " + passenger.getFirstName() + " " +
                passenger.getLastName() + " не сел в поезд из-за того что его билет не подходит");
    }


    public void addCargo(Cargo cargo) {
        TrainCarriage currentNode = head;
        while (currentNode != null) {
            Carriage carriage = currentNode.getCarriage();
            if (carriage instanceof CargoCarriage cargoCarriage) {
                boolean addCargo = cargoCarriage.addCargo(cargo);
                if (addCargo) {
                    logger.info("Груз № " + cargo.getNumber() + " весом " +
                            cargo.getWeight() + " погружен в вагон № " + cargoCarriage.getCarriageNumber());
                    logger.info("У вагоне № " + cargoCarriage.getCarriageNumber() +
                            " оставшаяся вместимость: " + cargoCarriage.getRemainingWeight());
                    return;
                }
            }
            currentNode = currentNode.getNext();
        }
        logger.error("Груз № " + cargo.getNumber() + " весом " +
                cargo.getWeight() + " не погружен в поезд");
    }

}