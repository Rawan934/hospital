package GUI;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloCotroller {
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

    public void HelloController2() {
    }

    @FXML
    private void LoginButtonClick(ActionEvent event) {
        String username = this.usernameField.getText();
        String password = this.passwordField.getText();
        if ("22".equals(username) && "10".equals(password)) {
            try {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("scene2.fxml"));
                Scene scene2 = new Scene((Parent)loader.load(), (double)800.0F, (double)600.0F);
                Stage stage = (Stage)this.LoginButton.getScene().getWindow();
                stage.setScene(scene2);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error loading the next scene.");
            }
        } else {
            this.welcomeText.setText("Invalid username or password. Please try again.");
            this.welcomeText.setStyle("-fx-text-fill: red;");
        }

    }
}
