import java.util.ArrayList;

import processing.core.PApplet;

public class sketchSpiralGraph{ 
//extends PApplet {

	//private static final long serialVersionUID = 1L;
	
	PApplet pa;
	public int px;
	public int py;
	public int width = 650;
	public int height = 650;
	public int alpha;
	public boolean viewAsMatrix = false;
	public boolean viewMovements = false;
	public String[] persons = new String[5];
	public int ammountOfPersons;
	public ArrayList<spiralGraph> allGraphs = new ArrayList<spiralGraph>();
	public int[][] colorsPersons = {{54, 146, 179},{158, 219, 41},{255, 243, 68},{253, 77, 72}};

	sketchSpiralGraph(PApplet pa,int px,int py,int width, int height,
			boolean viewAsMatrix, boolean viewMovements, String[] persons) {
		this.pa = pa;
		this.px = px;
		this.py = py;
		this.width = width;
		this.height = height;
		this.viewAsMatrix = viewAsMatrix;
		this.viewMovements = viewMovements;
		this.persons = persons;
		setup();
		draw();
	}
	
	
	//crap////////////////////////////////////
	//int alpha = 100;
	//public int background = color(42, 42, 42);
	//////////////////////////////////////////

	public float[] computePositions() {
		float[] rtrn = new float[4 * 3];
		
		if (!viewAsMatrix) {
			for (int i = 0; i < 4; i++) {
				rtrn[0 + 3 * i] = height / 2 + px;
				rtrn[1 + 3 * i] = height / 2 + py;
				rtrn[2 + 3 * i] = 2 * height / 2 - 50;
			}
		} else {

			if (allGraphs.size() == 1) {
				rtrn[0] = height / 2 + px;
				rtrn[1] = height / 2 + py;
				rtrn[2] = 2 * height / 2 - 50;
			} else {
				for (int i = 0; i < 2; i++) {
					rtrn[1 + i * 3] = height / 4 + py;
					rtrn[0] = height / 4+px;
					rtrn[2 + i * 3] = 2 * height / 4 - 25;
					rtrn[3] = 3 * height / 4 + py;
				}

				for (int i = 0; i < 2; i++) {
					rtrn[6 + 1 + i * 3] = 3 * height / 4 + py;
					rtrn[6 + 0] = height / 4 + px;
					rtrn[6 + 2 + i * 3] = 2 * height / 4 - 25;
					rtrn[6 + 3] = 3 * height / 4 + px;
				}
			}
		}
		return rtrn;
	}

	public void setup() {
		/////////////////////////
		//size(width, height);
		//persons[0] = "Tom";
		//persons[1] = "Hannes";
		/////////////////////////
		pa.imageMode(PApplet.CENTER);
		pa.strokeCap(PApplet.SQUARE);
		
		ammountOfPersons = 0;
		for (int i = 0; i < persons.length; i++) {
			if (persons[i] != null) {
				ammountOfPersons = ammountOfPersons + 1;
			}
		}

		float[] positions = computePositions();

		for (int i = 0; i < ammountOfPersons; i++) {
			if (persons[i] != null) {
				allGraphs.add(
						new spiralGraph(pa,positions[2 + 3 * i],
								positions[0 + 3 * i],positions[0 + 3 * i],
								persons[i]));
			}

		}
	}

	public void draw() {
		//////////////////////
		//background(background);
		//////////////////////
		
		float[] positions = computePositions();
		
		for (int i = 0; i < allGraphs.size(); i++) {
		
			spiralGraph s = allGraphs.get(i);
			
			if (viewAsMatrix) {
				alpha=255;
				s.setAlpha(alpha);
				s.setColors(viewAsMatrix,colorsPersons[i]);
			}else{
				alpha=128;
				s.setAlpha(alpha);
				s.setColors(viewAsMatrix,colorsPersons[i]);
			}
			
			s.setMovements(viewMovements);

			s.setCenterX(positions[0 + 3 * i]);
			s.setCenterY(positions[1 + 3 * i]);
			s.setRadius(positions[2 + 3 * i]);

			s.drawSpiral();
			s.drawClock();
			s.addData();
		}
	}
}

	
