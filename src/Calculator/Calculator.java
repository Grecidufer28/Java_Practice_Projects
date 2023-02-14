package Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator implements ActionListener {

    private JFrame frame;
    private JTextField textField;
    private JPanel btnPanel_full;
    private JPanel[] btnPanels;
    private JButton MRCBtn, M_minus_Btn, M_plus_Btn, sqrtBtn, signBtn;
    private JButton clrBtn, M_clear_Btn, percentBtn;
    private JButton addBtn, subtBtn, multBtn, divBtn, equBtn;
    private JButton zeroBtn, oneBtn, twoBtn, threeBtn, fourBtn, fiveBtn, sixBtn, sevBtn, eightBtn, nineBtn;
    private JButton decimalBtn;
    private double val1 = 0;
    private double val2 = 0;
    private double memVal = 0;

    public Calculator() {
        createCalculatorWindow();
        createCalculatorDisplay();
        finalizeCalculatorWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Digit input
        if( e.getSource() == oneBtn) {
            addDigit(1);
        }
        if( e.getSource() == twoBtn ) {
            addDigit(2);
        }
        if( e.getSource() == threeBtn ) {
            addDigit(3);
        }
        if( e.getSource() == fourBtn ) {
            addDigit(4);
        }
        if( e.getSource() == fiveBtn ) {
            addDigit(5);
        }
        if( e.getSource() == sixBtn ) {
            addDigit(6);
        }
        if( e.getSource() == sevBtn ) {
            addDigit(7);
        }
        if( e.getSource() == eightBtn ) {
            addDigit(8);
        }
        if( e.getSource() == nineBtn ) {
            addDigit(9);
        }
        if( e.getSource() == zeroBtn ) {
            addDigit(0);
        }

        // Operation input
        if( e.getSource() == addBtn) {
            activateOperation(addBtn, subtBtn, multBtn, divBtn);
        }
        if( e.getSource() == subtBtn ) {
            activateOperation(subtBtn, addBtn, multBtn, divBtn);
        }
        if( e.getSource() == multBtn ) {
            activateOperation(multBtn, addBtn, subtBtn, multBtn);
        }
        if( e.getSource() == divBtn ) {
            activateOperation(divBtn, addBtn, subtBtn, multBtn);
        }

        if( e.getSource() == equBtn ) {
            val2 = Double.parseDouble(textField.getText());
            if(addBtn.getBackground() == Color.CYAN) {
                addBtn.setBackground(null);
                val1+=val2;
            }
            if(subtBtn.getBackground() == Color.CYAN) {
                subtBtn.setBackground(null);
                val1-=val2;
            }
            if(multBtn.getBackground() == Color.CYAN) {
                multBtn.setBackground(null);
                val1*=val2;
            }
            if(divBtn.getBackground() == Color.CYAN) {
                divBtn.setBackground(null);
                val1/=val2;
            }
            val2 = 0;
            displayResult(val1);
        }

        if( e.getSource() == signBtn ) {
            if(textField.getText().charAt(0) == '-')
                textField.setText(textField.getText().substring(1));
            else
                textField.setText('-'+textField.getText());
        }

        if( e.getSource() == clrBtn ) {
            clear(textField.getText().equals("0"));
        }

        if( e.getSource() == sqrtBtn ) {
            double num = Double.parseDouble(textField.getText());
            if(num >= 0) {
                num = Math.sqrt(num);
                displayResult(num);
            }
        }

        if( e.getSource() == decimalBtn ) {
            if(!textField.getText().contains("."))
                textField.setText(textField.getText() + ".");
        }

        if( e.getSource() == percentBtn ) {
            double num = Double.parseDouble(textField.getText())/100;
            displayResult(num);
        }

        if( e.getSource() == M_plus_Btn ) {
            memVal = Double.parseDouble(textField.getText());
            MRCBtn.setBackground(Color.ORANGE);
        }

        if( e.getSource() == M_clear_Btn ) {
            memVal = 0;
            MRCBtn.setBackground(null);
        }

        if( e.getSource() == M_minus_Btn ) {
            memVal = 0;
            MRCBtn.setBackground(null);
        }

        if( e.getSource() == MRCBtn ) {
            displayResult(memVal);
        }
    }

    // Adds a digit to the display whenever a number is pressed
    private void addDigit(int digit) {
        if((addBtn.getBackground() == Color.CYAN || subtBtn.getBackground() == Color.CYAN ||
            multBtn.getBackground() == Color.CYAN || divBtn.getBackground() == Color.CYAN)
            && Double.parseDouble(textField.getText()) == val1) {
            textField.setText(digit+"");
        } else {
            if(textField.getText().equals("0"))
                textField.setText("" + digit);
            else
                textField.setText(textField.getText() + digit);
        }
    }

    // Displays the specified value with a decimal point when necessary
    private void displayResult(double value) {
        if((int)value == value)
            textField.setText((int)value + "");
        else
            textField.setText(value + "");
    }

    // Clears the display (and exits function execution of necessary)
    private void clear(boolean clearAll) {
        if(clearAll) {
            val1 = val2 = 0;
            addBtn.setBackground(null);
            subtBtn.setBackground(null);
            multBtn.setBackground(null);
            divBtn.setBackground(null);
        }
        else
            textField.setText("0");
    }

    private void activateOperation(JButton operBtn, JButton b1, JButton b2, JButton b3) {
        if(b1.getBackground() != Color.CYAN
                && b2.getBackground() != Color.CYAN
                && b3.getBackground() != Color.CYAN) {
            if (operBtn.getBackground() == Color.CYAN)
                val1 /= Double.parseDouble(textField.getText());
            else
                val1 = Double.parseDouble(textField.getText());
            if ((int) val1 == val1)
                textField.setText((int) val1 + "");
            else
                textField.setText(val1 + "");
            operBtn.setBackground(Color.CYAN);
        }
    }

    // Constructs Calculator window
    private void createCalculatorWindow() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 500);
        frame.setLayout(new BorderLayout());
    }

    // Constructs display and adds it to window
    private void createCalculatorDisplay() {
        textField = new JTextField("0");
        textField.setPreferredSize(new Dimension(225, 50));
        textField.setFont(new Font("Comic Sans", Font.PLAIN, 24));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        frame.add(textField, BorderLayout.NORTH);
        createButtonPanel();
    }

    // Constructs and arranges the buttons in a specific manner
    private void createButtonPanel() {
        btnPanels = new JPanel[6];
        for(int i=0; i<btnPanels.length; i++) {
            btnPanels[i] = new JPanel();
            btnPanels[i].setLayout(new FlowLayout());
        }

        MRCBtn = new JButton("MR");
        MRCBtn.setPreferredSize(new Dimension(55, 25));
        btnPanels[0].add(MRCBtn);
        M_minus_Btn = new JButton("M-");
        M_minus_Btn.setPreferredSize(new Dimension(50, 25));
        btnPanels[0].add(M_minus_Btn);
        M_plus_Btn = new JButton("M+");
        M_plus_Btn.setPreferredSize(new Dimension(55, 25));
        btnPanels[0].add(M_plus_Btn);
        sqrtBtn = new JButton("√");
        sqrtBtn.setPreferredSize(new Dimension(50, 25));
        btnPanels[0].add(sqrtBtn);
        signBtn = new JButton("+/-");
        signBtn.setPreferredSize(new Dimension(50, 25));
        btnPanels[0].add(signBtn);

        clrBtn = new JButton("C/AC");
        clrBtn.setPreferredSize(new Dimension(65, 25));
        btnPanels[1].add(clrBtn);
        M_clear_Btn = new JButton("MC");
        M_clear_Btn.setPreferredSize(new Dimension(55, 25));
        btnPanels[1].add(M_clear_Btn);
        percentBtn = new JButton("%");
        percentBtn.setPreferredSize(new Dimension(50, 25));
        btnPanels[1].add(percentBtn);
        divBtn = new JButton("÷");
        divBtn.setPreferredSize(new Dimension(50, 25));
        btnPanels[1].add(divBtn);

        sevBtn = new JButton("7");
        sevBtn.setPreferredSize(new Dimension(50, 25));
        btnPanels[2].add(sevBtn);
        eightBtn = new JButton("8");
        eightBtn.setPreferredSize(new Dimension(50, 25));
        btnPanels[2].add(eightBtn);
        nineBtn = new JButton("9");
        nineBtn.setPreferredSize(new Dimension(50, 25));
        btnPanels[2].add(nineBtn);
        multBtn = new JButton("×");
        multBtn.setPreferredSize(new Dimension(50, 25));
        btnPanels[2].add(multBtn);

        fourBtn = new JButton("4");
        fourBtn.setPreferredSize(new Dimension(50, 25));
        btnPanels[3].add(fourBtn);
        fiveBtn = new JButton("5");
        fiveBtn.setPreferredSize(new Dimension(50, 25));
        btnPanels[3].add(fiveBtn);
        sixBtn = new JButton("6");
        sixBtn.setPreferredSize(new Dimension(50, 25));
        btnPanels[3].add(sixBtn);
        subtBtn = new JButton("-");
        subtBtn.setPreferredSize(new Dimension(50, 25));
        btnPanels[3].add(subtBtn);

        oneBtn = new JButton("1");
        oneBtn.setPreferredSize(new Dimension(50, 25));
        btnPanels[4].add(oneBtn);
        twoBtn = new JButton("2");
        twoBtn.setPreferredSize(new Dimension(50, 25));
        btnPanels[4].add(twoBtn);
        threeBtn = new JButton("3");
        threeBtn.setPreferredSize(new Dimension(50, 25));
        btnPanels[4].add(threeBtn);
        addBtn = new JButton("+");
        addBtn.setPreferredSize(new Dimension(50, 25));
        btnPanels[4].add(addBtn);

        zeroBtn = new JButton(("0"));
        zeroBtn.setPreferredSize(new Dimension(100, 25));
        btnPanels[5].add(zeroBtn);
        decimalBtn = new JButton(".");
        decimalBtn.setPreferredSize(new Dimension(50, 25));
        btnPanels[5].add(decimalBtn);
        equBtn = new JButton("=");
        equBtn.setPreferredSize(new Dimension(50, 25));
        btnPanels[5].add(equBtn);

        btnPanel_full = new JPanel();
        btnPanel_full.setLayout(new BoxLayout(btnPanel_full, BoxLayout.Y_AXIS));
        for(int i=0; i<btnPanels.length; i++) {
            Component[] components = btnPanels[i].getComponents();
            for(int j=0; j < components.length; j++)
                ( (JButton) components[j]).addActionListener(this);
            btnPanels[i].setMaximumSize(new Dimension((int) btnPanels[i].getMaximumSize().getWidth(), 25));
            btnPanel_full.add(btnPanels[i]);
        }
        btnPanel_full.add(Box.createVerticalGlue());
    }

    // Adds the buttons to the Calculator window and makes the window visible
    private void finalizeCalculatorWindow() {
        frame.add(btnPanel_full, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
