package com.payment;

public class Card {
    private String carId;
    private String name;
    private String phone;
    private double money;

    //存储金额
    public void deposit(double money){
        setMoney(getMoney() + money);
    }
    //消费金额
    public void consume(double money){
        if(!ifEnough(money)){
            return;
        }
        System.out.println("使用普通卡消费：" + money);
        setMoney(getMoney() - money);
        System.out.println("当前余额：" + getMoney());
        System.out.println();
    }

    //余额不足
    public boolean ifEnough(double money){
        if(money>this.money){
            System.out.println("余额不足");
            System.out.println("本次消费失败");
            System.out.println();
            return false;
        }else{
            System.out.println("余额充足");
            return true;
        }
    }
    public Card(){

    }
    public Card(String carId, String name, String phone, double money){
        this.carId = carId;
        this.name = name;
        this.phone = phone;
        this.money = money;
    }

    public String getCarId(){
        return carId;
    }
    public void setCarId(String carId){
        this.carId=carId;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }

    public double getMoney(){
        return money;
    }
    public void setMoney(double money){
        this.money=money;
    }
}

