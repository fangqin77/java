package com.student;

import com.student.view.MainFrame;
import com.student.entity.Student;
import com.student.entity.Group;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                System.out.println(info.getName());
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
//            BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        MainFrame mainFrame = new MainFrame();

        // 创建小组
        List<Student> studentList = new ArrayList<>();
        Group groupA = new Group("A组", studentList);

        // 实例化多个学生
        for (int i = 1; i <= 5; i++) {
            Student student = new Student(String.format("%03d", i), "学生" + i, 80 + i * 2.5, groupA);
            studentList.add(student);
        }

        // 输出学生信息
        for (Student student : studentList) {
            System.out.println("学号: " + student.getStudentId() + ", 姓名: " + student.getName() + ", 成绩: " + student.getScore() + ", 所属小组: " + student.getGroup().getGroupName());
        }
    }
}
