import processing.core.*;


public class MyProcessingSketch extends PApplet {


	int sliderValue = 100;

	
	float px, py;
	PFont f;
	boolean draw = true, s = false;
	Diagramm diagramm;
	Dataset d;

	// used to create font
	PFont myFont;

	public void setup(){
	  size(1000, 700);
	  background (42);

	 


	  d = new Dataset("../src/Data/data.xml");

	  //noLoop();
	  f = createFont("Futura-Medium", 16);
	  textFont(f);
	  textAlign(CENTER, CENTER);
	  
	}

	public void draw(){
		
		int[] distance = {40,45,20,10,58,10,32,25,25,25,25,25};
		
		

		if(draw){
			draw = false;
			
		}
		
		  
	}
	

		
		
  public static void main(String args[]) {
	    PApplet.main(new String[] { "--present", "MyProcessingSketch" });
	  }
  
}
