package com.student.view;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import com.student.util.Constant;

public class GroupAddPanel extends JPanel {
    public GroupAddPanel() {
        this.setLayout(null);
        this.setBorder(new TitledBorder(new EtchedBorder(), "新增小组"));
        JLabel lblName = new JLabel("小组名称：");
        JTextField txtName = new JTextField();
        JButton btnName = new JButton("确认");
        this.add(lblName);
        this.add(txtName);
        this.add(btnName);
        lblName.setBounds(200, 80, 100, 30);
        txtName.setBounds(200, 130, 200, 30);
        btnName.setBounds(200, 180, 100, 30);

        btnName.addActionListener(e -> {
            String groupName = txtName.getText();
            if (groupName == null || groupName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "请填写小组名称", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                saveGroupToFile(groupName);
                JOptionPane.showMessageDialog(this, "新增小组成功", "", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void saveGroupToFile(String groupName) {
        String filePath = Constant.CLASS_PATH + "\\" + groupName + ".txt"; // 小组信息保存的文件路径
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8))) {
            writer.write(groupName);
            writer.newLine(); // 每个小组名称占一行
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "保存小组信息失败", "", JOptionPane.ERROR_MESSAGE);
        }
    }
}
