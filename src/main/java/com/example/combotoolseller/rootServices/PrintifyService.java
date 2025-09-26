package com.example.combotoolseller.rootServices;

import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.List;

public interface PrintifyService {
    public String getCreateMockupPrintify(WebDriver driver, String productUrl, String baseFolder, int index, List<String> colors,String urlImage);
}
