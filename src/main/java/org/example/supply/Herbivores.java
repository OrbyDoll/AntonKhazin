package org.example.supply;

public class Herbivores extends DefaultType{

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
