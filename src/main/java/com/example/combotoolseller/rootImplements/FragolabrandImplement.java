package com.example.combotoolseller.rootImplements;

import com.example.combotoolseller.BaseAll;
import com.example.combotoolseller.rootServices.FragolabrandService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class FragolabrandImplement extends BaseAll implements FragolabrandService {
    @Override
    public String getDowloadImage(WebDriver driver, String linkRoot, String baseFolder, int index, String limit) throws IOException, InterruptedException {
        String nameProduct = getNameProduct(driver,"h1.h3");
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
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h1.h3")));
            nameProduct = getNameProduct(driver,"h1.h3");
            if (nameProduct == null) {
                System.out.println("webloi.");
                return "False";
            }
        }

        String folderName = baseFolder + File.separator + index + "_" + nameProduct;
        Files.createDirectories(Paths.get(folderName));
        List<String> urlImage = new ArrayList<>();
        Document doc = Jsoup.parse(driver.getPageSource());
        Elements imgElements = doc.select("div.product-media-thumbnails-wrapper scroll-carousel product-thumbnails.product-media-thumbnails button.product-thumbnail div.media lqip-element.image-loader img.product-thumbnail-shade");
        if (imgElements.size() <= 0) {
            imgElements = doc.select("media-carousel div.product-gallery__slide div.product-media-item div.product-gallery-shade lqip-element.image-loader img.img--landscape");
        }
        int startLink = 0;
        for (String url: getListImageComponent(driver,imgElements,"\\?v=.*", "")) {
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
        if(urlImage.size() <= 0) {
            return "False";
        }
        int imgIndex = 1;
        for (String link: urlImage) {
            String ext = getFileExtension(link);

            String outputPath = getOutPut(folderName,index,imgIndex,ext);
            downloadFile(link,outputPath);
            imgIndex++;
        }
        return "True";
    }
}
