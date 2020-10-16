package com.preloode.panel.repository.setting;

import com.preloode.panel.model.setting.Setting;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface SettingRepository extends MongoRepository<Setting, String> {


    @Query("{}")
    public Setting findOneSort(Sort sort);


}
