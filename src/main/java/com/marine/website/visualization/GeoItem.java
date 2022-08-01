package com.marine.website.visualization;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeoItem {
    //private String address;

    private Long id;
    private String roadAddress;

    private String jibunAddress;

    private String x;

    private String y;

    public GeoItem(){

    }

    public GeoItem(String roadAddress, String jibunAddress, String x, String y) {
        this.roadAddress = roadAddress;
        this.jibunAddress = jibunAddress;
        this.x = x;
        this.y = y;
    }
}
