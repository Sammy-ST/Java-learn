import java.util.Scanner;
public class Test {
    public static void main(String[] args){

        Worker wk1=new Worker("小凯",1);
        Worker wk2=new Worker("小吉",5);
        Manager mg1=new Manager("小灿",6);
        Salesman sm1=new Salesman("小李",7);
        Intern it1=new Intern("小王",7);
//===============================================
        Scanner sc=new Scanner(System.in);
        System.out.print("请输入当前月份：");
        int month=sc.nextInt();
        System.out.println();
        System.out.print("请输入"+wk1.getName()+"本月的工作天数：");
        wk1.setWorkDays(sc.nextInt());
        System.out.println();
        System.out.print("请输入"+wk2.getName()+"本月的工作天数：");
        wk2.setWorkDays(sc.nextInt());
        System.out.println();
        System.out.print("请输入"+sm1.getName()+"本月的订单数：");
        sm1.setOrderCnt(sc.nextInt());
        System.out.println();
        System.out.print("请输入"+it1.getName()+"本月的工作天数：");
        it1.setWorkDays(sc.nextInt());
        System.out.println();

        System.out.println("正在打印工资单………………");
        System.out.println("小凯的工资为"+wk1.computeSalary(month,wk1.getWorkDays()));
        System.out.println("小吉的工资为"+wk2.computeSalary(month,wk2.getWorkDays()));
        System.out.println("小灿的工资为"+mg1.computeSalary(month,0));
        System.out.println("小李的工资为"+sm1.computeSalary(month,sm1.getOrderCnt()));
        System.out.println("小王的工资为"+it1.computeSalary(month,it1.getWorkDays()));

    }
}
