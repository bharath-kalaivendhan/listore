package com.linkstore.lstore.routehandler;

import com.linkstore.lstore.entity.TagEntity;
import com.linkstore.lstore.repository.TagRepository;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.io.IOException;
import java.util.*;

import static com.linkstore.lstore.constants.ID_PATH_PARAM;

@Component
public class TagRequestHandler {

    @Autowired
    private TagRepository tagRepository;

    // handler to create multiple tags
    public ServerResponse createTags(ServerRequest request) {
        ParameterizedTypeReference<List<String>> typeRef = new ParameterizedTypeReference<>() { };

        List<String> tags;
        try {
            tags = request.body(typeRef);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ServerResponse.badRequest().build();
        }

        List<TagEntity> tagEntities = new LinkedList<TagEntity>();
        for(String tag : tags) {
            TagEntity tagEntity = new TagEntity();
            tagEntity.setTag(tag);
            tagEntities.add(tagEntity);
        }
        tagRepository.saveAll(tagEntities);
        return ServerResponse.ok().build();
    }

    // handler to get all tags
    public ServerResponse getTags(ServerRequest request) {
        List<TagEntity> tags = tagRepository.findAll();
        if (!tags.isEmpty()) {
            return ServerResponse.ok().body(tags);
        }
        return ServerResponse.notFound().build();
    }

    // handler to delete tags by id
    public ServerResponse deleteTag(ServerRequest request) {
        String tagId;
        try {
            tagId = request.pathVariable(ID_PATH_PARAM);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ServerResponse.badRequest().build();
        }
        tagRepository.deleteById(tagId);
        return ServerResponse.ok().build();
    }
}