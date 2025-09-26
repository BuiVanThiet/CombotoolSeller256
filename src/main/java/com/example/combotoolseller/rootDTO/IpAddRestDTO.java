package com.example.combotoolseller.rootDTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IpAddRestDTO {
    private String ip;
    private String idDevice;
    private String country;
    private String region;
    private String city;
    private String lat;
    private String lon;

}
