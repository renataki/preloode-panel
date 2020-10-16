package com.preloode.panel.repository.shop;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.model.shop.ShopProduct;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Set;


public interface ShopProductRepository extends MongoRepository<ShopProduct, String> {


    @Query("{'branch.idList' : ?0, 'company.idList' : ?1, 'featured' : ?2, 'status' : ?3}")
    public List<ShopProduct> findByBranchIdListCompanyIdListFeaturedStatusPageable(String branchIdList, String companyIdList, boolean featured, Status status, Pageable pageable);


    @Query("{'branch.idList' : ?0, 'company.idList' : ?1, 'status' : ?2}")
    public List<ShopProduct> findByBranchIdListCompanyIdListStatusPageable(String branchIdList, String companyIdList, Status status, Pageable pageable);


    @Query("{'name' : ?0, 'category.id' : {$in : ?1}}")
    public ShopProduct findByNameInCategoryId(String name, Set<String> categoryId);


    @Query("{'branch.idList' : ?0, 'company.idList' : ?1, 'pathList' : ?2, 'status' : ?3, 'url' : ?4}")
    public List<ShopProduct> findByBranchIdListCompanyIdListPathListStatusUrlSort(String branchIdList, String companyIdList, String pathList, Status status, String url, Sort sort);


    @Query("{'status' : ?0}")
    public List<ShopProduct> findByStatusPageable(Status status, Pageable pageable);


    @Query("{'status' : ?0}")
    public List<ShopProduct> findByStatusSort(Status status, Sort sort);


    @Query("{'url' : ?0, 'category.id' : {$in : ?1}}")
    public ShopProduct findByUrlInCategoryId(String url, Set<String> categoryId);


    @Query("{'url' : ?0, 'category.id' : {$in : ?1}, 'id' : {$ne : ?2}}")
    public ShopProduct findByUrlInCategoryIdNotId(String url, Set<String> categoryId, String id);


}
