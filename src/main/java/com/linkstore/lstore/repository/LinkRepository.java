package com.linkstore.lstore.repository;

import com.linkstore.lstore.entity.LinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// https://docs.spring.io/spring-data/jpa/reference/repositories/definition.html
// https://www.linkedin.com/pulse/minute-read-how-repository-interfaces-autowired-spring-bhandari/

@Repository
public interface LinkRepository extends JpaRepository<LinkEntity, String> {
}
