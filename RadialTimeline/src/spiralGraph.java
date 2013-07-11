import processing.core.PApplet;
import processing.core.PFont;

public class spiralGraph extends PApplet {

	private static final long serialVersionUID = 1L;
	public PApplet pa;
	public PFont legend;

	public float width = 600;
	public float height = 600;
	public float centerX = height / 2;
	public float centerY = height / 2;
	public float radius = (height - 50); // this is obviously crap but i cant
											// refactor it
	public float scale;

	public float circumference = 2 * PI * radius;

	public int[] RangeOfdays;
	public float days = 7 + 1; // the '+1' is added to make sure, ther's
								// enough white space in the middle
	public int ammountOfDays = 0;
	public String person;

	public helperForSpiralGraph helper = null;
	public boolean viewMovements;

	public float thiknessOfOneDay = radius / (days + 1);
	public int background = color(42);
	public int alpha = 70;

	public int byFeet = color(194, 69, 78, alpha);
	public int byCar = color(123, 173, 141, alpha);
	public int byTram = color(255, 199, 70, alpha);
	public int byBike = color(247, 141, 71, alpha);

	public int home = color(255, 123, 106, alpha);
	public int uni = color(255, 242, 190, alpha);
	public int social = color(170, 235, 140, alpha);
	public int work = color(53, 189, 144, alpha);
	public int freeTime = color(0, 150, 163, alpha);

	public String[] meansOfTransportStrings = new String[4];
	public String[] places = new String[5];
	public float[] percentagesOfTransports = new float[4];
	public float[] percentagesPlaces = new float[5];

	public int[] colorsTransport = new int[4];
	public int[] colorsPlaces = new int[5];

	// constructor
	spiralGraph(PApplet pa, float centerX, float centerY, float radius,
			String person, int[] RangeOfdays) {
		this.pa = pa;
		this.centerX = centerX;
		this.centerY = centerY;
		this.radius = radius;
		this.width = radius + 50;
		this.height = radius + 50;
		this.person = person;
		this.scale = height / 600;
		this.RangeOfdays = RangeOfdays;
		this.ammountOfDays = RangeOfdays[1] - RangeOfdays[0];

		helper = new helperForSpiralGraph(scale, radius, centerX, centerY, days);
		legend = createFont("../src/typo/OpenSans-Light.ttf", 12);

	}

	// getter setter
	public void setWidth(float w) {
		this.width = w;
	}

	public void setHeight(float h) {
		this.height = h;
		helper.setRadius(h - 50);
		scale = h / 600;
		helper.setScale(scale);
	}

	public void setCenterX(float cntrX) {
		this.centerX = cntrX;
	}

	public void setCenterY(float cntrY) {
		this.centerY = cntrY;
	}

	public void setRadius(float radius) {
		this.radius = radius;
		this.width = radius + 50;
		this.height = radius + 50;
		this.scale = height / 600;
		thiknessOfOneDay = radius / (days + 1);
	}

	public void setAlpha(int a) {
		this.alpha = a;
	}

	public void setColors(boolean viewAsMatrix, int[] colors,
			boolean moreThanOne) {
		if (!viewAsMatrix && moreThanOne) {
			byFeet = color(colors[0], colors[1], colors[2], alpha);
			byCar = color(colors[0], colors[1], colors[2], alpha);
			byTram = color(colors[0], colors[1], colors[2], alpha);
			byBike = color(colors[0], colors[1], colors[2], alpha);

			home = color(colors[0], colors[1], colors[2], alpha);
			uni = color(colors[0], colors[1], colors[2], alpha);
			social = color(colors[0], colors[1], colors[2], alpha);
			work = color(colors[0], colors[1], colors[2], alpha);
			freeTime = color(colors[0], colors[1], colors[2], alpha);
		} else {
			byFeet = color(194, 69, 78, alpha);
			byCar = color(123, 173, 141, alpha);
			byTram = color(255, 199, 70, alpha);
			byBike = color(247, 141, 71, alpha);

			home = color(255, 123, 106, alpha);
			uni = color(255, 242, 190, alpha);
			social = color(170, 235, 140, alpha);
			work = color(53, 189, 144, alpha);
			freeTime = color(0, 150, 163, alpha);
		}
	}

	public void setMovements(boolean viewMovements) {
		this.viewMovements = viewMovements;
	}

