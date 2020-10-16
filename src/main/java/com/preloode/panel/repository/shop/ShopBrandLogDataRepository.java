package com.preloode.panel.repository.shop;

import com.preloode.panel.model.shop.ShopBrandLogData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShopBrandLogDataRepository extends MongoRepository<ShopBrandLogData, String> {


}
