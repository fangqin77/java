package dailytest.stu;

public class test {
    public static void main(String[] args) {
        student s1 = new student();
        s1.name = "张三丰";
        s1.age = 19;
        s1.gender = "男";
        s1.score = 97;
        s1.sayhi();

        student s2 = new student();
        s2.name = "张小美";
        s2.age = 19;
        s2.gender = "女";
        s2.score = 94;
        s2.sayhi();
    }
}
