import processing.core.PApplet;
import processing.core.PFont;

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
	helperForSpiralGraph helper = 
			new helperForSpiralGraph(radius, centerX, centerY, days);
	
	public float thiknessOfOneDay = radius / (days + 1);
	public int background = color(255, 255, 255);

	public int homeColor = color(236, 0, 140);
	public int uniColor = color(180, 210, 53);
	public int socialColor = color(40, 170, 225);
	public int workColor = color(255, 198, 11);
	public int freeTimeColor = color(59, 25, 133);

	public int byFeet = homeColor;
	public int byCar = uniColor;
	public int byTram = socialColor;
	public int byBike = workColor;
	
	public String[] meansOfTransportStrings = new String[4];
	public float[] percentagesOfTransports = new float[4];
	public int[] colors = new int[4];
	

	public void drawContentArround() {

		float xPos = 690;
		float yPos = 540;

		rectMode(CORNER);

		// by Feet
		fill(byFeet);
		noStroke();
		rect(85 + xPos, 25 + 0 * 30 + yPos, 40, 25);
		fill(0);
		
		float lclprc = percentagesOfTransports[0];
		String prcntg = helper.formatFloats(lclprc);

		PFont infoText = createFont("Dialog.plain", 12);
		textFont(infoText);

		text(prcntg, 130 + xPos, 42 + 0 * 30 + yPos);
		text("zu Fuﬂ", 25 + xPos, 42 + 0 * 30 + yPos);

		//by Tram
		fill(byTram);
		noStroke();
		rect(85 + xPos, 25 + 2 * 30 + yPos, 40, 25);

		fill(0);
		lclprc = percentagesOfTransports[2];
		prcntg = helper.formatFloats(lclprc);
		text(prcntg, 130 + xPos, 42 + 2 * 30 + yPos);
		text("OPNV", 25 + xPos, 42 + 2 * 30 + yPos);

		// byCar
		fill(byCar);
		noStroke();
		rect(85 + xPos, 25 + 1 * 30 + yPos, 40, 25);
		fill(0);
		lclprc = percentagesOfTransports[3];
		prcntg = helper.formatFloats(lclprc);

		text(prcntg, 130 + xPos, 42 + 1 * 30 + yPos);
		text("Auto", 25 + xPos, 42 + 1 * 30 + yPos);

		// by Bike
		fill(byBike);
		noStroke();
		rect(85 + xPos, 25 + 3 * 30 + yPos, 40, 25);
		fill(0);
		lclprc = percentagesOfTransports[1];
		prcntg = helper.formatFloats(lclprc);

		text(prcntg, 130 + xPos, 42 + 3 * 30 + yPos);
		text("Fahrrad", 25 + xPos, 42 + 3 * 30 + yPos);
		
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
			float[] pos = helper.degreesToXnY(i * 360 / 12, 0);
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
		float start = helper.convertTimeToDegrees(time);
		duration = helper.convertTimeToDegrees(duration);
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

			rest = helper.convertDegreesToTime(rest);
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
					radians(-90 + 180 + 0.3f));

			flagDrawIfInScope = false;

			rest = helper.convertDegreesToTime(rest);
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

			rest = helper.convertDegreesToTime(rest);
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

			rest = helper.convertDegreesToTime(rest);
			addTimeSlot(color, day + 1, 0, rest);

			drawnOnce = true;

		}

		if (flagDrawIfInScope) {
			if (start < 90 && start >= 0) {
				noFill();
				stroke(color);
				arc(centerX, centerY, localRadius - thiknessOfOneDay / 4,
						localRadius, radians(-90 + start - 0.4f), radians(-90
								+ start + duration));
			}

			if (start >= 90 && start < 180) {

				noFill();
				stroke(color);
				arc(centerX, centerY, localRadius - thiknessOfOneDay / 4,
						localRadius - thiknessOfOneDay / 2, radians(-90 + start
								- 0.4f), radians(-90 + start + duration));

			}
			if (start < 270 && start >= 180) {
				noFill();
				stroke(color);
				arc(centerX, centerY, localRadius - 3 * thiknessOfOneDay / 4,
						localRadius - thiknessOfOneDay / 2, radians(-90 + start
								- 0.4f), radians(-90 + start + duration));
			}

			if (start < 360 && start >= 270) {
				noFill();
				stroke(color);
				arc(centerX, centerY, localRadius - 3 * thiknessOfOneDay / 4,
						localRadius - thiknessOfOneDay, radians(-90 + start
								- 0.4f), radians(-90 + start + duration));

			}

		}
	}

	public void addData() {
		
		meansOfTransportStrings[0] = "zu Fuﬂ";
		percentagesOfTransports[0] = 0;
		colors[0] = byFeet;
		
		meansOfTransportStrings[1] = "Fahrrad";
		percentagesOfTransports[1] = 0;
		colors[1] = byBike;
		
		meansOfTransportStrings[2] = "Straﬂenbahn";
		percentagesOfTransports[2] = 0;
		colors[2] = byTram;
		
		meansOfTransportStrings[3] = "Auto";
		percentagesOfTransports[3] = 0;
		colors[3] = byCar;
		
		// this is a sample data set
		Dataset dtst = new Dataset("../src/Data/data.XML");

		Activity[] allActivities = dtst.getActivities();
		Activity act;
		int start;
		float dur;
		int timeSlotColor;
		float day;
		
		for (int i = 0; i < allActivities.length; i++) {
			if (allActivities[i] != null) {
				act = allActivities[i];				
				if (act.getHuman().getName().equals("Tom")) {
					
					start = act.bTime[0] * 60 + act.bTime[1];
					dur = (float) act.duration;
					timeSlotColor = 0;
					day = 0f;

					if (act.day.equals("Montag")) {
						day = 1f;
					}
					if (act.day.equals("Dienstag")) {
						day = 2f;
					}
					if (act.day.equals("Mittwoch")) {
						day = 3f;
					}
					if (act.day.equals("Donnerstag")) {
						day = 4f;
					}
					if (act.day.equals("Freitag")) {
						day = 5f;
					}
					if (act.day.equals("Samstag")) {
						day = 6f;
					}
					if (act.day.equals("Sonntag")) {
						day = 7f;
					}

					
					if (act.transport.equals("Straﬂenbahn")
							|| act.transport.equals("Zug")
							|| act.transport.equals("Bus")) {
						timeSlotColor = byTram;
						percentagesOfTransports[2] +=act.duration;

					}
					if(act.transport.equals("Auto")){
						timeSlotColor = byCar;
						percentagesOfTransports[3] +=act.duration;
					}
					if(act.transport.equals("Fahrrad")){
						timeSlotColor = byBike;
						percentagesOfTransports[1] += act.duration;
					}
					if(act.transport.equals("zu Fuﬂ")){
						timeSlotColor = byFeet;
						percentagesOfTransports[0] +=act.duration;
					}
					
					
					addTimeSlot(timeSlotColor, day, start, dur);
					
				}
			}		
		}



		//compute the percentages of all means of transport
		float sumOfAllDurations = 0;
		for(int j=0;j<4;j++){
			sumOfAllDurations = sumOfAllDurations + percentagesOfTransports[j];
		}
		for(int j=0;j<4;j++){
			percentagesOfTransports[j] = 100*percentagesOfTransports[j]/sumOfAllDurations;
		}	
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
		drawClock();
		addData();
		drawContentArround();
		//noLoop();

	}
}
