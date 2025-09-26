package com.example.combotoolseller.rootImplements;

import com.example.combotoolseller.BaseAll;
import com.example.combotoolseller.rootServices.AliexpressService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
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

public class AliexpressImplement extends BaseAll implements AliexpressService {
    @Override
    public String getDowloadImage(WebDriver driver, String linkRoot, String baseFolder, int index, String limit) throws IOException, InterruptedException {
        String nameProduct = getNameProduct(driver,"div.pdp-info-right h1");
//        Thread.sleep(2000);
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
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("div.pdp-info-right h1")));
            nameProduct = getNameProduct(driver,"div.pdp-info-right h1");
            if (nameProduct == null) {
                System.out.println("webloi.");
                return "False";
            }
        }

        System.out.println("nameProduct: " + nameProduct);

        String folderName = baseFolder + File.separator + index + "_" + nameProduct;
        Files.createDirectories(Paths.get(folderName));
        List<String> urlImage = new ArrayList<>();
        Document doc = Jsoup.parse(driver.getPageSource());
        List<WebElement> images = driver.findElements(By.cssSelector("div[class^='slider--slider'] div[class^='slider--item'] div[class^='slider--img'] img"));
        if(images.size() <= 0) {
            images = driver.findElements(By.cssSelector("img[class^='magnifier--image']"));
        }
//        Elements imgElements = doc.select("div.slider--slider--VKj5hty div div.slider--item--RpyeewA div.slider--img--kD4mIg7 img");
//        if (imgElements == null) {
//            imgElements = doc.select("div.image-view-v2--previewWrap--lICzhlN div.image-view-v2--previewBox--yPlyD6F div.magnifier--wrap--qjbuwmt img.magnifier--image--RM17RL2");
//        }
//
        int startLink = 0;
        for (String url: getListImageComponent(driver,images,"_\\d+x\\d+q\\d+\\.jpg_\\.avif", "")) {
            if (!limit.equals("")) {
                System.out.println("limit: "+limit);
                if (startLink >= Integer.parseInt(limit)) {
                    System.out.println("bi vao vung breack");
                    break;
                }
            }
            startLink++;
            urlImage.add(url);
        }

        // Sử dụng CSS Selector để tìm các thẻ img mà có class động

        if(urlImage.size() <= 0) {
            return "False";
        }
        int imgIndex = 1;
        for (String link: urlImage) {
            String ext = getFileExtension(link);

            String outputPath = getOutPut(folderName,index,imgIndex,ext);
            downloadFile(link,outputPath);
            System.out.println("da tai xong: " + link);
            imgIndex++;
        }
        return "True";
    }
}
