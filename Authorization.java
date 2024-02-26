import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Authorization extends JFrame implements ActionListener {
    JButton button;
    JPanel panel;
    JTextField login_textField;
    JPasswordField password_textField;
    JFrame frame;
    JLabel label;

    public Authorization() {
        frame = new JFrame();

        panel = new JPanel();
        panel.setBackground(Color.lightGray);
        panel.setBounds(75, 80, 250, 250);
        panel.setLayout(null);

        label = new JLabel("Authorization");
        label.setBounds(120, 30, 400, 50);
        label.setFont(new Font(null, Font.BOLD, 24));


        button = new JButton("Login");
        button.setFocusable(false);
        button.setBounds(75, 160, 100, 30);
        button.addActionListener(this);

        login_textField = new JTextField();
        login_textField.setBounds(60, 60, 130, 30);
        login_textField.setFont(new Font("Consolas", Font.PLAIN, 20));

        password_textField = new JPasswordField();
        password_textField.setBounds(60, 110, 130, 30);
        password_textField.setFont(new Font(null, Font.PLAIN, 30));

        frame.setTitle("Authorization");
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);


        frame.add(panel);
        frame.add(label);

        panel.add(button);
        panel.add(login_textField);
        panel.add(password_textField);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            String login = login_textField.getText();
            String password = new String(password_textField.getPassword());
            if (login.equals("admin") && password.equals("admin")) {
                {
                    DB_PAGE DB = new DB_PAGE();
                    frame.dispose();
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Wrong Login or Password", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
