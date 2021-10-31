package com.yahor.train.cargo;

public class Cargo {
    private final int number;
    private final int weight;

    public Cargo(int weight, int number) {
        this.weight = weight;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public int getWeight() {
        return weight;
    }

}