	public void drawLabel() {
		pa.strokeWeight(1);
		pa.stroke(255);
		pa.line(centerX - radius / 2 - 25, centerY - radius / 2 - 15, centerX
				- radius / 2 - 25, centerY - radius / 2+5);
		
		pa.textFont(legend);
		pa.textSize(18f);
		pa.text(person, centerX - radius / 2+10, centerY - radius / 2-10);
	}

	// relevant functions
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

		textFont(legend);

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
		pa.line(centerX - radius / 2, centerY, centerX + radius / 2 + 10,
				centerY);
		pa.line(centerX, centerY - radius / 2 - 10, centerX, centerY + radius
				/ 2 + 10);

		pa.pushMatrix();
		pa.stroke(65);
		pa.translate(centerX, centerY);
		pa.rotate(radians(30));
		pa.translate(-centerX, -centerY);
		pa.line(centerX - radius / 2, centerY, centerX + radius / 2 + 10,
				centerY);
		pa.line(centerX, centerY - radius / 2 - 10, centerX, centerY + radius
				/ 2 + 10);
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
		pa.line(centerX - radius / 2, centerY, centerX + radius / 2 + 10,
				centerY);
		pa.line(centerX, centerY - radius / 2 - 10, centerX, centerY + radius
				/ 2 + 10);
		pa.popMatrix();

		pa.fill(background);
		pa.noStroke();
		pa.ellipse(centerX, centerY, scale * 100f, scale * 100f);

		pa.rectMode(CENTER);
		pa.textFont(legend);

