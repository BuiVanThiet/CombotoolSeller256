package com.example.combotoolseller;

import com.example.combotoolseller.rootEntites.AmazonEntity;
import com.example.combotoolseller.rootEntites.EtsyEntity;
import com.example.combotoolseller.rootEntites.TikTokEntity;
import com.example.combotoolseller.rootImplements.*;
import com.example.combotoolseller.rootServices.*;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;

public class BaseAll {
    protected static TikTokService tikTokService = new TikTokImplement();
    protected static DriverService driverService2 = new DriverImplement();
    protected static AmazonService amazonService = new AmazonImplement();
    protected static AfrodomService afrodomService = new AfrodomImplement();
    protected static BogeybrosService bogeybrosService = new BogeybrosImplement();
    protected static DrinkinglookService drinkinglookService = new DrinkinglookImplement();
    protected static EbayService ebayService = new EbayImplement();
    protected static GerberaprintsService gerberaprintsService = new GerberaprintsImplement();
    protected static IdeasmvdService ideasmvdService = new IdeasmvdImplement();
    protected static ItAestheticsService itAestheticsService = new ItAestheticsImplement();
    protected static RedbapboService redbapboService = new RedbapboImpelment();
    protected static SeakoffService seakoffService = new SeakoffImplement();
    protected static SellmerchandiseService sellmerchandiseService = new SellmerchandiseImplement();
    protected static USBurgaService usBurgaService = new USBurgaImplement();
    protected static WebmemesService webmemesService = new WebmemesImplement();
    protected static BreezygolfService breezygolfService = new BreezygolfImplement();
    protected static BriefinsanityService briefinsanityService = new BriefinsanityImplement();
    protected static DisplateService displateService = new DisplateImplement();
    protected static BurgaService burgaService = new BurgaImplement();
    protected static EmbroideryonmaintxService embroideryonmaintxService = new EmbroideryonmaintxImplement();
    protected static HarperandblakeService harperandblakeService = new HarperandblakeImplement();
    protected static LimitlesstogetheronlineService limitlesstogetheronlineService = new LimitlesstogetheronlineImplement();
    protected static PostersbaseService postersbaseService = new PostersbaseImplement();
    protected static PrintlerService printlerService = new PrintlerImplement();
    protected static QualitypatchdesignsService qualitypatchdesignsService = new QualitypatchdesignsImplement();
    protected static VinovogueService vinovogueService = new VinovogueImplement();
    protected static PrintifyService printifyService = new PrintifyInplement();
    protected static EtsyService etsyService = new EtsyImplement();
    protected static AliexpressService aliexpressService = new AliexpressImplement();
    protected static FlexiquorService flexiquorService = new FlexiquorImplement();
    protected static PromemerteesService promemerteesService = new PromemerteesImplement();
    protected static FragolabrandService fragolabrandService = new FragolabrandImplement();
    protected static JimocornaService jimocornaService = new JimocornaImplement();
    protected static ThedeerlyService thedeerlyService = new ThedeerlyImplement();
    protected static LatinosfactoryService latinosfactoryService = new LatinosfactoryImplement();
    protected static GolfgentryService golfgentryService = new GolfgentryImplement();
    protected static TheurbanflairService theurbanflairService = new TheurbanflairImplement();
    protected static RhobackService rhobackService = new RhobackImplement();
    protected static MiashoopService miashoopService = new MiashoopImplement();
    protected static InprintwetrustService inprintwetrustService = new InprintwetrustImplement();
    protected static GetcaselyService getcaselyService = new GetcaselyImplement();
    protected static TodaysuniformService todaysuniformService = new TodaysuniformImplement();
    protected static BogeystarsService bogeystarsService = new BogeystarsImplement();
    protected static HottopicService hottopicService = new HottopicImplement();
    protected static PinsandacesService pinsandacesService = new PinsandacesImplement();

    protected static FiercepulseService fiercepulseService = new FiercepulseImplement();
    protected static GreazyteesService greazyteesService = new GreazyteesImplement();
    protected static BlntsService blntsService = new BlntsImplement();

    protected static SwagteeshirtService swagteeshirtService = new SwagteeshirtImplement();
    protected static BoxlunchService boxlunchService = new BoxlunchImplement();
    protected static OrnamentallyyouService ornamentallyyouService = new OrnamentallyyouImplement();
    protected static GrishkoService grishkoService = new GrishkoImplement();
    protected static WalmartService walmartService = new WalmartImplement();
    protected static PrintervalService printervalService = new PrintervalImplement();

    protected static Set<String> uniqueLinks = new HashSet<>();  // Dùng HashSet để loại bỏ trùng lặp
//    public static String getNameProduct(WebDriver driver, String elementNameProduct) {
//        String nameProduct = null;
//
//        // Phân tích HTML của trang web với Jsoup
//        Document doc = Jsoup.parse(driver.getPageSource());
//
//        // Tìm tất cả các phần tử khớp với selector
//        Elements listCheckNameProduct = doc.select(elementNameProduct);
//
//        // Tìm kiếm tên sản phẩm hợp lệ trong danh sách
//        for (Element nameCheck : listCheckNameProduct) {
//            // Lấy văn bản của phần tử (dùng text() thay vì getText())
//            String check = nameCheck.text().trim();
//
//            // Kiểm tra nếu tên sản phẩm không chứa từ "shipping"
//            if (!check.toLowerCase().contains("shipping")) {
//                nameProduct = check;
//                break;
//            }
//        }
//
//        // Kiểm tra nếu tên sản phẩm hợp lệ
//        if (nameProduct == null || nameProduct.isEmpty()) {
//            return null;
//        }
//        nameProduct = cleanFileName(nameProduct);
//        // Làm sạch tên sản phẩm để sử dụng cho tên file
//        return cleanURL(nameProduct);
//    }
//
//    public static String cleanFileName(String fileName) {
//        // Loại bỏ các ký tự không hợp lệ trong Windows
//        return fileName.replaceAll("[<>:\"/\\|?*]", "_").replaceAll("[\\s]+", "_");  // Thay thế cả khoảng trắng bằng "_"
//    }
//
//    public static String cleanURL(String url) {
//
//        // Thay thế tất cả dấu "_" thành khoảng trắng
//        return url.replace("_", " ");
//    }

