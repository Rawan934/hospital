package Backend;
public class Hospital {
    private String name;
    private int capacity; // Total bed capacity
    private int availableBeds; // Current available beds
    private int location; // Node index in the graph
    private boolean hasEmergencyFacilities; // Whether hospital has emergency facilities

    // Constructor
    public Hospital(String name, int capacity, int location, boolean hasEmergencyFacilities) {
        this.name = name;
        this.capacity = capacity;
        this.availableBeds = capacity;
        this.location = location;
        this.hasEmergencyFacilities = hasEmergencyFacilities;
    }

    // Check if the hospital can accept a new patient
    public boolean canAcceptPatient() {
        return availableBeds > 0;
    }

    // Admit a patient (reduce available beds)
    public void admitPatient() {
        if (canAcceptPatient()) {
            availableBeds--;
        }
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getLocation() {
        return location;
    }

    public boolean hasEmergencyFacilities() {
        return hasEmergencyFacilities;
    }

    public int getAvailableBeds() {
        return availableBeds;
    }

    // Utility method to reset available beds (for testing or simulation)
    public void resetBeds() {
        this.availableBeds = this.capacity;
    }
}
