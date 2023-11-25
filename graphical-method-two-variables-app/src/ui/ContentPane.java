package ui;

import javafx.scene.layout.BorderPane;
import ui.center.CenterPane;
import ui.top.TopPane;

/**
 * Created with IntelliJ IDEA.
 * User: DarkLink
 * Date: 20/10/13
 * Time: 7:56
 */

public class ContentPane extends BorderPane {
    private static ContentPane pane;

    private TopPane topPane;
    private CenterPane centerPane;

    public static ContentPane getInstance() {
        if(pane == null)
            pane = new ContentPane();
        return pane;
    }

    private ContentPane() {
        setId("ContentPane");

        //topPane = TopPane.getInstance();
        centerPane = CenterPane.getInstance();

        //setTop(topPane);
        setCenter(centerPane);
    }

}
