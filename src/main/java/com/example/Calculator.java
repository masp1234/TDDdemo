package com.example;

public class Calculator {

    private NumberSource numberSource;

    public Calculator(NumberSource numberSource) {
        this.numberSource = numberSource;
    }

    public long multiply() {
        return numberSource.next() * numberSource.next();
    }
}
