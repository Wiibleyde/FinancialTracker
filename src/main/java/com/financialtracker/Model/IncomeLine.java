package com.financialtracker.Model;

public class IncomeLine {
    private String period;
    private Float salary;
    private Float grant;
    private Float businessSalary;
    private Float passiveIncome;
    private Float other;

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Float getGrant() {
        return grant;
    }

    public void setGrant(Float grant) {
        this.grant = grant;
    }

    public Float getBusinessSalary() {
        return businessSalary;
    }

    public void setBusinessSalary(Float businessSalary) {
        this.businessSalary = businessSalary;
    }

    public Float getPassiveIncome() {
        return passiveIncome;
    }

    public void setPassiveIncome(Float passiveIncome) {
        this.passiveIncome = passiveIncome;
    }

    public Float getOther() {
        return other;
    }

    public void setOther(Float other) {
        this.other = other;
    }

    public Float getTotal() {
        return salary + grant + businessSalary + passiveIncome + other;
    }
}
