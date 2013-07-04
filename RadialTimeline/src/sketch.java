import processing.core.PApplet;


public class sketch extends PApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String[] persons = new String[2];
	
	public void setup(){
		background(42);
		size(1000,700);
		persons[0] = "Tom";
		persons[1] = "Hannes";
		
		/*
		sketchBundlingEdges sBE= 
				new sketchBundlingEdges(
						this, 600, 600, 10, 10, persons,false);
		*/
		sketchSpiralGraph spiral = 
				new sketchSpiralGraph(this, 10, 10, 650, 650, true, false, persons);
		
	}
	
	public void draw(){
		
		
	}

}
