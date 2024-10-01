package org.example.supply;

public abstract class Predators extends Animal {

    public Predators(String animalType) {
        super(animalType);
    }

    protected void eat(String mealType) {
        super.eat(mealType);
    }

    public void getSupplyType() {
        super.getSupplyType("Хищник");
    }
}
