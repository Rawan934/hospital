package Backend;

import java.util.ArrayList;
import java.util.List;

public class AppointmentNode {
    Appointment data;
    AppointmentNode next;
    public AppointmentNode(Appointment data) {
        this.data = data;
        this.next = null;
    }
}