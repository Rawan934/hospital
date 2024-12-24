package GUI;

import Backend.PatientManagementSystem;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class Scene5Controller {

    @FXML
    private Button Generate;  // Generate button

    @FXML
    private RadioButton Revenue;  // Radio button for Revenue

    @FXML
    private RadioButton appointment;  // Radio button for Appointment

    @FXML
    private RadioButton patient;  // Radio button for Patient

    @FXML
    private Label resultLabel;  // Label to display the result

    private PatientManagementSystem patientManagementSystem = Scene2Controller.patientManagementSystem;

    // Setter to allow setting the patient management system (if needed)
    public void setPatientManagementSystem(PatientManagementSystem patientManagementSystem) {
        this.patientManagementSystem = patientManagementSystem;
    }

    @FXML
    public void initialize() {

        // Group the radio buttons
        ToggleGroup toggleGroup = new ToggleGroup();

        // Ensure each radio button is in the same toggle group
        if (Revenue != null) {
            Revenue.setToggleGroup(toggleGroup);
        }

        if (appointment != null) {
            appointment.setToggleGroup(toggleGroup);
        }

        if (patient != null) {
            patient.setToggleGroup(toggleGroup);
        }

        // Add action to the Generate button
        if (Generate != null) {
            // Ensure the button's action is registered only once
            Generate.setOnAction(event -> {
                // Get the selected radio button
                RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();

                if (selectedRadioButton != null) {
                    // Retrieve the selected option and generate the report
                    String selectedOption = selectedRadioButton.getText().toLowerCase();
                    String result = patientManagementSystem.generateReport(selectedOption);
                    resultLabel.setText(result);
                } else {
                    resultLabel.setText("No option selected!");  // Error message if no radio button is selected
                }
            });
        }
    }
}