import processing.core.PApplet;
import controlP5.ControlP5;


public class Interaktion {
	
	PApplet p;
	ControlP5 button;
	Diagramm dia;
	float frequency, oldScale;
	boolean scale = false, first = true;
	
	 Interaktion(PApplet applet, Diagramm d) {
		p = applet;
		dia = d;
		button = new ControlP5(p);
		button.addSlider("slider")
		     .setPosition(100,605)
		     .setSize(200,20)
		     .setRange(1,5)
		     .setValue(2)
		     ;
	}
	 
	 
	 
	public void scale() {
		if(first){
			oldScale = dia.getScale();
			this.first = false;
		}
		
		//verkleinern
		if(this.oldScale < this.frequency){
			
			if((300/dia.getScale())>(300/this.frequency)){
				p.background(42);
				dia.scale(dia.getScale()+(this.frequency/20));
			}
			else{ first = true; scale = false;}
		}
		
		//vergroessern
		if(this.oldScale > this.frequency){
			
			if((300/dia.getScale())<(300/this.frequency)){
				p.background(42);
				dia.scale(dia.getScale()-(this.frequency/10));
			}
			else{ first = true; scale = false;}
		}
		}
		
	public void setFrequency(float f){
		this.frequency = f;
	}
	 
	public boolean getScale(){
		return this.scale;
	}
	
	public void setScale(boolean s){
		this.scale = s;
	}
	

}
