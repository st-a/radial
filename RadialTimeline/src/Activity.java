
public class Activity {
	


	String day,catagory,transport,start,end;
	Human human;
	int distance, duration;
	int[] bTime;
	int[] eTime;
	
	public Activity(Human h, String d, String c, String s, String e, String t, String du, String dist, String bTh, String bTm, String eTh, String eTm) {
		human = h;
		this.day = d;
		this.catagory = c;
		this.start = s;
		this.end = e;
		this.transport = t;
		
		
		
		bTime = new int[2];
		bTime[0] = Integer.parseInt(bTh);
		bTime[1] = Integer.parseInt(bTm);
		
		eTime = new int[2];
		eTime[0] = Integer.parseInt(eTh);
		eTime[1] = Integer.parseInt(eTm);
		
		if(dist == "") this.duration = 0;
		
		else this.duration = Integer.parseInt(du);		
		
		if(dist == "") this.distance = 0;
		else this.distance = Integer.parseInt(dist);
		
		if(h.name.equals("Tom")){
			System.out.println("dur: " + duration);
			System.out.println("start: " + bTime[0] + ":" + bTime[1]);
		}
	}

	
	public Human getHuman(){
		return human;
	}
	
	public String getDay(){
		return this.day;
	}
	
	public int getDistance(){
		return this.distance;
	}
	
	public int[] getBeginTime(){
		return this.bTime;
	}
	
	public int[] getEndTime(){
		return this.eTime;
	}
}



