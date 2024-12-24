package Backend;

import java.util.ArrayList;
import java.util.List;

public class PriorityQueue {
    private ArrayList<WaitingPatient> heap; // Underlying data structure

    // Constructor
    public PriorityQueue() {
        heap = new ArrayList<>();
    }

    // Add a patient
    public void add(WaitingPatient patient) {
        heap.add(patient); // Add at the end
        heapifyUp(heap.size() - 1);
        System.out.println("Added to priority queue: " + patient.getDetails());
    }

    // Remove and return the highest-priority patient
    public WaitingPatient remove() {
        if (heap.isEmpty()) {
            System.out.println("Priority queue is empty.");
            return null;
        }

        WaitingPatient topPatient = heap.get(0);
        WaitingPatient lastPatient = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, lastPatient); // Move last patient to root
            heapifyDown(0);
        }

        System.out.println("Removed from priority queue: " + topPatient.getDetails());
        return topPatient;
    }

    // LOok at the highest-priority patient without removing
    public WaitingPatient peek() {
        if (heap.isEmpty()) {
            System.out.println("Priority queue is empty.");
            return null;
        }
        return heap.get(0);
    }

    // Display all patients in the priority queue
    public List<String> getPatients() {
        if (heap.isEmpty()) {
            System.out.println("Priority queue is empty.");
            return null;
        }

        List<String> waitingPatients = new ArrayList<String>();
        for (WaitingPatient patient : heap) {
            waitingPatients.add(patient.getPatient().getName());
        }
    return waitingPatients;
    }

    // Get the size of the queue
    public int size() {
        return heap.size();
    }

    // Restore heap after addition "upward"
    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / 2;

        while (index > 0 && compare(heap.get(index), heap.get(parentIndex)) < 0) {
            swap(index, parentIndex);
            index = parentIndex; // To recheck the new parent
            parentIndex = (index - 1) / 2;
        }
    }

    // Restores heap structure after removal "downward"
    private void heapifyDown(int index) {
        int smallest = index;
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        // Check left child
        if (leftChild < heap.size() && compare(heap.get(leftChild), heap.get(smallest)) < 0) {
            smallest = leftChild;
        }

        // Check right child
        if (rightChild < heap.size() && compare(heap.get(rightChild), heap.get(smallest)) < 0) {
            smallest = rightChild;
        }

        // If the smallest is not the current node, swap and continue
        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    //Compare two WaitingPatient objects (by date and time)
    private int compare(WaitingPatient p1, WaitingPatient p2) {
        int dateComparison = p1.getRequestDate().compareTo(p2.getRequestDate());
        if (dateComparison != 0) {
            return dateComparison;
        }
        return p1.getRequestTime().compareTo(p2.getRequestTime());
    }

    // Swap two nodes in the heap
    private void swap(int i, int j) {
        WaitingPatient temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}