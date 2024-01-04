package com.example.openapi_test.server.request.service;


import com.example.openapi_test.common.dto.PageInfo;
import com.fasterxml.jackson.databind.JsonNode;
import reactor.core.publisher.Mono;

public interface SeoulApiService {

    Mono<JsonNode> getData(PageInfo pageInfo);

}
