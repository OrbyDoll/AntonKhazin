package org.example.animals;

import org.example.area.Walkable;
import org.example.supply.Herbivores;

public class Camel extends Herbivores implements Walkable {

    public Camel() {
        super("Верблюд");
    }

    @Override
    public void walk() {
        System.out.println("Верблюд - ходит");
    }

    public void eat() {
        super.eat("Трава");
    }
}
