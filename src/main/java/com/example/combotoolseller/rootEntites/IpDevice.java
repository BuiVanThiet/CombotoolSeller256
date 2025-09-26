package com.example.combotoolseller.rootEntites;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IpDevice {
    private Integer id;

    private String ipDevice;

    private Date createdate;

    private Integer status;


}
