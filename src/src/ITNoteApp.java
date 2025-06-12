import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ITNoteApp extends JFrame {
    private JPanel contentPanel;
    private JButton addCodeBoxBtn, saveBtn;

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

        JPanel topPanel = new JPanel();
        topPanel.add(addCodeBoxBtn);
        topPanel.add(saveBtn);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // 시작 시 일반 텍스트 박스 하나 추가
        addTextBox();

        // 코드박스 추가 이벤트
        addCodeBoxBtn.addActionListener(e -> addCodeBox());

        // 저장 버튼 이벤트
        saveBtn.addActionListener(e -> saveToTxt());
    }

    // 일반 텍스트 필기 영역 추가
    private void addTextBox() {
        JTextArea textArea = new JTextArea(10, 60);
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        textArea.setBorder(BorderFactory.createTitledBorder("Note"));
        contentPanel.add(textArea);
    }

    // 코드 박스 추가
    private void addCodeBox() {
        JTextArea codeArea = new JTextArea(8, 60);
        codeArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        codeArea.setBorder(BorderFactory.createTitledBorder("Code"));
        contentPanel.add(codeArea);

        // 코드박스 뒤에는 다시 일반 텍스트 박스가 오도록 설정
        addTextBox();

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // .txt 파일로 저장하는 메소드
    private void saveToTxt() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("저장할 파일 선택");
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            if (!fileToSave.getName().toLowerCase().endsWith(".txt")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".txt");
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                for (Component comp : contentPanel.getComponents()) {
                    if (comp instanceof JTextArea textArea) {
                        String title = ((javax.swing.border.TitledBorder) textArea.getBorder()).getTitle();
                        if (title.equals("Code")) {
                            writer.write("(code)-----------\n");
                            writer.write(textArea.getText());
                            writer.write("\n-----------\n\n");
                        } else {
                            writer.write(textArea.getText());
                            writer.write("\n\n");
                        }
                    }
                }
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

