import processing.core.PApplet;


public class helperForSpiralGraph extends PApplet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	float radius;
	float centerX;
	float centerY;
	float days;
	float thiknessOfOneDay;
	
	
	helperForSpiralGraph(float rad, float cntrX, float cntrY,float days){
		this.radius=rad;
		this.centerX = cntrX;
		this.centerY = cntrY;
		this.days = days;
		this.thiknessOfOneDay = radius / (days + 1);
	}

	public float convertTimeToDegrees(float minutes) {

		float wholeDay = 24 * 60;
		return minutes / wholeDay * 360;
	}

	public float convertDegreesToTime(float degrees) {
		// this function converts a degree value as float into a time value in
		// minutes
		return degrees / 360 * 24 * 60;
	}

	public float[] degreesToXnY(float deg, float day) {
		// this function gets as input only a degree-value and a day and returns
		// the concrete X and Y values in a float-Array
		float localRadius = radius / 2 - day * thiknessOfOneDay;
		if (day == 0) {
			// this is the case for drawing the clock
			localRadius = 25f;
		}

		float[] rtrn = new float[2];

		rtrn[0] = centerX + cos(radians(-90 + deg)) * (localRadius);
		rtrn[1] = centerY + sin(radians(-90 + deg)) * (localRadius);

		return rtrn;
	}
	
	public String formatFloats(float lclprc){
		String prcntg = "0";
		if (lclprc < 100) {
			prcntg = nfs(lclprc, 2, 1) + " %";
		}
		if (lclprc == 100) {
			prcntg = nfs(lclprc, 3, 1) + " %";
		}
		if (lclprc < 10) {
			prcntg = nfs(lclprc, 1, 1) + " %";
		}

		return prcntg;
	}
	
}
