package com.example.combotoolseller.rootImplements;

import com.example.combotoolseller.BaseAll;
import com.example.combotoolseller.rootServices.BlntsService;
import com.example.combotoolseller.rootServices.FiercepulseService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BlntsImplement extends BaseAll implements BlntsService {
    @Override
    public String getDowloadImage(WebDriver driver, String linkRoot, String baseFolder, int index, String limit) throws IOException, InterruptedException {
        String nameProduct = getNameProduct(driver,"h1.product-single__title");
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
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h1.product-single__title")));
            nameProduct = getNameProduct(driver,"h1.product-single__title");
            if (nameProduct == null) {
                System.out.println("webloi.");
                return "False";
            }
        }

        String folderName = baseFolder + File.separator + index + "_" + nameProduct;
        Files.createDirectories(Paths.get(folderName));
        List<String> urlImage = new ArrayList<>();
        Document doc = Jsoup.parse(driver.getPageSource());
        Elements imgElements = doc.select("div.text-link img");
        int startLink = 0;
        for (String url: getListImageComponent(driver,imgElements,"_110x110", "_2110x2110")) {
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

    @Override
    public String getLinkProductByPage(WebDriver driver, String baseFolder, String limit) {
        uniqueLinks = new HashSet<>();
        int quantityLink = 1;
        boolean checkCloneModal = false;
        System.out.println("limit: "+limit);

        for (int i = 0; i< 100000; i++) {
            if (checkCloneModal == false) {
                boolean clickCloneSale = getClickAction(driver,"button.needsclick.klaviyo-close-form");
                if(clickCloneSale == false) {
                    System.out.println("ko co clickCloneSale");
                }
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {

                }
                clickCloneSale = getClickAction(driver,"div.needsclick[data-testid='animated-teaser'] button.klaviyo-close-form.klaviyo-close-form");
                if(clickCloneSale == false) {
                    System.out.println("ko co clickCloneSale");
                }
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {

                }
                checkCloneModal = true;
            }
            Document docLinkProduct = Jsoup.parse(driver.getPageSource());
            Elements productLinks = docLinkProduct.select("h2.product--info--title a");
            if (productLinks.size() >= Integer.parseInt(limit)) {
                break;
            }
            if (i == 0){
                boolean clickViewAll = getClickAction(driver,"div.plp-spot-products-up div.plp-spot-pagination-wrap button.js-view-all");
                if(clickViewAll == false) {
                    System.out.println("ko co clickViewAll");
                }
            }
            // Cuộn trang xuống dưới cùng
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

            // Đợi một chút để bạn có thể nhìn thấy kết quả
            try {
                Thread.sleep(2000);  // Đợi 2 giây
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        Document docLinkProduct = Jsoup.parse(driver.getPageSource());
        Elements productLinks = docLinkProduct.select("h2.product--info--title a");
        List<String> listLink = getListLinkProductComponent(productLinks,"https://blnts.com");

        for (String link: listLink) {
            uniqueLinks.add(link);  // Chỉ thêm link nếu chưa có trong HashSet
            System.out.println("size: " +uniqueLinks.size());
            if (uniqueLinks.size() == Integer.parseInt(limit)) {
                break;
            }
        }

        // div#ps__widget_container iframe#ps__widget html body div.block-f2187d4b-9b22-4cdf-8c04-45f8e0560cd9 button

        List<String> listLinkAdd = new ArrayList<>(uniqueLinks);
        for (String link: listLinkAdd) {
            System.out.println(quantityLink+"_"+link);
            writeTextFile("./LinkMemory.txt",link);
        }
        return "True";
    }
}
