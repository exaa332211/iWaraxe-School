package com.yahor.train.user;

public class Driver extends User {

    public Driver(String firstName, String lastName, int age, boolean license) {
        super(firstName, lastName, age);
        if (age < 18) {
            throw new IllegalArgumentException("Водитель несовершеннолетний, его возраст: " + age);
        }
        if (!license) {
            throw new IllegalArgumentException("Водитель не имеет лицензии");
        }
    }
}