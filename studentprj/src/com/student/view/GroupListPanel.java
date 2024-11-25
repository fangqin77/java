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

public class GroupListPanel extends JPanel {
    String[] headers = {"序号", "小组名称"};
    String[][] data;
    JTable groupTable;

    public GroupListPanel() {
        this.setBorder(new TitledBorder(new EtchedBorder(), "小组列表"));
        this.setLayout(new BorderLayout());

        data = loadGroups(); // 从文件加载小组信息

        DefaultTableModel tableModel = new DefaultTableModel(data, headers);
        groupTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 不可编辑
            }
        };
        JScrollPane scrollPane = new JScrollPane(groupTable);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private String[][] loadGroups() {
        List<String[]> groups = new ArrayList<>();
        String filePath = "E:\\task\\classes\\groups.txt"; // 小组信息文件路径

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int index = 1;
            while ((line = br.readLine()) != null) {
                groups.add(new String[]{String.valueOf(index++), line.trim()});
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "加载小组信息失败", "", JOptionPane.ERROR_MESSAGE);
        }

        return groups.toArray(new String[0][0]); // 转换为二维数组
    }
}
