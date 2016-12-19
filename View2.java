// HelloMVC: a simple MVC example
// the model is just a counter 
// inspired by code by Joseph Mack, http://www.austintek.com/mvc/

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.*;

class View2 extends JPanel implements Observer {

	// the model that this view is showing
	private Model model;
	Color choose = (Color.BLACK);
	View2(Model model_) {
		model = model_;
		JPanel chooseC = new JPanel();
		JButton colour = new JButton("Choose");
		chooseC.setLayout(new GridLayout(0,2));
		Color[] color = new Color[]{Color.red,Color.BLACK,Color.WHITE,Color.PINK,Color.GREEN,Color.BLUE,Color.YELLOW,Color.GRAY,Color.cyan};
		for(int i = 0; i< 9 ; ++i){
			JButton a = new JButton();
			Dimension aa = new Dimension(20,20);
			a.setPreferredSize(aa);
			a.setBackground(color[i]);
			a.setOpaque(true);
			a.setBorderPainted(false);
			Color acc = color[i];
			a.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		    	choose = acc;  
		    	model.currentC = choose;
		    	colour.setBackground(choose);
		      }});
			chooseC.add(a);
		}
		
		
		
		
		
		JLabel col = new JLabel("Colour:");
		JLabel width = new JLabel("Width:");
		
		colour.setForeground(Color.WHITE);
		colour.setBackground(choose);
		colour.setOpaque(true);
		colour.setBorderPainted(false);
		chooseC.add(colour);
		//colour.setContentAreaFilled(false);
		ActionListener actionListener = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		    	  Color b = (Color.BLACK);
		    	  choose = JColorChooser.showDialog(null, "Pick Colour",b);
		    	  colour.setBackground(choose);
		    	  model.currentC = choose;
		    	  repaint();
		      }
		};
		colour.addActionListener(actionListener);
		this.setLayout(new GridLayout(2,1));
		//this.add(col);
		this.add(chooseC);
		//chooseC.setLayout(mgr);
		//this.add(colour);
		//this.add(width);
		JPanel chooseS = new JPanel();
		chooseS.setLayout(new GridLayout(5,0));
		this.add(chooseS);
		JButton one = new JButton(new ImageIcon("1.png"));
		JButton five = new JButton(new ImageIcon("5.png"));
		JButton ten = new JButton(new ImageIcon("10.png"));
		JButton fifth = new JButton(new ImageIcon("15.png"));
		JButton twti = new JButton(new ImageIcon("20.png"));
		one.addMouseListener(new MouseAdapter() { public void mouseClicked(MouseEvent e) {model.currentS = 1;}});
		five.addMouseListener(new MouseAdapter() { public void mouseClicked(MouseEvent e) {model.currentS = 5;}});
		ten.addMouseListener(new MouseAdapter() { public void mouseClicked(MouseEvent e) {model.currentS = 10;}});
		fifth.addMouseListener(new MouseAdapter() { public void mouseClicked(MouseEvent e) {model.currentS = 15;}});
		twti.addMouseListener(new MouseAdapter() { public void mouseClicked(MouseEvent e) {model.currentS = 20;}});
		chooseS.add(one);
		chooseS.add(five);
		chooseS.add(ten);
		chooseS.add(fifth);
		chooseS.add(twti);
		
	}
	@Override
	public void paintComponent(Graphics g) {
		//setBackground(Color.GRAY);
		Graphics2D g2 = (Graphics2D) g; // cast to get 2D drawing methods
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  // antialiasing look nicer
        					RenderingHints.VALUE_ANTIALIAS_ON);
        }

	// Observer interface 
	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
}
