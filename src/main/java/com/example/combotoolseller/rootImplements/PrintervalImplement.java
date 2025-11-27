package com.example.combotoolseller.rootImplements;

import com.example.combotoolseller.BaseAll;
import com.example.combotoolseller.rootServices.PrintervalService;
import com.example.combotoolseller.rootServices.WalmartService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class PrintervalImplement extends BaseAll implements PrintervalService {
    @Override
    public String getDowloadImage(WebDriver driver, String linkRoot, String baseFolder, int index, String limit) throws IOException, InterruptedException {
        String nameProduct = getNameProduct(driver,"h1.product-heading");
        Thread.sleep(2000);
        // Phát hiện lỗi -> tạm dừng chương trình, đợi người xử lý
        if(nameProduct == null) {
            boolean shouldContinue = showProblemDialog();
            if (!shouldContinue) {
                System.out.println("Người dùng chọn hủy. Kết thúc.");
                return "False";
            }
            driver.navigate().refresh();
            // Đợi cho đến khi trang hoàn tất load (tùy thuộc vào yếu tố bạn cần kiểm tra)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Ví dụ: đợi cho đến khi một element cụ thể xuất hiện trên trang (có thể là một element bất kỳ)
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h1.product-heading")));
            nameProduct = getNameProduct(driver,"h1.product-heading");
            if (nameProduct == null) {
                System.out.println("webloi.");
                return "False";
            }
        }

        String folderName = baseFolder + File.separator + index + "_" + nameProduct;
        Files.createDirectories(Paths.get(folderName));
        List<String> urlImage = new ArrayList<>();
        Elements imgElements = null;
        Document doc = Jsoup.parse(driver.getPageSource());
        imgElements = doc.select("div.product-gallery-nav div.gallery-nav-item img");

        int startLink = 0;
        for (String url: getListImageComponent(driver,imgElements,"540x540", "1540x1540")) {
            if (!limit.equals("")) {
                System.out.println("limit: "+limit);
                if (startLink >= Integer.parseInt(limit)) {
                    System.out.println("bi vao vung breack");
                    break;
                }
            }
            startLink++;
            // 1️⃣ Xóa đoạn &fmt=auto và tất cả phần sau nó
            String cleaned = url.replaceAll("&fmt=auto.*$", "");

            // 2️⃣ Xóa ký tự $ (nếu có)
            cleaned = cleaned.replace("$", "");

            urlImage.add(cleaned);
        }
        if(urlImage.size() <= 0) {
            return "False";
        }
        int imgIndex = 1;
        for (String link: urlImage) {
            String ext = getFileExtension(link);

//            String outputPath = getOutPut(folderName,index,imgIndex,ext);
            String outputPath = folderName+"/"+imgIndex+"_"+imgIndex+".png";

            downloadFile(link,outputPath);
            imgIndex++;
        }

        return "True";
    }

    public static void clickLastElement(WebDriver driver, String cssSelector) {
        // 1. Tìm tất cả các phần tử phù hợp với CSS Selector
        List<WebElement> elements = driver.findElements(By.cssSelector(cssSelector));

        // 2. Kiểm tra xem danh sách có phần tử nào không
        if (elements.isEmpty()) {
            System.out.println("Không tìm thấy phần tử nào với selector: " + cssSelector);
            return;
        }

        // 3. Lấy phần tử cuối cùng trong danh sách
        // Kích thước danh sách (size) là số lượng phần tử,
        // chỉ mục (index) của phần tử cuối cùng là size - 1.
        WebElement lastElement = elements.get(elements.size() - 1);

        // 4. Click vào phần tử cuối cùng
        lastElement.click();

        System.out.println("Đã click vào phần tử cuối cùng trong danh sách.");
    }
    public boolean waitForElementPresence(WebDriver driver, String cssSelector) {

        // Chuyển chuỗi CSS Selector thành đối tượng By
        By locator = By.cssSelector(cssSelector);

        // 1. Thiết lập WebDriverWait với thời gian tối đa là 5 giây
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            System.out.println("Bắt đầu chờ phần tử (" + cssSelector + ") trong 5 giây...");

            // 2. Chờ cho đến khi phần tử có mặt trong DOM
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));

            System.out.println("   --> Phần tử đã xuất hiện.");
            return true;

        } catch (TimeoutException e) {
            // Nếu hết thời gian 5s mà phần tử không xuất hiện
            System.out.println("   --> KHÔNG tìm thấy phần tử sau 5 giây.");
            return false;
        } catch (Exception e) {
            // Xử lý các lỗi khác (ví dụ: selector không hợp lệ)
            System.err.println("   --> Lỗi bất ngờ: " + e.getMessage());
            return false;
        }
    }

}
