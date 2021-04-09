package main;

import main.drivers.Driver;
import main.utils.Generator;
import main.vehicles.Bus;
import main.vehicles.Car;
import main.vehicles.Truck;
import main.vehicles.Vehicle;
import main.vignettes.Vignette;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class Demo {
    public static void main(String[] args) {
        GasStation gasStation = new GasStation();
        //gasStation.showAvailableVignettes(); //WORKS
        System.out.println();
        System.out.println("Count of available vignettes in original list before selling: " + gasStation.getVignettesCount());

        System.out.println();

        System.out.println("Creating drivers...");
        ArrayList<Driver> drivers = new ArrayList();
        for (int i = 0; i < 20; i++) {
            String name = "Driver" + (i+1);
            double money = Generator.generateRandomNumber(200, 5000);
            drivers.add(new Driver(name, money, gasStation));
        }

        ArrayList<Vehicle> vehicles = new ArrayList();
        int year = 1970;
        for (int i = 0; i < 200; i++) {
            Vehicle.Kind kind = Generator.generateKindOfVehicle();
            String model = "Model" + (i+1);
            int yearOfManufacturing = year++;
            if(year > 2021){
                year = 1970;
            }

            switch (kind){
                case CAR : vehicles.add(new Car(model, yearOfManufacturing)); break;
                case BUS: vehicles.add(new Bus(model, yearOfManufacturing)); break;
                case TRUCK: vehicles.add(new Truck(model, yearOfManufacturing)); break;
            }
        }

        System.out.println("Showing created vehicles: ");
        for(Vehicle v : vehicles){
            System.out.println(v);
        }

        System.out.println();

        System.out.println("Setting vehicles to drivers...");
        for(Driver driver : drivers){
            for (int i = 0; i < 10; i++) {
                Vehicle vehicle = vehicles.remove(0);
                driver.addVehicle(vehicle);
            }
        }

        System.out.println("Showing drivers with vehicles:");
        for(Driver driver : drivers){
            System.out.println(driver.getName() + ":");
            for(Vehicle vehicle : driver.getVehicles()){
                System.out.println("    " + vehicle);
            }
            System.out.println();
        }

        System.out.println("Drivers buying vignettes...");
        for (int i = 0; i < drivers.size(); i++) {
            Driver driver = drivers.get(i);
            if((i+1) % 3 == 0){
                int countOfBought = 0;

                while(countOfBought < 5){
                    int vehicleNum = Generator.generateRandomNumber(0, 10);
                    //System.out.println("Generated number: " + vehicleNum);
                    /*
                    int counter = 0;
                    Vehicle vehicle = null;
                    for(Iterator<Vehicle> iterator = driver.getVehicles().iterator(); iterator.hasNext(); ){
                        vehicle = iterator.next();
                        if(counter == vehicleNum) {
                            break;
                        }
                        counter++;
                    }

                    if(vehicle.hasVignette()){
                        continue;
                    }
                     */

                    if(driver.buyVignetteForAVehicle(vehicleNum, Generator.generatePeriodType())) {
                        System.out.println("BOUGHT");
                        countOfBought++;
                    }
                }
                System.out.println(driver.getName() + " bought " + countOfBought + " vignettes");
            }
            else{
                driver.buyVignetteForAllVehicles();
            }

        }

        System.out.println();

        System.out.println("Showing info for drivers: ");
        for(Driver driver : drivers){
            driver.showInfo();
        }

/*
        System.out.println("Printing all remaining vignettes sorted by price: ");
        for(Vignette vignette : gasStation.getSortedVignettes()){
            System.out.println(vignette);
        }

        */

        System.out.println();
        System.out.println("Count of available vignettes after selling: " + gasStation.getSortedVignettes().size());
        System.out.println();

        LocalDate localDate = LocalDate.of(2021, Generator.generateRandomNumber(1, 12), Generator.generateRandomNumber(1, 28));
        System.out.println("Showing trucks with vignettes expired for date " + localDate);
        int countOfExpired = 0;
        for(Vehicle vehicle : vehicles){
            if(vehicle.getKing() == Vehicle.Kind.TRUCK){
                if(vehicle.isVignetteExpiredAt(localDate)){
                    countOfExpired++;
                    System.out.println(vehicle);
                    System.out.println("Vignette expired in: " + vehicle.getVignette().getExpireDate());
                    System.out.println();
                }
            }
        }

        if(countOfExpired == 0){
            System.out.println("There are no trucks with vignettes expired for " + localDate);
        }

        //System.out.println("Showing available vignettes: ");
        //gasStation.showAvailableVignettes();

        System.out.println("Count of available vignettes in original list after selling: " + gasStation.getVignettesCount());

        System.out.println("Showing drivers with vehicles:");
        for(Driver driver : drivers){
            System.out.println(driver.getName() + ":");
            for(Vehicle vehicle : driver.getVehicles()){
                System.out.println("    " + vehicle);
            }
            System.out.println();
        }
    }
}
