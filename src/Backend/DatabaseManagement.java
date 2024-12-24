package Backend;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;
import com.google.gson.reflect.TypeToken;

public class DatabaseManagement {
    public static void addBillingRecord(int patientID, double billingAmount, String paymentHistory) {
        String sql = "INSERT INTO billing (patientID, billingAmount, paymentHistory) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, patientID);
            stmt.setDouble(2, billingAmount);
            stmt.setString(3, paymentHistory); // Store serialized payment history
            stmt.executeUpdate();
            System.out.println("Billing record added successfully.");
        } catch (Exception e) {
            System.err.println("Error adding billing record: " + e.getMessage());
        }
    }
    public static void adjustBillingAmount(int patientID, double newBillingAmount) {
        String sql = "UPDATE billing SET billingAmount = billingAmount + ? WHERE patientID = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, newBillingAmount);  // Set the new billing amount
            stmt.setInt(2, patientID);            // Set the patient ID
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Billing amount updated successfully for patient ID " + patientID);
            } else {
                System.out.println("No billing record found for patient ID " + patientID);
            }
        } catch (Exception e) {
            System.err.println("Error adjusting billing amount: " + e.getMessage());
        }
    }
    public static void addPayment(int patientID, double payment, List<Double> paymentHistory) {
        // Serialize the payment history and append the new payment to the list
        String serializedPaymentHistory = new Gson().toJson(paymentHistory);;

        // SQL query to update the billing amount and payment history
        String sql = "UPDATE billing SET billingAmount = billingAmount - ?, paymentHistory = ? WHERE patientID = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Set the payment amount to subtract from the billingAmount
            stmt.setDouble(1, payment);
            // Set the serialized paymentHistory
            stmt.setString(2, serializedPaymentHistory);
            // Set the patientID
            stmt.setInt(3, patientID);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Payment of $" + payment + " added for patient ID " + patientID);
            } else {
                System.out.println("No billing record found for patient ID " + patientID);
            }
        } catch (Exception e) {
            System.err.println("Error adding payment: " + e.getMessage());
        }
    }
    public static void addAppointment(int appointmentID, int patientID, String physician, String date, String time, String status) {
        String sql = "INSERT INTO appointment (appointmentID, patientID, physician, appointmentDate, appointmentTime, status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, appointmentID);
            stmt.setInt(2, patientID);
            stmt.setString(3, physician);
            stmt.setString(4, date);
            stmt.setString(5, time);
            stmt.setString(6, status);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Appointment added successfully for patient ID: " + patientID);
            } else {
                System.out.println("Failed to add appointment.");
            }
        } catch (Exception e) {
            System.err.println("Error adding appointment: " + e.getMessage());
        }
    }
    public static void cancelAppointment(int appointmentID) {
        String sql = "UPDATE appointment SET status = 'Canceled' WHERE appointmentID = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, appointmentID);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Appointment status updated to 'Canceled' successfully for ID: " + appointmentID);
            } else {
                System.out.println("No appointment found with ID: " + appointmentID);
            }
        } catch (Exception e) {
            System.err.println("Error updating appointment status to 'Canceled': " + e.getMessage());
        }
    }
    public static void rescheduleAppointment(int appointmentID, String newDate, String newTime) {
        String sql = "UPDATE appointment SET appointmentDate = ?, appointmentTime = ?, status = 'Rescheduled' WHERE appointmentID = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newDate);
            stmt.setString(2, newTime);
            stmt.setInt(3, appointmentID);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Appointment rescheduled successfully for ID: " + appointmentID);
            } else {
                System.out.println("No appointment found with ID: " + appointmentID);
            }
        } catch (Exception e) {
            System.err.println("Error rescheduling appointment: " + e.getMessage());
        }
    }
    public static void addPatient(int patientID, String name, int age, String contactInfo, String medicalHistory, String visitRecords) {
        String sql = "INSERT INTO patient (patientID, name, age, contactInfo, medicalHistory, visitRecords) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, patientID);
            stmt.setString(2, name);
            stmt.setInt(3, age);
            stmt.setString(4, contactInfo);
            stmt.setString(5, medicalHistory); // Serialized medical history
            stmt.setString(6, visitRecords);  // Serialized visit records

            stmt.executeUpdate();
            System.out.println("Patient record added successfully.");
        } catch (Exception e) {
            System.err.println("Error adding patient record: " + e.getMessage());
        }
    }

    public static void updateContactInfo(int patientID, String newContactInfo) {
        String sql = "UPDATE patient SET contactInfo = ? WHERE patientID = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newContactInfo);
            stmt.setInt(2, patientID);
            stmt.executeUpdate();
            System.out.println("Contact information updated successfully.");
        } catch (Exception e) {
            System.err.println("Error updating contact information: " + e.getMessage());
        }
    }

    public static void updateVisitRecords(int patientID, List<String> visitRecords) {
        Gson gson = new Gson();
        String serializedRecords = gson.toJson(visitRecords); // Convert list to JSON

        String sql = "UPDATE patient SET visitRecords = ? WHERE patientID = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, serializedRecords);
            stmt.setInt(2, patientID);
            stmt.executeUpdate();
            System.out.println("Visit records updated successfully.");
        } catch (Exception e) {
            System.err.println("Error updating visit records: " + e.getMessage());
        }
    }

    public static void updateMedicalHistory(int patientID, String newMedicalHistory) {
        String sql = "UPDATE patient SET medicalHistory = CONCAT(medicalHistory, ', ', ?) WHERE patientID = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newMedicalHistory);
            stmt.setInt(2, patientID);
            stmt.executeUpdate();
            System.out.println("Medical history updated successfully.");
        } catch (Exception e) {
            System.err.println("Error updating medical history: " + e.getMessage());
        }
    }
    public static PatientBST loadPatientsToBST() {
        PatientBST patientBST = new PatientBST();
        String query = "SELECT * FROM patient";

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                // Retrieve patient details
                int patientID = rs.getInt("patientID");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String contactInfo = rs.getString("contactInfo");
                String medicalHistory = rs.getString("medicalHistory");

                // Deserialize visitRecords from JSON
                String visitRecordsJson = rs.getString("visitRecords");
                List<String> visitRecords = new Gson().fromJson(
                        visitRecordsJson, new TypeToken<List<String>>() {}.getType()
                );

                // Create a Patient object using the database data
                Patient patient = new Patient(patientID, name, age, contactInfo, medicalHistory, visitRecords);

                // Add the Patient to the BST
                patientBST.addPatient(patient);
                Patient.getUsedPatientIDs().add(patientID); // To make sure of having all patients with unique IDs
            }

            System.out.println("All patients successfully loaded into the BST.");
        } catch (Exception e) {
            System.err.println("Error loading patients into the BST: " + e.getMessage());
        }
        return patientBST;
    }
    public static AppointmentQueue loadAppointmentsToQueue(PatientBST patientBST) {
        AppointmentQueue appointmentQueue = new AppointmentQueue();
        String query = "SELECT * FROM appointment";

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                // Retrieve appointment details
                int appointmentID = rs.getInt("appointmentID");
                int patientID = rs.getInt("patientID");
                String physician = rs.getString("physician");
                String date = rs.getString("appointmentDate");
                String time = rs.getString("appointmentTime");
                String status = rs.getString("status");

                // Find the patient in the BST using the patientID
                Patient patient = patientBST.findPatient(patientID);
                if (patient == null) {
                    System.err.println("Patient with ID " + patientID + " not found. Skipping appointment " + appointmentID + ".");
                    continue; // Skip if patient does not exist
                }

                // Create a new Appointment object
                Appointment appointment = new Appointment(appointmentID, patient, physician, date, time, status);

                // Enqueue the Appointment into the AppointmentQueue
                appointmentQueue.enqueue(appointment);
                Appointment.getUsedAppointmentsIDs().add(appointmentID);
            }

            System.out.println("All appointments successfully loaded into the queue.");
        } catch (Exception e) {
            System.err.println("Error loading appointments into the queue: " + e.getMessage());
        }
        return appointmentQueue;
    }

    public static void loadBillingData() {
        String sql = "SELECT * FROM billing";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Iterate through the result set and create Billing objects
            while (rs.next()) {
                int patientID = rs.getInt("patientID");
                double billingAmount = rs.getDouble("billingAmount");
                String paymentHistoryStr = rs.getString("paymentHistory");

                // Deserialize the paymentHistory from JSON-like string to List<Double>
                List<Double> paymentHistory = new ArrayList<>();
                if (paymentHistoryStr != null && !paymentHistoryStr.isEmpty()) {
                    for (String value : paymentHistoryStr.split(",")) {
                        try {
                            String sanitized = paymentHistoryStr.replace("[", "").replace("]", "").trim();
                            if (!sanitized.isEmpty()) {
                                paymentHistory = Arrays.stream(sanitized.split(","))
                                        .map(String::trim)
                                        .map(Double::parseDouble)
                                        .toList();}                        }
                        catch (NumberFormatException e) {
                            System.err.println("Invalid payment value: " + value);
                        }
                    }
                }
                // Create a Billing object and add it to the Billings list
                Billing.getBillings().add(new Billing(patientID, billingAmount, paymentHistory));
            }

            System.out.println("Billing data loaded successfully.");
        } catch (Exception e) {
            System.err.println("Error loading billing data: " + e.getMessage());

        }

    }
    public static void recieveAppointment(int appointmentID) {
        String sql = "UPDATE appointment SET status = 'Recieved' WHERE appointmentID = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, appointmentID);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Appointment status updated to 'Recieved' successfully for ID: " + appointmentID);
            } else {
                System.out.println("No appointment found with ID: " + appointmentID);
            }
        } catch (Exception e) {
            System.err.println("Error updating appointment status to 'Recieved': " + e.getMessage());
        }
    }
}


