package org.example.animals;

import org.example.area.Walkable;
import org.example.supply.Predators;

public class Tiger extends Predators implements Walkable {

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
