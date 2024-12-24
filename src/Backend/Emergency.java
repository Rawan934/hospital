package Backend;
import java.util.*;

public class Emergency {
    private List<Ambulance> ambulances;
    private List<Hospital> hospitals;
    private Graph cityMap;

    // Constructor
    public Emergency(List<Ambulance> ambulances, List<Hospital> hospitals, Graph cityMap) {
        this.ambulances = ambulances;
        this.hospitals = hospitals;
        this.cityMap = cityMap;
    }

    // Handle the entire emergency process
    public String handleEmergency(int patientLocation) {
        StringBuilder result = new StringBuilder();

        result.append("Emergency detected at location: ").append(patientLocation).append("\n");

        // Choose the best ambulance
        Ambulance bestAmbulance = chooseBestAmbulance(patientLocation);
        if (bestAmbulance == null) {
            result.append("No available ambulances to dispatch.\n");
            return result.toString();
        }

        result.append("Dispatching ").append(bestAmbulance.getId()).append("\n");

        // Find the closest hospital with available resources
        Hospital bestHospital = findBestHospital(bestAmbulance);
        if (bestHospital != null) {
            result.append(bestAmbulance.getId())
                    .append(" handled patient and on route to ").append(bestHospital.getName()).append("\n");
            bestHospital.admitPatient();
        } else {
            result.append("No available hospitals with required resources.\n");
        }

        return result.toString();
    }

    // Choose the best ambulance based on patient location
    private Ambulance chooseBestAmbulance(int patientLocation) {
        Ambulance bestAmbulance = null;
        int shortestDistance = Integer.MAX_VALUE;
        int[] distances = cityMap.dijkstra(patientLocation); // Shortest distances from patient

        for (Ambulance ambulance : ambulances) {
            int distance = distances[ambulance.getCurrentLocation()]; // Distance to this ambulance

            if (distance < shortestDistance) {
                shortestDistance = distance;
                bestAmbulance = ambulance;
            }
        }

        return bestAmbulance;
    }

    // Find the best hospital based on ambulance location
    private Hospital findBestHospital(Ambulance ambulance) {
        Hospital bestHospital = null;
        int shortestDistance = Integer.MAX_VALUE;


        int[] distances = cityMap.dijkstra(ambulance.getCurrentLocation());

        for (Hospital hospital : hospitals) {
            if (hospital.canAcceptPatient() && hospital.hasEmergencyFacilities()) {
                int distance = distances[hospital.getLocation()]; // hospital's location

                if (distance < shortestDistance) {
                    shortestDistance = distance;
                    bestHospital = hospital;
                }
            }
        }

        return bestHospital;
    }
}