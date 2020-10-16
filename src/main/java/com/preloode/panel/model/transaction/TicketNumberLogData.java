package com.preloode.panel.model.transaction;

import com.preloode.panel.enumeration.transaction.TransactionType;
import com.preloode.panel.model.global.Base;
import com.preloode.panel.model.global.TimestampReference;
import org.springframework.data.mongodb.core.index.Indexed;


public class TicketNumberLogData extends Base {


    @Indexed(unique = true)
    private String last;

    @Indexed
    private String ticketNumberId;

    @Indexed
    private TransactionType type;


    public TicketNumberLogData() {


    }


    public TicketNumberLogData(String id, TimestampReference created, TimestampReference modified, String last, String ticketNumberId, TransactionType type) {

        super(id, created, modified);
        this.last = last;
        this.ticketNumberId = ticketNumberId;
        this.type = type;

    }


    public String getLast() {

        return last;

    }


    public void setLast(String last) {

        this.last = last;

    }


    public String getTicketNumberId() {

        return ticketNumberId;

    }


    public void setTicketNumberId(String ticketNumberId) {

        this.ticketNumberId = ticketNumberId;

    }


    public TransactionType getType() {

        return type;

    }


    public void setType(TransactionType type) {

        this.type = type;

    }


}
