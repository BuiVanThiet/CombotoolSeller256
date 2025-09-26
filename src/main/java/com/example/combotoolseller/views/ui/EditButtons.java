package com.example.combotoolseller.views.ui;

import javax.swing.*;
import java.awt.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ADMIN
 */
public class EditButtons {
    public void Edit(JButton button) {
        // Đặt màu nền là #32c22d (màu xanh lá cây)
        button.setBackground(new Color(50, 194, 45));  // Màu nền xanh lá cây

        // Đặt kích thước cho nút
        Dimension buttonSize = new Dimension(100, 20);
        button.setPreferredSize(buttonSize);

        // Đặt phông chữ cho nút
        Font f = new Font("Times New Roman", Font.BOLD, 14);
        button.setFont(f);

        // Đặt viền với màu #32c22d và độ dày viền là 3px
//        button.setBorder(BorderFactory.createLineBorder(new Color(50, 194, 45), 3)); // Độ dày viền: 3px
        // Đặt màu chữ là trắng
        button.setForeground(Color.WHITE); // Màu chữ trắng
    }
    
    public void EditNoneBorder(JButton button) {
//        button.setSize(new Dimension(100, 35));
        button.setBackground(new Color(0, 0, 0, 0));
        Dimension buttonSize = new Dimension(100, 20);
        button.setPreferredSize(buttonSize);
        Font f = new Font("Times New Roman", Font.BOLD, 14);
        button.setFont(f);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
        button.setForeground(new Color(51,51,51));
    }
    
    //them vao 8/12
     public void EditAddCreart(JButton button) {
        button.setBackground(new Color(0, 0, 0, 0));
        Dimension buttonSize = new Dimension(160, 28);
        button.setPreferredSize(buttonSize);
        Font f = new Font("Times New Roman", Font.BOLD, 14);
        button.setFont(f);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }
     
     public void EditEmployee_and_client(JButton button) {
        button.setBackground(new Color(0, 0, 0, 0));
        Dimension buttonSize = new Dimension(154, 20);
        button.setPreferredSize(buttonSize);
        Font f = new Font("Times New Roman", Font.BOLD, 14);
        button.setFont(f);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }
}
