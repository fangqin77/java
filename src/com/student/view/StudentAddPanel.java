package com.student.view;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.student.util.Constant;

public class StudentAddPanel extends JPanel {
    public StudentAddPanel() {
        this.setLayout(null);
        this.setBorder(new TitledBorder(new EtchedBorder(), "新增学生"));
        
        JLabel lblId = new JLabel("学号：");
        JTextField txtId = new JTextField();
        JLabel lblName = new JLabel("姓名：");
        JTextField txtName = new JTextField();
        JLabel lblGroup = new JLabel("小组：");
        JComboBox<String> groupComboBox = new JComboBox<>(loadAllGroups());
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

    private String[] loadAllGroups() {
        List<String> groups = new ArrayList<>();
        File classesDir = new File("E:\\task\\classes");
        if (!classesDir.exists() || !classesDir.isDirectory()) {
            JOptionPane.showMessageDialog(this, "班级文件夹不存在", "", JOptionPane.ERROR_MESSAGE);
            return new String[0];
        }

        // 获取所有班级文件夹
        File[] classFolders = classesDir.listFiles(File::isDirectory);
        if (classFolders != null) {
            for (File classFolder : classFolders) {
                // 获取每个班级文件夹下的所有txt文件（小组）
                File[] groupFiles = classFolder.listFiles((dir, name) -> name.endsWith(".txt"));
                if (groupFiles != null) {
                    for (File groupFile : groupFiles) {
                        String groupName = groupFile.getName().replace(".txt", "");
                        groups.add(groupName);
                    }
                }
            }
        }

        return groups.toArray(new String[0]);
    }

    private void saveStudentToFile(String studentId, String studentName, String selectedGroup) {
        String filePath = Constant.CLASS_PATH + "\\" + selectedGroup + ".txt"; // 学生信息保存的文件路径
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) { // 追加模式
            writer.write(studentId + "," + studentName);
            writer.newLine(); // 每个学生信息占一行
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "保存学生信息失败", "", JOptionPane.ERROR_MESSAGE);
        }
    }
}
