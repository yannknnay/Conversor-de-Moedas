package view;

import controller.CurrencyHandler;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    JPanel panel;
    JTextField textField;
    JComboBox<String> currency1;
    JComboBox<String> currency2;
    JButton convertBtn;
    CurrencyHandler controller = new CurrencyHandler();

    public Window() {
        setTitle("Currency Converter");
        setSize(400,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        startWindow();
    }

    private void startWindow() {
        panel = new JPanel(new GridLayout(4,2, 10, 10));

        textField = new JTextField();

        currency1 = new JComboBox<>();
        currency2 = new JComboBox<>();

        controller.populateJComboBox(currency1);
        controller.populateJComboBox(currency2);

        convertBtn = new JButton("Convert");

        panel.add(new JLabel("Value: "));
        panel.add(textField);
        panel.add(new JLabel("From: "));
        panel.add(currency1);
        panel.add(new JLabel("To: "));
        panel.add(currency2);
        panel.add(new JLabel(""));
        panel.add(convertBtn);

        add(panel);
    }
}
