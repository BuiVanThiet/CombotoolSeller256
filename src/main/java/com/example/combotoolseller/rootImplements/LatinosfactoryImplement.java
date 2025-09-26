package com.example.combotoolseller.rootImplements;

import com.example.combotoolseller.BaseAll;
import com.example.combotoolseller.rootServices.LatinosfactoryService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
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

public class LatinosfactoryImplement extends BaseAll implements LatinosfactoryService {

    @Override
    public String getDowloadImage(WebDriver driver, String linkRoot, String baseFolder, int index, String limit) throws IOException, InterruptedException {
//        Thread.sleep(3000);
//        boolean checkCloneDowloadTiktok = getClickAction(driver,"button#ps-widget-custom-form__close-button");
//        if(checkCloneDowloadTiktok == false) {
//            System.out.println("Khong co nut clone!");
//        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Đợi iframe xuất hiện
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe#ps__widget")));
        WebElement closeBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("div#ps-desktop-widget__close"))
        );
        closeBtn.click();

        driver.switchTo().defaultContent();



        String nameProduct = getNameProduct(driver,"h1.product_title");
        Thread.sleep(2000);
        if(nameProduct == null) {
            boolean shouldContinue = showProblemDialog();
            if (!shouldContinue) {
                System.out.println("Người dùng chọn hủy. Kết thúc.");
                return "False";
            }
            driver.navigate().refresh();
            // Đợi cho đến khi trang hoàn tất load (tùy thuộc vào yếu tố bạn cần kiểm tra)
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Ví dụ: đợi cho đến khi một element cụ thể xuất hiện trên trang (có thể là một element bất kỳ)
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h1.product_title")));
            nameProduct = getNameProduct(driver,"h1.product_title");
            if (nameProduct == null) {
                System.out.println("webloi.");
                return "False";
            }
        }
        String folderName = baseFolder + File.separator + index + "_" + nameProduct;
        Files.createDirectories(Paths.get(folderName));
        List<String> urlImage = new ArrayList<>();
        Document doc = Jsoup.parse(driver.getPageSource());


        boolean openAllImage = getClickAction(driver,"button.br__40");
        if(openAllImage == false) {
            System.out.println("Khong co nut!");
        }

//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Đợi cho đến khi modal đóng và hình ảnh trong carousel xuất hiện
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("div.pswp_thumb_item img")
        ));

        // Lấy HTML hiện tại của trang đã được cập nhật
        String pageSource = driver.getPageSource();

        // Phân tích HTML bằng Jsoup
        Document docImage = Jsoup.parse(pageSource);

        Elements imgElements = docImage.select("div.pswp_thumb_item img");
        if (imgElements.size() <= 0) {
            imgElements = docImage.select("div.t4s-row.t4s-row__product div.t4s-col-md-6 div.t4s-row div.t4s-col-12 div.t4s-carousel__nav-scroller div.t4s-row div.t4s-col-item div.t4s_ratio img.lazyautosizes");
        }
        int startLink = 0;
        for (String url: getListImageComponent(driver,imgElements,"_\\d+x", "")) {
            if (!limit.equals("")) {
                System.out.println("limit: "+limit);
                if (startLink >= Integer.parseInt(limit)) {
                    System.out.println("bi vao vung breack");
                    break;
                }
            }
            startLink++;
            urlImage.add(url.replaceAll("\\?.*", ""));
        }

        if(urlImage.size() <= 0) {
            System.out.println("Khong co anh");
            return "False";
        }
        int imgIndex = 1;
        for (String link: urlImage) {
            System.out.println(imgIndex+"_url: "+link);

            String ext = getFileExtension(link);

            String outputPath = getOutPut(folderName,index,imgIndex,ext);
            downloadFile(link,outputPath);
            imgIndex++;
        }

        return "True";
    }
}