    // Hàm đệ quy để lấy tên sản phẩm
    public static String getNameProduct(WebDriver driver, String elementNameProduct) {
        String nameProduct = null;

        // Phân tích HTML của trang web với Jsoup
        Document doc = Jsoup.parse(driver.getPageSource());

        // Tìm tất cả các phần tử khớp với selector
        Elements listCheckNameProduct = doc.select(elementNameProduct);

        // Tìm kiếm tên sản phẩm hợp lệ trong danh sách
        for (Element nameCheck : listCheckNameProduct) {
            // Lấy văn bản của phần tử (dùng text() thay vì getText())
            String check = nameCheck.text().trim();

            // Kiểm tra nếu tên sản phẩm không chứa từ "shipping"
            if (!check.toLowerCase().contains("shipping")) {
                nameProduct = check;
                break;
            }
        }

        // Kiểm tra nếu tên sản phẩm hợp lệ
        if (nameProduct == null || nameProduct.isEmpty()) {
            return null;
        }

        // Làm sạch tên sản phẩm để sử dụng cho tên file
        nameProduct = cleanFileName(nameProduct);

        // Làm sạch URL nếu cần (hoặc nếu bạn muốn thực hiện hành động với URL)
        nameProduct = cleanURL(nameProduct);

        return stripTrailingDotsSpacesForPathSegment(clearExtraSpaces(nameProduct));
    }

    public static String stripTrailingDotsSpacesForPathSegment(String s) {
        if (s == null) return "";
        // xóa hết khoảng trắng + dấu chấm ở cuối
        s = s.replaceAll("[\\s\\.]+$", "");
        return s.isEmpty() ? "untitled" : s;
    }

    // Làm sạch tên sản phẩm để sử dụng cho tên file (thay thế các ký tự không hợp lệ)
    public static String cleanFileName(String fileName) {
        // Loại bỏ các ký tự không hợp lệ trong Windows và thay thế các khoảng trắng thừa thành 1 khoảng trắng duy nhất
        return fileName.replaceAll("[<>:\"/\\|?*]", "_")  // Thay thế các ký tự không hợp lệ
                .replaceAll("\\s+", " ")        // Thay thế tất cả khoảng trắng dư thừa thành 1 dấu cách duy nhất
                .trim();                        // Loại bỏ khoảng trắng ở đầu và cuối chuỗi
    }

    // Làm sạch URL (thay gạch dưới thành khoảng trắng nếu cần)
    public static String cleanURL(String url) {
        // Thay thế dấu "_" thành khoảng trắng (nếu bạn muốn)
        return url.replace("_", " ");
    }

    public static String clearExtraSpaces(String input) {
        if (input == null) {
            return null;  // Kiểm tra nếu input là null
        }

        // Thay thế tất cả các khoảng trắng dư thừa thành 1 khoảng trắng duy nhất và loại bỏ khoảng trắng đầu/cuối
        return input.replaceAll("\\s+", " ").trim();
    }

    public static int getNextIndex(String baseFolder) {
        File folder = new File(baseFolder);
        if (!folder.exists()) {
            folder.mkdirs();
            return 1;
        }
        File[] subFolders = folder.listFiles(File::isDirectory);
        if (subFolders == null || subFolders.length == 0) {
            return 1;
        }
        int maxIndex = 0;
        for (File subFolder : subFolders) {
            String name = subFolder.getName();
            if (name.matches("\\d+_.*")) {
                int index = Integer.parseInt(name.split("_")[0]);
                maxIndex = Math.max(maxIndex, index);
            }
        }
        return maxIndex + 1;
    }

//    public static List<String> getListImageComponent(WebDriver driver, Elements thumbnails, String inputChange, String outPutChange) {
//        List<String> listUrlImage = new ArrayList<>();
//        Set<String> linkOne = new HashSet<>();
//        for (Element img : thumbnails) {
//            String imgUrl = img.attr("src");
//            if (imgUrl == null || imgUrl.isEmpty()) {
//                imgUrl = img.attr("data-src");
//                if (imgUrl == null || imgUrl.isEmpty()) {
//                    imgUrl = img.attr("data-original");
//                }
//                if(imgUrl == null || imgUrl.isEmpty()) {
//                    imgUrl = img.attr("href");
//                }
//            }
//
//            if (imgUrl.isEmpty()) continue;
//
//            if (imgUrl.contains("data")) {
//                imgUrl = img.attr("data-srcset");
//            }
//            // Thêm https nếu thiếu
//            if (imgUrl.startsWith("//")) {
//                imgUrl = "https:" + imgUrl;
//            }
//
//            if (imgUrl.isEmpty()) {
//                System.out.println("Dong nay ko co link anh");
//                continue;
//            }
//            if (inputChange != null || outPutChange != null) {
//                imgUrl = imgUrl.replaceAll(inputChange,outPutChange);
//            }
//            // Kiểm tra trùng lặp trước khi thêm vào danh sách
//            if (!linkOne.contains(imgUrl)) {
//                System.out.println("URL BEN TACH CHUOI: "+imgUrl);
//                listUrlImage.add(imgUrl); // Thêm vào danh sách chỉ khi chưa có trong Set
//                linkOne.add(imgUrl); // Thêm vào Set để theo dõi các URL đã thêm
//            }
//        }
//        return listUrlImage;
//    }

