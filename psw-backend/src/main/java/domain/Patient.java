package domain;

public class Patient extends Person {

    private String medicalNumber;
    private long medicalRecordId;
    private double height;
    private double width;
    private String bloodType;
    private Iterable<Appointment> scheduledAppointments;
    private Iterable<Perscription> perscriptions;

}
