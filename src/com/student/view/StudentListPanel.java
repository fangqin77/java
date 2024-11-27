package com.student.view;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
        String classPath = "E:\\task\\classes"; // 班级文件夹路径

        File classDir = new File(classPath);
        if (!classDir.exists() || !classDir.isDirectory()) {
            JOptionPane.showMessageDialog(this, "班级文件夹不存在", "", JOptionPane.ERROR_MESSAGE);
            return new String[0][0];
        }

        File[] classFolders = classDir.listFiles(File::isDirectory);
        if (classFolders == null) {
            JOptionPane.showMessageDialog(this, "班级文件夹为空", "", JOptionPane.ERROR_MESSAGE);
            return new String[0][0];
        }

        for (File classFolder : classFolders) {
            File[] groupFiles = classFolder.listFiles((dir, name) -> name.endsWith(".txt"));
            if (groupFiles != null) {
                for (File groupFile : groupFiles) {
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(groupFile), "GBK"))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            String[] studentInfo = line.split(","); // 按逗号分割
                            if (studentInfo.length >= 2) {
                                String[] row = new String[3];
                                row[0] = studentInfo[0].trim(); // 学号
                                row[1] = studentInfo[1].trim(); // 姓名
                                row[2] = groupFile.getName().replace(".txt", "").trim(); // 小组
                                students.add(row);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "加载学生信息失败", "", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }

        return students.toArray(new String[0][0]); // 转换为二维数组
    }
}
