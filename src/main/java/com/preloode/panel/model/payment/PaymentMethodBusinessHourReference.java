package com.preloode.panel.model.payment;

public class PaymentMethodBusinessHourReference {


    private PaymentMethodBusinessHourRangeReference friday;

    private PaymentMethodBusinessHourRangeReference monday;

    private PaymentMethodBusinessHourRangeReference saturday;

    private PaymentMethodBusinessHourRangeReference sunday;

    private PaymentMethodBusinessHourRangeReference thursday;

    private PaymentMethodBusinessHourRangeReference tuesday;

    private PaymentMethodBusinessHourRangeReference wednesday;


    public PaymentMethodBusinessHourReference() {


    }


    public PaymentMethodBusinessHourReference(PaymentMethodBusinessHourRangeReference friday, PaymentMethodBusinessHourRangeReference monday, PaymentMethodBusinessHourRangeReference saturday, PaymentMethodBusinessHourRangeReference sunday, PaymentMethodBusinessHourRangeReference thursday, PaymentMethodBusinessHourRangeReference tuesday, PaymentMethodBusinessHourRangeReference wednesday) {

        this.friday = friday;
        this.monday = monday;
        this.saturday = saturday;
        this.sunday = sunday;
        this.thursday = thursday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;

    }


    public PaymentMethodBusinessHourRangeReference getFriday() {

        return friday;

    }


    public void setFriday(PaymentMethodBusinessHourRangeReference friday) {

        this.friday = friday;

    }


    public PaymentMethodBusinessHourRangeReference getMonday() {

        return monday;

    }


    public void setMonday(PaymentMethodBusinessHourRangeReference monday) {

        this.monday = monday;

    }


    public PaymentMethodBusinessHourRangeReference getSaturday() {

        return saturday;

    }


    public void setSaturday(PaymentMethodBusinessHourRangeReference saturday) {

        this.saturday = saturday;

    }


    public PaymentMethodBusinessHourRangeReference getSunday() {

        return sunday;

    }


    public void setSunday(PaymentMethodBusinessHourRangeReference sunday) {

        this.sunday = sunday;

    }


    public PaymentMethodBusinessHourRangeReference getThursday() {

        return thursday;

    }


    public void setThursday(PaymentMethodBusinessHourRangeReference thursday) {

        this.thursday = thursday;

    }


    public PaymentMethodBusinessHourRangeReference getTuesday() {

        return tuesday;

    }


    public void setTuesday(PaymentMethodBusinessHourRangeReference tuesday) {

        this.tuesday = tuesday;

    }


    public PaymentMethodBusinessHourRangeReference getWednesday() {

        return wednesday;

    }


    public void setWednesday(PaymentMethodBusinessHourRangeReference wednesday) {

        this.wednesday = wednesday;

    }


}
