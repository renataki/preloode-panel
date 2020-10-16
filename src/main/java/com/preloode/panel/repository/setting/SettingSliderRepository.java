package com.preloode.panel.repository.setting;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.model.setting.SettingSlider;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface SettingSliderRepository extends MongoRepository<SettingSlider, String> {


    @Query("{'branch.idList' : ?0, 'company.idList' : ?1, 'status' : ?2}")
    public List<SettingSlider> findByBranchIdListCompanyIdListStatusSort(String branchIdList, String companyIdList, Status status, Sort sort);


    @Query("{'name' : ?0}")
    public SettingSlider findByName(String name);


    @Query("{'status' : ?0}")
    public List<SettingSlider> findByStatusSort(Status status, Sort sort);


}
