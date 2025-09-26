package com.example.combotoolseller.rootEntites;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class EtsyEntity {
    private String link;
    private String status;

    public EtsyEntity(String link, String status) {
        this.link = link;
        this.status = status;
    }
}
