package FilmProfile;

import java.util.Scanner;
public class MovieOperator {
    // 存储电影对象的数组
    //      构造方法，接收外部传入的电影数组
    // 需求1：展示系统中全部电影信息（名称、价格）
    //      printAllMovies()
    // 需求2：根据电影编号id查询某部电影的详细信息
    //      searchMovieById()

    private Movie[] movies;

    public MovieOperator(Movie[] movies){
        this.movies=movies;
    }

    // 需求1：展示系统中全部电影信息（名称、价格）
    //      printAllMovies()
    public void printAllMovies(){

        System.out.println("--------全部电影--------");
        for(int i=0;i<movies.length;i++){
            Movie m=movies[i];
            System.out.println("编号：" + m.getId() + "\t| 名称：" + m.getName() + "\t| 价格：" + m.getPrice());
        }
        System.out.println();
    }

    // 需求2：根据电影编号id查询某部电影的详细信息
    //      searchMovieById()
    public void searchMovieById(){
        Scanner sc=new Scanner(System.in);
        System.out.print("请输入你要查询的电影的编号：");
        System.out.println();
        int target_id=sc.nextInt();

        //开始搜索
        for(int i=0;i<movies.length;i++){//静态方法不能直接访问非静态成员变量。
            Movie m=movies[i];
            if(target_id==m.getId()){//【×】m.id    【√】m.getId()
                System.out.println("--------查询到的电影信息如下--------");
                System.out.println("编号：" + m.getId());
                System.out.println("名称：" + m.getName());
                System.out.println("价格：" + m.getPrice());
                System.out.println("主演：" + m.getActor());
                return; // 找到后直接结束方法
            }
        }
        // 循环结束没找到，说明编号不存在
        System.out.println("抱歉，不存在该编号的电影！");
    }

    public int table(Scanner sc){
        System.out.println("--------电影操作系统--------");
        System.out.println("1.展示所有电影");
        System.out.println("2.根据编号查询电影");
        System.out.println("3.退出系统");
        System.out.print("请输入你的选择：");
        int choose=sc.nextInt();
        return choose;
    }

}
