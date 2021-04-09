package main;

import com.sun.source.tree.Tree;
import main.drivers.Driver;
import main.utils.Generator;
import main.vehicles.Vehicle;
import main.vignettes.BusVignette;
import main.vignettes.CarVignette;
import main.vignettes.TruckVignette;
import main.vignettes.Vignette;

import java.time.LocalDate;
import java.util.*;

public class GasStation {
    private double income;
    private TreeSet<Vignette> sortedVignettes;
    private HashMap<Vehicle.Kind, HashMap<Vignette.PeriodType, ArrayList<Vignette>>> vignettes;

    public GasStation() {
        this.vignettes = new HashMap<>();
        this.sortedVignettes = new TreeSet<>();
        this.generateVignettes();
        //this.sortedVignettes.addAll(this.vignettes.values().values());
    }

    private void generateVignettes() {
        for (int i = 0; i < 10000; i++) {
            Vehicle.Kind kind = Generator.generateKindOfVehicle();
            Vignette.PeriodType periodType = Generator.generatePeriodType();

            Vignette vignette;
            switch (kind) {
                case CAR:
                    vignette = new CarVignette(periodType);
                    break;
                case BUS:
                    vignette = new BusVignette(periodType);
                    break;
                default:
                    vignette = new TruckVignette(periodType);
                    break;
            }

            if (!this.vignettes.containsKey(kind)) {
                this.vignettes.put(kind, new HashMap<>());
            }

            if (!this.vignettes.get(kind).containsKey(periodType)) {
                this.vignettes.get(kind).put(periodType, new ArrayList<>());
            }

            this.vignettes.get(kind).get(periodType).add(vignette);
            this.sortedVignettes.add(vignette);
        }
    }

    public Vignette sellVignette(Vehicle.Kind wantedKind, Vignette.PeriodType wantedPeriodType, Driver driver) {
        Vignette vignette;
        boolean available = false;

        for (Vehicle.Kind kind : this.vignettes.keySet()) {
            if (kind == wantedKind) {
                HashMap<Vignette.PeriodType, ArrayList<Vignette>> vignettesFromThisKind = this.vignettes.get(kind);
                for (Vignette.PeriodType periodType : vignettesFromThisKind.keySet()) {
                    if (periodType == wantedPeriodType) {
                        ArrayList<Vignette> availableVignettes = vignettesFromThisKind.get(periodType);
                        if (availableVignettes.size() > 0) {
                            available = true;
                            vignette = availableVignettes.get(0);
                            if (vignette.getPrice() > driver.getMoney()) {
                                System.out.println("Driver " + driver.getName() + " doesn't have enough money to buy the vignette!");
                                return null;
                            }

                            availableVignettes.remove(vignette);
                            for(Iterator<Vignette> iterator = this.sortedVignettes.iterator(); iterator.hasNext();) {
                                if(iterator.next().equals(vignette)){
                                    System.out.println("EQUALS");
                                    iterator.remove();
                                    break;
                                }
                                /*
                                if (this.sortedVignettes.remove(vignette)) {
                                    System.out.println("REMOVED FROM TREESET");
                                } else {
                                    System.out.println("NOT REMOVED FROM TREESET");
                                }

                                 */
                            }

                            vignette.setIssueDate(LocalDate.now());
                            vignette.setExpireDate();
                            driver.decreaseMoney(vignette.getPrice());
                            this.income += vignette.getPrice();
                            System.out.println(periodType + " " + kind + " vignette is sold!");
                            return vignette;
                        }
                    }
                }
            }
        }

        if (!available) {
            System.out.println("There is no available " + wantedKind + " vignette for period " + wantedPeriodType);
        }

        return null;
    }

    public void showAvailableVignettes() {
        for (Vehicle.Kind kind : this.vignettes.keySet()) {
            System.out.println(kind + ":");
            HashMap<Vignette.PeriodType, ArrayList<Vignette>> hashMap = this.vignettes.get(kind);
            for (Map.Entry<Vignette.PeriodType, ArrayList<Vignette>> entry : hashMap.entrySet()) {
                System.out.println("    " + entry.getKey());
                for (Vignette vignette : entry.getValue()) {
                    System.out.println("        " + vignette);
                }
            }
            System.out.println();
        }
    }

    public TreeSet<Vignette> getSortedVignettes() {
        return sortedVignettes;
    }

    public int getVignettesCount() {
        int count = 0;
        for(HashMap<Vignette.PeriodType, ArrayList<Vignette>> hashMap : this.vignettes.values()){
            for(ArrayList<Vignette> arrayList : hashMap.values()){
                count+=arrayList.size();
            }
        }

        return count;
    }
}