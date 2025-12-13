
import javax.swing.table.JTableHeader;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FinalScheduleUI extends JFrame {// كلاس لطباعة الجدول ك واجهة رسومية

    private JTable table;
    private DefaultTableModel model;

    public FinalScheduleUI(List<String[]> finalData) {

        setTitle("Final Schedule");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // أسماء الأعمدة
        String[] columnNames = {"Course id", "Course name", "year", "room name", "day","time start"};

        // إنشاء النموذج
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        // إدخال البيانات داخل الجدول
        for (String[] row : finalData) {
            model.addRow(row);
        }

        // ضبط حجم الخط
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(28);

        // محاذاة النص لوسط الخلايا
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // جعل عناوين الأعمدة بخط واضح
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 17));
        header.setPreferredSize(new Dimension(header.getWidth(), 30));

        // إضافة ScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}
