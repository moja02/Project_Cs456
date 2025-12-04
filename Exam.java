package project;

public class Exam {
    private Courses course;//مادة الامتحان
    private Room room;//قاعة الامتحان
    private TimePeriod time;//وقت الامتحان

    public Exam(Courses course, Room room, TimePeriod time) {//فاليو كونستراكت
        this.course = course;
        this.room = room;
        this.time = time;
        
    }
    // Copy constructor (نسخ عميق على مستوى Exam)
    public Exam(Exam other) {
        this.course   = other.getCourse();    // غالبًا هذي كائنات ثابتة نقدر نشاركها
        this.room     = other.getRoom();
        this.time = other.getTimePeriod();
    }

    // Getters
    public Courses getCourse() {
        return course;
    }
    public Room getRoom() {
        return room;
    }

    public TimePeriod getTimePeriod() {
        return time;
    }
    // Setters
    public void setCourse(Courses course) {
        this.course = course;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setTimePeriod(TimePeriod time) {
        this.time = time;
    }
}