    public static List<String> getListImageComponent(WebDriver driver, Elements thumbnails, String inputChange, String outPutChange) {
        List<String> listUrlImage = new ArrayList<>();
        Set<String> linkOne = new HashSet<>();
        for (Element img : thumbnails) {
            String imgUrl = img.attr("src");
            if (imgUrl == null || imgUrl.isEmpty()) {
                imgUrl = img.attr("data-src");
                if (imgUrl == null || imgUrl.isEmpty()) {
                    imgUrl = img.attr("data-original");
                }
                if(imgUrl == null || imgUrl.isEmpty()) {
                    imgUrl = img.attr("href");
                }
            }

            if (imgUrl.isEmpty()) continue;

            // Kiểm tra nếu imgUrl chứa data-srcset
            if (imgUrl.contains("data")) {
                imgUrl = img.attr("data-srcset");

                // Tách chuỗi imgUrl và lấy phần trước dấu ","
                if (imgUrl != null && imgUrl.contains(",")) {
                    imgUrl = imgUrl.split(",")[0].trim(); // Lấy URL đầu tiên trước dấu ","
                    System.out.println("URL BEN TACH CHUOI chua cat: " + imgUrl);
                }
            }

            // Thêm https nếu thiếu
            if (imgUrl.startsWith("//")) {
                imgUrl = "https:" + imgUrl;
            }

            if (imgUrl.isEmpty()) {
                System.out.println("Dong nay ko co link anh");
                continue;
            }

            if (inputChange != null || outPutChange != null) {
                imgUrl = imgUrl.replaceAll(inputChange, outPutChange);
            }

            // Kiểm tra trùng lặp trước khi thêm vào danh sách
            if (!linkOne.contains(imgUrl)) {
                System.out.println("URL BEN TACH CHUOI: " + imgUrl);
                listUrlImage.add(imgUrl); // Thêm vào danh sách chỉ khi chưa có trong Set
                linkOne.add(imgUrl); // Thêm vào Set để theo dõi các URL đã thêm
            }
        }
        return listUrlImage;
    }


    public static List<String> getListImageComponent(WebDriver driver, List<WebElement> thumbnails, String inputChange, String outPutChange) {
        List<String> listUrlImage = new ArrayList<>();
        Set<String> linkOne = new HashSet<>();

        // Duyệt qua từng WebElement trong List<WebElement>
        for (WebElement img : thumbnails) {
            // Lấy thuộc tính "src" của thẻ img
            String imgUrl = img.getAttribute("src");

            // Kiểm tra các thuộc tính khác nếu "src" không có giá trị
            if (imgUrl == null || imgUrl.isEmpty()) {
                imgUrl = img.getAttribute("data-src");
                if (imgUrl == null || imgUrl.isEmpty()) {
                    imgUrl = img.getAttribute("data-original");
                }
            }

            // Nếu không có URL, bỏ qua phần tử này
            if (imgUrl == null || imgUrl.isEmpty()) {
                continue;
            }

            // Thêm "https://" nếu URL bắt đầu bằng "//"
            if (imgUrl.startsWith("//")) {
                imgUrl = "https:" + imgUrl;
            }

            // Nếu URL vẫn trống sau khi thay đổi, bỏ qua
            if (imgUrl.isEmpty()) {
                System.out.println("No valid image URL found.");
                continue;
            }

            // Thực hiện thay đổi URL nếu có yêu cầu
            if (inputChange != null && outPutChange != null) {
                imgUrl = imgUrl.replaceAll(inputChange, outPutChange);
            }

            System.out.println("Updated Image URL: " + imgUrl);
            linkOne.add(imgUrl);  // Thêm URL vào Set để loại bỏ trùng lặp
        }

        // Thêm tất cả các URL đã thu thập vào danh sách
        listUrlImage.addAll(linkOne);

        return listUrlImage;
    }

    public static List<String> getListLinkProductComponent(Elements thumbnails,String urlWebsite) {
        List<String> listUrlImage = new ArrayList<>();
        for (Element link : thumbnails) {
            String linkURL = link.attr("href");

            if (linkURL.isEmpty() || linkURL.equals("#")) continue;

            System.out.println("_imgUrl: " + linkURL);
            if (!linkURL.trim().contains("http")) {
                listUrlImage.add((urlWebsite+linkURL).trim());
            } else {
                listUrlImage.add((linkURL).trim());
            }
        }
        if (listUrlImage.size() <= 0) {
            return null;
        }
        return listUrlImage;
    }

    public static int countLinksInElements(Elements elements) {
        int linkCount = 0;

        // Duyệt qua tất cả các phần tử trong Elements
        for (Element link : elements) {
            // Kiểm tra xem phần tử có phải là thẻ <a> hay không
            if (link.tagName().equals("a")) {
                linkCount++;  // Tăng số lượng link lên
            }
        }
        return linkCount;  // Trả về số lượng thẻ <a>
    }

    public static boolean getClickAction(WebDriver driver,String elementClick) {
//        System.out.println(elementClick);
//        try {
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//            WebElement modal = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(elementClick)));
//            System.out.println("✅ Modal found in DOM");
//
//            if (modal.isDisplayed()) {
//                System.out.println("✅ Modal is visible");
//                modal.click();
//                Thread.sleep(2500);
//                return true;
//            } else {
//                System.out.println("⚠️ Modal not visible");
//                return false;
//            }
//        } catch (Exception e) {
//            System.out.println("❌ Modal not found or not clickable: " + e.getMessage());
//            return false;
//        }
        System.out.println(elementClick);
        try {
            // Tạo WebDriverWait để chờ phần tử
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Chờ tối đa 10 giây
            WebElement modal = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(elementClick)));
            System.out.println("✅ Modal found in DOM");

