import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;

public class bundlingEdges extends PApplet {

	private static final long serialVersionUID = 6838248409653034981L;

	// global variables

	PApplet pa;
	public PFont legend;

	public float centerX;
	public float centerY;
	public float radius;
	public float scale = 1f;
	public String[] person;

	public float circumference;
	public float alpha;
	public float densityOfLines;

	public float ammountOfPlaces; // home, Uni, Social, Work, Freetime

	helperForBundlingEdges helper;

	public ArrayList<place> places = new ArrayList<place>();

	public String[] namesOfPlaces;

	// independent from the ammount of arcs the free space between
	// the arcs will allways beu 10% of the circumference
	// freeSpace equals 36deg/ammountOfPlaces
	public float freeSpace;
	public float freeSpaceInDegrees;

	public boolean drawBeziers;
	PFont headerFont;
	PFont infoText;
	PFont percenteges;

	// constructor
	bundlingEdges(String[] Person, float cntrX, float cntrY, float radius,
			PApplet pa) {
		this.person = Person;
		this.centerX = cntrX;
		this.centerY = cntrY;
		this.radius = radius;
		this.pa = pa;

		circumference = 2 * PI * radius;
		alpha = 60f;
		densityOfLines = 10f;

		ammountOfPlaces = 5; // home, Uni, Social, Work, Freetime

		helper = new helperForBundlingEdges(centerX, centerY, radius,
				ammountOfPlaces);

		namesOfPlaces = new String[5];

		// independent from the ammount of arcs the free space between
		// the arcs will allways beu 10% of the circumference
		// freeSpace equals 36deg/ammountOfPlaces
		freeSpace = (0.1f * circumference) / ammountOfPlaces;
		freeSpaceInDegrees = (0.1f * 360) / ammountOfPlaces;

		drawBeziers = true;
		legend = createFont("../src/typo/OpenSans-Light.ttf", 12);

	}

	// setter + getter

	public void setCntrX(float cntrX) {
		this.centerX = cntrX;
		this.helper.setCntrX(cntrX);
	}

	public void setCntrY(float cntrY) {
		this.centerY = cntrY;
		this.helper.setCntrY(cntrY);
	}

	public void setRadius(float rad) {
		this.radius = rad;
		scale = rad / 200;
		densityOfLines = scale * 10f;
	}

	// relevant functions
	public void drawContentArround() {

		pa.stroke(color(20, 20, 20));
		pa.strokeWeight(1);
		pa.line(10, height, width - 10, height);

		for (int i = 0; i < places.size(); i++) {

			pa.noStroke();

			Float lclprc = places.get(i).percentage * 100;
			String prcntg = helper.formatPercentage(lclprc);

			// percentages in the graph
			// percenteges = createFont("Dialog.plain", 12 * scale + 15 * lclprc
			// / 100);
			pa.textFont(legend);

			pa.textSize(12 * scale + 15 * lclprc / 100);
			float[] percPos = helper.degreesToXnY(places.get(i).middle, radius
					+ scale * 70f - 30f * 1 / lclprc);
			pa.text(prcntg, percPos[0] - 20, percPos[1]);
		}
	}
	
	public void drawKey(){
		for(place pl : places){
			pa.fill(pl.backgroundcolor);
			pa.rect(40+120*pl.index,640,10,40);
			pa.fill(255);
			pa.textFont(legend);
			pa.textSize(12);
			pa.text(pl.name, 55+120*pl.index, 653);
		}
	}

	public void drawLabels(String pers,boolean OneOfTheDiagramsOnTheRight) {
		float plusX = 0f;
		
		if(OneOfTheDiagramsOnTheRight){
			plusX = 15f;
		}
		pa.fill(255);
		pa.strokeWeight(1);
		pa.stroke(255);
		pa.line(centerX - radius - 50-plusX, centerY - radius - 40, centerX - radius
				- 50-plusX, centerY - radius-20);
		// pa.line(centerX-radius, centerY-radius, centerX-radius,
		// centerY-radius+100);
		pa.textFont(legend);
		pa.textSize(18f);
		pa.text(pers, centerX - radius-35-plusX, centerY - radius-25);
	}

	public void drawElipse(bundlingEdges be) {
		stroke(100);
		strokeWeight(2);
		noFill();
		ellipse(centerX, centerY, 2 * radius, 2 * radius);
	}

