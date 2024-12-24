package GUI;

import Backend.PatientManagementSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SceneCancelAppointmentController {

    // Reference to the PatientManagementSystem
    private PatientManagementSystem patientManagementSystem = new PatientManagementSystem();

    // Variable to store the previous scene
    private Scene previousScene;

    @FXML
    private TextField appointmentId; // TextField for entering appointment ID

    @FXML
    private Label resultLabel; // Label to display results (success or failure)

    @FXML
    private Button cancelButton; // Button to trigger the cancel action

    // Setter to pass the previous scene from where this scene is opened
    public void setPreviousScene(Scene previousScene) {
        this.previousScene = previousScene;
    }

    // Handle Back button to return to the previous scene
    public void handleBack(ActionEvent event) {
        if (previousScene != null) {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(previousScene);
            stage.show();
        } else {
            System.out.println("No previous scene to return to.");
        }
    }

    // Handle Cancel Appointment button click
    @FXML
    public void handleCancelAppointment(ActionEvent event) {
        try {
            // Get the appointment ID from the TextField
            int appointmentID = Integer.parseInt(appointmentId.getText());

            // Call the cancelAppointment method in the PatientManagementSystem
            patientManagementSystem.cancelAppointment(appointmentID);

            // Update the result label with success message
            resultLabel.setText("Appointment " + appointmentID + " has been canceled.");

        } catch (NumberFormatException e) {
            // Handle invalid input (non-integer appointment ID)
            resultLabel.setText("Invalid appointment ID entered.");
        }
    }
}
