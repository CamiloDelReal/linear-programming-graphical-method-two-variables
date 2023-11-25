package models;

/**
 * Created with IntelliJ IDEA.
 * User: DarkLink
 * Date: 23/10/13
 * Time: 22:39
 */

public class ObjectiveFunction {
    private double x1;
    private double x2;
    private String type;

    public ObjectiveFunction(double x1, double x2, String type) {
        this.x1 = x1;
        this.x2 = x2;
        this.type = type;
    }

    public ObjectiveFunction() {
        this.x1 = 0;
        this.x2 = 0;
        this.type = "";
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
