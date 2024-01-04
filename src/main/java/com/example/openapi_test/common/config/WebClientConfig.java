package com.example.openapi_test.common.config;

import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.LoggingCodecSupport;
import org.springframework.util.function.ThrowingConsumer;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Configuration
@Slf4j
// 직접 생성잘르 통해 WebClient 생성이 가능하지만, 설정+ 여러서비스에서 호출할 경우 bean 등록하여 공통으로 관리하자
public class WebClientConfig {

    @Bean
    public WebClient commonWebClient() {
        ExchangeStrategies exchangeStrategies =
                ExchangeStrategies.builder()
                        .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024*1024*50))
                        .build();
        // default in-memory buffer 값이 256KB 기본설정되어있음
        // 초과시 DataBufferLimitException 에러발생

        exchangeStrategies.messageWriters().stream()
                .filter(LoggingCodecSupport.class::isInstance)
                .forEach(writer ->((LoggingCodecSupport)writer).setEnableLoggingRequestDetails(true));
        // Debug Lev => form Data && Trace Lev => header 민감 정보

        return WebClient.builder()
//                .clientConnector(
//                        new ReactorClientHttpConnector(
//                                HttpClient.create()
//                                        .secure(ThrowingConsumer.of(sslContextSpec -> sslContextSpec.sslContext(
//                                                SslContextBuilder.forClient()
//                                                        .trustManager(InsecureTrustManagerFactory.INSTANCE).build()
//                                        )))
//                        )
//                )
                .exchangeStrategies(exchangeStrategies)
                .filter(ExchangeFilterFunction.ofRequestProcessor(
                        clientRequest -> {
                            log.debug("Request: {} {}",clientRequest.method(),clientRequest.url());
                            // Mono.just -> Mono 객체 생성
                            return Mono.just(clientRequest);
                        }
                ))
                .filter(ExchangeFilterFunction.ofResponseProcessor(
                        clientResponse -> {
                            clientResponse.headers()
                                    .asHttpHeaders().forEach((name, values) ->
                                            values.forEach(value -> log.debug("{} : {}", name, value)));
                            return Mono.just(clientResponse);
                        }
                ))
                .build();
    }
}
