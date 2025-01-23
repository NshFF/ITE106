import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class CalculatorNash extends JFrame implements ActionListener {

    private JTextField textDisplay;
    private JTextArea historyDisplay;

    private double input1, input2, resultingValue;
    private String operator;
    private boolean done;

    public CalculatorNash() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLayout(new BorderLayout());

        textDisplay = new JTextField();
        textDisplay.setEditable(false);
        textDisplay.setFont(new Font("Arial", Font.BOLD, 24));
        textDisplay.setHorizontalAlignment(JTextField.RIGHT);
        textDisplay.setBackground(Color.black);
        textDisplay.setForeground(Color.white);
        textDisplay.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(textDisplay, BorderLayout.NORTH);

        JPanel buttonGroup = new JPanel();
        buttonGroup.setBackground(Color.black);
        buttonGroup.setForeground(Color.black);
        buttonGroup.setLayout(new GridLayout(5, 4, 5, 5));
        buttonGroup.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        };

        for (String button : buttons) {
            JButton b = new JButton(button);
            b.setFont(new Font("Arial", Font.BOLD, 24));
            b.setBackground(Color.gray);
            b.setForeground(Color.white);
            b.setFocusPainted(false);
            b.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
            b.addActionListener(this);
            buttonGroup.add(b);
        }

        add(buttonGroup, BorderLayout.CENTER);

        JPanel history = new JPanel();
        history.setBackground(Color.black);
        history.setLayout(new BorderLayout());

        historyDisplay = new JTextArea(30, 20);
        historyDisplay.setEditable(false);
        historyDisplay.setFont(new Font("Courier New", Font.PLAIN, 14));
        historyDisplay.setBackground(Color.black);
        historyDisplay.setForeground(Color.white);
        historyDisplay.setText("History:\n");

        JScrollPane scrollPane = new JScrollPane(historyDisplay);
        history.add(scrollPane, BorderLayout.CENTER);

        add(history, BorderLayout.WEST);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String calcuInput = e.getActionCommand();

        if (textDisplay.getText().length() <= 0 && calcuInput.equals("0")) {
            return;
        }

        if (calcuInput.equals("C")) {
            input1 = 0;
            input2 = 0;
            textDisplay.setText("");
            done = false;
        } else if (Character.isDigit(calcuInput.charAt(0)) || calcuInput.equals(".")) {
            if (done) {
                textDisplay.setText("");
                done = false;
            }

            if (calcuInput.equals(".") && textDisplay.getText().contains(".")) {
                return;
            }

            textDisplay.setText(textDisplay.getText() + calcuInput);
        } else if (calcuInput.equals("=")) {
            if (textDisplay.getText().isEmpty()) {
                return;
            }
            input2 = Double.parseDouble(textDisplay.getText());
            calculate();
            textDisplay.setText(String.valueOf(resultingValue));

            done = true;

            try {
                String record = input1 + " " + operator + " " + input2 + " = " + resultingValue;
                historyRecorder(record);
                historyDisplay.append(record + "\n");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else {
            if (textDisplay.getText().isEmpty()) {
                return;
            }
            operator = calcuInput;
            input1 = Double.parseDouble(textDisplay.getText());
            textDisplay.setText("");
            done = false;
        }
    }

    public void calculate() {
        switch (operator) {
            case "+":
                resultingValue = input1 + input2;
                break;
            case "-":
                resultingValue = input1 - input2;
                break;
            case "*":
                resultingValue = input1 * input2;
                break;
            case "/":
                if (input2 == 0) {
                    resultingValue = 0;
                    textDisplay.setText("Error");
                } else {
                    resultingValue = input1 / input2;
                }
                break;
        }
    }

    public String historyRecorder(String record) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("calculator_history.txt"));
        String line;
        String history = "";
        while ((line = reader.readLine()) != null) {
            history += line + System.lineSeparator();
        }
        history += record;
        BufferedWriter writer = new BufferedWriter(new FileWriter("calculator_history.txt", true));
        writer.write(record + "\n");
        reader.close();
        writer.close();
        return history;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("calculator_history.txt"));
        writer.write("");
        writer.close();

        new CalculatorNash();
    }
}
