import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {

    // ----------- Fields (حقول) -----------
    private double mutationRate;        // معدل الطفرة
    private double crossoverRate;       // معدل التزاوج
    private int populationSize;         // حجم الجيل
    private int generations;            // عدد الأجيال
    private Random random;              // أداة توليد العشوائي

    // ----------- Constructor -----------
    public GeneticAlgorithm(double mutationRate, double crossoverRate, int populationSize, int generations) {//في حال الادخال للقيمة العشوائية عشوائي
        this.mutationRate=mutationRate;
        this.crossoverRate=crossoverRate;
        this.populationSize=populationSize;
        this.generations=generations;
        this.random=new Random();
    }
    public GeneticAlgorithm(double mutationRate, double crossoverRate, int populationSize, int generations, int seed) {//ادخل باستخدام الseed
        this(mutationRate, crossoverRate, populationSize, generations);
        this.random=new Random(seed);
    }

    // ----------- Methods (أسماء فقط) -----------

    // إنشاء الكروموسومات الأولية (الجيل الأول)
    private List<Chromosome> initializePopulation(List<Courses> courses, List<Room> rooms, List<TimePeriod> periods) { 
        List<Chromosome> population = new ArrayList<>(); //الجيل الاول

        for (int i = 0; i < populationSize; i++) {
            Chromosome chromosome = createRandomChromosome(courses, rooms, periods);
            population.add(chromosome);
        }

        return population;
    }

    // إنشاء كروموسوم عشوائي واحد
    private Chromosome createRandomChromosome(List<Courses> courses, List<Room> rooms, List<TimePeriod> periods) { 
    List<Exam> exams = new ArrayList<>();

        // لكل مادة ننشئ امتحان في قاعة وفترة عشوائية
        for (Courses course : courses) {
            Room randomRoom = rooms.get(random.nextInt(rooms.size()));
            TimePeriod randomPeriod = periods.get(random.nextInt(periods.size()));

            Exam exam = new Exam(course, randomRoom, randomPeriod);
            exams.add(exam);
        }

        return new Chromosome(exams);
    }

    // اختيار Parent (Selection)
    private Chromosome selectParent(List<Chromosome> population) { 
        int tournamentSize = 4; // ممكن ان نخلوا الاختيار بين اكثر من 2 لرؤية جودة الجيل الجديد ف هذي الحالة
        Chromosome best = null;

        for (int i = 0; i < tournamentSize; i++) {
            Chromosome candidate = population.get(random.nextInt(population.size()));
            if (best == null || candidate.getFitness() > best.getFitness()) {
                best = candidate;
            }
        }
        return best;
    }

    // كروس أوفر بين Parent1 و Parent2
    private Chromosome crossover(Chromosome parent1, Chromosome parent2) {
    List<Exam> exams1 = parent1.getExams();
    List<Exam> exams2 = parent2.getExams();

    int size = exams1.size();
    List<Exam> childExams = new ArrayList<>(size);

    // احتمال عدم تطبيق الكروس اوفر -> نرجع نسخة عميقة من parent1
    if (random.nextDouble() > crossoverRate) {
        return copyChromosome(parent1);
    }

    // Uniform crossover: لكل جين 50% من parent1 و 50% من parent2
    for (int i = 0; i < size; i++) {
        if (random.nextDouble() < 0.5) {
            // ناخذ الجين من الأب الأول (مع deep copy)
            childExams.add(new Exam(exams1.get(i)));
        } else {
            // ناخذ الجين من الأب الثاني (مع deep copy)
            childExams.add(new Exam(exams2.get(i)));
        }
    }

    Chromosome child = new Chromosome(childExams);
   //child.calculateFitness();   // لأن الفتنس عندك double
    return child;
}
 //دالة مساعدة copyChromosome
private Chromosome copyChromosome(Chromosome original) {
    List<Exam> newExams = new ArrayList<>();
    for (Exam e : original.getExams()) {
        newExams.add(new Exam(e));   // deep copy لكل Exam
    }
    Chromosome copy = new Chromosome(newExams);
   //copy.calculateFitness();
    return copy;
}

    // طفرة (Mutation) على كروموسوم واحد
    private void mutate(Chromosome chromosome, List<Room> rooms, List<TimePeriod> periods) {
        List<Exam> exams = chromosome.getExams();

        for (int i = 0; i < exams.size(); i++) {
            if (random.nextDouble() < mutationRate) {
                Exam exam = exams.get(i);

                // نختار عشوائياً هل نغير القاعة أو الفترة أو الاثنين
                int choice = random.nextInt(3); // 0 or 1 or 2

                switch (choice) {
                    case 0:
                        // تغيير القاعة فقط
                        Room newRoom = rooms.get(random.nextInt(rooms.size()));
                        exam.setRoom(newRoom);
                        break;
                    case 1:
                        // تغيير الفترة فقط
                        TimePeriod newPeriod = periods.get(random.nextInt(periods.size()));
                        exam.setTimePeriod(newPeriod);
                        break;
                    case 2:
                        // تغيير الاثنين
                        Room newRoom2 = rooms.get(random.nextInt(rooms.size()));
                        TimePeriod newPeriod2 = periods.get(random.nextInt(periods.size()));
                        exam.setRoom(newRoom2);
                        exam.setTimePeriod(newPeriod2);
                        break;
                }
            }
        }
        // بعد الطفرة نعيد حساب الفتنس
        chromosome.setFitness(chromosome.calculateFitness());
    }
//     private void mutate(Chromosome chromosome, List<Room> rooms, List<TimePeriod> periods)  { //ديما بيختار قاعة و فترة عشوائية

//     List<Exam> exams = chromosome.getExams();

//     for (Exam exam : exams) {
//         if (random.nextDouble() < mutationRate) {
//             // نختار قاعة جديدة وفترة جديدة عشوائياً
//             Room newRoom = rooms.get(random.nextInt(rooms.size()));
//             TimePeriod newPeriod = periods.get(random.nextInt(periods.size()));

//             exam.setRoom(newRoom);
//             exam.setTimePeriod(newPeriod);
//         }
//     }

//    chromosome.setFitness(chromosome.calculateFitness()); 
//}

    // الحصول على أفضل كروموسوم في الجيل
    private Chromosome getBest(List<Chromosome> population) { 
    Chromosome best = population.get(0); // نبدأ بأول فرد
    for (Chromosome c : population) {
        if (c.getFitness() > best.getFitness()) {
            best = c; // لو لقينا فرد أفضل نحتفظ به
        }
    }
    return best; // نرجع أفضل فرد
    }

public Chromosome run(List<Courses> courses, List<Room> rooms, List<TimePeriod> periods) {

    // تهيئة المجتمع الأولي
    List<Chromosome> population = initializePopulation(courses, rooms, periods);

    // أفضل فرد مبدئياً
    Chromosome globalBest = getBest(population);

    //  حلقة الأجيال
    for (int gen = 0; gen < generations; gen++) {

        List<Chromosome> newPopulation = new ArrayList<>();

        //  النخبة: الاحتفاظ بأفضل كروموسوم كما هو
        Chromosome elite = copyChromosome(globalBest);
        newPopulation.add(elite);

        //  توليد بقية الأفراد في الجيل الجديد
        while (newPopulation.size() < populationSize) {
            Chromosome parent1 = selectParent(population);
            Chromosome parent2 = selectParent(population);

            Chromosome child = crossover(parent1, parent2);      
            mutate(child, rooms, periods);                       // تغيير الجينات + حساب الفتنس

            newPopulation.add(child);
        }

        // تحديث المجتمع
        population = newPopulation;

        // تحديث أفضل حل عالمي
        Chromosome bestOfGen = getBest(population);
        if (bestOfGen.getFitness() > globalBest.getFitness()) {
            globalBest = copyChromosome(bestOfGen);
        }

        // طباعة تقدّم الخوارزمية
        System.out.println("Generation " + gen +
        " - Best fitness = " + globalBest.getFitness());
    }

    // إرجاع أفضل جدول امتحانات
    return globalBest;
}

}
