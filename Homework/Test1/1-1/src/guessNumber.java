import java.util.Scanner;
import java.util.Random;

public class guessNumber {

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        Random rd=new Random();

        while (true){
            System.out.println("欢迎来到猜数字游戏");
            int target=rd.nextInt(100)+1;
            int cnt=0;
            while(true){
                System.out.print("请输入你的猜测：");
                int guess=sc.nextInt();
                cnt++;
                if(guess==target){
                    System.out.println("猜对了！一共猜了"+cnt+"次。");
                    break;
                }else if(guess>target){
                    System.out.println("猜大了");
                }else{
                    System.out.println("猜小了");
                }
            }
            endTable();
            int choose=sc.nextInt();
            if(choose==2){
                break;
            }
        }
    }

    public static void endTable(){
        System.out.println("游戏结束");
        System.out.println("1. 重新开始");
        System.out.println("2. 退出");
        System.out.print("请输入你的选择：");
    }
}
