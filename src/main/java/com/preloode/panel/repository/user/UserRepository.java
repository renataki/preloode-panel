package com.preloode.panel.repository.user;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.enumeration.user.UserType;
import com.preloode.panel.model.user.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends MongoRepository<User, String> {


    @Query("{'contact.emailAddress' : ?0}")
    public List<User> findByContactEmailAddress(String contactEmailAddress);


    @Query("{'contact.faxNumber' : ?0}")
    public List<User> findByContactFaxNumber(String contactFaxNumber);


    @Query("{'contact.lineId' : ?0}")
    public List<User> findByContactLineId(String contactLineId);


    @Query("{'contact.phoneNumber' : ?0}")
    public List<User> findByContactPhoneNumber(String contactPhoneNumber);


    @Query("{'contact.wechatId' : ?0}")
    public List<User> findByContactWechatId(String contactWechatId);


    @Query("{'contact.whatsappNumber' : ?0}")
    public List<User> findByContactWhatsappNumber(String contactWhatsappNumber);


    @Query("{'id': ?0, 'status' : ?1, 'type' : {$ne : ?2}}")
    public List<User> findByIdStatusNotTypeSort(String id, Status status, UserType type, Sort sort);


    @Query("{'id' : ?0, 'username' : ?1}")
    public User findByIdUsername(String id, String username);


    @Query("{'id' : ?0, 'username' : ?1, 'account.usernameList' : ?2}")
    public User findByIdUsernameAccountUsername(String id, String username, String accountUsername);


    @Query("{'status' : ?0, 'type' : {$ne : ?1}}")
    public List<User> findByStatusNotTypeSort(Status status, UserType type, Sort sort);


    @Query("{'status' : ?0}")
    public List<User> findByStatusSort(Status status, Sort sort);


    @Query("{'username' : ?0}")
    public User findByUsername(String username);


}