	public void drawBeziers(int i) {
		pa.noFill();
		float[] outgoings = places.get(i).outgoings;
		float arcLengthFrom = places.get(i).lenght / 2;

		for (int j = 0; j < places.size(); j++) {

			float arcLengthTo = places.get(j).lenght / 2;

			pa.stroke(places.get(i).backgroundcolor, 60f);
			pa.strokeWeight(1);

			float lengthOfOneColorFrom = outgoings[j] * arcLengthFrom; // in
			float lengthOfOneColorTo = places.get(j).incomings[i] * arcLengthTo;
			float rel = lengthOfOneColorTo / lengthOfOneColorFrom;

			for (int k = 0; k < densityOfLines * (lengthOfOneColorFrom - 0.3f); k++) {

				float[] start = helper.degreesToXnY(

				places.get(i).start
						+ places.get(i).currentLengtgOfOutgoingLinks, radius);

				float[] end = helper.degreesToXnY(
						places.get(j).end
								- places.get(j).currentLengthOfIncomingLinks,
						radius);

				pa.bezier(start[0], start[1], centerX, centerY, centerX,
						centerY, end[0], end[1]);

				places.get(i).currentLengtgOfOutgoingLinks = places.get(i).currentLengtgOfOutgoingLinks
						+ 1 / densityOfLines;
				places.get(j).currentLengthOfIncomingLinks = places.get(j).currentLengthOfIncomingLinks
						+ rel / densityOfLines;
			}
		}
		drawBeziers = false;
	}

	public void drawArcsnArrows() {
		// drawArcs
		for (int i = 0; i < places.size(); i++) {

			// ########Call the drawBeziers-fnct. for every place#
			if (drawBeziers) {
				for (int l = 0; l < places.size(); l++) {
					drawBeziers(l);
				}
			}

			// Arc, in color
			pa.strokeWeight(scale * 10f);
			pa.stroke(places.get(i).backgroundcolor);
			pa.arc(centerX, centerY, 2 * (radius + scale * 15),
					2 * (radius + scale * 15),
					radians(places.get(i).start - 90),
					radians(places.get(i).end - 90));

			// calculating iconpositions and drawing icons
			// no more Icons...icons disabled
			// float[] iconPos = degreesToXnY(places.get(i).middle);
			// image(places.get(i).icon, iconPos[0], iconPos[1]);

			// calculating arrowpositions and drawing/rotating
			float arrowIn = places.get(i).start
					+ (places.get(i).percentage * (360 - 36)) / 4; // in
																	// Degrees
			float arrowOut = places.get(i).start + 3
					* (places.get(i).percentage * (360 - 36)) / 4;// in
																	// Degrees

			float[] arrowPosIn = helper.degreesToXnY(arrowIn, radius - 3);
			float[] arrowPosOut = helper.degreesToXnY(arrowOut, radius + 3);

			float minValue = 0f;

			if (radius <= 100) {
				minValue = 4f;
			} else {
				minValue = 8f;
			}

			// (places.get(i).lenght / 2) / 360f * scale
			// * circumference;

			// Draw and rotate Arrows
			pa.noStroke();
			pa.pushMatrix();
			pa.translate(arrowPosOut[0], arrowPosOut[1]);
			pa.rotate(radians(arrowOut - 180));
			pa.translate(-arrowPosOut[0], -arrowPosOut[1]);
			pa.fill(100);
			triangleArrow arrow1 = new triangleArrow(arrowPosOut, minValue);

			pa.triangle(arrow1.p0[0], arrow1.p0[1], arrow1.p1[0], arrow1.p1[1],
					arrow1.p2[0], arrow1.p2[1]);
			pa.popMatrix();

			pa.pushMatrix();
			pa.translate(arrowPosIn[0], arrowPosIn[1]);
			pa.rotate(radians(arrowIn + 0));
			pa.translate(-arrowPosIn[0], -arrowPosIn[1]);
			pa.fill(200);
			triangleArrow arrow0 = new triangleArrow(arrowPosIn, minValue);
			pa.triangle(arrow0.p0[0], arrow0.p0[1], arrow0.p1[0], arrow0.p1[1],
					arrow0.p2[0], arrow0.p2[1]);
			pa.popMatrix();

			// Arc Part One, Outgoing
			pa.stroke(200);
			pa.noFill();
			pa.strokeWeight(scale * 10f);
			pa.arc(centerX, centerY, 2 * radius, 2 * radius,
					radians(places.get(i).start - 90),
					radians(places.get(i).middle - 90));

			// Arc Part Two, Incoming
			pa.stroke(100);
			pa.arc(centerX, centerY, 2 * radius, 2 * radius,
					radians(places.get(i).middle - 90),
					radians(places.get(i).end - 90));
		}
	}

