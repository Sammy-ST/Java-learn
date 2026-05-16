package Interface_Integrated_Demo;

public class ClassDataInterImpl1 implements ClassDataInter{

    private Student[] allStudents;

    public ClassDataInterImpl1(Student[] allStudents){
        this.allStudents=allStudents;
    }
    @Override
    public void printAll(){
        System.out.println("====基础方案-全班学生信息====");
        for(Student s:allStudents){
            System.out.println("姓名："+s.getName()+"\t|性别："+s.getSex()+"\t|分数："+s.getScore());
        }
    }

    @Override
    public void printAverage(){
        int sum=0;

        for(Student s:allStudents){
            sum+=s.getScore();
        }

        double average=sum*1.0/allStudents.length;
        System.out.println("全班的平均分为："+average);
    }

}
