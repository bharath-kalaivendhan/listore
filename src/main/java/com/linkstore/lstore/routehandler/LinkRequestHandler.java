package com.linkstore.lstore.routehandler;

import com.linkstore.lstore.constants;
import com.linkstore.lstore.entity.LinkEntity;
import com.linkstore.lstore.repository.LinkRepository;
import jakarta.servlet.ServletException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.*;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

// LinkRequestHandler a class to hold request handler functions
// Request handler functions are as same as functions that is annotated with @RequestMapping
// It takes ServerRequest and returns a ServerResponse
// https://hantsy.github.io/spring-reactive-sample/web/func.html
@Component
@NoArgsConstructor
public class LinkRequestHandler {

    @Autowired
    private LinkRepository linkRepository;

    public ServerResponse createLink(ServerRequest request) {
        LinkEntity link = null;
        try {
            link = request.body(LinkEntity.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ServerResponse.badRequest().build();
        }
        linkRepository.save(link);
        return ServerResponse.ok().build();
    }

    public ServerResponse getLink(ServerRequest request) {
        String linkId;
        try {
            linkId = request.pathVariable(constants.ID_PATH_PARAM);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ServerResponse.badRequest().build();
        }
        Optional<LinkEntity> linkEntityOptional = linkRepository.findById(linkId);
        if (linkEntityOptional.isPresent()) {
            return ServerResponse.ok().body(linkEntityOptional.get());
        }
        return ServerResponse.notFound().build();
    }

    public ServerResponse getLinks(ServerRequest request) {
        List<LinkEntity> links = linkRepository.findAll();
        if (!links.isEmpty()) {
            return ServerResponse.ok().body(links);
        }
        return ServerResponse.notFound().build();
    }

    public ServerResponse updateLink(ServerRequest request) {
        String linkId;
        LinkEntity link;
        try {
            linkId = request.pathVariable(constants.ID_PATH_PARAM);
            link = request.body(LinkEntity.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ServerResponse.badRequest().build();
        }
        Optional<LinkEntity> linkEntityOptional = linkRepository.findById(linkId);
        if (linkEntityOptional.isPresent()) {
            LinkEntity linkEntity = linkEntityOptional.get();
            if(link.getLink() != null)
                linkEntity.setLink(link.getLink());
            if(link.getTitle() != null)
                linkEntity.setTitle(link.getTitle());

            linkRepository.save(linkEntity);
            return ServerResponse.ok().build();
        }
        return ServerResponse.notFound().build();
    }

    public ServerResponse deleteLink(ServerRequest request) {
        String linkId;
        try {
            linkId = request.pathVariable(constants.ID_PATH_PARAM);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ServerResponse.badRequest().build();
        }
        linkRepository.deleteById(linkId);
        return ServerResponse.ok().build();
    }

    public ServerResponse redirectToLink(ServerRequest request) {
        String linkId;
        try {
            linkId = request.pathVariable(constants.ID_PATH_PARAM);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ServerResponse.badRequest().build();
        }
        Optional<LinkEntity> linkEntityOptional = linkRepository.findById(linkId);
        if (linkEntityOptional.isPresent()) {
            return ServerResponse.seeOther(URI.create(linkEntityOptional.get().getLink())).build();
        }
        return ServerResponse.notFound().build();
    }
}
