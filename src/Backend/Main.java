package Backend;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // city map graph
        Graph cityMap = new Graph(6);

        // Add edges with forward and backward weights
        cityMap.addEdge(0, 1, 5, 7); // Edge from 0 to 1: 5, back: 7
        cityMap.addEdge(1, 2, 10, 8); // Edge from 1 to 2: 10, back: 8
        cityMap.addEdge(2, 3, 3, 4); // Edge from 2 to 3: 3, back: 4
        cityMap.addEdge(3, 4, 1, 1); // Edge from 3 to 4: 1, back: 1
        cityMap.addEdge(4, 5, 2, 2); // Edge from 4 to 5: 2, back: 2
        cityMap.addEdge(0, 5, 15, 10); // Edge from 0 to 5: 15, back: 10

        // Initialize ambulances
        List<Ambulance> ambulances = new ArrayList<>();
        ambulances.add(new Ambulance("Ambulance1", 0, true)); // At location 0
        ambulances.add(new Ambulance("Ambulance2", 2, true)); // At location 2
        ambulances.add(new Ambulance("Ambulance3", 4, true)); // At location 4

        // Initialize hospitals
        List<Hospital> hospitals = new ArrayList<>();
        hospitals.add(new Hospital("Hospital A", 1, 5, true)); // At location 1, 5 beds, emergency facilities
        hospitals.add(new Hospital("Hospital B", 3, 2, true)); // At location 3, 2 beds, emergency facilities
        hospitals.add(new Hospital("Hospital C", 5, 0, false)); // At location 5, 0 beds, no emergency facilities

        // Initialize the emergency handling system
        Emergency emergencySystem = new Emergency(ambulances, hospitals, cityMap);

        // Simulate an emergency
        int patientLocation = 0; // Emergency detected at location 0
        String result = emergencySystem.handleEmergency(patientLocation);

        // Print the results
        System.out.println(result);

        // Simulate another emergency
        patientLocation = 3; // Emergency detected at location 3
        result = emergencySystem.handleEmergency(patientLocation);
        System.out.println(result);
    }
}

