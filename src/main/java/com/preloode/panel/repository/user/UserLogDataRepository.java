package com.preloode.panel.repository.user;

import com.preloode.panel.model.user.UserLogData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserLogDataRepository extends MongoRepository<UserLogData, String> {


}
