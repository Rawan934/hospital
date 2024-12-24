package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Scene4Controller {

    public Button addAppointmentButton;
    public Button viewAppointmentsButton;
    public Button cancelAppointmentButton;
    public Button addToWaitingListButton;  // Button to add to the waiting list
    private Scene previousScene;

    // Setter for previous scene (so we can go back)
    public void setPreviousScene(Scene previousScene) {
        this.previousScene = previousScene;
    }

    // Back button handler
    public void handleBackToPreviousScene(ActionEvent event) {
        if (previousScene != null) {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(previousScene);
            stage.show();
        } else {
            System.out.println("No previous scene to return to.");
        }
    }

    // View Appointments button handler
    public void handleViewAppointments(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SceneViewAppointments.fxml"));
            Scene viewAppointmentsScene = new Scene(loader.load(), 600, 400);
            SceneViewAppointmentsController controller = loader.getController();
            controller.setPreviousScene(((Button) event.getSource()).getScene());
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(viewAppointmentsScene);
            stage.setTitle("View Appointments");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading View Appointments scene.");
        }
    }

    // Add Appointment button handler
    public void handleAddAppointment(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SceneAddAppointment.fxml"));
            Scene addAppointmentScene = new Scene(loader.load(), 600, 400);
            SceneAddAppointmentController controller = loader.getController();
            controller.setPreviousScene(((Button) event.getSource()).getScene());
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(addAppointmentScene);
            stage.setTitle("Add Appointment");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading Add Appointment scene.");
        }
    }

    // Cancel Appointment button handler
    public void handleCancelAppointment(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SceneCancelAppointment.fxml"));
            Scene cancelAppointmentScene = new Scene(loader.load(), 600, 400);
            SceneCancelAppointmentController controller = loader.getController();
            controller.setPreviousScene(((Button) event.getSource()).getScene());
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(cancelAppointmentScene);
            stage.setTitle("Cancel Appointment");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading Cancel Appointment scene.");
        }
    }

    // Add to Waiting List button handler
    public void handleAddToWaitingList(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene10.fxml"));
            Scene waitingListScene = new Scene(loader.load(), 600, 400);
            Scene10Controller controller = loader.getController();
            controller.setPreviousScene(((Button) event.getSource()).getScene());
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(waitingListScene);
            stage.setTitle("Waiting List");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading Waiting List scene.");
        }
    }
}
