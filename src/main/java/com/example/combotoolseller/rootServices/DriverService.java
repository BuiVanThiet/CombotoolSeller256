package com.example.combotoolseller.rootServices;

import org.openqa.selenium.WebDriver;

public interface DriverService {
    WebDriver getDriver(boolean checkSearchEngine, boolean checkProfile);
    WebDriver getDriverCustom(boolean checkSearchEngine, boolean checkProfile, String profile, String userData);
}
