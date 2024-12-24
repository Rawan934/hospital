package GUI;

import Backend.PatientManagementSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SceneAddAppointmentController {

    @FXML
    private TextField Appoinmentid;

    @FXML
    private TextField NewDate;

    @FXML
    private TextField NewTime;

    @FXML
    private Label label;

    @FXML
    private Button Reschedule;


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

    @FXML
    private void handleRescheduleAppointment(ActionEvent event) {
      PatientManagementSystem patientManagementSystem = Scene2Controller.patientManagementSystem;
        label.setText(patientManagementSystem.rescheduleAppointment(Integer.parseInt(Appoinmentid.getText()),
                NewDate.getText(), NewTime.getText()));
        label.setText("Appointment has been rescheduled successfully.");
    }
}
