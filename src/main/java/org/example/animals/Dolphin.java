package org.example.animals;

import org.example.area.Swimable;
import org.example.supply.Predators;

public class Dolphin extends Predators implements Swimable {

    public Dolphin() {
        super("Дельфин");
    }

    @Override
    public void swim() {
        System.out.println("Дельфин - плавает");
    }

    public void eat() {
        super.eat("Рыба");
    }
}
