import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class Buffer_Demo2 {

    public static void main(String[] args) {

        String iPath = "E:\\code-java\\bookLearn\\day11\\QNT.txt";
        String oPath = "E:\\code-java\\bookLearn\\day11\\Catch.txt";

        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            FileInputStream fis = new FileInputStream(iPath);
            InputStreamReader isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);

            List<String> list = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }

            Collections.sort(list, new Comparator<String>() {
                @Override
                public int compare(String a, String b) {
                    return a.charAt(0) - b.charAt(0);
                }
            });

            FileOutputStream fos = new FileOutputStream(oPath);
            OutputStreamWriter osr = new OutputStreamWriter(fos);
            bw = new BufferedWriter(osr);

            for (int i = 0; i < list.size(); i++) {
                String content = list.get(i);
                bw.write(content);
                bw.newLine();
            }
            System.out.println("文件处理完成！");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
