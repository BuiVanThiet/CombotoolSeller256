package com.example.combotoolseller.rootImplements;

import com.example.combotoolseller.BaseAll;
import com.example.combotoolseller.rootServices.JimocornaService;
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

public class JimocornaImplement extends BaseAll implements JimocornaService {
    @Override
    public String getDowloadImage(WebDriver driver, String linkRoot, String baseFolder, int index, String limit) throws IOException, InterruptedException {

        String nameProduct = getNameProduct(driver,"div.product__title h1");
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

        String folderName = baseFolder + File.separator + index + "-" + nameProduct;
        Files.createDirectories(Paths.get(folderName));
        List<String> urlImage = new ArrayList<>();
        Document doc = Jsoup.parse(driver.getPageSource());
        Elements imgElements = doc.select("media-gallery.product__column-sticky slider-component.thumbnail-slider ul.thumbnail-list li.thumbnail-list__item button.thumbnail img");
//        if (imgElements.size() <= 0) {
//            imgElements = doc.select("media-carousel div.product-gallery__slide div.product-media-item div.product-gallery-shade lqip-element.image-loader img.img--landscape");
//        }
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
            Elements productLinks = docLinkProduct.select("ul#product-grid li.grid__item div.card-wrapper div.card.card--card div.card__content div.card__information h3.card__heading.h5 a.full-unstyled-link");
            List<String> listLink = getListLinkProductComponent(productLinks,"https://jimocorna.com");

            for (String link: listLink) {
                uniqueLinks.add(link.split("\\?")[0]);  // Chỉ thêm link nếu chưa có trong HashSet
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

            boolean checkClickNext = clickNextPage(driver,"li a[aria-label='Next page']");
            if(checkClickNext == false) {
                boolean shouldContinue = showProblemDialog();
                if (!shouldContinue) {
                    System.out.println("Người dùng chọn hủy. Kết thúc.");
                    break;
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                checkClickNext = clickNextPage(driver,"li a[aria-label='Next page']");
                if (checkClickNext == false) {
                    System.out.println("webloi.");
                    break;
                } else {
                    quantityLinkMax = 0;
                    checkQuantityCard = 0;
                }
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
