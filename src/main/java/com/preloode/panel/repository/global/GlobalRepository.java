package com.preloode.panel.repository.global;

import com.preloode.panel.configuration.data.MongodbConfiguration;
import com.preloode.panel.model.blog.*;
import com.preloode.panel.model.company.Company;
import com.preloode.panel.model.company.CompanyBranch;
import com.preloode.panel.model.company.CompanyBranchLogData;
import com.preloode.panel.model.company.CompanyLogData;
import com.preloode.panel.model.crm.*;
import com.preloode.panel.model.cryptocurrency.CryptocurrencyAsset;
import com.preloode.panel.model.cryptocurrency.CryptocurrencyTriangularArbitrage;
import com.preloode.panel.model.cryptocurrency.binance.CryptocurrencyBinanceAveragePriceTicker;
import com.preloode.panel.model.cryptocurrency.binance.CryptocurrencyBinanceExchangeInformation;
import com.preloode.panel.model.cryptocurrency.bitfinex.CryptocurrencyBitfinexSymbolDetail;
import com.preloode.panel.model.cryptocurrency.bittrex.CryptocurrencyBittrexCurrency;
import com.preloode.panel.model.cryptocurrency.kraken.CryptocurrencyKrakenAssetPair;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.payment.PaymentAccount;
import com.preloode.panel.model.payment.PaymentAccountLogData;
import com.preloode.panel.model.payment.PaymentMethod;
import com.preloode.panel.model.payment.PaymentMethodLogData;
import com.preloode.panel.model.setting.Setting;
import com.preloode.panel.model.setting.SettingLogData;
import com.preloode.panel.model.setting.SettingSlider;
import com.preloode.panel.model.setting.SettingSliderLogData;
import com.preloode.panel.model.shop.*;
import com.preloode.panel.model.thirdParty.ThirdPartyAccount;
import com.preloode.panel.model.thirdParty.ThirdPartyAccountLogData;
import com.preloode.panel.model.thirdParty.ThirdPartyProvider;
import com.preloode.panel.model.thirdParty.ThirdPartyProviderLogData;
import com.preloode.panel.model.transaction.*;
import com.preloode.panel.model.user.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class GlobalRepository {


    private static final Logger logger = LoggerFactory.getLogger(GlobalRepository.class);

    @Autowired
    private MongodbConfiguration mongodb;


    public BaseResponse dropAllCollection() {

        BaseResponse result = new BaseResponse(
                "Failed to drop all collection",
                false
        );

        try {

            MongoOperations mongoOperation = mongodb.mongoTemplate();
            mongoOperation.dropCollection(BlogCategory.class);
            mongoOperation.dropCollection(BlogCategoryLogData.class);
            mongoOperation.dropCollection(BlogPost.class);
            mongoOperation.dropCollection(BlogPostLogData.class);
            mongoOperation.dropCollection(BlogStar.class);
            mongoOperation.dropCollection(BlogStarLogData.class);
            mongoOperation.dropCollection(Company.class);
            mongoOperation.dropCollection(CompanyLogData.class);
            mongoOperation.dropCollection(CompanyBranch.class);
            mongoOperation.dropCollection(CompanyBranchLogData.class);
            mongoOperation.dropCollection(CrmDatabase.class);
            mongoOperation.dropCollection(CrmDatabaseLogData.class);
            mongoOperation.dropCollection(CrmDatabaseSource.class);
            mongoOperation.dropCollection(CrmDatabaseSourceLogData.class);
            mongoOperation.dropCollection(CrmGroup.class);
            mongoOperation.dropCollection(CrmGroupLogData.class);
            mongoOperation.dropCollection(CryptocurrencyAsset.class);
            mongoOperation.dropCollection(CryptocurrencyBinanceAveragePriceTicker.class);
            mongoOperation.dropCollection(CryptocurrencyBinanceExchangeInformation.class);
            mongoOperation.dropCollection(CryptocurrencyBitfinexSymbolDetail.class);
            mongoOperation.dropCollection(CryptocurrencyBittrexCurrency.class);
            mongoOperation.dropCollection(CryptocurrencyKrakenAssetPair.class);
            mongoOperation.dropCollection(CryptocurrencyTriangularArbitrage.class);
            mongoOperation.dropCollection(PaymentAccount.class);
            mongoOperation.dropCollection(PaymentAccountLogData.class);
            mongoOperation.dropCollection(PaymentMethod.class);
            mongoOperation.dropCollection(PaymentMethodLogData.class);
            mongoOperation.dropCollection(Setting.class);
            mongoOperation.dropCollection(SettingLogData.class);
            mongoOperation.dropCollection(SettingSlider.class);
            mongoOperation.dropCollection(SettingSliderLogData.class);
            mongoOperation.dropCollection(ShopBrand.class);
            mongoOperation.dropCollection(ShopBrandLogData.class);
            mongoOperation.dropCollection(ShopCategory.class);
            mongoOperation.dropCollection(ShopCategoryLogData.class);
            mongoOperation.dropCollection(ShopProduct.class);
            mongoOperation.dropCollection(ShopProductLogData.class);
            mongoOperation.dropCollection(ThirdPartyAccount.class);
            mongoOperation.dropCollection(ThirdPartyAccountLogData.class);
            mongoOperation.dropCollection(ThirdPartyProvider.class);
            mongoOperation.dropCollection(ThirdPartyProviderLogData.class);
            mongoOperation.dropCollection(TicketNumber.class);
            mongoOperation.dropCollection(TicketNumberLogData.class);
            mongoOperation.dropCollection(Transaction.class);
            mongoOperation.dropCollection(TransactionLogData.class);
            mongoOperation.dropCollection(TransactionCart.class);
            mongoOperation.dropCollection(TransactionCartLogData.class);
            mongoOperation.dropCollection(User.class);
            mongoOperation.dropCollection(UserGroup.class);
            mongoOperation.dropCollection(UserGroupLogData.class);
            mongoOperation.dropCollection(UserLog.class);
            mongoOperation.dropCollection(UserLogData.class);
            mongoOperation.dropCollection(UserRole.class);
            mongoOperation.dropCollection(UserRoleLogData.class);

            result.setResponse("All collection dropped");
            result.setResult(true);

            logger.info(result.getResponse());

        } catch(Exception exception) {

            logger.info(exception.getMessage());

        }

        return result;

    }


    public <T> List<T> findPagination(Query query, Class<T> model) {

        List<T> result = new ArrayList<>();

        try {

            MongoOperations mongoOperation = mongodb.mongoTemplate();
            result = mongoOperation.find(query, model);
            logger.info("Pagination retrieved");

        } catch(Exception exception) {

            logger.info(exception.getMessage());

        }

        return result;

    }


}
