import processing.core.*;
import controlP5.*;

public class Application extends PApplet {

	int animationAlpha = 255;
	String[] sHuman = { "Hannes", "Tom", "Albert" };
	String sDay = "Montag";
	Human[] aHuman;
	String[] aDay;

	PFont interfaceHealines = createFont("../src/typo/OpenSans-Regular.ttf", 18);
	PFont legendenText = createFont("../src/typo/OpenSans-Light.ttf", 12);
	PFont normalText = createFont("../src/typo/OpenSans-Light.ttf", 18);
	PFont Headlines = createFont("../src/typo/OpenSans-Regular.ttf", 28);
	ControlP5 cp5;
	RadioButton dayBtn;
	RadioButton vizBtn;
	RadioButton styleBtn;
	CheckBox personCheckb;
	int myColorBackground = color(0, 0, 0);
	boolean redraw = true;
	Dataset d;
	// Vizualisierungen
	Diagramm webViz;
	sketchBundlingEdges bundling;
	sketchSpiralGraph spiral;

	public void setup() {
		size(1000, 700);
		background(42);

		// Dataset
		d = new Dataset("../src/data/data.xml");
		aHuman = d.getPersons();
		// control
		cp5 = new ControlP5(this);

		this.drawControl();
	}

	public void draw() {

		if (redraw) {
			textAlign(LEFT, BASELINE);
			this.drawInterface();

			textFont(this.Headlines);
			text("Bundling Edges", 40, 2 * 20);
			line(260, 1 * 20, 260, 4 * 20);
			textFont(this.normalText);
			boolean moreThanOne = false;
			String sChain = "";
			for (int i = 0; i < 7; i++) {
				if (moreThanOne && this.dayBtn.getItem(i).getState()) {
					sChain = sChain + ", ";
					moreThanOne = false;
				}
				if (this.dayBtn.getItem(i).getState()) {
					sChain = sChain + this.dayBtn.getItem(i).getName();
					moreThanOne = true;
				}
			}

			text("Tag: " + sChain, 280, 2 * 20);
			moreThanOne = false;
			sChain = "";

			for (int i = 0; i < 4; i++) {
				if (moreThanOne && this.personCheckb.getItem(i).getState()) {
					sChain = sChain + ", ";
					moreThanOne = false;
				}
				if (this.personCheckb.getItem(i).getState()) {
					sChain = sChain + this.personCheckb.getItem(i).getName();
					moreThanOne = true;
				}
			}

			text("Person: " + sChain, 280, 3 * 20);

			if (this.vizBtn.getItem(0).getState()) {
				bundling = new sketchBundlingEdges(this, 650, 650, 50, 80,
						sHuman, this.styleBtn.getItem(1).getState());
			}

			if (this.vizBtn.getItem(1).getState()) {
				spiral = new sketchSpiralGraph(this, 40, 100, 500, 500,
						this.styleBtn.getItem(1).getState(), true, sHuman);
			}

			if (this.vizBtn.getItem(2).getState()) {
				int[] distance = { 40, 45, 20, 10, 58, 10, 32, 25, 25, 25, 25,
						25 };
				this.webViz = new Diagramm(this, 40, 40, distance, d, 1,
						this.legendenText);
				if (this.styleBtn.getItem(1).getState()) {
					webViz.drawMatrix(aHuman, sDay);
				} else
					if(aHuman.length > 1){
						webViz.draw(350, 380, aHuman, sDay, 1.2f);
					}
					else
					webViz.draw(350, 380, aHuman[0], sDay, 1.2f);
			}
		}
	}

