package main.vehicles;

import main.vignettes.Vignette;

public class Bus extends Vehicle{
    public Bus(String model, int yearOfManufacturing) {
        super(model, yearOfManufacturing);
    }

    @Override
    public Kind getKing() {
        return Kind.BUS;
    }
}