package com.preloode.panel.repository.cryptocurrency.binance;

import com.preloode.panel.enumeration.cryptocurrency.binance.BinanceSymbolStatus;
import com.preloode.panel.model.cryptocurrency.binance.CryptocurrencyBinanceExchangeInformation;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CryptocurrencyBinanceExchangeInformationRepository extends MongoRepository<CryptocurrencyBinanceExchangeInformation, String> {


    @Query("{'status' : ?0}")
    public List<CryptocurrencyBinanceExchangeInformation> findByStatusSort(BinanceSymbolStatus status, Sort sort);


}
