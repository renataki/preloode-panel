package com.preloode.panel.model.setting;

import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;


public class SettingMaintenanceReference {


    @Indexed
    private Date finish;

    @Indexed
    private Date next;


    public SettingMaintenanceReference() {


    }


    public SettingMaintenanceReference(Date finish, Date next) {

        this.finish = finish;
        this.next = next;

    }


    public Date getFinish() {

        return finish;

    }


    public void setFinish(Date finish) {

        this.finish = finish;

    }


    public Date getNext() {

        return next;

    }


    public void setNext(Date next) {

        this.next = next;

    }


}