            if (modal.isDisplayed()) {
                System.out.println("✅ Modal is visible");
                modal.click();  // Click vào phần tử modal
                Thread.sleep(2500);  // Đợi 2.5 giây
                return true;
            } else {
                System.out.println("⚠️ Modal not visible");
                return false;
            }
        } catch (Exception e) {
            System.out.println("❌ Modal not found or not clickable: " + e.getMessage());
            return false;
        }
    }

    public static void scrollDownByPixels(WebDriver driver, int pixels) {
        try {
            Thread.sleep(5000);
            // Sử dụng JavascriptExecutor để cuộn trang
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, arguments[0]);", pixels);  // Cuộn theo chiều dọc (Y-axis)
            System.out.println("Đã cuộn màn xuống " + pixels + " pixel");
        } catch (Exception e) {
            System.out.println("Lỗi khi cuộn trang: " + e.getMessage());
        }
    }

    public static void scrollToFooter(WebDriver driver) throws InterruptedException {
        // Sử dụng JavascriptExecutor để cuộn thẳng xuống cuối trang
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Cuộn trang đến cuối
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        // Chờ một chút để đảm bảo trang đã tải xong
        Thread.sleep(2000); // Chờ 2 giây để các phần tử cuối trang load xong
        System.out.println("Reached the footer!");
    }

    public static boolean clickNextPage(WebDriver driver, String element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(element)));

            // Cuộn trang tới nút "Next" để tránh bị che khuất
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextButton);
            // Kiểm tra nếu phần tử chứa class 'disabled'
            WebElement parentLi = nextButton.findElement(By.xpath("..")); // Lấy thẻ 'li' bao quanh 'a'

            if (parentLi.getAttribute("class").contains("disabled")) {
                System.out.println("Nút 'Next' bị vô hiệu hóa.");
                return false;  // Trả về false nếu nút 'Next' bị vô hiệu hóa
            }

            // Đảm bảo nút "Next" có thể click
            if (nextButton != null) {
                String currentUrl = driver.getCurrentUrl();
                System.out.println("URL hiện tại: " + currentUrl);
                nextButton.click();
                System.out.println("Đã nhấn nút 'Next' để chuyển sang trang tiếp theo.");
                Thread.sleep(5000);
                return true;
            } else {
                System.out.println("Không thể bấm nút 'Next' hoặc nút 'Next' bị disabled.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi bấm nút 'Next': " + e.getMessage());
            return false;
        }
    }

    public static boolean clickNextPageLast(WebDriver driver, String element) {
        System.out.println("Da vao vung nhan nut");
        try {
            Thread.sleep(2000);
            List<WebElement> buttons = driver.findElements(By.cssSelector(element));
            // Lặp qua các button để tìm nút "Next"
            for (WebElement button : buttons) {
                if (button.getText().toLowerCase().contains("next")) {
                    Thread.sleep(500);  // Tùy chọn, thời gian chờ có thể điều chỉnh
                    // Nhấn vào nút "Next"
                    button.click();
                    System.out.println("Đã nhấn vào nút 'Next'");
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi khi bấm nút 'Next': " + e.getMessage());
            return false;
        }
    }

    public static String getOutPut(String folderName, int index, int imgIndex, String ext) {
        return String.format("%s/%d_%02d.%s", folderName, index, imgIndex, ext);
    }
    public static String getFileExtension(String url) {
        int dotIndex = url.lastIndexOf('.');
        if (dotIndex != -1) {
            return url.substring(dotIndex + 1).split("[?&#]")[0];
        }
        return "png"; // fallback
    }

    public static void convertImagesToPNG(String folderPath) {
        File folder = new File(folderPath);

        // Kiểm tra thư mục có tồn tại không
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Đường dẫn không hợp lệ hoặc không phải thư mục.");
            return;
        }

        // Lấy tất cả các file trong thư mục
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".webp") || name.endsWith(".gif"));

        if (files == null || files.length == 0) {
            System.out.println("Không tìm thấy ảnh trong thư mục.");
            return;
        }

        // Lặp qua tất cả các file ảnh
        for (File file : files) {
            try {
                // Đọc ảnh từ file
                BufferedImage image = ImageIO.read(file);

                // Nếu ảnh không thể đọc, bỏ qua
                if (image == null) {
                    System.out.println("Không thể đọc ảnh: " + file.getName());
                    continue;
                }

                // Tạo đường dẫn mới cho file PNG
                String outputFilePath = file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf('.')) + ".png";
                File outputFile = new File(outputFilePath);

                // Ghi ảnh đã chuyển đổi thành PNG
                ImageIO.write(image, "PNG", outputFile);
                System.out.println("Đã chuyển đổi ảnh: " + file.getName() + " thành " + outputFile.getName());

                // Xóa file gốc không phải .png sau khi chuyển đổi thành công
                if (!file.getName().endsWith(".png")) {
                    if (file.delete()) {
                        System.out.println("Đã xóa file gốc: " + file.getName());
                    } else {
                        System.out.println("Không thể xóa file gốc: " + file.getName());
                    }
                }

            } catch (IOException e) {
                System.out.println("Lỗi khi chuyển đổi ảnh: " + file.getName());
                e.printStackTrace();
            }
        }
    }

    public static void downloadFile(String url, String outputPath) {
        try (InputStream in = Request.get(url).execute().returnContent().asStream();
             FileOutputStream out = new FileOutputStream(outputPath)) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            Thread.sleep(2000);

        } catch (Exception e) {
            try {
                // Tạo đối tượng URL từ chuỗi URL ảnh
                URL url2 = new URL(url);

                // Mở kết nối và nhận dữ liệu từ URL
                InputStream inputStream = url2.openStream();

                // Tạo FileOutputStream để lưu ảnh vào file
                System.out.println("outputPath: "+outputPath);
                FileOutputStream fileOutputStream = new FileOutputStream(outputPath);

                // Đọc byte từ input stream và ghi vào file
                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }

                // Đóng các stream
                fileOutputStream.close();
                inputStream.close();

                System.out.println("Image downloaded successfully!");

            } catch (IOException e2) {
                e.printStackTrace();
                System.out.println("Error downloading image: " + e2.getMessage());
            }
        }

    }

    //doc file excel
    public static List<Map<String, String>> readExcel(String excelFilePath) {
        List<Map<String, String>> dataList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            int startRow = 1; // Bắt đầu từ dòng thứ 2 (index = 1)
            int maxColumns = 0;

            // Xác định số cột tối đa (lấy từ dòng thứ 2 trở đi)
            for (int i = startRow; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    maxColumns = Math.max(maxColumns, row.getLastCellNum());
                }
            }

            // Tạo tên cột giả định: Column 1, Column 2, ...
            List<String> headers = new ArrayList<>();
            for (int i = 0; i < maxColumns; i++) {
                headers.add("Column " + (i + 1));
            }

            // Đọc dữ liệu từ dòng thứ 2 trở đi
            for (int i = startRow; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                Map<String, String> rowData = new LinkedHashMap<>();

                for (int j = 0; j < maxColumns; j++) {
                    String cellValue = "notName";

                    if (row != null) {
                        Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        cellValue = getCellValueAsString(cell).isEmpty() ? "notName" : getCellValueAsString(cell);
                    }
                    if (!cellValue.equals("notName")) {
                        rowData.put(headers.get(j), cellValue);
                    }
                }

                dataList.add(rowData);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList;
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) return "notName";

        DataFormatter formatter = new DataFormatter();
        String value = formatter.formatCellValue(cell).trim();
        return value.isEmpty() ? "notName" : value;
    }

    public static void writeExcelFile(List<List<String>> data, String filePath, List<String> columnNames) {
        Workbook workbook;
        Sheet sheet;

        // Tên sheet theo giờ phút giây ngày tháng năm: dataHHmmssDDMMYYYY
        String sheetName = "data" + new SimpleDateFormat("HHmmssddMMyyyy").format(new Date());

        try {
            Path path = Paths.get(filePath);
            if (!Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }

            File file = new File(filePath);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                workbook = new XSSFWorkbook(fis);
                fis.close();
            } else {
                workbook = new XSSFWorkbook();
            }

            // Tạo sheet mới
            sheet = workbook.createSheet(sheetName);

            // Ghi tên cột ở dòng đầu tiên
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < columnNames.size(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columnNames.get(i));
            }

            // Ghi dữ liệu từ dòng 2 (index = 1)
            int rowIndex = 1;
            for (List<String> rowData : data) {
                Row row = sheet.createRow(rowIndex++);
                for (int i = 0; i < rowData.size(); i++) {
                    row.createCell(i).setCellValue(rowData.get(i));
                }
            }

            // Ghi workbook ra file
            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);
            fos.close();
            workbook.close();

            System.out.println("Excel written successfully with sheet: " + sheetName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //doc file text
    public static List<String> readTextFile(String filePath) {
        List<String> lines = new ArrayList<>();

        try {
            List<String> allLines = Files.readAllLines(Paths.get(filePath));

            for (String line : allLines) {
                String cleaned = line.trim();
                lines.add(cleaned.isEmpty() ? "Trống" : cleaned);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
    //Xoa file text
    public static void clearTextFile(String filePath) {
        try {
            // Tạo một đối tượng FileWriter với tham số true để xóa hết nội dung của file
            FileWriter writer = new FileWriter(filePath);
            writer.close();  // Không cần ghi gì cả, chỉ cần mở file và đóng lại để xóa nội dung
            System.out.println("File đã được xóa nội dung.");
        } catch (IOException e) {
            System.out.println("Lỗi khi xóa nội dung trong file: " + e.getMessage());
        }
    }

    // Hàm ghi từng String vào file text
    public static void writeTextFile(String filePath, String content) {
        try {
            Path path = Paths.get(filePath);

            // Tạo thư mục cha nếu chưa tồn tại
            if (!Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }

            // Ghi nội dung vào file (tạo mới nếu chưa có, hoặc thêm nếu đã có)
            Files.write(path, (content + System.lineSeparator()).getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);

            System.out.println("File written successfully to: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean showProblemDialog() {
        Object[] options = {"OK", "Cancel"};

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setVisible(true);

        // Show dialog và nhận kết quả
        int choice = JOptionPane.showOptionDialog(
                frame,
                "Sản phẩm có vấn đề",
                "Thông báo",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[0]
        );

        frame.dispose();

        // Nếu chọn OK, trả về true. Nếu Cancel hoặc đóng X, trả về false
        return choice == 0; // 0 là OK, nếu là Cancel hoặc đóng cửa sẽ trả về false
    }

    protected static List<String> extractColors(String colorString) {
        List<String> colors = new ArrayList<>();

        // Tách các màu sắc dựa trên dấu phẩy
        String[] colorArray = colorString.split(",");
        for (String color : colorArray) {
            colors.add(color.trim()); // Thêm màu vào list, loại bỏ khoảng trắng
        }

        return colors;
    }

//    lướt

    protected static void scrollToElement(WebDriver driver, WebElement element) {
        try {
            // Cách 1: scroll vào giữa màn hình bằng Javascript
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element
            );

            // Nếu có header dính ở trên thì kéo lên một chút
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -80);");

        } catch (Exception e) {
            System.out.println("❌ Không thể scroll đến element: " + e.getMessage());
        }
    }

    protected static List<List<String>> getWebTool(String link,String nameTool,WebDriver driver,String outputFolder,int index,List<String> listColor,String limit) {
        String resul = "";
        List<List<String>> dataExcel = new ArrayList<>();
        String timestamp = new SimpleDateFormat("HH:mm:ss-dd/MM/yyyy").format(new Date());
        TikTokEntity tikTokEntity = null;
        List<TikTokEntity> tikTokEntities = new ArrayList<>();
        List<AmazonEntity> amazonEntitys = new ArrayList<>();
        List<EtsyEntity> etsyEntities = new ArrayList<>();

        try {
            if(link.toLowerCase().contains("tiktok")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = tikTokService.getDowloadImage(driver,link,outputFolder,index,limit);
                } else if (nameTool.equals("taiAnhTheoLinkSanPhamChonVersion")) {
                    resul = tikTokService.getDowloadImageVersion(driver,link,outputFolder,index,limit);
                }
            } else if (link.toLowerCase().contains("amazon")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamCoMau")) {
                    resul = amazonService.getDowloadImageColor(driver,link,outputFolder,index,listColor,limit);
                } else if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = amazonService.getDowloadImageNotColor(driver,link,outputFolder,index,limit);
                } else if (nameTool.equals("taiAnhTheoLinkSanPhamChonVersion")) {
                    resul = amazonService.getDowloadImageByVersion(driver,link,outputFolder,index,limit);
                }
            } else if (link.toLowerCase().contains("afrodom")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = afrodomService.getDowloadImage(driver,link,outputFolder,index,limit);
                } else if (nameTool.equals("taiAnhTheoLinkTrangSanPham")) {
                    resul = afrodomService.getLinkProductByPage(driver,link,limit);
                }
            } else if (link.toLowerCase().contains("bogeybros")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = bogeybrosService.getDowloadImage(driver,link,outputFolder,index,limit);
                } else if (nameTool.equals("taiAnhTheoLinkTrangSanPham")){
                    resul = bogeybrosService.getLinkProductByPage(driver,link,limit);
                }
            } else if (link.toLowerCase().contains("drinkinglook")) {
                resul = drinkinglookService.getDowloadImage(driver,link,outputFolder,index,limit);
            } else if (link.toLowerCase().contains("ebay")) {
                resul = ebayService.getDowloadImage(driver,link,outputFolder,index,limit);
            } else if (link.toLowerCase().contains("gerberaprints")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = gerberaprintsService.getDowloadImage(driver,link,outputFolder,index,limit);
                } else if (nameTool.equals("taiAnhTheoLinkTrangSanPham")){
                    resul = gerberaprintsService.getLinkProductByPage(driver,link,limit);
                }
            } else if (link.toLowerCase().contains("ideasmvd")) {
                resul = ideasmvdService.getDowloadImage(driver,link,outputFolder,index,limit);
            } else if (link.toLowerCase().contains("it-aesthetics")) {
                resul = itAestheticsService.getDowloadImage(driver,link,outputFolder,index,limit);
            } else if (link.toLowerCase().contains("redbubble")) {
                resul = redbapboService.getDowloadImage(driver,link,outputFolder,index,limit);
            } else if (link.toLowerCase().contains("seakoff")) {
                resul = seakoffService.getDowloadImage(driver,link,outputFolder,index,limit);
            } else if (link.toLowerCase().contains("sellmerchandise")) {
                resul = sellmerchandiseService.getDowloadImage(driver,link,outputFolder,index,limit);
            }
            else if (link.toLowerCase().contains("weebmemes")) {
                resul = webmemesService.getDowloadImage(driver,link,outputFolder,index,limit);
            } else if (link.toLowerCase().contains("breezygolf")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = breezygolfService.getDowloadImage(driver,link,outputFolder,index,limit);
                } else if (nameTool.equals("taiAnhTheoLinkTrangSanPham")){
                    resul = breezygolfService.getLinkProductByPage(driver,link,limit);
                }
            } else if (link.toLowerCase().contains("briefinsanity")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = briefinsanityService.getDowloadImage(driver,link,outputFolder,index,limit);
                } else if (nameTool.equals("taiAnhTheoLinkTrangSanPham")){
                    resul = briefinsanityService.getLinkProductByPage(driver,link,limit);
                }
            } else if (link.toLowerCase().contains("displate")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = displateService.getDowloadImage(driver,link,outputFolder,index,limit);
                } else if (nameTool.equals("taiAnhTheoLinkTrangSanPham")){
                    resul = displateService.getLinkProductByPage(driver,link,limit);
                }
            } else if (link.toLowerCase().contains("burga")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = burgaService.getDowloadImage(driver,link,outputFolder,index,limit);
                } else if (nameTool.equals("taiAnhTheoLinkTrangSanPham")){
                    resul = burgaService.getLinkProductByPage(driver,link,limit);
                }
            } else if (link.toLowerCase().contains("embroideryonmaintx")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = embroideryonmaintxService.getDowloadImage(driver,link,outputFolder,index,limit);
                } else if (nameTool.equals("taiAnhTheoLinkTrangSanPham")){
                    resul = embroideryonmaintxService.getLinkProductByPage(driver,link,limit);
                }
            } else if (link.toLowerCase().contains("harperandblake")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = harperandblakeService.getDowloadImage(driver,link,outputFolder,index,limit);
                } else if (nameTool.equals("taiAnhTheoLinkTrangSanPham")){
                    resul = harperandblakeService.getLinkProductByPage(driver,link,limit);
                }
            } else if (link.toLowerCase().contains("limitlesstogetheronline")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = limitlesstogetheronlineService.getDowloadImage(driver,link,outputFolder,index,limit);
                } else if (nameTool.equals("taiAnhTheoLinkTrangSanPham")){
                    resul = limitlesstogetheronlineService.getLinkProductByPage(driver,link,limit);
                }
            } else if (link.toLowerCase().contains("postersbase")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = postersbaseService.getDowloadImage(driver,link,outputFolder,index,limit);
                } else if (nameTool.equals("taiAnhTheoLinkTrangSanPham")){
                    resul = postersbaseService.getLinkProductByPage(driver,link,limit);
                }
            } else if (link.toLowerCase().contains("printler")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = printlerService.getDowloadImage(driver,link,outputFolder,index,limit);
                } else if (nameTool.equals("taiAnhTheoLinkTrangSanPham")){
                    resul = printlerService.getLinkProductByPage(driver,link,limit);
                }
            } else if (link.toLowerCase().contains("qualitypatchdesigns")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = qualitypatchdesignsService.getDowloadImage(driver,link,outputFolder,index,limit);
                } else if (nameTool.equals("taiAnhTheoLinkTrangSanPham")){
                    resul = qualitypatchdesignsService.getLinkProductByPage(driver,link,limit);
                }
            } else if (link.toLowerCase().contains("vinovogue")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = vinovogueService.getDowloadImage(driver,link,outputFolder,index,limit);
                } else if (nameTool.equals("taiAnhTheoLinkTrangSanPham")){
                    resul = vinovogueService.getLinkProductByPage(driver,link,limit);
                }
            } else if (link.toLowerCase().contains("etsy")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    System.out.println("da vao etsy");
                    resul = etsyService.getDowloadImage(driver,link,outputFolder,index,limit);
                }
            } else if (link.toLowerCase().contains("aliexpress")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = aliexpressService.getDowloadImage(driver,link,outputFolder,index,limit);
                }
            } else if (link.toLowerCase().contains("flexiquor")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = flexiquorService.getDowloadImage(driver,link,outputFolder,index,limit);
                }
            } else if (link.toLowerCase().contains("promemertees")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = promemerteesService.getDowloadImage(driver,link,outputFolder,index,limit);
                }
            } else if (link.toLowerCase().contains("fragolabrand")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = fragolabrandService.getDowloadImage(driver,link,outputFolder,index,limit);
                }
            } else if (link.toLowerCase().contains("jimocorna")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = jimocornaService.getDowloadImage(driver,link,outputFolder,index,limit);
                } else if (nameTool.equals("taiAnhTheoLinkTrangSanPham")){
                    resul = jimocornaService.getLinkProductByPage(driver,link,limit);
                }
            } else if (link.toLowerCase().contains("thedeerly")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = thedeerlyService.getDowloadImage(driver,link,outputFolder,index,limit);
                } else if (nameTool.equals("taiAnhTheoLinkTrangSanPham")){
                    resul = thedeerlyService.getLinkProductByPage(driver,link,limit);
                }
            } else if (link.toLowerCase().contains("latinosfactory")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = latinosfactoryService.getDowloadImage(driver,link,outputFolder,index,limit);
                }
            } else if (link.toLowerCase().contains("golfgentry")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = golfgentryService.getDowloadImage(driver,link,outputFolder,index,limit);
                } else if (nameTool.equals("taiAnhTheoLinkTrangSanPham")){
                    resul = golfgentryService.getLinkProductByPage(driver,link,limit);
                }
            } else if (link.toLowerCase().contains("theurbanflair")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = theurbanflairService.getDowloadImage(driver,link,outputFolder,index,limit);
                } else if (nameTool.equals("taiAnhTheoLinkTrangSanPham")){
                    resul = theurbanflairService.getLinkProductByPage(driver,link,limit);
                }
            } else if (link.toLowerCase().contains("rhoback")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = rhobackService.getDowloadImage(driver,link,outputFolder,index,limit);
                }
            } else if (link.toLowerCase().contains("miashoop")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = miashoopService.getDowloadImage(driver,link,outputFolder,index,limit);
                }
            } else if (link.toLowerCase().contains("inprintwetrust")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = inprintwetrustService.getDowloadImage(driver,link,outputFolder,index,limit);
                } else if (nameTool.equals("taiAnhTheoLinkTrangSanPham")){
                    resul = inprintwetrustService.getLinkProductByPage(driver,link,limit);
                }
            } else if (link.toLowerCase().contains("getcasely")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = getcaselyService.getDowloadImage(driver,link,outputFolder,index,limit);
                }
            } else if (link.toLowerCase().contains("todaysuniform")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = todaysuniformService.getDowloadImage(driver,link,outputFolder,index,limit);
                }
            } else if (link.toLowerCase().contains("bogeystars")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = bogeystarsService.getDowloadImage(driver,link,outputFolder,index,limit);
                }
            } else if (link.toLowerCase().contains("hottopic")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = hottopicService.getDowloadImage(driver,link,outputFolder,index,limit);
                } else if (nameTool.equals("taiAnhTheoLinkTrangSanPham")){
                    resul = hottopicService.getLinkProductByPage(driver,link,limit);
                }
            } else if (link.toLowerCase().contains("pinsandaces")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = pinsandacesService.getDowloadImage(driver,link,outputFolder,index,limit);
                } else if (nameTool.equals("taiAnhTheoLinkTrangSanPham")){
                    resul = pinsandacesService.getLinkProductByPage(driver,link,limit);
                }
            } else if (link.toLowerCase().contains("fiercepulse")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = fiercepulseService.getDowloadImage(driver,link,outputFolder,index,limit);
                } else if (nameTool.equals("taiAnhTheoLinkTrangSanPham")){
                    resul = fiercepulseService.getLinkProductByPage(driver,link,limit);
                }
            } else if (link.toLowerCase().contains("blnts")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = blntsService.getDowloadImage(driver,link,outputFolder,index,limit);
                } else if (nameTool.equals("taiAnhTheoLinkTrangSanPham")){
                    resul = blntsService.getLinkProductByPage(driver,link,limit);
                }
            } else if (link.toLowerCase().contains("greazytees")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = greazyteesService.getDowloadImage(driver,link,outputFolder,index,limit);
                } else if (nameTool.equals("taiAnhTheoLinkTrangSanPham")){
                    resul = greazyteesService.getLinkProductByPage(driver,link,limit);
                }
            } else if (link.toLowerCase().contains("swagteeshirt")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = swagteeshirtService.getDowloadImage(driver,link,outputFolder,index,limit);
                } else if (nameTool.equals("taiAnhTheoLinkTrangSanPham")){
                    resul = swagteeshirtService.getLinkProductByPage(driver,link,limit);
                }
            } else if (link.toLowerCase().contains("boxlunch")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = boxlunchService.getDowloadImage(driver,link,outputFolder,index,limit);
                } else if (nameTool.equals("taiAnhTheoLinkTrangSanPham")){
                    resul = boxlunchService.getLinkProductByPage(driver,link,limit);
                }
            } else if (link.toLowerCase().contains("ornamentallyyou")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = ornamentallyyouService.getDowloadImage(driver,link,outputFolder,index,limit);
                } else if (nameTool.equals("taiAnhTheoLinkTrangSanPham")){
                    resul = ornamentallyyouService.getLinkProductByPage(driver,link,limit);
                }
            } else if (link.toLowerCase().contains("grishko")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = grishkoService.getDowloadImage(driver,link,outputFolder,index,limit);
                } else if (nameTool.equals("taiAnhTheoLinkTrangSanPham")){
                    resul = grishkoService.getLinkProductByPage(driver,link,limit);
                }
            } else if (link.toLowerCase().contains("walmart")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = walmartService.getDowloadImage(driver,link,outputFolder,index,limit);
                }
            } else if (link.toLowerCase().contains("printerval")) {
                if (nameTool.equals("taiAnhTheoLinkSanPhamKhongMau")) {
                    resul = printervalService.getDowloadImage(driver,link,outputFolder,index,limit);
                }
            } else {
                System.out.println("Ko co bo service");
                resul = "False";
            }
        } catch (Exception e) {
            System.out.println("Loi vung chon web: "+ e);
        }

        if(nameTool.equals("taiAnhTheoLinkSanPhamKhongMau") || nameTool.equals("taiAnhTheoLinkSanPhamChonVersion")) {
            dataExcel.add(List.of(link, resul,timestamp));
        } else if (nameTool.equals("taiAnhTheoLinkTrangSanPham")){
            dataExcel.add(List.of(link,resul,timestamp));
        } else if (nameTool.equals("kiemTraSoLuongSanPhamBan")){
            dataExcel.add(List.of(link, tikTokEntity.getTitle(), tikTokEntity.getSold(),tikTokEntity.getNameShop(),tikTokEntity.getItems(),timestamp,tikTokEntity.getCheck()));
        } else if (nameTool.equals("layIdSanPhamTrenTikTok")){
            for (TikTokEntity tikTok: tikTokEntities) {
                dataExcel.add(List.of(link, tikTok.getIdProduct(), tikTok.getNameShop(),timestamp));
            }
        } else if (nameTool.equals("layLinkSanPhamTrenAmazon")){
            for (AmazonEntity amazon: amazonEntitys) {
                dataExcel.add(List.of(amazon.getUrlRoot(), amazon.getUrlProductSuccess(), amazon.getBought(),amazon.getSoldBy(),amazon.getDateFirstAvailable(),amazon.getConditionType(),amazon.getRank(),timestamp));
            }
        } else if (nameTool.equals("layLinkSanPhamTrenTikTok")) {
            for (TikTokEntity tikTok: tikTokEntities) {
                dataExcel.add(List.of(link, tikTok.getUrl(),timestamp));
            }
        } else if (nameTool.equals("checkLiveShop")) {
            for (TikTokEntity tikTok :tikTokEntities) {
                dataExcel.add(List.of(link, tikTok.getNameShop(),tikTok.getQuantityLive(),timestamp));

            }
        } else if (nameTool.equals("layLinkSanPhamTrenEtsyB2")) {
            for (EtsyEntity etsyEntity: etsyEntities) {
                dataExcel.add(List.of(link, etsyEntity.getStatus(),timestamp));
            }
        }
        return dataExcel;
    }

    public static void showMessageDone(String textVoid) {
        // Sử dụng SwingUtilities để đảm bảo thao tác GUI trên thread đúng
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Tạo thông báo chi tiết, hiển thị tất cả mọi thứ
                String message = textVoid + ". Nhấn OK để tiếp tục.";

                // Tạo hộp thoại luôn ở trên các cửa sổ khác
                JOptionPane.showConfirmDialog(
                        null,
                        message,
                        "Thành công",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
    //luot tu tu xuong fotter
    public void scrollToFooterSlowly(WebDriver driver) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            long lastHeight = (long) js.executeScript("return document.body.scrollHeight");
            long currentPosition = 0;
            int step = 300; // số pixel mỗi lần cuộn
            int delay = 500; // thời gian nghỉ giữa các lần (ms)
            System.out.println("Đang lướt");
            while (currentPosition < lastHeight) {
                currentPosition += step;
                js.executeScript("window.scrollTo(0, arguments[0]);", currentPosition);
                Thread.sleep(delay);

                long newHeight = (long) js.executeScript("return document.body.scrollHeight");
                if (newHeight != lastHeight) {
                    lastHeight = newHeight; // trang có thể load thêm nội dung (lazy load)
                } else if (currentPosition >= lastHeight) {
                    break; // đã tới cuối trang
                }
            }

            // Đảm bảo cuộn hẳn xuống footer
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Scrolling interrupted: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error during scrolling: " + e.getMessage());
        }
    }

    public void scrollToElementSlowly(WebDriver driver, String cssSelector) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int step = 400;   // khoảng cách mỗi lần cuộn (pixel)
        int delay = 700;  // thời gian nghỉ mỗi lần (ms)
        int maxScroll = 50; // tối đa 50 lần cuộn để tránh vòng lặp vô tận

        for (int i = 0; i < maxScroll; i++) {
            try {
                WebElement element = driver.findElement(By.cssSelector(cssSelector));

                // Kiểm tra phần tử đã hiện trong viewport chưa
                boolean inViewport = (Boolean) js.executeScript(
                        "var elem = arguments[0];" +
                                "var rect = elem.getBoundingClientRect();" +
                                "return (" +
                                "rect.top >= 0 && rect.top <= window.innerHeight);",
                        element
                );

                if (inViewport) {
                    System.out.println("✅ Đã cuộn đến phần tử: " + cssSelector);
                    try {
                        Thread.sleep(3000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return;
                }
            } catch (NoSuchElementException e) {
                // phần tử chưa xuất hiện, tiếp tục cuộn
            }

            // Cuộn thêm một đoạn
            js.executeScript("window.scrollBy(0, arguments[0]);", step);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Scrolling interrupted");
                return;
            }
        }

        System.out.println("⚠️ Không tìm thấy phần tử sau khi cuộn: " + cssSelector);
    }

}
