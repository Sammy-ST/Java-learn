public class Manager extends Employee{
    public final double managerSalary=4000;
    @Override
    public double computeSalary(int currentMonth,int number) {
        double salary=basicSalary+managerSalary;

        if(currentMonth==getBirthMonth()){
            salary+=100;
        }
        return salary;
    }

    public Manager(){}
    public Manager(String name,int birthMonth){
        setName(name);//调用setName()和setBirthMonth()
        setBirthMonth(birthMonth);
    }
}
