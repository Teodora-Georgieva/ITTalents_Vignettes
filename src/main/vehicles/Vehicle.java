package main.vehicles;

import main.utils.Validator;
import main.vignettes.Vignette;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Vehicle {
    private static int id = 1;
    private int vehicleID;
    private String model;
    private Vignette vignette;
    private int yearOfManufacturing;

    public enum Kind{
        CAR, BUS, TRUCK;
    }

    public Vehicle(String model, int yearOfManufacturing) {
        this.vehicleID = id++;
        this.vignette = null;

        if(Validator.isValidString(model)) {
            this.model = model;
        }
        else{
            this.model = "Mercedes Benz E63";
        }

        if(yearOfManufacturing > 1960) {
            this.yearOfManufacturing = yearOfManufacturing;
        }
        else{
            this.yearOfManufacturing = 2005;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return vehicleID == vehicle.vehicleID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleID);
    }

    public abstract Kind getKing();

    public Vignette getVignette() {
        return vignette;
    }

    public int stickVignette(Vignette vignette){
        this.vignette = vignette;
        return vignette.stickVignette();
    }

    public boolean hasVignette(){
        return this.vignette != null;
    }

    public boolean isVignetteExpiredAt(LocalDate localDate){
        return this.getVignette().getExpireDate().isBefore(localDate);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleID=" + vehicleID +
                ", model='" + model + '\'' +
                ", vignette=" + ((vignette == null) ? "no vignette" : vignette) +
                ", yearOfManufacturing=" + yearOfManufacturing +
                '}';
    }
}