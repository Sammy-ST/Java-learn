public class Employee {
    private String name;
    private int birthMonth;
    public final double basicSalary=4000;//基本工资4000

    //计算工资的
    public double computeSalary(int currentMonth,int number){
        double salary=basicSalary;

        //生日月+100
        if (currentMonth==birthMonth) {
            salary += 100;
        }
        return salary;
    }


    //get、set缸
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public int getBirthMonth(){
        return birthMonth;
    }
    public void setBirthMonth(int birthMonth){
        this.birthMonth=birthMonth;
    }

    public Employee(){}
    public Employee(String name,int birthMonth){
        this.name=name;
        this.birthMonth=birthMonth;
    }
}