	public void drawControl() {

		vizBtn = cp5.addRadioButton("vizRadioButton").setPosition(780, 3 * 20)
				.setSize(20, 20).setSpacingRow(20)
				.setColorForeground(color(100)).setColorActive(color(255))
				.setItemsPerRow(2).setColorBackground(color(50))
				.setSpacingColumn(60).addItem("Bundling", 1)
				.addItem("Spiral", 2).addItem("Spinne", 3);

		for (Toggle t : vizBtn.getItems()) {
			t.captionLabel().setColorBackground(color(20));
			t.setColorCaptionLabel(color(255));
			t.captionLabel().style().moveMargin(0, 0, 0, 0);
			t.captionLabel().style().movePadding(0, 0, 0, 0);
			t.captionLabel().style().backgroundWidth = 0;
			t.captionLabel().style().backgroundHeight = 0;
		}

		personCheckb = cp5.addCheckBox("personCheckBox")
				.setPosition(780, 10 * 20).setColorLabel(color(255))
				.setSpacingRow(20).setSize(20, 20)
				.setColorForeground(color(100)).setColorActive(color(255))
				.setItemsPerRow(2).setColorBackground(color(50))
				.setSpacingColumn(60).addItem("Albert", 0)
				.addItem("Hannes", 50).addItem("Sophia", 100)
				.addItem("Tom", 150);

		dayBtn = cp5.addRadioButton("dayRadioButton").setPosition(780, 17 * 20)
				.setSize(20, 20).setSpacingRow(20)
				.setColorForeground(color(100)).setColorActive(color(255))
				.setItemsPerRow(2).setColorBackground(color(50))
				.setSpacingColumn(60).addItem("Montag", 1)
				.addItem("Dienstag", 2).addItem("Mittwoch", 3)
				.addItem("Donnerstag", 4).addItem("Freitag", 5)
				.addItem("Samstag", 6).addItem("Sonntag", 7);

		for (Toggle t : dayBtn.getItems()) {
			t.captionLabel().setColorBackground(color(20));
			t.setColorCaptionLabel(color(255));
			t.captionLabel().style().moveMargin(0, 0, 0, 0);
			t.captionLabel().style().movePadding(0, 0, 0, 0);
			t.captionLabel().style().backgroundWidth = 0;
			t.captionLabel().style().backgroundHeight = 0;
		}

		this.styleBtn = cp5.addRadioButton("styleRadioButton")
				.setPosition(780, 28 * 20).setSize(20, 20).setSpacingRow(20)
				.setColorForeground(color(100)).setColorActive(color(255))
				.setItemsPerRow(2).setColorBackground(color(50))
				.setSpacingColumn(50).addItem("Single", 1).addItem("Matrix", 2);

		for (Toggle t : styleBtn.getItems()) {
			t.captionLabel().setColorBackground(color(20));
			t.setColorCaptionLabel(color(255));
			t.captionLabel().style().moveMargin(0, 0, 0, 0);
			t.captionLabel().style().movePadding(0, 0, 0, 0);
			t.captionLabel().style().backgroundWidth = 0;
			t.captionLabel().style().backgroundHeight = 0;
		}

		this.dayBtn.activate(0);
		this.personCheckb.activate(0);
		this.vizBtn.activate(0);
		this.styleBtn.activate(0);
	}

	public void drawInterface() {
		background(42);
		fill(35);
		noStroke();
		rect(700, 0, 300, 700);
		fill(255);

		textFont(this.interfaceHealines);
		text("Visualisierungen", 760, 2 * 20);
		stroke(255);
		strokeWeight(1);
		line(760, 3 * 20, 760, 7 * 20);
		text("Personen", 760, 9 * 20);
		line(760, 10 * 20, 760, 14 * 20);
		text("Tag", 760, 16 * 20);
		line(760, 17 * 20, 760, 25 * 20);
		text("Style", 760, 27 * 20);
		line(760, 28 * 20, 760, 32 * 20);
		this.redraw = false;
	}

	public void vizRadioButton(int a) {
		if (a >= 0) {
			this.redraw = true;
		}
	}

	public void dayRadioButton(int a) {
		if (a >= 0) {
			this.sDay = this.dayBtn.getItem(a - 1).getName();
			this.redraw = true;
		} else
			this.dayBtn.activate(a - 1);
	}

	public void personCheckBox(float[] a) {
		int personCount = 0;	
		this.sHuman = new String[4];
		for (int i = 0; i < 4; i++) {
			if (this.personCheckb.getItem(i).getState()) {
				personCount++;
				this.sHuman[i] = this.personCheckb.getItem(i).getName();
			}
		}

		if (personCount > 0) {
			aHuman = new Human[personCount];
			int z = 0;
			for (int i = 0; i < 4; i++) {
				if (this.personCheckb.getItem(i).getState()) {
					aHuman[z] = d.getPerson(this.personCheckb.getItem(i)
							.getName());
					z++;
				}
			}
		} else {
			aHuman = d.getPersons();
			this.personCheckb.activateAll();
		}

		this.redraw = true;

	}

	public void styleRadioButton(int a) {
		if (a >= 0) {
			
			this.redraw = true;
		}
		else this.styleBtn.activate(0);
	}

}
