package com.linkstore.lstore.repository;

import com.linkstore.lstore.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// https://docs.spring.io/spring-data/jpa/reference/repositories/definition.html
// https://www.linkedin.com/pulse/minute-read-how-repository-interfaces-autowired-spring-bhandari/

@Repository
public interface TagRepository extends JpaRepository<TagEntity, String> {
}
