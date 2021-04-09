package main.vignettes;

public class BusVignette extends Vignette{
    private static final String color = "RED";

    public BusVignette(PeriodType periodType) {
        super(periodType);
    }

    @Override
    int getDayPrice() {
        return 9;
    }

    @Override
    public int stickVignette() {
        System.out.println("Sticking " + this.getPeriodType() + " vignette on a bus...");
        return 20;
    }

    @Override
    String getColor() {
        return color;
    }
}