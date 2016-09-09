import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fanw.freecalculator.FreeCalculator;
import fanw.freecalculator.operator.Division;
import fanw.freecalculator.operator.Minus;
import fanw.freecalculator.operator.Multiply;
import fanw.freecalculator.operator.Operator;
import fanw.freecalculator.operator.Plus;
import fanw.freecalculator.operator.Power;

public class FreeCalculatorMain extends JFrame{

	private static final long serialVersionUID = 1L;
	private static final Map<String,Operator> mapOrperator;
	
	static{
		mapOrperator=new HashMap<String,Operator>();
		mapOrperator.put("-", new Minus());
		
		mapOrperator.put("+", new Plus());
		mapOrperator.put("*", new Multiply());
		mapOrperator.put("/", new Division());
		mapOrperator.put("^", new Power());
	}
	
    private JTextField txtFormula;
    //private JLabel lblAnswer;
    private JTextArea txtaAnswer;
    
    
	public static void main(String[] args) throws IOException {
	
		new FreeCalculatorMain().setVisible(true);
	}
	
	 public FreeCalculatorMain(){
	        //this.setSize(new Dimension(300,200));
	        this.setBounds(500, 300, 600,400);
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        txtFormula = new JTextField();
	        this.add(txtFormula,BorderLayout.NORTH);
	        
	        /*lblAnswer = new JLabel();
	        this.add(lblAnswer,BorderLayout.CENTER);*/
	        
	        txtaAnswer=new JTextArea();
	        txtaAnswer.setBounds(500, 300, 600,400);
	        this.add(txtaAnswer,BorderLayout.CENTER);
	        final FreeCalculator cal=new FreeCalculator(mapOrperator);
	        
	        txtFormula.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent e) {
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
					if(e.getKeyCode()==10){
						try{
							cal.setFormula(txtFormula.getText());
							txtaAnswer.setText(cal.calculate().toString());
						}catch(Exception e1){
							txtaAnswer.setText("式が間違いました。");
						}
					}
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
				}
			});
	    }
}
