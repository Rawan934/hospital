package Backend;

import com.google.gson.Gson;

import java.util.List;

public class PatientManagementSystem {


    // Attributes
    private PatientBST patientList; // Stores registered patients
    private AppointmentQueue appointmentQueue; // Manages appointment scheduling
    private PriorityQueue waitingList; // Patients waiting for availability
    private List<Billing> billingRecords; // Billing data for patients
    private ReportGenerator reportGenerator; // Generates system reports
    private PriorityQueue priorityQueue;

    // Constructor
    public PatientManagementSystem() {
        DatabaseConnection.connect();
        this.patientList = DatabaseManagement.loadPatientsToBST();
        this.appointmentQueue = DatabaseManagement.loadAppointmentsToQueue(patientList);
        this.waitingList = new PriorityQueue();
        DatabaseManagement.loadBillingData();
        this.billingRecords = Billing.getBillings();
        this.reportGenerator = new ReportGenerator();
        this.priorityQueue = new PriorityQueue();
    }


    // Add a new patient
    public void addPatient(Patient patient) {
        patientList.addPatient(patient);
        Gson gson = new Gson();
        String visits = gson.toJson(patient.getVisitRecords());
        DatabaseManagement.addPatient(patient.getPatientID(), patient.getName(), patient.getAge(), patient.getContactInfo(), patient.getMedicalHistory(), visits);
        System.out.println("Patient added: " + patient.getPatientID());
    }

    // Find a patient by ID
    public Patient findPatient(int patientID) {
        Patient patient = patientList.findPatient(patientID);
        return patient;
    }

    // Schedule an appointment
    public String scheduleAppointment(int patientid, String physician, String date, String time) {
        Appointment appointment = new Appointment(patientList.findPatient(patientid), physician, date, time);
        appointment.schedule();
        appointmentQueue.enqueue(appointment);
        DatabaseManagement.addAppointment(appointment.getAppointmentID(), appointment.getPatient().getPatientID(), appointment.getPhysician(), appointment.getDate(), appointment.getTime(), appointment.getStatus());
        return "Appointment scheduled for Patient ID: " + appointment.getPatient().getPatientID();
    }

    // Cancel an appointment
    public void cancelAppointment(int appointmentID) {
        Appointment appointment = appointmentQueue.findAppointmentById(appointmentID);
        if (appointment != null) {
            appointment.cancel();
            DatabaseManagement.cancelAppointment(appointment.getAppointmentID());
            System.out.println("Appointment with ID " + appointmentID + " has been canceled.");
        } else {
            System.out.println("No appointment found with ID " + appointmentID);
        }
    }

    // Generate a report
    public String generateReport(String reportType) {
        switch (reportType.toLowerCase()) {
            case "patient":
                reportGenerator.setData(patientList);
                return reportGenerator.generatePatientReport();
            case "appointment":
                reportGenerator.setData(appointmentQueue);
                return reportGenerator.generateAppointmentReport();
            case "revenue":
                reportGenerator.setData(billingRecords);
                return reportGenerator.generateHospitalRevenueReport();
            default:
                return "Invalid report type. Available options: patient, appointment, revenue.";
        }
    }

    public String rescheduleAppointment(int appointmentID, String newDate, String newTime) {
        Appointment appointment = appointmentQueue.findAppointmentById(appointmentID);
        return appointment.reschedule(newDate, newTime);
    }

    // Add a billing record
    public void addBillingRecord(Billing billing) {
        billingRecords.add(billing);
        String serializedPayments = new Gson().toJson(billing.getPaymentHistory());
        DatabaseManagement.addBillingRecord(billing.getPatientID(), billing.getBillingAmount(), serializedPayments);
        System.out.println("Billing record added for Patient ID: " + billing.getPatientID());
    }

    public void addPayment(Billing billing, double amount) {
        billing.addPayment(amount);
    }

    public String generateBill(Billing billing, double amount) {
        return billing.generateBill(amount);
    }

    public String generateBill(Billing billing) {
        return billing.generateBill();
    }

    public PatientBST getPatientList() {
        return patientList;
    }

    public AppointmentQueue getAppointmentQueue() {
        return appointmentQueue;
    }

    public PriorityQueue getWaitingList() {
        return waitingList;
    }

    public List<Billing> getBillingRecords() {
        return billingRecords;
    }

    public ReportGenerator getReportGenerator() {
        return reportGenerator;
    }

    // build object using appointment attributes
    public void addPatienttoWaitingList(int appointmentId){
        WaitingPatient waitingPatient = new WaitingPatient(appointmentQueue.findAppointmentById(appointmentId),CurrentDate.getCurrentDate(),CurrentTime.getCurrentTime());
        this.priorityQueue.add(waitingPatient);
    }
    public String nextPatient(){
        WaitingPatient waitingPatient =  priorityQueue.remove();
        DatabaseManagement.recieveAppointment(waitingPatient.getAppointment().getAppointmentID());
        return waitingPatient.getDetails();
    }


    public String getPaymentHistory (int patientId){
        return Billing.searchBillingByPatientID(patientId).printBillingDetails();
    }
}