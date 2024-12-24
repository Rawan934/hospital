
package GUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            // Load the first scene (Login Scene)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Scene loginScene = new Scene(loader.load(), 600, 400);

            // Configure the primary stage
            stage.setTitle("Login");
            stage.setScene(loginScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error starting the application.");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
