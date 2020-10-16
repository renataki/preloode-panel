package com.preloode.panel.repository.company;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.model.company.Company;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {


    @Query("{'contact.emailAddress' : ?0}")
    public List<Company> findByContactEmailAddress(String contactEmailAddress);


    @Query("{'contact.faxNumber' : ?0}")
    public List<Company> findByContactFaxNumber(String contactFaxNumber);


    @Query("{'contact.lineId' : ?0}")
    public List<Company> findByContactLineId(String contactLineId);


    @Query("{'contact.phoneNumber' : ?0}")
    public List<Company> findByContactPhoneNumber(String contactPhoneNumber);


    @Query("{'contact.wechatId' : ?0}")
    public List<Company> findByContactWechatId(String contactWechatId);


    @Query("{'contact.whatsappNumber' : ?0}")
    public List<Company> findByContactWhatsappNumber(String contactWhatsappNumber);


    @Query("{'name' : ?0}")
    public Company findByName(String name);


    @Query("{'status' : ?0}")
    public List<Company> findByStatusSort(Status status, Sort sort);


}
