package com.example.combotoolseller.rootImplements;

import com.example.combotoolseller.rootServices.DriverService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
@Service
public class DriverImplement implements DriverService {
    @Override
    public WebDriver getDriver(boolean checkSearchEngine, boolean checkProfile) {
        //checkSearchEngine == true la chrome
        //checkSearchEngine == fale laf firefox
        //checkProfile == true là không có profile
        //checkProfile == fale là có profile

        String profile = "";
        String userData = "";

        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            // Windows
            userData = System.getProperty("user.home") + (checkSearchEngine == false ? "\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles" : "\\AppData\\Local\\Google\\Chrome\\User Data");
            System.out.println("Đường dẫn userdata Firefox trên Windows: " + userData);
        } else if (os.contains("mac")) {
            // macOS
            userData = System.getProperty("user.home") + (checkSearchEngine == false ? "/Library/Application Support/Firefox/Profiles" : "/Library/Application Support/Google/Chrome");
            System.out.println("Đường dẫn userdata Firefox trên macOS: " + userData);
        } else if (os.contains("nix") || os.contains("nux")) {
            // Linux
            userData = System.getProperty("user.home") + (checkSearchEngine == false ? "/.mozilla/firefox" : "/.config/google-chrome");
            System.out.println("Đường dẫn userdata Firefox trên Linux: " + userData);
        } else {
            System.out.println("Hệ điều hành không xác định!");
        }

        if(checkSearchEngine == false) {
            System.setProperty("webdriver.gecko.driver", "./chrome/geckodriver.exe");  // Đảm bảo đường dẫn chính xác
            // Cấu hình FirefoxOptions
            profile = findDefaultFirefoxProfileName(userData);
            String profilePath = userData + File.separator + profile;
            FirefoxProfile profile2 = new FirefoxProfile(new File(profilePath));
            FirefoxOptions options = new FirefoxOptions();
            if(checkProfile == true) {
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-gpu");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-software-rasterizer");
                options.addArguments("--disable-blink-features=AutomationControlled"); // Lưu ý: một số tùy chọn Chrome không hỗ trợ trên Firefox
            } else {
                System.out.println(profile2);
                options.setProfile(profile2);
                try {
                    Process process = Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
                    process.waitFor();
                } catch (Exception e) {
                    System.out.println("⚠ Không thể đóng Firefox, tiếp tục chạy...");
                }
            }

            WebDriver driver = new FirefoxDriver(options);
            driver.manage().window().maximize();
            return driver;
        }else {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-software-rasterizer");
            options.addArguments("--disable-blink-features=AutomationControlled");
            if(checkProfile == false) {
                profile = readProfileFromFile("./Input/ProfileGG/profile.txt");
                options.addArguments("user-data-dir=" + userData);
                options.addArguments("profile-directory=" + profile);
                try {
                    Process process = Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
                    process.waitFor();
                } catch (Exception e) {
                    System.out.println("⚠ Không thể đóng Chrome, tiếp tục chạy...");
                }
            } else {
                try {
                    Process process = Runtime.getRuntime().exec("taskkill /F /IM chromeDriver.exe");
                    process.waitFor();
                } catch (Exception e) {
                    System.out.println("⚠ Không thể đóng Chrome, tiếp tục chạy...");
                }
            }
            WebDriver driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            return driver;
        }
    }

    @Override
    public WebDriver getDriverCustom(boolean checkSearchEngine, boolean checkProfile, String profile, String userData) {

        //check == true la chrome
        //check == fale laf firefox
        //checkProfile == true là không có profile
        //checkProfile == fale là có profile

        if(checkSearchEngine == false) {
            System.setProperty("webdriver.gecko.driver", "./chrome/geckodriver.exe");  // Đảm bảo đường dẫn chính xác
            // Cấu hình FirefoxOptions
            String profilePath = userData + File.separator + profile;
            FirefoxProfile profile2 = new FirefoxProfile(new File(profilePath));
            FirefoxOptions options = new FirefoxOptions();
            if(checkProfile == true) {
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-gpu");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-software-rasterizer");
                options.addArguments("--disable-blink-features=AutomationControlled"); // Lưu ý: một số tùy chọn Chrome không hỗ trợ trên Firefox
            } else {
                options.setProfile(profile2);
                try {
                    Process process = Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
                    process.waitFor();
                } catch (Exception e) {
                    System.out.println("⚠ Không thể đóng Firefox, tiếp tục chạy...");
                }
            }
            WebDriver driver = new FirefoxDriver(options);
            driver.manage().window().maximize();
            return driver;
        }else {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-software-rasterizer");
            options.addArguments("--disable-blink-features=AutomationControlled");
            if(checkProfile == false) {
                profile = readProfileFromFile("./Input/ProfileGG/profile.txt");
                options.addArguments("user-data-dir=" + userData);
                options.addArguments("profile-directory=" + profile);
                try {
                    Process process = Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
                    process.waitFor();
                } catch (Exception e) {
                    System.out.println("⚠ Không thể đóng Chrome, tiếp tục chạy...");
                }
            } else {
                try {
                    Process process = Runtime.getRuntime().exec("taskkill /F /IM chromeDriver.exe");
                    process.waitFor();
                } catch (Exception e) {
                    System.out.println("⚠ Không thể đóng Chrome, tiếp tục chạy...");
                }
            }
            WebDriver driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            return driver;
        }
    }

    public static String findDefaultFirefoxProfileName(String profilesRootPath) {
        File profilesDir = new File(profilesRootPath);
        if (!profilesDir.exists() || !profilesDir.isDirectory()) {
            System.out.println("Thư mục Profiles Firefox không tồn tại: " + profilesRootPath);
            return null;
        }

        File[] profileDirs = profilesDir.listFiles(File::isDirectory);
        if (profileDirs == null || profileDirs.length == 0) {
            System.out.println("Không tìm thấy thư mục profile nào trong: " + profilesRootPath);
            return null;
        }

        for (File profileDir : profileDirs) {
            String name = profileDir.getName();
            // Kiểm tra tên phải kết thúc chính xác bằng ".default-release"
            if (name.endsWith(".default-release")) {
                System.out.println("Tìm thấy profile chính xác: " + name);
                return name;
            }
        }

        System.out.println("Không tìm thấy profile kết thúc bằng '.default-release', sử dụng profile đầu tiên: " + profileDirs[0].getName());
        return profileDirs[0].getName();
    }
    public static String readProfileFromFile(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                line = line.trim();
                if (!line.isEmpty()) {
                    return line; // Trả về dòng đầu tiên không rỗng
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Không thể đọc file profile.txt: " + e.getMessage());
        }
        return null; // Nếu không có dòng nào hợp lệ
    }

}
