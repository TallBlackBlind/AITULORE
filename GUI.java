import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.DriverManager;

public class GUI extends JFrame {
    private JButton button;
    private JLabel label;
    JCheckBox checkBox;
    JRadioButton male, female;
    JTextField name_field, id_field;

    public GUI() {
        super("AITU Students");
        super.setBounds(600, 300, 400, 400);
        setSize(400, 300);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = super.getContentPane();
        container.setLayout(new GridLayout(5, 2, 2, 10));

        JLabel name = new JLabel("Input Student name:");
        name_field= new JTextField("", 1);
        JLabel id = new JLabel("Input Student id:");
        id_field= new JTextField("", 1);

        container.add(name);
        container.add(name_field);
        container.add(id);
        container.add(id_field);

        male = new JRadioButton("Man");
        female = new JRadioButton("Woman");
        checkBox = new JCheckBox("You are from AITU?", false);
        JButton send_button = new JButton("Send");

        male.setSelected(true);
        container.add(male);
        container.add(female);

        ButtonGroup group = new ButtonGroup();
        group.add(male);
        group.add(female);

        container.add(checkBox);
        container.add(send_button);

        send_button.addActionListener(new ButtonEvent());
    }
    class ButtonEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = name_field.getText();
            Integer id = Integer.parseInt(id_field.getText());
            String sex = "Man";
            if(!checkBox.isSelected()){
                if (!male.isSelected()){
                    sex = sex = "Woman";
                }
            }
            else{
                sex = "gay";
                if (!male.isSelected()){
                    sex = "lesbian";
                }
            }
            boolean checkbox = checkBox.isSelected();

            if(checkbox){
                JOptionPane.showMessageDialog(null, "You're id: " + id
                                + "\nYou're " + sex.toUpperCase() + "!",
                        "Hello from AITU ", JOptionPane.PLAIN_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null, "You're id: " + id
                                + "\nYour sex: " + sex + "\n Are you sure? " + checkbox,
                        "Hello, " + name, JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setVisible(true);

    }


}



