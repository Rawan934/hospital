package Backend;
public class WaitingPatient {
    private Appointment appointment;
    private String requestDate; // Format: YYYY-MM-DD
    private String requestTime; // Format: HH:MM

    // Constructor
    public WaitingPatient(Appointment appointment, String requestDate, String requestTime) {
        this.appointment = appointment;
        this.requestDate = requestDate;
        this.requestTime = requestTime;
    }

    // Getters
    public Appointment getAppointment(){
        return this.appointment;
    }
    public Patient getPatient() {
        return appointment.getPatient();
    }

    public String getRequestDate() {
        return requestDate;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public String getDetails() {
        return "Appointment ID: " +appointment.getAppointmentID()+
                ", Patient: " + appointment.getPatient().getName() +
                ", Physician: " + appointment.getPhysician() +
                ", Request Date: " + requestDate +
                ", Request Time: " + requestTime;
    }
}