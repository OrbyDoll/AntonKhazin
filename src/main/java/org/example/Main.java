package org.example;
import org.example.animals.*;

public class Main {
    public static void main(String[] args) {
        Tiger tiger = new Tiger();
        tiger.eat();
        tiger.walk();
        tiger.getSupplyType();

        Eagle eagle = new Eagle();
        eagle.fly();
        eagle.getSupplyType();
        eagle.eat();

        Camel camel = new Camel();
        camel.getSupplyType();
        camel.eat();
        camel.walk();
    }
}
