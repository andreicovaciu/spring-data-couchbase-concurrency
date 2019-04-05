package com.example;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.couchbase.core.mapping.Document;

@Document
public class Settings {
    @Id
    @Field
    private String id;
    @Version
    private long version;
    @Field
    private Value property;

    public Settings(){}

    public Settings(String id, long version, Value property) {
        this.id = id;
        this.version = version;
        this.property = property;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Value getProperty() {
        return property;
    }

    public void setProperty(Value property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "id='" + id + '\'' +
                ", version=" + version +
                ", property=" + property +
                '}';
    }
}
