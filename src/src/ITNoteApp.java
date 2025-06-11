import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

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

        // 저장(.txt) 버튼 이벤트
        saveBtn.addActionListener(e -> saveToTxt());

        // PDF 내보내기 기능은 미구현
    }

    // .txt 파일로 저장하는 메소드
    private void saveToTxt() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("저장할 파일 선택");
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                for (Component comp : contentPanel.getComponents()) {
                    if (comp instanceof JTextArea) {
                        writer.write(((JTextArea) comp).getText());
                        writer.write("\n\n");
                    }
                }
                // 저장 완료 알림
                JOptionPane.showMessageDialog(this, "저장 완료!", "알림", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "저장 실패: " + ex.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ITNoteApp().setVisible(true));
    }
}