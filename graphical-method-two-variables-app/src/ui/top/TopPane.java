package ui.top;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.util.Duration;
import main.Main;
import ui.center.CenterPane;

/**
 * Created with IntelliJ IDEA.
 * User: DarkLink
 * Date: 20/10/13
 * Time: 7:59
 */

public class TopPane extends HBox{
    private static TopPane pane;

    private static double DURATION_GLUR_TO_NORMAL = 300;
    private static double DURATION_NORMAL_TO_GLUR = 300;

    private HBox buttons;
    private ToolButton btnNew;
    private ToolButton btnAbout;

    private OwnerToolTip tips;
    private boolean showingTooltips = false;

    private double difference = (OwnerToolTip.PREF_WIDTH - ToolButton.PREF_WIDTH) / 2;

    private static double speed = 100;

    public static TopPane getInstance() {
        if(pane == null)
            pane = new TopPane();
        return pane;
    }

    private TopPane() {
        setId("TopPane");

        buttons = new HBox();
        buttons.setSpacing(10);
        buttons.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent e){
                tips.hide();
                showingTooltips = false;
            }
        });
        tips = new OwnerToolTip("Nada", "Nada");
        tips.setAutoHide(true);
        buttons.getChildren().addAll(getBtnNew(), getBtnAbout());

        Region spacer1 = new Region();
        HBox.setHgrow(spacer1, Priority.ALWAYS);
        Region spacer2 = new Region();
        spacer2.setMinWidth(80);
        spacer2.setMaxWidth(80);
        spacer2.setPrefWidth(80);

        setSpacing(3);
        getChildren().addAll(spacer1, buttons, spacer2);
    }

    private void showToolTips(MouseEvent e){
        double posInButtonX = e.getX();
        double posInButtonY = e.getY();

        double posInScreenX = e.getScreenX();
        double posInScreenY = e.getScreenY();

        difference = (OwnerToolTip.PREF_WIDTH - ToolButton.PREF_WIDTH) / 2;

        posInScreenX -= posInButtonX + difference - 10;
        posInScreenY -= posInButtonY - 85;

        tips.show(Main.getScene().getWindow(), posInScreenX, posInScreenY);
    }

    private ToolButton getBtnNew() {
        if(btnNew == null)
        {
            btnNew = new ToolButton();
            btnNew.setId("btnNew");
            final Timeline btnNewToNormal = new Timeline();
            btnNewToNormal.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(btnNew.setOpacityProperty(), 0.7)),
                    new KeyFrame(Duration.millis(DURATION_GLUR_TO_NORMAL), new KeyValue(btnNew.setOpacityProperty(), 1.0))
            );
            final Timeline btnNewToGlur = new Timeline();
            btnNewToGlur.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(btnNew.setOpacityProperty(), 1.0)),
                    new KeyFrame(Duration.millis(DURATION_NORMAL_TO_GLUR), new KeyValue(btnNew.setOpacityProperty(), 0.7))
            );
            btnNew.setOnMouseEntered(new EventHandler<MouseEvent>(){
                public void handle(MouseEvent e){
                    btnNewToNormal.play();

                    tips.setTitle("Nuevo");
                    tips.setText("Hay que redactarlo. Hay que redactarlo. Hay que redactarlo. Hay que redactarlo. Hay que redactarlo");

                    if(!showingTooltips){
                        showToolTips(e);
                        showingTooltips = true;
                    }
                    else{
                        double posInButtonX = e.getX();
                        double posInScreenX = e.getScreenX();

                        Timeline moveTo = new Timeline();
                        moveTo.getKeyFrames().addAll(
                                new KeyFrame(Duration.ZERO, new KeyValue(tips.getPositionX(), tips.getX())),
                                new KeyFrame(Duration.millis(speed), new KeyValue(tips.getPositionX(), posInScreenX - posInButtonX - difference))
                        );
                        moveTo.play();
                    }
                }
            });
            btnNew.setOnMouseExited(new EventHandler<MouseEvent>(){
                public void handle(MouseEvent e){
                    btnNewToGlur.play();
                }
            });
            btnNew.setOnAction(new EventHandler<ActionEvent>(){
                public void handle(ActionEvent e){
                    CenterPane.getInstance().initializeAll();
                }
            });
        }

        return btnNew;
    }

    private ToolButton getBtnAbout() {
        if(btnAbout == null)
        {
            btnAbout = new ToolButton();
            btnAbout.setId("btnAbout");
            final Timeline btnAboutToNormal = new Timeline();
            btnAboutToNormal.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(btnAbout.setOpacityProperty(), 0.7)),
                    new KeyFrame(Duration.millis(DURATION_GLUR_TO_NORMAL), new KeyValue(btnAbout.setOpacityProperty(), 1.0))
            );
            final Timeline btnAboutToGlur = new Timeline();
            btnAboutToGlur.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(btnAbout.setOpacityProperty(), 1.0)),
                    new KeyFrame(Duration.millis(DURATION_NORMAL_TO_GLUR), new KeyValue(btnAbout.setOpacityProperty(), 0.7))
            );
            btnAbout.setOnMouseEntered(new EventHandler<MouseEvent>(){
                public void handle(MouseEvent e){
                    btnAboutToNormal.play();

                    tips.setTitle("Acerca de..");
                    tips.setText("Hay que redactarlo. Hay que redactarlo. Hay que redactarlo. Hay que redactarlo. Hay que redactarlo");

                    if(!showingTooltips){
                        showToolTips(e);
                        showingTooltips = true;
                    }
                    else{
                        double posInButtonX = e.getX();
                        double posInScreenX = e.getScreenX();

                        Timeline moveTo = new Timeline();
                        moveTo.getKeyFrames().addAll(
                                new KeyFrame(Duration.ZERO, new KeyValue(tips.getPositionX(), tips.getX())),
                                new KeyFrame(Duration.millis(speed), new KeyValue(tips.getPositionX(), posInScreenX - posInButtonX - difference))
                        );
                        moveTo.play();
                    }
                }
            });
            btnAbout.setOnMouseExited(new EventHandler<MouseEvent>(){
                public void handle(MouseEvent e){
                    btnAboutToGlur.play();
                }
            });
            btnAbout.setOnAction(new EventHandler<ActionEvent>(){
                public void handle(ActionEvent e){
                    //showingTooltips = false;

                }
            });
        }

        return btnAbout;
    }

}
