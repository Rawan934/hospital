package Backend;

import java.util.*;

public class PatientBST {
    private PatientNode root;

    public void addPatient(Patient patient) {
        root = addRecursive(root, patient);
    }

    private PatientNode addRecursive(PatientNode current, Patient patient) {
        if (current == null) {
            return new PatientNode(patient);
        }

        if (patient.getPatientID() < current.patient.getPatientID()) {
            current.left = addRecursive(current.left, patient);
        } else if (patient.getPatientID() > current.patient.getPatientID()) {
            current.right = addRecursive(current.right, patient);
        } else {
            // Patient ID already exists
            return current;
        }

        return current;
    }

    public Patient findPatient(int patientID) {
        return findRecursive(root, patientID);
    }

    private Patient findRecursive(PatientNode current, int patientID) {
        if (current == null) {
            return null;
        }

        if (patientID == current.patient.getPatientID()) {
            return current.patient;
        }

        return patientID < current.patient.getPatientID()
                ? findRecursive(current.left, patientID)
                : findRecursive(current.right, patientID);
    }

    public List<Patient> toList() {
        List<Patient> patients = new ArrayList<>();
        inOrderTraversal(root, patients);
        return patients;
    }

    private void inOrderTraversal(PatientNode node, List<Patient> patients) {
        if (node != null) {
            inOrderTraversal(node.left, patients); // Visit left subtree
            patients.add(node.patient);            // Add current node's patient
            inOrderTraversal(node.right, patients);// Visit right subtree
        }
    }

}