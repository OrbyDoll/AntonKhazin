package org.example.animals;
import org.example.area.Walkable;
import org.example.supply.Herbivores;

public class Horse extends Herbivores implements Walkable {

    public Horse() {
        super("Лошадь");
    }

    @Override
    public void walk() {
        System.out.println("Лошадь - ходит");
    }

    public void eat() {
        super.eat("Трава");
    }
}
