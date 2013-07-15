import processing.core.PApplet;

public class sketch extends PApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String[] persons = new String[4];
	
	public void setup(){
		background(42);
		size(1000,700);
		persons[0] = "Sophia";

		
		
		sketchBundlingEdges sBE= 
				new sketchBundlingEdges(
						this, 600, 600, 10, 10, persons,false);
		
		/*
		sketchSpiralGraph spiral = 
				new sketchSpiralGraph(this, 10, 10, 650, 650, true, false, persons);
		*/
	}
}
