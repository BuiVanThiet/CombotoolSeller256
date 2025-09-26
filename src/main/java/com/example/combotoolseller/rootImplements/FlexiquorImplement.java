package com.example.combotoolseller.rootImplements;

import com.example.combotoolseller.BaseAll;
import com.example.combotoolseller.rootServices.FlexiquorService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class FlexiquorImplement extends BaseAll implements FlexiquorService {
    @Override
    public String getDowloadImage(WebDriver driver, String linkRoot, String baseFolder, int index, String limit) {
        String nameProduct = getNameProduct(driver,"h1");
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            return "False";
        }
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
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h1")));
            nameProduct = getNameProduct(driver,"h1");
            if (nameProduct == null) {
                System.out.println("webloi.");
                return "False";
            }
        }

        String folderName = baseFolder + File.separator + index + "_" + nameProduct;
        try {
            Files.createDirectories(Paths.get(folderName));
        } catch (Exception e) {
            return "False";
        }
        List<String> urlImage = new ArrayList<>();
        Document doc = Jsoup.parse(driver.getPageSource());
        Elements imgElements = doc.select("div.swiper-wrapper div.swiper-slide.relative.cursor-pointer.media-type-image.swiper-slide-visible div.sf-prod-media.media-image responsive-image.sf-image img.f-img-loaded");
        if (imgElements.size() <= 0) {
            imgElements = doc.select("div.sf-preview__wrapper div.h-full.group.f-media-desktop div.swiper-wrapper.main-slider.sf-pis.h-full div.swiper-slide.sf-prod-media-item.relative.overflow-hidden.media-type-image div.sf-prod-media.media-image responsive-image.sf-image img.f-img-loaded");
            System.out.println("imgElements.size1: " +imgElements.size());
        }

        System.out.println("imgElements.size2: " +imgElements.size());
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
