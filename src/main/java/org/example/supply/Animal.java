package org.example.supply;

public abstract class Animal {
    private final String animalType;

    public Animal(String animalType) {
        this.animalType = animalType;
    }

    protected void eat(String mealType) {
        System.out.println(animalType + " ест " + mealType);
    }

    protected void getSupplyType(String supplyType) {
        System.out.println(animalType + " - " + supplyType);
    }
}
