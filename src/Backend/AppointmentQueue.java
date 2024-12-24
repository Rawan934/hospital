package Backend;

import java.util.ArrayList;
import java.util.List;

public class AppointmentQueue {
    private AppointmentNode front; // Points to the first node
    private AppointmentNode rear;  // Points to the last node
    private int size;   // Tracks the number of elements in the queue
    // Constructor
    public AppointmentQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    // Enqueue: Add an appointment to the queue
    public void enqueue(Appointment appointment) {
        AppointmentNode newNode = new AppointmentNode(appointment);
        if (rear == null) { // Queue is empty
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    //Remove and return the first appointment in the queue
    public Appointment dequeue() {
        if (front == null) {
            return null;
        }
        Appointment appointment = front.data;
        front = front.next;
        if (front == null) { // Queue is now empty
            rear = null;
        }
        size--;
        return appointment;
    }

    // Peek: View the first appointment without removing it
    public Appointment peek() {
        if (front == null) {
            return null;
        }
        return front.data;
    }

    // return all appointments in the queue
    public ArrayList<Appointment> allAppointments() {
        if (front == null) {
            return null;
        }
        ArrayList<Appointment> appointments = new ArrayList<>();
        AppointmentNode current = front;
        while (current != null) {
            appointments.add(current.data);
            current = current.next;
        }
        return appointments;
    }

    public Appointment findAppointmentById(int appointmentID) {
        AppointmentNode current = front; // Start from the front of the queue

        while (current != null) {
            if (current.data.getAppointmentID() == appointmentID) {
                return current.data; // Return the appointment if found
            }
            current = current.next; // Move to the next node
        }

        // Return null if no appointment matches the ID
        System.out.println("No appointment found with ID: " + appointmentID);
        return null;
    }

    // Cancel a specific appointment by ID
    public String cancelAppointment(int appointmentID) {
        if (front == null) {
            return "Queue is Empty";
        }

        AppointmentNode current = front;
        AppointmentNode previous = null;

        while (current != null && current.data.getAppointmentID() != appointmentID) {
            previous = current;
            current = current.next;
        }

        if (current == null) { // Appointment not found
            return "Appointment " + appointmentID + " isn't found";
        }

        // Remove the appointment
        if (previous == null) { // Removing the first node only
            front = front.next;
        } else {
            previous.next = current.next;
        }

        if (current == rear) { // Removing the last node
            rear = previous;
        }

        size--;
        return ("Canceled appointment: " + current.data.toString());
    }

    // Convert the queue to a List
    public List<Appointment> toList() {
        List<Appointment> appointments = new ArrayList<>();
        AppointmentNode current = front; // Start from the front of the queue
        while (current != null) {
            appointments.add(current.data); // Add each appointment to the list
            current = current.next; // Move to the next node
        }
        return appointments;
    }

    // Get the size of the queue
    public int size() {
        return size;
    }
}

