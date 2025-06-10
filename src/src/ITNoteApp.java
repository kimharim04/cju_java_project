import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ITNoteApp extends JFrame {
    private JPanel contentPanel;
    private JButton addCodeBoxBtn, saveBtn, exportPdfBtn;

    public ITNoteApp() {
        setTitle("IT계열 친화적 필기노트");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(contentPanel);

        addCodeBoxBtn = new JButton("코드박스 추가");
        saveBtn = new JButton("저장(.txt)");
        exportPdfBtn = new JButton("PDF 내보내기");

        JPanel topPanel = new JPanel();
        topPanel.add(addCodeBoxBtn);
        topPanel.add(saveBtn);
        topPanel.add(exportPdfBtn);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // 코드박스 추가 이벤트
        addCodeBoxBtn.addActionListener(e -> {
            JTextArea codeArea = new JTextArea(8, 60);
            codeArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
            codeArea.setBorder(BorderFactory.createTitledBorder("Code"));
            contentPanel.add(codeArea);
            contentPanel.revalidate();
        });

        // 기타 저장, PDF 내보내기 이벤트 구현 필요
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ITNoteApp().setVisible(true));
    }
}