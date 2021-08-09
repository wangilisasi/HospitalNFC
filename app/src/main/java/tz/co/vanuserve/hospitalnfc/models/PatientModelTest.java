package tz.co.vanuserve.hospitalnfc.models;

public class PatientModelTest {

    private String name,room,disease,doctor;

    public PatientModelTest(){}// Required by Firestore

    public PatientModelTest(String name, String room, String disease,String doctor) {
        this.name = name;
        this.room = room;
        this.disease = disease;
        this.doctor=doctor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }
}
