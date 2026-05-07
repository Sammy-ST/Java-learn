package com.payment;
//Card->“当前消费价格+优惠后价格”+扣钱
//      ifEnough“余额不足/充足”
//      deposit存储金额、consume消费金额
//金卡->“当前消费价格+优惠后价格”+扣钱+“满200元，可获得一张洗车券”--+券（剩余数量）
//银卡->“当前消费价格+优惠后价格”+扣钱

//Test->提前写三个用户，测试  【table+pay+match】
//      【table->123】
//      【pay->”请输入您本次的消费金额”+consume（存储变化+信息）
//      switch->输入卡号+匹配（调用pay）

import java.util.Scanner;

public class Test {
    GoldCard goldcard=new GoldCard("鄂A52M68","金角大王","12743896501",5000);
    SilverCard silvercard=new SilverCard("粤JM1996L","银角大王","15902834671",600);
    Card card=new Card("京A09M09","小王","12345678901",100);

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        Test t=new Test();
        while(true){
            int c=t.table(sc);
            String cardId = null; // 提前定义变量，避免重复定义
            Card card=null;
            switch(c){
                case 1:
                    System.out.print("请输入卡号：");
                    cardId = sc.next();

                    //匹配卡号
                    card =t.match(cardId,sc);
                    if (card != null) { // 新增非空判断
                        t.pay(card, sc);
                    }
                    break;
                case 2:
                    System.out.print("请输入卡号：");
                    cardId = sc.next();
                    //匹配卡号
                    card =t.match(cardId,sc);
                    if (card != null) { // 新增非空判断
                        System.out.println("您的余额为" + card.getMoney() + "元。");
                        System.out.println();
                    }
                    break;
                case 3:
                    System.out.println("欢迎下次光临");
                    System.exit(0);
                    break;
                default:
                    System.out.println("请输入正确的数字");
                    break;
            }
        }
    }

    public int table(Scanner sc){
        System.out.println();
        System.out.println("========支付系统========");
        System.out.println("1.消费");
        System.out.println("2.余额查询");
        System.out.println("3.退出");
        System.out.print("请输入你的选择：");
        int choice=sc.nextInt();
        return choice;
    }

    public void pay(Card c, Scanner sc){
        System.out.print("请刷卡，请输入您本次的消费金额：");

        double money=sc.nextDouble();

        c.consume(money);
    }

    public Card match(String carId,Scanner sc){
        if(carId.equals(goldcard.getCarId())){
            return goldcard;
            //pay(goldcard,sc);
        }else if(carId.equals(silvercard.getCarId())){
            return silvercard;
            //pay(silvercard,sc);
        }else if(carId.equals(card.getCarId())){
            return card;
            //pay(card,sc);
        }else{
            System.out.println("未找到该用户");
            return null;
        }
    }
}
