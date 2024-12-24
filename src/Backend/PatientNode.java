package Backend;

public class PatientNode {
    Patient patient;
    PatientNode left, right;
    int height;
    public PatientNode(Patient patient) {
        this.patient = patient;
        left = right = null;
        this.height = 1;
    }
}