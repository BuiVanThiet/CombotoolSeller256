package com.example.combotoolseller.rootImplements;

import com.example.combotoolseller.BaseAll;
import com.example.combotoolseller.rootDTO.IpAddRestDTO;
import com.example.combotoolseller.rootEntites.IPLogin;
import com.example.combotoolseller.rootEntites.Ip256ColorGreen;
import com.example.combotoolseller.rootEntites.IpDevice;
import com.example.combotoolseller.rootEntites.TableKey;
import com.example.combotoolseller.rootServices.GetAPIService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.openqa.selenium.json.TypeToken;

import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class GetAPIImplement extends BaseAll implements GetAPIService {
    @Override
    public List<TableKey> getAllKey() {
        try {
            // Tạo một HttpClient với chế độ tự động theo dõi chuyển hướng
            HttpClient client = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.ALWAYS)  // Đảm bảo chuyển hướng luôn được theo dõi
                    .build();

            // Tạo yêu cầu HTTP GET
            String url = "http://oarfish-awake-roughy.ngrok-free.app/256-green-color-api/get-key-v3";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            // Gửi yêu cầu và nhận phản hồi
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Kiểm tra nếu phản hồi thành công
            if (response.statusCode() == 200) {
                String responseBody = response.body();

                // Sử dụng Gson để chuyển đổi JSON thành đối tượng Java
                Gson gson = new Gson();
                Type listType = new TypeToken<List<TableKey>>(){}.getType();
                List<TableKey> tableKeys = gson.fromJson(responseBody, listType);

                // In các đối tượng từ dữ liệu JSON
                for (TableKey key : tableKeys) {
                    System.out.println(key);
                }

                return tableKeys;
            } else {
                System.out.println("Error: Unable to fetch data. Status code: " + response.statusCode());
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Ip256ColorGreen> getAllIpSuccess() {
        try {
            // Tạo một HttpClient với chế độ tự động theo dõi chuyển hướng
            HttpClient client = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.ALWAYS)  // Đảm bảo chuyển hướng luôn được theo dõi
                    .build();

            // Tạo yêu cầu HTTP GET
            String url = "http://oarfish-awake-roughy.ngrok-free.app/256-green-color-api/get-ip-success";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            // Gửi yêu cầu và nhận phản hồi
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Kiểm tra nếu phản hồi thành công
            if (response.statusCode() == 200) {
                String responseBody = response.body();

                // Sử dụng Gson để chuyển đổi JSON thành đối tượng Java
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Ip256ColorGreen>>(){}.getType();
                List<Ip256ColorGreen> ip256ColorGreen = gson.fromJson(responseBody, listType);

                // In các đối tượng từ dữ liệu JSON
                for (Ip256ColorGreen key : ip256ColorGreen) {
                    System.out.println(key);
                }

                return ip256ColorGreen;
            } else {
                System.out.println("Error: Unable to fetch data. Status code: " + response.statusCode());
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<IPLogin> getAllIpLogin() {
        try {
            // Tạo một HttpClient với chế độ tự động theo dõi chuyển hướng
            HttpClient client = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.ALWAYS)  // Đảm bảo chuyển hướng luôn được theo dõi
                    .build();

            // Tạo yêu cầu HTTP GET
            String url = "http://oarfish-awake-roughy.ngrok-free.app/256-green-color-api/get-all-ip-login";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            // Gửi yêu cầu và nhận phản hồi
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Kiểm tra nếu phản hồi thành công
            if (response.statusCode() == 200) {
                String responseBody = response.body();

                // Sử dụng Gson để chuyển đổi JSON thành đối tượng Java
                Gson gson = new Gson();
                Type listType = new TypeToken<List<IPLogin>>(){}.getType();
                List<IPLogin> iPLogins = gson.fromJson(responseBody, listType);

                // In các đối tượng từ dữ liệu JSON
                for (IPLogin key : iPLogins) {
                    System.out.println(key);
                }

                return iPLogins;
            } else {
                System.out.println("Error: Unable to fetch data. Status code: " + response.statusCode());
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<IpDevice> getAllIpDevice() {
        try {
            // Tạo một HttpClient với chế độ tự động theo dõi chuyển hướng
            HttpClient client = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.ALWAYS)  // Đảm bảo chuyển hướng luôn được theo dõi
                    .build();

            // Tạo yêu cầu HTTP GET
            String url = "http://oarfish-awake-roughy.ngrok-free.app/256-green-color-api/get-all-ip-device";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            // Gửi yêu cầu và nhận phản hồi
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Kiểm tra nếu phản hồi thành công
            if (response.statusCode() == 200) {
                String responseBody = response.body();

                // Sử dụng Gson để chuyển đổi JSON thành đối tượng Java
                Gson gson = new Gson();
                Type listType = new TypeToken<List<IpDevice>>(){}.getType();
                List<IpDevice> ipDevices = gson.fromJson(responseBody, listType);

                // In các đối tượng từ dữ liệu JSON
                for (IpDevice key : ipDevices) {
                    System.out.println(key);
                }

                return ipDevices;
            } else {
                System.out.println("Error: Unable to fetch data. Status code: " + response.statusCode());
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void getAddIp() {
        try {
            // Tạo một HttpClient với chế độ tự động theo dõi chuyển hướng
            HttpClient client = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.ALWAYS)  // Đảm bảo chuyển hướng luôn được theo dõi
                    .build();

            // Tạo yêu cầu HTTP GET
            String url = "http://oarfish-awake-roughy.ngrok-free.app/256-green-color-api/add-ip-login";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            // Gửi yêu cầu và nhận phản hồi
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Kiểm tra nếu phản hồi thành công
            if (response.statusCode() == 200) {


            } else {
                System.out.println("Error: Unable to fetch data. Status code: " + response.statusCode());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public IpAddRestDTO getIpAddRest() {
        try {
            // Tạo HttpClient để gửi yêu cầu
            HttpClient client = HttpClient.newHttpClient();

            // URL của dịch vụ geolocation API (ip-api)
            String url = "http://ip-api.com/json/";

            // Gửi yêu cầu GET tới API
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            // Nhận phản hồi từ API
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Nếu yêu cầu thành công (status code 200)
            if (response.statusCode() == 200) {
                // Phân tích dữ liệu JSON trả về
                String responseBody = response.body();
                JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();

                // Lấy thông tin vị trí từ API
                String ip = jsonObject.get("query").getAsString(); // Địa chỉ IP
                String country = jsonObject.get("country").getAsString(); // Quốc gia
                String region = jsonObject.get("regionName").getAsString(); // Tỉnh/thành phố
                String city = jsonObject.get("city").getAsString(); // Thành phố
                String lat = jsonObject.get("lat").getAsString(); // Vĩ độ
                String lon = jsonObject.get("lon").getAsString(); // Kinh độ
                // Lấy địa chỉ IP cục bộ
                InetAddress inetAddress = InetAddress.getLocalHost();
                String localIp = inetAddress.getHostAddress();
                IpAddRestDTO ipAddRestDTO = new IpAddRestDTO(ip,localIp,country,region,city,lat,lon);
                return ipAddRestDTO;
            } else {
                System.out.println("Failed to get IP geolocation data.");
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
