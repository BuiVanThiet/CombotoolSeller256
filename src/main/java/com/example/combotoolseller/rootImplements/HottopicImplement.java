package com.example.combotoolseller.rootImplements;

import com.example.combotoolseller.BaseAll;
import com.example.combotoolseller.rootServices.EmbroideryonmaintxService;
import com.example.combotoolseller.rootServices.HottopicService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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

public class HottopicImplement extends BaseAll implements HottopicService {
    @Override
    public String getDowloadImage(WebDriver driver, String linkRoot, String baseFolder, int index, String limit) throws IOException, InterruptedException {
        String nameProduct = getNameProduct(driver,"h1.product-name");
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
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("div.product__title h1")));
            nameProduct = getNameProduct(driver,"div.product__title h1");
            if (nameProduct == null) {
                System.out.println("webloi.");
                return "False";
            }
        }

        String folderName = baseFolder + File.separator + index + "_" + nameProduct;
        Files.createDirectories(Paths.get(folderName));
        List<String> urlImage = new ArrayList<>();
        Document doc = Jsoup.parse(driver.getPageSource());
        Elements imgElements = doc.select("div.product-images__product-thumbnail-images-container div.product-thumbnail-images div.product-thumbnail-image-wrapper picture img.product-thumbnail-image");
        int startLink = 0;
        for (String url: getListImageComponent(driver,imgElements,"productThumbDesktop", "productMainDesktop")) {
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

//            String outputPath = getOutPut(folderName,index,imgIndex,ext);
            String outputPath = folderName+"/"+imgIndex+"_"+imgIndex+".png";

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
        int quantityLinkMax = 0;
        int checkQuantityCard = 0;
        System.out.println("limit: "+limit);
        while (checkQuantityCard < 10) {
//            for(int i = 0; i < 6; i++) {
//                scrollDownByPixels(driver,2500);
//            }
            Document docLinkProduct = Jsoup.parse(driver.getPageSource());
            Elements productLinks = docLinkProduct.select("div.row.product-grid div.product div.product-tile div.image-container a.pdpLink");
            List<String> listLink = getListLinkProductComponent(productLinks,"https://www.hottopic.com");

            for (String link: listLink) {
                uniqueLinks.add(link);  // Chỉ thêm link nếu chưa có trong HashSet
                System.out.println("size: " +uniqueLinks.size());
                if (uniqueLinks.size() == Integer.parseInt(limit)) {
                    break;
                }
            }

            int quantityCard_a = countLinksInElements(productLinks);
            if (quantityCard_a > quantityLinkMax) {
                quantityLinkMax = quantityCard_a;
            }

            if(quantityLinkMax == quantityCard_a) {
                checkQuantityCard++;
                System.out.println(checkQuantityCard);
            }

            if (uniqueLinks.size() == Integer.parseInt(limit)) {
                break;
            }

//            boolean checkClickNext = clickNextPage(driver,"div.row.product-grid div.col-12.grid-footer div.pagination-wrapper div.pagination.clearfix.show-more.p-0 ul.paginationList li.first-last.next-container a.page-next.pagination-arrow[aria-label='Next']");
//            if(checkClickNext == false) {
//                try {
////                    ((JavascriptExecutor) driver).executeScript(
////                            "var css='.details.d-lg-block.px-lg-2{pointer-events:none!important;opacity:0!important;visibility:hidden!important}';" +
////                                    "var s=document.createElement('style');s.type='text/css';s.appendChild(document.createTextNode(css));" +
////                                    "document.head.appendChild(s);"
////                    );
//
//                    JavascriptExecutor js = (JavascriptExecutor) driver;
//                    js.executeScript("var el = document.getElementById('clockdiv'); if (el) el.style.display = 'none';");
//
//                    Thread.sleep(2000);
//                    checkClickNext = clickNextPage(driver,"div.row.product-grid div.col-12.grid-footer div.pagination-wrapper div.pagination.clearfix.show-more.p-0 ul.paginationList li.first-last.next-container a.page-next.pagination-arrow[aria-label='Next']");
//                    if(checkClickNext == false) {
                        break;
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    break;
//                }
//            } else {
//                quantityLinkMax = 0;
//                checkQuantityCard = 0;
//            }
        }

        List<String> listLink = new ArrayList<>(uniqueLinks);
        for (String link: listLink) {
            System.out.println(quantityLink+"_"+link);
            writeTextFile("./LinkMemory.txt",link);
        }

        return "True";
    }
}
