package GUI;

import Backend.PatientManagementSystem;
import Backend.WaitingPatient; // Assuming this class exists in the Backend package
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class Scene2Controller {

    @FXML
    private Button AddPatient;

    @FXML
    public Button GenerateReport;

    @FXML
    private Button ManageAppointments;

    @FXML
    private Button NextPatient;

    @FXML
    private Button ShowBilling;


    // Variable to store the previous scene
    private Scene previousScene;

    // Assuming you have this instance in your controller
    public static PatientManagementSystem patientManagementSystem = new PatientManagementSystem();

    // Method to go to Scene 3 (Add Patient)
    @FXML
    private void handleAddPatient(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("scene3.fxml"));
            Scene scene3 = new Scene(loader.load(), 800, 600);

            // Set the current scene as previous scene for returning later
            previousScene = ((Button) event.getSource()).getScene();

            // Get the controller of Scene3 and pass the previous scene to it
            Scene3Controller scene3Controller = loader.getController();
            scene3Controller.setPreviousScene(previousScene);

            // Switch to Scene 3
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(scene3);
            stage.setTitle("Add Patient");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading Scene 3.");
        }
    }

    // Handle "Back" button in Scene3 to return to previous scene
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

    // Method to go to Scene 4 (Manage Appointments)
    @FXML
    private void handleManageAppointments(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("scene4.fxml"));
            Scene scene4 = new Scene(loader.load(), 800, 600);

            // Set the current scene as previous scene for returning later
            previousScene = ((Button) event.getSource()).getScene();

            // Get the controller of Scene 4 and pass the previous scene to it
            Scene4Controller scene4Controller = loader.getController();
            scene4Controller.setPreviousScene(previousScene);

            // Switch to Scene 4
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(scene4);
            stage.setTitle("Manage Appointments");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading Scene 4.");
        }
    }

    // Method to go to Scene 5 (Generate Report)
    @FXML
    private void handleGenerateReport(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("scene5.fxml"));
            Scene scene5 = new Scene(loader.load(), 800, 600);

            // Set the current scene as previous scene for returning later
            previousScene = ((Button) event.getSource()).getScene();

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(scene5);
            stage.setTitle("Generate Report");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading Scene 5.");
        }
    }

    // Method to go to Scene 6 (Show Billing)
    @FXML
    private void handleShowBilling(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("scene6.fxml"));
            Scene scene6 = new Scene(loader.load(), 800, 600);

            // Set the current scene as previous scene for returning later
            previousScene = ((Button) event.getSource()).getScene();

            // Get the controller of Scene 6 and pass the previous scene to it
            Scene6Controller scene6Controller = loader.getController();
            scene6Controller.setPreviousScene(previousScene);

            // Switch to Scene 6
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(scene6);
            stage.setTitle("Show Billing");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading Scene 6.");
        }
    }

    // Handle "Next Patient" button click
    @FXML
    Label nextPatientLabel;
    @FXML
    private void handleNextPatient(ActionEvent event) {
        String nextPatient = patientManagementSystem.nextPatient();
        if (nextPatient != null) {
            System.out.println("Next patient: " + nextPatient);
            nextPatientLabel.setText("Next patient: " + nextPatient);
            // You can update a label or show a dialog here with the patient details.
        } else {
            System.out.println("No more patients in the queue.");
            nextPatientLabel.setText("No more patients in the queue.");
        }
    }


}
