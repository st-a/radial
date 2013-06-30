import processing.core.*;

public class Application extends PApplet {

	PFont interfaceHealines = createFont("../src/typo/OpenSans-Regular.ttf", 18);
	PFont legendenText = createFont("../src/typo/OpenSans-Light.ttf", 12);
	PFont normalText = createFont("../src/typo/OpenSans-Light.ttf", 20);
	PFont Headlines = createFont("../src/typo/OpenSans-Light.ttf", 28);
	boolean draw = true, s = false;
	Dataset d;

	public void setup(){
	  size(1000, 700);
	  background (42);
	  d = new Dataset("../src/data/data.xml");
	// The font must be located in the sketch's 
	// "data" directory to load successfully
	  
	  fill(35);
	  noStroke();
	  rect(700, 0, 300, 700);
		
	  fill(255);
	  
	  textFont(this.interfaceHealines);
	  text("Visualisierungen", 740, 3*20);
	  stroke(255);
	  strokeWeight(1);
	  line(740,4*20, 740,8*20);
	  text("Personen", 740, 14*20);
	  line(740,15*20, 740,19*20);
	  text("Tag", 740, 24*20);
	  line(740,25*20, 740,29*20);
	}

	public void draw(){
		

  }
}
