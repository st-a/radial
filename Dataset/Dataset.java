import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Dataset {

	String source;
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
							Element a = (Element) e.getElementsByTagName("activity").item(k);	
							String begin = 		a.getElementsByTagName("begin").item(0).getTextContent();
							String end = 		a.getElementsByTagName("end").item(0).getTextContent();
							String category = 	a.getElementsByTagName("category").item(0).getTextContent();
							String transport = 	a.getElementsByTagName("transport").item(0).getTextContent();
							String duration = 	a.getElementsByTagName("duration").item(0).getTextContent();
							String distance = 	a.getElementsByTagName("distance").item(0).getTextContent();
							
							Element bTime = (Element) a.getElementsByTagName("beginTime").item(0);
							String bTimeH = bTime.getAttribute("hour");
							String bTimeM = bTime.getAttribute("minutes");
							
							Element eTime = (Element) a.getElementsByTagName("endTime").item(0);
							String eTimeH = eTime.getAttribute("hour");
							String eTimeM = eTime.getAttribute("minutes");


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
			if(a.getHuman().getName().equals(name))
				++i;
		}
		
		Activity[] aArray = new Activity[i];
		int j= 0;
		
		for (Activity a :this.activities){
			if(a.getHuman().getName().equals(name)){
				aArray[j] = a;
				++j;
			}
		}
		
		return aArray;
	}
	
	public Activity[] getActivities(){
		return this.activities;
	}

	}

