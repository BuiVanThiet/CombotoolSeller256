package com.example.combotoolseller.rootImplements;

import com.example.combotoolseller.BaseAll;
import com.example.combotoolseller.rootServices.GerberaprintsService;
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
import java.util.HashSet;
import java.util.List;

public class GerberaprintsImplement extends BaseAll implements GerberaprintsService {
    @Override
    public String getDowloadImage(WebDriver driver, String linkRoot, String baseFolder, int index, String limit) throws IOException, InterruptedException {
        String nameProduct = getNameProduct(driver,"h1.t4s-product__title");
        Thread.sleep(2000);
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
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h1.t4s-product__title")));
            nameProduct = getNameProduct(driver,"h1.t4s-product__title");
            if (nameProduct == null) {
                System.out.println("webloi.");
                return "False";
            }
        }
        String folderName = baseFolder + File.separator + index + "_" + nameProduct;
        Files.createDirectories(Paths.get(folderName));
        List<String> urlImage = new ArrayList<>();
        Document doc = Jsoup.parse(driver.getPageSource());

//        List<WebElement> elements = driver.findElements(By.cssSelector("div.t4s-row.t4s-row__product div.t4s-col-md-6 div.t4s-row div.t4s-col-12 div.t4s-pr-group-btns button.t4s-pr__pswp-btn"));
//        if (!elements.isEmpty()) {
//            elements.get(0).click();  // phần tử đầu tiên
//            System.out.println("Đã nhấn vào phần tử đầu tiên");
//            Thread.sleep(2000);
//        }

        boolean checkCloneSale = getClickAction(driver,"div.t4s-row.t4s-row__product div.t4s-col-md-6 div.t4s-row div.t4s-col-12 div.t4s-pr-group-btns button.t4s-pr__pswp-btn");
        if(checkCloneSale == false) {
            System.out.println("Khong co nut!");
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Đợi cho đến khi modal đóng và hình ảnh trong carousel xuất hiện
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("div.pswp.pswp__t4s div.pswp__thumbnails div.pswp_thumb_item img")
        ));

        // Lấy HTML hiện tại của trang đã được cập nhật
        String pageSource = driver.getPageSource();

        // Phân tích HTML bằng Jsoup
        Document docImage = Jsoup.parse(pageSource);

        Elements imgElements = docImage.select("div.pswp.pswp__t4s div.pswp__thumbnails div.pswp_thumb_item img");
        if (imgElements.size() <= 0) {
            imgElements = docImage.select("div.t4s-row.t4s-row__product div.t4s-col-md-6 div.t4s-row div.t4s-col-12 div.t4s-carousel__nav-scroller div.t4s-row div.t4s-col-item div.t4s_ratio img.lazyautosizes");
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
            System.out.println("Khong co anh");
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
        int quantityLinkMax = 0;
        int checkQuantityCard = 0;
        System.out.println("limit: "+limit);
        while (checkQuantityCard < 10) {
            Document docLinkProduct = Jsoup.parse(driver.getPageSource());
            Elements productLinks = docLinkProduct.select("h3.t4s-product-title a");
            List<String> listLink = getListLinkProductComponent(productLinks,"https://gerberaprints.com");

            for (String link: listLink) {
                uniqueLinks.add(link);  // Chỉ thêm link nếu chưa có trong HashSet
                System.out.println("size: " +uniqueLinks.size());
                if (uniqueLinks.size() == Integer.parseInt(limit)) {
                    break;
                }
            }

            if(checkCloneModal == false) {
                boolean checkCloneSale = getClickAction(driver,"button[aria-label='Close dialog']");
                if(checkCloneSale == false) {
                    System.out.println("Khong co nut sale!");
                } else {
                    checkCloneModal = true;
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

            boolean checkClickNext = clickNextPage(driver,"li a[aria-label='Next']");
            if(checkClickNext == false) {
                break;
            } else {
                quantityLinkMax = 0;
                checkQuantityCard = 0;
            }
        }

        List<String> listLink = new ArrayList<>(uniqueLinks);
        for (String link: listLink) {
            System.out.println(quantityLink+"_"+link);
            writeTextFile("./LinkMemory.txt",link);
        }

        return "True";
    }
}
