package com.preloode.panel.repository.transaction;

import com.preloode.panel.model.transaction.TicketNumberLogData;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TicketNumberLogDataRepository extends MongoRepository<TicketNumberLogData, String> {


}
