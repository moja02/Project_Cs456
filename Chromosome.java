package project;

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
    int conflicts = 0;

    for (int i = 0; i < exames.size(); i++) {
        Exam e1 = exames.get(i);
        for (int j = i + 1; j < exames.size(); j++) {
            Exam e2 = exames.get(j);
            // نفس الفترة + نفس القاعة
            if (( e1.getTimePeriod().getTId() == e2.getTimePeriod().getTId() ) &&
                e1.getRoom().getRName().equals(e2.getRoom().getRName())) {
                conflicts++;
            }
            // نفس الفترة + نفس السنة الجامعية
            if (e1.getTimePeriod().getTId() == e2.getTimePeriod().getTId() &&
                isConflict(e1.getCourse(), e2.getCourse())) {
                conflicts++;
            }
        }
    }
    return 1.0 / (1 + conflicts);  // كل ما نقصت القيمة → الجدول أحسن
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
