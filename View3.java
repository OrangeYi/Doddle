import javax.swing.*;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.event.*;
import java.util.*;

class View3 extends JPanel implements Observer {

	/**
	 * 
	 */
	Timer tm = new Timer(50, new ActionListener() {   
        int i = 0;
        int j = 0;
        @Override
        public void actionPerformed(ActionEvent ae) {
        	
        	if(j < model.point.get(i).size()){
        		model.paintpoints.get(i).x++;
        		j++;
        	}
        	else {
        		i++;
        		j = 0;
        	}
        	
        	model.change();
        }
	});
    
	
	
	private static final long serialVersionUID = 1L;
	// the model that this view is showing
	private Model model;
	private JSlider slider;
	View3(Model model_) {
		// create UI
		setBackground(Color.WHITE);
		setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));

		// set the model
		model = model_;
		
		JButton play = new JButton("Play");
		play.addMouseListener(new MouseAdapter() { 
            public void mouseClicked(MouseEvent e) {
            	model.play = true;
            	tm.start();
            	model.change();
            }
		});
		this.add(play);
		
		slider = new JSlider(0,model.point.size());
		slider.setPaintTicks(true); 
		slider.setPaintLabels( true ); 
	 	slider.setSnapToTicks( true ); 
	 	slider.setMajorTickSpacing(1); 
	 	//slider.setMinorTickSpacing(1);
	 	slider.setPaintLabels( true ); 
	 	slider.setSnapToTicks( true );
	 	slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//model.currentCount = slider.getValue();
				//slider.setValue(model.currentCount);
				JSlider s = (JSlider)e.getSource();
				if(s.getValueIsAdjusting()){
					model.currentCount = s.getValue();
				}
				
			}
		});
		this.add(slider);
		JButton star = new JButton("Star");
		star.addMouseListener(new MouseAdapter() { 
            public void mouseClicked(MouseEvent e) {
            	model.currentCount = 0;
            }
		});
		JButton end = new JButton("End");
		end.addMouseListener(new MouseAdapter() { 
            public void mouseClicked(MouseEvent e) {
            	model.currentCount = model.point.size();
            }
		});
		this.add(star);
		this.add(end);
	}
	public void paintComponent(Graphics g) {
		//setBackground(Color.GRAY);
		Graphics2D g2 = (Graphics2D) g; // cast to get 2D drawing methods
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  // antialiasing look nicer
        					RenderingHints.VALUE_ANTIALIAS_ON);
	}

	// Observer interface 
	@Override
	public void update(Observable o, Object arg) {
		this.slider.setMaximum(model.point.size());
		this.slider.setSize(getWidth()-220, getHeight());
		this.slider.setValue(model.currentCount);
		//repaint();
	}
}