import processing.core.PApplet;


public class helperForSpiralGraph extends PApplet{

	private static final long serialVersionUID = 1L;
	
	float radius;
	float centerX;
	float centerY;
	float days;
	float thiknessOfOneDay;
	float scale;
	
	//constructor
	helperForSpiralGraph(float scale,float rad, float cntrX, float cntrY,float days){
		this.radius=rad;
		this.centerX = cntrX;
		this.centerY = cntrY;
		this.days = days;
		this.thiknessOfOneDay = radius / (days + 1);
		this.scale = scale;
	}
	
	//setter getter
	public void setRadius(float r){
		this.radius = r;
	}
	
	public void setScale(float scale){
		this.scale = scale;
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

	public float[] degreesToXnY(float deg, float day,float scale,float[] XY) {
		// this function gets as input only a degree-value and a day and returns
		// the concrete X and Y values in a float-Array
		float localRadius = radius / 2 - day * thiknessOfOneDay;
		if (day == 0) {
			// this is the case for drawing the clock
			localRadius = scale*10f;
		}

		float[] rtrn = new float[2];

		rtrn[0] = XY[0] + cos(radians(-90 + deg)) * (localRadius);
		rtrn[1] = XY[1] + sin(radians(-90 + deg)) * (localRadius);

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
