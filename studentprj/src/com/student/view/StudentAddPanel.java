package com.student.view;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class StudentAddPanel extends JPanel {
    public StudentAddPanel(List<String> groupList) {
        this.setLayout(null);
        this.setBorder(new TitledBorder(new EtchedBorder(), "新增学生"));
        
        JLabel lblId = new JLabel("学号：");
        JTextField txtId = new JTextField();
        JLabel lblName = new JLabel("姓名：");
        JTextField txtName = new JTextField();
        JLabel lblGroup = new JLabel("小组：");
        JComboBox<String> groupComboBox = new JComboBox<>(groupList.toArray(new String[0]));
        JButton btnConfirm = new JButton("确认");

        this.add(lblId);
        this.add(txtId);
        this.add(lblName);
        this.add(txtName);
        this.add(lblGroup);
        this.add(groupComboBox);
        this.add(btnConfirm);

        lblId.setBounds(200, 80, 100, 30);
        txtId.setBounds(200, 130, 200, 30);
        lblName.setBounds(200, 180, 100, 30);
        txtName.setBounds(200, 230, 200, 30);
        lblGroup.setBounds(200, 280, 100, 30);
        groupComboBox.setBounds(200, 320, 200, 30);
        btnConfirm.setBounds(200, 370, 100, 30);

        btnConfirm.addActionListener(e -> {
            String studentId = txtId.getText();
            String studentName = txtName.getText();
            String selectedGroup = (String) groupComboBox.getSelectedItem();
            if (studentId.isEmpty() || studentName.isEmpty() || selectedGroup == null) {
                JOptionPane.showMessageDialog(this, "请填写所有信息", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                saveStudentToFile(studentId, studentName, selectedGroup);
                JOptionPane.showMessageDialog(this, "新增学生成功", "", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void saveStudentToFile(String studentId, String studentName, String groupName) {
        String filePath = "E:\\task\\classes\\students.txt"; // 学生信息保存的文件路径
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(studentId + "," + studentName + "," + groupName); // 保存格式：学号,姓名,小组
            writer.newLine(); // 每个学生信息占一行
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "保存学生信息失败", "", JOptionPane.ERROR_MESSAGE);
        }
    }
}
