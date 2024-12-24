package Backend;
public class Ambulance {
    private String id;
    private int currentLocation;
    private boolean available;

    // Constructor
    public Ambulance(String id, int currentLocation, boolean available) {
        this.id = id;
        this.currentLocation = currentLocation;
        this.available = available;
    }

    // Getters
    public String getId() {
        return id;
    }

    public int getCurrentLocation() {
        return currentLocation;
    }

    public boolean isAvailable() {
        return available;
    }

    // Setters
    public void setCurrentLocation(int currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Ambulance{" +
                "id='" + id + '\'' +
                ", currentLocation=" + currentLocation +
                ", available=" + available +
                '}';
    }
}
