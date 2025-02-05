package com.linkstore.lstore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLInsert;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tag")
// SQLInsert annotation: used to provide our owm insert query instead of hibernate generated one.
// Note: id tag should always come at last
// https://docs.jboss.org/hibernate/orm/6.3/javadocs/org/hibernate/annotations/SQLInsert.html
@SQLInsert(sql = "INSERT INTO tag(tag, id) " + "VALUES (?, ?) ON CONFLICT (tag) DO NOTHING")
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String tag;
}
