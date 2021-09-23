package com.example.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CardPaymentsModal implements Parcelable {
    private String CardHolderName;
    private String  CardNumber;
    private String  ExpirationDate;
    private String CVCNo;
    private String PaymentId;

    public CardPaymentsModal(){

    }

    public CardPaymentsModal(String cardHolderName, String cardNumber, String expirationDate, String CVCNo, String paymentId) {
        this.CardHolderName = cardHolderName;
        this.CardNumber = cardNumber;
        this.ExpirationDate = expirationDate;
        this.CVCNo = CVCNo;
        this.PaymentId = paymentId;
    }

    protected CardPaymentsModal(Parcel in) {
        CardHolderName = in.readString();
        CardNumber = in.readString();
        ExpirationDate = in.readString();
        CVCNo = in.readString();
        PaymentId = in.readString();
    }

    public static final Creator<CardPaymentsModal> CREATOR = new Creator<CardPaymentsModal>() {
        @Override
        public CardPaymentsModal createFromParcel(Parcel in) {
            return new CardPaymentsModal(in);
        }

        @Override
        public CardPaymentsModal[] newArray(int size) {
            return new CardPaymentsModal[size];
        }
    };

    public String getCardHolderName() {
        return CardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        CardHolderName = cardHolderName;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return ExpirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        ExpirationDate = expirationDate;
    }

    public String getCVCNo() {
        return CVCNo;
    }

    public void setCVCNo(String CVCNo) {
        this.CVCNo = CVCNo;
    }

    public String getPaymentId() {
        return PaymentId;
    }

    public void setPaymentId(String paymentId) {
        PaymentId = paymentId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(CardHolderName);
        parcel.writeString(CardNumber);
        parcel.writeString(ExpirationDate);
        parcel.writeString(CVCNo);
        parcel.writeString(PaymentId);
    }
}
