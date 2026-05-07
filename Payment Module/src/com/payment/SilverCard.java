package com.payment;

public class SilverCard extends Card {

    public SilverCard(String carId, String name, String phone, double money){
        super(carId, name, phone, money);
    }

    @Override
    public void consume(double money){
        if(!ifEnough(money)){
            return;
        }
        System.out.println("您当前消费：" + money);
        System.out.println("优惠后价格"+money*0.9);
        setMoney(getMoney()-money*0.9);
        System.out.println("当前余额：" + getMoney());
        System.out.println();
    }
}
