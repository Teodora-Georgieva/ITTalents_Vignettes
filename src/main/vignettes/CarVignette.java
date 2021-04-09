package main.vignettes;

public class CarVignette extends Vignette{
    private static final String color = "GREEN";

    public CarVignette(PeriodType periodType) {
        super(periodType);
    }

    @Override
    int getDayPrice() {
        return 5;
    }

    @Override
    public int stickVignette() {
        System.out.println("Sticking " + this.getPeriodType() + " vignette on a car...");
        return 5;
    }

    @Override
    String getColor() {
        return color;
    }
}
