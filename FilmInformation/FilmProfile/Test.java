package FilmProfile;
import java.util.Scanner;
public class Test {
    // 1. 创建电影对象数组，存储6部电影
    // 2. 创建电影操作对象，传入电影数组
    // 3. 调用方法，完成需求
    public static void main(String[] args){
    //1
        Movie[] movies=new Movie[6];
        movies[0] = new Movie(1, "星际穿越", 9.6, "安妮·海瑟薇");
        movies[1] = new Movie(2, "速度与激情8", 9.2, "瑞秋·费尔南多");
        movies[2] = new Movie(3, "夏洛特烦恼", 9.2, "沈腾");
        movies[3] = new Movie(4, "战狼2", 9.2, "吴京");
        movies[4] = new Movie(5, "让子弹飞", 9.2, "姜文");
        movies[5] = new Movie(6, "暗战", 9.2, "王大陆、渣渣辉");

    //2
        MovieOperator mo=new MovieOperator(movies);

        Scanner sc=new Scanner(System.in);

        while(true){
            int a=mo.table(sc);
            switch(a){
                case 1:
                    mo.printAllMovies();
                    break;
                case 2:
                    mo.searchMovieById();
                    break;
                case 3:
                    System.out.println("Bye~");
                    return;
                default:
                    System.out.println("请输入正确的数字！");
                    break;
            }
        }

    }
}
