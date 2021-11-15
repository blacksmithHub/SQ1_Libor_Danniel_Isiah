package com.example.libor.sq1_libor_danniel_isiah;

import java.io.Serializable;

public class OrderDetailClass implements Serializable{

    private int teaNo;
    private int addOn;
    private int Size;
    private int Sugar;
    private int Qty;
    private boolean Order;

    public OrderDetailClass() {
        this.teaNo = 0;
        this.addOn = 0;
        this.Size = 0;
        this.Sugar = 0;
        this.Qty = 0;
        this.Order = false;
    }
    public OrderDetailClass(int teaNo, int addOn, int sugar, int size, int qty, boolean order) {
        this.teaNo = teaNo;
        this.addOn = addOn;
        this.Sugar = sugar;
        this.Size =  size;
        this.Qty = qty;
        this.Order = order;

    }
    public int getTeaNo() {
        return teaNo;
    }
    public void setTeaNo(int teaNo) {
        this.teaNo = teaNo;
    }
    public int getAddOn() {
        return addOn;
    }
    public void setAddOn(int addOn) {
        this.addOn = addOn;
    }
    public int getSize() {
        return Size;

    }
    public void setSize(int size) {
        this.Size = size;
    }
    public int getSugar() {
        return Sugar;
    }

    public void setSugar(int sugar) {
        this.Sugar = sugar;
    }
    public int getQty() {
        return Qty;
    }

    public void setQty(int Qty) {
    this.Qty = Qty;
    }

    public boolean isOrder() {
        return Order;
    }

    public void setOrder(boolean order) {
        this.Order = order;
    }

    @Override
    public String toString() {
        return "OrderDetailClass{" +
                "teaNo=" + teaNo +
                ", AddOn= " + addOn +
                ", Size=" + Size +
                ", Sugar=" + Sugar +
                ", Qty=" + Qty +
                ", Order=" + Order +
                '}';
    }

}
