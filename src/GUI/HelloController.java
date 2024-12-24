package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Hyperlink FpHyperlink;

    @FXML
    private Button LoginButton;

    @FXML
    private Label welcomeText;

    @FXML
    private void LoginButtonClick(ActionEvent event) {


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("scene2.fxml"));
            Scene scene2 = new Scene(loader.load(), 800, 600);

            Stage stage = (Stage) LoginButton.getScene().getWindow();
            stage.setScene(scene2);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading the next scene.");
        }
    }
    }

