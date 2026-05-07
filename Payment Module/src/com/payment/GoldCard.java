package com.payment;

public class GoldCard extends Card {

    private int coupons=0;
    public int getCoupons(){
        return coupons;
    }
    public void setCoupons(int coupons){
        this.coupons=coupons;
    }

    public GoldCard(String carId, String name, String phone, double money){
        super(carId, name, phone, money);
    }

    @Override
    public void consume(double money){
        if(!ifEnough(money)){
            return;
        }
        System.out.println("您当前消费：" + money);
        System.out.println("优惠后价格：" + (money * 0.8));
        setMoney(getMoney()-money*0.8);
        System.out.println("当前余额：" + getMoney());

        if(money*0.8>=200){
            coupons++;
            System.out.println("您本次消费满200元，本次消费获得一张洗车券");
        }else{
            System.out.println("您本次消费未满200元，本次消费无法获得洗车券");
        }
        System.out.println();
    }

//    public void printTicket(){
//        System.out.println("尊敬的金卡用户，您剩余洗车券有"+getCoupons()+"张。");
//    }
}
