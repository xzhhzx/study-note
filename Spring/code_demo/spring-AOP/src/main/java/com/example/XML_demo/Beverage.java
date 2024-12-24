package com.example.XML_demo;

public class Beverage {

    private String cupSize;
    private String sweet;
    private boolean addIce;


    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public String getSweet() {
        return sweet;
    }

    public void setSweet(String sweet) {
        this.sweet = sweet;
    }

    public boolean isAddIce() {
        return addIce;
    }

    public void setAddIce(boolean addIce) {
        this.addIce = addIce;
    }

    @Override
    public String toString() {
        return "Beverage{" +
                "cupSize='" + cupSize + '\'' +
                ", sweet='" + sweet + '\'' +
                ", addIce=" + addIce +
                '}';
    }
}
