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
@Table(name = "link_tag_map")
@SQLInsert(sql = "INSERT INTO link_tag_map(link_id, tag, id) " + "VALUES (?, ?, ?) ON CONFLICT (link_id, tag) DO NOTHING")
public class LinkTagMapEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String linkId;
    private String tag;
}
