package com.preloode.panel.repository.blog;

import com.preloode.panel.model.blog.BlogPostLogData;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface BlogPostLogDataRepository extends MongoRepository<BlogPostLogData, String> {


}
