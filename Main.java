package project;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // 1) تجهيز البيانات (مثال تجريبي) -----------------
        List<Courses> courses = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        List<TimePeriod> periods = new ArrayList<>();

        // قاعات
        // ====== القاعات (6 قاعات) ======
        rooms.add(new Room("R1-IT"));
        rooms.add(new Room("R2-IT"));
        // rooms.add(new Room("R3-IT"));
        // rooms.add(new Room("R4-Main"));
        // rooms.add(new Room("R5-Main"));
        // rooms.add(new Room("R6-Lab"));

        // ====== الفترات الزمنية (5 أيام × فترتين = 10 فترات) ======
        // السبت
        periods.add(new TimePeriod(0, "Saturday", "09:00"));
        periods.add(new TimePeriod(1, "Saturday", "12:00"));
        // الأحد
        periods.add(new TimePeriod(2, "Sunday", "09:00"));
        periods.add(new TimePeriod(3, "Sunday", "12:00"));
        // الاثنين
        periods.add(new TimePeriod(4, "Monday", "09:00"));
        periods.add(new TimePeriod(5, "Monday", "12:00"));
        // الثلاثاء
        periods.add(new TimePeriod(6, "Tuesday", "09:00"));
        periods.add(new TimePeriod(7, "Tuesday", "12:00"));
        // الأربعاء
        periods.add(new TimePeriod(8, "Wednesday", "09:00"));
        periods.add(new TimePeriod(9, "Wednesday", "12:00"));

        // ====== المقررات (مثال ~ 20 مقرر عبر 4 سنوات) ======
        // سنة أولى
        courses.add(new Courses("C101", "Math 1",          1));
        courses.add(new Courses("C102", "Physics 1",       1));
        courses.add(new Courses("C103", "Introduction to CS", 1));
        courses.add(new Courses("C104", "English 1",       1));
        courses.add(new Courses("C105", "Discrete Math",   1));

        // سنة ثانية
        courses.add(new Courses("C201", "Data Structures", 2));
        courses.add(new Courses("C202", "Computer Architecture", 2));
        courses.add(new Courses("C203", "Linear Algebra",  2));
        courses.add(new Courses("C204", "Probability",     2));
        courses.add(new Courses("C205", "Database 1",      2));

        // سنة ثالثة
        courses.add(new Courses("C301", "Operating Systems",    3));
        courses.add(new Courses("C302", "Algorithms",           3));
        courses.add(new Courses("C303", "Software Engineering", 3));
        courses.add(new Courses("C304", "Computer Networks",    3));
        courses.add(new Courses("C305", "Database 2",           3));

        // سنة رابعة
        courses.add(new Courses("C401", "AI Fundamentals",      4));
        courses.add(new Courses("C402", "Machine Learning",     4));
        courses.add(new Courses("C403", "Distributed Systems",  4));
        courses.add(new Courses("C404", "Information Security", 4));
        courses.add(new Courses("C405", "Graduation Project",   4));

        // 2) إنشاء كائن الخوارزمية الجينية -----------------
        double mutationRate  = 0.05;
        double crossoverRate = 0.8;
        int populationSize   = 80;
        int generations      = 50;

        GeneticAlgorithm ga = new GeneticAlgorithm(
                mutationRate,
                crossoverRate,
                populationSize,
                generations
        );

        // 3) تشغيل الخوارزمية والحصول على أفضل جدول --------
        Chromosome best = ga.run(courses, rooms, periods);

        // 4) طباعة النتيجة النهائية ------------------------
        System.out.println("\n===== Final Best Solution =====");
        System.out.println("Best fitness = " + best.getFitness());

        for (Exam e : best.getExams()) {
            System.out.println(
                    e.getCourse().getCId() + " - " +
                    e.getCourse().getCName() + " | Year " + e.getCourse().getYear() +
                    " -> Room " + e.getRoom().getRName() +
                    " @ " + e.getTimePeriod().getTDay() +
                    " " + e.getTimePeriod().getTStart()
            );
        }
    }
}
