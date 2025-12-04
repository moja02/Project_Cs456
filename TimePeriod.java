package project;

public class TimePeriod {//صنف الفترة الزمنية للامتحان
    private int tId;//رقم الفترة
    private String tDay;//يوم الامتحان
    private String tStart;//وقت بداية الفترة

    public TimePeriod(int tId,String tDay,String tStart){//فاليو كونستراكت
        this.tId=tId;
        this.tDay=tDay;
        this.tStart=tStart;
    }

    //get method
    public int getTId(){return tId;}//دالة ارجاع لرقم الفترة
    public String getTDay(){return tDay;}//دالة ارجاع ليوم الفترة
    public String getTStart(){return tStart;}//دالة ارجاع لوققت بداية الفترة

    //set method
    public void setTId(int id){tId=id;}//تعديل رقم الفترة
    public void setTDay(String day){tDay=day;}//تعديل يوم الفترة
    public void setTStart(String start){tStart=start;}//نعديل وقت بداية الفترة
}
