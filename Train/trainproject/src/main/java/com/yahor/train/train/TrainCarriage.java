package com.yahor.train.train;

import com.yahor.train.carriage.Carriage;

public class TrainCarriage {
    private final Carriage carriage;
    private TrainCarriage next;

    public TrainCarriage(Carriage carriage) {
        this.carriage = carriage;
    }

    public Carriage getCarriage() {
        return carriage;
    }

    public TrainCarriage getNext() {
        return next;
    }

    public void setNext(TrainCarriage next) {
        this.next = next;
    }
}
