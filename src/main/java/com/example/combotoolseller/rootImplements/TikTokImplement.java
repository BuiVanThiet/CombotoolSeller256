package com.example.combotoolseller.rootImplements;

import com.example.combotoolseller.BaseAll;
import com.example.combotoolseller.rootServices.TikTokService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class TikTokImplement extends BaseAll implements TikTokService {
    @Override
    public String getDowloadImage(WebDriver driver,String linkRoot,String baseFolder, int index, String limit) throws IOException, InterruptedException {
        System.out.println("driver.getTitle(): "+driver.getTitle());
        if (driver.getTitle().equals("Security Check")) {
            boolean shouldContinue = showProblemDialog();
            if (!shouldContinue) {
                System.out.println("Người dùng chọn hủy. Kết thúc.");
                return "False";
            }
            driver.navigate().refresh();
        }

        if (getCheckSecurity(driver) == false) {
            return "False";
        }

        String nameProduct = getNameProduct(driver,"h1");
        System.out.println("name: "+nameProduct);

        if (getCheckSecurity(driver) == false) {
            return "False";
        }

        boolean checkCloneSale = getClickAction(driver,"div.tux-web-canary div[class^='closeIcon-'] svg");
        if(checkCloneSale == false) {
            System.out.println("Khong co nut sale!");
        }

        if (getCheckSecurity(driver) == false) {
            return "False";
        }

        boolean checkCloneDowloadTiktok = getClickAction(driver,"button.tux-base-dialog__close-button");
        if(checkCloneDowloadTiktok == false) {
            System.out.println("Khong co nut dowload!");
        }

        if (nameProduct == null) {
            nameProduct = getNameProduct(driver,"div.flex.flex-col span.H2-Semibold.text-color-UIText1Display[data-fmp='true']");
            System.out.println("name: "+nameProduct);
        }

        if (nameProduct == null) {
            System.out.println("webloi.");
            return "False";
        }

        List<String> urlImage = new ArrayList<>();
        Document doc = Jsoup.parse(driver.getPageSource());

        Elements imgElements = doc.select("div.slick-slider div.slick-list div.slick-track div.slick-slide div div.item-ACAGi1 img");
        if (imgElements.size() <= 0) {
            imgElements = doc.select("div.w-full.relative.flex.items-center.mt-16 div.flex.flex-row.items-center.gap-12.w-full.overflow-x-scroll.relative.overflow-visible div.relative.flex.justify-center.items-center.w-66.h-66.grow-0.shrink-0 img");
        }
        int imgIndex = 1;
        int startLink = 0;
        for (String url: getListImageComponent(driver,imgElements,"resize-(jpeg|webp):\\d+:\\d+\\.[a-z]+\\?[^\\s]*","origin-jpeg.jpeg")) {
            if (!limit.equals("")) {
                System.out.println("limit: "+limit);
                if (startLink >= Integer.parseInt(limit)) {
                    System.out.println("bi vao vung breack");
                    break;
                }
            }
            startLink++;
            urlImage.add(url);
            String folderName = baseFolder + File.separator + index + "_" + nameProduct;
            Files.createDirectories(Paths.get(folderName));
            String ext = getFileExtension(url);
            System.out.println("URL CAT CHUOI: "+url);
            String outputPath = getOutPut(folderName,index,imgIndex,ext);
            downloadFile(url,outputPath);
            imgIndex++;
        }

        if(urlImage.size() <= 0) {
            return "False";
        }
        return "True";
    }

    @Override
    public String getDowloadImageVersion(WebDriver driver, String linkRoot, String baseFolder, int index,String limit) throws IOException, InterruptedException {
        System.out.println("driver.getTitle(): "+driver.getTitle());
        if (driver.getTitle().equals("Security Check")) {
            boolean shouldContinue = showProblemDialog();
            if (!shouldContinue) {
                System.out.println("Người dùng chọn hủy. Kết thúc.");
                return "False";
            }
            driver.navigate().refresh();
        }

        if (getCheckSecurity(driver) == false) {
            return "False";
        }
        String nameProduct = getNameProduct(driver,"h1");
        if (nameProduct == null) {
            nameProduct = getNameProduct(driver,"div.mx-16 h1");
        }
        System.out.println("name: "+nameProduct);

        if (getCheckSecurity(driver) == false) {
            return "False";
        }

        boolean checkCloneSale = getClickAction(driver,"div.tux-web-canary div[class^='closeIcon-'] svg");
        if(checkCloneSale == false) {
            System.out.println("Khong co nut sale!");
        }

        if (getCheckSecurity(driver) == false) {
            return "False";
        }

        boolean checkCloneDowloadTiktok = getClickAction(driver,"button.tux-base-dialog__close-button");
        if(checkCloneDowloadTiktok == false) {
            System.out.println("Khong co nut dowload!");
        }

        if (nameProduct == null) {
            nameProduct = getNameProduct(driver,"div.flex.flex-col span.H2-Semibold.text-color-UIText1Display[data-fmp='true']");
            System.out.println("name: "+nameProduct);
        }

        if (nameProduct == null) {
            System.out.println("webloi.");
            return "False";
        }

        List<String> urlImage = new ArrayList<>();
        Document doc = Jsoup.parse(driver.getPageSource());
        Elements imgElements = doc.select("div.slick-slider div.slick-list div.slick-track div.slick-slide div div.item-ACAGi1 img");
        if (imgElements.size() <= 0) {
            imgElements = doc.select("div.w-full.relative.flex.items-center.mt-16 div.flex.flex-row.items-center.gap-12.w-full.overflow-x-scroll.relative.overflow-visible div.relative.flex.justify-center.items-center.w-66.h-66.grow-0.shrink-0 img");
        }
        int imgIndex = 1;
        int startLink = 0;
        for (String url: getListImageComponent(driver,imgElements,"resize-(jpeg|webp):\\d+:\\d+\\.[a-z]+\\?[^\\s]*","origin-jpeg.jpeg")) {
            if (!limit.equals("")) {
                System.out.println("limit: "+limit);
                if (startLink >= Integer.parseInt(limit)) {
                    System.out.println("bi vao vung breack");
                    break;
                }
            }
            startLink++;
            urlImage.add(url);
            String folderName = baseFolder + File.separator + index + "_" + nameProduct;
            Files.createDirectories(Paths.get(folderName));
            String ext = getFileExtension(url);
            System.out.println("URL CAT CHUOI: "+url);
            String outputPath = getOutPut(folderName,index,imgIndex,ext);
            downloadFile(url,outputPath);
            imgIndex++;
        }

        if (getCheckSecurity(driver) == false) {
            return "False";
        }
        Thread.sleep(2000);
        boolean checkCloneSecurity = getClickAction(driver,"div.captcha_verify_bar div.captcha_verify_bar--close a.verify-bar-close");
        if(checkCloneSecurity == false) {
            System.out.println("Khong co nut Security!");
        }

        boolean checkCloneSpin = getClickAction(driver,"div[class^='closeIcon-'] svg");
        if(checkCloneSpin == false) {
            System.out.println("Khong co nut Spin!");
        }
        Thread.sleep(2000);
        List<WebElement> elements = driver.findElements(By.cssSelector("div.mx-16 div.itemContainer-y229Va div.firstLineContainer-jKMRis"));
        if (!elements.isEmpty()) {
            elements.get(0).click();  // phần tử đầu tiên
            System.out.println("Đã nhấn vào phần tử đầu tiên");
            Thread.sleep(2000);
        }
        Document doc2 = Jsoup.parse(driver.getPageSource());
        imgElements = doc2.select("div.expand-OeIAiB div.specItemArea-BszsWn div.imageFrame-BnLbCU img.image-HX3r7O.visible-n8Ln5m");
        if (imgElements.size() <= 0) {
            imgElements = doc2.select("div.flex.flex-row.overflow-x-auto.gap-12.flex-wrap div.flex.flex-col.p-6.rounded-4.border-2.items-center.w-80.justify-center.border-solid.cursor-pointer img");
        }
        imgIndex = 1;
        startLink = 0;
        for (String url: getListImageComponent(driver,imgElements,"resize-(jpeg|webp):\\d+:\\d+\\.[a-z]+\\?[^\\s]*","origin-jpeg.jpeg")) {
            if (!limit.equals("")) {
                System.out.println("limit: "+limit);
                if (startLink >= Integer.parseInt(limit)) {
                    System.out.println("bi vao vung breack");
                    break;
                }
            }
            startLink++;
            urlImage.add(url);
            String folderName = baseFolder + File.separator + index + "_" + nameProduct +File.separator+"version";
            Files.createDirectories(Paths.get(folderName));
            String ext = getFileExtension(url);
            System.out.println(url);
            String outputPath = getOutPut(folderName,index,imgIndex,ext);
            downloadFile(url,outputPath);
            imgIndex++;
        }

        if(urlImage.size() <= 0) {
            return "False";
        }
        return "True";
    }

    public boolean getCheckSecurity(WebDriver driver) {
        String checkSecurity = getNameProduct(driver,"captcha_verify_bar--title");
        if (checkSecurity == null) {
            return true;
        }
        if (checkSecurity.trim().contains("Verify to continue:")) {
            boolean shouldContinue = showProblemDialog();
            if (!shouldContinue) {
                System.out.println("Người dùng chọn hủy. Kết thúc.");
                return false;
            }
            driver.navigate().refresh();
        }
        return true;
    }

}
