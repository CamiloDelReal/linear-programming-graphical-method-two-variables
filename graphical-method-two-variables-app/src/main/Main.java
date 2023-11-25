package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import models.Restriction;
import ui.ContentPane;

/**
 * Created with IntelliJ IDEA.
 * User: DarkLink
 * Date: 20/10/13
 * Time: 8:08
 */

public class Main extends Application {

    private static Scene rootScene;
    private static StackPane rootPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        rootPane = new StackPane();
        rootPane.setId("");
        rootPane.getChildren().addAll(ContentPane.getInstance());

        rootScene = new Scene(rootPane, 1024, 680);
        rootScene.getStylesheets().add(getClass().getResource("/ui/css/main_style.css").toExternalForm());

        primaryStage.setScene(rootScene);
        primaryStage.setTitle("ModIO");
        primaryStage.show();

        Restriction r = new Restriction(4, 9, "<=", 90);
        System.out.println(r);
    }

    public static Scene getScene() {
        return rootScene;
    }

    public static StackPane getPane() {
        return rootPane;
    }
}
