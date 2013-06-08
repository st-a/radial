import java.util.ArrayList;

import javax.swing.text.StyledEditorKit.FontSizeAction;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class bundlingEdges extends PApplet {

	private static final long serialVersionUID = 6838248409653034981L;

	// global variables
	public int width = 800;
	public int height = 600;
	public float centerX = width / 2 - 100;
	public float centerY = height / 2;
	public float radius = (height - 150) / 2;
	public float circumference = 2 * PI * radius;

	public float ammountOfPlaces = 5; // home, Uni, Social, Work, Freetime
	public ArrayList<place> places = new ArrayList<>();

	// independent from the ammount of arcs the free space between
	// the arcs will allways beu 10% of the circumference
	// freeSpace equals 36deg/ammountOfPlaces
	public float freeSpace = (0.1f * circumference) / ammountOfPlaces;
	public float freeSpaceInDegrees = (0.1f * 360) / ammountOfPlaces;

	public boolean drawBeziers = true;
	PImage arrow;
	PFont headerFont;
	PFont infoText;
	PFont percenteges;

	public float[] computeArcs(place plc) {
		// this is the float Array which will be returned by
		// this function
		// it will look like this:
		// rtrn[0] = X-Value of the beginning of the Arc
		// rtrn[1] = Y-Value of the beginning of the Arc
		// rtrn[2] = X-Value of the end of the Arc
		// rtrn[3] = Y-Value of the end of the Arc
		float[] rtrn = new float[4];

		float start = plc.start;
		float end = plc.end;

		rtrn[0] = centerX + cos(radians(start - 90)) * (radius);
		rtrn[1] = centerY + sin(radians(start - 90)) * (radius);
		rtrn[0] = centerX + cos(radians(end - 90)) * (radius);
		rtrn[1] = centerY + sin(radians(end - 90)) * (radius);

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

	public ArrayList<place> getPlaces() {
		return places;
	}

	public void drawContentArround() {

		float xPos = 580;
		float yPos = 400;

		for (int i = 0; i < places.size(); i++) {
			noStroke();

			// Box at the bottom right
			fill(places.get(i).backgroundcolor);
			rect(85 + xPos, 25 + i * 30 + yPos, 40, 25);
			image(places.get(i).icon, 105 + xPos, 37 + i * 30 + yPos);
			fill(0);
			Float lclprc = places.get(i).percentage * 100;
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
			stroke(200);
			strokeWeight(2);
			noFill();
			rect(xPos + 10, yPos + 10, 180, 175);

			infoText = createFont("Dialog.plain", 12);
			textFont(infoText);

			text(prcntg, 130 + xPos, 42 + i * 30 + yPos);
			text(places.get(i).name, 25 + xPos, 42 + i * 30 + yPos);
			// percentages
			percenteges = createFont("Dialog.plain", 10+15*lclprc/100);
			textFont(percenteges);
			
			float[] percPos = degreesToXnY(places.get(i).middle,radius+35);
			
			text(prcntg,percPos[0]-20,percPos[1]);
		}
		// headers
		headerFont = createFont("Dialog.plain", 22);
		textFont(headerFont);
		
	}

	public void drawElipse() {
		stroke(100);
		strokeWeight(2);
		noFill();
		ellipse(centerX, centerY, 2 * radius, 2 * radius);
	}

	public void drawBeziers(int i) {
		noFill();
		float[] outgoings = places.get(i).outgoings;
		float arcLengthFrom = places.get(i).lenght/2;

		for (int j = 0; j < places.size(); j++) {
			
			float arcLengthTo = places.get(j).lenght/2;

			stroke(places.get(i).backgroundcolor);
			noFill();
			strokeWeight(1);

			float lengthOfOneColorFrom = outgoings[j] * arcLengthFrom; // in
			float lengthOfOneColorTo = places.get(j).incomings[i]*arcLengthTo;
			float rel = lengthOfOneColorTo/lengthOfOneColorFrom;

			for (int k = 0; k < lengthOfOneColorFrom-1; k++) {
												
				float[] start = degreesToXnY(places.get(i).start
						+ places.get(i).currentLengtgOfOutgoingLinks,radius);

				float[] end = degreesToXnY(places.get(j).end
						- places.get(j).currentLengthOfIncomingLinks, radius);

				bezier(start[0], start[1], centerX, centerY, centerX, centerY,
						end[0], end[1]);

				places.get(i).currentLengtgOfOutgoingLinks++;
				places.get(j).currentLengthOfIncomingLinks = 
						places.get(j).currentLengthOfIncomingLinks + rel;
			}
		}
		drawBeziers = false;
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

	public void setup() {
		size(width, height);
		background(255);
		arrow = loadImage("Icons/arrow-01.png");

		imageMode(CENTER);
		strokeCap(SQUARE);

		// home, Uni, Social, Work, Freetime
		// add all Places to the Places-ArrayList
		// and handle all the Data
		// TODO ADD DATA HERE, only these fove arraysv

		// test data set
		// float[] outOfHome = { 0, 0.25f, 0.25f, 0.25f, 0.25f };
		// float[] outOfUni = { 0.25f, 0, 0.25f, 0.25f, 0.25f };
		// float[] outOfSocial = { 0.25f, 0.25f, 0, 0.25f, 0.25f };
		// float[] outOfWork = { 0.25f, 0.25f, 0.25f, 0, 0.25f };
		// float[] outOfFreeTime = { 0.25f, 0.25f, 0.25f, 0.25f, 0 };;

		// my data set
		float[] outOfHome = { 1 / 7f, 1 / 7f, 2 / 7f, 5 / 14f, 1 / 14f };
		float[] outOfUni = { 1f, 0f, 0f, 0f, 0f };
		float[] outOfSocial = { 5 / 6f, 0f, 0f, 0f, 1 / 6f };
		float[] outOfWork = { 3 / 5f, 0f, 1 / 5f, 0f, 1 / 5f };
		float[] outOfFreeTime = { 3 / 5f, 0f, 0f, 0f, 2 / 5f };

		float[][] placesAndPercentages = new float[5][5];

		placesAndPercentages[0] = outOfHome;
		placesAndPercentages[1] = outOfUni;
		placesAndPercentages[2] = outOfSocial;
		placesAndPercentages[3] = outOfWork;
		placesAndPercentages[4] = outOfFreeTime;

		float[] percenteges = calculatePercenteges(placesAndPercentages);

		place home = new place(0, "home", percenteges[0], "Icons/iconHome.png");
		home.setColor(color(236, 0, 140));
		home.setSecondColor(color(255, 40, 180));
		home.setIndex(0);
		home.setOutgoings(outOfHome);
		places.add(home);

		place uni = new place(1, "uni", percenteges[1], "Icons/iconUni.png");
		uni.setColor(color(180, 210, 53));
		uni.setSecondColor(color(220, 250, 93));
		uni.setIndex(1);
		uni.setOutgoings(outOfUni);
		places.add(uni);

		place social = new place(2, "social", percenteges[2],
				"Icons/iconSocial.png");
		social.setColor(color(40, 170, 225));
		social.setSecondColor(color(60, 210, 1255));
		social.setIndex(2);
		social.setOutgoings(outOfSocial);
		places.add(social);

		place work = new place(3, "work", percenteges[3], "Icons/iconWork.png");
		work.setColor(color(255, 198, 11));
		work.setSecondColor(color(255, 238, 51));
		work.setIndex(3);
		work.setOutgoings(outOfWork);
		places.add(work);

		place freeTime = new place(4, "freeTime", percenteges[4],
				"Icons/iconBeer.png");
		freeTime.setColor(color(59, 25, 133));
		freeTime.setSecondColor(color(99, 65, 173));
		freeTime.setIndex(4);
		freeTime.setOutgoings(outOfFreeTime);
		places.add(freeTime);

		for (int i = 0; i < ammountOfPlaces; i++) {

			if (i == 0) {
				places.get(i).start = 0;
			} else {
				places.get(i).start = places.get(i - 1).end
						+ freeSpaceInDegrees;
			}
			places.get(i).end = places.get(i).start + places.get(i).percentage
					* (360 - 36);
			places.get(i).middle = places.get(i).start
					+ (places.get(i).percentage * (360 - 36)) / 2;
			places.get(i).lenght = places.get(i).end - places.get(i).start;

		}
		// nearly at least set the incomings to get relative values of the other
		// places
		// it has to be hear because they have to be initialized
		for (int i = 0; i < places.size(); i++) {
			places.get(i).computeIncomings(places);
		}

		drawContentArround();

	}

	public void draw() {

		// background(255);
		// ellipse...no more
		// drawElipse();

		// drawArcs
		for (int i = 0; i < ammountOfPlaces; i++) {

			// bezier curves
			// one curve per degree - so there is a maximum of 324/2 bezier
			// curves

			// This was a trial, maybe I'll use and complete it later
			/*
			 * float[][] outgoing = places.get(i).outgoingLinks;
			 * 
			 * float[] start = degreesToXnY(places.get(0).start
			 * +places.get(0).currentLengtgOfOutgoingLinks); float[] end =
			 * degreesToXnY(places.get(1).end-
			 * +places.get(1).currentLengthOfIncomingLinks);
			 * 
			 * stroke(places.get(i).backgroundcolor); noFill(); strokeWeight(1);
			 * bezier(start[0],start[1], centerX, centerY, centerX, centerY,
			 * end[0], end[1]);
			 */
			if (drawBeziers) {
				for (int l = 0; l < ammountOfPlaces; l++) {
					drawBeziers(l);
				}
			}

			strokeWeight(15);
			// Arc Part One, Outgoing
			noFill();
			stroke(places.get(i).backgroundcolor);
			arc(centerX, centerY, 2 * radius, 2 * radius,
					radians(places.get(i).start - 90),
					radians(places.get(i).middle - 90));

			// Arc Part Two, Incoming
			stroke(places.get(i).secondColor);
			arc(centerX, centerY, 2 * radius, 2 * radius,
					radians(places.get(i).middle - 90),
					radians(places.get(i).end - 90));

			// calculating iconpositions and drawing icons
			// no more Icons...icons disabled
			// float[] iconPos = degreesToXnY(places.get(i).middle);
			// image(places.get(i).icon, iconPos[0], iconPos[1]);

			// calculating arrowpositions and drawing/rotating
			float arrowIn = places.get(i).start
					+ (places.get(i).percentage * (360 - 36)) / 4; // in Degrees
			float arrowOut = places.get(i).start + 3
					* (places.get(i).percentage * (360 - 36)) / 4;// in Degrees

			float[] arrowPosIn = degreesToXnY(arrowIn,radius);
			float[] arrowPosOut = degreesToXnY(arrowOut,radius);

			pushMatrix();
			translate(arrowPosIn[0], arrowPosIn[1]);
			rotate(radians(arrowIn + 90));
			translate(-arrowPosIn[0], -arrowPosIn[1]);
			image(arrow, arrowPosIn[0], arrowPosIn[1]);
			popMatrix();

			pushMatrix();
			translate(arrowPosOut[0], arrowPosOut[1]);
			rotate(radians(arrowOut - 90));
			translate(-arrowPosOut[0], -arrowPosOut[1]);
			image(arrow, arrowPosOut[0], arrowPosOut[1]);
			popMatrix();
			
			
			noLoop();

		}
	}
}