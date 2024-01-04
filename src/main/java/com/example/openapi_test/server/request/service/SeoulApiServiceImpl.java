package com.example.openapi_test.server.request.service;

import com.example.openapi_test.server.request.dto.CultureLocationInfo;
import com.example.openapi_test.common.dto.PageInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class SeoulApiServiceImpl implements SeoulApiService{

    @Value("${seoulApi.securityKey}")
    private String securityKey;
    @Value("${seoulApi.callBackUrl}")
    private String callBackUrl;

    @Value("${seoulApi.type}")
    private String type;

    private final WebClient webClient;

    @Override
    public Mono<JsonNode> getData(PageInfo pageInfo) {
        String serviceName = "/culturalSpaceInfo";
        int startIdx = (pageInfo.getCurPage()-1)*10+1;
        StringBuilder urlBuilder = new StringBuilder(securityKey+type+serviceName);
        urlBuilder.append("/").append(startIdx+"");
        urlBuilder.append("/").append(startIdx+10+"");


        // mutate(): builder()를 다시 생성하여 추가적인 옵션 설정이 가능하다(기존설정에 추가적인 옵션을 넣는것)
       return  webClient.mutate()
                        .baseUrl(callBackUrl)
                        .build()
                        .get()
                        .uri(String.valueOf(urlBuilder))
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(JsonNode.class);
        // retrieve(): ResponseBody 처리를 할 수 있음(권장)
        // exchange(): 세세한 컨트롤 사용가능
    }

    private Object parseObject(Mono<JsonNode> jsonNodeMono) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.treeToValue(jsonNodeMono.block(),CultureLocationInfo.class);

    }
}
