public class Intern extends Employee{
    private int workDays;
    //public final double basicSalary=2000;

    @Override
    public double computeSalary(int currentMonth,int workDays){
        setWorkDays(workDays);
        double salary=getWorkDays()*100;

        //生日月+100
        if (currentMonth==getBirthMonth()) {
            salary += 100;
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

    public Intern(){}
    public Intern(String name,int birthMonth){
        super(name,birthMonth);
        //setWorkDays(workDays);
    }
}
