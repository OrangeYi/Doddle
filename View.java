// HelloMVC: a simple MVC example
// the model is just a counter 
// inspired by code by Joseph Mack, http://www.austintek.com/mvc/

import javax.swing.*;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.*;	
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
import java.util.ArrayList;
import java.util.List;


class View extends JPanel implements Observer{
    int change = 0;
    
	// the view's main user interface
	private JButton button; 
	private JButton buttonb; 
	private int width;
	private int height;
	private int aniation;
	
	
	// the model that this view is showing
	private Model model;
	
	public int count = -1;
	
	View(Model model_) {
		super();
		this.setFocusable(true);
		
		width = getWidth();
		height = getHeight();
		
		// create the view UI
		button = new JButton("clean");
		buttonb = new JButton("back");
		button.setMaximumSize(new Dimension(100, 50));
		button.setPreferredSize(new Dimension(100, 50));
		// a GridBagLayout with default constraints centres
		// the widget in the window
		//this.setLayout(new GridBagLayout());
		//this.add(button);
		//this.add(buttonb);
		button.addMouseListener(new MouseAdapter() { 
            public void mouseClicked(MouseEvent e) {
            	count = -1;
            	model.point.clear();
            	model.colour.clear();
            	repaint();
            }
		});
		buttonb.addMouseListener(new MouseAdapter() { 
            public void mouseClicked(MouseEvent e) {
            	if(count>0){
            		count -= 1;
            		model.point.remove(model.point.size() - 1);
            		model.colour.remove(model.colour.size()-1);
            		repaint();
            	}
            }
		});
		
		// set the model 
		model = model_;
		
		// setup the event to go to the "controller"
		// (this anonymous class is essentially the controller)
		
		addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if(model.currentCount-1 != count){
						count = model.currentCount;
						int acc =  model.point.size();
						for(int i = model.currentCount ; i < acc ; ++i){
							int acc2 = model.colour.size()-1;
							model.colour.remove(model.colour.size()-1);
							model.stock.remove(acc2);
							model.point.remove(acc2);
						}
						model.currentCount = count+1;
						model.colour.add(model.currentC);
						Point poi = new Point(model.currentS,-1);
						model.stock.add(poi);
						model.point.add(new ArrayList<Point>());
					}
					else{
						count += 1;
						model.currentCount = count+1;
						model.colour.add(model.currentC);
						Point poi = new Point(model.currentS,-1);
						model.stock.add(poi);
						model.point.add(new ArrayList<Point>());
					}
					model.change();
				}
				public void mouseReleased(MouseEvent e) {
					model.buildpaintpoints();
					//System.out.println(model.paintpoints.size() + " " + model.point.size());
				}
		});
		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				Point poi = new Point(x,y);
				model.point.get(count).add(poi);
				//model.point.get(count).add(new Point(x,y));
				repaint();
			}
	});
	} 

	int[] x_points, y_points;
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g; // cast to get 2D drawing methods
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  // antialiasing look nicer
        					RenderingHints.VALUE_ANTIALIAS_ON);
       
        
        
        
        
        
        if(width != getWidth() || height!= getHeight()){
        	model.change();
        }
        
        if(!model.play){
        	for (int i=0; i < model.currentCount; i++) {
        		x_points = new int[model.point.get(i).size()];
        		y_points = new int[model.point.get(i).size()];
        		for(int j = 0; j < model.point.get(i).size(); ++j){
        			x_points[j] = (int)model.point.get(i).get(j).x;
        			y_points[j] = (int)model.point.get(i).get(j).y;
        		}
        		int s = (int)model.stock.get(i).getX();
        		g2.setStroke(new BasicStroke(s)); 
        		Color c = model.colour.get(i);
        		g2.setColor(c);
        		g2.drawPolyline(x_points, y_points, model.point.get(i).size());
        	}
        } 
        else {
        	for (int i = model.currentCount; i < count+1; i++) {
        		x_points = new int[model.point.get(i).size()];
        		y_points = new int[model.point.get(i).size()];
        		for(int j = 0; j < model.point.get(i).size(); ++j){
        			x_points[j] = (int)model.point.get(i).get(j).x;
        			y_points[j] = (int)model.point.get(i).get(j).y;
        		}
        		int s = (int)model.stock.get(i).getX();
        		g2.setStroke(new BasicStroke(s)); 
        		Color c = model.colour.get(i);
        		g2.setColor(c);
        		g2.drawPolyline(x_points, y_points, model.paintpoints.get(i).x);
        		}
        	//model.play = false;
        	//model.currentCount = count+1;
        	}
    } 
	// Observer interface 
	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}
} 
