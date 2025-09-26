package com.example.combotoolseller.mainSwing.TaiAnhTheoLinkSanPham;

import com.example.combotoolseller.BaseAll;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainToolTaiAnhCoMauMau extends BaseAll {
    public void mainStart(boolean checkSearchEngine, boolean checkProfile, String profile, String userData) {
        WebDriver driver = driverService2.getDriverCustom(checkSearchEngine,checkProfile,profile,userData);
        ((JavascriptExecutor) driver).executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");

        String filePath = "./Input/ToolTaiAnhTheoLinkSanPham/urlsLinkSanPhamTheoMau.xlsx";
        List<Map<String, String>> excelData = readExcel(filePath);
        List<List<String>> data = new ArrayList<>();
        try {
            for (Map<String, String> row : excelData) {
                String link = row.get("Column 1").trim();
                String folderSave = "";
                String colorString = row.get("Column 2").trim();
                if(link.toLowerCase().contains("tiktok")) {
                    link = link+"?_svg=3&utm_source=copy";
                }
                List<String> colors = extractColors(colorString);
                if (row.get("Column 3") == null) {
                    folderSave = "";
                }else {
                    folderSave = row.get("Column 3").trim();
                }
                String limitImage = "";
                System.out.println(row);
                if (row.get("Column 4") == null) {
                    limitImage = "";
                } else {
                    limitImage = row.get("Column 4").trim();
                }
                System.out.println("🔹 Đang xử lý: " + link);
                String outputFolder = new File("./Output/ToolTaiAnhTheoLinkSanPham/"+folderSave).getAbsolutePath();
                driver.navigate().to(link);

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Đợi tối đa 10 giây
                wait.until((WebDriver d) -> ((JavascriptExecutor) d)
                        .executeScript("return document.readyState").equals("complete"));
                Thread.sleep(3000);

                int index = getNextIndex(outputFolder);
                data.addAll(getWebTool(link,"taiAnhTheoLinkSanPhamCoMau",driver,outputFolder,index,colors,limitImage));
            }

            List<String> columnNames = List.of("Link", "Trang thai", "Ngay su dung");
            String filePathExcel = "./Output/ToolTaiAnhTheoLinkSanPham/ketQuaTaiAnh.xlsx";
            writeExcelFile(data, filePathExcel, columnNames);
        } catch (Exception e) {
            System.out.println("Loi roi: " + e.getMessage());
        }
        driver.quit();
    }
}
