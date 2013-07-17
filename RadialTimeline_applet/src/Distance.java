import processing.core.PApplet;

public class Distance {

	PApplet p;
	int[] distance;
	int highest = 0;
	Diagramm dia;

	Distance(PApplet parent, Diagramm diagramm, Human[] h, String day) {
		distance = new int[24];
		int[] activityCount = new int[24];
		p = parent;
		dia = diagramm;

		for(int i = 0; i < 24; i++){
			distance[i] = 0;
			activityCount[i] = 0;
		}
		
		
		for (int n = 0; n < h.length; n++) {
			Activity[] aAct = dia.dataset.getActivityByDayAndPerson(day,
					h[n].getName());
			for (int i = 1; i < 25; i++) {
				
				for (int j = 0; j < aAct.length; j++) {
					if ((aAct[j].getBeginTime()[0] >= (i - 1))
							&& (aAct[j].getBeginTime()[0] < i)) {
						if ((i - 1) < 6){
							this.distance[i + 17] = this.distance[i + 17]
									+ aAct[j].distance;
							activityCount[i+17]++;
						}
						else{
							this.distance[i - 7] = this.distance[i - 7]
									+ aAct[j].distance;
							activityCount[i-7]++;
						}
					}
				}
			}
		}
		
		for(int i = 0; i < 24; i++){
			if(activityCount[i] != 0)
			distance[i] = distance[i]/activityCount[i];		
		}

		for (int i = 0; i < this.distance.length; i++) {
			if (highest < this.distance[i])
				highest = distance[i];
		}

	}

	Distance(PApplet parent, Diagramm diagramm, Human h, String day) {
		distance = new int[24];
		p = parent;
		dia = diagramm;

		Activity[] aAct = dia.dataset.getActivityByDayAndPerson(day,
				h.getName());
		for (int i = 1; i < 25; i++) {
			int activityCount = 0;
			for (int j = 0; j < aAct.length; j++) {

				if ((aAct[j].getBeginTime()[0] >= (i - 1))
						&& (aAct[j].getBeginTime()[0] < i)) {
					activityCount++;
					if ((i - 1) < 6)
						this.distance[i + 17] = this.distance[i + 17]
								+ aAct[j].distance;
					else
						this.distance[i - 7] = this.distance[i - 7]
								+ aAct[j].distance;
				}
			}
			if (activityCount == 0) {
				if ((i - 1) < 6)
					this.distance[i + 17] = 0;
				else
					this.distance[i - 7] = 0;
			} else {
				if ((i - 1) < 6)
					this.distance[i + 17] = (int) (this.distance[i + 17] / activityCount);
				else
					this.distance[i - 7] = (int) (this.distance[i - 7] / activityCount);
			}
		}

		for (int i = 0; i < this.distance.length; i++) {
			if (highest < this.distance[i])
				highest = distance[i];
		}

	}

	public void draw(float x, float y) {
		int angle = 15;
		float lastAngle = 0;
		int fontStart = 5;
		p.noStroke();
		p.ellipseMode(p.CENTER);

		if (this.highest != 0) {
			for (int i = 0; i < 24; i++) {
				p.fill(70);
				float klaus = (this.distance[i] * 390) / this.highest + 210;
				float diameter = (float) (p.min(p.height, klaus / dia.scale));
				p.arc(x, y, diameter, diameter, lastAngle,
						lastAngle + p.radians(angle));
				lastAngle += p.radians(angle);
				
				if(this.distance[i] != 0){
				float radius = 320 / dia.scale;	
				p.textFont(this.dia.f, 12 / dia.scale);
				p.textAlign(p.CENTER, p.CENTER);
				p.fill(255);
				p.text(this.distance[i]+"m", dia.cosPx(dia.posX, fontStart, radius), dia.sinPy(dia.posY, fontStart, radius));
				}
				p.noFill();
				fontStart += angle;
			}
		}

	}

	public int getHighest() {
		return this.highest;
	}

}
