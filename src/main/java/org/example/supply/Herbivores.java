package org.example.supply;

public abstract class Herbivores extends Animal{

    public Herbivores(String animalType) {
        super(animalType);
    }

    protected void eat(String mealType) {
        super.eat(mealType);
    }

    public void getSupplyType() {
        super.getSupplyType("Травоядный");
    }
}
