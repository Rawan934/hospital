package Backend;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CurrentDate {
    public static String getCurrentDate() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        // Return the date as a string
        return currentDate.toString();  // ISO format: yyyy-MM-dd
    }

}