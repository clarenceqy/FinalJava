package com.tandon.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/views/home.fxml")
public class HomeVC {
    @FXML
    public SplitPane splitpane;
    @FXML
    public AnchorPane leftpane;
    @FXML
    public StackPane rightpane;
    @FXML
    public AnchorPane layer1;
    @FXML
    public void initialize(){

    }

    /**
     * @Title: ${checkScheduleBtnClicked}
     * @Description: This method updates right split pane to checkschedule view.
     */
    @FXML
    public void checkScheduleBtnClicked(){

    }

    /**
     * @Title: ${addScheduleBtnClicked}
     * @Description: This method updates right split pane to addschedule view.
     */
    @FXML
    public void addScheduleBtnClicked(){

    }
}