import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Dataset {

	Activity[] activities;
	Human[] humans;
	
	
	public Dataset (String s){
		
		humans = new Human[4];
		activities = new Activity[300];
		int x = 0;
		
		try{
			File stocks = new File(s);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(stocks);
			doc.getDocumentElement().normalize();

			
			NodeList nList = doc.getElementsByTagName("person");
		
			for (int i = 0; i < nList.getLength(); i++) {
				Node node = nList.item(i);
				Element e = (Element) node;
				humans[i] = new Human(e.getAttribute("name"));
				
				for(int j = 0; j < e.getElementsByTagName("tag").getLength(); j++){
					Element day = (Element) e.getElementsByTagName("tag").item(j);
					String sDay = day.getAttribute("name");

						for(int k = 0; k < day.getElementsByTagName("activity").getLength() ;k++){
							Element a = (Element) day.getElementsByTagName("activity").item(k);
							
							
							String begin= "0";
							String end= "0";
							String category= "0";
							String transport= "0";
							String duration= "0";
							String distance= "0";
							String bTimeH= "0";
							String bTimeM= "0";
							String eTimeH= "0";
							String eTimeM = "0";
							
							

							if(a.getElementsByTagName("begin").item(0) != null)
								begin = a.getElementsByTagName("begin").item(0).getTextContent();
							
							if(a.getElementsByTagName("end").item(0) != null)
								end = 		a.getElementsByTagName("end").item(0).getTextContent();

							if(a.getElementsByTagName("category").item(0) != null)
								category = 	a.getElementsByTagName("category").item(0).getTextContent();
							
							if(a.getElementsByTagName("transport").item(0) != null)
								transport = a.getElementsByTagName("transport").item(0).getTextContent();
							
							if(a.getElementsByTagName("duration").item(0) != null)
								duration = 	a.getElementsByTagName("duration").item(0).getTextContent();
							
							if(a.getElementsByTagName("distance").item(0) != null)	
								distance = 	a.getElementsByTagName("distance").item(0).getTextContent();
								
							if(a.getElementsByTagName("beginTime").item(0) != null)	{
								Element bTime = (Element) a.getElementsByTagName("beginTime").item(0);
								bTimeH = bTime.getAttribute("hour");
								bTimeM = bTime.getAttribute("minutes");
							}
								
							if(a.getElementsByTagName("endTime").item(0) != null)	{
								Element eTime = (Element) a.getElementsByTagName("endTime").item(0);
								eTimeH = eTime.getAttribute("hour");
								eTimeM = eTime.getAttribute("minutes");
							}

							
							


							this.activities[x] = new Activity(humans[i], sDay, category, begin, end, transport, duration, distance, bTimeH, bTimeM, eTimeH, eTimeM);
							x++;
						}
				}
			}

		
		}
	
 catch (Exception e) {
e.printStackTrace();
 }
	}
	
	
	public Human getPerson(String name){
		Human h = null;
		for (Human hu : this.humans){
			if (hu != null){
				if(hu.getName().equals(name)) return hu;
			}	
		}
		return h;
	}
	
	public Human[] getPersons(){
		return this.humans;
	}
	
	public Activity[] getPersonActivities(String name){
		int i = 0;
		for (Activity a :this.activities){
			if(a != null){
			if(a.getHuman().getName().equals(name)){
				++i;
				}
			}
		}
		
		Activity[] aArray = new Activity[i];
		int j= 0;
		
		for (Activity a :this.activities){
			if(a != null){
				if(a.getHuman().getName().equals(name)){
					aArray[j] = a;
					++j;
				}
			}
		}
		
		return aArray;
	}
	
	public Activity[] getActivities(){
		return this.activities;
	}
	
	public Activity[] getActivityByDay(String day){
		int i = 0;
		for (Activity a :this.activities){
			if(a != null){
			if(a.getDay().equals(day)){
				++i;
				}
			}
		}
		
		Activity[] aArray = new Activity[i];
		int j= 0;
		
		for (Activity a :this.activities){
			if(a != null){
				if(a.getDay().equals(day)){
					aArray[j] = a;
					++j;
				}
			}
		}	
		
		return aArray;
	}
	
	public Activity[] getActivityByDayAndPerson(String day, String name){
		int i = 0;
		for (Activity a :this.activities){
			if(a != null){
			if(a.getDay().equals(day) && a.getHuman().getName().equals(name)){
				++i;
				}
			}
		}
		
		Activity[] aArray = new Activity[i];
		int j= 0;
		
		for (Activity a :this.activities){
			if(a != null){
				if(a.getDay().equals(day) && a.getHuman().getName().equals(name)){
					aArray[j] = a;
					++j;
				}
			}
		}	
		
		return aArray;
	}
	
	public Activity lastActivity(String day,String name){
		Activity[] aArray = this.getActivityByDayAndPerson(day, name);
		return aArray[aArray.length-1];		
	}
	
	public Activity firstActivity(String day,String name){
		Activity[] aArray = this.getActivityByDayAndPerson(day, name);
		return aArray[0];		
	}
	
	
	
	

	}

