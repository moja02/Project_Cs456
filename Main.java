import java.time.LocalDate;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Courses> courses = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        List<TimePeriod> periods = new ArrayList<>();

        // القاعات
        rooms.add(new Room("R1-IT"));
        rooms.add(new Room("R2-IT"));

        // نختار تاريخ بداية يكون السبت (مثال)
        LocalDate startDate = LocalDate.of(2025, 1, 4); // هذا اليوم لازم يكون Saturday
        int numberOfDays = 14;  // مثلاً أسبوعين
        int tId = 0;

        for (int i = 0; i < numberOfDays; i++) {
            LocalDate current = startDate.plusDays(i);
            DayOfWeek dow = current.getDayOfWeek();

            // تخطي الجمعة فقط
            if (dow == DayOfWeek.FRIDAY) {
                continue;
            }

            // فترتين في اليوم: 09:00 و 12:00
            periods.add(new TimePeriod(tId++, current, LocalTime.of(9, 0)));
            periods.add(new TimePeriod(tId++, current, LocalTime.of(12, 0)));
        }

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

        double mutationRate  = 0.05;
        double crossoverRate = 0.8;
        int populationSize   = 80;
        int generations      = 50;

        GeneticAlgorithm ga = new GeneticAlgorithm(
                mutationRate,
                crossoverRate,
                populationSize,
                generations,
                2 // seed
        );

        Chromosome best = ga.run(courses, rooms, periods);

        System.out.println("\n===== Final Best Solution =====");
        System.out.println("Best fitness = " + best.getFitness());

        List<String[]> tableData = new ArrayList<>();

      List<Exam> sortedExams = new ArrayList<>(best.getExams());

        sortedExams.sort(
                Comparator.comparing((Exam e) -> e.getTimePeriod().getTDay())   // أولاً: التاريخ
                        .thenComparing(e -> e.getTimePeriod().getTStart())   // ثانياً: الوقت
        );

        // الآن نعبي الجدول بالترتيب الزمني
        for (Exam e : sortedExams) {


            String[] row = {
                   e.getCourse().getCId(),
                    e.getCourse().getCName(),
                    String.valueOf(e.getCourse().getYear()),
                    e.getRoom().getRName(),
                    e.getTimePeriod().getTDay().getDayOfWeek().toString(),
                    e.getTimePeriod().getTDay().toString(),   // التاريخ الفعلي
                    e.getTimePeriod().getTStart().toString(),             // الوقت
            };

            tableData.add(row);
        }

        // نعرض الواجهة
        new FinalScheduleUI(tableData);
    }
}
    
