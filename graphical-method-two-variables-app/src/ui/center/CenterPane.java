package ui.center;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import main.Main;
import models.Simplex;
import models.Simplex_Incompleto;
import modelsInterfaceToUI.GraphicRestriction;
import modelsInterfaceToUI.Manager;
import modelsInterfaceToUI.exceptions.RepetedRestrictionException;
import ui.util.Utilities;

/**
 * Created with IntelliJ IDEA.
 * User: DarkLink
 * Date: 20/10/13
 * Time: 8:24
 */

public class CenterPane extends BorderPane {
    private static CenterPane pane;

    private static final String INSERT = "Insertar";
    private static final String MODIFY = "Modificar";
    private static final String DELETE = "Eliminar";
    private static final String SAVE = "Guardar";
    private static final String CANCEL = "Cancelar";

    private VBox panelLeft;

    //Semantica
    private Label titleSemantic;
    private Label labX1;
    private Label labX2;
    private TextField txtX1;
    private TextField txtX2;

    //Funcion Objetivo
    private Label titleFO;
    private ChoiceBox<String> typeFO;
    private Label z;
    private Label labx1fo;
    private Label labx2fo;
    private NumberField x1fo;
    private NumberField x2fo;
    //private Button btnfo;

    //Restricciones
    private Label titleRestric;
    private Label labx1res;
    private Label labx2res;
    private NumberField fieldX1;
    private NumberField fieldX2;
    private ChoiceBox<String> simbol;
    private NumberField pd;

    //Botones
    private Button btnNewRestr;
    private Button btnDelRestr;

    //Graphic
    ObservableList<AreaChart.Series> chartData;
    LineChart chart;
    NumberAxis xAxis;
    NumberAxis yAxis;

    //Lists con el conjunto de restricciones
    private ListView<GraphicRestriction> listRestr;

    //Calculo
    private Button btnCalculo;

    public static CenterPane getInstance() {
        if(pane == null)
            pane = new CenterPane();
        return pane;
    }

