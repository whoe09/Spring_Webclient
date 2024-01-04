# WebClient 사용법

의존성 추가
- Maven
``<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-webflux</artifactId>
  </dependency>``
- Gradle
``compile 'org.springframework.boot:spring-boot-starter-webflux'``

## Webclient 생성 방법
1.디폴트 옵션 생성
``WebClient.create("http://www.naver.com");``
2.customization 생성
``WebClient client = WebClient.builder()
    .baseUrl("http://localhost:8080")
    .defaultCookie("cookieKey", "cookieValue")
    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
    .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
.build();``
