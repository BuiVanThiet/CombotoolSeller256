package com.example.combotoolseller.rootServices;

import com.example.combotoolseller.rootEntites.TikTokEntity;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.List;

public interface TikTokService {
    String getDowloadImage(WebDriver driver, String linkRoot, String baseFolder, int index,String limit) throws IOException,InterruptedException;
    String getDowloadImageVersion(WebDriver driver, String linkRoot, String baseFolder, int index,String limit) throws IOException,InterruptedException;
}
