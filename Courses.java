
public class Courses{
    private String cId;//رمز المقرر
    private String cName;//اسم المقرر
    private int year;//سنة الجامعية للمقرر
    public  Courses(String cId , String cName , int year){//براميتر كونستراكت
        this.cId = cId;
        this.cName = cName;
        this.year =year;
    }
    // get functions
    public String getCId(){return cId;}//دالة لارجاع رمز المادة
    public String getCName(){return cName;}//لارجاع اسم المادة
    public int getYear(){return year;}//لارجاع سنة الجامعية للمادة
    
    // set funcctions

    public void setCId(String id){ cId = id ;}//دالة تعديل رمز المادة
    public void  setCName(String name){ cName = name ;}//دالة تعديل اسم المادة
    public void setYear(int year){ this.year = year ;}//دالة تعديل السنة الجامعية للمادة
    
    public String toString(){//لتحقق من معلومات الكلاس 
        String returnString="Course Id : "+cId+"Course Name : "+cName;
        return returnString;

    }
}