	public void handleDate() {

		namesOfPlaces[0] = "Home";
		namesOfPlaces[1] = "Uni";
		namesOfPlaces[2] = "Social";
		namesOfPlaces[3] = "Work";
		namesOfPlaces[4] = "Freizeit";

		// test data set
		float[] outOfHome = { 0f, 0f, 0f, 0f, 0f };
		float[] outOfUni = { 0f, 0f, 0f, 0f, 0f };
		float[] outOfSocial = { 0f, 0f, 0f, 0f, 0f };
		float[] outOfWork = { 0f, 0f, 0f, 0f, 0f };
		float[] outOfFreeTime = { 0f, 0f, 0f, 0f, 0f };

		Dataset dtst = new Dataset("../src/Data/data.xml");

		Activity[] allActivities = null;
		if (person.length == 1) {
			allActivities = dtst.getPersonActivities(person[0]);
		} else {
			int length = 0;
			for (int i = 0; i < person.length; i++) {
				length = length + dtst.getPersonActivities(person[i]).length;
			}
			Activity[] helperArray = new Activity[length];

			int index = 0;
			for (int j = 0; j < person.length; j++) {
				for (int k = 0; k < dtst.getPersonActivities(person[j]).length; k++) {
					helperArray[index] = dtst.getPersonActivities(person[j])[k];
					index = index + 1;
				}
			}

			allActivities = helperArray;

		}

		// my data set
		/*
		 * float[] outOfHome = { 1 / 7f, 1 / 7f, 2 / 7f, 5 / 14f, 1 / 14f };
		 * float[] outOfUni = { 1f, 0f, 0f, 0f, 0f }; float[] outOfSocial = { 5
		 * / 6f, 0f, 0f, 0f, 1 / 6f }; float[] outOfWork = { 3 / 5f, 0f, 1 / 5f,
		 * 0f, 1 / 5f }; float[] outOfFreeTime = { 3 / 5f, 0f, 0f, 0f, 2 / 5f };
		 */

		float[][] placesAndPercentages = new float[5][5];

		placesAndPercentages[0] = outOfHome;
		placesAndPercentages[1] = outOfUni;
		placesAndPercentages[2] = outOfSocial;
		placesAndPercentages[3] = outOfWork;
		placesAndPercentages[4] = outOfFreeTime;

		for (int i = 1; i < allActivities.length; i++) {
			Activity act1 = allActivities[i];
			Activity act0 = allActivities[i - 1];

			int index0 = 1;
			int index1 = 1;

			for (int j = 0; j < 5; j++) {
				if (act0 != null & act1 != null) {
					if (act0.catagory.equals(namesOfPlaces[j])) {
						index1 = j;
					}
					if (act1.catagory.equals(namesOfPlaces[j])) {
						index0 = j;
					}
				}
			}
			placesAndPercentages[index1][index0] += 1f;
		}

		for (int i = 0; i < 5; i++) {
			float sumOfOne = 0;
			for (int j = 0; j < 5; j++) {
				sumOfOne += placesAndPercentages[i][j];
			}
			for (int j = 0; j < 5; j++) {
				placesAndPercentages[i][j] = placesAndPercentages[i][j]
						/ sumOfOne;
			}
		}

		float[] percenteges = helper.calculatePercenteges(placesAndPercentages);

		place home = new place(0, "Home", percenteges[0],
				"../src/Icons/iconHome.png");
		home.setColor(color(255, 123, 106));
		home.setIndex(0);
		home.setOutgoings(outOfHome);
		this.places.add(home);

		place uni = new place(1, "Uni", percenteges[1],
				"../src/Icons/iconUni.png");
		uni.setColor(color(255, 242, 190));
		uni.setIndex(1);
		uni.setOutgoings(outOfUni);
		this.places.add(uni);

		place social = new place(2, "Social", percenteges[2],
				"../src/Icons/iconSocial.png");
		social.setColor(color(170, 235, 140));
		social.setIndex(2);
		social.setOutgoings(outOfSocial);
		this.places.add(social);

		place work = new place(3, "Work", percenteges[3],
				"../src/Icons/iconWork.png");
		work.setColor(color(53, 189, 144));
		work.setIndex(3);
		work.setOutgoings(outOfWork);
		this.places.add(work);

		place freeTime = new place(4, "Freetime", percenteges[4],
				"../src/Icons/iconFreetime.png");
		freeTime.setColor(color(0, 150, 163));
		freeTime.setIndex(4);
		freeTime.setOutgoings(outOfFreeTime);
		this.places.add(freeTime);

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
		// it has to be here because they have to be initialized
		for (int i = 0; i < places.size(); i++) {
			places.get(i).computeIncomings(places);
		}
	}

	public void resetEverything() {
		// Resetting everything that was changed
		for (int k = 0; k < ammountOfPlaces; k++) {
			places.get(k).currentLengtgOfOutgoingLinks = 0f;
			places.get(k).currentLengthOfIncomingLinks = 0.5f;
		}
	}
}