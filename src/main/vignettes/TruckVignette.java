package main.vignettes;

public class TruckVignette extends Vignette{
    private static final String color = "BLUE";

    public TruckVignette(PeriodType periodType) {
        super(periodType);
    }

    @Override
    int getDayPrice() {
        return 7;
    }

    @Override
    public int stickVignette() {
        System.out.println("Sticking " + this.getPeriodType() + " vignette on a truck...");
        return 10;
    }

    @Override
    String getColor() {
        return color;
    }
}
