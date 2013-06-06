
public class Activity {
	


	String day,catagory,transport,start,end,eTime,sTime, distance, duration;
	Human human;
	
	public Activity(Human h, String d, String c, String s, String e, String t, String du, String dist) {
		human = h;
		this.day = d;
		this.catagory = c;
		this.start = s;
		this.end = e;
		this.transport = t;
		this.duration = du;
		this.distance = dist;
	}

	
	public Human getHuman(){
		return human;
	}
	
	public String getDay(){
		return this.day;
	}
}



