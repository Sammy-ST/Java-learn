import java.util.ArrayList;
import java.util.Scanner;

public class MovieService {

    private static ArrayList<Movie> movies = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    //0.主界面入口
    public void start() {
        while (true) {
            System.out.println("=====电影信息操作系统====");
            System.out.println("1、上架");
            System.out.println("2、下架某个电影");
            System.out.println("3、查询某个电影");
            System.out.println("4、封杀某个明星");
            System.out.println("5、退出");
            System.out.println("请您输入操作命令：");

            String num = sc.next();
            switch (num) {
                case "1":
                    addMovie();
                    break;
                case "2":
                    deleteMovie();
                    break;
                case "3":
                    queryMovie();
                    break;
                case "4":
                    banActor();
                    break;
                case "5":
                    System.out.println("  感谢使用，退出系统！  ");
                    return;
                default:
                    System.out.println("  输入有误，请重新输入！  ");
                    System.out.println();
            }
        }
    }

    //1.上架电影
    private void addMovie() {
        Movie movie = new Movie();
        System.out.println("请您输入电影名称：");
        movie.setName(sc.next());
        System.out.println("请您输入主演：");
        movie.setActor(sc.next());
        System.out.println("请您输入电影价格：");
        movie.setPrice(sc.nextDouble());
        System.out.println("请您输入电影评分：");
        movie.setScore(sc.nextDouble());

        movies.add(movie);
        System.out.println("上架成功！");
    }

    //2.下架电影 —— 你写的方式完全正确！
    private void deleteMovie() {
        System.out.println("请输入要下架的电影名称：");
        String deleteName = sc.next();
        boolean flag = false;

        for (Movie movie : movies) {
            if (movie.getName().equals(deleteName)) {
                movies.remove(movie); // ✅ 删除单个，这样写完全OK！
                flag = true;
                System.out.println("下架成功！");
                break;
            }
        }

        if (!flag) {
            System.out.println("未找到该电影，下架失败！");
        }
    }

    //3.查询电影
    private void queryMovie() {
        System.out.println("请输入要查询的电影名称：");
        String queryName = sc.next();

        for (Movie movie : movies) {
            if (movie.getName().equals(queryName)) {
                System.out.println("电影名称：" + movie.getName());
                System.out.println("主演：" + movie.getActor());
                System.out.println("价格：" + movie.getPrice());
                System.out.println("评分：" + movie.getScore());
                return;
            }
        }
        System.out.println("未找到该电影！");
    }

    //4.封杀明星 —— 必须倒序索引！
    private void banActor() {
        System.out.println("请输入要封杀的明星姓名：");
        String actor = sc.next();

        int cnt = 0;

        // 修复：必须用 movies.size()，不能用写死的SIZE
        for (int i = movies.size() - 1; i >= 0; i--) {
            // 修复：正确获取演员
            if (movies.get(i).getActor().equals(actor)) {
                movies.remove(i);
                cnt++;
            }
        }

        if (cnt > 0) {
            System.out.println("已成功封杀该明星，共删除" + cnt + "部电影！");
        } else {
            System.out.println("未找到该明星主演的电影！");
        }
    }
}