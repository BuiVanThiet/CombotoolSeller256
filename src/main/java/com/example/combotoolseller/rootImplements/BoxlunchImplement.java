package com.example.combotoolseller.rootImplements;

import com.example.combotoolseller.BaseAll;
import com.example.combotoolseller.rootServices.BoxlunchService;
import com.example.combotoolseller.rootServices.HottopicService;
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

public class BoxlunchImplement extends BaseAll implements BoxlunchService {
    @Override
    public String getDowloadImage(WebDriver driver, String linkRoot, String baseFolder, int index, String limit) throws IOException, InterruptedException {
        String nameProduct = getNameProduct(driver,"div.col.license-link-category h1.product-name");
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
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("div.col.license-link-category h1.product-name")));
            nameProduct = getNameProduct(driver,"div.col.license-link-category h1.product-name");
            if (nameProduct == null) {
                System.out.println("webloi.");
                return "False";
            }
        }

        String folderName = baseFolder + File.separator + index + "_" + nameProduct;
        Files.createDirectories(Paths.get(folderName));
        List<String> urlImage = new ArrayList<>();
        Document doc = Jsoup.parse(driver.getPageSource());
        Elements imgElements = doc.select("div.product-images__product-thumbnail-images-container.col-xl-2.p-0 div.product-thumbnail-image-wrapper picture img.product-thumbnail-image");
        int startLink = 0;
        for (String url: getListImageComponent(driver,imgElements,"productThumbDesktop$&fmt=auto", "productThumbDesktop")) {
            if (!limit.equals("")) {
                System.out.println("limit: "+limit);
                if (startLink >= Integer.parseInt(limit)) {
                    System.out.println("bi vao vung breack");
                    break;
                }
            }
            startLink++;
            // 1️⃣ Xóa đoạn &fmt=auto và tất cả phần sau nó
            String cleaned = url.replaceAll("&fmt=auto.*$", "");

            // 2️⃣ Xóa ký tự $ (nếu có)
            cleaned = cleaned.replace("$", "");

            urlImage.add(cleaned);
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
            if(checkCloneModal == false) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    // Chuyển đến iframe
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div iframe#attentive_creative")));

                    // Chuyển vào iframe
                    driver.switchTo().frame(iframe);

                    // Tìm phần tử trong iframe và thao tác với nó
                    WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button#closeIconContainer")));  // Chỉnh sửa lại selector nếu cần

                    // Thực hiện hành động như click vào phần tử
                    element.click();  // Ví dụ click vào phần tử
// Tìm phần tử footer
                    Thread.sleep(2000);
                    driver.switchTo().defaultContent();
                } catch (Exception e) {

                }
                checkCloneModal = true;
            }

            this.scrollToFooterSlowly(driver);

            Document docLinkProduct = Jsoup.parse(driver.getPageSource());
            Elements productLinks = docLinkProduct.select("div.row.product-grid a.link");
            List<String> listLink = getListLinkProductComponent(productLinks,"https://www.boxlunch.com");

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
//            this.scrollToElementSlowly(driver,"li.first-last.next-container");
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollBy(0, arguments[0]);", -600);
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            boolean checkClickNext = safeClickNext(driver);
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

    private static void hideObstructingDivs(WebDriver driver) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                    "document.querySelectorAll('div.details.d-lg-block.px-lg-2').forEach(e => e.style.display = 'none');"
            );
            System.out.println("✅ Đã ẩn toàn bộ div.details.d-lg-block.px-lg-2");
        } catch (Exception e) {
            System.err.println("⚠️ Không thể ẩn div.details.d-lg-block.px-lg-2: " + e.getMessage());
        }
    }

    /**
     * Cuộn đến và click an toàn vào nút Next
     */
    public static boolean safeClickNext(WebDriver driver) {
        try {
            hideObstructingDivs(driver);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement nextButton = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.cssSelector("li.first-last.next-container a.page-next.pagination-arrow")
                    )
            );

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", nextButton);
            Thread.sleep(500);

            // Click bằng JavaScript để bỏ qua lỗi “obscured”
            js.executeScript("arguments[0].click();", nextButton);

            System.out.println("✅ Đã click thành công vào nút Next");
            return true;
        } catch (Exception e) {
            System.err.println("⛔ Lỗi khi click nút Next: " + e.getMessage());
            return false;
        }
    }

}
