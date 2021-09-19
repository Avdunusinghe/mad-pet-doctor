package com.example.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DoctorReg implements Parcelable {
    private String docPic;
    private String fullName;
    private String docLicenseNo;
    private String qualification;
    private String medicalCenter;
    private String address;
    private String telNo;
    private String email;
    private String doctorId;

    public DoctorReg(){

    }

    public DoctorReg(String docPic, String fullName, String docLicenseNo, String qualification, String medicalCenter, String address, String telNo, String email, String doctorId) {
        this.docPic = docPic;
        this.fullName = fullName;
        this.docLicenseNo = docLicenseNo;
        this.qualification = qualification;
        this.medicalCenter = medicalCenter;
        this.address = address;
        this.telNo = telNo;
        this.email = email;
        this.doctorId = doctorId;
    }

    protected DoctorReg(Parcel in) {
        docPic = in.readString();
        fullName = in.readString();
        docLicenseNo = in.readString();
        qualification = in.readString();
        medicalCenter = in.readString();
        address = in.readString();
        telNo = in.readString();
        email = in.readString();
        doctorId = in.readString();
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

    public String getDocPic() {
        return docPic;
    }

    public void setDocPic(String docPic) {
        this.docPic = docPic;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDocLicenseNo() {
        return docLicenseNo;
    }

    public void setDocLicenseNo(String docLicenseNo) {
        this.docLicenseNo = docLicenseNo;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getMedicalCenter() {
        return medicalCenter;
    }

    public void setMedicalCenter(String medicalCenter) {
        this.medicalCenter = medicalCenter;
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

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(docPic);
        dest.writeString(fullName);
        dest.writeString(docLicenseNo);
        dest.writeString(qualification);
        dest.writeString(medicalCenter);
        dest.writeString(address);
        dest.writeString(telNo);
        dest.writeString(email);
        dest.writeString(doctorId);
    }
}
