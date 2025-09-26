package com.example.combotoolseller.rootServices;

import org.openqa.selenium.WebDriver;

import java.io.IOException;

public interface ThedeerlyService {
    String getDowloadImage(WebDriver driver, String linkRoot, String baseFolder, int index,String limit) throws IOException,InterruptedException;
    public String getLinkProductByPage(WebDriver driver, String baseFolder, String limit);
}
