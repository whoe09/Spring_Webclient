package com.example.openapi_test.server.request.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CultureLocationInfo {
    private String num;
    private String subjCode;

    private String facName;

    private String addr;

    private String phNum;

    private String homePage;

    private String openHour;
    private String entFree;
    private String closeDay;
    private String facDesc;

    @Builder
    public CultureLocationInfo(String num, String subjCode, String facName, String addr, String phNum, String homePage, String openHour, String entFree, String closeDay, String facDesc) {
        this.num = num;
        this.subjCode = subjCode;
        this.facName = facName;
        this.addr = addr;
        this.phNum = phNum;
        this.homePage = homePage;
        this.openHour = openHour;
        this.entFree = entFree;
        this.closeDay = closeDay;
        this.facDesc = facDesc;
    }

    @Override
    public String toString() {
        return "CultureLocationInfo{" +
                "num='" + num + '\'' +
                ", subjCode='" + subjCode + '\'' +
                ", facName='" + facName + '\'' +
                ", addr='" + addr + '\'' +
                ", phNum='" + phNum + '\'' +
                ", homePage='" + homePage + '\'' +
                ", openHour='" + openHour + '\'' +
                ", entFree='" + entFree + '\'' +
                ", closeDay='" + closeDay + '\'' +
                ", facDesc='" + facDesc + '\'' +
                '}';
    }
}
