package models;

/**
 * Created with IntelliJ IDEA.
 * User: DarkLink
 * Date: 20/10/13
 * Time: 9:34
 */

public class Restriction implements Comparable<Restriction>{
    private double x1;
    private double x2;
    private String simbol;
    private double pd;

    public Restriction(double x1, double x2, String simbol, double pd) {
        this.x1 = x1;
        this.x2 = x2;
        this.simbol = simbol;
        this.pd = pd;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public String getSimbol() {
        return simbol;
    }

    public void setSimbol(String simbol) {
        this.simbol = simbol;
    }

    public double getPd() {
        return pd;
    }

    public void setPd(double pd) {
        this.pd = pd;
    }

    public String toString() {
        String value =  "";
        if(x1 != 0)
            value += String.valueOf(x1) + "X1";
        if(x2 > 0)
            value += " + " + String.valueOf(x2) + "X2";
        else if(x2 < 0)
            value += " - " + String.valueOf((x2 * -1)) + "X2";
        value += " " + simbol;
        if(pd > 0)
            value += String.valueOf(pd);
        else if(pd < 0)
            value += " - " + String.valueOf((pd * -1));

        return value;
    }

    @Override
    public int compareTo(Restriction o) {
        int value = 1;

        if(x1 == o.getX1() && x2 == o.getX2() && simbol.equals(o.getSimbol()) && pd == o.getPd())
            value = 0;  //Sin iguales;
        return value;
    }
}
