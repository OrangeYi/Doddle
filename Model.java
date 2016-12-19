import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;

// HelloMVC: a simple MVC example
// the model is just a counter 
// inspired by code by Joseph Mack, http://www.austintek.com/mvc/

public class Model extends Observable {	
	// the data in the model, just a counter
	public Color currentC;
	public int currentS;
	public int currentCount;
	public ArrayList<ArrayList<Point>> point;
	public ArrayList<Point> stock;
	public ArrayList<Color> colour;
	public Boolean play;
	public ArrayList<Point> paintpoints;
	public int controlan;
	Model() {
		controlan = 0;
		play = false;
		currentCount = 0;
		currentS = 10;
		currentC = Color.BLACK;
		stock = new ArrayList<Point>();
		paintpoints = new ArrayList<Point>();
		point = new ArrayList<ArrayList<Point>>();
		colour = new ArrayList<Color>();
		setChanged();
		//buildpaintpoints();
	}
	public void buildpaintpoints(){
		Point p = new Point(0,1);
		this.paintpoints.add(p);
	}
	public void change(){
		setChanged();
		notifyObservers();
	}
}
