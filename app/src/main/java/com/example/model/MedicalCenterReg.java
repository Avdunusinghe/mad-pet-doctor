package com.example.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MedicalCenterReg implements Parcelable {
    private String medicalCenterNo;
    private String name;
    private String address;
    private String telNo;
    private String email;;
    private String medicalCenterId;

    public MedicalCenterReg() {

    }

    public MedicalCenterReg(String medicalCenterNo, String name, String address, String telNo, String email, String medicalCenterId) {
        this.medicalCenterNo = medicalCenterNo;
        this.name = name;
        this.address = address;
        this.telNo = telNo;
        this.email = email;
        this.medicalCenterId = medicalCenterId;
    }

    protected MedicalCenterReg(Parcel in) {
        medicalCenterNo = in.readString();
        name = in.readString();
        address = in.readString();
        telNo = in.readString();
        email = in.readString();
        medicalCenterId = in.readString();
    }

    public static final Creator<MedicalCenterReg> CREATOR = new Creator<MedicalCenterReg>() {
        @Override
        public MedicalCenterReg createFromParcel(Parcel in) {
            return new MedicalCenterReg(in);
        }

        @Override
        public MedicalCenterReg[] newArray(int size) {
            return new MedicalCenterReg[size];
        }
    };

    public String getMedicalCenterNo() {
        return medicalCenterNo;
    }

    public void setMedicalCenterNo(String medicalCenterNo) {
        this.medicalCenterNo = medicalCenterNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMedicalCenterId() {
        return medicalCenterId;
    }

    public void setMedicalCenterId(String medicalCenterId) {
        this.medicalCenterId = medicalCenterId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(medicalCenterNo);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(telNo);
        dest.writeString(email);
        dest.writeString(medicalCenterId);
    }
}
