package com.example.combotoolseller;
import java.io.*;
import java.net.*;
public class MainTest {
    public static void main(String[] args) {
        String imageUrl = "https://cdn.media.amplience.net/s/hottopic/34699984_hi?$productMainDesktop$&fmt=auto"; // URL của ảnh
        String destinationFile = "downloaded_image.jpg"; // Đường dẫn và tên file bạn muốn lưu ảnh

        try {
            // Tạo đối tượng URL từ chuỗi URL ảnh
            URL url = new URL(imageUrl);

            // Mở kết nối và nhận dữ liệu từ URL
            InputStream inputStream = url.openStream();

            // Tạo FileOutputStream để lưu ảnh vào file
            FileOutputStream fileOutputStream = new FileOutputStream(destinationFile);

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

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error downloading image: " + e.getMessage());
        }
    }
}
