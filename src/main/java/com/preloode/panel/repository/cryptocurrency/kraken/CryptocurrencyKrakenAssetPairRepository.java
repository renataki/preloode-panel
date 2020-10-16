package com.preloode.panel.repository.cryptocurrency.kraken;

import com.preloode.panel.model.cryptocurrency.kraken.CryptocurrencyKrakenAssetPair;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface CryptocurrencyKrakenAssetPairRepository extends MongoRepository<CryptocurrencyKrakenAssetPair, String> {


    @Query("{'$or': [{'base': ?0}, {'quote': ?0}]}")
    public List<CryptocurrencyKrakenAssetPair> findByBaseOrQuoteSort(String pairName, Sort sort);


}
