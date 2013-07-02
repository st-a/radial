import java.util.ArrayList;

import processing.core.PApplet;

public class sketchSpiralGraph extends PApplet {

	private static final long serialVersionUID = 1L;
	PApplet pa = this;
	public int width = 600;
	public int height = 600;
	public int background = color(42, 42, 42);

	public spiralGraph spiralTom = new spiralGraph(pa, width, height, "Tom");
	public spiralGraph spiralHannes = new spiralGraph(pa, width, height,
			"Hannes");
	public spiralGraph spiralTom1 = new spiralGraph(pa, width, height, "Tom");
	public spiralGraph spiralHannes1 = new spiralGraph(pa, width, height,
			"Hannes");

	boolean viewAsMatrix = false;
	int alpha = 100;

	public ArrayList<spiralGraph> allGraphs = new ArrayList<spiralGraph>();

	public float[] computePositions() {
		float[] rtrn = new float[4 * 3];
		if (!viewAsMatrix) {
			for (int i = 0; i < 4; i++) {
				rtrn[0 + 3 * i] = height / 2;
				rtrn[1 + 3 * i] = height / 2;
				rtrn[2 + 3 * i] = 2 * height / 2 - 50;
			}
		} else {

			if (allGraphs.size() == 1) {
				rtrn[0] = height / 2;
				rtrn[1] = height / 2;
				rtrn[2] = 2 * height / 2 - 50;
			} else {
				for (int i = 0; i < 2; i++) {
					rtrn[1 + i * 3] = height / 4;
					rtrn[0] = height / 4;
					rtrn[2 + i * 3] = 2 * height / 4 - 25;
					rtrn[3] = 3 * height / 4;
				}

				for (int i = 0; i < 2; i++) {
					rtrn[6 + 1 + i * 3] = 3 * height / 4;
					rtrn[6 + 0] = height / 4;
					rtrn[6 + 2 + i * 3] = 2 * height / 4 - 25;
					rtrn[6 + 3] = 3 * height / 4;
				}
			}
		}
		return rtrn;
	}

	public void setup() {
		size(width, height);
		imageMode(CENTER);
		strokeCap(SQUARE);

		viewAsMatrix = false;
		allGraphs.add(spiralHannes);
		allGraphs.add(spiralTom);
		allGraphs.add(spiralHannes1);
		allGraphs.add(spiralTom1);

	}

	public void draw() {
		background(background);

		float[] positions = computePositions();
		
		for (int i = 0; i < allGraphs.size(); i++) {
		
			spiralGraph s = allGraphs.get(i);
			
			if (!viewAsMatrix) {
				s.setAlpha(alpha);
				s.setColors();
			}

			s.setCenterX(positions[0 + 3 * i]);
			s.setCenterY(positions[1 + 3 * i]);
			s.setRadius(positions[2 + 3 * i]);

			s.drawSpiral();
			s.drawClock();
			s.addData();
			// drawContentArround();
		}
	}
}
