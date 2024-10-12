package org.example.animals;

import org.example.area.Flying;
import org.example.supply.Predators;

public class Eagle extends Predators implements Flying {

    public Eagle() {
        super("Орел");
    }

    @Override
    public void fly() {
        System.out.println("Орел - летает");
    }

    public void eat() {
        super.eat("Мясо");
    }
}
