package com.preloode.panel.repository.blog;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.model.blog.BlogStar;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface BlogStarRepository extends MongoRepository<BlogStar, String> {


    @Query("{'branch.idList' : ?0, 'company.idList' : ?1, 'status' : ?2}")
    public List<BlogStar> findByBranchIdListCompanyIdListStatusSort(String branchIdList, String companyIdList, Status status, Sort sort);


    @Query("{'name' : ?0}")
    public BlogStar findByName(String name);


    @Query("{'status' : ?0}")
    public List<BlogStar> findByStatusSort(Status status, Sort sort);


    @Query("{'url' : ?0}")
    public BlogStar findByUrl(String url);


    @Query("{'url' : ?0, 'id' : {$ne : ?1}}")
    public BlogStar findByUrlNotId(String url, String id);


}
