package com.example.combotoolseller.rootImplements;

import com.example.combotoolseller.BaseAll;
import com.example.combotoolseller.rootServices.PrintifyService;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Base64;
import java.util.List;

public class PrintifyInplement extends BaseAll implements PrintifyService {
    @Override
    public String getCreateMockupPrintify(WebDriver driver, String productUrl, String baseFolder, int index, List<String> colors,String urlImage) {
        try {
            try {
                // Thay bằng đường dẫn đến ảnh bạn muốn tải lên
                uploadImageByBase64(urlImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            boolean cloneIntroduce = getClickAction(driver,"pfy-button[data-testid='closeButton'] button[data-testid='button']");
            if (cloneIntroduce == false) {
                System.out.println("Khong thay introduce");
            }
            boolean contionueSite = getClickAction(driver,"button[title='Continue to Site']");
            if (contionueSite == false) {
                System.out.println("Khong thay contionueSite");
            }
            boolean confirmIntroduce = getClickAction(driver,"pfy-button[data-testid='confirmButton'] button[data-testid='button']");
            if (confirmIntroduce == false) {
                System.out.println("Khong thay confirmIntroduce");
            }
//            try{
//                WebElement uploadButton = wait.until(ExpectedConditions.elementToBeClickable(
//                        By.xpath("//pfy-left-bar-button[@data-testid='newUploadOption']")
//                ));
//                uploadButton.click();
//                Thread.sleep(2000);
//            }catch (Exception e) {
//                System.out.println("⚠️ Không tìm thay nut newUploadOption.");
//            }
//
//            try{
//                WebElement exploreButton = wait.until(ExpectedConditions.elementToBeClickable(
//                        By.xpath("//button[@data-testid='addMyDeviceLayerButton']")
//                ));
//                exploreButton.click();
//                System.out.println("✅ Đã click vào 'open'!");
//                Thread.sleep(2000);
//            }catch (Exception e) {
//                System.out.println("⚠️ Không tìm thay nut Explore.");
//            }
//
//            try{
//                Robot robot = new Robot();
//
//                // Copy đường dẫn vào Clipboard
//                StringSelection selection = new StringSelection(urlImage);
//                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
//
//                // Nhấn Ctrl + V để dán đường dẫn
//                robot.keyPress(KeyEvent.VK_CONTROL);
//                robot.keyPress(KeyEvent.VK_V);
//                robot.keyRelease(KeyEvent.VK_V);
//                robot.keyRelease(KeyEvent.VK_CONTROL);
//
//                Thread.sleep(2000);
//
//                // Nhấn ENTER để xác nhận
//                robot.keyPress(KeyEvent.VK_ENTER);
//                robot.keyRelease(KeyEvent.VK_ENTER);
//            }catch (Exception e) {
//                System.out.println("⚠️ Loi robot keo");
//            }


            boolean uploadButton = getClickAction(driver,"pfy-left-bar-button[data-testid='myLibraryOption'] button");
            if (uploadButton == false) {
                System.out.println("Khong thay pfy-left-bar-button[data-testid='myLibraryOption'] button");
            }

            boolean clickImage = getClickAction(driver,"pfd-image-card button.image-wrapper");
            if (clickImage == false) {
                System.out.println("Khong thay pfy-left-bar-button[data-testid='myLibraryOption'] button");
            }
            Thread.sleep(5000);

            boolean uploadButton2 = getClickAction(driver,"pfy-left-bar-button[data-testid='myLibraryOption'] button");
            if (uploadButton2 == false) {
                System.out.println("Khong thay pfy-left-bar-button[data-testid='myLibraryOption'] button");
            }

            boolean clickRemoveImage = getClickAction(driver,"pfd-image-card pfy-button button");
            if (clickRemoveImage == false) {
                System.out.println("Khong thay clickRemoveImage");
            }

            WebElement selectColor = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//pfy-button[@data-testid='openVariantsTable']")
            ));
            selectColor.click();
            Thread.sleep(5000);

            WebElement colorKiwi  = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//td//pfy-checkbox[@data-test-value='Kiwi']//label")
            ));
            colorKiwi.click();
            Thread.sleep(5000);

