package com.example.combotoolseller.ClearJar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        // Đường dẫn tới file JAR cụ thể của bạn
        String jarFilePath = "./out/artifacts/ComboToolSeller_jar/ComboToolSeller.jar";
        File jarFile = new File(jarFilePath);

        if (!jarFile.exists()) {
            System.out.println("File not found: " + jarFilePath);
            return;
        }

        // Tạo thư mục đầu ra nếu nó chưa tồn tại
        File outputDir = new File("./Output/jarNotNode/");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        // Tạo file JAR đầu ra
            String outputJarPath = "./Output/newfam.jar";
//        String outputJarPath = "./Output/MainTaiAnhKhongMau.jar";

        try {
            // Mở file JAR cũ và đọc
            try (ZipFile zipFile = new ZipFile(jarFile)) {
                // Tạo một output stream để ghi file JAR mới
                try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(outputJarPath))) {
                    Enumeration<? extends ZipEntry> entries = zipFile.entries();
                    while (entries.hasMoreElements()) {
                        ZipEntry entry = entries.nextElement();
                        String entryName = entry.getName();
                        // Nếu file trong META-INF chứa chữ ký, bỏ qua nó
                        if (entryName.startsWith("META-INF/") &&
                                (entryName.endsWith(".SF") || entryName.endsWith(".RSA") || entryName.endsWith(".DSA"))) {
                            System.out.println("Skipping signature file: " + entryName);
                            continue;
                        }
                        // Nếu không phải là file chữ ký, thêm nó vào JAR mới
                        zos.putNextEntry(new ZipEntry(entryName));
                        try (InputStream in = zipFile.getInputStream(entry)) {
                            byte[] buffer = new byte[1024];
                            int len;
                            while ((len = in.read(buffer)) > 0) {
                                zos.write(buffer, 0, len);
                            }
                        }
                        zos.closeEntry();
                    }
                }
            }
            System.out.println("Successfully created JAR without signature: " + outputJarPath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error processing the JAR file.");
        }
    }
}
