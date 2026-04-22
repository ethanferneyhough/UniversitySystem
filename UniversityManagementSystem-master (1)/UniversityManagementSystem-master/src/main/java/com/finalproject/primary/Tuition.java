package com.finalproject.primary;

//two variable tuition class for tracking fee and status separate from student class
public class Tuition {
    private float fee;
    private boolean paymentStatus;

    public Tuition(){
        this.fee = 0;
        this.paymentStatus = false;
    }
    public Tuition(float fee, boolean paymentStatus){
        this.fee = fee;
        this.paymentStatus = paymentStatus;
    }
    public float getFee(){
        return this.fee;
    }
    public void setFee(float fee){
        this.fee = fee;
    }
    public boolean getPaymentStatus(){
        return this.paymentStatus;
    }
    public void setPaymentStatus(boolean paymentStatus){
        this.paymentStatus = paymentStatus;
    }
}
