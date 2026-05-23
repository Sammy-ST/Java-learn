import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Buffer_Demo {

      public static void main(String[] args){

            String srcPath="E:\\code-java\\bookLearn\\day11\\QNT.txt";  // 读取的文件
            String desPath="E:\\code-java\\bookLearn\\day11\\Catch.txt"; // 写出的文件

            BufferedReader br=null;
            BufferedWriter bw=null;

            try{
                  // 1. 创建字符输入流
                  FileReader fr=new FileReader(srcPath);
                  br=new BufferedReader(fr);

                  // 2. 读取每一行，存到集合
                  List<String> list=new ArrayList<>();
                  String line;

                  // 错误1：readline() → 改成 readLine() 大写L
                  while((line=br.readLine())!=null){
                        list.add(line);
                  }

                  // 3. 排序（无Lambda，最标准写法）
                  Collections.sort(list,new Comparator<String>(){
                        // 错误2：@Overrride → 改成 @Override
                        @Override
                        public int compare(String o1,String o2){
                              // 错误3：02.charAt(0) → 改成 o2.charAt(0)
                              return o1.charAt(0) - o2.charAt(0);
                        }
                  });

                  // 4. 创建字符输出流
                  // 错误4：desParh → 改成 desPath
                  FileWriter fw=new FileWriter(desPath);
                  bw=new BufferedWriter(fw);

                  // 5. 写出数据
                  for (int i = 0; i < list.size(); i++) {
                        String content = list.get(i);
                        bw.write(content);
                        bw.newLine();
                  }

                  System.out.println("文件处理完成！已生成排序后的文件：" + desPath);

            }catch (FileNotFoundException e) {
                  System.out.println("错误：找不到文件，请检查文件路径是否正确！");
                  e.printStackTrace();
            } catch (IOException e) {
                  System.out.println("错误：文件读写失败！");
                  e.printStackTrace();
            } catch (Exception e) {
                  System.out.println("发生未知错误！");
                  e.printStackTrace();
            } finally {
                  // 关流
                  try {
                        if (bw != null) {
                              bw.close();
                        }
                  } catch (IOException e) {
                        e.printStackTrace();
                  }

                  try {
                        if (br != null) {
                              br.close();
                        }
                  } catch (IOException e) {
                        e.printStackTrace();
                  }
            }
      }
}