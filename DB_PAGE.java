import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class DB_PAGE extends JFrame {
    JPanel parametersPanel;
    JPanel fieldPanel;
    JPanel buttonPanel;
    JPanel consolePanel;
    JPanel tablePanel;
    JLabel idText;
    JLabel nameText;
    JLabel ageText;
    JLabel majorText;
    JTextField idField;
    JTextField nameField;
    JTextField ageField;
    JTextField majorField;
    JButton selectButton;
    JButton addButton;
    JButton deleteButton;
    JButton updateButton;
    JButton reloadButton;
    DefaultTableModel studentTable;
    JTable table;
    JTextArea consoleTextArea;

    private final DatabaseControl databaseControl;

    public DB_PAGE() {
        JFrame frame = new JFrame();
        databaseControl = new DatabaseControl();

        Image image = new ImageIcon("aitu-logo_.png").getImage();
        frame.setIconImage(image);


        parametersPanel = new JPanel();
        parametersPanel.setBackground(new Color(0xD4E7C5));
        parametersPanel.setBounds(10, 10, 200, 300);
        parametersPanel.setLayout(null);

        fieldPanel = new JPanel();
        fieldPanel.setBackground(new Color(0xBFD8AF));
        fieldPanel.setBounds(200, 10, 200, 300);
        fieldPanel.setLayout(null);

        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0xE1F0DA));
        buttonPanel.setBounds(10, 300, 390, 200);
        buttonPanel.setLayout(null);

        consolePanel = new JPanel();
        consolePanel.setBackground(Color.black);
        consolePanel.setBounds(10, 500, 380, 208);
        consolePanel.setLayout(null);

        tablePanel = new JPanel();
        tablePanel.setBackground(new Color(0x99BC85));
        tablePanel.setBounds(400, 10, 500, 700);
        tablePanel.setLayout(new GridLayout(0,1));



        idText = new JLabel("Student ID");
        idText.setBounds(35, 50, 150, 50);
        idText.setFont(new Font("Consolas", Font.BOLD, 20));

        nameText = new JLabel("Student Name");
        nameText.setBounds(35, 100, 150, 50);
        nameText.setFont(new Font("Consolas", Font.BOLD, 20));

        ageText = new JLabel("Student Age");
        ageText.setBounds(35, 150, 150, 50);
        ageText.setFont(new Font("Consolas", Font.BOLD, 20));

        majorText = new JLabel("Student Major");
        majorText.setBounds(35, 200, 150, 50);
        majorText.setFont(new Font("Consolas", Font.BOLD, 20));

        idField = new JTextField();
        idField.setBounds(35, 50, 150, 25);
        idField.setFont(new Font("Consolas", Font.PLAIN, 20));

        nameField = new JTextField();
        nameField.setBounds(35, 105, 150, 25);
        nameField.setFont(new Font("Consolas", Font.PLAIN, 20));

        ageField = new JTextField();
        ageField.setBounds(35, 160, 150, 25);
        ageField.setFont(new Font("Consolas", Font.PLAIN, 20));

        majorField = new JTextField();
        majorField.setBounds(35, 215, 150, 25);
        majorField.setFont(new Font("Consolas", Font.PLAIN, 20));

        selectButton = new JButton("Select");
        selectButton.setBounds(35, 35, 120, 30);
        selectButton.setFocusable(false);
        selectButton.setFont(new Font("Consolas", Font.PLAIN, 15));


        addButton = new JButton("Add");
        addButton.setBounds(35, 85, 120, 30);
        addButton.setFocusable(false);
        addButton.setFont(new Font("Consolas", Font.PLAIN, 15));

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(235, 35, 120, 30);
        deleteButton.setFocusable(false);
        deleteButton.setFont(new Font("Consolas", Font.PLAIN, 15));

        updateButton = new JButton("Update");
        updateButton.setBounds(235, 85, 120, 30);
        updateButton.setFocusable(false);
        updateButton.setFont(new Font("Consolas", Font.PLAIN, 15));

        reloadButton = new JButton("Reload Database");
        reloadButton.setBounds(70, 135, 240,30);
        reloadButton.setFocusable(false);
        reloadButton.setFont(new Font("Consolas", Font.PLAIN, 15));

        consoleTextArea = new JTextArea();
        consoleTextArea.setBounds(10, 10, 360, 188);
        consoleTextArea.setEditable(false);
        consoleTextArea.setBackground(Color.BLACK);
        consoleTextArea.setForeground(Color.WHITE);
        consoleTextArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        JScrollPane consoleScrollPane = new JScrollPane(consoleTextArea);
        consoleScrollPane.setBounds(10, 10, 360, 188);
        consolePanel.add(consoleScrollPane);
        redirectSystemStreams();


        ArrayList<Student> students = databaseControl.selectAll();
        String[] columnNames = {"Roll", "Names", "Age", "Major"};

        studentTable = new DefaultTableModel(null, columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for(Student student: students){
            studentTable.addRow(new Object[]{student.getId(), student.getName(), student.getAge(), student.getMajor()});
        }
        table = new JTable(studentTable);
        table.setFont(new Font("Monserate", Font.PLAIN, 15));
        table.setRowHeight(30);
        table.setLayout(new BorderLayout());



        frame.setTitle("Student DataBase");
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(900, 750);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setBackground(new Color(0x000000));

        frame.add(parametersPanel);
        frame.add(fieldPanel);
        frame.add(buttonPanel);
        frame.add(tablePanel);
        frame.add(consolePanel);

        parametersPanel.add(idText);
        parametersPanel.add(nameText);
        parametersPanel.add(ageText);
        parametersPanel.add(majorText);

        fieldPanel.add(idField);
        fieldPanel.add(nameField);
        fieldPanel.add(ageField);
        fieldPanel.add(majorField);

        buttonPanel.add(selectButton);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(reloadButton);

        tablePanel.add(table, BorderLayout.CENTER);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(10,10));
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);
        reloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                studentTable.setRowCount(0);

                // Fetch the latest data
                ArrayList<Student> students = databaseControl.selectAll();

                // Repopulate the table model
                for(Student student : students) {
                    studentTable.addRow(new Object[]{student.getId(), student.getName(), student.getAge(), student.getMajor()});
   }
            }
        });


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                databaseControl.insertRecord(idField.getText(), nameField.getText(), ageField.getText(), majorField.getText());
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!nameField.getText().isEmpty()){
                    try {
                        databaseControl.updateStudentField(idField.getText(), "name", nameField.getText());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error", "eRROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (!ageField.getText().isEmpty()){
                    try {
                        databaseControl.updateStudentField(idField.getText(), "age", ageField.getText());
                    } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "error", "error?", JOptionPane.ERROR_MESSAGE);
                }
                }
                if (!majorField.getText().isEmpty()){
                    try{
                    databaseControl.updateStudentField(idField.getText(),"major",majorField.getText());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                databaseControl.deleteStudent(idField.getText());
            }
        });
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, databaseControl.searchStudent(idField.getText()), "Output", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
    private void redirectSystemStreams() {
        PrintStream printStream = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                consoleTextArea.append(String.valueOf((char) b));
                consoleTextArea.setCaretPosition(consoleTextArea.getDocument().getLength());
            }
        });

        System.setOut(printStream);
        System.setErr(printStream);
    }
}