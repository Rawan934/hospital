package GUI;

import Backend.CurrentTime; // Import the CurrentTime class
import Backend.PatientManagementSystem; // Import the PatientManagementSystem class
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Scene10Controller {

    @FXML
    private Button backToScene4Button;

    @FXML
    private Button addToWaitingListButton; // Reference to the "Add to waiting list" button

    @FXML
    private TextField patientIDField; // Reference to the TextField where the user enters the patient ID

    private Scene previousScene;

    // Instance of PatientManagementSystem to access addPatienttoWaitingList
    private PatientManagementSystem patientManagementSystem = Scene2Controller.patientManagementSystem;

    // Setter for previous scene
    public void setPreviousScene(Scene previousScene) {
        this.previousScene = previousScene;
    }
    public void setPatientManagementSystem(PatientManagementSystem patientManagementSystem) {
        this.patientManagementSystem = patientManagementSystem;
    }

    // Initialize the controller by creating a new instance of PatientManagementSystem
    public void initialize() {

    }

    // Back button action
    @FXML
    public void handleBackToScene4(ActionEvent event) {
        if (previousScene != null) {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(previousScene);
            stage.show();
        } else {
            System.out.println("No previous scene to return to.");
        }
    }

    // Method to add a patient to the waiting list
    @FXML
    public void handleAddToWaitingList(ActionEvent event) {
        try {
            // Retrieve the patient ID entered by the user
            int patientID = Integer.parseInt(patientIDField.getText());
            // Call the addPatienttoWaitingList method in PatientManagementSystem
            patientManagementSystem.addPatienttoWaitingList(patientID);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Patient ID. Please enter a valid number.");

        }
    }
}