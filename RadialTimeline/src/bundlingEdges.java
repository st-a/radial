import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;

public class bundlingEdges extends PApplet{

	private static final long serialVersionUID = 6838248409653034981L;

	// global variables
	
	public float centerX;
	public float centerY;
	public float radius;
	public float scale;
	String person;
	
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
	
	//constructor
	bundlingEdges(String Person,float cntrX,float cntrY,float radius){
		this.person = Person;
		this.centerX = cntrX;
		this.centerY = cntrY;
		this.radius = radius;
		
		circumference = 2 * PI * radius;
		alpha = 60f;
		densityOfLines = 10f;

		ammountOfPlaces = 5; // home, Uni, Social, Work, Freetime
		
		helper = new helperForBundlingEdges(centerX, centerY, radius, ammountOfPlaces);
		
		namesOfPlaces = new String[5];

		// independent from the ammount of arcs the free space between
		// the arcs will allways beu 10% of the circumference
		// freeSpace equals 36deg/ammountOfPlaces
		freeSpace = (0.1f * circumference) / ammountOfPlaces;
		freeSpaceInDegrees = (0.1f * 360) / ammountOfPlaces;

		drawBeziers = true;
	}
	
	//setter + getter
	public void setRadius(float rad){
		this.radius = rad;
		scale = rad/200;
		println("scale: " + scale);
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

		Dataset dtst = new Dataset("../src/Data/data.XML");

		Activity[] allActivities = dtst.getPersonActivities(person);
		
		
		// my data set
		/*
		float[] outOfHome = { 1 / 7f, 1 / 7f, 2 / 7f, 5 / 14f, 1 / 14f };
		float[] outOfUni = { 1f, 0f, 0f, 0f, 0f };
		float[] outOfSocial = { 5 / 6f, 0f, 0f, 0f, 1 / 6f };
		float[] outOfWork = { 3 / 5f, 0f, 1 / 5f, 0f, 1 / 5f };
		float[] outOfFreeTime = { 3 / 5f, 0f, 0f, 0f, 2 / 5f };
		*/
		
		float[][] placesAndPercentages = new float[5][5];

		placesAndPercentages[0] = outOfHome;
		placesAndPercentages[1] = outOfUni;
		placesAndPercentages[2] = outOfSocial;
		placesAndPercentages[3] = outOfWork;
		placesAndPercentages[4] = outOfFreeTime;
		
		for(int i=1;i<allActivities.length;i++){
			Activity act1 = allActivities[i];
			Activity act0 = allActivities[i-1];

			int index0 = 1;
			int index1 = 1;
			
			for(int j=0;j<5;j++){
				if(act0.catagory.equals(namesOfPlaces[j])){
					index1 = j;
				}
				if(act1.catagory.equals(namesOfPlaces[j])){
					index0 = j;
				}
			}
			placesAndPercentages[index1][index0] += 1f;
		}
		
		
		for(int i=0;i<5;i++){
			float sumOfOne = 0;
			for(int j=0;j<5;j++){
				sumOfOne += placesAndPercentages[i][j];
			}
			for(int j=0;j<5;j++){
				placesAndPercentages[i][j] = placesAndPercentages[i][j]/sumOfOne;
			}
		}

		float[] percenteges = helper.calculatePercenteges(placesAndPercentages);
		
		place home = new place(0, "home", percenteges[0], "../src/Icons/iconHome.png");
		home.setColor(color(255, 123, 106));
		home.setIndex(0);
		home.setOutgoings(outOfHome);
		this.places.add(home);

		place uni = new place(1, "uni", percenteges[1], "../src/Icons/iconUni.png");
		uni.setColor(color(255, 242, 190));
		uni.setIndex(1);
		uni.setOutgoings(outOfUni);
		this.places.add(uni);

		place social = new place(2, "social", percenteges[2],
				"../src/Icons/iconSocial.png");
		social.setColor(color(170, 235, 140));
		social.setIndex(2);
		social.setOutgoings(outOfSocial);
		this.places.add(social);

		place work = new place(3, "work", percenteges[3], "../src/Icons/iconWork.png");
		work.setColor(color(53, 189, 144));
		work.setIndex(3);
		work.setOutgoings(outOfWork);
		this.places.add(work);

		place freeTime = new place(4, "freeTime", percenteges[4],
				"../src/Icons/iconBeer.png");
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
	
	public void resetEverything(){
		// Resetting everything that was changed
		for (int k = 0; k < ammountOfPlaces; k++) {
			places.get(k).currentLengtgOfOutgoingLinks = 0f;
			places.get(k).currentLengthOfIncomingLinks = 0.5f;
		}
	}
}