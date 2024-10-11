package project;
import java.util.Random;

public class ClassManager {
    private group[] groups;

    public ClassManager(group[] groups){
        this.groups = groups;
    }

    //随机抽取小组
    public group randomGroup(){
        if(groups == null||groups.length == 0){
            return null;
        }
        Random random = new Random();
        int index = random.nextInt(groups.length);
        return groups[index];
    }


    //随机抽取一个小组中的学生
    public student randomStudentFromGroup(group luckgroup){//传入要抽取学生的小组
        if(groups == null||groups.length == 0){
            return null;
        }
        Random random = new Random();
        int index = random.nextInt(luckgroup.getStudents().length);
        return luckgroup.getStudents()[index];
    }

    //从全班中抽取一个学生
    public student randomStudentFromClass(){
        if(groups == null||groups.length == 0){
            return null;
        }
        Random random = new Random();
        int Allstudents = 0;
        for(group g : groups){
            Allstudents += g.getStudents().length;
        }
        int randomIndex = random.nextInt(Allstudents);
        int now_Index = 0;
        for(group g : groups){
            if(randomIndex < now_Index + g.getStudents().length){
                return g.getStudents()[randomIndex-now_Index];
            }
            now_Index += g.getStudents().length;
        }
        return null;
    }

    public static void main(String[] args) {
        //创建实例化学生对象
        student s1 = new student(0001,"janice","男");
        student s2 = new student(0002,"mike","男");
        student s3 = new student(0003,"lisa","女");
        student s4 = new student(0004,"charlie","女");
        student s5 = new student(0005,"zoe","男");
        student s6 = new student(0006,"SAAD","男");
        student s7 = new student(0007,"Ada","女");
        System.out.println(s1.show());


        //创建实例化小组对象
        student[] g1students = {s1,s2,s3};
        group g1 = new group("g1",g1students);

        student[] g2students = {s4,s7};
        group g2 = new group("g2",g2students);

        student[] g3students = {s5,s6};
        group g3 = new group("g3",g2students);
        //修改小组名字
        //        g2.setG_name("g2666");

        System.out.println(g1.show());
        //        System.out.println(g2.show());


        //创建并传入小组数组
        ClassManager classManager = new ClassManager(new group[]{g1,g2,g3});

        //随机抽取一个小组
        group luckgroup = classManager.randomGroup();
        if(luckgroup != null){
            System.out.println("随机抽取小组:" + luckgroup.show());
        }else{
            System.out.println("无小组可供抽取");
        }

        //随机抽取一个小组中的学生
        student randomStudentFromGroup = classManager.randomStudentFromClass();
        if(randomStudentFromGroup != null){
            System.out.println("随机抽取小组中的学生:" + randomStudentFromGroup.show());
        }else{
            System.out.println("无学生可抽取");
        }

        //随机从全班中抽取一个学生
        student randomStudentFromClass = classManager.randomStudentFromClass();
        if(randomStudentFromClass != null){
            System.out.println("随机从全班抽取学生" + randomStudentFromClass.show());
        }else{
            System.out.println("没有学生可抽取");
        }

    }
}


