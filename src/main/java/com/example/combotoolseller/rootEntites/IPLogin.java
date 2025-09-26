/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.combotoolseller.rootEntites;

import lombok.*;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IPLogin {
    private Integer id;
    
    private String ipLogin;

    private Date createdate;

    private Integer status;

}
