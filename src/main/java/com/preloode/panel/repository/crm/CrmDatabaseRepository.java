package com.preloode.panel.repository.crm;

import com.preloode.panel.enumeration.crm.CrmDatabaseStatus;
import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.model.blog.BlogPost;
import com.preloode.panel.model.crm.CrmDatabase;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CrmDatabaseRepository extends MongoRepository<CrmDatabase, String> {


    @Query("{'contact.emailAddress' : ?0}")
    public List<CrmDatabase> findByContactEmailAddress(String contactEmailAddress);


    @Query("{'contact.faxNumber' : ?0}")
    public List<CrmDatabase> findByContactFaxNumber(String contactFaxNumber);


    @Query("{'contact.lineId' : ?0}")
    public List<CrmDatabase> findByContactLineId(String contactLineId);


    @Query("{'contact.phoneNumber' : ?0}")
    public List<CrmDatabase> findByContactPhoneNumber(String contactPhoneNumber);


    @Query("{'contact.wechatId' : ?0}")
    public List<CrmDatabase> findByContactWechatId(String contactWechatId);


    @Query("{'contact.whatsappNumber' : ?0}")
    public List<CrmDatabase> findByContactWhatsappNumber(String contactWhatsappNumber);


    @Query("{'status' : ?0}")
    public List<CrmDatabase> findByStatusSort(Status status, Sort sort);


    @Query("{'group.id' : ?0, 'status' : ?1}")
    public List<CrmDatabase> findByGroupIdStatusPageable(String groupId, CrmDatabaseStatus status, Pageable pageable);


}
