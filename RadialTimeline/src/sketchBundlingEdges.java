import java.util.ArrayList;

import processing.core.PApplet;

public class sketchBundlingEdges extends PApplet {

	private static final long serialVersionUID = 1L;
	
	PApplet pa = this;
	public int width = 650;
	public int height = 600;
	public int background = color(42, 42, 42);

	bundlingEdges beTom = new bundlingEdges("Tom", height / 2 - 100, height / 2,
			(height - 150) / 2,pa);
	bundlingEdges beHannes = new bundlingEdges("Hannes", height/ 2 - 100,
			height / 2, (height - 150) / 2,pa);
	bundlingEdges beAll = new bundlingEdges("all", height / 2 - 100,
			height / 2, (height - 150) / 2,pa);


	ArrayList<bundlingEdges> allDiagrams = new ArrayList<bundlingEdges>();

	public float[] computeDiagrammPositions() {
		float[] rtrn = new float[4 * 3 + 1];
		int i = 1;// this is the size of the raster -> ixi
		int n = 0;

		// returns the values centerX, centerY and radius of every diagram in
		// allDiagrams. If a new one was added, the diagrams will be rearranged

		while (allDiagrams.size() > n) {
			n = (int) Math.pow(i, 2);
			if (allDiagrams.size() <= n) {
				rtrn[0] = i;
				// i = size of one side
			} else {
				i++;
			}
		}

		if (allDiagrams.size() == 1) {
			rtrn[1] = height / 2;
			rtrn[2] = height / 2;
			rtrn[3] = (height - 200) / 2;
		} else {
			for(int j=0;j<2;j++){
				rtrn[1] = height / 4 + 10;
				rtrn[2] = height / 4;
				rtrn[3*(j+1)] = (height - 200) / 2 * 1 /i;
				rtrn[4] = 3*height / 4 + 30;
				rtrn[5] = height / 4;
			}
			for(int j=0;j<2;j++){
				rtrn[7] = height / 4 + 10;
				rtrn[8] = 3* height / 4;
				rtrn[9+j*3] = (height - 200) / 2 * 1 /i;
				rtrn[10] = 3*height / 4 + 30;
				rtrn[11] = 3*height / 4;
			}
		}
		return rtrn;
	}

	public void setup() {
		size(width, height);
		background(background);
		imageMode(CENTER);
		strokeCap(SQUARE);

		allDiagrams.add(beTom);
		allDiagrams.add(beHannes);
		allDiagrams.add(beAll);

		for (bundlingEdges be : allDiagrams) {
			be.handleDate();
		}
	}

	public void draw() {
		background(background);

		//bundling edges
		float[] positions = computeDiagrammPositions();

		// draw all diagrams
		for(int i=0;i<allDiagrams.size();i++){
			
			bundlingEdges b = allDiagrams.get(i);
			
			b.setCntrX(positions[1+3*i]);
			b.setCntrY(positions[2+3*i]);
			b.setRadius(positions[3+3*i]);
			
			b.resetEverything();
			b.drawBeziers = true;

			b.drawArcsnArrows();
			b.drawContentArround();
		}
	}
}
