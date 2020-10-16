package com.preloode.panel.repository.cryptocurrency.bitfinex;

import com.preloode.panel.model.cryptocurrency.bitfinex.CryptocurrencyBitfinexSymbolDetail;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CryptocurrencyBitfinexSymbolDetailRepository extends MongoRepository<CryptocurrencyBitfinexSymbolDetail, String> {


}
