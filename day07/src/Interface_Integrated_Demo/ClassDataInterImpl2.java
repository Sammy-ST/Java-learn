package Interface_Integrated_Demo;

public class ClassDataInterImpl2 implements ClassDataInter{
    private Student[] allStudents;
    public ClassDataInterImpl2(Student[] allStudents){
        this.allStudents=allStudents;
    }

    @Override
    public void printAll(){
        int boy=0;
        for(Student s:allStudents){
            System.out.println("姓名："+s.getName()+"\t|性别："+s.getSex()+"\t|分数："+s.getScore());
            if(s.getSex().equals("男")){
                boy++;
            }
        }
        System.out.println("男生人数："+boy+"\t女生人数："+(allStudents.length-boy));
    }

    @Override
    public void printAverage(){
        int sum=0;
        int max=allStudents[0].getScore();
        int min=allStudents[0].getScore();

        for(Student s:allStudents){
            sum+=s.getScore();
            if(max<s.getScore()){
                max=s.getScore();
            }
            if(min>s.getScore()){
                min=s.getScore();
            }
        }

        double average=(sum-max-min)*1.0/(allStudents.length-2);
        System.out.println("去掉最高分和最低分后的平均分："+average);
    }
}
