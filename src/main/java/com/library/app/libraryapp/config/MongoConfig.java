package com.library.app.libraryapp.config;

import java.util.Collection;
import java.util.Collections;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    private final static String DATABASE_NAME = "test";

    private final static String MONGO_CONNECTION = "mongodb://localhost:27017/test";

    private final static String BASE_PACKAGE = "com.library.app.libraryapp";

    @Override
    protected String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Override
    public MongoClient mongoClient() {
        final ConnectionString connectionString = new ConnectionString(MONGO_CONNECTION);
        final MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings);
    }

    @Override
    public Collection<String> getMappingBasePackages() {
        return Collections.singleton(BASE_PACKAGE);
    }

}