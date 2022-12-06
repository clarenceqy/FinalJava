package com.tandon.finaljava.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

/**
 * @author Yichao Qin
 */
@Component
@FxmlView("/login.fxml")
public class LoginController {
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
