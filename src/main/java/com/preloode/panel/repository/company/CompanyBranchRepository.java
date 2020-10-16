package com.preloode.panel.repository.company;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.model.company.CompanyBranch;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface CompanyBranchRepository extends MongoRepository<CompanyBranch, String> {


    @Query("{'contact.emailAddress' : ?0}")
    public List<CompanyBranch> findByContactEmailAddress(String contactEmailAddress);


    @Query("{'contact.faxNumber' : ?0}")
    public List<CompanyBranch> findByContactFaxNumber(String contactFaxNumber);


    @Query("{'contact.lineId' : ?0}")
    public List<CompanyBranch> findByContactLineId(String contactLineId);


    @Query("{'contact.phoneNumber' : ?0}")
    public List<CompanyBranch> findByContactPhoneNumber(String contactPhoneNumber);


    @Query("{'contact.wechatId' : ?0}")
    public List<CompanyBranch> findByContactWechatId(String contactWechatId);


    @Query("{'contact.whatsappNumber' : ?0}")
    public List<CompanyBranch> findByContactWhatsappNumber(String contactWhatsappNumber);


    @Query("{'name' : ?0}")
    public CompanyBranch findByName(String name);


    @Query("{'status' : ?0}")
    public List<CompanyBranch> findByStatusSort(Status status, Sort sort);


}
