package com.example.combotoolseller.rootImplements;

import com.example.combotoolseller.BaseAll;
import com.example.combotoolseller.rootEntites.AmazonEntity;
import com.example.combotoolseller.rootServices.AmazonService;
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
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AmazonImplement extends BaseAll implements AmazonService {
    static String conditionType = "";
    @Override
    public String getDowloadImageColor(WebDriver driver, String linkRoot, String baseFolder, int index,List<String> colors, String limit) throws IOException, InterruptedException {
        String nameProduct = getNameProduct(driver,"h1");

        // Phát hiện lỗi -> tạm dừng chương trình, đợi người xử lý
        if(nameProduct == null) {
            boolean shouldContinue = showProblemDialog();
            if (!shouldContinue) {
                System.out.println("Người dùng chọn hủy. Kết thúc.");
                return "False";
            }
        }

        Document doc = Jsoup.parse(driver.getPageSource());
        Elements listImageColor = doc.select("div div img.imgSwatch");
        Elements listImageColorV2 = doc.select("span.a-list-item span.image-swatch-button span.a-button-inner span.a-button-text div.swatch-image-container div.swatch-image-wrapper img.swatch-image");
        int imgIndex = 1;
        int numberColor = 0;

        for (Element color : listImageColor.isEmpty() ? listImageColorV2 : listImageColor) {
            // Duyệt qua các màu trong danh sách colors
            for (String colorCustom : colors) {
                // Lấy giá trị của alt từ phần tử img
                String colorCheck = color.hasAttr("alt") ? color.attr("alt") : "";

                // Kiểm tra xem alt có khớp với màu trong colors không
                if (colorCheck.trim().equalsIgnoreCase(colorCustom.trim())) {
                    if(!listImageColor.isEmpty()) {
                        clickColorV1(driver,"button img[alt='" + colorCheck + "']");
                    }else if (!listImageColorV2.isEmpty()) {
                        clickColorV2(driver,"span.a-button-inner input[aria-labelledby='color_name_" + numberColor + "-announce']");
                    }
                    // Đợi một chút để xác nhận hành động click
                    Thread.sleep(5000); // Bạn có thể điều chỉnh thời gian này tùy vào yêu cầu
                    // Cập nhật lại nội dung trang và parse lại với Jsoup
                    String updatedHtml = driver.getPageSource();  // Lấy lại HTML sau khi tương tác
                    Document updatedDoc = Jsoup.parse(updatedHtml);  // Parse lại HTML mới

                    // Tìm các thumbnails trong HTML mới
                    Elements thumbnails = updatedDoc.select("li.image span.a-list-item span.a-declarative div.imgTagWrapper img.a-dynamic-image");
                    int startLink = 0;
                    for (Element img : thumbnails) {
                        String imgUrl = img.hasAttr("src") ? img.attr("src") : "";
                        if (imgUrl.isEmpty()) continue;

                        // Thêm https nếu thiếu
                        if (imgUrl.startsWith("//")) {
                            imgUrl = "https:" + imgUrl;
                        }
                        if (!limit.equals("")) {
                            System.out.println("limit: "+limit);
                            if (startLink >= Integer.parseInt(limit)) {
                                System.out.println("bi vao vung breack");
                                break;
                            }
                        }
                        startLink++;
                        // Loại bỏ &width=... khỏi URL
                        imgUrl = imgUrl.replaceAll("SX\\d+_", "");
                        String ext = getFileExtension(imgUrl);
                        String folderName = baseFolder + File.separator + index + "_" + nameProduct+"/"+colorCheck;
                        Files.createDirectories(Paths.get(folderName));
                        String outputPath = getOutPut(folderName,index,imgIndex,ext);
                        downloadFile(imgUrl,outputPath);

                        System.out.println("🖼️ Đã tải ảnh: " + outputPath);
                        imgIndex++;
                    }
                }
            }
            numberColor++;
        }

        return "True";
    }

    @Override
    public String getDowloadImageNotColor(WebDriver driver, String linkRoot, String baseFolder, int index, String limit) throws IOException, InterruptedException {
        String nameProduct = getNameProduct(driver,"h1");

        // Phát hiện lỗi -> tạm dừng chương trình, đợi người xử lý
        if(nameProduct == null) {
            boolean shouldContinue = showProblemDialog();
            if (!shouldContinue) {
                System.out.println("Người dùng chọn hủy. Kết thúc.");
                return "False";
            }
        }

        String folderName = baseFolder + File.separator + index + "_" + nameProduct;
        Files.createDirectories(Paths.get(folderName));
        Thread.sleep(3000);

        List<WebElement> listImageHover = driver.findElements(By.cssSelector("div#altImages ul.a-unordered-list li.a-spacing-small[data-csa-c-action='image-block-alt-image-hover'] span.a-list-item span.a-button.a-button-thumbnail span.a-button-inner input.a-button-input"));
        if(listImageHover != null) {
            for (WebElement imgHover :  listImageHover) {
                imgHover.click();
                Thread.sleep(3000);
            }
        }

        List<String> urlImage = new ArrayList<>();
        String updatedHtml = driver.getPageSource();
        Document updatedDoc = Jsoup.parse(updatedHtml);
        Elements thumbnails = updatedDoc.select("li.image span.a-list-item span.a-declarative div.imgTagWrapper img.a-dynamic-image");
        if (thumbnails == null) {
            thumbnails = updatedDoc.select("img#landingImage");
        }
        for (String url: getListImageComponent(driver,thumbnails,"[A-Z]\\d+_","")) {
            urlImage.add(url);
        }

        if(urlImage.size() <= 0) {
            return "False";
        }
        int imgIndex = 1;
        int startLink = 0;
        for (String link: urlImage) {
            String ext = getFileExtension(link);
            if (!limit.equals("")) {
                System.out.println("limit: "+limit);
                if (startLink >= Integer.parseInt(limit)) {
                    System.out.println("bi vao vung breack");
                    break;
                }
            }
            startLink++;
            String outputPath = getOutPut(folderName,index,imgIndex,ext);
            downloadFile(link,outputPath);
            imgIndex++;
        }

        return "True";
    }


    @Override
    public String getDowloadImageByVersion(WebDriver driver, String linkRoot, String baseFolder, int index,String limit) {
        try {
            String nameProduct = getNameProduct(driver,"h1");
//        // Phát hiện lỗi -> tạm dừng chương trình, đợi người xử lý
            if(nameProduct == null) {
                boolean shouldContinue = showProblemDialog();
                if (!shouldContinue) {
                    System.out.println("Người dùng chọn hủy. Kết thúc.");
                    return "False";
                }
            }
            List<String> urlImage = null;
            Thread.sleep(3000);
            List<WebElement> elementsVersion = driver.findElements(By.cssSelector("li.dimension-value-list-item-square-image"));
            System.out.println("Số phần tử: " + elementsVersion.size());
            if (elementsVersion.size() <= 0) {
                elementsVersion = driver.findElements(By.cssSelector("ul.a-unordered-list li span.a-list-item div.tooltip span.a-declarative span.a-button span.a-button-inner button.a-button-text"));
            }
            if (elementsVersion.size() <= 0) {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Chờ tối đa 10 giây
                wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("div#altImages ul.a-unordered-list li.a-spacing-small[data-csa-c-action='image-block-alt-image-hover'] span.a-list-item span.a-button.a-button-thumbnail span.a-button-inner input.a-button-input")
                ));
                List<WebElement> listImageHover = driver.findElements(By.cssSelector("div#altImages ul.a-unordered-list li.a-spacing-small[data-csa-c-action='image-block-alt-image-hover'] span.a-list-item span.a-button.a-button-thumbnail span.a-button-inner input.a-button-input"));
                if(listImageHover != null) {
                    for (WebElement imgHover :  listImageHover) {
                        imgHover.click();
                        Thread.sleep(3000);
                    }
                }
                String updatedHtml = driver.getPageSource();
                Document updatedDoc = Jsoup.parse(updatedHtml);
                Elements thumbnails = updatedDoc.select("li.image span.a-list-item span.a-declarative div.imgTagWrapper img.a-dynamic-image");
                if (thumbnails == null) {
                    thumbnails = updatedDoc.select("img#landingImage");
                }
                for (String url: getListImageComponent(driver,thumbnails,"[A-Z]\\d+_","")) {
                    urlImage.add(url);
                }

                if(urlImage.size() <= 0) {
                    return "False";
                }
                int imgIndex = 1;
                String nameColor = getNameProduct(driver,"div#inline-twister-dim-title-color_name div.inline-twister-dim-title-value-truncate-expanded span.a-size-base.a-color-base");
                String folderName = baseFolder + File.separator + index + "_" + nameProduct+File.separator;
                Files.createDirectories(Paths.get(folderName));
                for (String link: urlImage) {
                    String ext = getFileExtension(link);

                    String outputPath = getOutPut(folderName,index,imgIndex,ext);
                    downloadFile(link,outputPath);
                    imgIndex++;
                }
            } else {
                for (int i = 0; i < elementsVersion.size(); i++) {
                    urlImage = new ArrayList<>();
                    WebElement versionHover = elementsVersion.get(i);
                    versionHover.click();
                    System.out.println("Đã click phần tử thứ " + (i + 1));
                    Thread.sleep(3000);
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Chờ tối đa 10 giây
                    wait.until(ExpectedConditions.presenceOfElementLocated(
                            By.cssSelector("div#altImages ul.a-unordered-list li.a-spacing-small[data-csa-c-action='image-block-alt-image-hover'] span.a-list-item span.a-button.a-button-thumbnail span.a-button-inner input.a-button-input")
                    ));
                    List<WebElement> listImageHover = driver.findElements(By.cssSelector("div#altImages ul.a-unordered-list li.a-spacing-small[data-csa-c-action='image-block-alt-image-hover'] span.a-list-item span.a-button.a-button-thumbnail span.a-button-inner input.a-button-input"));
                    if(listImageHover != null) {
                        for (WebElement imgHover :  listImageHover) {
                            imgHover.click();
                            Thread.sleep(3000);
                        }
                    }
                    String updatedHtml = driver.getPageSource();
                    Document updatedDoc = Jsoup.parse(updatedHtml);
                    Elements thumbnails = updatedDoc.select("li.image span.a-list-item span.a-declarative div.imgTagWrapper img.a-dynamic-image");
                    if (thumbnails == null) {
                        thumbnails = updatedDoc.select("img#landingImage");
                    }
                    for (String url: getListImageComponent(driver,thumbnails,"[A-Z]\\d+_","")) {
                        urlImage.add(url);
                    }

                    if(urlImage.size() <= 0) {
                        return "False";
                    }
                    int imgIndex = 1;
                    String folderName = baseFolder + File.separator + index + "_" + nameProduct+File.separator+"Version_"+(i + 1);
                    Files.createDirectories(Paths.get(folderName));
                    int startLink = 0;
                    for (String link: urlImage) {
                        String ext = getFileExtension(link);
                        if (!limit.equals("")) {
                            System.out.println("limit: "+limit);
                            if (startLink >= Integer.parseInt(limit)) {
                                System.out.println("bi vao vung breack");
                                break;
                            }
                        }
                        startLink++;
                        String outputPath = getOutPut(folderName,index,imgIndex,ext);
                        downloadFile(link,outputPath);
                        imgIndex++;
                    }
                }
            }
            return "True";
        } catch (Exception e) {
            return "Fall";
        }
    }

    //kiem tra dieu kien link
    // Hàm kiểm tra xem trang có chứa từ "bought"
    public static boolean checkIfBought(WebDriver driver) {
        try {
            // Sử dụng WebDriverWait để chờ phần tử tải
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Chờ tối đa 10 giây
            WebElement boughtElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("span#social-proofing-faceout-title-tk_bought span.a-text-bold")
            ));

            // Lấy văn bản trong phần tử này và chuyển sang chữ thường để kiểm tra
            String elementText = boughtElement.getText().toLowerCase();
            System.out.println(elementText);
            // Kiểm tra xem văn bản có chứa từ "bought" không
            if (elementText.contains("bought")) {
                return true;
            }
        } catch (Exception e) {
            // Nếu không tìm thấy phần tử hoặc gặp lỗi khác, trả về false
            System.out.println("Error or element not found: " + e.getMessage());
        }
        return false;  // Nếu không tìm thấy hoặc không có "bought", trả về false
    }

    // Hàm kiểm tra nếu sản phẩm được bán bởi Amazon
    public static boolean checkSoldByAmazon(WebDriver driver) {
        try {
            // Tìm tất cả các phần tử có chứa "Sold by"
            List<WebElement> soldByElements = driver.findElements(By.xpath("//span[contains(text(),'Sold by')]"));
            // Duyệt qua từng phần tử chứa "Sold by"
            for (WebElement soldByElement : soldByElements) {
                // Tìm phần tử sau "Sold by" chứa "Amazon.com"
                WebElement amazonElement = soldByElement.findElement(By.xpath("following::div[contains(@class,'offer-display-feature-text')]//span[contains(text(), 'Amazon.com')]"));
                // Kiểm tra nếu phần tử chứa "Amazon.com"
                if (amazonElement != null && amazonElement.getText().contains("Amazon.com")) {
                    return true; // Nếu tìm thấy "Amazon.com", trả về true
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false; // Nếu không tìm thấy "Amazon.com", trả về false
    }

    public static boolean checkVisit(WebDriver driver) {
        // Tìm phần tử theo XPath và lấy text
        try {
            // Truyền vào XPath của phần tử mà bạn muốn lấy text
            WebElement bylineElement = driver.findElement(By.cssSelector("a#bylineInfo"));

            String check = bylineElement.getText();
            System.out.println(check);
            if(check.trim().contains("Visit the")) {
                return false;
            }else {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Element not found.");
            return true;
        }
    }

    // Hàm kiểm tra điều kiện dựa trên ngày và rank
    public static boolean checkRankAndDateCondition(String formattedDate, int minRank) {
        try {
            // Lấy ngày hiện tại
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
            Date currentDate = new Date();
            Date productDate = sdf.parse(formattedDate); // Ngày phát hành sản phẩm
            // Tính số ngày giữa ngày hiện tại và ngày phát hành sản phẩm
            long diffInMillis = currentDate.getTime() - productDate.getTime();
            long diffInDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
            // Kiểm tra nếu ngày phát hành trong vòng 1 năm
            if (diffInDays <= 365 && minRank < 60000) {
                conditionType = "<60k";
                // Điều kiện 1: SP phát hành trong vòng 1 năm và rank dưới 60k
                System.out.println("Condition 1: Product released within 1 year and rank below 60k.");
                return true;
            }
            // Kiểm tra nếu ngày phát hành đã qua 1 năm và rank dưới 20k
            if (diffInDays > 365 && minRank < 20000) {
                conditionType = "<20k";
                // Điều kiện 2: SP phát hành hơn 1 năm trước và rank dưới 20k
                System.out.println("Condition 2: Product released more than 1 year ago and rank below 20k.");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        // Trả về false nếu không thỏa mãn cả hai điều kiện
        return false;
    }
    //kiem tra dieu kien link


    public static String extractCode(String input) {
        // Regex: tìm phần _ + từ 6-15 ký tự là chữ in hoa và số + _title
        String regex = "_([A-Z0-9]+)_title";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return extractCodeFromFormattedString("_" + matcher.group(1) + "_title");
        }
        return ""; // Trả về chuỗi rỗng nếu không khớp
    }

    public static String extractCodeFromFormattedString(String input) {
        // Regex mềm hơn: match mọi cụm _<ký tự in hoa + số bất kỳ độ dài>_title
        String regex = "_([A-Z0-9]+)_title";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return matcher.group(1); // Trả về phần giữa: MÃ
        }
        return "";
    }

    public void clickColorV1(WebDriver driver, String colorCheck) {
        try {
            // Tìm phần tử button chứa img có alt tương ứng
            WebElement buttonElement = driver.findElement(By.cssSelector(colorCheck));

            // Click vào button chứa img có alt khớp với màu
            buttonElement.click();
            System.out.println("Clicked on color: " + colorCheck);

        } catch (Exception e) {
            System.out.println("Error clicking color: " + e.getMessage());
        }
    }

    public void clickColorV2(WebDriver driver, String colorCheck) {
        try {
            // Tìm phần tử button chứa img có alt tương ứng
            WebElement buttonElement = driver.findElement(By.cssSelector(colorCheck));

            // Click vào button chứa img có alt khớp với màu
            buttonElement.click();
            System.out.println("Clicked on color: " + colorCheck);

        } catch (Exception e) {
            System.out.println("Error clicking color: " + e.getMessage());
        }
    }
}
