import java.awt.*;        // Using AWT container and component classes
import java.awt.event.*;  // Using AWT event classes and listener interfaces
import javax.swing.*;
 
// An AWT program inherits from the top-level container java.awt.JFrame
public class EasyCalculator extends JFrame {
    private JLabel lblOperator, lblResult;
    private JTextField tfLeftOperand, tfRightOperand;
    private enum Operation {
        Add,
        Minus,
        Multiply,
        Divide,
        NumberOfOperations,
        Null
    };
    private String OperationSigns[] = {"+", "-", "*", "/"};
    private JButton[] ChangeOperationJButtons = new JButton[Operation.NumberOfOperations.ordinal()];
    private JButton btnOK;
    private Operation operation = Operation.Null;
 
    /** Constructor to setup GUI components and event handling */
    public EasyCalculator () {
        setLayout(new GridLayout(2, 5));
      
        // first row
        tfLeftOperand = new JTextField();
        add(tfLeftOperand);
          
        lblOperator = new JLabel();
        lblOperator.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblOperator);
          
        tfRightOperand = new JTextField();
        add(tfRightOperand);
        
        JLabel lblEuqal = new JLabel("=");
        lblEuqal.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblEuqal);
          
        lblResult = new JLabel();
        lblResult.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblResult);

        // second row
        for (int i = 0; i < Operation.NumberOfOperations.ordinal(); ++i) {
            ChangeOperationJButtons[i] = new JButton(OperationSigns[i]);
            ChangeOperationJButtons[i].addActionListener(new ChangeOperationAction(Operation.values()[i]));
            add(ChangeOperationJButtons[i]);
        }
      
        btnOK = new JButton("OK");
        btnOK.addActionListener(new CalculateAction());
        add(btnOK);
     
        setTitle("Easy Calculator");
        setSize(250 * 2, (100 + 30) * 2);
        setVisible(true);         // "super" JFrame shows
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
            EasyCalculator.this.lblOperator.setText(OperationSigns[operation.ordinal()]);
            EasyCalculator.this.operation = operation;
        }
    }

    private class CalculateAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
        	int leftOperand, rightOperand;
        	String result;
        	try {
        		leftOperand = Integer.parseInt(tfLeftOperand.getText());
        		rightOperand = Integer.parseInt(tfRightOperand.getText());
        	} catch (NumberFormatException e) {
        		JOptionPane.showMessageDialog(btnOK, "Not all operands are valid numbers!");
        		return;
        	}
            switch (EasyCalculator.this.operation) {
	        	case Add :
	        		result = Integer.toString(leftOperand + rightOperand);
	        		break;
	        	case Minus :
	        		result = Integer.toString(leftOperand - rightOperand);
	        		break;
	        	case Multiply :
	        		result = Integer.toString(leftOperand * rightOperand);
	        		break;
	        	case Divide :
	        		if (rightOperand == 0) {
	            		JOptionPane.showMessageDialog(btnOK, "Do not divide 0!");
	            		return;
	        		}
	        		result = Integer.toString(leftOperand / rightOperand);
	        		break;
	        	default:  // operand not specified
            		return;
            }
            EasyCalculator.this.lblResult.setText(result);
        }
    }
}