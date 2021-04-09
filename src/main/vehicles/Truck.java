package main.vehicles;

import main.vignettes.Vignette;

public class Truck extends Vehicle{
    @Override
    public Kind getKing() {
        return Kind.TRUCK;
    }

    public Truck(String model, int yearOfManufacturing) {
        super(model, yearOfManufacturing);
    }
}
