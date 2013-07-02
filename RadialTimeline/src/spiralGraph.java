import processing.core.PApplet;
import processing.core.PFont;

public class spiralGraph extends PApplet {

	private static final long serialVersionUID = 1L;
	public PApplet pa;

	public float width = 600;
	public float height = 600;
	public float centerX = height / 2;
	public float centerY = height / 2;
	public float radius = (height - 50); // this is obviously crap but i cant
	public float scale; // refactor it

	public float circumference = 2 * PI * radius;

	public float days = 7 + 1; // the '+1' is added to make sure, ther's
								// enough white space in the middle
	public String person;

	public helperForSpiralGraph helper = null;

	public float thiknessOfOneDay = radius / (days + 1);
	public int background = color(42);

	public int byFeet = color(194, 69, 78);
	public int byCar = color(123, 173, 141);
	public int byTram = color(255, 199, 70);
	public int byBike = color(247, 141, 71);

	public String[] meansOfTransportStrings = new String[4];
	public float[] percentagesOfTransports = new float[4];
	public int[] colors = new int[4];

	
	//constructor
	spiralGraph(PApplet pa, float width, float height, String person) {
		this.pa = pa;
		this.width = width;
		this.height = height;
		this.person = person;
		this.scale = height/ 600;
		
		helper = new helperForSpiralGraph(scale, radius, centerX,
					centerY, days);
	}
	
	//getter setter
	public void setWidth(float w){
		this.width = w;
	}
	
	public void setHeight(float h){
		this.height = h;
		helper.setRadius(h-50);
		scale = h/600;
		helper.setScale(scale);
	}
	
	public void setCenterX(float cntrX){
		this.centerX = cntrX;
	}
	
	public void setCenterY(float cntrY){
		this.centerY = cntrY;
	}

	//relevant functions
	public void drawContentArround() {

		float xPos = 690;
		float yPos = 540;

		rectMode(CORNER);

		// by Feet
		fill(byFeet);
		noStroke();
		rect(85 + xPos, 25 + 0 * 30 + yPos, 40, 25);

		float lclprc = percentagesOfTransports[0];
		String prcntg = helper.formatFloats(lclprc);

		PFont infoText = createFont("Dialog.plain", 12);
		textFont(infoText);

		// text(prcntg, 130 + xPos, 42 + 0 * 30 + yPos);
		// text("zu Fuﬂ", 25 + xPos, 42 + 0 * 30 + yPos);

		// by Tram
		fill(byTram);
		noStroke();
		rect(85 + xPos, 25 + 2 * 30 + yPos, 40, 25);

		fill(255);
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
		pa.strokeWeight(1);
		pa.stroke(100);
		pa.line(centerX - radius / 2, centerY, centerX + radius / 2 + 10, centerY);
		pa.line(centerX, centerY - radius / 2 - 10, centerX, centerY + radius / 2
				+ 10);

		pa.pushMatrix();
		pa.stroke(65);
		pa.translate(centerX, centerY);
		pa.rotate(radians(30));
		pa.translate(-centerX, -centerY);
		pa.line(centerX - radius / 2, centerY, centerX + radius / 2 + 10, centerY);
		pa.line(centerX, centerY - radius / 2 - 10, centerX, centerY + radius / 2
				+ 10);
		pa.popMatrix();

		for (int i = 0; i < 6; i++) {
			pa.pushMatrix();
			pa.stroke(50);
			pa.strokeWeight(1.5f);
			pa.translate(centerX, centerY);
			pa.rotate(radians(i * 30 + 15));
			pa.translate(-centerX, -centerY);
			pa.line(centerX - radius / 2, centerY, centerX + radius / 2 + 10,
					centerY);
			pa.popMatrix();
		}

		pa.pushMatrix();
		pa.stroke(65);
		pa.translate(centerX, centerY);
		pa.rotate(radians(60));
		pa.translate(-centerX, -centerY);
		pa.line(centerX - radius / 2, centerY, centerX + radius / 2 + 10, centerY);
		pa.line(centerX, centerY - radius / 2 - 10, centerX, centerY + radius / 2
				+ 10);
		pa.popMatrix();

		pa.fill(42);
		pa.noStroke();
		pa.ellipse(centerX, centerY, 100, 100);

		pa.rectMode(CENTER);
		PFont infoText = createFont("Dialog.plain", 13);
		pa.textFont(infoText);

		for (int i = 0; i < 12; i++) {
			float[] pos = helper.degreesToXnY(i * 360 / 12, 0);
			pa.fill(255);
			pa.pushMatrix();
			pa.translate(pos[0], pos[1]);
			pa.rotate(radians((float) i / 12 * 360));
			pa.translate(-pos[0], -pos[1]);
			pa.rect(pos[0], pos[1], 1, 5);
			pa.popMatrix();
		}
		pa.fill(255);
		pa.text("0", centerX - 4, centerY - 32);
		pa.text("12", centerX - 8, centerY + 42);
		pa.text("18", centerX - 48, centerY + 4);
		pa.text("6", centerX + 30, centerY + 6);
	}

