public class Worker extends Employee{
    private int workDays;

    @Override
    public double computeSalary(int currentMonth,int workDays){//wd=workDays工作天数
        setWorkDays(workDays);
        double salary=basicSalary+getWorkDays()*100;

        if(currentMonth==getBirthMonth()){
            salary+=100;
        }

        return salary;
    }

    //get\set
    public int getWorkDays(){
        return workDays;
    }
    public void setWorkDays(int workDays){
        this.workDays=workDays;
    }

    public Worker(){}
    public Worker(String name,int birthMonth){
        super(name,birthMonth);//用super调用父类的构造方法【和Manager的对比】
        //setWorkDays(workDays);
    }
}
