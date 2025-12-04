package project;

public class Courses {
    private String cId ;
    private String cName ;
    private int year;

    public Courses(String cId , String cName , int year){
        this.cId = cId;
        this.cName = cName;
        this.year =year;
    }
    public String getCId(){ return cId ;} //دالة لارجاع رمز المادة
    public String getCName(){ return cName ;} //لارجاع اسم المادة
    public int getYear(){ return year ;} //لارجاع سنة الجامعية للمادة

    public void setCId(String id){ cId = id ;}
    public void  setCName(String name){ cName = name ;}
    public void setYear(int year){ this.year = year ;}

    public String toString(){//لتحقق من معلومات الكلاس 
        String returnString="Course Id : "+cId+"Course Name : "+cName;
        return returnString;

    }
}
