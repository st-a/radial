import java.util.ArrayList;

import processing.core.PApplet;

public class sketchBundlingEdges{

	PApplet pa;
	public int width = 650;
	public int height = 650;
	public int px = 0;
	public int py = 0;
	public boolean matrix;
	public boolean allInOne = false;
	public int ammountOfPersons = 0;
	public String[] persons = new String[5];
	ArrayList<bundlingEdges> allDiagrams = new ArrayList<bundlingEdges>();

	// constructor
	
	sketchBundlingEdges(PApplet pa, int width, int height, int px, int py,
			String[] persons, boolean allInOne, boolean matrix) {
		this.pa = pa;
		this.width = 650;
		this.height = 600;
		this.px = px;
		this.py = py;
		this.persons = persons;
		this.matrix = matrix;
		this.allInOne = allInOne;
		setup();
		draw();
	}
	
	
	// crap global variables

	public float[] computeDiagrammPositions() {
		float[] rtrn = new float[4 * 3 + 1];

		if (!matrix) {
			rtrn[1] = height / 2;
			rtrn[2] = height / 2;
			rtrn[3] = (height - 200) / 2;
			
		} else {
			for (int j = 0; j < 2; j++) {
				rtrn[1] = height / 4 + 10;
				rtrn[2] = height / 4;
				rtrn[3 * (j + 1)] = (height - 200) / 4;
				rtrn[4] = 3 * height / 4;
				rtrn[5] = height / 4;
			}
			for (int j = 0; j < 2; j++) {
				rtrn[7] = height / 4 + 10;
				rtrn[8] = 3 * height / 4;
				rtrn[9 + j * 3] = (height - 200) / 4;
				rtrn[10] = 3 * height / 4;
				rtrn[11] = 3 * height / 4;
			}
		}
		return rtrn;
	}

	public void setup() {
		// ///////////////////////
		pa.imageMode(PApplet.CENTER);
		pa.strokeCap(PApplet.SQUARE);

		ammountOfPersons = 0;
		if(!matrix&&allInOne&&ammountOfPersons==4){
			for(int i=0;i<persons.length;i++){
				persons[i]=null;
				
			}
			persons[0]="all";
			ammountOfPersons=1;

		}
		
		for (int i = 0; i < persons.length; i++) {
			if (persons[i] != null) {
				ammountOfPersons = ammountOfPersons + 1;
			}
		}

		float[] positions = computeDiagrammPositions();

		for (int i = 0; i < ammountOfPersons; i++) {
			if (persons[i] != null) {
				allDiagrams.add(new bundlingEdges(persons[i],
						positions[1 + 3 * i], positions[2 + 3 * i],
						positions[3 + 3 * i], pa));
				allDiagrams.get(i).handleDate();
			}

		}
	}

	public void draw() {
		// bundling edges
		float[] positions = computeDiagrammPositions();

		// draw all diagrams
		for (int i = 0; i < allDiagrams.size(); i++) {

			bundlingEdges b = allDiagrams.get(i);

			b.setCntrX(positions[1 + 3 * i]+px);
			b.setCntrY(positions[2 + 3 * i]+py);
			b.setRadius(positions[3 + 3 * i]);

			b.resetEverything();
			b.drawBeziers = true;

			b.drawArcsnArrows();
			
			b.drawContentArround();
		}
	}
}
