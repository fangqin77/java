package com.student.view;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentListPanel extends JPanel {
    String[] headers = {"学号", "姓名", "小组"};
    String[][] data;
    JTable studentTable;

    public StudentListPanel() {
        this.setBorder(new TitledBorder(new EtchedBorder(), "学生列表"));
        this.setLayout(new BorderLayout());

        data = loadStudents(); // 从文件加载学生信息

        DefaultTableModel tableModel = new DefaultTableModel(data, headers);
        studentTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 不可编辑
            }
        };
        JScrollPane scrollPane = new JScrollPane(studentTable);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private String[][] loadStudents() {
        List<String[]> students = new ArrayList<>();
        String filePath = "E:\\task\\classes\\students.txt"; // 学生信息文件路径

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] studentInfo = line.split(","); // 按逗号分割
                students.add(studentInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "加载学生信息失败", "", JOptionPane.ERROR_MESSAGE);
        }

        return students.toArray(new String[0][0]); // 转换为二维数组
    }
}
