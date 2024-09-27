package org.example.supply;

public abstract class DefaultType {
    private final String animalType;

    public DefaultType(String animalType) {
        this.animalType = animalType;
    }

    protected void eat(String mealType) {
        System.out.println(animalType + " ест " + mealType);
    }

    protected void getSupplyType(String supplyType) {
        System.out.println(animalType + " - " + supplyType);
    }
}
