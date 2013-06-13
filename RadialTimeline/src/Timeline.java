import processing.core.*;


public class Timeline {
	
	PApplet parent;
	int[] color = new int[3];
	int[] points;
	int r, g, b;
	Diagramm dia;
	
	  Timeline(PApplet p, int[] point, int red, int green, int blue, Diagramm diagramm) {
		    parent = p;
		    points = point;
		    r = red;
		    g = green;
		    b = blue;
		    dia = diagramm;
		  }
	  
	  public void drawLine(){
		  parent.stroke(r,g,b,190);
		  parent.strokeWeight(4/dia.scale);
		  int angle = -90;
		  float radius = dia.radius;
		  float x1, y1;  
		  
			if(points[0] == 0)  radius = 110/dia.scale;
			if(points[0] == 1)  radius = 140/dia.scale;
			if(points[0] == 2)  radius = 170/dia.scale;
			if(points[0] == 3)  radius = 200/dia.scale;
			if(points[0] == 4)  radius = 230/dia.scale;

	      x1 = dia.cosPx(dia.posX, angle, radius);
	      y1 = dia.sinPy(dia.posY, angle, radius);
	      float startX = x1;
	      float startY = y1;
	      
		  for (int i=1; i<12; i++){
			  angle += 30;
		
				if(points[i] == 0)  radius = 110/dia.scale;
				if(points[i] == 1)  radius = 140/dia.scale;
				if(points[i] == 2)  radius = 170/dia.scale;
				if(points[i] == 3)  radius = 200/dia.scale;
				if(points[i] == 4)  radius = 230/dia.scale;
			 				
			  float x2 = dia.cosPx(dia.posX, angle, radius);
			  float y2 = dia.sinPy(dia.posY, angle, radius);
			  parent.line(x1, y1, x2, y2);
			  x1 = x2;
			  y1 = y2;		
			  }	
		  parent.line(x1,y1,startX, startY);
		  parent.stroke(0);
		  parent.strokeWeight(1);
			}
		  
	  

	  
	  
}
