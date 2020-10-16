package com.preloode.panel.repository.blog;

import com.preloode.panel.model.blog.BlogCategoryLogData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BlogCategoryLogDataRepository extends MongoRepository<BlogCategoryLogData, String> {


}
