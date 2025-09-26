package com.example.combotoolseller.rootServices;

import org.openqa.selenium.WebDriver;

import java.io.IOException;

public interface MiashoopService {
    String getDowloadImage(WebDriver driver, String linkRoot, String baseFolder, int index,String limit) throws IOException,InterruptedException;
}
