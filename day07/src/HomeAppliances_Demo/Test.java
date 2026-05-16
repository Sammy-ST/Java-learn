package HomeAppliances_Demo;

import java.util.Scanner;

public class Test {
    public static void main(String[] agrs){
        JD[] jds = new JD[4];
        jds[0] = new TV("小米电视", true);      // 初始：开
        jds[1] = new WashMachine("美的洗衣机", false); // 初始：关
        jds[2] = new Lamp("欧普吊灯", true);     // 初始：开
        jds[3] = new AC("格力空调", false);    // 初始：关

        SmartHomeControl smartHomeControl = SmartHomeControl.getInstance();

        Scanner sc=new Scanner(System.in);

        while (true) {
            // 打印所有设备当前状态
            smartHomeControl.printAll(jds);

            // 打印操作菜单
            System.out.println("\n请您选择要控制的设备：");
            System.out.println("1 - 控制电视 | 2 - 控制洗衣机 | 3 - 控制吊灯 | 4 - 控制空调 | 0 - 退出系统");

            // 接收用户输入
            String command = sc.next();

            switch (command) {
                case "1":
                    smartHomeControl.control(jds[0]);
                    break;
                case "2":
                    smartHomeControl.control(jds[1]);
                    break;
                case "3":
                    smartHomeControl.control(jds[2]);
                    break;
                case "4":
                    smartHomeControl.control(jds[3]);
                    break;
                case "0":
                    System.out.println("系统已退出，再见！");
                    sc.close();
                    return; // 结束程序
                default:
                    System.out.println("输入有误，请重新输入！");
            }

            // 分隔线，优化交互体验
            System.out.println("---------------------------------");
        }
    }
}
