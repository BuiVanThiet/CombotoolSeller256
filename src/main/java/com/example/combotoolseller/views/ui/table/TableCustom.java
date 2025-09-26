package com.example.combotoolreject662025.views.ui.table;

//import com.example.combotoolreject662025.ui.scroll.ScrollBarCustomUI;

import com.example.combotoolreject662025.views.ui.scroll.ScrollBarCustomUI;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class TableCustom {

//    public static void apply(JScrollPane scroll, TableType type) {
//        JTable table = (JTable) scroll.getViewport().getComponent(0);
//        table.setSelectionBackground(new Color(50, 194, 45));
//        table.setSelectionForeground(Color.WHITE);
//
//        table.getTableHeader().setReorderingAllowed(false);
//        table.getTableHeader().setDefaultRenderer(new TableHeaderCustomCellRender(table));
//        table.setRowHeight(30);
//        HoverIndex hoverRow = new HoverIndex();
//        TableCellRenderer cellRender;
//        if (type == TableType.DEFAULT) {
//            cellRender = new TableCustomCellRender(hoverRow);
//        } else {
//            cellRender = new TextAreaCellRenderer(hoverRow);
//        }
//        table.setDefaultRenderer(Object.class, cellRender);
//        table.setDefaultRenderer(Boolean.class, new BooleanCellRenderer(hoverRow));
//        table.setShowVerticalLines(true);
//        table.setGridColor(new Color(220, 220, 220));
//        table.setForeground(new Color(51, 51, 51));
//        table.setSelectionForeground(new Color(51, 51, 51));
//        scroll.setBorder(new LineBorder(new Color(220, 220, 220)));
//        JPanel panel = new JPanel() {
//            @Override
//            public void paint(Graphics grphcs) {
//                super.paint(grphcs);
//                grphcs.setColor(new Color(220, 220, 220));
//                grphcs.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
//                grphcs.dispose();
//            }
//        };
//        panel.setBackground(new Color(250, 250, 250));
//        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, panel);
//        scroll.getViewport().setBackground(Color.WHITE);
//        scroll.getVerticalScrollBar().setUI(new ScrollBarCustomUI());
//        scroll.getHorizontalScrollBar().setUI(new ScrollBarCustomUI());
//        table.getTableHeader().setBackground(new Color(250, 250, 250));
//        table.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseExited(MouseEvent e) {
//                hoverRow.setIndex(-1);
//                table.repaint();
//            }
//
//        });
//        table.addMouseMotionListener(new MouseMotionAdapter() {
//            @Override
//            public void mouseMoved(MouseEvent e) {
//                int row = table.rowAtPoint(e.getPoint());
//                if (row != hoverRow.getIndex()) {
//                    hoverRow.setIndex(row);
//                    table.repaint();
//                }
//            }
//
//            @Override
//            public void mouseDragged(MouseEvent e) {
//                int row = table.rowAtPoint(e.getPoint());
//                if (row != hoverRow.getIndex()) {
//                    hoverRow.setIndex(row);
//                    table.repaint();
//                }
//            }
//        });
//    }

    public static void apply(JScrollPane scroll, TableType type) {
        JTable table = (JTable) scroll.getViewport().getComponent(0);

        // Đặt màu nền khi chọn vào cột hoặc dòng
        table.setSelectionBackground(new Color(50, 194, 45));  // Màu nền khi chọn #32c22d (màu xanh lá cây)
        table.setSelectionForeground(Color.WHITE); // Màu chữ khi chọn (màu trắng)

        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setDefaultRenderer(new TableHeaderCustomCellRender(table));
        table.setRowHeight(30);
        HoverIndex hoverRow = new HoverIndex();
        TableCellRenderer cellRender;

        if (type == TableType.DEFAULT) {
            cellRender = new TableCustomCellRender(hoverRow);
        } else {
            cellRender = new TextAreaCellRenderer(hoverRow);
        }

        table.setDefaultRenderer(Object.class, cellRender);
        table.setDefaultRenderer(Boolean.class, new BooleanCellRenderer(hoverRow));
        table.setShowVerticalLines(true);
        table.setGridColor(new Color(220, 220, 220));
        table.setForeground(new Color(51, 51, 51));
        table.setSelectionForeground(Color.WHITE); // Đặt màu chữ khi chọn thành trắng
        scroll.setBorder(new LineBorder(new Color(220, 220, 220)));

        JPanel panel = new JPanel() {
            @Override
            public void paint(Graphics grphcs) {
                super.paint(grphcs);
                grphcs.setColor(new Color(220, 220, 220));
                grphcs.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
                grphcs.dispose();
            }
        };

        panel.setBackground(new Color(250, 250, 250));
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, panel);
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.getVerticalScrollBar().setUI(new ScrollBarCustomUI());
        scroll.getHorizontalScrollBar().setUI(new ScrollBarCustomUI());

        table.getTableHeader().setBackground(new Color(250, 250, 250));

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                hoverRow.setIndex(-1);
                table.repaint();
            }
        });

        table.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row != hoverRow.getIndex()) {
                    hoverRow.setIndex(row);
                    table.repaint();
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row != hoverRow.getIndex()) {
                    hoverRow.setIndex(row);
                    table.repaint();
                }
            }
        });
    }


    public static enum TableType {
        MULTI_LINE, DEFAULT
    }
}
