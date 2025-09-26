package com.example.combotoolseller.rootImplements;

import com.example.combotoolseller.BaseAll;
import com.example.combotoolseller.rootServices.AfrodomService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class AfrodomImplement extends BaseAll implements AfrodomService {

    @Override
    public String getDowloadImage(WebDriver driver, String linkRoot, String baseFolder, int index, String limit) throws IOException, InterruptedException {
        String nameProduct = getNameProduct(driver,"h1");
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
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h1")));
            nameProduct = getNameProduct(driver,"h1");
            if (nameProduct == null) {
                System.out.println("webloi.");
                return "False";
            }
        }

        String folderName = baseFolder + File.separator + index + "_" + nameProduct;
        Files.createDirectories(Paths.get(folderName));
        List<String> urlImage = new ArrayList<>();
        Document doc = Jsoup.parse(driver.getPageSource());
        Elements imgElements = doc.select("div.row div.col-12 div.media-gallery-carousel ul.flex-wrap li.relative img");
        if (imgElements.size() <= 0) {
            imgElements = doc.select("div[role='tabpanel'] img.image.sb-lazy.priority.progressive.w-100.shape-round-all");
        }

        int startLink = 0;
        for (String url: getListImageComponent(driver,imgElements,"\\d+x\\d+", "2000x2000")) {
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
        int quantityLinkMax = 0;
        int checkQuantityCard = 0;
        System.out.println("limit: "+limit);
        while (checkQuantityCard < 10) {
            Document docLinkProduct = Jsoup.parse(driver.getPageSource());
            Elements productLinks = docLinkProduct.select("div.w-100 div div.product-grid div.product-col div.collection-product-wrap a.\\:hover-no-underline.d-block");
            List<String> listLink = getListLinkProductComponent(productLinks,"https://afrodom.com");

            for (String link: listLink) {
                uniqueLinks.add(link);  // Chỉ thêm link nếu chưa có trong HashSet
                System.out.println("size: " +uniqueLinks.size());
                if (uniqueLinks.size() == Integer.parseInt(limit)) {
                    break;
                }
            }

            scrollDownByPixels(driver,1200);
            if(checkCloneModal == false) {
                boolean checkCloneSale = getClickAction(driver,"button[aria-label='Close dialog']");
                if(checkCloneSale == false) {
                    System.out.println("Khong co nut sale!");
                } else {
                    checkCloneModal = true;
                }
            }

            int quantityCard_a = countLinksInElements(productLinks);
            if(quantityLinkMax == quantityCard_a) {
                checkQuantityCard++;
                System.out.println(checkQuantityCard);
            }
            if (quantityCard_a > quantityLinkMax) {
                quantityLinkMax = quantityCard_a;
            }

            if (uniqueLinks.size() == Integer.parseInt(limit)) {
                break;
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
