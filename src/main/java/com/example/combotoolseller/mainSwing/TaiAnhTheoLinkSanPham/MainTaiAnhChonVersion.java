package com.example.combotoolseller.mainSwing.TaiAnhTheoLinkSanPham;

import com.example.combotoolseller.BaseAll;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class MainTaiAnhChonVersion extends BaseAll {
    public void mainStart(boolean checkSearchEngine, boolean checkProfile, String profile, String userData) {
        WebDriver driver = driverService2.getDriverCustom(checkSearchEngine,checkProfile,profile,userData);
        ((JavascriptExecutor) driver).executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");

        String filePath = "./Input/ToolTaiAnhTheoLinkSanPham/urlsLinkSanPhamKhongTheoMau.xlsx";
        List<Map<String, String>> excelData = readExcel(filePath);
        List<List<String>> data = new ArrayList<>();
        try {
            for (Map<String, String> row : excelData) {
                String link = row.get("Column 1").trim();
                if(link.toLowerCase().contains("tiktok")) {
                    int randomNumber = ThreadLocalRandom.current().nextInt(1, 1000);
                    link = link+"?_svg="+randomNumber+"&utm_source=copy";
                }
                String folderSave;
                if (row.get("Column 2") == null) {
                    folderSave = "";
                }else {
                    folderSave = row.get("Column 2").trim();
                }

                String limitImage = "";
                System.out.println(row);
                if (row.get("Column 3") == null) {
                    limitImage = "";
                } else {
                    limitImage = row.get("Column 3").trim();
                }

                System.out.println("🔹 Đang xử lý: " + link);
                String outputFolder = new File("./Output/ToolTaiAnhTheoLinkSanPham/"+folderSave).getAbsolutePath();
                if(link.contains("aliexpress")) {
                    int questionMarkIndex = link.indexOf('?');

                    // Cắt chuỗi trước dấu '?'
                    if (questionMarkIndex != -1) {
                        link = link.substring(0, questionMarkIndex);
                        System.out.println("Cắt chuỗi đến dấu '?': " + link);
                    } else {
                        System.out.println("Không có dấu '?' trong URL.");
                    }
                }
                driver.navigate().to(link);

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Đợi tối đa 10 giây
                wait.until((WebDriver d) -> ((JavascriptExecutor) d)
                        .executeScript("return document.readyState").equals("complete"));
                int index = getNextIndex(outputFolder);
                data.addAll(getWebTool(link,"taiAnhTheoLinkSanPhamChonVersion",driver,outputFolder,index,null,limitImage));
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
