package Backend;

import java.util.HashSet; // to assign random unique id's to Appointments
import java.util.Random;
import java.util.Set;

public class Appointment {
    private int appointmentID;
    private Patient patient;
    private String physician;
    private String date; // Format: YYYY-MM-DD
    private String time; // Format: HH:MM
    private String status; // Options: Scheduled, Canceled, Rescheduled
    private static Set<Integer> usedAppointmentsIDs = new HashSet<>(); // Set to store used IDs
    private static Random random = new Random();

    public Appointment(Patient patient,String physician, String date, String time) {
        this.appointmentID = generateUniqueID();
        this.patient = patient;
        this.physician = physician;
        this.date = date;
        this.time = time;
        this.status = "Scheduled"; // Default
//        DatabaseManagement.addAppointment(this.appointmentID,this.patient.getPatientID(),this.physician,this.date,this.time,this.status);
    }
    public Appointment(int appointmentID, Patient patient,String physician, String date, String time, String status) {
        this.appointmentID = appointmentID;
        this.patient = patient;
        this.physician = physician;
        this.date = date;
        this.time = time;
        this.status = status;
    }
    private int generateUniqueID() {
        int newID;
        do {
            newID = random.nextInt(1000000); // Generate a random ID between 0 and 999,999
        } while (usedAppointmentsIDs.contains(newID)); // Check if the ID has already been used
        usedAppointmentsIDs.add(newID); // Add to the set to mark it as used
        return newID;
    }

    // Getters and Setters
    public int getAppointmentID() {
        return appointmentID;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }
    public String getPhysician(){
        return this.physician;
    }

    //schedule the appointment
    public void schedule() {
        this.status = "Scheduled";
        System.out.println("Appointment scheduled for " + patient.getName() + " on " + date + " at " + time);
    }

    //cancel the appointment
    public void cancel() {
        this.status = "Canceled";
        System.out.println("Appointment canceled for " + patient.getName());
    }

    //reschedule the appointment
    public String reschedule(String newDate, String newTime) {
        this.date = newDate;
        this.time = newTime;
        this.status = "Rescheduled";
        DatabaseManagement.rescheduleAppointment(this.appointmentID,this.date,this.time);
        return "Appointment rescheduled for " + patient.getName() + " to " + newDate + " at " + newTime;
    }

    public static Set<Integer> getUsedAppointmentsIDs() {
        return usedAppointmentsIDs;
    }

    //get appointment details

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentID : " + appointmentID +
                ", patient : " + patient +
                ", physician : '" + physician + '\'' +
                ", date : '" + date + '\'' +
                ", time : '" + time + '\'' +
                ", status : '" + status + '\'' +
                '}';
    }
}

