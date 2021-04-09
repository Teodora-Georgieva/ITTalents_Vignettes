package main.drivers;

import main.GasStation;
import main.utils.Generator;
import main.utils.Validator;
import main.vehicles.Vehicle;
import main.vignettes.Vignette;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class Driver{
    private String name;
    private double money;
    private GasStation gasStation;
    private LinkedHashSet<Vehicle> vehicles;

    public Driver(String name, double money, GasStation gasStation) {
        if(Validator.isValidString(name)) {
            this.name = name;
        }
        else{
            this.name = "Ivan Ivanov";
        }

        if(money > 0) {
            this.money = money;
        }
        else{
            this.money = 500;
        }

        this.gasStation = gasStation;
        this.vehicles = new LinkedHashSet<>();
    }

    public void addVehicle(Vehicle vehicle){
        this.vehicles.add(vehicle);
    }

    public void buyVignetteForAllVehicles(){
        Vignette.PeriodType periodType = Generator.generatePeriodType();
        for(Vehicle vehicle : this.vehicles){
            if(vehicle.hasVignette()){
                System.out.println("This vehicle already has a vignette ALL VEHICLES!");
                continue;
            }

            Vignette vignette = this.gasStation.sellVignette(vehicle.getKing(), periodType, this);
            if(vignette == null){
                return;
            }
            this.stickVignetteToVehicle(vehicle, vignette);
        }
    }

    public double getMoney() {
        return money;
    }

    public void decreaseMoney(int amount){
        if(amount <= this.money){
            this.money -= amount;
        }
    }
    public String getName() {
        return name;
    }

    public boolean buyVignetteForAVehicle(int vehicleNum, Vignette.PeriodType periodType){
        if(vehicleNum < 0 || vehicleNum > this.vehicles.size()){
            System.out.println("Invalid vehicle number!");
            return false;
        }

        Vehicle vehicle = null;
        int counter = 0;
        for(Iterator<Vehicle> it = this.vehicles.iterator(); it.hasNext(); ){
            vehicle = it.next();
            if(counter == vehicleNum){
                break;
            }
            counter++;
        }

        if(vehicle.hasVignette()){
            System.out.println("This vehicle already has a vignette!");
            return false;
        }

        Vignette vignette = this.gasStation.sellVignette(vehicle.getKing(), periodType, this);
        if(vignette == null){
            return false;
        }
        this.stickVignetteToVehicle(vehicle, vignette);
        return true;
    }

    public void getListWithExpiredVehicles(LocalDate date){
        System.out.println("Showing list with vehicles with expired vignettes on date " + date + ":");
        boolean hasExpiredVehicles = false;
        for(Vehicle v : this.vehicles){
            if(LocalDate.now().isAfter(v.getVignette().getExpireDate())){
                hasExpiredVehicles = true;
                System.out.println(v + " , vignette expired at: " + v.getVignette().getExpireDate());
            }
        }
        if(!hasExpiredVehicles){
            System.out.println("All vehicles of " + this.name + " are with valid vignettes");
        }
        System.out.println();
    }

    private int stickVignetteToVehicle(Vehicle vehicle, Vignette vignette){
        return vehicle.stickVignette(vignette);
    }

    public LinkedHashSet<Vehicle> getVehicles() {
        return vehicles;
    }

    public void showInfo() {
        System.out.println(this.name + " has " + this.vehicles.size() + " vehicles and is left with " +
                this.money + " money");
        LocalDate localDate = LocalDate.of(2021, Generator.generateRandomNumber(1, 12), Generator.generateRandomNumber(1, 28));
        System.out.println("Showing vehicles with expired vignettes on " + localDate);
        int countOfExpired = 0;
        for(Vehicle v : this.vehicles){
            if(!v.hasVignette()){
                System.out.println("This vehicle has no vignette");
            }
            else if(v.isVignetteExpiredAt(localDate)){
                countOfExpired++;
                System.out.println(v);
                System.out.println("Vignette expired at: " + v.getVignette().getExpireDate());
                System.out.println();
            }
            else{
                System.out.println("The vignette hasn't expired at " + localDate);
            }
        }

        System.out.println("Count of expired: " + countOfExpired);
        System.out.println();
    }
}