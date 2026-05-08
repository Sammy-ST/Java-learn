public class Salesman extends Employee{
    private int orderCnt;

    @Override
    public double computeSalary(int currentMonth,int orderCnt){//oc=orderCnt订单数
        setOrderCnt(orderCnt);
        double salary=basicSalary+getOrderCnt()*120;

        if(currentMonth==getBirthMonth()){
            salary+=100;
        }
        return salary;
    }

    //get、set缸
    public int getOrderCnt(){
        return orderCnt;
    }
    public void setOrderCnt(int orderCnt){
        this.orderCnt=orderCnt;
    }

    public Salesman(){
    }
    public Salesman(String name,int birthMonth){
        super(name,birthMonth);
        //setOrderCnt(orderCnt);
    }
}
