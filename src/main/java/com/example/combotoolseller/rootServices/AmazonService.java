package com.example.combotoolseller.rootServices;

import com.example.combotoolseller.rootEntites.AmazonEntity;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.List;

public interface AmazonService {
    String getDowloadImageColor(WebDriver driver, String linkRoot, String baseFolder, int index, List<String> colors,String limit) throws IOException,InterruptedException;
    String getDowloadImageNotColor(WebDriver driver, String linkRoot, String baseFolder, int index,String limit) throws IOException,InterruptedException;
    String getDowloadImageByVersion(WebDriver driver, String linkRoot, String baseFolder, int index,String limit);
}
