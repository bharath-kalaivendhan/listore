package com.linkstore.lstore;

import com.linkstore.lstore.routehandler.LinkRequestHandler;
import com.linkstore.lstore.routehandler.LinkTagMapRequestHandler;
import com.linkstore.lstore.routehandler.TagRequestHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    // Configure link router functions as bean to serve routes
    // https://docs.spring.io/spring-framework/reference/web/webmvc-functional.html
    // https://hantsy.github.io/spring-reactive-sample/web/func.html
    @Bean
    public RouterFunction<ServerResponse> linkRouterFunctions(LinkRequestHandler linkRequestHandler) {
        return RouterFunctions.route()
                .POST("/links", linkRequestHandler::createLink)
                .GET("/links", linkRequestHandler::getLinks)
                .GET("/links/{id}", linkRequestHandler::getLink)
                .PUT("/links/{id}", linkRequestHandler::updateLink)
                .DELETE("/links", linkRequestHandler::deleteLink)
                .GET("/links/{id}/redirect", linkRequestHandler::redirectToLink)
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> tagRouterFuctions(TagRequestHandler tagRequestHandler) {
        return RouterFunctions.route()
                .POST("/tags", tagRequestHandler::createTags)
                .DELETE("/tags", tagRequestHandler::deleteTag)
                .GET("/tags", tagRequestHandler::getTags)
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> linkTagMapRouterFuctions(LinkTagMapRequestHandler linkTagMapRequestHandler) {
        return RouterFunctions.route()
                .POST("/link-tag-map", linkTagMapRequestHandler::mapTagsToLink)
                .build();
    }
}
