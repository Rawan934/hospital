package Backend;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CurrentTime {
    public static String getCurrentTime() {
        // Get the current time
        LocalTime currentTime = LocalTime.now();
        // Define the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        // Return the formatted time
        return currentTime.format(formatter);
    }
}