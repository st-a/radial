import processing.core.*;
import controlP5.*;

public class Application extends PApplet {

	int animationAlpha = 255;
	String[] sHuman = { "Albert" };
	String sDay = "Montag";
	Human[] aHuman = new Human[1];
	String[] aDay = { "Montag", "Dienstag", "Mittwoch", "Donnerstag",
			"Freitag", "Samstag", "Sonntag" };
	String[] cutedDay = { "Montag", "Dienstag", "Mittwoch", "Donnerstag",
			"Freitag", "Samstag", "Sonntag" };

	PFont interfaceHealines = createFont("../src/typo/OpenSans-Regular.ttf", 18);
	PFont legendenText = createFont("../src/typo/OpenSans-Light.ttf", 12);
	PFont normalText = createFont("../src/typo/OpenSans-Light.ttf", 18);
	PFont Headlines = createFont("../src/typo/OpenSans-Regular.ttf", 28);
	ControlP5 cp5;
	RadioButton dayBtn;
	RadioButton vizBtn;
	RadioButton styleBtn;
	RadioButton viewBtn;
	CheckBox personCheckb;
	Range range;
	CheckBox dayCheckb;
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
		aHuman[0] = d.getPerson("Albert");
		// control
		cp5 = new ControlP5(this);

		this.drawControl();
	}

	public void draw() {

		if (redraw) {

			textAlign(LEFT, BASELINE);
			this.drawInterface();
			this.setControl();

			textFont(this.Headlines);
			text("Bundling Edges", 40, 2 * 20);
			line(260, 1 * 20, 260, 4 * 20);

			// Bundling
			if (this.vizBtn.getItem(0).getState()) {
				bundling = new sketchBundlingEdges(this, 650, 650, 50, 80,
						sHuman, this.styleBtn.getItem(1).getState());
			}

			// Spiral
			int[] rangeOfDays = new int[2];
			rangeOfDays[0] = 1;
			rangeOfDays[1] = 7;  
			
			if (this.vizBtn.getItem(1).getState()) {
				spiral = new sketchSpiralGraph(this, 100, 100, 500, 500,
						this.styleBtn.getItem(1).getState(), this.viewBtn
								.getItem(0).getState(), sHuman,rangeOfDays);
			}

			// Spiderweb
			if (this.vizBtn.getItem(2).getState()) {
				int[] distance = { 40, 45, 20, 10, 58, 10, 32, 25, 25, 25, 25,
						25 };
				this.webViz = new Diagramm(this, 40, 40, distance, d, 1,
						this.legendenText);
				if (this.styleBtn.getItem(1).getState()) {
					webViz.drawMatrix(aHuman, aDay);
				} else if (aHuman.length > 1) {
					webViz.draw(350, 340, aHuman, sDay, 1.2f);
				} else
					webViz.draw(350, 340, aHuman[0], sDay, 1.2f);
			}
		}
	}

	public void drawControl() {

		vizBtn = cp5.addRadioButton("vizRadioButton").setPosition(780, 3 * 20)
				.setSize(20, 20).setSpacingRow(20)
				.setColorForeground(color(100)).setColorActive(color(255))
				.setItemsPerRow(2).setColorBackground(color(50))
				.setSpacingColumn(60).addItem("Bundling Edges", 1)
				.addItem("Spiralgraph", 2).addItem("Spinnedings", 3);

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
				.setColorForeground(color(100)).setItemsPerRow(2)
				.setColorBackground(color(50)).setSpacingColumn(60)
				.addItem("Albert", 0).addItem("Hannes", 50)
				.addItem("Sophia", 100).addItem("Tom", 150);
		this.personCheckb.getItem(0).setColorActive(color(54, 146, 179));
		this.personCheckb.getItem(1).setColorActive(color(158, 219, 41));
		this.personCheckb.getItem(2).setColorActive(color(255, 243, 68));
		this.personCheckb.getItem(3).setColorActive(color(253, 77, 72));

		dayCheckb = cp5.addCheckBox("dayCheckBox").setPosition(780, 17 * 20)
				.setColorLabel(color(255)).setSpacingRow(20).setSize(20, 20)
				.setColorForeground(color(100)).setColorActive(color(255))
				.setItemsPerRow(2).setColorBackground(color(50))
				.setSpacingColumn(60).addItem("Mo", 1).addItem("Di", 2)
				.addItem("Mi", 3).addItem("Do", 4).addItem("Fr", 5)
				.addItem("Sa", 6).addItem("So", 7);
		this.dayCheckb.getItem(0).setLabel("Montag");
		this.dayCheckb.getItem(1).setLabel("Dienstag");
		this.dayCheckb.getItem(2).setLabel("Mittwoch");
		this.dayCheckb.getItem(3).setLabel("Donnerstag");
		this.dayCheckb.getItem(4).setLabel("Freitag");
		this.dayCheckb.getItem(5).setLabel("Samstag");
		this.dayCheckb.getItem(6).setLabel("Sonntag");

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
		cp5 = new ControlP5(this);
		range = cp5
				.addRange("rangeController")
				// disable broadcasting since setRange and setRangeValues will
				// trigger an event
				.setBroadcast(false).setPosition(780, 17 * 20).setSize(180, 20)
				.setHandleSize(10).setRange(0, 6).setRangeValues(0, 6)
				.setNumberOfTickMarks(7).setColorBackground(color(50))
				.setColorForeground(color(100)).setColorActive(color(255))
				.setColorCaptionLabel(255).setDecimalPrecision(0)
				// after the initialization we turn broadcast back on again
				.setBroadcast(true);

		this.viewBtn = cp5.addRadioButton("viewRadioButton")
				.setPosition(780, 30 * 20).setSize(20, 20).setSpacingRow(20)
				.setColorForeground(color(100)).setColorActive(color(255))
				.setItemsPerRow(2).setColorBackground(color(50))
				.setSpacingColumn(50).addItem("Transport/Aufenthalt", 1);

		viewBtn.getItem(0).captionLabel().setColorBackground(color(20));
		viewBtn.getItem(0).setColorCaptionLabel(color(255));
		viewBtn.getItem(0).captionLabel().style().moveMargin(0, 0, 0, 0);
		viewBtn.getItem(0).captionLabel().style().movePadding(0, 0, 0, 0);
		viewBtn.getItem(0).captionLabel().style().backgroundWidth = 0;
		viewBtn.getItem(0).captionLabel().style().backgroundHeight = 0;

		this.range.setLabelVisible(false);
		this.dayBtn.activate(0);
		this.personCheckb.activate(0);
		this.vizBtn.activate(0);
		this.styleBtn.activate(0);
		this.dayCheckb.activateAll();
		this.viewBtn.activate(0);
		this.dayCheckb.setVisible(false);
		this.range.setVisible(false);
		this.viewBtn.setVisible(false);
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

	public void setControl() {
		// SPpinnensate
		if (this.vizBtn.getItem(2).getState()
				&& this.styleBtn.getItem(1).getState()) {
			this.dayBtn.setVisible(false);
			this.dayCheckb.setVisible(true);
			this.range.setVisible(false);
			this.viewBtn.setVisible(false);
		}

		if (this.vizBtn.getItem(2).getState()
				&& this.styleBtn.getItem(0).getState()) {
			this.dayBtn.setVisible(true);
			this.dayCheckb.setVisible(false);
			this.range.setVisible(false);
			this.viewBtn.setVisible(false);
		}

		// SpiralStates
		if (this.vizBtn.getItem(1).getState()) {
			this.dayBtn.setVisible(false);
			this.dayCheckb.setVisible(false);
			this.range.setVisible(true);
			textFont(this.Headlines, 10);
			text("MO", 780, 19 * 20);
			text("SO", 950, 19 * 20);
			this.viewBtn.setVisible(true);
		}

		// BundlingStates
		if (this.vizBtn.getItem(0).getState()) {
			this.dayBtn.setVisible(false);
			this.dayCheckb.setVisible(true);
			this.dayCheckb.activateAll();
			this.aDay = new String[7];
			for (int i = 0; i < 7; i++) {
				this.aDay[i] = this.dayCheckb.getItem(i).getLabel();
			}
			this.range.setVisible(false);
			this.viewBtn.setVisible(false);
		}

	}

	public void vizRadioButton(int a) {
		if (a < 0) {
			this.vizBtn.activate(0);
		}
		this.redraw = true;
	}

	public void dayRadioButton(int a) {
		if (a < 0) {
			this.dayBtn.activate(0);
			this.sDay = this.dayBtn.getItem(0).getName();
		} else
			this.sDay = this.dayBtn.getItem(a - 1).getName();
		this.redraw = true;
	}

	public void personCheckBox(float[] a) {
		int personCount = 0;
		this.sHuman = new String[4];
		for (int i = 0; i < 4; i++) {
			if (this.personCheckb.getItem(i).getState()) {
				personCount++;
			}
		}

		if (personCount > 0) {
			aHuman = new Human[personCount];
			int z = 0;
			for (int i = 0; i < 4; i++) {
				if (this.personCheckb.getItem(i).getState()) {
					aHuman[z] = d.getPerson(this.personCheckb.getItem(i)
							.getName());
					this.sHuman[z] = this.personCheckb.getItem(i).getName();
					z++;
				}
			}
		} else {
			this.aHuman = d.getPersons();
			this.personCheckb.activateAll();
			for (int i = 0; i < 4; i++) {
				this.sHuman[i] = this.personCheckb.getItem(i).getName();
			}
		}
		this.redraw = true;
	}

	public void styleRadioButton(int a) {
		if (a < 0) {
			this.styleBtn.activate(0);
		}
		this.redraw = true;
	}

	public void dayCheckBox(float[] a) {
		int dayCount = 0;
		
		for (int i = 0; i < 7; i++) {
			if (this.dayCheckb.getItem(i).getState()) {
				dayCount++;
			}
		}

		if (dayCount > 0) {
			aDay = new String[dayCount];
			int z = 0;
			for (int i = 0; i < 7; i++) {
				if (this.dayCheckb.getItem(i).getState()) {
					aDay[z] = this.dayCheckb.getItem(i).getLabel();
					z++;
				}
			}
		} else {
			this.dayCheckb.activateAll();
			this.aDay = new String[7];
			for (int i = 0; i < 7; i++) {
				this.aDay[i] = this.dayCheckb.getItem(i).getLabel();
			}
		}
		this.redraw = true;
	}

	public void viewRadioButton(int a){
		this.redraw = true;
	}
	
	public void controlEvent(ControlEvent theControlEvent) {
		if (theControlEvent.isFrom("rangeController")) {

			/*
			 * this.range.setBroadcast(false);
			 * this.range.setArrayValue(Math.round(this.range.getArrayValue(0)),
			 * Math.round(this.range.getArrayValue(1)));
			 * this.range.setRangeValues
			 * (Math.round(this.range.getArrayValue(0)),
			 * Math.round(this.range.getArrayValue(1)));
			 * this.range.setBroadcast(true);
			 */
		}

	}

}
