package Interface_Integrated_Demo;

public class Test {
    public static void main(String[] args){
        Student[] allStudents=new Student[10];
        allStudents[0]=new Student("赵敏","女",100);
        allStudents[1]=new Student("老一","男",52);
        allStudents[2]=new Student("大二","女",89);
        allStudents[3]=new Student("大三","男",77);
        allStudents[4]=new Student("大四","女",82);
        allStudents[5]=new Student("大五","女",85);
        allStudents[6] = new Student("张无忌", "男", 95);
        allStudents[7] = new Student("张三丰", "男", 98);
        allStudents[8] = new Student("小昭", "女", 88);
        allStudents[9] = new Student("周芷若", "女", 90);

        ClassDataInter plan1=new ClassDataInterImpl1(allStudents);
        ClassDataInter plan2=new ClassDataInterImpl2(allStudents);

        System.out.println("=== 方案1：基础版 ===");
        plan1.printAll();
        plan1.printAverage();

        System.out.println("\n=== 方案2：进阶版 ===");
        plan2.printAll();
        plan2.printAverage();
    }
}
