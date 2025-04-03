package com.financialtracker;

public class Line {
    private String period;
    private Float total;
    private Float housing;
    private Float food;
    private Float exits;
    private Float transport;
    private Float travel;
    private Float taxes;
    private Float other;

    public Line() {}

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Float getHousing() {
        return housing;
    }

    public void setHousing(Float housing) {
        this.housing = housing;
    }

    public Float getFood() {
        return food;
    }

    public void setFood(Float food) {
        this.food = food;
    }

    public Float getExits() {
        return exits;
    }

    public void setExits(Float exits) {
        this.exits = exits;
    }

    public Float getTransport() {
        return transport;
    }

    public void setTransport(Float transport) {
        this.transport = transport;
    }

    public Float getTravel() {
        return travel;
    }

    public void setTravel(Float travel) {
        this.travel = travel;
    }

    public Float getTaxes() {
        return taxes;
    }

    public void setTaxes(Float taxes) {
        this.taxes = taxes;
    }

    public Float getOther() {
        return other;
    }

    public void setOther(Float other) {
        this.other = other;
    }
}
