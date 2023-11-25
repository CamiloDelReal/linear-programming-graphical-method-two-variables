package modelsInterfaceToUI;

import models.ObjectiveFunction;
import models.Restriction;
import modelsInterfaceToUI.exceptions.RepetedRestrictionException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: DarkLink
 * Date: 23/10/13
 * Time: 21:58
 */

public class Manager {
    private static Manager manage;
    public static Manager getInstance() {
        if (manage == null)
            manage = new Manager();
        return manage;
    }

    private static double maxValueX1 = 100;
    private static double maxValueX2 = 100;
    private boolean isFirtsTime = true;

    private List<GraphicRestriction> restns;
    private ObjectiveFunction fo;

    private Manager () {
        restns = new ArrayList<GraphicRestriction>();
        fo = new ObjectiveFunction();
    }

    public static double getMaxValueX1() {
        return maxValueX1;
    }
    public static double getMaxValueX2() {
        return maxValueX2;
    }

    public void setObjectiveFunction(double x1, double x2, String simbol) {
        fo.setX1(x1);
        fo.setX2(x2);
        fo.setType(simbol);
        System.out.println("FO: " + x1 + " " + x2 + " " + simbol);
    }

    public GraphicRestriction addRestriction(double x1, double x2, String simbol, double pd) throws RepetedRestrictionException {
        GraphicRestriction gr = new GraphicRestriction(x1, x2, simbol, pd);
        if(!isRepeted(gr)) {
            //Si la funcion tiene x1 y x2 se pueden determinar nuevos valores maximos para x1 y x2
            if(x1 != 0 && x2 != 0) {
                //Ajustar los nuevos valores maximos
                if(isFirtsTime) {
                    maxValueX1 = pd / x1;
                    maxValueX2 = pd / x2;
                    isFirtsTime = false;
                }
                else {
                    maxValueX1 = (maxValueX1 < (pd / x1)) ? (pd / x1) : maxValueX1;
                    maxValueX2 = (maxValueX2 < (pd / x2)) ? (pd / x2) : maxValueX2;
                }

                //Redimensionar las series ya introducidas a partir de los nuevos maximos
                for(GraphicRestriction g : restns) {
                    g.adjustMaxValues();
                }
            }

            restns.add(gr);
        }
        else
            throw new RepetedRestrictionException();

        return gr;
    }

    public void deleteRestriction(GraphicRestriction gr) {
        restns.remove(gr);

        double auxMaxX1 = 0;
        double auxMaxX2 = 0;
        for(GraphicRestriction g : restns) {
            if(g.getPossibleToIncrese() == GraphicRestriction.Var.NONE) {
                if(auxMaxX1 < (g.getRestriction().getPd() / g.getRestriction().getX1()))
                    auxMaxX1 = g.getRestriction().getPd() / g.getRestriction().getX1();
                if(auxMaxX2 < (g.getRestriction().getPd() / g.getRestriction().getX2()))
                    auxMaxX2 = g.getRestriction().getPd() / g.getRestriction().getX2();
            }
        }

        if(auxMaxX1 > 0 && auxMaxX2 > 0) {
            maxValueX1 = auxMaxX1;
            maxValueX2 = auxMaxX2;
        }

        //Redimensionar las series ya introducidas a partir de los nuevos maximos
        for(GraphicRestriction g : restns) {
            g.adjustMaxValues();
        }
    }

    public void emptyAll() {
        while(restns.size() > 0)
            restns.remove(0);
    }

    public List<Restriction> getRestrictionsList() {
        List<Restriction> l = new ArrayList<Restriction>();

        for(GraphicRestriction gr : restns)
            l.add(gr.getRestriction());

        return l;
    }
    public ObjectiveFunction getObjecttiveFunction() {
        return fo;
    }

    private boolean isRepeted(GraphicRestriction gr) {
        boolean is = false;
        Iterator<GraphicRestriction> it = restns.iterator();

        while (!false && it.hasNext())
            if(it.next().compareTo(gr) == 0)
                is = true;

        return is;
    }

    public List<GraphicRestriction> getRestrictions() {
        return restns;
    }


    public double[][] getMatrix() {
        double [][]matrix = new double[restns.size()][];

        for(int i = 0; i < restns.size(); i++) {
            matrix[i] = new double[2];

            matrix[i][0] = restns.get(i).getRestriction().getX1();
            matrix[i][1] = restns.get(i).getRestriction().getX2();
        }

        return matrix;
    }

    public double[] getCoeficientesFO() {
        double[] coef = new double[2];
        coef[0] = fo.getX1();
        coef[1] = fo.getX2();
        return coef;
    }

    public double[] getPD() {
        double[] pds = new double[restns.size()];

        for(int i = 0; i < restns.size(); i++) {
            pds[i] = restns.get(i).getRestriction().getPd();
        }

        return pds;
    }
}
