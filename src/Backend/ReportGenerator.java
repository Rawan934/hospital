package Backend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportGenerator {

    private String reportType;
    // Data attribute to store the relevant data (either PatientBST, AppointmentQueue, or Billings list)
    private Object data;

    // Constructor to initialize the data attribute
    public ReportGenerator(String reportType, Object data) {
        this.reportType = reportType;
        this.data = data;
    }

    public ReportGenerator() {
        this.reportType = null;
        this.data = null;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public void setData(Object data) {
        this.data = data;
    }

    // Generate Patient Report
    public String generatePatientReport() {
        if (data == null || !(data instanceof PatientBST)) {
            return "No patient data available for the report.";
        }

        PatientBST patientBST = (PatientBST) data;  // Cast the data to PatientBST
        List<Patient> patients = patientBST.toList(); // Convert BST to a list
        quickSortPatients(patients, 0, patients.size() - 1);
        StringBuilder report = new StringBuilder("Patient Visit History Report:\n");

        for (Patient patient : patients) {
            report.append("Patient ID: ").append(patient.getPatientID())
                    .append(", Name: ").append(patient.getName())
                    .append(", Visit Records: ").append(patient.getVisitRecords())
                    .append("\n");
        }
        return report.toString();
    }

    // Generate Appointment Report
    public String generateAppointmentReport() {
        if (data == null || !(data instanceof AppointmentQueue)) {
            return "No appointment data available for the report.";
        }

        AppointmentQueue appointmentQueue = (AppointmentQueue) data;  // Cast the data to AppointmentQueue
        List<Appointment> appointments = appointmentQueue.toList(); // Convert queue to a list

        // Sort appointments by date using QuickSort
        quickSortAppointments(appointments, 0, appointments.size() - 1);

        StringBuilder report = new StringBuilder("Appointment Statistics Report:\n");

        for (Appointment appointment : appointments) {
            report.append("Appointment ID: ").append(appointment.getAppointmentID())
                    .append(", Patient ID: ").append(appointment.getPatient().getPatientID())
                    .append(", Physician: ").append(appointment.getPhysician())
                    .append(", Date: ").append(appointment.getDate())
                    .append(", Time: ").append(appointment.getTime())
                    .append(", Status: ").append(appointment.getStatus())
                    .append("\n");
        }
        return report.toString();
    }

    public String generateAppointmentStatusReport() {
        if (data == null || !(data instanceof AppointmentQueue)) {
            return "No appointment data available for the report.";
        }

        AppointmentQueue appointmentQueue = (AppointmentQueue) data;
        List<Appointment> appointments = appointmentQueue.toList();
        Map<String, Integer> statusCount = new HashMap<>();

        for (Appointment appointment : appointments) {
            String status = appointment.getStatus();
            statusCount.put(status, statusCount.getOrDefault(status, 0) + 1);
        }

        StringBuilder report = new StringBuilder("Appointment Status Summary Report:\n");
        for (Map.Entry<String, Integer> entry : statusCount.entrySet()) {
            report.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        return report.toString();
    }

    public String generatePhysicianAppointmentReport() {
        if (data == null || !(data instanceof AppointmentQueue)) {
            return "No appointment data available for the report.";
        }

        AppointmentQueue appointmentQueue = (AppointmentQueue) data;
        List<Appointment> appointments = appointmentQueue.toList();
        Map<String, Integer> physicianCount = new HashMap<>();

        for (Appointment appointment : appointments) {
            String physician = appointment.getPhysician();
            physicianCount.put(physician, physicianCount.getOrDefault(physician, 0) + 1);
        }

        StringBuilder report = new StringBuilder("Physician Appointment Summary Report:\n");
        for (Map.Entry<String, Integer> entry : physicianCount.entrySet()) {
            report.append(entry.getKey()).append(": ").append(entry.getValue()).append(" appointments\n");
        }

        return report.toString();
    }

    public String generateAppointmentTimeDistributionReport() {
        if (data == null || !(data instanceof AppointmentQueue)) {
            return "No appointment data available for the report.";
        }

        AppointmentQueue appointmentQueue = (AppointmentQueue) data;
        List<Appointment> appointments = appointmentQueue.toList();
        Map<String, Integer> timeSlotCount = new HashMap<>();

        for (Appointment appointment : appointments) {
            String time = appointment.getTime(); // Extract time from appointment
            timeSlotCount.put(time, timeSlotCount.getOrDefault(time, 0) + 1);
        }

        StringBuilder report = new StringBuilder("Appointment Time Slot Distribution Report:\n");
        for (Map.Entry<String, Integer> entry : timeSlotCount.entrySet()) {
            report.append("Time Slot: ").append(entry.getKey()).append(" - ").append(entry.getValue()).append(" appointments\n");
        }

        return report.toString();
    }


    // Generate Revenue Report
    // Pass List of Billlings
    public String generateHospitalRevenueReport() {
        if (data == null || !(data instanceof List)) {
            return "No billing data available for the report.";
        }

        @SuppressWarnings("unchecked")
        List<Billing> billings = (List<Billing>) data; // Safely cast after verifying it's a List

        double totalPayments = 0; // Total revenue from payment histories
        double totalBillings = 0; // Total revenue from billing amounts

        for (Billing billing : billings) {
            totalPayments += billing.getPaymentHistory().stream().mapToDouble(Double::doubleValue).sum();
            totalBillings += billing.getBillingAmount();
        }

        return String.format(
                "Hospital Revenue Report:\nTotal Revenue from Payments: $%.2f\nTotal Billed Revenue: $%.2f\n",
                totalPayments, totalBillings
        );
    }


    //Generate Most Profitable Clients
    //List of Billlings
    public String generateMostProfitable() {
        if (data == null || !(data instanceof List)) {
            return "No billing data available for the report.";
        }

        List<Billing> billings = (List<Billing>) data;  // Cast the data to a List of Billing
        quickSortBillings(billings, 0, billings.size() - 1);  // Sort based on payment history summation

        double totalRevenue = 0;
        StringBuilder report = new StringBuilder("Most Paying Patients:\n");

        for (Billing billing : billings) {
            double paymentSum = billing.getPaymentHistory().stream().mapToDouble(Double::doubleValue).sum();
            totalRevenue += paymentSum; // Update total revenue based on payment history
            report.append("Patient ID: ").append(billing.getPatientID())
                    .append(", Total Billing Amount: $").append(billing.getBillingAmount())
                    .append(", Payment History Total: $").append(paymentSum)
                    .append("\n");
        }

        report.append("Total Revenue from Payments: $").append(totalRevenue);
        return report.toString();
    }


    // Sort by Patient Name
    private void quickSortPatients(List<Patient> list, int low, int high) {
        if (low < high) {
            int pi = partitionPatients(list, low, high);
            quickSortPatients(list, low, pi - 1);
            quickSortPatients(list, pi + 1, high);
        }
    }

    private int partitionPatients(List<Patient> list, int low, int high) {
        Patient pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            // Compare based on patient name (alphabetically)
            if (list.get(j).getName().compareTo(pivot.getName()) < 0) {
                i++;
                swapPatients(list, i, j);
            }
        }
        swapPatients(list, i + 1, high);
        return i + 1;
    }

    private void swapPatients(List<Patient> list, int i, int j) {
        Patient temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    private void quickSortAppointments(List<Appointment> appointments, int low, int high) {
        if (low < high) {
            int pivotIndex = partitionAppointments(appointments, low, high);
            quickSortAppointments(appointments, low, pivotIndex - 1);
            quickSortAppointments(appointments, pivotIndex + 1, high);
        }
    }

    private int partitionAppointments(List<Appointment> appointments, int low, int high) {
        Appointment pivot = appointments.get(high); // Pivot is the last element
        String pivotDate = pivot.getDate(); // Get the pivot date

        int i = low - 1; // Index of the smaller element

        for (int j = low; j < high; j++) {
            String currentDate = appointments.get(j).getDate();
            if (currentDate.compareTo(pivotDate) <= 0) { // Compare dates lexicographically, if smaller it return -ve so no swap
                i++;
                swapAppointments(appointments, i, j); // Use the new swap method
            }
        }

        // Swap the pivot into the correct position
        swapAppointments(appointments, i + 1, high);
        return i + 1; // Return the partition index
    }

    private void swapAppointments(List<Appointment> appointments, int i, int j) {
        Appointment temp = appointments.get(i);
        appointments.set(i, appointments.get(j));
        appointments.set(j, temp);
    }

    // QuickSort for Billing based on the sum of paymentHistory
    private void quickSortBillings(List<Billing> billings, int low, int high) {
        if (low < high) {
            int pi = partitionBillings(billings, low, high);

            // Recursively sort elements before and after partition
            quickSortBillings(billings, low, pi - 1);
            quickSortBillings(billings, pi + 1, high);
        }
    }

    private int partitionBillings(List<Billing> list, int low, int high) {
        Billing pivot = list.get(high); // Use the last element as the pivot
        int i = low - 1;

        for (int j = low; j < high; j++) {
            // Calculate the sum of paymentHistory for comparison
            double currentSum = calculatePaymentHistorySum(list.get(j));
            double pivotSum = calculatePaymentHistorySum(pivot);

            // Change the comparison to sort in descending order
            if (currentSum > pivotSum) {
                i++;
                swapBillings(list, i, j);
            }
        }
        swapBillings(list, i + 1, high);
        return i + 1;
    }

    private double calculatePaymentHistorySum(Billing billing) {
        return billing.getPaymentHistory().stream().mapToDouble(Double::doubleValue).sum();
    }

    private void swapBillings(List<Billing> list, int i, int j) {
        Billing temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

}