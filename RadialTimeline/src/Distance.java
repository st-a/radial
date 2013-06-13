import processing.core.PApplet;


public class Distance {
	
	PApplet p;
	int[] distance;
	int highest = 0;
	Diagramm dia;

	  Distance(PApplet parent, int[] d, Diagramm diagramm) {
		    p = parent;
		    distance = d;
		    dia = diagramm;
		    for(int i = 0; i < this.distance.length; i++ ){
		    	if ( highest < this.distance[i]) highest = distance[i];
		    }
		    
		  }
	  
	  
	  public void draw(float x,float y){
		  int angle = 30;
		  float lastAngle = 0;
		  p.noStroke();
		  p.fill(70);
		  p.ellipseMode(p.CENTER);

		  for (int i=0; i<12; i++){
			 float klaus = (this.distance[i] * 420)/this.highest +160 ;
			 float diameter = (float) (p.min(p.height, klaus/dia.scale));
			  p.arc(x, y, diameter, diameter, lastAngle, lastAngle+p.radians(angle));
			  lastAngle += p.radians(angle);
		  }

	  }
	  
	  public int getHighest(){
		  return this.highest;
	  }
		
}
