package com.example.openapi_test.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageInfo {
    private Integer allPage;
    private Integer curPage;
    private Integer startPage;
    private Integer endPage;

}
