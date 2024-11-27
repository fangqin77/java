package com.student.view;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.student.util.Constant;

public class GroupListPanel extends JPanel {
    String[] headers = {"序号", "小组名称"};
    JTable groupTable;

    public GroupListPanel() {
        this.setBorder(new TitledBorder(new EtchedBorder(), "小组列表"));
        this.setLayout(new BorderLayout());

        String[][] data = loadGroups(); // 从文件加载小组信息

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
        File classDir = new File(Constant.CLASS_PATH);
        File[] groupFiles = classDir.listFiles((dir, name) -> name.endsWith(".txt")); // 只获取以 .txt 结尾的文件

        if (groupFiles != null) {
            for (int i = 0; i < groupFiles.length; i++) {
                String groupName = groupFiles[i].getName().replace(".txt", ""); // 去掉文件扩展名
                groups.add(new String[]{String.valueOf(i + 1), groupName});
            }
        }

        return groups.toArray(new String[0][0]); // 转换为二维数组
    }
}
