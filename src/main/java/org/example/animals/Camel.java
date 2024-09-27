package org.example.animals;

import org.example.area.Terrestrial;
import org.example.supply.Herbivores;

public class Camel extends Herbivores implements Terrestrial {

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
