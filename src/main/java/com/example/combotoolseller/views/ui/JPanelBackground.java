/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.combotoolseller.views.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author ADMIN
 */
public class JPanelBackground extends JPanel{
    private Image backgroundImage;

    // Constructor để tải ảnh nền
    public JPanelBackground() {
        try {
            // Tải ảnh từ file (đảm bảo đường dẫn đúng)
            backgroundImage = ImageIO.read(new File("./logo256.jpg")); // Cập nhật đường dẫn chính xác
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Override phương thức paintComponent để vẽ ảnh nền
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Vẽ ảnh nền nếu ảnh được tải thành công
        if (backgroundImage != null) {
            // Vẽ hình ảnh nền với kích thước của JPanel
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Image Background Example");
//        JPanelBackground panel = new JPanelBackground();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(800, 600);
//        frame.add(panel);
//        frame.setVisible(true);
//    }
    
}
