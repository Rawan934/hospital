package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Backend.PatientManagementSystem;

public class Scene6Controller {

    @FXML
    private TextField billingLabel; // For inputting patient ID

    @FXML
    private Button paymenthistory; // Button to fetch payment history

    @FXML
    private Label paymentHistoryLabel; // Label to display the payment history

    // Reference to PatientManagementSystem from Scene2Controller
    private PatientManagementSystem patientManagementSystem = Scene2Controller.patientManagementSystem;

    // Variable to store the previous scene
    private Scene previousScene;

    // Setter method to pass the previous scene from Scene 2
    public void setPreviousScene(Scene previousScene) {
        this.previousScene = previousScene;
    }

    // Handle "Back" button in Scene 6 to return to previous scene (Scene 2)
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

    // Handle "Payment History" button
    @FXML
    private void handlePaymentHistory(ActionEvent event) {
        try {
            // Retrieve patient ID from the input field
            int patientId = Integer.parseInt(billingLabel.getText().trim());

            // Call the method to get payment history
            String paymentHistory = patientManagementSystem.getPaymentHistory(patientId);

            // Display the payment history in the Label
            paymentHistoryLabel.setText(paymentHistory);
        } catch (NumberFormatException e) {
            // Handle invalid patient ID input
            paymentHistoryLabel.setText("Invalid Patient ID. Please enter a valid number.");
        } catch (Exception e) {
            // Handle any other exceptions
            paymentHistoryLabel.setText("Error retrieving payment history: " + e.getMessage());
        }
    }
}