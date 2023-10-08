package cn.lidan.start;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import lindan.cn.util.Const;
public class Calculator extends JFrame implements ActionListener{
	//North component:
	private JPanel jp_north = new JPanel();
	private JTextField input_text = new JTextField();
	private JButton c_Button = new JButton("C");
	
	//Central component:
	private JPanel jp_centre = new JPanel();
	public Calculator () throws HeadlessException{
		this.init();
		this.addNorthComponent();
		this.addCentreButton();
	}
	//Doing initialization:
	public void init() {
		this.setTitle(Const.TITLE);
		this.setSize(Const.FRAME_H,Const.FRAME_W);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	//Adding north component:
	public void addNorthComponent() {
		this.input_text.setPreferredSize(new Dimension(300,50));
		jp_north.add(input_text);
		
		this.c_Button.setForeground(Color.black);
		jp_north.add(c_Button);
		c_Button.setFont(new Font("ДжЬх",Font.BOLD,16));
		
		c_Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				input_text.setText("");
			}
			
		}
			);
		
		this.add(jp_north,BorderLayout.NORTH);
	}
	//Adding central component:
	public void addCentreButton(){
		String [] txt = {"1","2","3","+","^","4","5","6","-","sin","7","8","9","*","cos","0",".","=","/","tan"};
		String reg = "[\\+\\-\\*\\/\\.\\=]";
		this.jp_centre.setLayout(new GridLayout(4,5));
		for (int i = 0;i<20;i++) {
			String temp = txt[i];
			JButton button = new JButton();
			button.setText(temp);
			if(temp.matches(reg)) {
				button.setFont(new Font("ДжЬх",Font.BOLD,24));
				button.setForeground(Color.BLACK);
			}else{
				button.setFont(new Font("ДжЬх",Font.BOLD,22));
				button.setForeground(Color.GRAY);
			}
			button.addActionListener(this);
			jp_centre.add(button);
		}
		this.add(jp_centre,BorderLayout.CENTER);
	}
	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		calculator.setVisible(true);
	}
	private String preInput = null;
	private String operator = null; 
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String clickStr = e.getActionCommand();
		if(".0123456789".indexOf(clickStr) != -1) {//if enter the numbers on the calculator
			this.input_text.setText(input_text.getText() + clickStr);
			this.input_text.setHorizontalAlignment(JTextField.RIGHT);
		}else if(clickStr.matches("[\\+\\-\\*\\/\\^]{1}")) {//record the order of plus, minus, multiplication and division.
			operator = clickStr;
			preInput = this.input_text.getText();
			this.input_text.setText("");
		}
		else if(clickStr.equals("=")&&(operator != "sin"||operator != "cos"||operator != "tan")) {
			double preValue = Double.valueOf(preInput);
			double result = 0;
			boolean flag = true;
			String error = null;
			switch(operator) {
			//doing calculation:
			case "+":
					double latValue1 = Double.valueOf(this.input_text.getText());
					result = preValue + latValue1;
					break;
			case "-":
					double latValue2 = Double.valueOf(this.input_text.getText());
					result = preValue -latValue2;
					break;
			case "*":
					double latValue3 = Double.valueOf(this.input_text.getText());
					result = preValue*latValue3;
					break;
			case "/":
					double latValue4 = Double.valueOf(this.input_text.getText());
					if(latValue4 != 0) {
						result = preValue/latValue4;
						break;
					}else {
						flag = false;
						error = "Invalid Operation!";
						break;
					}
			case"^":
					double latValue5 = Double.valueOf(this.input_text.getText());
					result = Math.pow(preValue, latValue5);
					break;
					}
			
			if(flag)
				this.input_text.setText(String.valueOf(result));
			else
				this.input_text.setText(error);
			}
		}
		
	}

