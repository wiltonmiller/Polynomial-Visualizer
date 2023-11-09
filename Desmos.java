package Desmos;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Path2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Desmos extends JFrame implements ActionListener, KeyListener {
	
	JPanel EnterPanel;
	
	NumberPanel numberPanel;
	
	JTextArea Text; 
	JTextArea Coefficients; //Idk how to spell
	JTextField NumberEnter;
	
	JButton enterButton;
	JButton drawGraph;
	
	static ArrayList<Double> coefficients = new ArrayList<Double>();
	
	static int coefficientsEntered = 0;
	
	static int xPrev;
	static int yPrev;
	
	public Desmos() {
		
		super("Shity Version of Desmos that can only draw polynomials up to degree 10");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 800);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setLayout(null);
		
		EnterPanel = new JPanel();
		EnterPanel.setSize(800, 800);
		EnterPanel.setBackground(Color.DARK_GRAY);
		EnterPanel.setLayout(null);
		EnterPanel.setVisible(true);
		this.add(EnterPanel);
		
		numberPanel = new NumberPanel();
		this.add(numberPanel);
		
		Text = new JTextArea();
		Font font = new Font ("Arial Black", Font.BOLD, 20);
		Text.setFont(font);
		Text.setText("Enter the coefficietns of the polynomial");
		Text.setBackground(Color.BLACK); //set background color
		Text.setForeground(Color.BLUE); //sets text color
		Text.setBounds(80, 50, 470, 30);
		EnterPanel.add(Text);
		Text.setEditable(false);  
		Text.setVisible(true);
		
		Coefficients = new JTextArea();
		Font font2 = new Font ("Arial Black", Font.BOLD, 20);
		Coefficients.setFont(font2);
		Coefficients.setText("");
		Coefficients.setBackground(Color.WHITE); //set background color
		Coefficients.setForeground(Color.RED); //sets text color
		Coefficients.setBounds(80, 500, 10, 30);
		EnterPanel.add(Coefficients);
		Coefficients.setEditable(false);  
		Coefficients.setVisible(false);
		
		NumberEnter = new JTextField(2);
		NumberEnter.setHorizontalAlignment(JTextField.CENTER);
		Font font3 = new Font ("Monospaced", Font.BOLD, 50);
		NumberEnter.setFont(font3);
		NumberEnter.setBounds(560, 40, 50, 50);
		NumberEnter.setVisible(true);
		NumberEnter.addActionListener(this);
		NumberEnter.addKeyListener(this);
		NumberEnter.setEditable(true);
		EnterPanel.add(NumberEnter);
		
		enterButton = new JButton("Enter");
		enterButton.setBounds(620, 30, 70, 70);
		enterButton.setBackground(Color.RED);
		enterButton.setVisible(true);
	    enterButton.addActionListener(this);
		EnterPanel.add(enterButton);
		
		drawGraph = new JButton("Draw Graph");
		drawGraph.setBounds(400, 500, 150, 70);
		drawGraph.setBackground(Color.RED);
		drawGraph.setVisible(false);
	    drawGraph.addActionListener(this);
		EnterPanel.add(drawGraph);
		
		
		//it only appears when you hover over it, which is just gonna be a feature, not a bug
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == enterButton) 
			if(coefficientsEntered <= 10 && isNumber(NumberEnter.getText())) {
				coefficientsEntered++;
				Text.setText("Enter up to 10 numbers");
				double n = Double.parseDouble(NumberEnter.getText());
				coefficients.add(0, n); //adds to the beginning of the list
				updateCoefficienets(NumberEnter.getText());
				NumberEnter.setText(null);
				drawGraph.setVisible(true);
				
			}
			else {
				Text.setText("Enter a number u knob");
			}
		
		if(e.getSource() == drawGraph) {
			EnterPanel.setVisible(false);
			numberPanel.setVisible(true);
		}
	}
		




	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) 
			if(coefficientsEntered <= 10 && isNumber(NumberEnter.getText())) {
				coefficientsEntered++;
				Text.setText("Enter up to 10 numbers");
				double n = Double.parseDouble(NumberEnter.getText());
				coefficients.add(0 , n); //adds to the begginig of the list
				updateCoefficienets(NumberEnter.getText());
				NumberEnter.setText(null);
				drawGraph.setVisible(true);
			}
			else {
				Text.setText("Enter a number u knob");
			}
		}
		
	


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void updateCoefficienets(String s) {
		
		Coefficients.setText(Coefficients.getText() + " " + s);
		Coefficients.setVisible(true);
		Coefficients.resize(Coefficients.getWidth() + 25, 30);
		EnterPanel.updateUI();
		
	}
	
	public static boolean isNumber(String num) {
	    if (num == null) {
	        return false;
	    }
	    try {
	        double n = Double.parseDouble(num);
	    } 
	    catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	public static void main(String [] args) {
		//TODO auto generated stub
		
		Desmos desmos = new Desmos();
		
	}
	
	class NumberPanel extends JPanel
	{
	    public NumberPanel(){
			this.setSize(800, 800);
			this.setBackground(Color.DARK_GRAY);
			this.setLayout(null);
			this.setVisible(false);
	    }

	    @Override
	    public void paintComponent(Graphics g){
	        super.paintComponent(g);
	        
	        Graphics2D g2 = (Graphics2D) g;
	        
	        Path2D.Double path = new Path2D.Double();
	        
	        
	        g2.setStroke(new BasicStroke(2));
	        g2.setColor(Color.WHITE);
	        g2.drawLine(400, 100, 400, 500); //y axis
	        g2.drawLine(200, 300, 600, 300); //x axis
	        
	        int y;

	    	for(int x = -200; x <= +200; x++)
	    	{
	    		y = 0;
	    		System.out.println("x" + " "+ x);
	    		for(int i = coefficients.size(); i >= 1; i--) {
	    			y = (int) (y + coefficients.get(i - 1)*(Math.pow(x, i)));//needs to be i-1 since the length is 3 but it only
	    			//goes up to index 2, since an arraylist starts at index 0
	    		}
	    		
	    		System.out.println("y final" + " " + y);
	    		g2.setStroke(new BasicStroke(1));
	    		g2.setColor(Color.PINK);
	    		g2.drawOval(x + 400, y*-1 + 300, 1, 1); //NEED TO MULTIPLY Y BY -1 SINCE THE POSTIVE Y DIRECTION ON THE
	    		//COMPUTER IS DOWN, NOT UP LIKE ON THE CARTESIAN PLANE
	    		//THE +400 AND THE +300 ARE TO EXTRAPOLATE THE POINTS FROM THE COORDIANITES AROUND THE ORIGIN 
	    		//TO THEN PAINTING THEM ON THE SCREEN
	    		
	    	
	    	
	    	}
	    	
	    	
	    }
	}
	           
}
	    
