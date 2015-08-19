// Using AWT container and component classes
import java.awt.*;
//Using AWT event classes and listener interfaces
import java.awt.event.*;
import javax.swing.*;
 
// An AWT program inherits from the top-level container java.awt.JFrame
public class EasyCalculator extends JFrame {
    private static int result;
    private JLabel lblOperator, lblResult;
    private JTextField tfLeftOperand, tfRightOperand;
    private enum Operation {
        ADD,
        MINUS,
        MULTIPLY,
        DIVIDE,
        NUMBER_OF_OPERATIONS,
        NULL
    };
    private String operationSigns[] = {"+", "-", "*", "/"};
    private JButton btnOK;
    private Operation operation = Operation.NULL;
    private static final int WINDOW_WIDTH = 500, WINDOW_HEIGHT = 200, GRID_ROWS = 2, GRID_COLS = 5;
 
    /** Constructor to setup GUI components and event handling */
    public EasyCalculator () {
        setLayout(new GridLayout(GRID_ROWS, GRID_COLS));

        // first row
        tfLeftOperand = new JTextField("12");
        tfLeftOperand.setHorizontalAlignment(SwingConstants.CENTER);
        add(tfLeftOperand);

        lblOperator = new JLabel();
        lblOperator.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblOperator);

        tfRightOperand = new JTextField("2");
        tfRightOperand.setHorizontalAlignment(SwingConstants.CENTER);
        add(tfRightOperand);

        JLabel lblEuqal = new JLabel("=");
        lblEuqal.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblEuqal);

        lblResult = new JLabel();
        lblResult.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblResult);

        // second row
        for (int i = 0; i < Operation.NUMBER_OF_OPERATIONS.ordinal(); ++i) {
            JButton newJButton = new JButton(operationSigns[i]);
            newJButton.addActionListener(new ChangeOperationAction(Operation.values()[i]));
            add(newJButton);
        }
      
        btnOK = new JButton("OK");
        btnOK.addActionListener(new CalculateAction());
        add(btnOK);
     
        setTitle("Easy Calculator");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        // "super" JFrame shows
        setVisible(true);
    }
 
    /** The entry main() method */
    public static void main(String[] args) {
       // Invoke the constructor to setup the GUI, by allocating an instance
        EasyCalculator app = new EasyCalculator();
    }
 
    /* ActionListener as an inner class */
    private class ChangeOperationAction implements ActionListener {
        private Operation operation;

        public ChangeOperationAction(Operation o) {
            operation = o;
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            EasyCalculator.this.lblOperator.setText(operationSigns[operation.ordinal()]);
            EasyCalculator.this.operation = operation;
        }
    }

    // inner action class
    private class CalculateAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            int leftOperand, rightOperand;
            try {
                leftOperand = Integer.parseInt(tfLeftOperand.getText());
                rightOperand = Integer.parseInt(tfRightOperand.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(btnOK, "Not all operands are valid numbers!");
                return;
            }
            switch (EasyCalculator.this.operation) {
                case ADD :
                    result = leftOperand + rightOperand;
                    break;
                case MINUS :
                    result = leftOperand - rightOperand;
                    break;
                case MULTIPLY :
                    result = leftOperand * rightOperand;
                    break;
                case DIVIDE :
                    if (rightOperand == 0) {
                        JOptionPane.showMessageDialog(btnOK, "Do not divide 0!");
                        return;
                    }
                    result = leftOperand / rightOperand;
                    break;
                // operand not specified
                default:
                    return;
            }
            EasyCalculator.this.lblResult.setText(Integer.toString(result));
        }
    }
}
