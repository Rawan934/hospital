package GUI;

import Backend.Appointment;
import Backend.Patient;
import Backend.PatientManagementSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SceneViewAppointmentsController {

    @FXML
    private TextField Date;

    @FXML
    private Label LABEL;

    @FXML
    private TextField Patientid;

    @FXML
    private TextField Time;

    @FXML
    private TextField physician;

    @FXML
    private Button Schedule;

    private Scene previousScene; // Variable to store the previous scene

    public void setPreviousScene(Scene previousScene) {
        this.previousScene = previousScene;
    }

    // Method to handle back navigation
    @FXML
    private void handleBack(ActionEvent event) {
        if (previousScene != null) {
            Stage stage = (Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow();
            stage.setScene(previousScene);
            stage.show();
        } else {
            System.out.println("No previous scene to return to.");
        }
    }



    public void handleScheduleAppointment(ActionEvent event) {
        PatientManagementSystem patientManagementSystem = new PatientManagementSystem();
        patientManagementSystem.scheduleAppointment(Integer.parseInt(Patientid.getText()),
                physician.getText(), Date.getText(), Time.getText());;
        LABEL.setText("Appointment has been scheduled successfully.");
    }
}

