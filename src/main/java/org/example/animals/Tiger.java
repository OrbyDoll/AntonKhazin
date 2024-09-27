package org.example.animals;

import org.example.area.Terrestrial;
import org.example.supply.Predators;

public class Tiger extends Predators implements Terrestrial {

    public Tiger() {
        super("Тигр");
    }

    @Override
    public void walk() {
        System.out.println("Тигр - ходит");
    }

    public void eat() {
        super.eat("Говядина");
    }
}
