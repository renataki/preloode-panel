package com.preloode.panel.model.transaction;

import com.preloode.panel.enumeration.transaction.TransactionType;
import com.preloode.panel.model.global.Base;
import com.preloode.panel.model.global.TimestampReference;
import org.springframework.data.mongodb.core.index.Indexed;


public class TicketNumber extends Base {


    @Indexed(unique = true)
    private String last;

    @Indexed(unique = true)
    private TransactionType type;


    public TicketNumber() {


    }


    public TicketNumber(String id, TimestampReference created, TimestampReference modified, String last, TransactionType type) {

        super(id, created, modified);
        this.last = last;
        this.type = type;

    }


    public String getLast() {

        return last;

    }


    public void setLast(String last) {

        this.last = last;

    }


    public TransactionType getType() {

        return type;

    }


    public void setType(TransactionType type) {

        this.type = type;

    }


}
