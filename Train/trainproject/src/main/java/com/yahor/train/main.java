package com.yahor.train;

import com.yahor.train.cargo.Cargo;
import com.yahor.train.carriage.CargoCarriage;
import com.yahor.train.carriage.Locomotive;
import com.yahor.train.carriage.PassengerCarriage;
import com.yahor.train.train.Train;
import com.yahor.train.user.Driver;
import com.yahor.train.user.Passenger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    private static final Logger logger
            = LoggerFactory.getLogger(main.class);

    public static void main(String[] args) throws Exception {
        int newPassanger = 1;
        boolean licenseDriver = true;
        int license = 1;
        boolean lic = false;
        int newCarriage = 1;
        int newCargo = 1;
        int carriage = 1;
        int weightCargo = 1;
        int age = 1;
        int cargoNumber = 0;
        int maxWeightOrPlaces = 1;
        int carriageNumber = 0;
        int ticketCounter = 0;
        int ticketNumber = 0;
        int cargoTicket = 1;
        Scanner in = new Scanner(System.in);

        System.out.print("Задайте номер поезда: ");
        int numberTrain = in.nextInt();
        Train firstTrain = new Train(numberTrain);
        List<Cargo> cargoList = new ArrayList<>();
        List<Passenger> passangersList = new ArrayList<>();

        System.out.println("Задайте информацию о водителе:");
        System.out.print("Имя: ");
        String firstName = in.next();
        System.out.print("Фамилия: ");
        String lastName = in.next();
        System.out.print("Возраст: ");
        age = in.nextInt();
        while (!lic) {
            System.out.print("Имеет лицензию? (1 - да, 0 - нет): ");
            license = in.nextInt();
            if (license != 1 && license != 0) {
                logger.error("Неверный ответ");
                continue;
            }
            licenseDriver = license == 1;
            lic = true;
        }

        Driver driver = new Driver(firstName, lastName, age, licenseDriver);
        Locomotive locomotive = new Locomotive(carriageNumber,driver);
        firstTrain.insert(locomotive);
        carriageNumber++;

        System.out.print("Добавьте вагоны и вместимость: \n");
        while (newCarriage != 0) {
            System.out.print("1 - пассажирский вагон, 2 - грузовой вагон:");
            carriage = in.nextInt();
            if (carriage != 1 && carriage != 2) {
                logger.error("Неверный тип вагона");
                continue;
            }

            System.out.print("кол-во мест/максимальный вес:");
            maxWeightOrPlaces = in.nextInt();
            if (maxWeightOrPlaces == 0) {
                logger.error("Не может быть равно 0");
                continue;
            }
            if (carriage == 1) {
                int maxTicket = maxWeightOrPlaces + ticketCounter;
                PassengerCarriage cargoCarriage = new PassengerCarriage(carriageNumber, ticketCounter + 1,
                        maxTicket, maxWeightOrPlaces);
                firstTrain.insert(cargoCarriage);
                ticketCounter = maxWeightOrPlaces + ticketCounter;


            } else {
                CargoCarriage cargoCarriage = new CargoCarriage(carriageNumber, maxWeightOrPlaces);
                firstTrain.insert(cargoCarriage);
            }
            carriageNumber++;

            System.out.print("1 - новый вагон, 0 - конец сборки");
            newCarriage = in.nextInt();
            if (newCarriage != 1 && newCarriage != 0) {
                logger.error("Неверное число");
            }
        }

        System.out.println("Задайте вес для груза: ");
        while (newCargo != 0) {
            System.out.print("Вес: ");
            weightCargo = in.nextInt();
            if (weightCargo == 0) {
                logger.error("Не может быть равен 0");
                continue;
            }
            cargoNumber++;
            cargoList.add(new Cargo(weightCargo, cargoNumber));
            System.out.print("1 - новый груз, 0 - конец груза");
            newCargo = in.nextInt();
            if (newCargo != 1 && newCargo != 0) {
                logger.error("Неверное число");
            }
        }
        while (newPassanger != 0) {
            System.out.println("Задайте информацию о пассажирах(каждый раз новый пассажир):");
            System.out.print("Имя: ");
            firstName = in.next();
            System.out.print("Фамилия: ");
            lastName = in.next();
            System.out.print("Возраст: ");
            age = in.nextInt();
            System.out.print("Номер вагона в котором едет: ");
            ticketNumber = in.nextInt();
            if (age == 0 || ticketNumber == 0) {
                logger.error("Неверные данные");
                continue;
            }
            passangersList.add(new Passenger(firstName, lastName, age, ticketNumber));
            System.out.print("1 - новый пассажир,0 - конец пассажиров:");
            newPassanger = in.nextInt();
            if (newPassanger != 1 && newPassanger != 0) {
                logger.error("Неверное число");
            }
        }
        in.close();
        for (
                Cargo cargo : cargoList) {
            firstTrain.addCargo(cargo);
        }
        for (
                Passenger passenger : passangersList) {
            firstTrain.addPassenger(passenger);
        }
    }
}
