public class Card {

    private String size;
    private String color;

    // 1. 构造方法（必须有！不然 new Card() 报错）
    public Card(){

    }
    public Card(String size, String color) {
        this.size = size;
        this.color = color;
    }

    public String getSize(){
        return size;
    }
    public void setSize(String size){
        this.size=size;
    }

    public String getColor(){
        return color;
    }
    public void setColor(String color){
        this.color=color;
    }

    @Override
    public String toString(){
        return color+size;
    }
}
