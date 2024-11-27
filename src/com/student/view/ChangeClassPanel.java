package com.student.view;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.io.File;
import java.util.List;
import com.student.util.Constant;

public class ChangeClassPanel extends JPanel {
    public ChangeClassPanel(MainFrame mainFrame, List<String> classList) {
        this.setLayout(null);
        this.setBorder(new TitledBorder(new EtchedBorder(), "切换班级"));
        
        JLabel lblClass = new JLabel("选择班级：");
        JComboBox<String> classComboBox = new JComboBox<>(classList.toArray(new String[0])); // 从班级列表加载班级
        JButton btnConfirm = new JButton("确认");

        this.add(lblClass);
        this.add(classComboBox);
        this.add(btnConfirm);
        
        lblClass.setBounds(200, 80, 100, 30);
        classComboBox.setBounds(200, 130, 200, 30);
        btnConfirm.setBounds(200, 180, 100, 30);

        btnConfirm.addActionListener(e -> {
            String selectedClass = (String) classComboBox.getSelectedItem();
            if (selectedClass != null) {
                // 更新 Constant.CLASS_PATH
                Constant.CLASS_PATH = "E:\\task\\classes\\" + selectedClass;
                JOptionPane.showMessageDialog(this, "已切换到班级: " + selectedClass, "", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
