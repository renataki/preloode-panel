package com.preloode.panel.repository.cryptocurrency.bittrex;

import com.preloode.panel.enumeration.cryptocurrency.bittrex.BittrexCurrencyStatus;
import com.preloode.panel.model.cryptocurrency.bittrex.CryptocurrencyBittrexCurrency;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CryptocurrencyBittrexCurrencyRepository extends MongoRepository<CryptocurrencyBittrexCurrency, String> {


    @Query("{'status' : ?0}")
    public List<CryptocurrencyBittrexCurrency> findByStatusSort(BittrexCurrencyStatus status, Sort sort);


}
