import java.util.ArrayList;

import processing.core.PApplet;


public class sketchSpiralGraph extends PApplet {

	private static final long serialVersionUID = 1L;
	PApplet pa = this;
	public int width = 600;
	public int height = 600;
	public int background = color(42, 42, 42);
	
	public spiralGraph spiralTom = new spiralGraph(pa, width, height, "Tom");
	public spiralGraph spiralHannes = new spiralGraph(pa, width, height, "Hannes");
	
	public ArrayList<spiralGraph> allGraphs =
			new ArrayList<spiralGraph>();
	
	public void setup() {
		size(width, height);
		imageMode(CENTER);
		strokeCap(SQUARE);
		
		allGraphs.add(spiralTom);
		allGraphs.add(spiralHannes);
	}

	public void draw() {
		background(background);
		
		
		allGraphs.get(1).drawSpiral();
		allGraphs.get(1).drawClock();
		allGraphs.get(1).addData();
		//drawContentArround();
	}
}
