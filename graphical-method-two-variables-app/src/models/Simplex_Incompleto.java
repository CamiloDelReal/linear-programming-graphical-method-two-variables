package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: DarkLink
 * Date: 24/10/13
 * Time: 3:45
 */

public class Simplex_Incompleto {
    private List<List<Object>> matrix;
    private int col;
    private int row;

    private double x1Sol;
    private double x2Sol;

    public Simplex_Incompleto(List<Restriction> rest, ObjectiveFunction fo) {
        System.out.println("Creando Simplex");

        col = 4 + rest.size();
        row = 2 + rest.size();

        //Inicializacion de la matrix
        matrix = new ArrayList<List<Object>> (row);

        for(int i = 0; i < row; i++)
            matrix.add( new ArrayList<Object>(col) );

        //Inicializar primera fila con todas las variables
        matrix.get(0).add("VB");
        matrix.get(0).add("X1");
        matrix.get(0).add("X2");
        for(int i = 3, j = 1; i < (col - 1); i++, j++)
            matrix.get(0).add("S" + j);
        matrix.get(0).add( "PD");

        //Inicializar la primera columna
        matrix.get(1).add("Z");
        for(int i = 2, j = 1; i < row; i++, j++)
            matrix.get(i).add("S" + j);

        //Test -- Satisfactorio
        /*for(int j = 0; j < col; j++) {
            System.out.print(matrix.get(0).get(j) + " " );
        }
        System.out.println();

        for(int j = 0; j < row; j++) {
            System.out.print(matrix.get(j).get(0) + " " );
        }*/

        //Inicializando fila de Z
        matrix.get(1).add(fo.getX1());
        matrix.get(1).add(fo.getX2());
        for(int i = 3; i < col; i++)
            matrix.get(1).add(new Double(0));

        //Inicializando los valores de las siguentes
        int u = 3;
        int k = 0;
        for(int i = 2; i < row; i++, k++, u++) {
            matrix.get(i).add(new Double(rest.get(k).getX1()));
            matrix.get(i).add(new Double(rest.get(k).getX2()));
            for(int j = 3; j < (col - 1); j++) {
                matrix.get(i).add(new Double(0));
            }
            matrix.get(i).set(u, new Double(1));
            matrix.get(i).add(new Double(rest.get(k).getPd()));
        }

        //Test -- Satisfactorio
        /*for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                System.out.print(matrix.get(i).get(j) + "\t");
            }
            System.out.println();
        }*/
    }


    public void iterate() {
        List<List<Object>> nueva;

        //Inicializacion de la matrix
        nueva = new ArrayList<List<Object>> (row);

        for(int i = 0; i < row; i++)
            nueva.add( new ArrayList<Object>(col) );

        //Inicializar primera fila con todas las variables
        nueva.get(0).add( "VB");
        nueva.get(0).add( "X1");
        nueva.get(0).add( "X2");
        for(int i = 3, j = 1; i < (col - 1); i++, j++)
            nueva.get(0).add("S" + j);
        nueva.get(0).add( "PD");

        //Determinar columna entrante
        int indexCol = colPivote(matrix);
        //Determinar fila saliente


    }

    private int colPivote(List<List<Object>> list) {
        int indexCol = 1;
        double min = (Double)list.get(1).get(1);
        for(int i = 2; i < col; i++)
            if((Double)list.get(1).get(i) < min){
                min = (Double)list.get(1).get(i);
                indexCol = i;
            }
        return indexCol;
    }
}
