import java.time.LocalDate;
import java.time.LocalTime;

public class TimePeriod {//صنف الفترة الزمنية للامتحان
    private int tId;//رقم الفترة
    private LocalDate tDay;//يوم الامتحان
    private LocalTime tStart;//وقت بداية الفترة

    public TimePeriod(int tId,LocalDate tDay,LocalTime tStart){//فاليو كونستراكت
        this.tId=tId;
        this.tDay=tDay;
        this.tStart=tStart;
    }

    //get method
    public int getTId(){return tId;}//دالة ارجاع لرقم الفترة
    public LocalDate getTDay(){return tDay;}//دالة ارجاع ليوم الفترة
     public LocalTime getTStart(){return tStart;}//دالة ارجاع لوققت بداية الفترة

    //set method
    public void setTId(int id){tId=id;}//تعديل رقم الفترة
    public void setTDay(LocalDate day){tDay=day;}//تعديل يوم الفترة
    public void setTStart(LocalTime start){tStart=start;}//نعديل وقت بداية الفترة
}