    private CenterPane() {
        setId("CenterPane");

        panelLeft = new VBox();
        panelLeft.setId("PanelLeft");
        panelLeft.setSpacing(5);
        panelLeft.setMinWidth(335);
        panelLeft.setMaxWidth(335);
        panelLeft.setPrefWidth(335);

        //Valores Semanticos de las variables
        titleSemantic = new Label("Valores semánticos");
        titleSemantic.setId("Title");
        titleSemantic.setMinWidth(318);
        titleSemantic.setAlignment(Pos.CENTER);
        labX1 = new Label("X1: ");
        labX1.getStyleClass().add("tagX");
        txtX1 = new TextField();
        txtX1.setMinWidth(292);
        txtX1.setMaxWidth(292);
        txtX1.setPrefWidth(292);
        txtX1.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                xAxis.setLabel(txtX1.getText()+keyEvent.getCharacter());
            }
        });
        HBox semX1 = new HBox();
        semX1.getChildren().addAll(labX1, txtX1);

        labX2 = new Label("X2: ");
        labX2.getStyleClass().add("tagX");
        txtX2 = new TextField();
        txtX2.setMinWidth(292);
        txtX2.setMaxWidth(292);
        txtX2.setPrefWidth(292);
        txtX2.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                yAxis.setLabel(txtX2.getText()+keyEvent.getCharacter());
            }
        });
        HBox semX2 = new HBox();
        semX2.getChildren().addAll(labX2, txtX2);

        panelLeft.getChildren().addAll(titleSemantic, semX1, semX2);
        //Fin valores semanticos de las variables

        //Funcion objetivo
        titleFO = new Label("Función objetivo");
        titleFO.setId("Title");
        titleFO.setMinWidth(318);
        titleFO.setAlignment(Pos.CENTER);

        typeFO = new ChoiceBox<String>();
        typeFO.getItems().addAll("Max", "Min");
        typeFO.getSelectionModel().selectFirst();
        typeFO.setMinSize(55, 24);
        typeFO.setMaxSize(55, 24);
        typeFO.setPrefSize(55, 24);
        z = new Label("Z = ");
        z.getStyleClass().add("tagx");
        x1fo = new NumberField();
        x1fo.setMinSize(85, 24);
        x1fo.setMaxSize(85, 24);
        x1fo.setPrefSize(85, 24);

        x2fo = new NumberField();
        x2fo.setMinSize(85, 24);
        x2fo.setMaxSize(85, 24);
        x2fo.setPrefSize(85, 24);

        labx1fo = new Label("X1");
        labx1fo.getStyleClass().add("tagx");
        labx2fo = new Label("X2");
        labx2fo.getStyleClass().add("tagx");

        HBox foBox = new HBox();

        Region spacer9 = new Region();
        Region spacer10 = new Region();
        HBox.setHgrow(spacer9, Priority.ALWAYS);
        HBox.setHgrow(spacer10, Priority.ALWAYS);
        HBox aux2 = new HBox();
        aux2.getStyleClass().add("fobox");
        aux2.setSpacing(2);
        aux2.setMinSize(319, 28);
        aux2.setMaxSize(319, 28);
        aux2.setPrefSize(319, 28);

        aux2.getChildren().addAll(typeFO, z, x1fo, labx1fo, x2fo, labx2fo);

        foBox.getChildren().addAll(spacer9, aux2, spacer10);

        panelLeft.getChildren().addAll(titleFO, foBox);

       /* btnfo = new Button("Guardar");
        btnfo.setMinWidth(80);
        btnfo.setMaxWidth(80);
        btnfo.setPrefWidth(80);
        btnfo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Validar

                Manager.getInstance().setObjectiveFunction(
                        Double.parseDouble(x1fo.getText()),
                        Double.parseDouble(x2fo.getText()),
                        typeFO.getValue()
                    );
            }
        });  */

        HBox aux4 = new HBox();
        Region spacer15 = new Region();
        HBox.setHgrow(spacer15, Priority.ALWAYS);
        Region spacer16 = new Region();
        spacer16.setMinWidth(6);
        spacer16.setMaxWidth(6);
        spacer16.setPrefWidth(6);

        //aux4.getChildren().addAll(spacer15, btnfo, spacer16);

        panelLeft.getChildren().add(aux4);
        //Fin funcion objetivo

        //Conjunto de restricciones
        HBox terms = new HBox();
        terms.getStyleClass().add("termsBox");
        terms.setSpacing(2);
        terms.setMinSize(319, 28);
        terms.setMaxSize(319, 28);
        terms.setPrefSize(319, 28);

        fieldX1 = new NumberField();
        fieldX1.setMinSize(75, 24);
        fieldX1.setMaxSize(75, 24);
        fieldX1.setPrefSize(75, 24);
        labx1res = new Label("X1");
        labx1res.getStyleClass().add("tagx");

        fieldX2 = new NumberField();
        fieldX2.setMinSize(75, 24);
        fieldX2.setMaxSize(75, 24);
        fieldX2.setPrefSize(75, 24);
        labx2res = new Label("X2");
        labx2res.getStyleClass().add("tagx");

        simbol = new ChoiceBox<String>();
        simbol.getItems().addAll("<=", "<", ">=", ">", "=");
        simbol.getSelectionModel().selectFirst();
        simbol.setMinSize(45, 24);
        simbol.setMaxSize(45, 24);
        simbol.setPrefSize(45, 24);

        pd = new NumberField();
        pd.setMinSize(75, 24);
        pd.setMaxSize(75, 24);
        pd.setPrefSize(75, 24);

        Region spacer1 = new Region();
        Region spacer2 = new Region();
        HBox.setHgrow(spacer1, Priority.ALWAYS);
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        terms.getChildren().addAll( fieldX1, labx1res, fieldX2, labx2res, simbol, pd);

        HBox aux = new HBox();
        aux.getChildren().addAll(spacer1, terms, spacer2);

        titleRestric = new Label("Conjunto de restricciones");
        titleRestric.setId("Title");
        titleRestric.setMinWidth(318);
        titleRestric.setAlignment(Pos.CENTER);

        panelLeft.getChildren().addAll(titleRestric, aux);
        //Fin al conjunto de resticciones

        //Boton Añadir restricciones
        HBox buttons = new HBox();
        btnNewRestr = new Button(INSERT);
        btnNewRestr.setMinWidth(102);
        btnNewRestr.setMaxWidth(102);
        btnNewRestr.setPrefWidth(102);

        btnNewRestr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Validaciones
                if( (Utilities.isEmpty(fieldX1.getText()) && Utilities.isEmpty(fieldX2.getText())) || Utilities.isEmpty(pd.getText())) {
                    DialogBox box = new DialogBox("Debe introducir al menos un campos de la ecuación restrictiva, y su parte real");
                    box.show();
                }
                else {
                    if(Utilities.isEmpty(fieldX1.getText()))
                        fieldX1.setText(String.valueOf(0));
                    else if(Utilities.isEmpty(fieldX2.getText()))
                        fieldX2.setText(String.valueOf(0));

                    //Guardar los datos
                    GraphicRestriction gr = null;
                    try {
                        gr = Manager.getInstance().addRestriction(
                                Double.parseDouble(fieldX1.getText()),
                                Double.parseDouble(fieldX2.getText()),
                                simbol.getValue(),
                                Double.parseDouble(pd.getText())
                        );

                        //Actualizar la lista
                        listRestr.getItems().add(gr);
                        //Actualizar el grafico
                        chart.getData().add(gr.getSerie());

                        //Vaciar los componentes
                        fieldX1.setText("");
                        fieldX2.setText("");
                        pd.setText("");
                    } catch (RepetedRestrictionException repetedRestriction) {
                        DialogBox diag = new DialogBox(repetedRestriction.getMessage());
                        diag.show();
                    }
                }
            }
        });


        btnDelRestr = new Button(DELETE);
        btnDelRestr.setMinWidth(102);
        btnDelRestr.setMaxWidth(102);
        btnDelRestr.setPrefWidth(102);
        btnDelRestr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GraphicRestriction gr = listRestr.getSelectionModel().getSelectedItem();

                if(gr == null) {
                    DialogBox diag = new DialogBox("Debe seleccionar una restricción de la lista");
                    diag.show();
                }
                else {
                    DeleteBox box = new DeleteBox("Realmente desea eliminar\n" + gr, gr);
                    box.show();
                }
            }
        });

        Region spacer3 = new Region();
        HBox.setHgrow(spacer3, Priority.ALWAYS);
        Region spacer4 = new Region();
        HBox.setHgrow(spacer4, Priority.ALWAYS);

        buttons.getChildren().addAll(spacer3, btnNewRestr, btnDelRestr, spacer4);
        panelLeft.getChildren().add(buttons);
        //Fin en la creacion del boton de añadir restricciones

        //Lista de restricciones
        HBox list = new HBox();
        listRestr = new ListView<GraphicRestriction>();
        listRestr.setMinSize(318, 200);
        listRestr.setMaxWidth(318);
        listRestr.setPrefSize(318, 200);
        //listRestr.getItems().addAll(Manager.getInstance().getRestrictions());

        Region spacer5 = new Region();
        HBox.setHgrow(spacer5, Priority.ALWAYS);
        Region spacer6 = new Region();
        HBox.setHgrow(spacer6, Priority.ALWAYS);

        list.getChildren().addAll(spacer5, listRestr, spacer6);
        panelLeft.getChildren().add(list);
        //Fin lista de restricciones

        //Boton de calculo del punto optimo
        btnCalculo = new Button("Calcular");
        btnCalculo.setMinSize(318, 30);
        btnCalculo.setMaxSize(318, 30);
        btnCalculo.setPrefSize(318, 30);
        btnCalculo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Calcular Simplex");
                //Simplex_Incompleto sim = new Simplex_Incompleto(Manager.getInstance().getRestrictionsList(), Manager.getInstance().getObjecttiveFunction());

                if(Utilities.isEmpty(x1fo.getText()) || Utilities.isEmpty(x2fo.getText()) || listRestr.getItems().size() == 0) {
                    DialogBox box = new DialogBox("Debe introducir ambos campos de la Función Objetivo y al menos un requerimiento");
                    box.show();
                }
                else {

                    try{
                        Manager.getInstance().setObjectiveFunction(
                                Double.parseDouble(x1fo.getText()),
                                Double.parseDouble(x2fo.getText()),
                                typeFO.getValue()
                        );

                        double[][] data = Manager.getInstance().getMatrix();
                        for(int i = 0; i < data.length; i++){
                            System.out.println(data[i][0] + " " + data[i][1]);
                        }

                        double[] pds = Manager.getInstance().getPD();
                        for(int u = 0; u < pds.length; u++)
                            System.out.print(pds[u] + " ");


                        double[] coefFO = Manager.getInstance().getCoeficientesFO();
                        for(int u = 0; u < coefFO.length; u++)
                            System.out.print(coefFO[u] + " ");

                        Simplex lp = new Simplex(data, pds, coefFO);

                        double[] res = lp.primal();

                        System.out.println("Primal: " + res[0] + "  " + res[1]);

                        //Dibujar Solucion
                        LineChart.Series serie;
                        double n = res[0] * Manager.getInstance().getObjecttiveFunction().getX1() + res[1] * Manager.getInstance().getObjecttiveFunction().getX2();
                        double y = (n / Manager.getInstance().getObjecttiveFunction().getX2());
                        double x = (n / Manager.getInstance().getObjecttiveFunction().getX1());
                        LineChart.Data point1 = new LineChart.Data(0, y);
                        LineChart.Data pointC = new LineChart.Data(res[0], res[1]);
                        LineChart.Data point2 = new LineChart.Data(x, 0);

                        serie = new LineChart.Series("FO", FXCollections.observableArrayList(point1, pointC, point2));
                        chart.getData().add(serie);
                        chart.setTitle(
                                "Solucion óptima\nX1 = " + Math.rint(res[0]) + " X2 = " + Math.rint(res[1]) + "\nZ = " + n
                        );
                    }
                    catch (Exception e) {
                        DialogBox box = new DialogBox("No se puede determinar la solucion para el sistema introducido");
                        box.show();
                    }

                }

            }
        });

        Region spacer7 = new Region();
        HBox.setHgrow(spacer7, Priority.ALWAYS);
        Region spacer8 = new Region();
        HBox.setHgrow(spacer8, Priority.ALWAYS);
        HBox auxCalculo = new HBox();
        auxCalculo.getChildren().addAll(spacer7, btnCalculo, spacer8);

        panelLeft.getChildren().add(auxCalculo);
        //Fin boton de calculo de punto optimo

        setLeft(panelLeft);

        //Inicializacion del grafico, los ejes y las listas de control de los datos del grafico
        xAxis = new NumberAxis();
        xAxis.setLabel("X1");
        yAxis = new NumberAxis();
        yAxis.setLabel("X2");

        chartData = FXCollections.observableArrayList();
        chart = new LineChart(xAxis, yAxis, chartData);
        //Fin del grafico, los ejes y las listas de control de los datos del grafico

        setCenter(chart);

    }

    private void refillListRestrictions() {
        listRestr.getItems().addAll(Manager.getInstance().getRestrictions());
    }
    private void emptyListRestrictions() {
        while(listRestr.getItems().size() > 0)
            listRestr.getItems().remove(0);
    }


    public void initializeAll() {
        System.out.println("Initialize all");

        //Limpiar el grafico
        while(chart.getData().size() > 0)
            chart.getData().remove(0);

        //Limpiar la lista
        while(listRestr.getItems().size() > 0)
            listRestr.getItems().remove(0);

        //Decirle al Manager que limpie la lista con las ecuciones

    }


    class DeleteBox extends VBox{
        private StackPane dialogPane;
        private String text;
        private Image picture = new Image(DialogBox.class.getResource("../img/alert.png").toExternalForm());
        private GraphicRestriction gr;

        public DeleteBox(String text, GraphicRestriction gr){
            this.text = text;
            dialogPane = new StackPane();
            dialogPane.setId("dialogRoot");

            setId("dialogo");
            setMinSize(280, 110);
            setPrefSize(280, 110);
            setMaxSize(280, 110);

            this.gr = gr;

            init();
        }

        private void init(){
            HBox content = new HBox();

            if(picture != null){
                ImageView view = new ImageView(picture);
                content.getChildren().add(view);
            }

            Label tag = new Label(text);
            tag.setWrapText(true);

            VBox textBox = new VBox();
            textBox.setTranslateX(8);
            Region spacer1 = new Region();
            spacer1.setPrefHeight(15);
            Region spacer2 = new Region();
            VBox.setVgrow(spacer2, Priority.ALWAYS);

            textBox.getChildren().addAll(spacer1, tag, spacer2);

            content.getChildren().add(textBox);

            Region separation = new Region();
            VBox.setVgrow(separation, Priority.ALWAYS);

            HBox regButton = new HBox();

            Region spacer4 = new Region();
            HBox.setHgrow(spacer4, Priority.ALWAYS);

            Button yes = new Button("Si");
            yes.setDefaultButton(true);
            yes.setMinSize(70, 20);
            yes.setPrefSize(70, 20);
            yes.setMaxSize(70, 20);
            yes.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    listRestr.getItems().remove(gr);
                    chart.getData().remove(gr.getSerie());
                    Manager.getInstance().deleteRestriction(gr);

                    dialogPane.getChildren().remove(DeleteBox.this);
                    if(dialogPane.getChildren().isEmpty())
                        dialogPane.setVisible(false);

                    System.out.print(Manager.getInstance().getRestrictions().size());
                }
            });

            Button close = new Button("No");
            close.setDefaultButton(true);
            close.setMinSize(70, 20);
            close.setPrefSize(70, 20);
            close.setMaxSize(70, 20);
            close.setOnAction(new EventHandler<ActionEvent>(){
                public void handle(ActionEvent e){
                    dialogPane.getChildren().remove(DeleteBox.this);
                    if(dialogPane.getChildren().isEmpty())
                        dialogPane.setVisible(false);
                }
            });

            regButton.getChildren().addAll(spacer4, yes, close);

            getChildren().addAll(content, separation, regButton);

            dialogPane.getChildren().add(DeleteBox.this);
        }

        public void show(){
            Main.getPane().getChildren().add(dialogPane);
        }

        public void setSize(double width, double height){
            setMinSize(width, height);
            setPrefSize(width, height);
            setMaxSize(width, height);
        }

    }
}
