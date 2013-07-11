import processing.core.PApplet;

public class Distance {

	PApplet p;
	int[] distance = new int[12];
	int highest = 0;
	Diagramm dia;

	Distance(PApplet parent, Diagramm diagramm, Human[] h, String day) {
		p = parent;

		dia = diagramm;
		for (int i = 0; i < this.distance.length; i++) {
			if (highest < this.distance[i])
				highest = distance[i];
		}

	}

	Distance(PApplet parent, Diagramm diagramm, Human h, String day) {
		p = parent;
		dia = diagramm;

		Activity[] aAct = dia.dataset.getActivityByDayAndPerson(day,
				h.getName());
		for (int i = 1; i < 13; i++) {
			int activityCount = 0;
			for (int j = 0; j < aAct.length; j++) {

				if ((aAct[j].getBeginTime()[0] >= (i - 1) * 2)
						&& (aAct[j].getBeginTime()[0] < i * 2)) {
					activityCount++;
					if ((i - 1) < 3)
						this.distance[i+ 8] = this.distance[i + 8]
								+ aAct[j].distance;
					else
						this.distance[i - 4] = this.distance[i - 4]
								+ aAct[j].distance;
				}
			}
			if (activityCount == 0) {
				if ((i - 1) < 3)
					this.distance[i + 8] = 0;
				else
					this.distance[i - 4] = 0;
			} else {
				if ((i - 1) < 3)
					this.distance[i + 8] = (int)(this.distance[i + 8]
							/ activityCount);
				else
					this.distance[i - 4] = (int)(this.distance[i - 4]
							/ activityCount);
			}
		}

		for (int i = 0; i < this.distance.length; i++) {
			if (highest < this.distance[i])
				highest = distance[i];
		}

	}

	public void draw(float x, float y) {
		int angle = 30;
		float lastAngle = 0;
		p.noStroke();
		p.fill(70);
		p.ellipseMode(p.CENTER);

		if(this.highest != 0){
		for (int i = 0; i < 12; i++) {
			float klaus = (this.distance[i] * 420) / this.highest + 160;
			float diameter = (float) (p.min(p.height, klaus / dia.scale));
			p.arc(x, y, diameter, diameter, lastAngle,
					lastAngle + p.radians(angle));
			lastAngle += p.radians(angle);
		}
		}

	}

	public int getHighest() {
		return this.highest;
	}

}
