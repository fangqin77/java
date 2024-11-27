package com.student.view;

import com.student.util.Constant;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomGroupPanel extends JPanel {
    private JLabel lbl1 = new JLabel("小组名：");
    private JLabel lbl2 = new JLabel("学生姓名：");
    private JLabel lbl3 = new JLabel("学生照片：");
    private JLabel lblPic = new JLabel("照片");
    private JLabel lbl4 = new JLabel("小组评分");
    private JTextField txtGroup = new JTextField();
    private JTextField txtStudent = new JTextField();
    private JTextField txtScore = new JTextField();
    private JButton btnChooseGroup = new JButton("随机小组");
    private JButton btnChooseStudent = new JButton("随机学生");
    private JButton btnAbsence = new JButton("缺勤");
    private JButton btnLeave = new JButton("请假");
    private JButton btnScore = new JButton("小组评分");
    private Thread threadGroup = null;   // 随机抽取小组的线程
    private Thread threadStudent = null;   // 随机抽取学生的线程
    private volatile boolean isRunning = false;
    private Random random = new Random();
    private List<String> groupList = new ArrayList<>();
    private List<String[]> currentGroupStudents = new ArrayList<>(); // 当前选中小组的学生列表

    public RandomGroupPanel() {
        this.setBorder(new TitledBorder(new EtchedBorder(), "随机小组点名"));
        this.setLayout(null);
        initComponents();
        loadGroups();
    }

    private void initComponents() {
        // 添加组件到面板
        this.add(lbl1);
        this.add(lbl2);
        this.add(lbl3);
        this.add(txtGroup);
        this.add(txtStudent);
        this.add(lblPic);
        this.add(btnChooseGroup);
        this.add(btnChooseStudent);
        this.add(btnAbsence);
        this.add(btnLeave);
        this.add(lbl4);
        this.add(txtScore);
        this.add(btnScore);

        // 设置组件位置
        lbl1.setBounds(50, 50, 100, 30);
        txtGroup.setBounds(50, 90, 100, 30);
        txtGroup.setEditable(false);
        btnChooseGroup.setBounds(50, 130, 100, 30);

        lbl4.setBounds(50, 190, 100, 30);
        txtScore.setBounds(50, 230, 100, 30);
        btnScore.setBounds(50, 270, 100, 30);

        lbl2.setBounds(220, 50, 100, 30);
        txtStudent.setBounds(220, 90, 130, 30);
        txtStudent.setEditable(false);
        lblPic.setBounds(220, 130, 130, 150);
        btnChooseStudent.setBounds(220, 300, 100, 30);
        btnAbsence.setBounds(220, 340, 60, 30);
        btnLeave.setBounds(290, 340, 60, 30);

        // 添加随机小组事件
        btnChooseGroup.addActionListener(e -> {
            if (groupList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "没有可用的小组", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            if (btnChooseGroup.getText().equals("停")) {
                stopRandomGroup();
                // 当停止随机小组时，加载该小组的学生
                loadStudentsForGroup(txtGroup.getText());
            } else {
                startRandomGroup();
            }
        });

        // 添加随机学生事件
        btnChooseStudent.addActionListener(e -> {
            if (txtGroup.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "请先选择小组", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            if (currentGroupStudents.isEmpty()) {
                JOptionPane.showMessageDialog(this, "当前小组没有学生", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            if (btnChooseStudent.getText().equals("停")) {
                stopRandomStudent();
            } else {
                startRandomStudent();
            }
        });
    }

    private void loadGroups() {
        groupList.clear();
        File classDir = new File(Constant.CLASS_PATH);
        File[] groupFiles = classDir.listFiles((dir, name) -> name.endsWith(".txt"));
        if (groupFiles != null) {
            for (File file : groupFiles) {
                groupList.add(file.getName().replace(".txt", ""));
            }
        }
    }

    private void loadStudentsForGroup(String groupName) {
        currentGroupStudents.clear();
        String groupFilePath = Constant.CLASS_PATH + "\\" + groupName + ".txt";
        File groupFile = new File(groupFilePath);
        
        if (groupFile.exists()) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(groupFile), "GBK"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] studentInfo = line.split(",");
                    if (studentInfo.length >= 2) {
                        currentGroupStudents.add(new String[]{studentInfo[0].trim(), studentInfo[1].trim()});
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "加载学生信息失败", "", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void startRandomGroup() {
        btnChooseGroup.setText("停");
        isRunning = true;
        threadGroup = new Thread(() -> {
            while (isRunning) {
                int index = random.nextInt(groupList.size());
                String groupName = groupList.get(index);
                SwingUtilities.invokeLater(() -> txtGroup.setText(groupName));
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        threadGroup.start();
    }

    private void stopRandomGroup() {
        isRunning = false;
        btnChooseGroup.setText("随机小组");
        if (threadGroup != null) {
            threadGroup.interrupt();
        }
    }

    private void startRandomStudent() {
        btnChooseStudent.setText("停");
        isRunning = true;
        threadStudent = new Thread(() -> {
            while (isRunning) {
                int index = random.nextInt(currentGroupStudents.size());
                String[] student = currentGroupStudents.get(index);
                SwingUtilities.invokeLater(() -> txtStudent.setText(student[1])); // 显示学生姓名
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        threadStudent.start();
    }

    private void stopRandomStudent() {
        isRunning = false;
        btnChooseStudent.setText("随机学生");
        if (threadStudent != null) {
            threadStudent.interrupt();
        }
    }
}
