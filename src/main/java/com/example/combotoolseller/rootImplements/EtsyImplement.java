package com.example.combotoolseller.rootImplements;

import com.example.combotoolseller.BaseAll;
import com.example.combotoolseller.rootEntites.EtsyEntity;
import com.example.combotoolseller.rootServices.EtsyService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EtsyImplement extends BaseAll implements EtsyService {
    static Set<String> uniqueUrls = new HashSet<>();
    @Override
    public String getDowloadImage(WebDriver driver, String linkRoot, String baseFolder, int index, String limit) throws IOException, InterruptedException {
        String pageSource = driver.getPageSource();
        Document doc = Jsoup.parse(pageSource);
        System.out.println("da vao implement etsy");
        try{
            Element linkError = doc.selectFirst("div.wt-grid div.wt-grid__item-md-6 h2.wt-text-body-01");
            if(linkError != null && linkError.text().trim().equals("Sorry, the page you were looking for was not found.")) {
                System.out.println("⚠ Link loi");
                return "Fail";
            }
        }catch (Exception e) {

        }
        try{
            Elements checkBots = doc.select("p.captcha__human__title");
            if(checkBots.size() > 0) {
                boolean shouldContinue = showProblemDialog();
                if (!shouldContinue) {
                    System.out.println("Người dùng chọn hủy. Kết thúc.");
                    return "False";
                }
            }
        }catch (Exception e) {

        }

        String nameProduct = getNameProduct(driver,"h1.wt-line-height-tight");
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
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h1.wt-line-height-tight")));
            nameProduct = getNameProduct(driver,"h1.wt-line-height-tight");
            if (nameProduct == null) {
                System.out.println("webloi.");
                return "False";
            }
        }
        System.out.println("name etsy: "+nameProduct);

        String folderName = baseFolder + File.separator + index + "_" + nameProduct;
        Files.createDirectories(Paths.get(folderName));
        List<String> urlImage = new ArrayList<>();
        Elements imgElements = doc.select("li[data-carousel-pagination-item] img[data-carousel-thumbnail-image]");
        if (imgElements.size() <= 0) {
            imgElements = doc.select("img.wt-max-width-full.wt-horizontal-center.wt-vertical-center.carousel-image.wt-rounded");
        }
        int startLink = 0;
        for (String url: getListImageComponent(driver,imgElements,"il_\\d+x\\d+", "il_fullxfull")) {
            System.out.println("url: "+url);
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

    // Hàm kiểm tra nếu sản phẩm được bán bởi Amazon
    public static boolean checkThisItem(WebDriver driver) {
        try {
            String pageSource = driver.getPageSource();
            // Sử dụng Jsoup để phân tích mã nguồn HTML
            Document doc = Jsoup.parse(pageSource);
            Elements productLinks = doc.select("div#reviews div.wt-flex-xl-5 div.wt-grid div.wt-grid__item-xs-12 div.wt-bb-xs div.wt-flex-xs-1 div.wt-flex-md-3 div.wt-mb-xs-1 span.wt-badge");

            System.out.println("Số phần tử tìm được: " + productLinks.size());

            return !productLinks.isEmpty();
        } catch (Exception e) {
            System.out.println("Lỗi khi tìm phần tử: " + e.getMessage());
            return false;
        }
    }

    // Hàm kiểm tra xem trang có chứa từ "bought"
    public static boolean checkReview(WebDriver driver) {
        try {
            String pageSource = driver.getPageSource();
            // Sử dụng Jsoup để phân tích mã nguồn HTML
            Document doc = Jsoup.parse(pageSource);
            Elements productLinks = doc.select("div.wt-align-items-flex-start div div.wt-mb-md-4 div.wt-tab-container div.reviews__tabs button.wt-tab__item#same-listing-reviews-tab");

            System.out.println("Số phần tử tìm được: " + productLinks.size());

            return !productLinks.isEmpty();
        } catch (Exception e) {
            // Bắt thêm các ngoại lệ khác nếu cần
            return false;
        }
    }

    public static String check24Hours(WebDriver driver) {
        // Tìm phần tử theo XPath và lấy text
        try {
            // Truyền vào XPath của phần tử mà bạn muốn lấy text
            WebElement bylineElement = driver.findElement(By.cssSelector("div.wt-display-flex-lg div.wt-mb-xs-1 p.wt-text-title-01.wt-text-brick"));

            String check = bylineElement.getText();
            System.out.println(check);

            if(check.trim().contains("views in the last 24 hours")) {
                return "views in the last 24 hours";
            } else if (check.trim().contains("carts")) {
                Pattern pattern = Pattern.compile("(\\d+\\+?)\\s+carts");
                Matcher matcher = pattern.matcher(check.toLowerCase().trim());
                if (matcher.find()) {
                    String numberStr = matcher.group(1);
                    return check;
                } else {
                    System.out.println("No carts number found in: " + check);
                    return null;
                }
            } else if (check.trim().contains("bought this in the last 24 hours")) {
                return "bought this in the last 24 hours";
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Element not found.");
            return null;
        }
    }

    public static void checkLink(List<Element> links,int pageCount,int targetCount,WebDriver driver) throws IOException, InterruptedException {
        String inputFile = "./Input/LinkExcept.txt";
        List<String> urls = Files.readAllLines(Paths.get(inputFile));
        // In ra tất cả các URL trong các thẻ a
        for (Element link : links) {
            String url = link.attr("href");
            url = url.split("\\?")[0];  // Lấy phần trước dấu "?"
            // Bước 2: Cắt path, giữ đến phần listing + id
            String[] parts = url.split("/");
            // parts = ["https:", "", "www.etsy.com", "listing", "4303292511", "funny3dpufffoamembroideredupsidedownbase"]
            // Giữ lại tới phần thứ 5 (index 4)
            if(parts.length > 5) {
                url = parts[0] + "//" + parts[2] + "/" + parts[3] + "/" + parts[4] + "/";
            }
            System.out.println(url);
            String result = "";
            // Kiểm tra điều kiện: không trùng lặp và không chứa #customerReviews
            if (!url.isEmpty()) {
                // Nếu URL chưa gặp, in ra và thêm vào Set
                if (uniqueUrls.add(url)) {
                    // Tạo hoặc mở file LinkMemory.txt với chế độ append
                    File linkFile = new File("LinkMemory.txt");

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(linkFile, true))) {
                        // Ghi đường link vào file với định dạng yêu cầu
                        if(getCheckLinkMemory(urls,url) == true) {
                            writer.write(url);
                            writer.newLine(); // Đảm bảo mỗi link nằm trên một dòng mới
                        }
                    } catch (IOException e) {
                        System.out.println("Có lỗi khi ghi vào file: " + e.getMessage());
                    }
                }
            }
        }

        if (pageCount <= targetCount) {
            WebElement buttonNext = checkNext(driver);
            if(buttonNext != null) {
                buttonNext.click();
                Thread.sleep(3000);
            }

        }
    }
    public static WebElement checkNext(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            List<WebElement> nextButtons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//nav[@aria-label='Pagination of listings']//a[./span[contains(@class,'wt-screen-reader-only') and contains(text(),'Next page')]]")
            ));

            if (nextButtons.size() >= 3) {
                WebElement targetNextButton = nextButtons.get(2); // phần tử thứ 3 (index bắt đầu từ 0)

                // Kiểm tra class wt-is-disabled
                String classes = targetNextButton.getAttribute("class");
                if (classes != null && classes.contains("wt-is-disabled")) {
                    System.out.println("Nút next bị disabled, không click nữa."); // hoặc break nếu trong vòng lặp
                    return null;
                }

                WebDriverWait waitClickable = new WebDriverWait(driver, Duration.ofSeconds(10));
                waitClickable.until(ExpectedConditions.elementToBeClickable(targetNextButton));

                if (targetNextButton.isDisplayed() && targetNextButton.isEnabled()) {
                    return targetNextButton;
                } else {
                    System.out.println("Phần tử thứ 3 không thể click được");
                    return null;
                }
            } else {
                System.out.println("Không tìm thấy đủ 3 nút next");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Không tìm thấy hoặc không thể bấm nút next: " + e.getMessage());
            return null;
        }
    }

    public static void checkLinkKeySearch(List<Element> links,int pageCount,int targetCount,WebDriver driver) throws IOException, InterruptedException {
        String inputFile = "./Input/LinkExcept.txt";
        List<String> urls = Files.readAllLines(Paths.get(inputFile));
        // In ra tất cả các URL trong các thẻ a
        for (Element link : links) {
            String url = link.attr("href");
            url = url.split("\\?")[0];  // Lấy phần trước dấu "?"
            // Bước 2: Cắt path, giữ đến phần listing + id
            String[] parts = url.split("/");
            // parts = ["https:", "", "www.etsy.com", "listing", "4303292511", "funny3dpufffoamembroideredupsidedownbase"]
            // Giữ lại tới phần thứ 5 (index 4)
            if(parts.length > 5) {
                url = parts[0] + "//" + parts[2] + "/" + parts[3] + "/" + parts[4] + "/";
            }
            System.out.println(url);
            String result = "";
            // Kiểm tra điều kiện: không trùng lặp và không chứa #customerReviews
            if (!url.isEmpty()) {
                // Nếu URL chưa gặp, in ra và thêm vào Set
                if (uniqueUrls.add(url)) {
                    // Tạo hoặc mở file LinkMemory.txt với chế độ append
                    File linkFile = new File("./LinkMemory.txt");

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(linkFile, true))) {
                        // Ghi đường link vào file với định dạng yêu cầu
                        if(getCheckLinkMemory(urls,url) == true) {
                            writer.write(url);
                            writer.newLine(); // Đảm bảo mỗi link nằm trên một dòng mới
                        }
                    } catch (IOException e) {
                        System.out.println("Có lỗi khi ghi vào file: " + e.getMessage());
                    }
                }
            }
        }

        if (pageCount <= targetCount) {
            WebElement buttonNext = checkNextSearchKey(driver);
            if(buttonNext != null) {
                buttonNext.click();
                Thread.sleep(3000);
            }

        }
    }

    public static boolean getCheckLinkMemory(List<String> listLink, String link) {
        for (String linkCheck: listLink) {
            if(linkCheck.equals(link)) {
                return false;
            }
        }
        return true;
    }

    public static WebElement checkNextSearchKey(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Lấy danh sách tất cả các nút matching selector
            List<WebElement> buttons = driver.findElements(By.cssSelector(
                    "div.wt-mb-xs-5 div div.appears-ready div div.wt-hide-xs nav[aria-label='Review Page Results'] div.wt-action-group div.wt-action-group__item-container a.wt-btn"
            ));

            if (buttons.isEmpty()) {
                System.out.println("Không tìm thấy nút 'Next'.");
                return null;
            }

            // Lấy phần tử cuối cùng
            WebElement nextButton = buttons.get(buttons.size() - 1);

            // Chờ phần tử cuối cùng có thể click được
            wait.until(ExpectedConditions.elementToBeClickable(nextButton));

            // Cuộn trang tới nút "Next" để tránh bị che khuất
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextButton);

            String classes = nextButton.getAttribute("class");

            // Kiểm tra nút không bị disabled và có thể click
            if (nextButton.isDisplayed() && nextButton.isEnabled() && (classes == null || !classes.contains("disabled"))) {
                String currentUrl = driver.getCurrentUrl();
                System.out.println("URL hiện tại: " + currentUrl);
                return nextButton;
            } else {
                System.out.println("Nút 'Next' bị disabled hoặc không thể click.");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Không tìm thấy hoặc không thể bấm nút next: " + e.getMessage());
            return null;
        }
    }


}
