package com.preloode.panel.repository.cryptocurrency.binance;

import com.preloode.panel.model.cryptocurrency.binance.CryptocurrencyBinanceAveragePriceTicker;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CryptocurrencyBinanceAveragePriceTickerRepository extends MongoRepository<CryptocurrencyBinanceAveragePriceTicker, String> {


}
