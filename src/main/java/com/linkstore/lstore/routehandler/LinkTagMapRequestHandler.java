package com.linkstore.lstore.routehandler;

import com.linkstore.lstore.entity.LinkEntity;
import com.linkstore.lstore.entity.LinkTagMapEntity;
import com.linkstore.lstore.entity.TagEntity;
import com.linkstore.lstore.model.LinkTagMapModel;
import com.linkstore.lstore.repository.LinkTagMapRepository;
import com.linkstore.lstore.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.linkstore.lstore.constants.LINK_TAG_MAP_LIST;
import static com.linkstore.lstore.constants.TAG_LIST;

@Component
public class LinkTagMapRequestHandler {

    @Autowired
    private LinkTagMapRepository linkTagMapRepository;

    @Autowired
    private TagRepository tagRepository;

    // handler to map tags to link
    public ServerResponse mapTagsToLink(ServerRequest request) {
        LinkTagMapModel linkTagMapModel;
        try {
            linkTagMapModel = request.body(LinkTagMapModel.class);
        } catch (Exception e) {
            return ServerResponse.badRequest().build();
        }

        Map<String, List<?>> entitiesListMap = getTagAndTagMapEntities(linkTagMapModel.getLinkId(), linkTagMapModel.getTags());
        List<LinkTagMapEntity> linkTagMapEntities = (List<LinkTagMapEntity>) entitiesListMap.get(LINK_TAG_MAP_LIST);
        List<TagEntity> tagEntities = (List<TagEntity>) entitiesListMap.get(TAG_LIST);

        // Insert new tags
        tagRepository.saveAll(tagEntities);

        // Delete link tags map not from the tags list;
        linkTagMapRepository.deleteLinkTagMapByTagsNotInList(linkTagMapModel.getLinkId(), linkTagMapModel.getTags());

        // Delete link tags map not from the tags list;
        linkTagMapRepository.saveAll(linkTagMapEntities);
        
        return ServerResponse.ok().build();
    }

    // TODO: Instead of using map create a custom class to hold these 2 different list.
    private Map<String, List<?>> getTagAndTagMapEntities(String listId, List<String> tags) {
        List<LinkTagMapEntity> linkTagMapEntities = new LinkedList<>();
        List<TagEntity> tagEntities = new LinkedList<>();
        for(String tag : tags) {
            LinkTagMapEntity linkTagMapEntity = new LinkTagMapEntity();
            linkTagMapEntity.setLinkId(listId);
            linkTagMapEntity.setTag(tag);
            linkTagMapEntities.add(linkTagMapEntity);

            TagEntity tagEntity = new TagEntity();
            tagEntity.setTag(tag);
            tagEntities.add(tagEntity);
        }

        Map<String, List<?>> entitiesListMap = new HashMap<>();
        entitiesListMap.put("LINK_TAG_MAP_LIST", linkTagMapEntities);
        entitiesListMap.put("TAG_LIST", tagEntities);
        return entitiesListMap;
    }
}
