package com.preloode.panel.repository.shop;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.model.shop.ShopBrand;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ShopBrandRepository extends MongoRepository<ShopBrand, String> {


    @Query("{'branch.idList' : ?0, 'company.idList' : ?1, 'status' : ?2}")
    public List<ShopBrand> findByBranchIdListCompanyIdListStatusSort(String branchIdList, String companyIdList, Status status, Sort sort);


    @Query("{'name' : ?0}")
    public ShopBrand findByName(String name);


    @Query("{'status' : ?0}")
    public List<ShopBrand> findByStatusSort(Status status, Sort sort);


    @Query("{'url' : ?0}")
    public ShopBrand findByUrl(String url);


    @Query("{'url' : ?0, 'id' : {$ne : ?1}}")
    public ShopBrand findByUrlNotId(String url, String id);


}
