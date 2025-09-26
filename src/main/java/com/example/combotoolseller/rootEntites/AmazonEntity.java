package com.example.combotoolseller.rootEntites;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AmazonEntity {
    private String urlRoot;
    private String urlProductSuccess;
    private String bought;
    private String SoldBy;
    private String dateFirstAvailable;
    private String conditionType;
    private String rank;

    public AmazonEntity(String urlRoot, String urlProductSuccess, String bought, String soldBy, String dateFirstAvailable, String conditionType, String rank) {
        this.urlRoot = urlRoot;
        this.urlProductSuccess = urlProductSuccess;
        this.bought = bought;
        SoldBy = soldBy;
        this.dateFirstAvailable = dateFirstAvailable;
        this.conditionType = conditionType;
        this.rank = rank;
    }
}
