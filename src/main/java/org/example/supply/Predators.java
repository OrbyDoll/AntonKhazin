package org.example.supply;

public class Predators extends DefaultType {

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
