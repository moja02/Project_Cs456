
public class Room {
    private String rName;//اسم القاعة

    public Room(String rName) {//فاليو كوني=ستراكت
        this.rName = rName;
        
    }

    //get method

    public String getRName(){return rName;}//دالة ارجاع لاسم القاعة

    //SET method

    public void setCName(String name){rName=name;}//دالة تعديل لاسم القاعة
}
