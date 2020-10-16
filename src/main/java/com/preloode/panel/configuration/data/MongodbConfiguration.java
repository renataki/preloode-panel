package com.preloode.panel.configuration.data;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class MongodbConfiguration extends AbstractMongoConfiguration {


    @Value("${database.database}")
    private String database;

    @Value("${database.host}")
    private String host;

    @Value("${database.password}")
    private String password;

    @Value("${database.port}")
    private int port;

    @Value("${database.user}")
    private String user;


    @Bean
    public MongoDbFactory mongoDbFactory() {

        return new SimpleMongoDbFactory(mongoClient(), database);

    }


    @Bean
    public MappingMongoConverter mappingMongoConverter() {

        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory());
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, new MongoMappingContext());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        return converter;

    }


    @Override
    public MongoClient mongoClient() {

        MongoClientOptions.Builder option = MongoClientOptions.builder().serverSelectionTimeout(3000);

        MongoClient mongoClient = null;

        if(!user.isEmpty()) {

            List<MongoCredential> credentials = new ArrayList<>() {
                {
                    add(MongoCredential.createCredential(user, database, password.toCharArray()));
                }
            };

            mongoClient = new MongoClient(new ServerAddress(host, port), credentials, option.build());

        } else {

            mongoClient = new MongoClient(new ServerAddress(host, port), option.build());

        }

        return mongoClient;

    }


    @Override
    protected String getDatabaseName() {

        return database;

    }


}
