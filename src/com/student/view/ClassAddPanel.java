package com.student.view;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ClassAddPanel extends JPanel {
    public ClassAddPanel() {
        this.setLayout(null);
        this.setBorder(new TitledBorder(new EtchedBorder(), "新增班级"));
        JLabel lblName = new JLabel("班级名称：");
        JTextField txtName = new JTextField();
        JButton btnName = new JButton("确认");
        this.add(lblName);
        this.add(txtName);
        this.add(btnName);
        lblName.setBounds(200, 80, 100, 30);
        txtName.setBounds(200, 130, 200, 30);
        btnName.setBounds(200, 180, 100, 30);

        btnName.addActionListener(e -> {
            String className = txtName.getText();
            if (className == null || className.isEmpty()) {
                JOptionPane.showMessageDialog(this, "请填写班级名称", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // 创建班级文件夹
                File classDir = new File("E:\\task\\classes\\" + className);
                if (classDir.mkdir()) {
                    JOptionPane.showMessageDialog(this, "新增班级成功", "", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "新增班级失败", "", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void saveClassToFile(String className) {
        String filePath = "E:\\task\\classes\\" + className + ".txt"; // 文件路径
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("班级名称: " + className);
            writer.newLine();
            writer.write("学生信息: "); // 这里可以添加更多学生信息
            // TODO: 这里可以添加学生的相关信息
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "保存班级信息失败", "", JOptionPane.ERROR_MESSAGE);
        }
    }
}
