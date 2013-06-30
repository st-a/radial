import processing.core.*;

public class Application extends PApplet {

	PFont f;
	boolean draw = true, s = false;
	Dataset d;

	// used to create font
	PFont myFont;

	public void setup(){
	  size(1000, 700);
	  background (42);
	  d = new Dataset("../src/Data/data.xml");

	  
	}

	public void draw(){
		
		fill(35);
		noStroke();
		rect(700, 0, 300, 700);
  }
}
