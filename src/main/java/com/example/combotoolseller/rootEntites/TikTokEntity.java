package com.example.combotoolseller.rootEntites;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TikTokEntity {
    private String idProduct;
    private String url;
    private String title;
    private String sold;
    private String nameShop;
    private String items;
    private String check;
    private String quantityLive;

    public TikTokEntity(String url, String title, String sold, String nameShop, String items, String check) {
        this.url = url;
        this.title = title;
        this.sold = sold;
        this.nameShop = nameShop;
        this.items = items;
        this.check = check;
    }

    public TikTokEntity(String idProduct, String url, String title, String sold, String nameShop) {
        this.idProduct = idProduct;
        this.url = url;
        this.title = title;
        this.sold = sold;
        this.nameShop = nameShop;
    }

    public TikTokEntity(String url, String nameShop, String quantityLive) {
        this.url = url;
        this.nameShop = nameShop;
        this.quantityLive = quantityLive;
    }
}
