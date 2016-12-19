// HelloMVC: a simple MVC example
// the model is just a counter 
// inspired by code by Joseph Mack, http://www.austintek.com/mvc/

/**
 *  Two views with integrated controllers.  Uses java.util.Observ{er, able} instead
 *  of custom IView.
 */

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.File;
import java.io.FileFilter;
import javax.swing.filechooser.*;

public class Main{

	public static void main(String[] args){	
		JFrame frame = new JFrame("Doodle");
		frame.setTitle("Doodle");
		
		//meum
		JMenuItem save = new JMenuItem("save");
		JMenuItem load = new JMenuItem("load");
		JMenuItem exit = new JMenuItem("exit");
		JMenuItem full_size = new JMenuItem("full-size");
		JMenuItem fix = new JMenuItem("fix");
		
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		save.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		          JFileChooser save = new JFileChooser();
		          //save.setfil
		          //save.setDialogTitle("Select to save");
		          int returnValue = save.showOpenDialog(null);
		          if (returnValue == JFileChooser.APPROVE_OPTION) {
		            File selectedFile = save.getSelectedFile();
		            System.out.println(selectedFile.getName());
		          }
		        }
		      });
		
		load.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		          JFileChooser fileChooser = new JFileChooser();
		          int returnValue = fileChooser.showOpenDialog(null);
		          if (returnValue == JFileChooser.APPROVE_OPTION) {
		            File selectedFile = fileChooser.getSelectedFile();
		            System.out.println(selectedFile.getName());
		          }
		        }
		      });
		JMenuBar meum = new JMenuBar();
		JMenu file = new JMenu("File");
		file.add(save);
		file.add(load);
		file.add(exit);
        
		JMenu view = new JMenu("View");
		view.add(full_size);
		view.add(fix);
	
		meum.add(file);
		meum.add(view);
		frame.setJMenuBar(meum);
		
		// create Model and initialize it
		Model model = new Model();
		
		// create View, tell it about model (and controller)
		View view1 = new View(model);
		// tell Model about View. 
		model.addObserver(view1);
		
		// create second view ...
		View2 view2 = new View2(model);
		model.addObserver(view2);
		
		View3 view3 = new View3(model);
		model.addObserver(view3);
		// let all the views know that they're connected to the model
		model.notifyObservers();
		
		// create the window
		//JPanel p = new JPanel(new GridLayout(2,1));
		frame.add(view1,BorderLayout.CENTER);
		frame.add(view2,BorderLayout.WEST);
		frame.add(view3,BorderLayout.SOUTH);
		//frame.getContentPane().add(view2);
		//frame.getContentPane().add(view1);
		//p.add(view);
		
		//p.add(view2);
		
		frame.setPreferredSize(new Dimension(800,1000));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	} 
}
