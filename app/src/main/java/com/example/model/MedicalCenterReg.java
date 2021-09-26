package com.example.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MedicalCenterReg implements Parcelable {
    private String MedicalCenterId;
    private String MedicalCenterNo;
    private String Name;
    private String Address;
    private String TelNo;
    private String Email;;

    public MedicalCenterReg() {

    }

    public MedicalCenterReg(String medicalCenterId, String medicalCenterNo, String name, String address, String telNo, String email) {
        MedicalCenterId = medicalCenterId;
        MedicalCenterNo = medicalCenterNo;
        Name = name;
        Address = address;
        TelNo = telNo;
        Email = email;
    }



    protected MedicalCenterReg(Parcel in) {
        MedicalCenterId = in.readString();
        MedicalCenterNo = in.readString();
        Name = in.readString();
        Address = in.readString();
        TelNo = in.readString();
        Email = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(MedicalCenterId);
        dest.writeString(MedicalCenterNo);
        dest.writeString(Name);
        dest.writeString(Address);
        dest.writeString(TelNo);
        dest.writeString(Email);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public String getMedicalCenterId() {
        return MedicalCenterId;
    }

    public void setMedicalCenterId(String medicalCenterId) {
        MedicalCenterId = medicalCenterId;
    }

    public String getMedicalCenterNo() {
        return MedicalCenterNo;
    }

    public void setMedicalCenterNo(String medicalCenterNo) {
        MedicalCenterNo = medicalCenterNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getTelNo() {
        return TelNo;
    }

    public void setTelNo(String telNo) {
        TelNo = telNo;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
