import processing.core.PApplet;


public class helperForBundlingEdges extends PApplet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	float centerX;
	float centerY;
	float radius;
	float ammountOfPlaces;
	
	public helperForBundlingEdges(float centerX,float centerY,float radius,	float ammountOfPlaces) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.radius = radius;
		this.ammountOfPlaces = ammountOfPlaces;
	}
	
	public void setCntrX(float cntrX){
		this.centerX = cntrX;
	}
	
	public void setCntrY(float cntrY){
		this.centerY = cntrY;
	}

	public String formatPercentage(float prc) {
		String rtrn = " ";
		if (prc < 100) {
			rtrn = nfs(prc, 2, 1) + " %";
		}
		if (prc == 100) {
			rtrn = nfs(prc, 3, 1) + " %";
		}
		if (prc < 10) {
			rtrn = nfs(prc, 1, 1) + " %";
		}
		return rtrn;
	}

	public float[] degreesToXnY(float deg, float radius) {
		// this function gets as input only a degree-value and returns the
		// concrete
		// X and Y values in a float-Array
		float[] rtrn = new float[2];

		rtrn[0] = centerX + cos(radians(deg - 90)) * (radius);
		rtrn[1] = centerY + sin(radians(deg - 90)) * (radius);

		return rtrn;
	}
	
	public float[] calculatePercenteges(float[][] perc) {
		float[] rtrn = new float[(int) ammountOfPlaces];

		for (int i = 0; i < ammountOfPlaces; i++) {
			for (int j = 0; j < ammountOfPlaces; j++) {
				rtrn[j] += perc[i][j];
			}
		}
		for (int k = 0; k < ammountOfPlaces; k++) {
			rtrn[k] = rtrn[k] / ammountOfPlaces;
		}

		return rtrn;
	}

}
