import processing.core.PApplet;
import processing.core.PFont;


public class Diagramm {
	
	PApplet p;
	float posX, posY;
	float radius = 300;
	PFont f;
	Distance dStatistic;
	Timeline[] timelines;
	int[][] lines;
	int [][] colors = {{54, 146, 179},{158, 219, 41},{255, 243, 68},{253, 77, 72}};
	float scale;
	int[] d; 
	Dataset dataset;
	
	
	  Diagramm(PApplet parent, float x, float y, int[] distance, int[][] l,Dataset data, float s) {
		  	  
		    p = parent;
		    posX = x;
		    posY = y;
		    scale = s;
		    radius = radius/scale;
		    dataset = data;
		    d = distance;
		    lines = l;
		    timelines = new Timeline[lines.length];
			dStatistic = new Distance(p, d, this);
		    this.draw(posX, posY, lines,dataset.getPersons(), scale);
	  }
	
	  
	public void draw(float x, float y, int[][] lines,Human[] humans,float s){	
		radius = 300/scale;
	    posX = x;
	    posY = y;
		scale = s;
	    int angle = 90;
	    int frequency = 15;
		   
		    //km-Statistic
			  dStatistic.draw(x,y);
			  p.noFill();
		

			  //aeuseren Ringe
			  p.ellipseMode(p.CENTER);
			  p.stroke(50);	
			  p.ellipse(posX, posY,520/scale,520/scale);
			  p.ellipse(posX, posY,460/scale,460/scale);
			  p.ellipse(posX, posY,400/scale,400/scale);
			  p.ellipse(posX, posY,340/scale,340/scale);
			  p.ellipse(posX, posY,280/scale,280/scale);
			  p.ellipse(posX, posY,220/scale,220/scale);
			  p.ellipse(posX, posY,160/scale,160/scale);

			  //Skalenstriche
			  for (int i=0; i < 40; i++){
				  p.stroke(50);	  
				  if(i>23){
					  p.stroke(65);
					  frequency = 30;
				  }
				  if(i>35){
					  p.stroke(100);
					  frequency = 90;	  
				  } 
				  p.line(posX, posY, this.cosPx(posX, angle, 300/scale), this.sinPy(posY, angle, 300/scale));
			  	  angle -= frequency;
			  	}
			  
			  //innere Kreis
			  p.stroke(42);
			  p.fill(42);
			  p.ellipse(posX, posY, 160/scale, 160/scale);	
			  angle = 90;
			  p.noFill();
			  
			  
			  //Uhr innen
			  for (int i=0; i < 40; i++){
			  p.stroke(255);
			  p.strokeWeight(1/scale); 
			  p.line(posX, posY, this.cosPx(posX, angle, 20/scale), this.sinPy(posY, angle, 20/scale));
			  angle -= 30;
			  }
			  
			  //Uhr innerer Kreis
			  p.noStroke();
			  p.fill(42);
			  p.ellipse(posX, posY, 25/scale, 25/scale);	
			  	  
			  
			  //Beschriftung
			  f = p.createFont("Futura-Medium", 16/scale);
			  p.textFont(f);
			  p.textAlign(p.CENTER, p.CENTER);
			  p.fill(255);
			  p.text("0", posX, posY-(40/scale));
			  p.text("6", posX+(40/scale), posY);
			  p.text("12", posX, posY+(40/scale));
			  p.text("18", posX-(40/scale), posY);
			  
			
			  //Timelines
			  
			 for(int i=0; i < humans.length; i++){
				  int[] h = new int[12];
				  for(int j=0; j < lines[i].length; j++){
					h[j] = lines[i][j];
				  }
				timelines[i] = new Timeline(p, h, colors[i][0], colors[i][1], colors[i][2], this);
					  
				timelines[i].drawLine();
  
			  }
			  
			 
			 //Skalenpunkte
			  angle = 90;
			  radius = 260/scale;
			  frequency = 30;
			  
			  for(int i=0; i<(5*12); i++){
				  p.fill(230);
				  p.noStroke();
				  if(i%12==0)
					  radius = radius-30/scale;
				  p.ellipse(this.cosPx(posX, angle, radius), this.sinPy(posY, angle, radius),3/scale,3/scale);
				  angle -= frequency;
			  }
			  
			  
			  p.noStroke();
			/*  int pos = 110;
			  for(int i=0; i< 7; i++){
				  
				  if(!((i+1)%2==0)){
					  p.fill(255);
					  p.ellipse(posX, posY+pos/scale,25/scale,25/scale);
					  p.fill(0);
					  p.textSize(12/scale);
					  p.text((this.dStatistic.getHighest()/7)*(i+1), posX, posY+(pos/scale)-1);
				  }
				  if(i<5){
					  p.fill(0);  
					  p.ellipse(posX, posY-(pos/scale),25/scale,25/scale);
				  } 
					pos += 30;
				  
				  
			  }*/

	}
		    
		    
	  
	  //Funktionen
	  
	  public float cosPx(float px,int angle, float r){
		float x = px + p.cos(p.radians(angle))*(r);
		return x;
	  }
	  
	  public float sinPy(float py,int angle, float r){
		float y = py + p.sin(p.radians(angle))*(r);
		return y;
	  }
	  
	  public float getScale(){
		  return this.scale;
	  }
	  
	  
	  public void scale(float s){
			 
		  this.draw(posX, posY, lines,dataset.getPersons(), s);
	  }
		 
	  public void transform(float x, float y){
		  this.draw(x, y, lines,dataset.getPersons(), scale);
	  }
	  
	  public void drawMatrix(Human[] h){
		  p.background(42);
		  
		  for(int i=0; i < h.length; i++){
			  f = p.createFont("Futura-Medium", 50/(h.length/2));
			  p.textFont(f);
			  p.textAlign(p.LEFT, p.CENTER);
			  p.fill(255);
			  p.text(h[i].getName(),60+(130*i), 30);
			  
			  this.draw(90+(130*i), 130,lines,dataset.getPersons(), 5.1f);
			 /* for(int j=0; j < ; j++){

			  }*/
		  }
	  }
	  
	  

}
