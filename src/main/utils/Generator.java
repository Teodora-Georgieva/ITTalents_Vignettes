package main.utils;

import main.vehicles.Vehicle;
import main.vignettes.Vignette;

import java.util.Random;

public abstract class Generator {
    public static int generateRandomNumber(int min, int max){
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }

    public static Vignette.PeriodType generatePeriodType(){
        Vignette.PeriodType periodType;
        int periodNum = Generator.generateRandomNumber(1, 3);
        switch (periodNum){
            case 1: periodType = Vignette.PeriodType.DAY; break;
            case 2: periodType = Vignette.PeriodType.MONTH; break;
            default: periodType = Vignette.PeriodType.YEAR; break;
        }

        return periodType;
    }

    public static Vehicle.Kind generateKindOfVehicle(){
        int kindNum = Generator.generateRandomNumber(1, 3);
        Vehicle.Kind kind;
        switch (kindNum){
            case 1: kind = Vehicle.Kind.BUS; break;
            case 2: kind = Vehicle.Kind.CAR; break;
            default: kind = Vehicle.Kind.TRUCK; break;
        }

        return kind;
    }
}