package com.tandon.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

/**
 * @author Yichao Qin
 */
@Component
public class LoginVC {
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

    @FXML
    public void initialize() {

    }

    @FXML
    public void handleSubmitButtonAction(){}

    @FXML
    public void handleCancelButtonAction(){}
}
