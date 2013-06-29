import processing.core.*;


public class MyProcessingSketch extends PApplet {


	int sliderValue = 100;

	
	float px, py;
	float angle = 90;
	PFont f;
	boolean draw = true, s = false;
	Diagramm diagramm;
	Interaktion intera;
	Dataset d;

	// used to create font
	PFont myFont;

	public void setup(){
	  size(1000, 700);
	  background (42);

	 


	  d = new Dataset("../src/Data/data.xml");
	/*  
	  Activity[] a = d.getActivityByDayAndPerson("Dienstag", "Tom");
	  
	  for(int i=0; i< a.length; i++){
		  println(a[i].getDay());
		  println("Start: " + a[i].start);
		  println("Ende: " + a[i].end);
		  
	  }*/
	  
	  
	  
	  //noLoop();
	  f = createFont("Futura-Medium", 16);
	  textFont(f);
	  textAlign(CENTER, CENTER);
	  
	}

	public void draw(){
		
		
		int[][] lines = new int[4][12];
		

		
		int[] distance = {40,45,20,10,58,10,32,25,25,25,25,25};
		
		

		if(draw){
			diagramm = new Diagramm(this, width/2, height/2, distance,this.d, (float) 1);
			diagramm.draw(width/2, height/2, d.getPerson("Tom"), "Sonntag", 1f);
			draw = false;
			
		}
		
		  
	}
	

		
		
  public static void main(String args[]) {
	    PApplet.main(new String[] { "--present", "MyProcessingSketch" });
	  }
  
}
