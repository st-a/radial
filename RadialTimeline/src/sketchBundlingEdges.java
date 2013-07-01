import java.util.ArrayList;

import processing.core.PApplet;


public class sketchBundlingEdges extends PApplet{

	private static final long serialVersionUID = 1L;
	public int width = 950;
	public int height = 600;
	public int background = color(42, 42, 42);
	
	bundlingEdges beTom = 
			new bundlingEdges("Tom",width / 2 - 100,height / 2,(height - 150) / 2);
	bundlingEdges beHannes = 
			new bundlingEdges("Hannes",width / 2 - 100,height / 2,(height - 150) / 2);
	bundlingEdges beTom1 = 
			new bundlingEdges("Tom",width / 2 - 100,height / 2,(height - 150) / 2);
	bundlingEdges beHannes1 = 
			new bundlingEdges("Hannes",width / 2 - 100,height / 2,(height - 150) / 2);
	
	ArrayList<bundlingEdges> allDiagrams = new ArrayList<bundlingEdges>();
	
	public void drawContentArround(bundlingEdges be) {

		float xPos = 10;
		float yPos = height+5;
		
		stroke(color(20,20,20));
		strokeWeight(1);
		line(10, height, width-10, height);

		for (int i = 0; i < be.places.size(); i++) {
			
			noStroke();

			// Box at the bottom right
			fill(be.places.get(i).backgroundcolor);
			
			rect(xPos+110*i, yPos, 35, 15);
			fill(250);
			
			Float lclprc = be.places.get(i).percentage * 100;
			String prcntg = be.helper.formatPercentage(lclprc);
			noFill();

			be.infoText = createFont("Dialog.plain", 12);
			textFont(be.infoText);
			text(be.places.get(i).name, xPos+47+110*i, yPos+12);

			// percentages in the graph
			be.percenteges = createFont("Dialog.plain", 12*be.scale + 15 * lclprc / 100);
			textFont(be.percenteges);

			float[] percPos = be.helper.degreesToXnY(be,be.places.get(i).middle, be.radius + be.scale*70f);
			text(prcntg, percPos[0] - 20, percPos[1]);
		}
		// headers
		be.headerFont = createFont("Dialog.plain", 22);
		textFont(be.headerFont);

	}

	public void drawElipse(bundlingEdges be) {
		stroke(100);
		strokeWeight(2);
		noFill();
		ellipse(be.centerX, be.centerY, 2 * be.radius, 2 * be.radius);
	}

	public void drawBeziers(bundlingEdges be,int i) {
		float[] outgoings = be.places.get(i).outgoings;
		float arcLengthFrom = be.places.get(i).lenght / 2;

		for (int j = 0; j < be.places.size(); j++) {

			float arcLengthTo = be.places.get(j).lenght / 2;

			stroke(be.places.get(i).backgroundcolor, 60f);
			strokeWeight(1);

			float lengthOfOneColorFrom = outgoings[j] * arcLengthFrom; // in
			float lengthOfOneColorTo = be.places.get(j).incomings[i] * arcLengthTo;
			float rel = lengthOfOneColorTo / lengthOfOneColorFrom;

			for (int k = 0; k < be.densityOfLines * (lengthOfOneColorFrom - 0.3f); k++) {

				float[] start = be.helper.degreesToXnY(be,
						be.places.get(i).start
								+ be.places.get(i).currentLengtgOfOutgoingLinks,
						be.radius);

				float[] end = be.helper.degreesToXnY(be, be.places.get(j).end
						- be.places.get(j).currentLengthOfIncomingLinks, be.radius);

				bezier(start[0], start[1], be.centerX, be.centerY, be.centerX, be.centerY,
						end[0], end[1]);

				be.places.get(i).currentLengtgOfOutgoingLinks = be.places.get(i).currentLengtgOfOutgoingLinks
						+ 1 / be.densityOfLines;
				be.places.get(j).currentLengthOfIncomingLinks = be.places.get(j).currentLengthOfIncomingLinks
						+ rel / be.densityOfLines;
			}
		}
		be.drawBeziers = false;
	}

