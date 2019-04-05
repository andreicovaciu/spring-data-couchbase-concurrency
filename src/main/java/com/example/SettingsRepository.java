package com.example;

import org.springframework.context.annotation.Scope;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;


@N1qlPrimaryIndexed
@Scope("prototype")
public interface SettingsRepository extends CouchbasePagingAndSortingRepository<Settings, String> {

    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter}")
    Iterable<Settings> findAll();

    /**
     * The solution is to explicitly write the query
     *     <code>
     *         @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND property = '#{#property}'")
     *         Settings findByProperty(@Param("property") Value property);
     *     </code>
     * @param property
     * @return
     */
    Settings findByProperty(Value property);
}
