package com.student.view;

import com.student.util.Constant;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomStudentPanel extends JPanel {
    private JLabel lbl2 = new JLabel("学生姓名：");
    private JLabel lbl3 = new JLabel("学生照片：");
    private JLabel lblPic = new JLabel("照片");
    private JTextField txtStudent = new JTextField();
    private JButton btnChooseStudent = new JButton("随机学生");
    private JButton btnAbsence = new JButton("缺勤");
    private JButton btnLeave = new JButton("请假");
    private JButton btnAnswer = new JButton("答题");
    private Thread threadStudent = null;   // 随机抽取学生的线程
    private volatile boolean isRunning = false;
    private Random random = new Random();
    private List<String[]> studentList = new ArrayList<>();

    public RandomStudentPanel() {
        this.setBorder(new TitledBorder(new EtchedBorder(), "随机学生点名"));
        this.setLayout(null);
        initComponents();
        loadAllStudents();
    }

    private void initComponents() {
        this.add(lbl2);
        this.add(lbl3);
        this.add(txtStudent);
        this.add(lblPic);
        this.add(btnChooseStudent);
        this.add(btnAbsence);
        this.add(btnLeave);
        this.add(btnAnswer);

        lbl2.setBounds(160, 50, 100, 30);
        txtStudent.setBounds(160, 90, 130, 30);
        txtStudent.setEditable(false);
        lblPic.setBounds(160, 130, 130, 150);
        btnChooseStudent.setBounds(160, 300, 130, 30);
        btnAbsence.setBounds(160, 340, 60, 30);
        btnLeave.setBounds(230, 340, 60, 30);
        btnAnswer.setBounds(300, 340, 60, 30);

        btnChooseStudent.addActionListener(e -> {
            if (studentList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "没有可用的学生", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            if (btnChooseStudent.getText().equals("停")) {
                stopRandomStudent();
            } else {
                startRandomStudent();
            }
        });

        // 添加缺勤按钮事件
        btnAbsence.addActionListener(e -> {
            if (txtStudent.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "请先随机选择学生", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "已记录缺勤", "", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // 添加请假按钮事件
        btnLeave.addActionListener(e -> {
            if (txtStudent.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "请先随机选择学生", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "已记录请假", "", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // 添加答题按钮事件
        btnAnswer.addActionListener(e -> {
            if (txtStudent.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "请先随机选择学生", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "已记录答题", "", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void loadAllStudents() {
        studentList.clear();
        File classesDir = new File("E:\\task\\classes");
        
        // 获取所有班级文件夹
        File[] classFolders = classesDir.listFiles(File::isDirectory);
        if (classFolders != null) {
            for (File classFolder : classFolders) {
                // 获取每个班级文件夹下的所有txt文件（小组）
                File[] groupFiles = classFolder.listFiles((dir, name) -> name.endsWith(".txt"));
                if (groupFiles != null) {
                    for (File groupFile : groupFiles) {
                        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(groupFile), "GBK"))) {
                            String line;
                            while ((line = br.readLine()) != null) {
                                String[] studentInfo = line.split(",");
                                if (studentInfo.length >= 2) {
                                    studentList.add(new String[]{studentInfo[0].trim(), studentInfo[1].trim()});
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private void startRandomStudent() {
        btnChooseStudent.setText("停");
        isRunning = true;
        threadStudent = new Thread(() -> {
            while (isRunning) {
                int index = random.nextInt(studentList.size());
                String[] student = studentList.get(index);
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
