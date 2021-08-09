package tz.co.vanuserve.hospitalnfc.models;

public class PatientModel {
    String patientName;
    String roomNo;
    String disease;
    String doctor;
    int patientImage;

    public PatientModel(int patientImage, String patientName, String roomNo, String disease, String doctor) {
        this.patientName = patientName;
        this.roomNo = roomNo;
        this.disease = disease;
        this.doctor = doctor;
        this.patientImage=patientImage;
    }

    public int getPatientImage() {
        return patientImage;
    }

    public void setPatientImage(int patientImage) {
        this.patientImage = patientImage;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
}