package com.preloode.panel.repository.user;

import com.preloode.panel.model.user.UserLog;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserLogRepository extends MongoRepository<UserLog, String> {


    @Query("{'authentication' : ?0}")
    public List<UserLog> findByAuthenticationSort(String authentication, Sort sort);


    @Query("{'created.user.id' : ?0}")
    public List<UserLog> findByCreatedUserIdSort(String userId, Sort sort);


}
