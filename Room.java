public class Room {
    private String rName;//اسم القاعة
    
    //constractor
    public Room(String rName) {
        this.rName = rName;
        
    }

    //get method

    public String getRName(){return rName;}//دالة ارجاع لاسم القاعة

    //SET method

    public void setCName(String name){rName=name;}//دالة تعديل لاسم القاعة
}
