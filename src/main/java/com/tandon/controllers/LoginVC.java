package com.tandon.controllers;

import com.tandon.DAO.POJOs.UserSession;
import com.tandon.DAO.Service.UserSessionService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Yichao Qin
 */
@Component
@FxmlView("/views/login.fxml")
public class LoginVC implements ApplicationContextAware {
    @FXML
    public Label usernameLabel;
    @FXML
    public Label passwordLabel;
    @FXML
    public TextField textField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Button submitBtn;
    @FXML
    public Button cancelBtn;

    @Autowired
    private UserSessionService userservice;

    private ApplicationContext applicationContext;

    @FXML
    public void initialize() {
    }

    @FXML
    public void handleSubmitButtonAction(){
        String usernameinput = this.textField.getText();
        String passwordinput = this.passwordField.getText();

        //UserSession userSession = userservice.findByUsernameAndPassword(usernameinput,passwordinput);
    }

    @FXML
    public void handleCancelButtonAction(){
        textField.clear();
        passwordField.clear();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
