package project;
import java.util.Random;
class student {
    private int stu_number;
    private String stu_name;
    private String gender;

    public student(int stu_number,String stu_name,String gender){
        this.stu_number = stu_number;
        this.stu_name = stu_name;
        this.gender = gender;
    }

    public String show(){
        return "student{姓名:'"+stu_name+"',学号:'"+stu_number+"',性别:'"+gender+"'}";
    }
}
