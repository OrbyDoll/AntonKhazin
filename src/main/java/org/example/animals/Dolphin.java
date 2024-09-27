package org.example.animals;

import org.example.area.Waterfowl;
import org.example.supply.Predators;

public class Dolphin extends Predators implements Waterfowl {

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
