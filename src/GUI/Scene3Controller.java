package GUI;

import Backend.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Scene3Controller {
    @FXML
    TextField patientNameField;
    @FXML
    TextField patientAgeField;
    @FXML
    TextField patientConditionField;
    @FXML
    TextField patientConditionField1;
    @FXML
    Button submitPatientButton;
    @FXML
    Label addpatientlabel = new Label("");
    @FXML
    private TextField patientID;


    // Variable to store the previous scene
    private Scene previousScene;

    // Method to set the previous scene (Scene2)
    public void setPreviousScene(Scene scene) {
        this.previousScene = scene;
    }

    // Handle the "Back" button in Scene3 to return to the previous scene (Scene2)
    @FXML
    private void handleBackToPreviousScene(ActionEvent event) {
        if (previousScene != null) {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(previousScene);
            stage.show();
        } else {
            System.out.println("No previous scene to return to.");
        }
    }

    // Method to handle patient submission (you can implement the logic here)
    @FXML
    public void handleSubmitPatient(ActionEvent event) {
        PatientManagementSystem patientManagementSystem = Scene2Controller.patientManagementSystem;
        patientManagementSystem.addPatient(new Patient(patientNameField.getText(), Integer.parseInt(patientAgeField.getText()),
                patientConditionField.getText(), patientConditionField1.getText()));

        addpatientlabel.setText("Patient Has been Added");

    }
}
