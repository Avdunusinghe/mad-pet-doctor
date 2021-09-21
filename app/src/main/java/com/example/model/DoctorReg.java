package com.example.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DoctorReg implements Parcelable {
    private String DoctorId;
    private String FullName;
    private String DocLicenseNo;
    private String Qualification;
    private String MedicalCenter;
    private String Address;
    private String TelNo;
    private String Email;

    public DoctorReg(){

    }

    public DoctorReg(String doctorId, String fullName, String docLicenseNo, String qualification, String medicalCenter, String address, String telNo, String email) {
        DoctorId = doctorId;
        FullName = fullName;
        DocLicenseNo = docLicenseNo;
        Qualification = qualification;
        MedicalCenter = medicalCenter;
        Address = address;
        TelNo = telNo;
        Email = email;
    }

    protected DoctorReg(Parcel in) {
        DoctorId = in.readString();
        FullName = in.readString();
        DocLicenseNo = in.readString();
        Qualification = in.readString();
        MedicalCenter = in.readString();
        Address = in.readString();
        TelNo = in.readString();
        Email = in.readString();
    }

    public static final Creator<DoctorReg> CREATOR = new Creator<DoctorReg>() {
        @Override
        public DoctorReg createFromParcel(Parcel in) {
            return new DoctorReg(in);
        }

        @Override
        public DoctorReg[] newArray(int size) {
            return new DoctorReg[size];
        }
    };

    public String getDoctorId() {
        return DoctorId;
    }

    public void setDoctorId(String doctorId) {
        DoctorId = doctorId;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getDocLicenseNo() {
        return DocLicenseNo;
    }

    public void setDocLicenseNo(String docLicenseNo) {
        DocLicenseNo = docLicenseNo;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String qualification) {
        Qualification = qualification;
    }

    public String getMedicalCenter() {
        return MedicalCenter;
    }

    public void setMedicalCenter(String medicalCenter) {
        MedicalCenter = medicalCenter;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(DoctorId);
        dest.writeString(FullName);
        dest.writeString(DocLicenseNo);
        dest.writeString(Qualification);
        dest.writeString(MedicalCenter);
        dest.writeString(Address);
        dest.writeString(TelNo);
        dest.writeString(Email);
    }
}