		for (int i = 0; i < 12; i++) {
			float[] xy = { centerX, centerY };
			float[] pos = helper.degreesToXnY(i * 360 / 12, 0, scale, xy);
			pa.fill(255);
			pa.pushMatrix();
			pa.translate(pos[0], pos[1]);
			pa.rotate(radians((float) i / 12 * 360));
			pa.translate(-pos[0], -pos[1]);
			pa.rect(pos[0], pos[1], scale * 1f, scale * 5f);
			pa.popMatrix();
		}
		pa.textAlign(PApplet.CENTER, PApplet.CENTER);
		pa.textSize(12f * scale);
		pa.fill(255);
		pa.text("0", centerX, centerY - scale * 35f);
		pa.text("12", centerX, centerY + scale * 35f);
		pa.text("18", centerX - scale * 35f, centerY);
		pa.text("6", centerX + scale * 35f, centerY);
		pa.rectMode(PApplet.CORNER);
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
				pa.arc(centerX, centerY,
						localRadius - 3 * thiknessOfOneDay / 4, localRadius
								- thiknessOfOneDay / 2, radians(90),
						radians(180));
				// arc4
				pa.arc(centerX, centerY,
						localRadius - 3 * thiknessOfOneDay / 4, localRadius
								- thiknessOfOneDay, radians(180), radians(270));

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
						localRadius, radians(-90 + start), radians(-90 + start
								+ duration));
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
				pa.arc(centerX, centerY,
						localRadius - 3 * thiknessOfOneDay / 4, localRadius
								- thiknessOfOneDay / 2, radians(-90 + start
								- 0.4f), radians(-90 + start + duration));
			}

			if (start < 360 && start >= 270) {
				pa.noFill();
				pa.stroke(color);
				pa.arc(centerX, centerY,
						localRadius - 3 * thiknessOfOneDay / 4, localRadius
								- thiknessOfOneDay,
						radians(-90 + start - 0.4f), radians(-90 + start
								+ duration));

			}

		}
	}

	public void addData() {

		meansOfTransportStrings[0] = "by Feet";
		percentagesOfTransports[0] = 0;
		colorsTransport[0] = byFeet;

		meansOfTransportStrings[1] = "Fahrrad";
		percentagesOfTransports[1] = 0;
		colorsTransport[1] = byBike;

		meansOfTransportStrings[2] = "Tram";
		percentagesOfTransports[2] = 0;
		colorsTransport[2] = byTram;

		meansOfTransportStrings[3] = "Auto";
		percentagesOfTransports[3] = 0;
		colorsTransport[3] = byCar;

		places[0] = "home";
		percentagesPlaces[0] = 0;
		colorsPlaces[0] = home;

		places[1] = "uni";
		percentagesPlaces[1] = 0;
		colorsPlaces[1] = uni;

		places[2] = "work";
		percentagesPlaces[2] = 0;
		colorsPlaces[2] = work;

		places[3] = "social";
		percentagesPlaces[3] = 0;
		colorsPlaces[3] = social;

		places[4] = "free Time";
		percentagesPlaces[4] = 0;
		colorsPlaces[4] = freeTime;

		Dataset dtst = new Dataset("../src/Data/data.XML");

		Activity[] allActivities = null;
		if (person.equals("all")) {
			allActivities = dtst.getActivities();
		} else {
			allActivities = dtst.getPersonActivities(person);
		}
		Activity act;
		Activity act0;
		Activity act1;

		int start;
		int endtime;
		float dur = 0;
		float durAdvanced = 0;
		boolean overLength = false;
		int timeSlotColor;
		float day;

		if (viewMovements) {
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

					if (act.transport.equals("Tram")
							|| act.transport.equals("Zug")
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

					if (day < RangeOfdays[0] || day > RangeOfdays[1]) {
						timeSlotColor = 35;
					}
					addTimeSlot(timeSlotColor, day, start, dur);


				}
			}
		} else {
			// draws places not movements
			if (1 < RangeOfdays[0] || 1 > RangeOfdays[1]) {

				timeSlotColor = 35;
			}
			else{
				timeSlotColor = home;
			}
			addTimeSlot(timeSlotColor, 1, 0, allActivities[0].bTime[0] * 60
					+ allActivities[0].bTime[1]);
			
			boolean dontdraw = false;
			boolean dirtyhackdrawnonce = false;

			for (int i = 1; i < allActivities.length; i++) {
				if (allActivities[i] != null) {

					act1 = allActivities[i];
					act0 = allActivities[i - 1];

					start = act0.eTime[0] * 60 + act0.eTime[1];
					endtime = act1.bTime[0] * 60 + act1.bTime[1];

					

					if (endtime > start) {
						dur = (float) (endtime - start);
						overLength = false;
						durAdvanced = 0;
					} else {
						dur = 1440 - start;
						overLength = true;
						durAdvanced = endtime;
					}
					if (endtime > start
							&& !(act0.day.equals(act1.day))
							&& ((act0.bTime[0] * 60 + act0.bTime[1] + act0.duration) < 1440)) {
						dur += 1440;
					}

					timeSlotColor = 0;
					day = 0f;

					if (act0.day.equals("Montag")) {
						day = 1f;
					}
					if (act0.day.equals("Dienstag")) {
						day = 2f;
					}
					if (act0.day.equals("Mittwoch")) {
						day = 3f;
					}
					if (act0.day.equals("Donnerstag")) {
						day = 4f;
					}
					if (act0.day.equals("Freitag")) {
						day = 5f;
					}
					if (act0.day.equals("Samstag")) {
						day = 6f;
					}
					if (act0.day.equals("Sonntag")) {
						day = 7f;
					}

					if ((act0.bTime[0] * 60 + act0.bTime[1] + act0.duration > 1440)) {
						day++;
					}

					if (act0.catagory.equals("Home")) {
						timeSlotColor = home;
						percentagesPlaces[0] += dur;
					}
					if (act0.catagory.equals("Uni")) {
						timeSlotColor = uni;
						percentagesPlaces[1] += dur;
					}
					if (act0.catagory.equals("Social")) {
						timeSlotColor = social;
						percentagesPlaces[3] += dur;
					}
					if (act0.catagory.equals("Work")) {
						timeSlotColor = work;
						percentagesPlaces[2] += dur;
					}
					if (act0.catagory.equals("Freizeit")) {
						timeSlotColor = freeTime;
						percentagesPlaces[4] += dur;
					}
					
					if (endtime == start) {
						// DIRTY HACK. FAK U TOM
						// Y U NO WORK ON THURSDAY?
						// TODO FUK U
						if (!dirtyhackdrawnonce) {
							if (4 < RangeOfdays[0] || 4 > RangeOfdays[1]) {
								timeSlotColor = 35;
							}else{
								timeSlotColor = home;
							}
							addTimeSlot(timeSlotColor, 4, 0, 1440);

							dirtyhackdrawnonce = true;
						}
						dontdraw = true;
					}

					if (!dontdraw) {
						if (day < RangeOfdays[0] || day > RangeOfdays[1]) {
							timeSlotColor = 35;
						}
						addTimeSlot(timeSlotColor, day, start, dur);


						if (overLength) {
							if (day + 1 < RangeOfdays[0]
									|| day + 1 > RangeOfdays[1]) {
								timeSlotColor = 35;
							}
							addTimeSlot(timeSlotColor, day + 1, 0,
									durAdvanced);
							durAdvanced = 0;
							overLength = false;
						}
					}
					dontdraw = false;
				}
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
