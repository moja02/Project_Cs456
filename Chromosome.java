
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
    }

    public double getFitness(){  
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

