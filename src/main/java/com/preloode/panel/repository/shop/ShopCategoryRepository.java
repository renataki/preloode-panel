package com.preloode.panel.repository.shop;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.model.shop.ShopCategory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ShopCategoryRepository extends MongoRepository<ShopCategory, String> {


    @Query("{'branch.idList' : ?0, 'company.idList' : ?1, 'status' : ?2}")
    public List<ShopCategory> findByBranchIdListCompanyIdListStatusSort(String branchIdList, String companyIdList, Status status, Sort sort);


    @Query("{'name' : ?0, 'parent.id' : ?1}")
    public ShopCategory findByNameParentId(String name, String parentId);


    @Query("{'parent.id' : ?0}")
    public ShopCategory findByParentId(String parentId);


    @Query("{'status' : ?0}")
    public List<ShopCategory> findByStatusSort(Status status, Sort sort);


    @Query("{'status' : ?0, 'url' : ?1}")
    public List<ShopCategory> findByStatusUrlSort(Status status, String url, Sort sort);


    @Query("{'url' : ?0, 'parent.id' : ?1}")
    public ShopCategory findByUrlParentId(String url, String parentId);


    @Query("{'url' : ?0, 'parent.id' : ?1, 'id' : {$ne : ?2}}")
    public ShopCategory findByUrlParentIdNotId(String url, String parentId, String id);


    @Query("{'path' : {$regex : ?0}, 'branch.idList' : ?1, 'company.idList' : ?2, 'status' : ?3}")
    public List<ShopCategory> findLikePathByBranchIdListCompanyIdListStatus(String path, String branchIdList, String companyIdList, Status status);


}
