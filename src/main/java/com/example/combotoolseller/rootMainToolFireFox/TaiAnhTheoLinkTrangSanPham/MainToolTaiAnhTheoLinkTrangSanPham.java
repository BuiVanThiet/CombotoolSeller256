package com.example.combotoolseller.rootMainToolFireFox.TaiAnhTheoLinkTrangSanPham;

import com.example.combotoolseller.BaseAll;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MainToolTaiAnhTheoLinkTrangSanPham extends BaseAll {
    public static void main(String[] args) throws IOException, InterruptedException {
        String filePath = "./Input/ToolTaiAnhTheoLinkTrangSanPham/InputLinkTrang.xlsx";
        List<Map<String, String>> excelData = readExcel(filePath);
        List<List<String>> dataB1 = new ArrayList<>();
        List<List<String>> dataB2 = new ArrayList<>();

        try {
            for (Map<String, String> row : excelData) {
                if (row.get("Column 1") == null) {
                    return;
                }
                clearTextFile("./LinkMemory.txt");
                WebDriver driver = driverService2.getDriver(false,true);
                ((JavascriptExecutor) driver).executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");

                String link = row.get("Column 1").trim();
                if(link.toLowerCase().contains("tiktok")) {
                    link = link + "?_svg=3&utm_source=copy";
                }
                String folderSave;
                if (row.get("Column 3") == null) {
                    folderSave = "";
                } else {
                    folderSave = row.get("Column 3").trim();
                }
                String limitQuantity = row.get("Column 2").trim();
                String limitImage = "";
                System.out.println(row);
                if (row.get("Column 4") == null) {
                    limitImage = "";
                } else {
                    limitImage = row.get("Column 4").trim();
                }
                String outputFolder = new File("./Output/ToolTaiAnhTheoLinkTrangSanPham/" + folderSave).getAbsolutePath();
                if (link.contains("hottopic")) {
                    int start = 0;
                    int end = Integer.parseInt(limitQuantity);
                    int step = 20;

//                    System.out.println(i + "-" + rangeEnd);
//                    System.out.println("🔹 Đang xử lý: " + link+"?start="+i+"&sz="+rangeEnd);
                    driver.navigate().to(link+"?start="+start+"&sz="+end);
                    Thread.sleep(5000);
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Đợi tối đa 10 giây
                    wait.until((WebDriver d) -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
                    Thread.sleep(3000);
                    getWebTool(link+"?start="+start+"&sz="+end,"taiAnhTheoLinkTrangSanPham",driver,outputFolder,0,null,limitQuantity);
//                    for (int i = start; i < end; i += step) {
//                        int rangeEnd = i + step - 1;
//                        // Đảm bảo range không vượt quá 100
//                        if (rangeEnd > end) {
//                            rangeEnd = end;
//                        }
//                        System.out.println(i + "-" + rangeEnd);
//                        System.out.println("🔹 Đang xử lý: " + link+"?start="+i+"&sz="+rangeEnd);
//                        String outputFolder = new File("./Output/ToolTaiAnhTheoLinkTrangSanPham/" + folderSave).getAbsolutePath();
//                        driver.navigate().to(link+"?start="+i+"&sz="+rangeEnd);
//                        Thread.sleep(5000);
//                        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Đợi tối đa 10 giây
//                        wait.until((WebDriver d) -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
//                        Thread.sleep(3000);
//                        getWebTool(link+"?start="+i+"&sz="+rangeEnd,"taiAnhTheoLinkTrangSanPham",driver,outputFolder,0,null,limitQuantity);
//                    }
                } else {
                    System.out.println("🔹 Đang xử lý: " + link);
                    driver.navigate().to(link);
                    Thread.sleep(5000);
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Đợi tối đa 10 giây
                    wait.until((WebDriver d) -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
                    Thread.sleep(3000);

                    getWebTool(link,"taiAnhTheoLinkTrangSanPham",driver,outputFolder,0,null,limitQuantity);
                }

                driver.quit();

                Thread.sleep(3000);
                String timestamp = new SimpleDateFormat("HH:mm:ss-dd/MM/yyyy").format(new Date());

                List<String> getAllLinkMemory = readTextFile("./LinkMemory.txt");
                int totalLinkMemory = 0;
                for (String linkMemory: getAllLinkMemory) {
                    if (totalLinkMemory % 10 == 0) {
                        driver.quit();
                        driver = driverService2.getDriver(false,true);
                    }
                    if (!linkMemory.trim().equals("") || !linkMemory.trim().equals("notName")) {
                        totalLinkMemory++;
                        driver.navigate().to(linkMemory);
                        System.out.println("🔹 Đang xử lý(linkMemory): " + linkMemory);
                        int index = getNextIndex(outputFolder);
                        dataB2.addAll(getWebTool(linkMemory, "taiAnhTheoLinkSanPhamKhongMau", driver, outputFolder, index, null, limitImage));
                    }
//                    dataB2.add(List.of(link, linkMemory, resulB2, timestamp));
                }
                driver.quit();
                // Thêm dữ liệu vào dataB1
                dataB1.add(List.of(link, getAllLinkMemory.size()+"/"+limitQuantity, timestamp));
            }

        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }

        List<String> columnNamesB2 = List.of("Link gốc", "Link con", "Trạng thái", "Ngày sử dụng");
        String filePathExcelB2 = "./Output/ToolTaiAnhTheoLinkTrangSanPham/ketQuaTaiAnhB2.xlsx";
        writeExcelFile(dataB2, filePathExcelB2, columnNamesB2); // Ghi kết quả B2

        // Ghi kết quả cho B1 sau khi xử lý xong tất cả các dòng
        List<String> columnNamesB1 = List.of("Link gốc", "Số lượng link con", "Ngày sử dụng");
        String filePathExcelB1 = "./Output/ToolTaiAnhTheoLinkTrangSanPham/ketQuaTaiAnhB1.xlsx";
        writeExcelFile(dataB1, filePathExcelB1, columnNamesB1);  // Ghi kết quả B1
    }

}
