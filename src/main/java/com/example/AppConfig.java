package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import java.util.List;

@Configuration
@EnableCouchbaseRepositories
public class AppConfig extends AbstractCouchbaseConfiguration {
    @Value("#{'${couchbase.nodes}'.split(',')}")
    List<String> couchbaseNodes;
    @Value("${bucket.name}") String bucketName;
    @Value("${bucket.user}") String user;
    @Value("${bucket.password}") String bucketPassword;

    @Override
    protected List<String> getBootstrapHosts() {
        return couchbaseNodes;
    }

    @Override
    protected String getBucketName() {
        return bucketName;
    }

    @Override
    protected String getBucketPassword() {
        return bucketPassword;
    }

    @Override
    protected String getUsername() {
        return user;
    }
}
