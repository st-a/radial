import processing.core.*;


public class MyProcessingSketch extends PApplet {


	int sliderValue = 100;

	
	float px, py;
	float angle = 90;
	PFont f;
	boolean draw = true, s = false;
	Diagramm diagramm;
	Interaktion intera;

	// used to create font
	PFont myFont;

	public void setup(){
	  size(700, 700);
	  background (255);

	 



	  
	  //noLoop();
	  println(PFont.list());
	  f = createFont("Futura-Medium", 16);
	  textFont(f);
	  textAlign(CENTER, CENTER);
	  
	}

	public void draw(){
		
		
		int[][] lines = new int[4][12];
		

		
		int[] distance = {40,45,20,10,58,10,32,25,25,25,25,25};
		
		

		if(draw){
			for (int i=0; i<4;i++){
				for (int j=0; j< 12; j++){
					lines[i][j] = 0 + (int)(Math.random()*4);
				}
			}
			diagramm = new Diagramm(this, width/2, height/2, distance, lines, true, (float) 1);
			draw = false;
			intera = new Interaktion(this, diagramm);
		}
		
		if(intera.getScale()){
			intera.scale();
		}
		  
	}
	
	void slider(float scale) {
		intera.setFrequency(scale);
		intera.setScale(true);
	}
		
		
  public static void main(String args[]) {
	    PApplet.main(new String[] { "--present", "MyProcessingSketch" });
	  }
  
}