	public void drawSpiral() {

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

				// drawing the spiral using arcs

				localRadius = (radius - i * thiknessOfOneDay);
				pa.strokeWeight(2);
				pa.stroke(100);
				pa.noFill();
				// arc1

				pa.arc(centerX, centerY, localRadius - thiknessOfOneDay / 4,
						localRadius, radians(-90), radians(0));
				// arc2
				pa.arc(centerX, centerY, localRadius - thiknessOfOneDay / 4,
						localRadius - thiknessOfOneDay / 2, radians(0),
						radians(90));
				// arc3
				pa.arc(centerX, centerY, localRadius - 3 * thiknessOfOneDay / 4,
						localRadius - thiknessOfOneDay / 2, radians(90),
						radians(180));
				// arc4
				pa.arc(centerX, centerY, localRadius - 3 * thiknessOfOneDay / 4,
						localRadius - thiknessOfOneDay, radians(180),
						radians(270));

			} else {

				// this is for blending the center
				pa.noStroke();
				pa.noFill();
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

		pa.stroke(color);
		pa.strokeWeight(thiknessOfOneDay / 4);
		pa.noFill();

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
			pa.noFill();
			pa.stroke(color);
			pa.arc(centerX, centerY, localRadius - thiknessOfOneDay / 4,
					localRadius, radians(-90 + start), radians(0.3f));

			flagDrawIfInScope = false;

			rest = helper.convertDegreesToTime(rest);
			addTimeSlot(color, day, 361, rest);
			drawnOnce = true;

		}

		if (start < 180 && end > 180 && !drawnOnce) {

			float rest = end - (180);

			// arc in color
			pa.noFill();
			pa.stroke(color);
			pa.arc(centerX, centerY, localRadius - thiknessOfOneDay / 4,
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
			pa.noFill();
			pa.stroke(color);
			pa.arc(centerX, centerY, localRadius - 3 * thiknessOfOneDay / 4,
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
			pa.noFill();
			pa.stroke(color);
			pa.arc(centerX, centerY, localRadius - 3 * thiknessOfOneDay / 4,
					localRadius - thiknessOfOneDay / 2 - thiknessOfOneDay / 2,
					radians(-90 + start - 1), radians(-90 + 360 + 0.3f));

			flagDrawIfInScope = false;

			rest = helper.convertDegreesToTime(rest);
			addTimeSlot(color, day + 1, 0, rest);

			drawnOnce = true;

		}

		if (flagDrawIfInScope) {
			if (start < 90 && start >= 0) {
				pa.noFill();
				pa.stroke(color);
				pa.arc(centerX, centerY, localRadius - thiknessOfOneDay / 4,
						localRadius, radians(-90 + start), radians(-90
								+ start + duration));
			}

			if (start >= 90 && start < 180) {
				pa.noFill();
				pa.stroke(color);
				pa.arc(centerX, centerY, localRadius - thiknessOfOneDay / 4,
						localRadius - thiknessOfOneDay / 2, radians(-90 + start
								- 0.4f), radians(-90 + start + duration));

			}
			if (start < 270 && start >= 180) {
				pa.noFill();
				pa.stroke(color);
				pa.arc(centerX, centerY, localRadius - 3 * thiknessOfOneDay / 4,
						localRadius - thiknessOfOneDay / 2, radians(-90 + start
								- 0.4f), radians(-90 + start + duration));
			}

			if (start < 360 && start >= 270) {
				pa.noFill();
				pa.stroke(color);
				pa.arc(centerX, centerY, localRadius - 3 * thiknessOfOneDay / 4,
						localRadius - thiknessOfOneDay, radians(-90 + start
								- 0.4f), radians(-90 + start + duration));

			}

		}
	}

	public void addData() {

		meansOfTransportStrings[0] = "by Feet";
		percentagesOfTransports[0] = 0;
		colors[0] = byFeet;

		meansOfTransportStrings[1] = "Fahrrad";
		percentagesOfTransports[1] = 0;
		colors[1] = byBike;

		meansOfTransportStrings[2] = "Tram";
		percentagesOfTransports[2] = 0;
		colors[2] = byTram;

		meansOfTransportStrings[3] = "Auto";
		percentagesOfTransports[3] = 0;
		colors[3] = byCar;

		Dataset dtst = new Dataset("../src/Data/data.XML");

		Activity[] allActivities = null;
		if(person.equals("all")){
			allActivities = dtst.getActivities();
		}else{
			allActivities = dtst.getPersonActivities(person);
		}
		Activity act;
		int start;
		float dur;
		int timeSlotColor;
		float day;

		for (int i = 0; i < allActivities.length; i++) {
			if (allActivities[i] != null) {
				act = allActivities[i];
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

				if (act.transport.equals("Tram") || act.transport.equals("Zug")
						|| act.transport.equals("Bus")) {
					timeSlotColor = byTram;
					percentagesOfTransports[2] += act.duration;

				}
				if (act.transport.equals("Auto")) {
					timeSlotColor = byCar;
					percentagesOfTransports[3] += act.duration;
				}
				if (act.transport.equals("Fahrrad")) {
					timeSlotColor = byBike;
					percentagesOfTransports[1] += act.duration;
				}
				if (act.transport.equals("by feet")) {
					timeSlotColor = byFeet;
					percentagesOfTransports[0] += act.duration;
				}

				addTimeSlot(timeSlotColor, day, start, dur);

			}
		}

		// compute the percentages of all means of transport
		float sumOfAllDurations = 0;
		for (int j = 0; j < 4; j++) {
			sumOfAllDurations = sumOfAllDurations + percentagesOfTransports[j];
		}
		for (int j = 0; j < 4; j++) {
			percentagesOfTransports[j] = 100 * percentagesOfTransports[j]
					/ sumOfAllDurations;
		}
	}
}
