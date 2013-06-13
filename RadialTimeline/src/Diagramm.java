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
	int [][] colors = {{236, 0, 140},{180, 210, 53},{40, 170, 225},{255, 198, 11}};
	float scale;
	int[] d; 
	boolean type;
	
	
	  Diagramm(PApplet parent, float x, float y, int[] distance, int[][] l, boolean t, float s) {
		  	  
		    p = parent;
		    posX = x;
		    posY = y;
		    scale = s;
		    radius = radius/scale;
		    d = distance;
		    type = t;
		    lines = l;
		    timelines = new Timeline[lines.length];
			dStatistic = new Distance(p, d, this);
		    this.draw(posX, posY, d, lines, type, scale);
	  }
	
	 public void scale(float s){
		 
		 this.draw(posX, posY, d, lines, type, s);
	 }
	 
	 public void transform(float x, float y){
		 this.draw(x, y, d, lines, type, scale);
	 } 
	  
	  
	public void draw(float x, float y, int[] d, int[][] lines, boolean type,float s){	
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
			  p.stroke(245);	
			  p.ellipse(posX, posY,520/scale,520/scale);
			  p.ellipse(posX, posY,460/scale,460/scale);
			  p.ellipse(posX, posY,400/scale,400/scale);
			  p.ellipse(posX, posY,340/scale,340/scale);
			  p.ellipse(posX, posY,280/scale,280/scale);
			  p.ellipse(posX, posY,220/scale,220/scale);
			  p.ellipse(posX, posY,160/scale,160/scale);

			  //Skalenstriche
			  for (int i=0; i < 40; i++){
				  p.stroke(245);	  
				  if(i>23){
					  p.stroke(200);
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
			  p.stroke(255);
			  p.fill(255);
			  p.ellipse(posX, posY, 160/scale, 160/scale);	
			  angle = 90;
			  p.noFill();
			  
			  
			  //Uhr innen
			  for (int i=0; i < 40; i++){
			  p.stroke(100);
			  p.line(posX, posY, this.cosPx(posX, angle, 20/scale), this.sinPy(posY, angle, 20/scale));
			  angle -= 30;
			  }
			  
			  //Uhr innerer Kreis
			  p.noStroke();
			  p.fill(255);
			  p.ellipse(posX, posY, 25/scale, 25/scale);	
			  	  
			  
			  //Beschriftung
			  f = p.createFont("Futura-Medium", 16/scale);
			  p.textFont(f);
			  p.textAlign(p.CENTER, p.CENTER);
			  p.fill(0);
			  p.text("0", posX, posY-(40/scale));
			  p.text("6", posX+(40/scale), posY);
			  p.text("12", posX, posY+(40/scale));
			  p.text("18", posX-(40/scale), posY);
			  
			
			  //Timelines
			 for(int i=0; i < lines.length; i++){
				  int[] h = new int[12];
				  for(int j=0; j < lines[i].length; j++){
					h[j] = lines[i][j];
				  }
				  if(type)
					  timelines[i] = new Timeline(p, h, colors[i][0], colors[i][1], colors[i][2], this);
				  else
					  timelines[i] = new Timeline(p, h, 150, 150, 150, this);
					  
				timelines[i].drawLine();
  
			  }
			  
			 
			 //Skalenpunkte
			  angle = 90;
			  radius = 260/scale;
			  frequency = 30;
			  
			  for(int i=0; i<(5*12); i++){
				  p.fill(100);
				  p.noStroke();
				  if(i%12==0)
					  radius = radius-30/scale;
				  p.ellipse(this.cosPx(posX, angle, radius), this.sinPy(posY, angle, radius),5/scale,5/scale);
				  angle -= frequency;
			  }
			  
			  
			  p.noStroke();
			  int pos = 110;
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
				  
				  
			  }

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
	  
	  

}
