import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Room {

    private List<Card> cards=new ArrayList<>();

    public void start(){
        prepareCards();
        System.out.println("==初始牌堆==");
        System.out.println(cards);

        shuffleCards();
        System.out.println("\n=== 洗牌后牌堆 ===");
        System.out.println(cards);

        dealCards();

    }

    private void prepareCards(){
        String[] sizes={"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};
        String[] colors = {"♠", "♥", "♣", "♦"};

        //建立牌组
        for(String size:sizes){
            for(String color:colors){
                Card card = new Card(size, color);
                cards.add(card);
            }
        }

        //加入大小王
        cards.add(new Card("小王", "🃏"));
        cards.add(new Card("大王", "👑"));

    }

    //洗牌
    private void shuffleCards(){
        Collections.shuffle(cards);
    }

    private void dealCards(){

        List<Card> player1=new ArrayList<>();
        List<Card> player2=new ArrayList<>();
        List<Card> player3=new ArrayList<>();

        List<Card> dipai=new ArrayList<>();

        for(int i=0;i<cards.size();i++){
            Card card=cards.get(i);

            if(i>=cards.size()-3){
                dipai.add(card);
            }else{
                switch(i%3){
                    case 0:
                        player1.add(card);
                        break;
                    case 1:
                        player2.add(card);
                        break;
                    case 2:
                        player3.add(card);
                        break;
                }
            }
        }

    }

    private void sortCard(List<Card> playerCards){
        Collections.sort(playerCards,(c1,c2)->{
            String[] order = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2", "小王", "大王"};
            int index1 = -1;
            int index2 = -1;

            for (int i = 0; i < order.length; i++) {
                if (order[i].equals(c1.getSize())) index1 = i;
                if (order[i].equals(c2.getSize())) index2 = i;
            }
            return index1 - index2;
        });
    }
}
