import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class spiralGraph extends PApplet {

	private static final long serialVersionUID = 1L;

	public int width = 900;
	public int height = 700;
	public float centerX = width / 2 - 100;
	public float centerY = height / 2;
	public float radius = (height - 50); // this is obviously crap but i cant
											// refactor it
	public float circumference = 2 * PI * radius;

	public float days = 7 + 1; // the '+1' is added to make sure, ther's
								// enough white space in the middle
	public float thiknessOfOneDay = radius / (days + 1);

	public int background = color(255, 255, 255);
	public int homeColor = color(236, 0, 140);
	public int uniColor = color(180, 210, 53);
	public int socialColor = color(40, 170, 225);
	public int workColor = color(255, 198, 11);
	public int freeTimeColor = color(59, 25, 133);

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

		println("deg: " + deg);
		println("radius: " + radius);
		println("center: " + centerX + ", " + centerY);

		rtrn[0] = centerX + cos(radians(-90 + deg)) * (localRadius);
		rtrn[1] = centerY + sin(radians(-90 + deg)) * (localRadius);

		return rtrn;
	}

	public void drawContentArround() {

		float xPos = 690;
		float yPos = 500;

		noStroke();

		// home = 72.5%
		// work = 10.4%
		// social = 6%
		// freeTime = 8,7%
		// uni = 2.4%

		// Box at the bottom right

		// home
		fill(homeColor);
		rect(85 + xPos, 25 + 0 * 30 + yPos, 40, 25);
		PImage homeIcon = loadImage("../src/Icons/iconHome.png");
		image(homeIcon, 105 + xPos, 37 + 0 * 30 + yPos);
		fill(0);
		Float lclprc = 72.5f;
		String prcntg = "0";
		if (lclprc < 100) {
			prcntg = nfs(lclprc, 2, 1) + " %";
		} else {
			prcntg = nfs(lclprc, 3, 1) + " %";
		}
		stroke(200);
		strokeWeight(2);
		noFill();
		rect(xPos + 10, yPos + 10, 180, 175);

		PFont infoText = createFont("Dialog.plain", 12);
		textFont(infoText);

		text(prcntg, 130 + xPos, 42 + 0 * 30 + yPos);
		text("home", 25 + xPos, 42 + 0 * 30 + yPos);

		// work
		fill(workColor);
		noStroke();
		rect(85 + xPos, 25 + 2 * 30 + yPos, 40, 25);
		PImage workIcon = loadImage("../src/Icons/iconWork.png");
		image(workIcon, 105 + xPos, 37 + 2 * 30 + yPos);
		fill(0);
		lclprc = 10.4f;
		prcntg = "0";
		if (lclprc < 100) {
			prcntg = nfs(lclprc, 2, 1) + " %";
		}
		if (lclprc == 100) {
			prcntg = nfs(lclprc, 3, 1) + " %";
		}
		if (lclprc < 10) {
			prcntg = nfs(lclprc, 1, 1) + " %";
		}

		text(prcntg, 130 + xPos, 42 + 2 * 30 + yPos);
		text("work", 25 + xPos, 42 + 2 * 30 + yPos);

		// freeTime
		fill(freeTimeColor);
		noStroke();
		rect(85 + xPos, 25 + 1 * 30 + yPos, 40, 25);
		PImage freeTimeIcon = loadImage("../src/Icons/iconBeer.png");
		image(freeTimeIcon, 105 + xPos, 37 + 1 * 30 + yPos);
		fill(0);
		lclprc = 8.7f;
		prcntg = "0";
		if (lclprc < 100) {
			prcntg = nfs(lclprc, 2, 1) + " %";
		}
		if (lclprc == 100) {
			prcntg = nfs(lclprc, 3, 1) + " %";
		}
		if (lclprc < 10) {
			prcntg = nfs(lclprc, 1, 1) + " %";
		}

		text(prcntg, 130 + xPos, 42 + 1 * 30 + yPos);
		text("free time", 25 + xPos, 42 + 1 * 30 + yPos);

		// Uni
		fill(uniColor);
		noStroke();
		rect(85 + xPos, 25 + 3 * 30 + yPos, 40, 25);
		PImage uniIcon = loadImage("../src/Icons/iconUni.png");
		image(uniIcon, 105 + xPos, 37 + 3 * 30 + yPos);
		fill(0);
		lclprc = 2.4f;
		prcntg = "0";
		if (lclprc < 100) {
			prcntg = nfs(lclprc, 2, 1) + " %";
		}
		if (lclprc == 100) {
			prcntg = nfs(lclprc, 3, 1) + " %";
		}
		if (lclprc < 10) {
			prcntg = nfs(lclprc, 1, 1) + " %";
		}

		text(prcntg, 130 + xPos, 42 + 3 * 30 + yPos);
		text("uni", 25 + xPos, 42 + 3 * 30 + yPos);
		// social
		fill(socialColor);
		rect(85 + xPos, 25 + 4 * 30 + yPos, 40, 25);
		PImage socialIcon = loadImage("../src/Icons/iconSocial.png");
		image(socialIcon, 105 + xPos, 37 + 4 * 30 + yPos);
		fill(0);
		lclprc = 6f;
		prcntg = "0";
		if (lclprc < 100) {
			prcntg = nfs(lclprc, 2, 1) + " %";
		}
		if (lclprc == 100) {
			prcntg = nfs(lclprc, 3, 1) + " %";
		}
		if (lclprc < 10) {
			prcntg = nfs(lclprc, 1, 1) + " %";
		}

		text(prcntg, 130 + xPos, 42 + 4 * 30 + yPos);
		text("social", 25 + xPos, 42 + 4 * 30 + yPos);
	}

	public void drawClock() {
		// drawing the 24 grey-scaled lines
		fill(100);
		strokeWeight(1);
		stroke(100);
		line(centerX - radius / 2, centerY, centerX + radius / 2 + 10, centerY);
		line(centerX, centerY - radius / 2 - 10, centerX, centerY + radius / 2
				+ 10);

		pushMatrix();
		stroke(200);
		translate(centerX, centerY);
		rotate(radians(30));
		translate(-centerX, -centerY);
		line(centerX - radius / 2, centerY, centerX + radius / 2 + 10, centerY);
		line(centerX, centerY - radius / 2 - 10, centerX, centerY + radius / 2
				+ 10);
		popMatrix();

		for (int i = 0; i < 6; i++) {
			pushMatrix();
			stroke(255);
			strokeWeight(1.5f);
			translate(centerX, centerY);
			rotate(radians(i * 30 + 15));
			translate(-centerX, -centerY);
			line(centerX - radius / 2, centerY, centerX + radius / 2 + 10,
					centerY);
			popMatrix();
		}

		pushMatrix();
		stroke(200);
		translate(centerX, centerY);
		rotate(radians(60));
		translate(-centerX, -centerY);
		line(centerX - radius / 2, centerY, centerX + radius / 2 + 10, centerY);
		line(centerX, centerY - radius / 2 - 10, centerX, centerY + radius / 2
				+ 10);
		popMatrix();

		fill(255);
		noStroke();
		ellipse(centerX, centerY, 100, 100);

		rectMode(CENTER);
		PFont infoText = createFont("Dialog.plain", 13);
		textFont(infoText);
		for (int i = 0; i < 12; i++) {
			float[] pos = degreesToXnY(i * 360 / 12, 0);
			fill(50);
			pushMatrix();
			translate(pos[0], pos[1]);
			rotate(radians((float) i / 12 * 360));
			translate(-pos[0], -pos[1]);
			rect(pos[0], pos[1], 1.5f, 7);
			popMatrix();
			// ellipse(pos[0],pos[1],20,20);

		}

		text("0", centerX - 4, centerY - 32);
		text("12", centerX - 8, centerY + 42);
		text("18", centerX - 48, centerY + 4);
		text("6", centerX + 30, centerY + 6);
	}

	public void drawSpiral() {

		strokeWeight(1);
		stroke(100);
		noFill();
		float[] pointOne = new float[2];
		float[] pointTwo = new float[2];
		float[] pointThree = new float[2];
		float[] pointFour = new float[2];

		pointOne[0] = centerX;
		pointOne[1] = centerY - radius;
		pointTwo[0] = centerX + radius - thiknessOfOneDay / 4;
		pointTwo[1] = centerY;
		pointThree[0] = centerX;
		pointThree[1] = centerY + radius - thiknessOfOneDay / 2;
		pointFour[0] = centerX - radius + 3 * thiknessOfOneDay / 4;
		pointFour[1] = centerY;

		float localRadius;

		for (int i = 0; i < days + 1; i++) {
			if (i < days - 1) {

				/*
				 * localRadius = (radius-i*thiknessOfOneDay)/2; //draw spiral as
				 * 4 segments as bezier curves bezier(pointOne[0],
				 * pointOne[1]+i*thiknessOfOneDay, pointOne[0]+localRadius,
				 * pointOne[1]+i*thiknessOfOneDay,
				 * pointTwo[0]-i*thiknessOfOneDay,pointTwo[1]-localRadius,
				 * pointTwo[0]-i*thiknessOfOneDay,pointTwo[1]);
				 * 
				 * //localRadius -=thiknessOfOneDay/4;
				 * 
				 * bezier(pointTwo[0]-i*thiknessOfOneDay,pointTwo[1],
				 * pointTwo[0]-i*thiknessOfOneDay,pointTwo[1]+ localRadius,
				 * pointThree[0]+localRadius, pointThree[1]-i*thiknessOfOneDay,
				 * pointThree[0], pointThree[1]-i*thiknessOfOneDay);
				 * 
				 * //localRadius -=thiknessOfOneDay/4;
				 * 
				 * bezier(pointThree[0],pointThree[1]-i*thiknessOfOneDay,
				 * pointThree[0] - localRadius,pointThree[1]-i*thiknessOfOneDay,
				 * pointFour[0]+i*thiknessOfOneDay, pointFour[1]+localRadius,
				 * pointFour[0]+i*thiknessOfOneDay, pointFour[1]);
				 * 
				 * localRadius -=thiknessOfOneDay/2;
				 * 
				 * bezier(pointFour[0]+i*thiknessOfOneDay,pointFour[1],
				 * pointFour[0] + i*thiknessOfOneDay,pointFour[1]-localRadius,
				 * pointOne[0]-localRadius, pointOne[1]+(i+1)*thiknessOfOneDay,
				 * pointOne[0], pointOne[1]+(i+1)*thiknessOfOneDay);
				 */

				// drawing the spiral using arcs

				localRadius = (radius - i * thiknessOfOneDay);
				strokeWeight(1);
				fill(240);
				// arc1

				arc(centerX, centerY, localRadius - thiknessOfOneDay / 4,
						localRadius, radians(-90), radians(0));
				// arc2
				arc(centerX, centerY, localRadius - thiknessOfOneDay / 4,
						localRadius - thiknessOfOneDay / 2, radians(0),
						radians(90));
				// arc3
				arc(centerX, centerY, localRadius - 3 * thiknessOfOneDay / 4,
						localRadius - thiknessOfOneDay / 2, radians(90),
						radians(180));
				// arc4
				arc(centerX, centerY, localRadius - 3 * thiknessOfOneDay / 4,
						localRadius - thiknessOfOneDay, radians(180),
						radians(270));

			} else {

				// this is for blending the center
				noStroke();
				noFill();
			}
		}
	}

	public void addTimeSlot(int color, float day, int time, float duration) {
		// this function gets as input:
		// the color as int -> color(r,g,b):int of the specific timeslot (one of
		// the specified 5 colors)
		// the duration in minutes
		// the day 1-7
		// time in minutes

		stroke(color);
		strokeWeight(thiknessOfOneDay / 4);
		noFill();

		float localRadius = (radius - (day - 1) * thiknessOfOneDay)
				- thiknessOfOneDay / 4;
		float start = convertTimeToDegrees(time);
		duration = convertTimeToDegrees(duration);
		float end = start + duration;
		boolean flagDrawIfInScope = true;
		boolean drawnOnce = false;

		if (start <= 90 && end > 90 && !drawnOnce) {

			float rest = end - (90);

			// arc in color
			noFill();
			stroke(color);
			arc(centerX, centerY, localRadius - thiknessOfOneDay / 4,
					localRadius, radians(-90 + start), radians(0.3f));

			flagDrawIfInScope = false;

			rest = convertDegreesToTime(rest);
			addTimeSlot(color, day, 361, rest);
			drawnOnce = true;

		}

		if (start < 180 && end > 180 && !drawnOnce) {

			float rest = end - (180);

			// arc in color
			noFill();
			stroke(color);
			arc(centerX, centerY, localRadius - thiknessOfOneDay / 4,
					localRadius - thiknessOfOneDay / 2, radians(-90 + start),
					radians(-90 + 180+0.3f));

			flagDrawIfInScope = false;

			rest = convertDegreesToTime(rest);
			addTimeSlot(color, day, 721, rest);
			drawnOnce = true;

		}

		if (start <= 270 && end > 270 && !drawnOnce) {

			float rest = end - (270);

			// arc in color
			noFill();
			stroke(color);
			arc(centerX, centerY, localRadius - 3 * thiknessOfOneDay / 4,
					localRadius - thiknessOfOneDay / 2,
					radians(-90 + start - 1), radians(-90 + 270 + 0.3f));

			flagDrawIfInScope = false;

			rest = convertDegreesToTime(rest);
			addTimeSlot(color, day, 1081, rest);

			drawnOnce = true;

		}

		if ((start <= 360 && end > 360) && !drawnOnce) {
			float rest = end - (360);

			// arc in color
			noFill();
			stroke(color);
			arc(centerX, centerY, localRadius - 3 * thiknessOfOneDay / 4,
					localRadius - thiknessOfOneDay / 2 - thiknessOfOneDay / 2,
					radians(-90 + start - 1), radians(-90 + 360 + 0.3f));

			flagDrawIfInScope = false;

			rest = convertDegreesToTime(rest);
			addTimeSlot(color, day + 1, 0, rest);

			drawnOnce = true;

		}

		if (flagDrawIfInScope) {
			if (start < 90 && start >= 0) {
				noFill();
				stroke(color);
				arc(centerX, centerY, localRadius - thiknessOfOneDay / 4,
						localRadius, radians(-90 + start-0.4f), radians(-90 + start
								+ duration));
			}

			if (start >= 90 && start < 180) {

				noFill();
				stroke(color);
				arc(centerX, centerY, localRadius - thiknessOfOneDay / 4,
						localRadius - thiknessOfOneDay / 2, radians(-90 + start
								-0.4f), radians(-90 + start + duration));

			}
			if (start < 270 && start >= 180) {
				noFill();
				stroke(color);
				arc(centerX, centerY, localRadius - 3 * thiknessOfOneDay / 4,
						localRadius - thiknessOfOneDay / 2, radians(-90 + start-0.4f
								), radians(-90 + start + duration));
			}

			if (start < 360 && start >= 270) {
				noFill();
				stroke(color);
				arc(centerX, centerY, localRadius - 3 * thiknessOfOneDay / 4,
						localRadius - thiknessOfOneDay,
						radians(-90 + start -0.4f), radians(-90 + start
								+ duration));

			}

		}
	}

	public void addData() {
		// this is a sample data set
		Dataset myDataset = new Dataset(
				"../src/Data/data.XML");
		
		// day 1 Tuesday
		addTimeSlot(homeColor, 1, 0, 400);
		addTimeSlot(workColor, 1, 420, 270);
		addTimeSlot(homeColor, 1, 720, 30);
		addTimeSlot(socialColor, 1, 760, 60);
		addTimeSlot(homeColor, 1, 830, 310);
		addTimeSlot(socialColor, 1, 1150, 30);
		addTimeSlot(freeTimeColor, 1, 1190, 240);
		// day 2 Wednesday
		addTimeSlot(freeTimeColor, 2, 20, 60);
		addTimeSlot(homeColor, 2, 110, 790);
		addTimeSlot(workColor, 2, 930, 270);
		addTimeSlot(socialColor, 2, 1205, 10);
		addTimeSlot(homeColor, 2, 1245, 825);
		// day 3 thursday
		addTimeSlot(uniColor, 3, 660, 180);
		addTimeSlot(homeColor, 3, 870, 120);
		addTimeSlot(workColor, 3, 1020, 270);
		addTimeSlot(homeColor, 3, 1320, 855);
		// day4 friday
		addTimeSlot(socialColor, 4, 745, 30);
		addTimeSlot(homeColor, 4, 785, 355);
		addTimeSlot(freeTimeColor, 4, 1160, 140);
		addTimeSlot(freeTimeColor, 4, 1320, 180);
		// day5 = saturday
		addTimeSlot(homeColor, 5, 70, 540);
		addTimeSlot(homeColor, 5, 640, 1520);
		// day 6 = sunday
		addTimeSlot(homeColor, 6, 1080, 180);
		addTimeSlot(socialColor, 6, 1290, 150);
		// day 7 = monday
		addTimeSlot(homeColor, 7, 30, 540);
		addTimeSlot(workColor, 7, 600, 120);
		addTimeSlot(homeColor, 7, 730, 110);
		addTimeSlot(workColor, 7, 850, 180);
		addTimeSlot(freeTimeColor, 7, 1030, 50);
		addTimeSlot(homeColor, 7, 1110, 180);
		addTimeSlot(socialColor, 7, 1310, 130);

		// home = 72.5%
		// work = 10.4%
		// social = 6%
		// freeTime = 8,7%
		// uni = 2.4%

	}

	public void setup() {
		size(width, height);
		background(background);
		imageMode(CENTER);
		strokeCap(SQUARE);
	}

	public void draw() {
		background(background);
		drawSpiral();
		drawContentArround();
		drawClock();
		addData();
		noLoop();

	}
}