            WebElement colorWhite = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//td//pfy-checkbox[@data-test-value='White']//label")
            ));
            colorWhite.click();
            Thread.sleep(5000);

            // **Duyệt danh sách màu & chọn checkbox tương ứng**
            for (String color : colors) {
                try {
                    // **Tìm checkbox theo màu**
                    WebElement colorCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//td//pfy-checkbox[@data-test-value='" + color + "']//label")
                    ));

                    // **Click nếu checkbox chưa được chọn**
                    if (!colorCheckbox.isSelected()) {
                        colorCheckbox.click();
                        Thread.sleep(5000);
                        System.out.println("✅ Đã chọn màu: " + color);
                    } else {
                        System.out.println("🔹 Màu " + color + " đã được chọn trước.");
                    }
                } catch (Exception e) {
                    System.out.println("⚠️ Không tìm thấy màu: " + color);
                }
            }

            colorKiwi.click();
            Thread.sleep(5000);

            List<WebElement> buttons = driver.findElements(By.cssSelector(
                    "div.menu div.right-side pfd-header-tabs.header-action-item pfy-button-group.btn-group div.button-group pfy-button.button-group-item button.button div.custom-content"
            ));

            for (WebElement buttonContentDiv : buttons) {
                // Lấy text của div.custom-content
                String text = buttonContentDiv.getText().trim();
                System.out.println("text button: "+text);
                if ("Preview".equals(text)) {
                    // Lấy phần tử cha là button (div.custom-content nằm trong button)
                    WebElement button = buttonContentDiv.findElement(By.xpath("./ancestor::button"));
                    button.click();
                    System.out.println("Clicked on Preview button.");
                    break;
                }
            }
            try {
                WebElement buttonShowmore = driver.findElement(By.cssSelector(("div.preview-content pfd-preview-view-selector.preview-view-selector pfy-button.show-more button.button.ghost.large div.custom-content span.inner")));
                WebElement button = buttonShowmore.findElement(By.xpath("./ancestor::button"));
                button.click();
                System.out.println("Clicked on show more button.");
                Thread.sleep(5000);
            } catch (Exception e) {
                System.out.println("Loi show mỏe ");
            }

            List<WebElement> buttonColor = driver.findElements(By.cssSelector(
                    "pfd-preview-option-switcher.content-section.ng-star-inserted div.option.ng-star-inserted ul.grid li.item.ng-star-inserted pfd-color-chip-item.ng-star-inserted button.chip.ng-star-inserted"
            ));
            int indexColor = 0;
            for (WebElement colorButton: buttonColor) {
                String colorName = colors.get(indexColor);
                indexColor++;
                String newBaseFolder = baseFolder+"/"+colorName;
                int numberImage = 1;
                colorButton.click();
                Thread.sleep(5000);

                List<WebElement> buttonImage = driver.findElements(By.cssSelector(
                        "div.sidebar.ng-star-inserted pfd-preview-sidebar div.preview-content pfd-preview-view-selector.preview-view-selector ul.grid.ink-grid.ng-star-inserted li.item.ink-col-auto.ng-star-inserted button.item-container"
                ));

                int pageMax = buttonImage.size();
                int imgIndex = 1;
                for (WebElement imageButton: buttonImage) {
                    if(numberImage != pageMax) {
                        imageButton.click();
                        WebDriverWait waitImage = new WebDriverWait(driver, Duration.ofSeconds(30));

                        wait.until(new ExpectedCondition<Boolean>() {
                            @Override
                            public Boolean apply(WebDriver webDriver) {
                                WebElement element = webDriver.findElement(By.cssSelector("div.wrap.ng-star-inserted"));
                                String classAttr = element.getAttribute("class");
                                return classAttr == null || !classAttr.contains("loading");
                            }
                        });
                        List<WebElement> imgElements = driver.findElements(By.cssSelector("div.content-wrapper div.content pfd-preview-main.preview div.wrap.ng-star-inserted img.image.ng-star-inserted"));
                        for (WebElement img : imgElements) {
                            String src = img.getAttribute("src");
                            if (src == null || src.isEmpty()) continue;

                            if (src.startsWith("blob:")) {
                                // Lấy base64 từ ảnh blob bằng JS
                                JavascriptExecutor js = (JavascriptExecutor) driver;
                                String base64 = (String) js.executeScript(
                                        "var img = arguments[0];" +
                                                "var canvas = document.createElement('canvas');" +
                                                "canvas.width = img.naturalWidth;" +
                                                "canvas.height = img.naturalHeight;" +
                                                "var ctx = canvas.getContext('2d');" +
                                                "ctx.drawImage(img, 0, 0);" +
                                                "return canvas.toDataURL('image/png').substring(22);", img);

                                byte[] imageBytes = Base64.getDecoder().decode(base64);
                                File folder = new File(newBaseFolder);
                                if (!folder.exists()) {
                                    folder.mkdirs();  // Tạo thư mục cùng các thư mục cha nếu cần
                                }
                                // Lưu file vào thư mục
                                java.nio.file.Path outputPath = Paths.get(newBaseFolder, "image_" + imgIndex + ".png");
                                Files.write(outputPath, imageBytes);
                                imgIndex++;
                                System.out.println("Saved blob image to: " + outputPath.toString());
                            } else {
                                // Nếu src không phải blob URL thì tải trực tiếp bằng HTTP client hoặc selenium get
                                System.out.println("Skipping non-blob URL: " + src);
                            }
                        }
                    }else {
                        imgIndex = 1;
                    }
                    System.out.println("numberImage: "+numberImage);
                    numberImage++;
                }
            }

        } catch (Exception e) {
            System.out.println("⚠️ Không tìm thấy vùng `pfd-drag-and-drop` hoặc lỗi khi thả ảnh.");
        }
        // Chờ một chút để kiểm tra kết quả
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String encodeImageToBase64(String imagePath) throws IOException {
        // Đọc ảnh từ hệ thống và mã hóa thành Base64
        byte[] fileContent = Files.readAllBytes(Paths.get(imagePath));
        return Base64.getEncoder().encodeToString(fileContent);
    }

    public static void uploadImageByBase64(String imagePath) throws IOException {
        String url = "https://api.printify.com/v1/uploads/images.json";

        // Tạo HTTP Client
        HttpClient client = HttpClients.createDefault();

        // Tạo POST request
        HttpPost post = new HttpPost(url);

        // Đảm bảo thêm header cho authorization nếu cần
        String inputFile = "./Input/ToolTaoMockupPrintify/token.txt";
        List<String> urls = Files.readAllLines(Paths.get(inputFile));
//        post.setHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIzN2Q0YmQzMDM1ZmUxMWU5YTgwM2FiN2VlYjNjY2M5NyIsImp0aSI6ImU5YTQ1N2IwM2EyMzNiYjliMjQyNjBmNTc3Y2I1NTJlMDllN2U3ZmM2YzVkMzNlOTA0NTFlY2UyYzQ5MzM3N2FlMjY5NGI2MGMyNzhjNzkyIiwiaWF0IjoxNzUyNjM1MzM0LjA1OTI5MSwibmJmIjoxNzUyNjM1MzM0LjA1OTI5NCwiZXhwIjoxNzg0MTcxMzM0LjA1MjE5Mywic3ViIjoiMjM5MzI3MDciLCJzY29wZXMiOlsic2hvcHMubWFuYWdlIiwic2hvcHMucmVhZCIsImNhdGFsb2cucmVhZCIsIm9yZGVycy5yZWFkIiwib3JkZXJzLndyaXRlIiwicHJvZHVjdHMucmVhZCIsInByb2R1Y3RzLndyaXRlIiwid2ViaG9va3MucmVhZCIsIndlYmhvb2tzLndyaXRlIiwidXBsb2Fkcy5yZWFkIiwidXBsb2Fkcy53cml0ZSIsInByaW50X3Byb3ZpZGVycy5yZWFkIiwidXNlci5pbmZvIl19.JAznKnmsVzI1hwhgz0JE-o4xNK8rxegZsR8uzke1vX0TLMsTHSp0-RDX3wSEU56ZlBLwg_ubj61OS98z6u-skExdxWXT1GHumKYwE0Jy8b_nLYUpL5LAi6ZQqffbPIHWdXiSvZwX-Euo95JpMnhQZYMA4t2nYDEREUUtwZOGef3_HjXBdNQccwavhF2NJ4htKz1obMHflD-iotY1-TOZFBxOxZ2-hblzKYRC43hfpijLj7NkxWKyr4pF0MaPk07otr19lTW4N9SOovyp7d-Jh_FUCn6O-UYmOzPfgCV1ZvfuOO_mGaCF-IrIdPzhJQC73Kh5Uo40wejt_57VVzOFFAPL13jeK1h0uTznA-oy78Dr8aUhJqNwrUVkYRA0nO3pMaSUoia4pBejdKM3Rp-_uaUYdRp9sx0mrQNpbMvNXtEQ4tGzhc6NV-URXRl6FKVWnaU-YfH98cY71_qDvcEU-CmGogdixnbV2hFSlGPkLay2b7hWw2bzI_esRbY4rpd8b8aGytWGsXziE2w31qSe_nQTrpdV_87NCBY6qVUtgEFlFNoKoZqoRHQniDXuLzIBek1s0Acb-2WICqd8iGq581gY6OUBGnEx5IoqYYo3JbJihUxFlVgTnr463oWgvlFRP-ipNghN9QTopwxVVNyjaJht_GkoNbzU7SjpY-UWMhs"); // Thay bằng API key thực tế
        post.setHeader("Authorization", "Bearer "+ urls.get(0)); // Thay bằng API key thực tế

        // Mã hóa ảnh thành Base64
        String base64Image = encodeImageToBase64(imagePath);

        // Cấu trúc body request
        String jsonBody = "{ \"file_name\": \"image.png\", \"contents\": \"" + base64Image + "\" }";

        // Thêm body vào POST request
        StringEntity entity = new StringEntity(jsonBody);
        post.setEntity(entity);

        // Thực thi yêu cầu
        org.apache.http.HttpResponse response = client.execute(post);

        // Kiểm tra phản hồi
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            System.out.println("File uploaded successfully.");
        } else {
            System.out.println("Upload failed. Status code: " + statusCode);
        }
    }


}
