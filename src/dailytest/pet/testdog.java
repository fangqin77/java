package dailytest.pet;

public class testdog {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.brand = "哈士奇";
        dog.name = "小楠";
        dog.age = 3;
        dog.color = "白色";
        System.out.println(dog.name+"是一只"+dog.color+"的"+dog.brand+",今年"+dog.age+"岁了");

        dog.eat();
        dog.sleep();
    }
}
