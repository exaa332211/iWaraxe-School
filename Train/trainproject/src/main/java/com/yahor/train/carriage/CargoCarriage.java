package com.yahor.train.carriage;

import com.yahor.train.cargo.Cargo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CargoCarriage extends Carriage {
    private final int maxWeight;
    private int remainingWeight;
    private List<Cargo> someCargo = new ArrayList<>();
    private static final Logger logger
            = LoggerFactory.getLogger(CargoCarriage.class);

    public CargoCarriage(int carriageNumber, int maxWeight) {
        super(carriageNumber);
        this.maxWeight = maxWeight;
        this.remainingWeight = maxWeight;
    }


    public boolean addCargo(Cargo cargo) {
        if (remainingWeight == 0) {
            logger.info("Вагон № " + carriageNumber + " загружен");
            return false;
        }

        if (cargo.getWeight() > remainingWeight) {
            logger.info("Груз № " + cargo.getNumber() + " весом " +
                    cargo.getWeight() + " слшиком тяжелый для вагона № " + carriageNumber + " оставшейся вместимостью " + remainingWeight);
            return false;
        }

        remainingWeight = remainingWeight - cargo.getWeight();
        someCargo.add(cargo);
        return true;
    }

    public int getRemainingWeight() {
        return remainingWeight;
    }
}
