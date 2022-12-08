package com.tandon.controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/views/home.fxml")
public class HomeVC implements ApplicationContextAware {
    @FXML
    public SplitPane splitpane;
    @FXML
    public AnchorPane leftpane;
    @FXML
    public StackPane rightpane;
    @FXML
    public AnchorPane layer1;

    private ApplicationContext applicationContext;
    @FXML
    public void initialize(){

    }

    @FXML
    public void checkScheduleBtnClicked(){

    }

    @FXML
    public void onHomeBtnClicked(){
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(CalenderVC.class);
        rightpane.getChildren().add(root);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}