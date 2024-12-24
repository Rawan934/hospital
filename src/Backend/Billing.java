package Backend;

import java.util.ArrayList;
import java.util.List;

public class Billing {
    private int patientID;
    private double billingAmount;
    private List<Double> paymentHistory;
    private static List<Billing> Billings = new ArrayList<>();

    public Billing(int patientID, double initialAmount) {
        this.patientID = patientID;
        this.billingAmount = initialAmount;
        this.paymentHistory = new ArrayList<>();
//        String serializedPayments = new Gson().toJson(paymentHistory);
//        DatabaseManagement.addBillingRecord(patientID,initialAmount,serializedPayments);
    }

    public Billing(int patientID, double initialAmount, List<Double> payments) {
        this.patientID = patientID;
        this.billingAmount = initialAmount;
        this.paymentHistory = payments;
    }

    // Generate a bill and add the specified amount to the total billing amount
    public String generateBill(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid billing amount.");
            return null;
        }
        billingAmount += amount;
        DatabaseManagement.adjustBillingAmount(this.patientID, amount);
        System.out.println("Bill generated: $" + amount + ". Total due: $" + billingAmount);
        return "Bill generated: $" + amount + ". Total due: $" + billingAmount;
    }

    public String generateBill() {
        System.out.println("Total due: $" + billingAmount);
        return "Total due: $" + billingAmount;
    }

    // Add a payment to the history and reduce the billing amount
    public void addPayment(double payment) {
        if (payment <= 0) {
            System.out.println("Invalid payment amount.");
            return;
        }
        if (payment > billingAmount) {
            System.out.println("Payment exceeds the due amount. Accepting partial payment.");
            return;
        }
        paymentHistory.add(payment);
        billingAmount -= payment;
        if (billingAmount < 0) {
            billingAmount = 0; // Ensure no negative balances
        }
        DatabaseManagement.addPayment(this.patientID, payment, paymentHistory); //I handle serialization inside of it
        System.out.println("Payment of $" + payment + " added. Remaining due: $" + billingAmount);
    }

    // Get the current payment status
    public String getPaymentStatus() {
        if (billingAmount == 0) {
            return "All payments are settled.";
        } else {
            return "Remaining due: $" + billingAmount;
        }
    }

    // Get the patient's billing details
    public String printBillingDetails() {
        return ("Patient ID: " + patientID +
                "\nTotal Due: $" + billingAmount +
                "\nPayment History: " + paymentHistory);
    }

    public int getPatientID() {
        return patientID;
    }

    // Getter for billingAmount
    public double getBillingAmount() {
        return billingAmount;
    }

    // Getter for paymentHistory
    public List<Double> getPaymentHistory() {
        return new ArrayList<>(paymentHistory); // ensure immutability
    }

    public static List<Billing> getBillings() {
        return Billings;
    }

    public static Billing searchBillingByPatientID(int patientID) {
        for (Billing billing : Billings) {
            if (billing.getPatientID() == patientID) {
                return billing; // Return the matching billing record
            }
        }
        System.out.println("No billing record found for Patient ID: " + patientID);
        return null; // Return null if no record is found
    }

    @Override
    public String toString() {
        return "Billing{" +
                "patientID=" + patientID +
                ", billingAmount=" + billingAmount +
                ", paymentHistory=" + paymentHistory +
                '}';
    }

}