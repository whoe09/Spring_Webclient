package com.example.openapi_test.server.request.api;


import com.example.openapi_test.common.util.ApiResponse;
import com.example.openapi_test.common.dto.PageInfo;
import com.example.openapi_test.server.request.service.SeoulApiServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/seoul")
public class SeoulRestController {

    private final SeoulApiServiceImpl seoulApiService;

    @GetMapping("/culture")
    public ResponseEntity<ApiResponse> getCultureLocationInfo() throws Exception{
        log.info("Starting NON-BLOCKING Controller");
        PageInfo pageInfo = new PageInfo();
        pageInfo.setCurPage(1);
        Mono<JsonNode> apiData = seoulApiService.getData(pageInfo);
        final ApiResponse response = ApiResponse.builder()
                .code(ApiResponse.RESULT_OK_VALUE)
                .message(ApiResponse.RESULT_MSG)
                .result(Collections.singletonMap("apiData",apiData))
                .build();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
