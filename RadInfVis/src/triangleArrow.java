import processing.core.PApplet;


public class triangleArrow extends PApplet{
	
	private static final long serialVersionUID = 7154513646954860917L;
	
	float[] center;
	float[] p0 = new float[2];
	float[] p1 = new float[2];
	float[] p2 = new float[2];
	
	public triangleArrow(float[] cntr,float min) {
		float smaller = 10f;
		
		if (min<=20){
			println("min: " + min);
			smaller = smaller-min/10f;
		}
		this.center = cntr;
		p0[0] = center[0]-smaller;
		p0[1] = center[1];
		p1[0] = center[0]+smaller;
		p1[1] = center[1];
		p2[0] = center[0];
		p2[1] = center[1]+8f;
	
	}


}
