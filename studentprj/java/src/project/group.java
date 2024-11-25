package project;

import java.util.Random;

public class group {
    private String g_name;
    private student[] students;

    public group(String g_name,student[] students){
        this.g_name = g_name;
        this.students = students;
    }
    public String getG_name() {
        return g_name;
    }

    //修改小组的名字
    public void setG_name(String g_name) {
        this.g_name = g_name;
    }

    public student[] getStudents() {
        return students;
    }

    //修改小组里的学生
    public void setStudents(student[] students) {
        this.students = students;
    }

    public String show(){
        String result = "Group{名称:'"+g_name+"',学生:[";
        for (int i = 0; i < students.length; i++) {
            result += students[i].show();
            if(i<students.length - 1){
                result += ",";
            }
        }
        result += "]}";
        return result;
    }
}
