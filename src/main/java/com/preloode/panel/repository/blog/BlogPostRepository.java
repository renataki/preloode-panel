package com.preloode.panel.repository.blog;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.model.blog.BlogPost;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Set;


public interface BlogPostRepository extends MongoRepository<BlogPost, String> {


    @Query("{'branch.idList' : ?0, 'company.idList' : ?1, 'featured' : ?2, 'status' : ?3}")
    public List<BlogPost> findByBranchIdListCompanyIdListFeaturedStatusPageable(String branchIdList, String companyIdList, boolean featured, Status status, Pageable pageable);


    @Query("{'branch.idList' : ?0, 'company.idList' : ?1, 'status' : ?2}")
    public List<BlogPost> findByBranchIdListCompanyIdListStatusPageable(String branchIdList, String companyIdList, Status status, Pageable pageable);


    @Query("{'branch.idList' : ?0, 'company.idList' : ?1, 'pathList' : ?2, 'status' : ?3, 'url' : ?4}")
    public List<BlogPost> findByBranchIdListCompanyIdListPathListStatusUrlSort(String branchIdList, String companyIdList, String pathList, Status status, String url, Sort sort);


    @Query("{'status' : ?0}")
    public List<BlogPost> findByStatusPageable(Status status, Pageable pageable);


    @Query("{'status' : ?0}")
    public List<BlogPost> findByStatusSort(Status status, Sort sort);


    @Query("{'title' : ?0, 'category.id' : {$in : ?1}}")
    public BlogPost findByTitleInCategoryId(String title, Set<String> categoryId);


    @Query("{'url' : ?0, 'category.id' : {$in : ?1}}")
    public BlogPost findByUrlInCategoryId(String url, Set<String> categoryId);


    @Query("{'url' : ?0, 'category.id' : {$in : ?1}, 'id' : {$ne : ?2}}")
    public BlogPost findByUrlInCategoryIdNotId(String url, Set<String> categoryId, String id);


}
