package main.vignettes;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public abstract class Vignette implements Comparable<Vignette>{
    public enum PeriodType{
        DAY, MONTH, YEAR
    }

    private LocalDate issueDate;
    private LocalDate expireDate;
    private PeriodType periodType;

    public Vignette(PeriodType periodType){
        this.periodType = periodType;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public void setExpireDate() {
        switch (periodType){
            case DAY: this.expireDate = this.issueDate.plus(Period.ofDays(1)); break;
            case MONTH: this.expireDate = this.issueDate.plus(Period.ofMonths(1)); break;
            default: this.expireDate = this.issueDate.plus(Period.ofYears(1)); break;
        }
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public int getPrice(){
        PeriodType periodType = this.getPeriodType();

        switch (periodType){
            case DAY: return getDayPrice();
            case MONTH:
                return getDayPrice()*10;
            default:
                return getDayPrice()*60;
        }
    }

    abstract int getDayPrice();
    public abstract int stickVignette();

    public PeriodType getPeriodType() {
        return periodType;
    }

    abstract String getColor();

    @Override
    public int compareTo(Vignette o) {
        if(this.getPrice() - o.getPrice() == 0){
            return 1;
        }

        return this.getPrice() - o.getPrice();
    }

    @Override
    public String toString() {
        return "kind: " + getClass() + ", periodType = " + periodType + ", color: " + getColor() + ", price: " + getPrice();
    }
}