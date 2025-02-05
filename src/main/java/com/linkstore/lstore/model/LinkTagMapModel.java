package com.linkstore.lstore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LinkTagMapModel {
    private String linkId;
    private List<String> tags;
}
