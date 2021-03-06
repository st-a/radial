import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class Diagramm {

	PApplet p;
	PImage[] Icons;
	float posX, posY;
	float radius = 300;
	PFont f;
	Distance dStatistic;
	Timeline[] timelines;
	int[][] colors = { { 54, 146, 179 }, { 158, 219, 41 }, { 255, 243, 68 },
			{ 253, 77, 72 } };
	float scale;
	int[] d;
	String currentDay = "Montag";
	Dataset dataset;

	Diagramm(PApplet parent, float x, float y, Dataset data, float s, PFont font, PImage[] aI) {

		p = parent;
		posX = x;
		posY = y;
		scale = s;
		radius = radius / scale;
		dataset = data;
		f = font;
		Icons = aI;
	}

	public void draw(float x, float y, Human[] humans, String day, float s,
			boolean stat) {

		radius = 300 / scale;
		posX = x;
		posY = y;
		scale = s;
		int angle = 90;
		int frequency = 15;

		if (stat) {
			// km-Statistic
			if (humans.length == 1) {
				dStatistic = new Distance(p, this, humans[0], day);
				dStatistic.draw(x, y);
			} else {
				dStatistic = new Distance(p, this, humans, day);
				dStatistic.draw(x, y);
			}
		}
		p.noFill();

		// aeuseren Ringe
		p.ellipseMode(p.CENTER);
		p.stroke(50);
		p.ellipse(posX, posY, 520 / scale, 520 / scale);
		p.ellipse(posX, posY, 460 / scale, 460 / scale);
		p.ellipse(posX, posY, 400 / scale, 400 / scale);
		p.ellipse(posX, posY, 340 / scale, 340 / scale);
		p.ellipse(posX, posY, 280 / scale, 280 / scale);
		p.ellipse(posX, posY, 220 / scale, 220 / scale);
		p.ellipse(posX, posY, 160 / scale, 160 / scale);

		// Skalenstriche
		for (int i = 0; i < 40; i++) {
			p.stroke(50);
			if (i > 23) {
				p.stroke(65);
				frequency = 30;
			}
			if (i > 35) {
				p.stroke(100);
				frequency = 90;
			}
			p.line(posX, posY, this.cosPx(posX, angle, 300 / scale),
					this.sinPy(posY, angle, 300 / scale));
			angle -= frequency;
		}

		// innere Kreis
		p.stroke(42);
		p.fill(42);
		p.ellipse(posX, posY, 160 / scale, 160 / scale);
		angle = 90;
		p.noFill();

		// Uhr innen
		for (int i = 0; i < 40; i++) {
			p.stroke(255);
			p.strokeWeight(1 / scale);
			p.line(posX, posY, this.cosPx(posX, angle, 20 / scale),
					this.sinPy(posY, angle, 20 / scale));
			angle -= 30;
		}

		// Uhr innerer Kreis
		p.noStroke();
		p.fill(42);
		p.ellipse(posX, posY, 25 / scale, 25 / scale);

		// Beschriftung
		p.textFont(f, 18 / s);
		p.textAlign(p.CENTER, p.CENTER);
		p.fill(255);
		p.text("0", posX, posY - (40 / scale));
		p.text("6", posX + (40 / scale), posY);
		p.text("12", posX, posY + (40 / scale));
		p.text("18", posX - (40 / scale), posY);

		// Timelines
		timelines = new Timeline[humans.length];
		for (int i = 0; i < humans.length; i++) {

			if (humans[i].getName().equals("Albert"))
				this.timelines[i] = new Timeline(p,
						this.setLine(humans[i], day), colors[0][0],
						colors[0][1], colors[0][2], this);

			if (humans[i].getName().equals("Hannes"))
				this.timelines[i] = new Timeline(p,
						this.setLine(humans[i], day), colors[1][0],
						colors[1][1], colors[1][2], this);

			if (humans[i].getName().equals("Sophia"))
				this.timelines[i] = new Timeline(p,
						this.setLine(humans[i], day), colors[2][0],
						colors[2][1], colors[2][2], this);

			if (humans[i].getName().equals("Tom"))
				this.timelines[i] = new Timeline(p,
						this.setLine(humans[i], day), colors[3][0],
						colors[3][1], colors[3][2], this);

			if (humans.length > 1)
				timelines[i].drawLine(150);
			else
				timelines[i].drawLine(255);
		}

		// Skalenpunkte
		angle = 90;
		radius = 260 / scale;
		frequency = 30;

		for (int i = 0; i < (5 * 12); i++) {
			p.fill(230);
			p.noStroke();
			if (i % 12 == 0)
				radius = radius - 30 / scale;
			p.ellipse(this.cosPx(posX, angle, radius),
					this.sinPy(posY, angle, radius), 3 / scale, 3 / scale);
			angle -= frequency;
		}
		
		//Icons
		p.fill(255,255,255,150);
		p.imageMode(p.CENTER);
		for(int i=0; i<5; i++){
			p.ellipse(posX,posY-radius-(i*30)/scale, 25 / scale, 25 / scale);
			p.image(this.Icons[4-i], posX,posY-radius-(i*30)/scale,25 / scale, 25 / scale);
		}
		

	}

	// Funktionen

	public float cosPx(float px, int angle, float r) {
		float x = px + p.cos(p.radians(angle)) * (r);
		return x;
	}

	public float sinPy(float py, int angle, float r) {
		float y = py + p.sin(p.radians(angle)) * (r);
		return y;
	}

	public float getScale() {
		return this.scale;
	}

	public void scale(float s) {

		this.draw(posX, posY, dataset.getPersons(), currentDay, s, true);
	}

	public void draw(float x, float y, Human human, String day, float s,
			boolean stat) {
		Human[] h = new Human[1];
		h[0] = human;
		this.draw(x, y, h, day, s, stat);
	}

	public void transform(float x, float y) {
		this.draw(x, y, dataset.getPersons(), currentDay, scale, true);
	}

	public void drawMatrix(Human[] h, String[] aDay, boolean stat) {
		Human[] human = new Human[1];

		// fue alle personen
		for (int i = 0; i < h.length; i++) {
			for (int j = 0; j < aDay.length; j++) {
				this.draw(210 + (120 * i), 120 + (85 * j), h[i], aDay[j], 8f,
						stat);
			}
		}
		for (int j = 0; j < aDay.length; j++) {
			p.textFont(f, 18);
			p.textAlign(p.LEFT, p.CENTER);
			p.fill(255);
			p.text(aDay[j], 40, 130 + (85 * j));
			p.stroke(255);
			p.line(40, 160 + (85 * j), 140, 160 + (85 * j));
		}

	}

	public int[] setLine(Human h, String day) {
		int line[] = new int[25];
		Activity[] a = dataset.getActivityByDayAndPerson(day, h.getName());
		Activity lastDay = dataset.lastActivityLastDay(day, h.getName());
		if (day.equals("Montag"))
			line[0] = 0;

		else {
			for (int i = 0; i < 25; i++) {
				if (lastDay.catagory.equals("Home"))
					line[i] = 0;
				if (lastDay.catagory.equals("Uni"))
					line[i] = 1;
				if (lastDay.catagory.equals("Social"))
					line[i] = 2;
				if (lastDay.catagory.equals("Work"))
					line[i] = 3;
				if (lastDay.catagory.equals("Freizeit"))
					line[i] = 4;
			}
		}

		for (int i = 1; i < 25; i++) {
			for (int j = 0; j < a.length; j++) {
				int endTime = a[j].getEndTime()[0];
				if (a[j].getEndTime()[1] > 30)
					endTime = a[j].getEndTime()[0] + 1;
				if (endTime <= i) {
					if (a[j].catagory.equals("Home")) {
						line[i] = 0;
					}
					if (a[j].catagory.equals("Uni")) {
						line[i] = 1;
					}
					if (a[j].catagory.equals("Social")) {
						line[i] = 2;
					}
					if (a[j].catagory.equals("Work")) {
						line[i] = 3;
					}
					if (a[j].catagory.equals("Freizeit")) {
						line[i] = 4;
					}

				}
			}

		}
		return line;
	}
	
	
	public void drawLegend(){
		p.fill(255);
		p.imageMode(p.CENTER);
		for(int i=0; i<5; i++){
			p.ellipse(60+(120*i),660, 25, 25);
			p.image(this.Icons[4-i], 60+(120*i),660);
		}
		p.textFont(f, 12);
		p.textAlign(p.LEFT, p.CENTER);
		p.text("Home", 80, 660);
		p.text("Uni", 200, 660);
		p.text("Social", 320, 660);
		p.text("Work", 440, 660);
		p.text("Freetime", 560, 660);
		
	}
	
}