	public void drawArcsnArrows(bundlingEdges be) {
		// drawArcs
		for (int i = 0; i < be.places.size(); i++) {

			// ########Call the drawBeziers-fnct. for every place#
			if (be.drawBeziers) {
				for (int l = 0; l < be.places.size(); l++) {
					drawBeziers(be,l);
				}
			}

			// Arc, in color
			strokeWeight(be.scale*10f);
			stroke(be.places.get(i).backgroundcolor);
			arc(be.centerX, be.centerY, 2 * (be.radius + 15), 2 * (be.radius + 15),
					radians(be.places.get(i).start - 90),
					radians(be.places.get(i).end - 90));

			// calculating iconpositions and drawing icons
			// no more Icons...icons disabled
			// float[] iconPos = degreesToXnY(places.get(i).middle);
			// image(places.get(i).icon, iconPos[0], iconPos[1]);

			// calculating arrowpositions and drawing/rotating
			float arrowIn = be.places.get(i).start
					+ (be.places.get(i).percentage * (360 - 36)) / 4; // in Degrees
			float arrowOut = be.places.get(i).start + 3
					* (be.places.get(i).percentage * (360 - 36)) / 4;// in Degrees

			float[] arrowPosIn = be.helper.degreesToXnY(be, arrowIn, be.radius - 3);
			float[] arrowPosOut = be.helper.degreesToXnY(be, arrowOut, be.radius + 3);

			float minValue = (be.places.get(i).lenght / 2) / 360f * be.circumference;

			// Draw and rotate Arrows
			noStroke();
			pushMatrix();
			translate(arrowPosOut[0], arrowPosOut[1]);
			rotate(radians(arrowOut - 180));
			translate(-arrowPosOut[0], -arrowPosOut[1]);
			fill(100);
			triangleArrow arrow1 = new triangleArrow(arrowPosOut, minValue);
			triangle(arrow1.p0[0], arrow1.p0[1], arrow1.p1[0], arrow1.p1[1],
					arrow1.p2[0], arrow1.p2[1]);
			popMatrix();

			pushMatrix();
			translate(arrowPosIn[0], arrowPosIn[1]);
			rotate(radians(arrowIn + 0));
			translate(-arrowPosIn[0], -arrowPosIn[1]);
			fill(200);
			triangleArrow arrow0 = new triangleArrow(arrowPosIn, minValue);
			triangle(arrow0.p0[0], arrow0.p0[1], arrow0.p1[0], arrow0.p1[1],
					arrow0.p2[0], arrow0.p2[1]);
			popMatrix();

			// Arc Part One, Outgoing
			stroke(200);
			noFill();
			strokeWeight(be.scale*10f);
			arc(be.centerX, be.centerY, 2 * be.radius, 2 * be.radius,
					radians(be.places.get(i).start - 90),
					radians(be.places.get(i).middle - 90));

			// Arc Part Two, Incoming
			stroke(100);
			arc(be.centerX, be.centerY, 2 * be.radius, 2 * be.radius,
					radians(be.places.get(i).middle - 90),
					radians(be.places.get(i).end - 90));
		}
	}
	
	public float[] computeDiagrammPositions(){
		float[] rtrn = new float[allDiagrams.size()*3];
		int i=1;//this is the size of the raster -> ixi
		int n=0;
		
		//returns the values centerX, centerY and radius of every diagram in
		//allDiagrams. If a new one was added, the diagrams will be rearranged
		
		while(allDiagrams.size()>n){
			n= (int) Math.pow(i, 2);
			if(allDiagrams.size()<=n){
				rtrn[0]=i;
				//i = size of one side
			}
			else{
				i++;
			}
		}
		
		/*
		for(int j=0;j<allDiagrams.size();j++){
			rtrn[3*j+1] = width/i;
			rtrn[(3*j)+2] = height/i;
			rtrn[(3*j)+3] = (height-150)/n;			
		}
		*/
		
		return rtrn;
	}
	
	public void setup() {
		size(width, height+30);
		background(background);
		imageMode(CENTER);
		strokeCap(SQUARE);

		allDiagrams.add(beTom);
		allDiagrams.add(beHannes);
		allDiagrams.add(beTom1);
		allDiagrams.add(beHannes1);
		
		for(bundlingEdges be: allDiagrams){
			be.handleDate();
		}
	}

	public void draw() {
		background(background);
		
		//draw all diagrams
		
		float[] positions = computeDiagrammPositions();
		beHannes.centerX = (height/4+10);
		beHannes.centerY = (height/4);
		beHannes.setRadius((height - 200) / 2 * 1/positions[0]);
		
		beHannes1.centerX = 3*(height/4)+30;
		beHannes1.centerY = (height/4);
		beHannes1.setRadius((height - 200) / 2 * 1/positions[0]);
		
		beTom.centerX = 1*height / 4+10;
		beTom.centerY = 3	*height/4;
		beTom.setRadius((height - 200) / 2 * 1/positions[0]);
		
		beTom1.centerX = 3*height / 4+30;
		beTom1.centerY = 3*height/4;
		beTom1.setRadius((height - 200) / 2 * 1/positions[0]);
		
		for(bundlingEdges b:allDiagrams){
			
			b.resetEverything();
			b.drawBeziers = true;

			drawArcsnArrows(b);
			drawContentArround(b);
		}
	}
}
