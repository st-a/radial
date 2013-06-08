import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class place extends PApplet {

	private static final long serialVersionUID = 7154513646954860917L;

	public String name;
	public float percentage;
	public PImage icon;
	public int backgroundcolor;
	public int secondColor;
	public int index;

	public float start;
	public float end;
	public float lenght; // arcLength in degrees
	public float middle;
	public float currentLengthOfIncomingLinks = 0.5f; //length in degrees
	public float currentLengtgOfOutgoingLinks = 0;
	public float[][] outgoingLinks = new float[4][5];
	public float[] outgoings = new float[5];
	public float[] incomings = new float[5];

	public place(int index,String name, float percentage, String pathToIcon) {
		this.index = index;
		this.name = name;
		this.percentage = percentage;
		icon = loadImage(pathToIcon);

		setLinks();
	}

	public void setLinks() {
		
		//First Try, maybe i'll use this later, please dont touch ;)
		
		// configure outgoing links
		// its a 4x5 matrix, cause its 5 Places we could go to and 4 vehicles
		// add data here ... later
		/*
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				//example: 	ougoingLinks[0][x]	=	bike to place x
				//			outgoingLinks[1][4]	=	tram to place 4 (freeTime)
				//			outgoingLinks[2][3]	=	car to place 3 (work)
				//			outgoingLinks[3][0]	=	by foot to place 0 (home)
				this.outgoingLinks[i][j] = 1 / 16f;
				if(j==this.index){
					this.outgoingLinks[i][j] = 0;
				}

			}
		}
		*/
		/*for(int i=0;i<outgoings.length;i++){
			outgoings[i] = 0.25f;
			if(i==this.index){
				println("Index: " + this.index);
				outgoings[i] = 0f;
			}
		}*/
	}

	public void setColor(int color) {
		this.backgroundcolor = color;
	}
	
	public void setSecondColor(int color){
		this.secondColor = color;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setOutgoings(float[] out){
		this.outgoings = out;
	}
	
	public void computeIncomings(ArrayList<place> places){
		float sumOfIncommings=0;
		
		for (int i=0;i<places.size();i++){
			this.incomings[i] = places.get(i).outgoings[index] * 
								places.get(i).lenght;
			sumOfIncommings +=this.incomings[i];
		}
		
		float[] incomingsInPerc = new float[places.size()];
		
		for(int j=0;j<places.size();j++){
			incomingsInPerc[j]= this.incomings[j]/sumOfIncommings;
		}
		for(int j=0;j<places.size();j++){
			incomings[j] = incomingsInPerc[j];
		}
		
	}
}
