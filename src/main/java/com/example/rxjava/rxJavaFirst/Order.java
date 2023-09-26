package com.example.rxjava.rxJavaFirst;

public class Order {
    private String value;

    public Order(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Order{" +
                "value='" + value + '\'' +
                '}';
    }
}
