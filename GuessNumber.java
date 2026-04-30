import java.util.Scanner;
import java.util.Random;
public class GuessNumber {
    public static void main(String[] args) {
        table();
        while(true){
            Scanner sc = new Scanner(System.in);
            int choose=sc.nextInt();
            if(choose==2){
                System.out.println("游戏结束，再见~");
                break;
            }
            Random rd=new Random();
            int nb=rd.nextInt(100)+1;
            while (true){
               int a=sc.nextInt();
               judge(nb,a);
               if(nb==a){
                   break;
               }
            }
        }
    }

    public static void table(){
        System.out.println("====猜数字游戏====");
        System.out.println("1.开始游戏");
        System.out.println("2.退出游戏");
        System.out.println("请输入数字：");
    }
    public static void judge(int taget,int a){
        if(a>taget){
            System.out.println("猜大了");
        }else if(a<taget){
            System.out.println("猜小了");
        }else{
            congratulation();
            return;
        }
    }

    public static void congratulation(){
        System.out.println("恭喜你猜对了");
        System.out.println("游戏结束");
    }
}

