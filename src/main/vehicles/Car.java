package main.vehicles;

import main.vignettes.Vignette;

public class Car extends Vehicle{
    public Car(String model, int yearOfManufacturing) {
        super(model, yearOfManufacturing);
    }

    @Override
    public Kind getKing() {
        return Kind.CAR;
    }
}
