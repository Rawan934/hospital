package Backend;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet; // to assign random unique id's to patient
import java.util.Random;
import java.util.Set;

public class Patient {
    //private static PatientBST patientBST = new PatientBST();
    private final int patientID;
    private final String name;
    private int age;
    private String contactInfo;
    private String medicalHistory;
    private List<String> visitRecords;
    private static Set<Integer> usedPatientIDs = new HashSet<>(); // Set to store used IDs
    private static Random random = new Random();

    public Patient(String name, int age, String contactInfo, String medicalHistory) {
        this.patientID =generateUniquePatientID();
        this.name = name;
        this.age = age;
        this.contactInfo = contactInfo;
        this.medicalHistory = medicalHistory;
        this.visitRecords = new ArrayList<>();
//        Gson gson = new Gson();
//        String visits = gson.toJson(visitRecords);
//        DatabaseManagement.addPatient(this.patientID,this.name,this.age,this.contactInfo,this.medicalHistory,visits);
    }
    // Loading patient
    public Patient(int patientID, String name, int age, String contactInfo, String medicalHistory, List<String> visitRecords){
        this.patientID =patientID;
        this.name = name;
        this.age = age;
        this.contactInfo = contactInfo;
        this.medicalHistory = medicalHistory;
        this.visitRecords = visitRecords;
    }
    private static int generateUniquePatientID() {
        int newID;
        do {
            newID = random.nextInt(1000000); // Generate a random ID between 0 and 999,999
        } while (usedPatientIDs.contains(newID)); // Check if the ID has already been used
        usedPatientIDs.add(newID); // Add to the set to mark it as used
        return newID;
    }

    public void updateContactInfo(String newContactInfo) {
        this.contactInfo = newContactInfo;
        DatabaseManagement.updateContactInfo(this.patientID,newContactInfo);
    }

    public void addVisitRecord(String visitRecord) {
        this.visitRecords.add(visitRecord);
        DatabaseManagement.updateVisitRecords(this.patientID,visitRecords);
    }
    public void updateMedicalHistory(String medicalHistory){
        this.medicalHistory = this.medicalHistory +", " + medicalHistory;
        DatabaseManagement.updateMedicalHistory(this.patientID,medicalHistory);
    }

    public String getPatientInfo() {
        return "Patient ID: " + patientID + "\nName: " + name + "\nAge: " + age +
                "\nContact Info: " + contactInfo + "\nMedical History: " + medicalHistory +
                "\nVisit Records: " + visitRecords;
    }

    public static Set<Integer> getUsedPatientIDs() {
        return usedPatientIDs;
    }

    public int getAge() {
        return age;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public List<String> getVisitRecords() {
        return visitRecords;
    }

    public int getPatientID() {
        return patientID;
    }

    public String getName() {
        return name;
    }
}

