import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Start();
            }
        });
    }

    private JFrame window;
    private JButton calcButton = new JButton("Calculate");
    private JButton clearButton = new JButton("Clear");
    private JLabel jLabelEnter = new JLabel("Enter:");
    private JLabel jLabelReturn = new JLabel("Return:");
    private JTextArea jTextEnter = new JTextArea("Please enter 0-9 and +-*/.)(", 4, 25);
    private JTextArea jTextReturn = new JTextArea(4, 25);


    public Start() {
        window = new JFrame("Calculator");
        window.setSize(350, 240);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);


        someDesign();

    }

    private void someDesign(){      // располагаем элементы программы на 2х разных панелях, и работаем с кноппками
        JPanel panelButton = new JPanel();
        JPanel panelEnter = new JPanel();

        panelButton.add(calcButton);
        panelButton.add(clearButton);

        calcButton.addActionListener(new calcButtonActionListener());
        clearButton.addActionListener(new clearButtonActionListener());


        System.out.print(window.getRootPane());
        jTextEnter.setLineWrap(true);

        panelEnter.add(jLabelEnter);
        panelEnter.add(jTextEnter);
        panelEnter.add(jLabelReturn);
        panelEnter.add(jTextReturn);

        window.add(panelEnter, BorderLayout.CENTER);
        window.add(panelButton, BorderLayout.SOUTH);

        window.getRootPane().setDefaultButton(calcButton);

        jTextReturn.setEditable(false);
    }

    public class calcButtonActionListener implements ActionListener{   // метод для реализации клика по кнопке Calculate

        @Override
        public void actionPerformed(ActionEvent event){
            jTextReturn.setText("");
            jTextReturn.append(Calculator.calcAnswer(jTextEnter.getText()));
        }

    }

    public class clearButtonActionListener implements ActionListener{   // метод для реализации клика по кнопке Clear

        @Override
        public void actionPerformed(ActionEvent event){
            jTextEnter.setText("");
            jTextReturn.setText("");
        }

    }

}
