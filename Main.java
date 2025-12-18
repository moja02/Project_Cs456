import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
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
            //periods.add(new TimePeriod(tId++, current, LocalTime.of(2, 0)));
            
        
        }

       // ====== المقررات ======
        
        courses.add(new Courses("Cs100", "مقدمة في علم الحاسوب",          1));
      
        courses.add(new Courses("MM102", "هندسة تحليلية مستوية", 1));
        
        courses.add(new Courses("PH112", "فيزياء كهربية",   1));

        courses.add(new Courses("CS111", "برمجة بلغة الفورتران", 1));
        
       
        
       

        

        courses.add(new Courses("CS115", "برمجة بلغة السي 1",           1));
        courses.add(new Courses("CS200", "التركيبات المتقطعة", 2));
        courses.add(new Courses("CS207", "تنظيم الحاسب الالى",    2));
        courses.add(new Courses("PH200", "دوائر الكترونية رقمية",           2));

      
        courses.add(new Courses("CS211", "برمجة بلغة الاسمبلي",      2));
        courses.add(new Courses("CS215", "برمجة بلغة السي 2",     2));
        courses.add(new Courses("MA206", "جبر خطي",  2));
      
        courses.add(new Courses("CS321", "تحليل عددي 1",     3));
        courses.add(new Courses("CS331", "هياكل بيانات 1",  3));
        courses.add(new Courses("CS315", "برمجة ويب", 3));
        courses.add(new Courses("CS319", "تحليل و تصميم نظم",   3));

        courses.add(new Courses("CS332", "هياكل بيانات 2",      3));
        courses.add(new Courses("CS322", "تحليل عددي 2",     3));
        courses.add(new Courses("CS336", "لغات برمجة",  3));
        courses.add(new Courses("CS441", "نظرية الاتمتة", 4));
        courses.add(new Courses("CS340", "امن",   3));

        courses.add(new Courses("CS443", " شبكات الحاسب",      4));
        courses.add(new Courses("CS437", "نظم تشغيل",     4));
        courses.add(new Courses("CS431", "برمجة النظم",  4));
        courses.add(new Courses("CS438", "هندسة برمجيات", 4));
        courses.add(new Courses("CS456", "ذكاء الاصطناعي",   4));
        
        courses.add(new Courses("CS436", "قواعد بيانات",      4));
        courses.add(new Courses("CS461", "تعلم الة",     4));
        
        double mutationRate  = 0.05;
        double crossoverRate = 0.5;
        int populationSize   =60;
        int generations      = 100;

        GeneticAlgorithm ga = new GeneticAlgorithm(
                mutationRate,
                crossoverRate,
                populationSize,
                generations,
                2 // seed
        );

        // قبل استدعاء الخوارزمية
        long startTime = System.nanoTime();

        Chromosome best = ga.run(courses, rooms, periods);

        // بعد انتهاء الخوارزمية
        long endTime = System.nanoTime();

        // حساب الزمن بالميلي ثانية
        double executionTimeMs = (endTime - startTime) / 1_000_000.0;

        System.out.println("\n===== Final Best Solution =====");
        System.out.println("Best fitness = " + best.getFitness());
        System.out.println("Execution time = " + executionTimeMs + " ms");

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
    
