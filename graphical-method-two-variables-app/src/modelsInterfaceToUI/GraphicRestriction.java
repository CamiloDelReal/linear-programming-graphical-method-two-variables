package modelsInterfaceToUI;

import javafx.collections.FXCollections;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import models.Restriction;

/**
 * Created with IntelliJ IDEA.
 * User: DarkLink
 * Date: 23/10/13
 * Time: 21:25
 */

public class GraphicRestriction implements Comparable<GraphicRestriction>{

    public static enum Var {
        X1_AXIS, X2_AXIS, NONE
    }

    private Restriction rest;
    private LineChart.Series serie;
    private LineChart.Data point1;
    private LineChart.Data point2;
    private Var possibleToIncrese;
    //private AreaChart.Data toIncrese;

    public GraphicRestriction(double x1, double x2, String simbol, double pd) {
        //Guardar la restriccion introducida por el usuario
        rest = new Restriction(x1, x2, simbol, pd);

        //Armar los puntos que conformaran la funcion lineal en el grafico
        //Para el caso ax1 <=> pd
        if(x2 == 0) {
            point1 = new LineChart.Data((pd / x1), 0);
            point2 = new LineChart.Data((pd / x1), Manager.getMaxValueX2());
            possibleToIncrese = Var.X2_AXIS;
        }
        //Para el caso bx2 <=> pd
        else if(x1 == 0) {
            point1 = new LineChart.Data(0, (pd / x2));
            point2 = new LineChart.Data(Manager.getMaxValueX1(), (pd / x2));
            possibleToIncrese = Var.X1_AXIS;
        }
        //Para el caso ax1 + bx2 <=> pd
        else {
            point1 = new LineChart.Data((pd / x1), 0);
            point2 = new LineChart.Data(0, (pd / x2));
            possibleToIncrese = Var.NONE;
        }

        //Inicializacion de la serie a partir de los puntos generados
        serie = new LineChart.Series(rest.toString(), FXCollections.observableArrayList(point1, point2));
    }

    public Restriction getRestriction() {
        return rest;
    }

    public LineChart.Series getSerie() {
        return serie;
    }

    public Var getPossibleToIncrese() {
        return possibleToIncrese;
    }

    public void adjustMaxValues() {
        if(possibleToIncrese == Var.X1_AXIS) {
            point2.setXValue(Manager.getMaxValueX1());
        }
        else if(possibleToIncrese == Var.X2_AXIS) {
            point2.setYValue(Manager.getMaxValueX2());
        }
    }

    public String toString() {
        return rest.toString();
    }


    @Override
    public int compareTo(GraphicRestriction o) {
        return rest.compareTo(o.getRestriction());
    }
}
