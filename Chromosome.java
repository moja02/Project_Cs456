
import java.util.List;

public class Chromosome {//كل كروموسوم يمثل جدول
    private List<Exam> exames;
    private double fitness;

    public Chromosome(List<Exam> exams) {
        this.exames = exams;
        this.fitness = calculateFitness();
    } 
        // get method
    public List<Exam> getExams() {  
        return exames;  
    }

    // Set method
    public void setExams(List<Exam> exames) {  
        this.exames = exames;  
    }

    
//     public double calculateFitness() {
//     int conflicts = 0;

//     for (int i = 0; i < exames.size(); i++) {
//         Exam e1 = exames.get(i);
//         for (int j = i + 1; j < exames.size(); j++) {
//             Exam e2 = exames.get(j);
//             // نفس الفترة + نفس القاعة
//             if (( e1.getTimePeriod().getTId() == e2.getTimePeriod().getTId() ) &&
//                 e1.getRoom().getRName().equals(e2.getRoom().getRName())) {
//                 conflicts++;
//             }
//             // نفس الفترة + نفس السنة الجامعية
//             if (e1.getTimePeriod().getTId() == e2.getTimePeriod().getTId() &&
//                 isConflict(e1.getCourse(), e2.getCourse())) {
//                 conflicts++;
//             }
            
//         }
//     }
//     return 1.0 / (1 + conflicts);  // كل ما قربت لل 1 كان الجدول افضل
//    }
public double calculateFitness() {
        double hardConflicts = 0;   // H: التعارضات الصارمة
        double softSameDay   = 0;   // S: مواد نفس السنة في نفس اليوم (قيد ناعم)

        for (int i = 0; i < exames.size(); i++) {
            Exam e1 = exames.get(i);
            for (int j = i + 1; j < exames.size(); j++) {
                Exam e2 = exames.get(j);

                boolean sameSlot = e1.getTimePeriod().getTId() == e2.getTimePeriod().getTId();
                boolean sameRoom = e1.getRoom().getRName().equals(e2.getRoom().getRName());
                boolean sameYear = isConflict(e1.getCourse(), e2.getCourse()); // نفس السنة/نفس المجموعة
                boolean sameDay  = e1.getTimePeriod().getTDay().equals(e2.getTimePeriod().getTDay());
                
                // ===== القيود الصارمة (Hard Constraints) =====
                // 1) نفس الفترة + نفس القاعة
                if (sameSlot && sameRoom) {
                    hardConflicts++;
                }
                // 2) نفس الفترة + نفس السنة الجامعية
                if (sameSlot && sameYear) {
                    hardConflicts++;
                }
                // ===== القيد الناعم (Soft Constraint) =====
                // نفس اليوم + نفس السنة، لكن في فترات مختلفة
                if (!sameSlot && sameDay && sameYear) {
                    softSameDay++;
                }
            }
        }
        // أوزان العقوبة: نخلي الصارم أثقل من الناعم
        double alpha = 10.0;  // وزن التعارضات الصارمة H
        double beta  = 1.0;   // وزن القيد الناعم S

        double cost = alpha * hardConflicts + beta * softSameDay;

        // الفتنس: كل ما قلت الـ cost زادت الفتنس والجدول صار أحسن
        
        return 1.0 / (1.0 + cost);
}
    
    public double getFitness() {  
        return fitness;  
    }

    public void setFitness(double fitness) {  
        this.fitness = fitness;  
    }

    // الدالة البديلة لـ ConflictChecker
    protected boolean isConflict(Courses c1, Courses c2) {
        return c1.getYear() == c2.getYear();
    }

}
