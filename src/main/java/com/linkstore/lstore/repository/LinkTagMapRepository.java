package com.linkstore.lstore.repository;

import com.linkstore.lstore.entity.LinkTagMapEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// https://docs.spring.io/spring-data/jpa/reference/repositories/definition.html
// https://www.linkedin.com/pulse/minute-read-how-repository-interfaces-autowired-spring-bhandari/

@Repository
public interface LinkTagMapRepository extends JpaRepository<LinkTagMapEntity, String> {

    @Transactional
    // NOTE: If you're performing an update, delete, or insert operation (any modifying query),
    // you need to use the @Modifying annotation in conjunction with @Query.
    @Modifying
    // NOTE: by default we have to write JPQL in @Query annotation.
    // we have to table and column name should in entity class context.
    // to use name sql context, we have to set nativeQuery attribute as true.
    @Query("DELETE FROM LinkTagMapEntity WHERE linkId = :link AND tag NOT IN :tags")
    int deleteLinkTagMapByTagsNotInList( String link, List<String> tags);
}
